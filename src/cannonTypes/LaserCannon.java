package cannonTypes;

import engine.DrawingPanel;
import bulletTypes.Laser;

public class LaserCannon extends Cannon {

	private Laser myLaser;
	private double charge = 0;
	private int maximumCharge;
	private int minimumCharge;
	private double chargeUsePerTick;
	private boolean laserInGame = false;
	
	public LaserCannon(int x, int y, 
			boolean playerCannon, int maximumCharge, double chargeUsePerTick) {
		super(x, y, playerCannon);
		this.maximumCharge = maximumCharge;
		this.chargeUsePerTick = chargeUsePerTick;
		this.minimumCharge = maximumCharge;
	}
	public LaserCannon(int x, int y, 
			boolean playerCannon, int maximumCharge, int minimumCharge, double chargeUsePerTick) {
		super(x, y, playerCannon);
		this.maximumCharge = maximumCharge;
		this.chargeUsePerTick = chargeUsePerTick;
		this.minimumCharge = minimumCharge;
		if(this.maximumCharge<this.minimumCharge&&this.maximumCharge!=0)
		{
			int temp = this.minimumCharge;
			this.minimumCharge = this.maximumCharge;
			this.maximumCharge = temp;
		}
	}
	public void shootTick(DrawingPanel game)
	{
		if(myLaser.isFiring())
		{
			charge-=chargeUsePerTick;
			if(charge<0)
			{
				myLaser.stopFiring();
			}
		}
		else
		{
			charge++;
			if(charge>minimumCharge)
			{
				myLaser.startFiring();
			}
		}
	}
	@Override
	public void tick(DrawingPanel game) {
		if(!laserInGame)
		{
			if(isPlayerCannon())
				game.addPlayerBullet(myLaser);
			else
				game.addEnemyBullet(myLaser);
			
			laserInGame = true;
		}
		myLaser.setX(this.getX());
		myLaser.setY(this.getY());
		if(isShooting)
			shootTick(game);
		else
			tickButDontShoot();	
	}
	public void setLaserCopy(Laser l)
	{
		myLaser = (Laser) l.copy(this);
	}
	public void tickButDontShoot() {
		if(myLaser.isFiring())
			myLaser.stopFiring();
		charge++;
		if(charge>maximumCharge&&maximumCharge>0)
			charge = maximumCharge;
	}
	@Override
	public BulletShooter copy() {
		LaserCannon l =new LaserCannon(getRelX(), getRelY(), isPlayerCannon(), maximumCharge, minimumCharge, chargeUsePerTick);
		l.setLaserCopy(myLaser);
		return l;
	}
	@Override
	public boolean doIShoot() {
		return !laserInGame;
	}
}
