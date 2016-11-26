package hardcodedLevels;

import java.util.ArrayList;

import engine.DrawingPanel;
import engine.Event;
import engine.EventHandler;

public class EndlessEventHandler extends EventHandler{
	
	private boolean randomizedEnemyIsGo;
	
	public EndlessEventHandler()
	{
		super();
		randomizedEnemyIsGo = false;
	}
	
	@Override
	public boolean isEvent(int numResets, int tick)
	{
		boolean scheduled = (tick==0||tick%75==74||tick%320==159||tick%320==319);
		randomizedEnemyIsGo = (Math.random()<0.0014);
		return scheduled||randomizedEnemyIsGo;
	}
	
	@Override
	public ArrayList<Event> thisTick(int numResets, int tick)
	{	
		ArrayList<Event> spawnAnEnemy = new ArrayList<Event>();
		
		if(randomizedEnemyIsGo)
		{
			spawnAnEnemy.add(new Event(tick, (byte)1, 0, 0, 0, 0x0002));
			randomizedEnemyIsGo = false;
		}
		//5% chance per tick
			
		if(tick==0)
		{
			spawnAnEnemy.add(new Event(tick, (byte)1, 0, 0, 0, 0x0002));
			spawnAnEnemy.add(new Event(tick, (byte)1, 0, 0, 0, 0x0002));
		}
		//spawn enemy code 0, at 0,0 then randomizePosition
		
		
		if(tick%75==74)
		{
			spawnAnEnemy.add(new Event(tick, (byte)1, 1, 0, 0, 0x0002));
		//spawn enemy 1, at 0,0 then randomizePositionAtTop
		}
		
		
		if(tick%320==159)
		{
			spawnAnEnemy.add(new Event(tick, (byte)1, 2, DrawingPanel.pWidth, 0, 0x0004));
		//spawn enemy 2, at Width,0, then setDispose
			spawnAnEnemy.add(new Event(tick, (byte)1, 3, 0, 0, 0x0004));
		//spawn enemy 3, at 0,0, then setDispose
		}
		
		if(tick%320==319)
		{
			spawnAnEnemy.add(new Event(tick, (byte)1, 4, DrawingPanel.pWidth, 0, 0x0004));
		//spawn enemy 4, at Width,0, then setDispose
			spawnAnEnemy.add(new Event(tick, (byte)1, 5, 0, 0, 0x0004));
		//spawn enemy 5, at 0,0, then setDispose
		}
		
		return spawnAnEnemy;
	}
}
