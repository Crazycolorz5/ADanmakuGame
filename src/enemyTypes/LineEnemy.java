package enemyTypes;

import java.awt.Color;

import objects.Angle;

public class LineEnemy extends Enemy {
	
	public LineEnemy(int x, int y, int HP, Angle direction) {
		super(x, y, Color.RED,HP);
		this.setAngle(direction);
	}
	public LineEnemy(int x, int y, Color c, int HP, Angle direction) {
		super(x, y, c, HP);
		this.setAngle(direction);
	}
	public LineEnemy(int x, int y, int HP, double speed, Angle direction) {
		super(x, y, Color.RED, HP, speed);
		this.setAngle(direction);
	}
	public LineEnemy(int x, int y, Color c, int HP, double speed, Angle direction) {
		super(x, y, c,HP,speed);
		this.setAngle(direction);
	}
	public LineEnemy(int HP, double speed, double direction) {
		super(0, 0, Color.RED, HP, speed);
		this.setAngle(direction);
	}
	public LineEnemy(int x, int y, int HP, double speed, double direction) {
		super(x, y, Color.RED, HP, speed);
		this.setAngle(direction);
	}
	public LineEnemy(int x, int y, Color c, int HP, double speed, double direction) {
		super(x, y, c,HP,speed);
		this.setAngle(direction);
	}
	@Override
	public Enemy makeCopy(int x, int y) {
		return new LineEnemy(x,y,this.getColor(),this.getHP(),this.getAbsVel(),this.getAngle());
	}
}
