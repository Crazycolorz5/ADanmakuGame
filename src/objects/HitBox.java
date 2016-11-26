package objects;

import java.awt.Color;

import engine.DrawingPanel;

public abstract class HitBox implements Unit {

	protected int radius;
	private Color myColor;
	protected ObjCenter pos;
	protected double speed;
	protected Angle slope;
	boolean dispose=false;
	
	public HitBox(int x, int y)
	{
		myColor = Color.BLUE;
		pos = new ObjCenter(x,y, myColor);
		radius = 12;
		speed = 2;
		slope = new Angle(0);
	}
	public HitBox(int x, int y, int radius)
	{
		myColor = Color.BLUE;
		pos = new ObjCenter(x,y, myColor);
		this.radius = radius;
		speed = 2;
		slope = new Angle(0);
	}
	public HitBox(int x, int y, Color c)
	{
		myColor = c;
		pos = new ObjCenter(x,y, myColor);
		this.radius = 12;
		speed = 2;
		slope = new Angle(0);
	}
	public HitBox(int x, int y, int radius, Color c)
	{
		myColor = c;
		pos = new ObjCenter(x,y, myColor);
		this.radius = radius;
		speed = 2;
		slope = new Angle(0);
	}
	public HitBox(int x, int y, double speed)
	{
		myColor = Color.BLUE;
		pos = new ObjCenter(x,y, myColor);
		this.speed = speed;
		slope = new Angle(0);
	}
	public HitBox(int x, int y, int radius, Color c, double speed)
	{
		myColor = c;
		pos = new ObjCenter(x,y, myColor);
		this.radius = radius;
		speed = 2;
		slope = new Angle(0);
		this.speed = speed;
		slope = new Angle(0);
	}
	
	public void draw(DrawingPanel dp)
	{
		int x = pos.getX();
		int y = pos.getY();
		dp.getDBG().setColor(myColor);
		dp.getDBG().fillOval(x-radius, y-radius, radius*2, radius*2);
	}
	public int getRadius()
	{
		return radius;
	}
	public ObjCenter getCenter()
	{
		return pos;
	}
	public Color getColor()
	{
		return myColor;
	}
	public void setDispose()
	{
		dispose = true;
	}
	public boolean getDispose()
	{
		return dispose;
	}
	public void setX(double newx)
	{
		this.pos.setX(newx);
	}
	public void setY(double newy)
	{
		this.pos.setY(newy);
	}
	public abstract void tick(DrawingPanel dp);
	public void move()
	{
		pos.setX(pos.getXcoord() + getXVelocity());
		pos.setY(pos.getYcoord() + getYVelocity());
	}
	public void move(double xVel, double yVel)
	{
		pos.setX(pos.getXcoord() + xVel);
		pos.setY(pos.getYcoord() + yVel);
	}
	public int getX()
	{
		return pos.getX();
	}
	public int getY()
	{
		return pos.getY();
	}
	public double getXVelocity()
	{
		return speed*slope.getCosAngle();
	}
	public double getYVelocity()
	{
		return -speed*slope.getSinAngle();
	}
	public void setSpeed(double spd)
	{
		speed = spd;
	}
	public void setAngle(Angle newAngle)
	{
		slope = newAngle;
	}
	public Angle getAngle()
	{
		return slope;
	}
	public void setAngle(double newAngle)
	{
		slope.change(newAngle);
	}
	public double getAbsVel()
	{
		return speed;
	}
	@Override
	public float surfaceSize() {
		return radius;
	}
	@Override
	public Position getSurfaceCenter() {
		return this.getCenter();
	}
	public void isHit(int power)
	{
		//Do nothing
	}
	public void uponDeath()
	{
		//Do nothing special
	}
	public void setPosition(Position p)
	{
		this.setX(p.getXcoord());
	}
}
