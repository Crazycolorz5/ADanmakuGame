package enemyTypes;

import java.awt.Color;

import objects.Angle;

public class MoveThenStopEnemy extends LineEnemy {

	private int timeLeft;
	
	public MoveThenStopEnemy(int HP, double speed, Angle direction, int time) {
		super(0,0,HP,speed,direction);
		timeLeft = time;
	}
	public MoveThenStopEnemy(Color c, int HP, double speed, Angle direction, int time) {
		super(0,0,c,HP,speed,direction);
		timeLeft = time;
	}
	public MoveThenStopEnemy(int x, int y, int HP, double speed, Angle direction, int time) {
		super(x, y, Color.RED,HP,speed,direction);
		timeLeft = time;
	}
	public MoveThenStopEnemy(int x, int y, Color c, int HP, double speed, Angle direction, int time) {
		super(x, y, c, HP,speed,direction);
		timeLeft = time;
	}
	
	@Override
	public void move()
	{
		if(timeLeft>0)
		{
			timeLeft--;
			super.move();
		}
	}
	
	@Override
	public Enemy makeCopy(int x, int y)
	{
		return new MoveThenStopEnemy(x,y,this.getColor(),this.getHP(),this.getAbsVel(),this.getAngle(),timeLeft);
	}

}
