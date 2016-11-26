package bulletTypes;

public class BulletTemplate {
	private int type;
	private double angle;
	private int radius;
	
	public BulletTemplate(int type, double angle, int radius)
	{
		this.type = type;
		this.angle = angle;
		this.radius = radius;
	}
	public BulletTemplate(int type, int radius)
	{
		this.type = type;
		this.radius = radius;
	}
	public int getType() {
		return type;
	}
	public double getAngle() {
		return angle;
	}
	public int getRadius() {
		return radius;
	}
}
