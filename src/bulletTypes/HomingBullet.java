package bulletTypes;

import java.awt.Color;
import java.util.ArrayList;

import cannonTypes.BulletShooter;

import objects.Angle;
import objects.Angle.InvalidAngleException;
//import objects.ObjCenter;
import objects.Unit;
//import objects.Unit;


public class HomingBullet extends Bullet {

	ArrayList<Unit> targets;
	double maxHome = Math.PI/72; //Maximum angle change in 1 tick
	
	public HomingBullet(BulletShooter spawner, Color c, int power, Unit target, double initialAngle)
	{
		super(spawner, c, power);
		this.getAngle().change(initialAngle);
	}
	public HomingBullet(BulletShooter spawner, Color c, int power, int radius, Unit target, double initialAngle)
	{
		super(spawner, c, power, radius);
		targets = new ArrayList<Unit>();
		targets.add(target);
		this.getAngle().change(initialAngle);
	}
	public HomingBullet(BulletShooter spawner, int power, int radius, Unit target, double initialAngle)
	{
		super(spawner, power, radius);
		targets = new ArrayList<Unit>();
		targets.add(target);
		this.getAngle().change(initialAngle);
	}
	public HomingBullet(BulletShooter spawner, Color c, int power, int radius,ArrayList<Unit> targs, double initialAngle)
	{
		super(spawner, c, power, radius);
		targets = targs;
		this.getAngle().change(initialAngle);
	}
	public HomingBullet(BulletShooter spawner, int power, int radius, Unit target, double initialAngle, double maxChangeAngle)
	{
		super(spawner, power, radius);
		targets = new ArrayList<Unit>();
		targets.add(target);
		this.getAngle().change(initialAngle);
		maxHome = maxChangeAngle;
	}
	public HomingBullet(BulletShooter spawner, Color c, int power, int radius,ArrayList<Unit> targs, double initialAngle, double maxChangeAngle)
	{
		super(spawner, c, power, radius);
		targets = targs;
		this.getAngle().change(initialAngle);
		maxHome = maxChangeAngle;
	}
	/*public HomingBullet(BulletShooter spawner, Color c, int power,BulletTemplate b,ArrayList<Unit> targs, double initialAngle) {
		super(spawner, c, power, b);
		targets = targs;
		this.getAngle().change(initialAngle);
	}*/
	@Override
	public Bullet copy(BulletShooter c) {
		HomingBullet copier = new HomingBullet(
				c,
				this.getColor(),
				this.getPower(),
				(int)this.surfaceSize(),
				this.getTargets(),
				this.getAngle().getMeasure());
		return copier;
	}
	public ArrayList<Unit> getTargets()
	{
		return targets;
	}
	public void setTargets(ArrayList<Unit> newTargs)
	{
		targets = newTargs;
	}
	public Unit getNearestTarget() throws NoTarget
	{
		if(targets.size()>0)
		{
			double leastDistance = this.getSurfaceCenter().distanceTo(targets.get(0).getSurfaceCenter());
			Unit t = targets.get(0);
			for(Unit s: targets)
			{
				if(this.getSurfaceCenter().distanceTo(s.getSurfaceCenter())<leastDistance)
				{
					t = s;
					leastDistance = this.getSurfaceCenter().distanceTo(s.getSurfaceCenter());
				}
			}
			return t;
		}
		else
			throw new NoTarget();
	}
	@SuppressWarnings("serial")
	private class NoTarget extends Exception
	{
	}
	public void changeDirection() throws NoTarget
	{
		try {
			Unit target = getNearestTarget();
			Angle change = new Angle(Angle.getSlope(this.getSurfaceCenter(), target.getSurfaceCenter()).getMeasure() - this.getAngle().getMeasure());
			if(change.getMeasure()<maxHome||change.getMeasure()>2*Math.PI-maxHome)
			{
				this.getAngle().setSlope(this.getSurfaceCenter(), target.getSurfaceCenter());
			}
			else if(change.getMeasure()<=Math.PI)
			{
				this.getAngle().change(this.getAngle().getMeasure()+maxHome);
			}
			else if(change.getMeasure()>Math.PI)
				this.getAngle().change(this.getAngle().getMeasure()-maxHome);
		} catch (InvalidAngleException e) {
		}
	}
	public void move()
	{
		try {
			changeDirection();
		} catch (NoTarget e) {
			this.setX(0);//setting x and y to 0 will
			this.setY(0);//put it outside the boundary, thus deleting it.           
		}
		super.move();
	}
}
