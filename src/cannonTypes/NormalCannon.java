package cannonTypes;

import java.util.ArrayList;

import engine.DrawingPanel;

import bulletModifiers.BulletModifier;
import bulletModifiers.NullModifier;
import bulletTypes.Bullet;

public class NormalCannon extends Cannon implements StandardCannon {

	protected int shootPeriod;
	protected double shootSpeed;
	protected BulletModifier mod;
	protected ArrayList<Bullet> shotData;
	
	public NormalCannon(int shootP, double shootS)
	{
		shootPeriod = shootP;
		shootSpeed = shootS;
		mod = new NullModifier();
		shotData = new ArrayList<Bullet>();
	}
	public NormalCannon(int x, int y, int shootP, double shootS)
	{
		super(x,y);
		shootPeriod = shootP;
		shootSpeed = shootS;
		mod = new NullModifier();
		shotData = new ArrayList<Bullet>();
	}
	public NormalCannon(int x, int y, int shootP, double shootS, boolean playerCannon)
	{
		super(x,y,playerCannon);
		shootPeriod = shootP;
		shootSpeed = shootS;
		mod = new NullModifier();
		shotData = new ArrayList<Bullet>();
	}
	public NormalCannon(int x, int y, int shootP, double shootS, boolean playerCannon, BulletModifier mod)
	{
		super(x,y,playerCannon);
		shootPeriod = shootP;
		shootSpeed = shootS;
		this.mod = mod;
		shotData = new ArrayList<Bullet>();
	}
	public NormalCannon(int shootP, double shootS, BulletModifier mod)
	{
		super(0,0);
		shootPeriod = shootP;
		shootSpeed = shootS;
		isShooting = true;
		this.mod = mod;
		shotData = new ArrayList<Bullet>();
	}
	public NormalCannon(int x, int y, int shootP, double shootS, BulletModifier mod)
	{
		super(x,y);
		shootPeriod = shootP;
		shootSpeed = shootS;
		isShooting = true;
		this.mod = mod;
	}

	@Override
	public void tick(DrawingPanel game)
	{
		advanceCounter();
		if(doIShoot())
		{
			shootMyBullets(game);
			resetCounter();
		}
	}
	public void advanceCounter()
	{
		counter++;
		if(counter>shootPeriod)
		{
			counter = shootPeriod;
		}
	}
	/*public void shootTick(DrawingPanel game)
	{
		counter++;
		if(counter>=shootPeriod)
		{
			if(hasShots())
			{
				shootMyBullets(game);
			}
			counter = 0;
		}
	}*/
	@Override
	public void shootMyBullets(DrawingPanel game)
	{
		for(Bullet b: shotData)
		{
			Bullet fired = b.copy(this);
			fired.setSpeed(shootSpeed);
			mod.modifyBullet(fired);
			if(isPlayerCannon())
				game.addPlayerBullet(fired);
			else
				game.addEnemyBullet(fired);
		}
	}
	public double getShootSpeed()
	{
		return shootSpeed;
	}
	public void setModifier(BulletModifier mod)
	{
		this.mod = mod;
	}
	public BulletShooter copy()
	{
		NormalCannon copy = new NormalCannon(getRelX(), getRelY(), shootPeriod, shootSpeed, isPlayerCannon(), mod);
		for(Bullet b:shotData)
			copy.addBullet(b.copy(copy));
		return copy;
	}
	public void addBullet(Bullet b)
	{
		shotData.add(b);
	}
	public void setData(ArrayList<Bullet> data)
	{
		shotData = data;
	}
	public boolean hasShots() {
		return shotData.size()>0;
	}
	@Override
	public boolean doIShoot() {
		return counter>=shootPeriod&&isShooting();
	}
	@Override
	public void resetCounter() {
		counter = 0;
	}
	
}
