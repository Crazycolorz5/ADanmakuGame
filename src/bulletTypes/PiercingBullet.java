package bulletTypes;

import java.awt.Color;

import objects.Angle;
import objects.Unit;
import cannonTypes.BulletShooter;

public class PiercingBullet extends StraightBullet {

	public PiercingBullet(BulletShooter spawner, Angle ang, Color c, int power) {
		super(spawner, ang, c, power);
	}
	public PiercingBullet(BulletShooter spawner, int x, int y, Angle ang, Color c) {
		super(spawner,x,y,ang,c);
	}
	
	public boolean hasCollided(Unit collider)
	{
		collider.isHit(this.getPower());
		return false;
	}

}
