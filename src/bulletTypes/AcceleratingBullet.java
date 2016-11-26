package bulletTypes;

import java.awt.Color;

import cannonTypes.BulletShooter;

import objects.Angle;

public class AcceleratingBullet extends StraightBullet{
	
	public boolean isAccelerating;
	public double acceleration;
	public double lowestSpeed = .2;
	public double terminalSpeed = 0; //0=unlimited
	
	public AcceleratingBullet(BulletShooter spawner, Angle ang, double acceleration) {
		super(spawner, ang);
		this.acceleration = acceleration;
		isAccelerating = true;
	}
	public AcceleratingBullet(BulletShooter spawner, Angle ang, Color c, double acceleration) {
		super(spawner, ang, c);
		this.acceleration = acceleration;
		isAccelerating = true;
	}
	public AcceleratingBullet(BulletShooter spawner, Angle ang, Color c, int power, double acceleration) {
		super(spawner, ang, c, power);
		this.acceleration = acceleration;
		isAccelerating = true;
	}
	public AcceleratingBullet(BulletShooter spawner, int x, int y, Angle ang, Color c, double acceleration) {
		super(spawner,x,y,ang,c);
		this.acceleration = acceleration;
		isAccelerating = true;
	}
	public AcceleratingBullet(BulletShooter spawner, Angle ang, double acceleration, double lS, double tS) {
		super(spawner, ang);
		this.acceleration = acceleration;
		isAccelerating = true;
		lowestSpeed = lS;
		terminalSpeed = tS;
	}
	public AcceleratingBullet(BulletShooter spawner, Angle ang, Color c, double acceleration, double lS, double tS) {
		super(spawner, ang, c);
		this.acceleration = acceleration;
		isAccelerating = true;
		lowestSpeed = lS;
		terminalSpeed = tS;
	}
	public AcceleratingBullet(BulletShooter spawner, Angle ang, Color c, int power, double acceleration, double lS, double tS) {
		super(spawner, ang, c, power);
		this.acceleration = acceleration;
		isAccelerating = true;
		lowestSpeed = lS;
		terminalSpeed = tS;
	}
	public AcceleratingBullet(BulletShooter spawner, int x, int y, Angle ang, Color c, double acceleration, double lS, double tS) {
		super(spawner,x,y,ang,c);
		this.acceleration = acceleration;
		isAccelerating = true;
		lowestSpeed = lS;
		terminalSpeed = tS;
	}
	/*public AcceleratingBullet(BulletShooter spawner, Color c, int power, BulletTemplate b)
	{
		super(spawner, c, power, b);
		isAccelerating = true;
	}*/
	public Bullet copy(BulletShooter c)
	{
		return new AcceleratingBullet(c, this.getAngle(), this.getColor(), this.getPower(), acceleration);
	}
	public void move()
	{
		if(isAccelerating)
			this.setSpeed(this.getAbsVel()+acceleration);
		if(this.getAbsVel()<lowestSpeed)
			this.setSpeed(lowestSpeed);
		if(this.getAbsVel()>terminalSpeed&&terminalSpeed!=0)
			this.setSpeed(terminalSpeed);
		super.move();
	}
	public void stopAccelerating()
	{
		isAccelerating = false;
	}
	public void startAccelerating()
	{
		isAccelerating = true;
	}
}
