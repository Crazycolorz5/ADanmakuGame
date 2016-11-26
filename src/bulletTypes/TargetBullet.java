package bulletTypes;

import java.awt.Color;

import cannonTypes.BulletShooter;

import objects.Angle;
import objects.Position;
import objects.Angle.InvalidAngleException;

public class TargetBullet extends Bullet {
	
	private Position targ;
	private Angle offset = new Angle(0); //Offset to launch, relative to shooter-->target
	
	public TargetBullet(BulletShooter bs, Position target) {
		super(bs);
		try {
			this.getAngle().setSlope(this.getCenter(), target);
			//this.getAngle().change(this.getAngle().getMeasure()+offset.getMeasure());
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		targ = target;
	}
	public TargetBullet(BulletShooter bs, Position target, Angle offset) {
		super(bs);
		this.offset = offset;
		try {
			this.getAngle().setSlope(this.getCenter(), target);
			this.getAngle().change(this.getAngle().getMeasure()+this.offset.getMeasure());			
			//this.getAngle().change(this.getAngle().getMeasure()+offset.getMeasure());
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		targ = target;
	}
	public TargetBullet(BulletShooter bs, Position target, boolean uniqueTargets) {
		super(bs);
		try {
			this.getAngle().setSlope(this.getCenter(), target);
			//this.getAngle().change(this.getAngle().getMeasure()+offset.getMeasure());
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		if(!uniqueTargets)
			targ = new Position(target.getXcoord(), target.getYcoord());
		else
			targ = target;
	}
	public TargetBullet(BulletShooter bs, Position target, Angle offset, boolean uniqueTargets) {
		super(bs);
		this.offset = offset;
		try {
			this.getAngle().setSlope(this.getCenter(), target);
			this.getAngle().change(this.getAngle().getMeasure()+this.offset.getMeasure());			
			//this.getAngle().change(this.getAngle().getMeasure()+offset.getMeasure());
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		if(!uniqueTargets)
			targ = new Position(target.getXcoord(), target.getYcoord());
		else
			targ = target;
	}
	public TargetBullet(BulletShooter spawner, Position target, int power) {
		super(spawner, power);
		try {
			this.getAngle().setSlope(this.getCenter(), target);
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		targ = target;
	}
	public TargetBullet(BulletShooter spawner, int x, int y, Position target, Color c) {
		super(spawner,x,y,c);
		try {
			this.getAngle().setSlope(this.getCenter(), target);
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		targ = target;
	}
	public TargetBullet(BulletShooter spawner, Position target, double offset) {
		super(spawner);
		this.offset = new Angle(offset);
		try {
			this.getAngle().setSlope(this.getCenter(), target);
			this.getAngle().change(this.getAngle().getMeasure()+this.offset.getMeasure());
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		targ = target;
	}
	
	public TargetBullet(BulletShooter spawner, Position target, int power, double offset) {
		super(spawner, power);
		this.offset = new Angle(offset);		
		try {
			this.getAngle().setSlope(this.getCenter(), target);
			this.getAngle().change(this.getAngle().getMeasure()+this.offset.getMeasure());
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		targ = target;
	}
	public TargetBullet(BulletShooter spawner, int x, int y, Position target, Color c, int power, int radius, Angle offset) {
		super(spawner,x,y,c,power,radius);
		this.offset = offset;
		try {
			this.getAngle().setSlope(this.getCenter(), target);
			this.getAngle().change(this.getAngle().getMeasure()+this.offset.getMeasure());			
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
		targ = target;
	}
	
	@Override
	public Bullet copy(BulletShooter c)
	{
		return new TargetBullet(c,
				c.getX()+c.getRelX(),
				c.getY()+c.getRelY(),
				targ,
				this.getColor(),
				this.getPower(),
				this.getRadius(),
				this.offset);
	}
	public Position getTarget()
	{
		return targ;
	}
	public Angle getOffset()
	{
		return offset;
	}
}
