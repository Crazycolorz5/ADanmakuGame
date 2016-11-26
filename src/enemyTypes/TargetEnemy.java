package enemyTypes;

import java.awt.Color;

//import bulletTypes.TargetedDirectionBullet;

//import engine.DrawingPanel;

//import objects.Angle;
import objects.Angle.InvalidAngleException;
import objects.Position;

public class TargetEnemy extends Enemy {

	//Chases the player indefinitely.
	//Shoots aimed bullets at the player.
	
	private Position target;
	
	public TargetEnemy(int x, int y, Position t, int HP) {
		super(x, y, Color.RED,HP);
		setTarget(t);
	}
	public TargetEnemy(int x, int y, Position t,  int HP, double speed) {
		super(x, y, Color.RED,HP);
		setTarget(t);
		this.setSpeed(speed);
	}
	public TargetEnemy(int x, int y, Position t, Color c, int HP) {
		super(x, y, c,HP);
		setTarget(t);
	}
	public TargetEnemy(int x, int y, Position t, Color c, int HP, double speed) {
		super(x, y, c,HP);
		setTarget(t);
		this.setSpeed(speed);
	}
	public TargetEnemy(int HP, double speed, Position t) {
		super(0, 0, Color.RED, HP, speed);
		this.setTarget(t);
	}

	
	public void move(){
		if(getTarget()!=null)
		{
			if(target.distanceTo(getCenter())<this.getAbsVel())
				this.setPosition(target);
			else
			{
				try {
					this.getAngle().setSlope(getCenter(), target);
				} catch (InvalidAngleException e) {}
				
				super.move();
			}
		}
		else
		{
			super.move();
		}
	}
	@Override
	public Enemy makeCopy(int x, int y) {
		return new TargetEnemy(x,y,getTarget(),this.getColor(),this.getHP(),this.getAbsVel());
	}
	public Position getTarget() {
		return target;
	}
	public void setTarget(Position target) {
		this.target = target;
	}
}
