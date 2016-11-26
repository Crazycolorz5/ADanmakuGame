package bulletModifiers;

import bulletTypes.Bullet;

public interface BulletModifier {
	public void modifyBullet(Bullet b);
	public void modifyBulletWithoutPersonalChange(Bullet b);
	public void advanceModifier();
}
