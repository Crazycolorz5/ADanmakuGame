package bulletModifiers;

import bulletTypes.Bullet;
import objects.Angle;

public class RotaryModifier implements BulletModifier {

	Angle currentModification;
	Angle changeInAnglePerShot;
	
	public RotaryModifier(Angle changePerShot) {
		currentModification = new Angle(0);
		changeInAnglePerShot = changePerShot;
	}
	
	public RotaryModifier(Angle initialModification, Angle changePerShot) {
		currentModification = initialModification;
		changeInAnglePerShot = changePerShot;
	}

	@Override
	public void modifyBullet(Bullet b) {
		b.modifyAngle(currentModification.getMeasure());
		currentModification.change(currentModification.getMeasure()+changeInAnglePerShot.getMeasure());
	}

	@Override
	public void modifyBulletWithoutPersonalChange(Bullet b) {
		b.modifyAngle(currentModification.getMeasure());		
	}

	@Override
	public void advanceModifier() {
		currentModification.change(currentModification.getMeasure()+changeInAnglePerShot.getMeasure());
	}

}
