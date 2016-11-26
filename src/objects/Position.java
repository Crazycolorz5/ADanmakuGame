package objects;

public class Position {
	protected double x;
	protected double y;
	
	public Position(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return (int) Math.round(x);
	}
	public int getY()
	{
		return (int) Math.round(y);
	}
	public void setX(double newX)
	{
		x = newX;
	}
	public void setY(double newY)
	{
		y = newY;
	}
	public void moveX(double dX)
	{
		x += dX;
	}
	public void moveY(double dY)
	{
		y += dY;
	}
	public double getXcoord() 
	{
		return x;
	}
	public double getYcoord() 
	{
		return y;
	}
	public double distanceTo(Position other)
	{
		double dx = getX() - other.getX();
		double dy = getY() - other.getY();
		return Math.sqrt(dx*dx+dy*dy);
	}
}
