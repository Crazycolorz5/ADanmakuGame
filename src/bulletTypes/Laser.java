package bulletTypes;

import java.awt.Color;
import java.awt.Graphics;

import objects.OnSurface;
import objects.Position;
import objects.Surface;
import objects.Unit;
import cannonTypes.BulletShooter;
import engine.DrawingPanel;

public class Laser extends Bullet implements OnSurface {

	boolean firing = false;
	int thickness;
	
	public Laser(BulletShooter spawner, int thickness, boolean up, Color c)
	{
		super(spawner,c);
		this.thickness = thickness;
		if(up)
			this.setAngle(Math.PI/2);
		else
			this.setAngle(Math.PI*3/2);
	}
	public Laser(BulletShooter spawner, int thickness, int power, boolean up, Color c)
	{
		super(spawner,c, power);
		this.thickness = thickness;
		if(up)
			this.setAngle(Math.PI/2);
		else
			this.setAngle(Math.PI*3/2);
	}
	
	@Override
	public boolean isOnSurface(Surface s) {
		if(firing)
		{
			Position p = s.getSurfaceCenter();
			if(this.getAngle().getMeasure()==Math.PI/2)
			{
				//upward facing laser
				return p.getY()<this.getY()&&Math.abs(p.getX()-this.getX())<s.surfaceSize()+this.thickness/2;
			}
			else
			{
				//downward facing laser
				return p.getY()>this.getY()&&Math.abs(p.getX()-this.getX())<s.surfaceSize()+this.thickness/2;
			}
		}
		else
		{
			return false;
		}
	}
	public void draw(DrawingPanel dp)
	{
		Graphics g = dp.getDBG();
		g.setColor(this.getColor());
		if(firing)
		{
			/*drawSlantedRect(dp, (int)Math.round(this.getX()-thickness/2*getAngle().getCosAngle()), (int)Math.round(this.getY()-thickness/2*getAngle().getSinAngle()),
				(int)Math.round(this.getX()+thickness/2*getAngle().getSinAngle()), this.getAngle().getMeasure());*/
			if(this.getAngle().getMeasure()==Math.PI/2)
				g.fillRect((int)Math.round(this.getX()-thickness/2), 0, thickness, this.getY());
			else
				g.fillRect((int)Math.round(this.getX()-thickness/2), getY(), thickness, dp.getHeight());
			
		}
		else
		{
			if(this.getAngle().getMeasure()==Math.PI/2)
				g.drawLine(getX(), 0, getX(), getY());
			else
				g.drawLine(getX(), getY(), getX(), dp.getHeight());
		}
	}
	
	/*public void drawSlantedRect(DrawingPanel dp, int x1, int y1, int x2, double angle)
	{
		for(int c=0; c<=x2-x1; c++)
		{
			dp.getDBG().drawLine(x1+c, y1-(int)(c*1/Math.tan(this.getAngle().getMeasure())), (int)Math.round(1/Math.tan(getHeight(dp)))+x2, (int)getHeight(dp) - y1);
			
		}
	}*/
	public int getHeight(DrawingPanel dp)
	{
		if(this.getAngle().getMeasure()>Math.PI)
			return ((int)dp.getHeight() - this.getY());
		else
			return this.getY();
	}
	public void tick(DrawingPanel dp)
	{
		//do nothing;LaserBulletShooter handles this stuff.
	}
	@Override
	public Bullet copy(BulletShooter c) {
		return new Laser(c, thickness, this.getPower(), this.getAngle().getMeasure()==Math.PI/2, this.getColor());
	}
	public boolean isFiring()
	{
		return firing;
	}
	public void stopFiring()
	{
		firing = false;
	}
	public void startFiring()
	{
		firing = true;
	}
	public boolean hasCollided(Unit collider)
	{
		collider.isHit(this.getPower());
		//System.out.println("Laser.hasCollided called");
		return false;
	}
}
