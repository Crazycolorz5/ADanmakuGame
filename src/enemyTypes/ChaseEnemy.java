package enemyTypes;

import java.awt.Color;

import objects.ObjCenter;
import objects.Position;
import objects.Angle.InvalidAngleException;

public class ChaseEnemy extends TargetEnemy {

	public ChaseEnemy(int x, int y, Position t, int HP) {
		super(x, y, t, HP);
	}

	public ChaseEnemy(int x, int y, Position t, int HP, double speed) {
		super(x, y, t, HP, speed);
	}

	public ChaseEnemy(int x, int y, Position t, Color c, int HP) {
		super(x, y, t, c, HP);
	}

	public ChaseEnemy(int x, int y, Position t, Color c, int HP, double speed) {
		super(x, y, t, c, HP, speed);
	}
	public ChaseEnemy(int HP, double speed,Position t) {
		super(0,0, t, Color.RED, HP, speed);
	}

	public void setChaseAngle() {
		
		try {
			this.getAngle().setSlope(this.getCenter(), getTarget());
			this.getAngle().change(this.getAngle().getMeasure()+.34907*(Math.random()-.5));
			//Angle +-10 degrees/.34907
		} catch (InvalidAngleException e) {
			this.getAngle().change(2*Math.PI*Math.random());
		}
	}
	public void move(){
		if(getTarget()!=null)
		{
			//didHit();
			setChaseAngle();
			
			super.move(getXVelocity(), getYVelocity());
		}
		else
		{
			super.move();
		}
	}
}
