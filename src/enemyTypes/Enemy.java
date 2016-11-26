package enemyTypes;

import java.awt.Color;

import engine.DrawingPanel;

import objects.OnSurface;
import objects.Position;
import objects.Shooter;
import objects.Surface;

public abstract class Enemy extends Shooter implements OnSurface {
	int delay = 0;
	
	public Enemy(int x, int y, int HP) {
		super(x, y, Color.RED,HP);
	}
	public Enemy(int x, int y, Color c, int HP) {
		super(x, y, c, HP);
	}
	public Enemy(int x, int y, int HP, double speed) {
		super(x, y, Color.RED, HP);
		this.setSpeed(speed);
	}
	public Enemy(int x, int y, Color c, int HP, double speed) {
		super(x, y, c,HP);
		this.setSpeed(speed);
	}
	public boolean isOnSurface(Surface s)
	{
		Position point = s.getSurfaceCenter();
		return point.distanceTo(this.getCenter())<(this.surfaceSize()+s.surfaceSize());
	}
	public Enemy copy(int x, int y)
	{
		Enemy e = this.makeCopy(x,y);
		e.setCannonsCopy(getCannon());
		e.setDelay(delay);
		return e;
	}
	@Override
	public void tick(DrawingPanel p)
	{
		if(delay>0)
		{
			delay--;
		}
		else
			super.tick(p);
	}
	public void setDelay(int delay2) {
		delay = delay2;
	}
	public abstract Enemy makeCopy(int x, int y);
	
	/*@Override
	public boolean hasCollided(Unit collider) {
		collider.isHit(this.getHP());
		return true;
	}*/
	
}
