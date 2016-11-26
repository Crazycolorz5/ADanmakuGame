package bulletTypes;

import java.awt.Color;

import cannonTypes.BulletShooter;

import objects.Angle;
import objects.Angle.InvalidAngleException;
import objects.Position;

public class StraightBullet extends Bullet {
	
	public StraightBullet(BulletShooter spawner, Angle ang) {
		super(spawner);
		this.setAngle(ang.getMeasure());
	}
	public StraightBullet(BulletShooter spawner, Position t) {
		super(spawner);
		try {
			this.setAngle(Angle.getSlope(this.getCenter(), t));
		} catch (InvalidAngleException e) {
			this.setAngle(0);
		}
	}
	public StraightBullet(BulletShooter spawner, Angle ang, Color c) {
		super(spawner, c);
		this.setAngle(ang.getMeasure());
	}
	public StraightBullet(BulletShooter spawner, Angle ang, Color c, int power) {
		super(spawner, c, power);
		this.setAngle(ang.getMeasure());
	}
	public StraightBullet(BulletShooter spawner, Angle ang, Color c, int power, int radius) {
		super(spawner, c, power, radius);
		this.setAngle(ang.getMeasure());
	}
	public StraightBullet(BulletShooter spawner, int x, int y, Angle ang, Color c) {
		super(spawner,x,y,c);
		this.setAngle(ang.getMeasure());
	}
	public StraightBullet(BulletShooter spawner, int x, int y, Angle ang, Color c, int power, int radius) {
		super(spawner,x,y,c,power,radius);
		this.setAngle(ang.getMeasure());
	}
	/*public StraightBullet(BulletShooter spawner, Color c, int power, BulletTemplate b)
	{
		super(spawner, c, power, b);
		this.setAngle(b.getAngle());
		//getSpawnerInfo(spawner);
		//setInitialVelocity(game);
	}*/
	public Bullet copy(BulletShooter c)
	{
			return new StraightBullet(c,
					c.getX()+c.getRelX(),
					c.getY()+c.getRelY(),
					this.getAngle(),
					this.getColor(),
					this.getPower(),
					this.getRadius());
	}
}