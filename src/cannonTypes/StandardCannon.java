package cannonTypes;

import java.util.ArrayList;

import bulletModifiers.BulletModifier;
import bulletTypes.Bullet;
import engine.DrawingPanel;

public interface StandardCannon extends BulletShooter {

	
	/*public StandardCannon() {
		shotData = new ArrayList<Bullet>();
	}*/

	/*public StandardCannon(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}*/

	/*public StandardCannon(boolean playerCannon) {
		super(playerCannon);
		// TODO Auto-generated constructor stub
	}

	public StandardCannon(int x, int y, boolean playerCannon) {
		super(x, y, playerCannon);
		// TODO Auto-generated constructor stub
	}

	public StandardCannon(int shootP, double shootS) {
		super(shootP, shootS);
		// TODO Auto-generated constructor stub
	}

	public StandardCannon(int x, int y, int shootP, double shootS) {
		super(x, y, shootP, shootS);
		// TODO Auto-generated constructor stub
	}

	public StandardCannon(int x, int y, int shootP, double shootS,
			boolean playerCannon) {
		super(x, y, shootP, shootS, playerCannon);
		// TODO Auto-generated constructor stub
	}*/
	
	public abstract void addBullet(Bullet b);
	public abstract boolean hasShots();
	public abstract void setData(ArrayList<Bullet> data);
	public abstract void shootMyBullets(DrawingPanel game);
	public abstract void advanceCounter();
	public abstract void resetCounter();
	public abstract void setModifier(BulletModifier bm);
}
