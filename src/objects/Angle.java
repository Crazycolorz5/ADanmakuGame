package objects;

public class Angle {
	//Always [0,2pi)
	private double angle;
	public final static double pi = 3.14159265358979;
	
	public Angle(double ang)
	{
		angle = ang;
		forceToTwoPi();
	}
	public Angle(Position start, Position end) throws InvalidAngleException
	{
		double dy = start.getYcoord()-end.getYcoord();
		double dx = end.getXcoord()-start.getXcoord();
		if(dx<0)
		{
			if(dy<0)
			{//3rd quadrant
				angle = Math.atan(dy/dx)+Math.PI;
			}
			else if(dy>0)
			{//2nd quadrant
				angle = Math.atan(dy/dx)+Math.PI;
			}
		}
		else if(dx>0)
		{//1st, -4th quadrant
			angle = Math.atan(dy/dx);
		}
		else
		{//axes
			if(dy>0)
			{
				angle = Math.PI/2;
			}
			else if(dy<0)
			{
				angle = 3*Math.PI/2;
			}
			else if(dx>0)
			{
				angle = 0;
			}
			else if(dx<0)
			{
				angle = Math.PI;
			}
			else
				throw new InvalidAngleException();
		}
		forceToTwoPi();
	}
	public void setSlope(Position start, Position end) throws InvalidAngleException
	{
		double dy = start.getYcoord()-end.getYcoord();
		double dx = end.getXcoord()-start.getXcoord();
		if(dx<0)
		{
			if(dy<0)
			{//3rd quadrant
				angle = Math.atan(dy/dx)+Math.PI;
			}
			else if(dy>0)
			{//2nd quadrant
				angle = Math.atan(dy/dx)+Math.PI;
			}
			else
				angle = Math.PI;
				//negative x axis
		}
		else if(dx>0)
		{//1st, -4th quadrant
			if(dy!=0)
				angle = Math.atan(dy/dx);
			else
				angle = 0;
		}
		else
		{//axes
			if(dy>0)
			{
				angle = Math.PI/2;
			}
			else if(dy<0)
			{
				angle = 3*Math.PI/2;
			}
			else
				throw new InvalidAngleException();
			//origin
		}
		forceToTwoPi();
	}
	public double getSinAngle()
	{
		return Math.sin(angle);
	}
	public double getCosAngle()
	{
		return Math.cos(angle);
	}
	
	public double getMeasure()
	{
		return angle;
	}
	@SuppressWarnings("serial")
	public class InvalidAngleException extends Exception
	{
	}


	public void change(double newAngle) {
		angle = newAngle;
		forceToTwoPi();
	}
	private void forceToTwoPi()
	{
		while(angle<0)
		{
			angle += 2*Math.PI;
		}
		while(angle>=2*Math.PI)
		{
			angle -= 2*Math.PI;
		}
	}
	public static Angle getSlope(Position start, Position end) throws InvalidAngleException
	{
		Angle returner = new Angle(0);
		double dy = start.getYcoord()-end.getYcoord();
		double dx = end.getXcoord()-start.getXcoord();
		if(dx<0)
		{
			if(dy<0)
			{//3rd quadrant
				returner.change(Math.atan(dy/dx)+Math.PI);
			}
			else if(dy>0)
			{//2nd quadrant
				returner.change(Math.atan(dy/dx)+Math.PI);
			}
			else
				returner.change(Math.PI);
				//negative x axis
		}
		else if(dx>0)
		{//1st, -4th quadrant
			if(dy!=0)
				returner.change(Math.atan(dy/dx));
			else
				returner.change(0);
		}
		else
		{//axes
			if(dy>0)
			{
				returner.change(Math.PI/2);
			}
			else if(dy<0)
			{
				returner.change(3*Math.PI/2);
			}
			else
				throw returner.new InvalidAngleException();
			//origin
		}
		returner.forceToTwoPi();
		return returner;
	}
}
