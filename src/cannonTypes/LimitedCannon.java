package cannonTypes;

import java.util.ArrayList;

import objects.Shooter;
import engine.DrawingPanel;
import bulletModifiers.BulletModifier;
import bulletTypes.Bullet;

public class LimitedCannon implements StandardCannon {

	private StandardCannon myCannon;
	private int shotsLeft;
	
	public LimitedCannon(int maxShots, StandardCannon cannon) {
		myCannon = cannon;
		shotsLeft = maxShots;
	}
	
	public LimitedCannon(int shootP, double shootS, int maxShots) {
		myCannon = new NormalCannon(shootP, shootS);
		shotsLeft = maxShots;
	}
	
	/*public void shootTick(DrawingPanel game)
	{
		if(shotsLeft>0)
		{
			shotsLeft--;
			myCannon.shootMyBullets(game);
		}
	}*/
	@Override
	public void tick(DrawingPanel game) {
		if(shotsLeft>0)
		{
			myCannon.advanceCounter();
			if(myCannon.doIShoot())
			{
				shotsLeft--;
				shootMyBullets(game);
				myCannon.resetCounter();
			}
		}
	}
	/*@Override
	public void tickButDontShoot() {
		myCannon.tickButDontShoot();
	}*/
	@Override
	public BulletShooter copy() {
		return new LimitedCannon(shotsLeft, (StandardCannon) myCannon.copy());
	}
	@Override
	public void setOwner(Shooter shooter) {
		myCannon.setOwner(shooter);
	}
	@Override
	public void stopShooting() {
		myCannon.stopShooting();
	}
	@Override
	public void startShooting() {
		myCannon.startShooting();
	}
	@Override
	public boolean isShooting() {
		return myCannon.isShooting();
	}

	@Override
	public int getX() {
		return myCannon.getX();
	}

	@Override
	public int getY() {
		return myCannon.getY();
	}

	@Override
	public int getRelX() {
		return myCannon.getRelX();
	}

	@Override
	public int getRelY() {
		return myCannon.getRelY();
	}

	@Override
	public void addBullet(Bullet b) {
		myCannon.addBullet(b);
	}

	@Override
	public boolean hasShots() {
		return myCannon.hasShots();
	}

	@Override
	public void setData(ArrayList<Bullet> data) {
		myCannon.setData(data);		
	}

	@Override
	public void shootMyBullets(DrawingPanel game) {
		myCannon.shootMyBullets(game);
	}

	@Override
	public void advanceCounter() {
		myCannon.advanceCounter();
	}

	@Override
	public void resetCounter() {
		myCannon.resetCounter();
	}

	@Override
	public boolean doIShoot() {
		return myCannon.doIShoot();
	}

	@Override
	public void setModifier(BulletModifier bm) {
		myCannon.setModifier(bm);
	}
}
