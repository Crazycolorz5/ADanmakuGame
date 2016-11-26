package cannonTypes;

import objects.Shooter;
import engine.DrawingPanel;

public interface BulletShooter {
	public abstract void tick(DrawingPanel game);
	//public abstract void shootTick(DrawingPanel game);
	public abstract BulletShooter copy();
	public abstract void setOwner(Shooter shooter);
	public abstract void stopShooting();
	public abstract void startShooting();
	public abstract boolean isShooting();
	public abstract int getX();
	public abstract int getY();
	public abstract int getRelX();
	public abstract int getRelY();
	public abstract boolean doIShoot();
}
