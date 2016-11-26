package engine;

import java.util.ArrayList;

public class EventHandler {
	private ArrayList<ArrayList<Event>> events;
	private EventWriter writer;
	
	private class EventWriter {
		int resetCount = 0;
		public EventWriter()
		{}
		public void write(Event e)
		{
			events.get(resetCount).add(e);
		}
		public void nextReset()
		{
			resetCount++;
			events.add(new ArrayList<Event>());
		}
		public void setResetCount(int n)
		{
			resetCount = n;
		}
	}
	
	
	public EventHandler()
	{
		events = new ArrayList<ArrayList<Event>>();
		events.add(new ArrayList<Event>());
		writer = new EventWriter();
	}
	
	public void addEvent(Event e)
	{
		writer.write(e);
	}
	public void addEvents(ArrayList<Event> es)
	{
		for(Event e: es)
		{
			writer.write(e);
		}
	}
	public void nextReset()
	{
		writer.nextReset();
	}
	public void setResets(int n)
	{
		while(events.size()<n)
		{
			events.add(new ArrayList<Event>());
			n++;
		}
		writer.setResetCount(n);	
	}
	public boolean isEvent(int tickResets, int tick)
	{
		for(Event e : events.get(tickResets))
		{
			if(e.getTime()==tick)
				return true;
		}
		return false;
	}
	public ArrayList<Event> thisTick(int tickResets, int tick)
	{
		ArrayList<Event> nowEvents = new ArrayList<Event>();
		int c = 0;
		while(c<events.get(tickResets).size())
		{
			if(events.get(tickResets).get(c).getTime()==tick)
			{
				nowEvents.add(events.get(tickResets).remove(c));
				c--;
			}
			c++;
		}
		return nowEvents;
	}
}
