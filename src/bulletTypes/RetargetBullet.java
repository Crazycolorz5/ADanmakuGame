package bulletTypes;

import java.awt.Color;

import cannonTypes.BulletShooter;

import objects.Angle.InvalidAngleException;
import objects.Position;

public class RetargetBullet extends TargetBullet{

	private int count;
	private int retargPeriod;
	private int maxRetargs;
	
	public RetargetBullet(BulletShooter spawner, int x, int y, Position target, Color c, int period, int max) {
		super(spawner, x, y, target, c);
		retargPeriod = period;
		maxRetargs = max;
		count = 0;
	}
	public void move()
	{
		count++;
		if(count>=retargPeriod&&maxRetargs>0)
		{
			count = 0;
			try {
				this.getAngle().setSlope(this.getCenter(), this.getTarget());
			} catch (InvalidAngleException e) {
				this.getAngle().change(Math.PI*2*Math.random());
			}
			maxRetargs--;
		}
		super.move();
	}
}
