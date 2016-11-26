package objects;

import java.awt.Color;

import objects.Angle.InvalidAngleException;

public class Player extends Shooter {

	private double slowModeSpeed;// = 2.2;
	private double shootingSpeed;// = 4.5;
	private double nonShootingSpeed;// = 5;
	
	private boolean slow;
	private double XVRef;
	private double YVRef;
	//private ArrayList<PlayerCannon> pcan;
	
	public Player(int x, int y)
	{
		super(x,y,Color.BLUE,1,10);
		slow = false;
		speed = 0;
		XVRef = 0;
		YVRef = 0;
		setNotShooting();
		//pcan = new ArrayList<PlayerCannon>();
	}
	
	public Player(int x, int y, double slowModeSpeed, double shootingSpeed, double nonShootingSpeed)
	{
		super(x,y,Color.BLUE,1,10);
		slow = false;
		speed = 0;
		XVRef = 0;
		YVRef = 0;
		
		this.slowModeSpeed = slowModeSpeed;
		this.shootingSpeed = shootingSpeed;
		this.nonShootingSpeed = nonShootingSpeed;
		
		setNotShooting();
		//pcan = new ArrayList<PlayerCannon>();
	}
	
	public void setSpeeds(double slowModeSpeed, double shootingSpeed, double nonShootingSpeed)
	{
		this.slowModeSpeed = slowModeSpeed;
		this.shootingSpeed = shootingSpeed;
		this.nonShootingSpeed = nonShootingSpeed;
	}
	public void setSlow() 
	{
		slow = true;
		this.getCenter().setColor(Color.LIGHT_GRAY);
		this.setSpeed();
	}
	public void setFast()
	{
		slow = false;
		this.getCenter().setColor(getColor());
		this.setSpeed();
	}
	public void setShooting() 
	{
		isShooting = true;
		super.setShooting();
		this.setSpeed();
	}
	public void setNotShooting()
	{
		isShooting = false;
		super.setNotShooting();
		this.setSpeed();
	}
	public void move()
	{
		if(XVRef!=0||YVRef!=0)
		{
			super.move();
		}
	}

	public void moveUp() {
		YVRef += 3;
		refreshAngle();
	}
	public void moveDown() {
		YVRef -= 3;
		refreshAngle();
	}
	public void moveRight() {
		XVRef += 3;
		refreshAngle();
	}
	public void moveLeft() {
		XVRef -= 3;
		refreshAngle();
	}
	public void refreshAngle() {
		if(XVRef!=0||YVRef!=0)
		{
			if(slow)
				speed = slowModeSpeed;
			else if(isShooting)
				speed = shootingSpeed;
			else if(!isShooting)
				speed = nonShootingSpeed;
			try {
				this.getAngle().setSlope(this.getCenter(),new Position(this.getCenter().getXcoord()+XVRef,this.getCenter().getYcoord()-YVRef));
			} catch (InvalidAngleException e) {
				this.getAngle().change(0);
			}
		}
		else
			speed = 0;
	}
	public void setSpeed()
	{
		if(XVRef!=0||YVRef!=0)
		{
			if(slow)
				speed = slowModeSpeed;
			else if(isShooting)
				speed = shootingSpeed;
			else if(!isShooting)
				speed = nonShootingSpeed;
		}
		else
			speed = 0;
	}
	public float surfaceSize()
	{
		return getCenter().getPointRadius();
	}
	public boolean isSlow()
	{
		return slow;
	}
	/*public int getRadius() {
		return radius;
	}*/
}
