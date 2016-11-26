package engine;

import hardcodedLevels.Level;

import java.util.ArrayList;

import enemyTypes.Enemy;

public class Engine implements Runnable {

	public long period = 16666666L;   //nanoseconds
	private int ticks=-1;
	private int tickResets = 0;
	private DrawingPanel display;
	private RenderEngine rend;
	private Thread renderThread;
	private EventHandler gameEvents;
	private Level currentLevel;
	private boolean running;
	private boolean gameOver;
	private boolean catchUp = false;
	
	public Engine(DrawingPanel display)
	{
		this.display = display;
		running = false;
	}
	
	
	@Override
	public void run() {
		rend = new RenderEngine(display);
		renderThread = new Thread(rend);
		renderThread.start();
			
		long beforeTime, afterTime,timeDiff,sleepTime;
		long overSleepTime = 0L;
		while(running&&!gameOver){
			beforeTime = System.nanoTime();
			render();
			display.gameUpdate();
			//display.paintScreen();
			//display.gameRender();
			
			ticks++;
			if(gameEvents.isEvent(tickResets, ticks))
			{
				ArrayList<Event> temp = gameEvents.thisTick(tickResets, ticks);
				for(Event e: temp)
					executeEvent(e);
			}
			
			afterTime = System.nanoTime();
			
			timeDiff = afterTime - beforeTime; //nano seconds
			
			//if(catchUp)
			//	sleepTime = (period-timeDiff)-overSleepTime;
			//else
				sleepTime = (period-timeDiff);
			
			if(sleepTime > 0){
				long sleepTimeMillis = sleepTime/1000000;
				try {
					Thread.sleep(sleepTimeMillis, (int) (sleepTime%1000000));
				} catch (InterruptedException e) {}
				
				//overSleepTime = System.nanoTime() - beforeTime - period;
			}
			else if(sleepTime<0&&!gameOver)
			{
				overSleepTime = -sleepTime;
				
				System.out.println("Slowdown detected(ns)!: " + overSleepTime);
			}
			

		}
	}
	private void executeEvent(Event e) {
		if(e.getCode()==0) //Game Over
		{
			display.playerIsHit();
		}
		if(e.getCode()==1) //Add Enemy
		{
			Enemy EToAdd = currentLevel.getEnemyCopy(e.getData()[0]);
			display.addEnemy(EToAdd, e.getData()[1], e.getData()[2]);
			//data[3]'s LO Byte:
			//7 6 5 4    3 2 1 0
			// 0 = randomize position
			// 1 = randomize position at top (takes priority over randomize position)
			// 2 = setDispose
			//
			//
			if(getBit(e.getData()[3], 0))
				display.randomizeUnitPosition(EToAdd);
			if(getBit(e.getData()[3], 1))
				display.randomizeUnitPositionAtTop(EToAdd);
			if(getBit(e.getData()[3], 2))
				EToAdd.setDispose();
		}
		if(e.getCode()==2) //Add PCan
		{
			display.getPlayer().addCannon(currentLevel.getBonusCannon(e.getData()[0]));
		}
		if(e.getCode()==3) //Destroy non-Bosses and remove all bullets.
		{
			display.destroyAllEnemies();
			display.removeAllBullets();
		}
	}

	private static boolean getBit(int b, int position)
	{
		//low order Byte - 
		//7 6 5 4   3 2 1 0
		b >>>= position;
		return b%2==1;		
	}
	/*private static boolean getBit(byte b, int position)
	{
		//1 Byte - 
		//7 6 5 4   3 2 1 0
		b >>>= position;
		return b%2==1;		
	}*/
	
	public void start()
	{
		running = true;
	}
	public void stop()
	{
		running = false;
	}
	public void lose()
	{
		gameOver = true;
		/*display.paintScreen();
		display.gameRender();*/
		render();
	}
	/*public static void accuratePause(long nanos)
	{
		long startWait = System.nanoTime();
		long target = startWait + nanos;
		while(System.nanoTime()<target)
		{
			;
		}
	}*/
	public void resetTicks()
	{
		tickResets++;
		ticks = -1;
	}
	public EventHandler getEhandler()
	{
		return gameEvents; 
	}
	public int getTickResets()
	{
		return tickResets;
	}
	public int getTicks()
	{
		return ticks;
	}
	public void setPeriod(long period)
	{
		this.period = period;
	}
	public void render()
	{
		synchronized(rend)
		{
			rend.markToRender();
			rend.notify();
		}
	}
	public void setLevel(Level l)
	{
		currentLevel = l;
		gameEvents = l.getEvents();
		
		display.getPlayer().setCannonsCopy(l.getPlayerCannons());
	}
	public void setCatchUp()
	{
		catchUp = true;
	}
}
