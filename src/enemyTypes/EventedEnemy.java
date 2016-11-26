package enemyTypes;

import java.util.ArrayList;

import engine.DrawingPanel;
import engine.Event;
import engine.EventHandler;
import objects.Position;
import objects.Unit;

public class EventedEnemy implements Unit{

	//Wrapper for Enemy that also allows events
	private Enemy myEnemy;
	private EventHandler myEvents;
	private ArrayList<Event> deathEvents;
	
	public EventedEnemy(Enemy anEnemy)
	{
		myEnemy = anEnemy;
		myEvents = new EventHandler();
		deathEvents = new ArrayList<Event>();
	}
	public void addDeathEvent(Event e)
	{
		deathEvents.add(e);
	}
	public void addEvent(Event e)
	{
		myEvents.addEvent(e);
	}
	public void nextReset()
	{
		myEvents.nextReset();
	}
	public void setResets(int n)
	{
		myEvents.setResets(n);
	}
	
	@Override
	public float surfaceSize() {
		return myEnemy.surfaceSize();
	}
	@Override
	public Position getSurfaceCenter() {
		return myEnemy.getSurfaceCenter();
	}
	@Override
	public int getX() {
		return myEnemy.getX();
	}
	@Override
	public int getY() {
		return myEnemy.getY();
	}
	@Override
	public void draw(DrawingPanel drawingPanel) {
		myEnemy.draw(drawingPanel);		
	}
	@Override
	public boolean getDispose() {
		return myEnemy.getDispose();
	}
	@Override
	public void isHit(int power) {
		myEnemy.isHit(power);
	}

	@Override
	public void tick(DrawingPanel dp) {
		if(myEvents.isEvent(dp.getEngine().getTickResets(), dp.getEngine().getTicks()));
		{
			executeEvents(myEvents.thisTick(dp.getEngine().getTickResets(), dp.getEngine().getTicks()));
		}
		myEnemy.tick(dp);
	}

	public void executeEvents(ArrayList<Event> ale)
	{
		/*	Enemy Events
		 * 	0x00 - Kill this enemy
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		//TODO
		for(Event e : ale)
		{
			if(e.getCode()==0)
			{
				myEnemy.setHP(0);
			}
		}
	}

	@Override
	public void move() {
		myEnemy.move();
	}

	@Override
	public void uponDeath() {
		if(deathEvents.size()>0)
		{
			executeEvents(deathEvents);
		}
	}

}
