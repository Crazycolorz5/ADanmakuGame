package bulletModifiers;

import bulletTypes.Bullet;

public class NullModifier implements BulletModifier{
	
	public NullModifier(){}
	
	public void modifyBullet(Bullet b)
	{
	}

	@Override
	public void modifyBulletWithoutPersonalChange(Bullet b) {
	}

	@Override
	public void advanceModifier() {
	}
}
