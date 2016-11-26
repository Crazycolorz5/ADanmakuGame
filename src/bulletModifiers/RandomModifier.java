package bulletModifiers;

import bulletTypes.Bullet;

public class RandomModifier implements BulletModifier {
	
	private double randomAmount;
	
	public RandomModifier()
	{
		//defaults to changing direction up to 1.5 degrees on each side
		randomAmount = .0261799;
	}
	public RandomModifier(double amount)
	{
		randomAmount = amount;
	}
	
	@Override
	public void modifyBullet(Bullet b) {
		b.modifyAngle((Math.random()-.5)*randomAmount);
	}
	@Override
	public void modifyBulletWithoutPersonalChange(Bullet b) {
		modifyBullet(b);
		
	}
	@Override
	public void advanceModifier() {
	}
	
}
