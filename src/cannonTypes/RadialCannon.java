package cannonTypes;

import objects.Angle;
import engine.DrawingPanel;
import bulletModifiers.BulletModifier;
import bulletTypes.Bullet;

public class RadialCannon extends NormalCannon {

	private int bulletsPerShot;
	private Angle angleBetweenBullets;
	private Angle offset = new Angle(0);
	
	public RadialCannon(int shootP, double shootS) {
		super(shootP, shootS);
		angleBetweenBullets = new Angle(Math.PI/3); //Default to 6 bullets
		bulletsPerShot = 6;
	}
	public RadialCannon(int shootP, double shootS, int bulletsPerShot) {
		//Allows for not-full-radial shots.
		super(shootP, shootS);
		angleBetweenBullets = new Angle(Math.PI*2/bulletsPerShot);
		this.bulletsPerShot = bulletsPerShot;
	}
	public RadialCannon(int shootP, double shootS, int bulletsPerShot, Angle angleBetweenShots) {
		//Allows for not-full-radial shots.
		super(shootP, shootS);
		angleBetweenBullets = angleBetweenShots;
		this.bulletsPerShot = bulletsPerShot;
	}
	public RadialCannon(int shootP, double shootS, int bulletsPerShot, Angle angleBetweenShots, Angle offset) {
		//Allows for not-full-radial shots.
		super(shootP, shootS);
		this.offset = offset;
		angleBetweenBullets = angleBetweenShots;
		this.bulletsPerShot = bulletsPerShot;
	}
	public RadialCannon(int x, int y, int shootP, double shootS) {
		super(x, y, shootP, shootS);
		angleBetweenBullets = new Angle(Math.PI/3); //Default to 6 bullets
		bulletsPerShot = 6;
	}

	public RadialCannon(int x, int y, int shootP, double shootS,
			boolean playerCannon) {
		super(x, y, shootP, shootS, playerCannon);
		angleBetweenBullets = new Angle(Math.PI/3); //Default to 6 bullets
		bulletsPerShot = 6;
	}

	public RadialCannon(int x, int y, int shootP, double shootS,
			boolean playerCannon, BulletModifier mod) {
		super(x, y, shootP, shootS, playerCannon, mod);
		angleBetweenBullets = new Angle(Math.PI/3); //Default to 6 bullets
		bulletsPerShot = 6;
	}

	public RadialCannon(int shootP, double shootS, BulletModifier mod) {
		super(shootP, shootS, mod);
		angleBetweenBullets = new Angle(Math.PI/3); //Default to 6 bullets
		bulletsPerShot = 6;
	}

	public RadialCannon(int x, int y, int shootP, double shootS,
			BulletModifier mod) {
		super(x, y, shootP, shootS, mod);
		angleBetweenBullets = new Angle(Math.PI/3); //Default to 6 bullets
		bulletsPerShot = 6;
	}
	
	//bulletsPerShot MUST be >1
	public RadialCannon(int x, int y, int shootP, double shootS,
			boolean playerCannon, int bulletsPerShot) {
		super(x, y, shootP, shootS, playerCannon);
		angleBetweenBullets = new Angle(Math.PI*2/bulletsPerShot);
		this.bulletsPerShot = bulletsPerShot;
	}

	public RadialCannon(int x, int y, int shootP, double shootS,
			boolean playerCannon, BulletModifier mod, int bulletsPerShot) {
		super(x, y, shootP, shootS, playerCannon, mod);
		angleBetweenBullets = new Angle(Math.PI*2/bulletsPerShot);
		this.bulletsPerShot = bulletsPerShot;
	}
	
	public RadialCannon(int x, int y, int shootP, double shootS,
			boolean playerCannon, int bulletsPerShot, Angle angleBetweenShots) {
		//Allows for not-full-radial shots.
		super(x, y, shootP, shootS, playerCannon);
		angleBetweenBullets = angleBetweenShots;
		this.bulletsPerShot = bulletsPerShot;
	}
	public RadialCannon(int x, int y, int shootP, double shootS,
			boolean playerCannon, BulletModifier mod, int bulletsPerShot, Angle angleBetweenShots, Angle offset) {
		//Allows for not-full-radial shots.
		super(x, y, shootP, shootS, playerCannon, mod);
		angleBetweenBullets = angleBetweenShots;
		this.bulletsPerShot = bulletsPerShot;
		this.offset = offset;
	}
	public RadialCannon(int shootP, double shootS, BulletModifier mod, int bulletsPerShot) {
		super(shootP, shootS, mod);
		angleBetweenBullets = new Angle(Math.PI*2/bulletsPerShot);
		this.bulletsPerShot = bulletsPerShot;
	}
	
	@Override
	public void shootMyBullets(DrawingPanel game)
	{
		for(Bullet b: shotData)
		{
			for(int i=0; i<bulletsPerShot; i++)
			{
				Bullet fired = b.copy(this);
				fired.setSpeed(shootSpeed);
				mod.modifyBulletWithoutPersonalChange(fired);
				fired.modifyAngle(i*angleBetweenBullets.getMeasure()+offset.getMeasure());
				if(isPlayerCannon())
					game.addPlayerBullet(fired);
				else
					game.addEnemyBullet(fired);
			}
			mod.advanceModifier();
		}
	}
	public BulletShooter copy()
	{
		RadialCannon copy = new RadialCannon(getRelX(), getRelY(), shootPeriod, shootSpeed, isPlayerCannon(), mod, bulletsPerShot, angleBetweenBullets, offset);
		for(Bullet b:shotData)
			copy.addBullet(b.copy(copy));
		return copy;
	}

}
