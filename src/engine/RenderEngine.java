package engine;

public class RenderEngine implements Runnable {
	
	private DrawingPanel parent;
	private boolean render = true;
	private boolean running = true;
	
	public RenderEngine(DrawingPanel p)
	{
		parent = p;
	}
	
	@Override
	public void run() {
		long beforeTime, afterTime,timeDiff,sleepTime;
		long overSleepTime = 0L;
		
		while(running)
		{
			synchronized(this)
			{
				if(render)
				{
					try
					{
						render = false;
						beforeTime = System.nanoTime();
						parent.paintScreen();
						parent.gameRender();
						afterTime = System.nanoTime();
						timeDiff = afterTime - beforeTime;
						sleepTime = (parent.period-timeDiff);
						if(sleepTime<0)
						{
							overSleepTime = -sleepTime;
							
							System.out.println("Render Slowdown detected(ns)!: " + overSleepTime);
						}
					}
					catch(java.util.ConcurrentModificationException e)
					{
						System.out.println("2 renders tried running at once!");
					}
				}
				if(!render)
				{
					try {
						this.wait();
					} catch (InterruptedException e) {}
				}
			}
		}
	}
	public void markToRender()
	{
		render = true;
	}
}
