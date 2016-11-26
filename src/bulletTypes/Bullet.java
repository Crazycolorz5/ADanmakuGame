package bulletTypes;

import java.awt.Color;

import objects.HitBox;
import objects.OnSurface;
import objects.Position;
import objects.Surface;
import objects.Unit;
import cannonTypes.BulletShooter;
import engine.DrawingPanel;
//import engine.DrawingPanel;
//import objects.Player;
//import objects.Shooter;

public abstract class Bullet extends HitBox implements OnSurface {

	public static final int DEFAULT_BULLET_RADIUS = 4;
	private int power = 1;
	
	
	public Bullet(BulletShooter bs)
	{
		super(bs.getX(), bs.getY(), DEFAULT_BULLET_RADIUS, Color.MAGENTA);
		//this.setSpeed(spawner.getShootSpeed());
	}
	public Bullet(BulletShooter spawner, Color c)
	{
		super(spawner.getX(), spawner.getY(), DEFAULT_BULLET_RADIUS, c);
		//this.setSpeed(spawner.getShootSpeed());
	}
	public Bullet(BulletShooter spawner, int power)
	{
		super(spawner.getX(), spawner.getY(), DEFAULT_BULLET_RADIUS, Color.MAGENTA);
		//this.setSpeed(spawner.getShootSpeed());
		this.power = power;
	}
	public Bullet(BulletShooter spawner, Color c, int power)
	{
		super(spawner.getX(), spawner.getY(), DEFAULT_BULLET_RADIUS, c);
		//this.setSpeed(spawner.getShootSpeed());
		this.power = power;
	}
	/*public Bullet(BulletShooter spawner, Color c, int power, BulletTemplate b)
	{
		super(spawner.getX(), spawner.getY(), b.surfaceSize(), c);
		//this.setSpeed(spawner.getShootSpeed());
		this.power = power;
	}*/
	public Bullet(BulletShooter spawner, int power, int radius)
	{
		super(spawner.getX(), spawner.getY(), radius, Color.MAGENTA);
		//this.setSpeed(spawner.getShootSpeed());
		this.power = power;
	}
	public Bullet(BulletShooter spawner, Color c, int power, int radius)
	{
		super(spawner.getX(), spawner.getY(), radius, c);
		//this.setSpeed(spawner.getShootSpeed());
		this.power = power;
	}
	public Bullet(BulletShooter spawner, int x, int y, Color c)
	{
		super(x, y, DEFAULT_BULLET_RADIUS, c);
		//this.setSpeed(spawner.getShootSpeed());
	}
	public Bullet(BulletShooter spawner, int x, int y, Color c, int power, int radius)
	{
		super(spawner.getX(), spawner.getY(), radius, c);
		this.power = power;
	}
	public Bullet(BulletShooter spawner, int x, int y, Color c, int power, int radius, double speed)
	{
		super(spawner.getX(), spawner.getY(), radius, c);
		this.power = power;
		this.speed = speed;
	}
	public boolean isOnSurface(Surface s)
	{
		Position point = s.getSurfaceCenter();
		return point.distanceTo(this.getCenter())<(this.surfaceSize()+s.surfaceSize()-.5);
	}
	public abstract Bullet copy(BulletShooter c);
	public void modifyAngle(double amount)
	{
		this.setAngle(this.getAngle().getMeasure()+amount);
	}
	public int getPower()
	{
		return power;
	}
	public void tick(DrawingPanel dp)
	{}//Default to do nothing
	
	//returns true if the bullet should be removed upon collision.
	public boolean hasCollided(Unit collider)
	{
		collider.isHit(this.getPower());
		//System.out.println("Bullet.hasCollided called");
		return true;
	}
}