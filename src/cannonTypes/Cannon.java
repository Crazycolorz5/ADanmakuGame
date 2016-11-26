package cannonTypes;

import objects.Shooter;

import engine.DrawingPanel;

public abstract class Cannon implements BulletShooter {
	protected boolean isShooting;
	private Shooter owner;
	protected int relativeX;
	protected int relativeY;
	protected int counter;
	private boolean isPlayerCannon = false;
	
	public Cannon()
	{
		relativeX = 0;
		relativeY = 0;
		counter = 0;
		isShooting = true;
	}
	public Cannon(int x, int y)
	{
		relativeX = x;
		relativeY = y;
		counter = 0;
		isShooting = true;
	}
	public Cannon(boolean playerCannon)
	{
		relativeX = 0;
		relativeY = 0;
		counter = 0;
		isShooting = true;
		isPlayerCannon = playerCannon;
	}
	public Cannon(int x, int y, boolean playerCannon)
	{
		relativeX = x;
		relativeY = y;
		counter = 0;
		isShooting = true;
		isPlayerCannon = playerCannon;
	}
	public Cannon(int shootP, double shootS)
	{
		relativeX = 0;
		relativeY = 0;
		counter = 0;
		isShooting = true;
	}
	public Cannon(int x, int y, int shootP, double shootS)
	{
		relativeX = x;
		relativeY = y;
		counter = 0;
		isShooting = true;
	}
	
	public Cannon(int x, int y, int shootP, double shootS, boolean playerCannon)
	{
		relativeX = x;
		relativeY = y;
		counter = 0;
		isShooting = true;
		isPlayerCannon = playerCannon;
	}
	
	public abstract void tick(DrawingPanel game);
	//public abstract void tickButDontShoot();
	//public abstract void shootTick(DrawingPanel game);
	
	public Shooter getOwner() {
		return owner;
	}
	public void setOwner(Shooter s) {
		owner = s;
	}
	public boolean hasOwner()
	{
		return owner!=null;
	}
	public int getRelX() {
		return relativeX;
	}
	public int getRelY() {
		return relativeY;
	}
	public int getX(){
		if(owner!=null)
			return this.getOwner().getX()+relativeX;
		else
			return 0;
	}
	public int getY(){
		if(owner!=null)
			return this.getOwner().getY()-relativeY;
		else
			return 0;
	}
	
	@Override
	public boolean isShooting()
	{
		return isShooting;
	}

	public void stopShooting()
	{
		isShooting = false;
	}
	public void startShooting()
	{
		isShooting = true;
	}
	public abstract BulletShooter copy();
	public boolean isPlayerCannon()
	{
		return isPlayerCannon;
	}
}
