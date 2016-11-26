package objects;

import java.awt.Color;
import java.util.ArrayList;

import cannonTypes.BulletShooter;
import cannonTypes.Cannon;

import engine.DrawingPanel;

public abstract class Shooter extends HitBox {

	protected boolean isShooting = true;
	//private int counter;
	private int HP;
	private ArrayList<BulletShooter> cannons;
	
	public Shooter(int x, int y, int HP) {
		super(x, y, Color.RED);
		this.HP = HP;
		cannons = new ArrayList<BulletShooter>();
	}
	public Shooter(int x, int y, Color c, int HP) {
		super(x, y, c);
		this.HP = HP;
		cannons = new ArrayList<BulletShooter>();
	}
	public Shooter(int x, int y, Color c, int HP, int radius) {
		super(x, y, radius, c);
		this.HP = HP;
		cannons = new ArrayList<BulletShooter>();
	}
	public void tick(DrawingPanel drawingPanel)
	{
		//super.tick(drawingPanel);
		for(BulletShooter c: cannons)
			c.tick(drawingPanel);
	}
	public void setShooting() 
	{
		for(BulletShooter c : cannons)
			c.startShooting();
	}
	public void setNotShooting()
	{
		for(BulletShooter c : cannons)
			c.stopShooting();
	}
	public void isHit(int damage)
	{
		HP-=damage;
	}
	public int getHP()
	{
		return HP;
	}
	public void setHP(int newHP)
	{
		HP = newHP;
	}
	public boolean dead()
	{
		return HP<=0;
	}
	public void addCannon(BulletShooter bulletShooter)
	{
		cannons.add(bulletShooter);
		bulletShooter.setOwner(this);
		if(!isShooting)
			bulletShooter.stopShooting();
	}
	public ArrayList<BulletShooter> getCannon()
	{
		return cannons;
	}
	public void setCannons(ArrayList<BulletShooter> newCannons)
	{
		cannons = newCannons;
	}
	public void setCannonsCopy(ArrayList<BulletShooter> newCannons)
	{
		for(BulletShooter c:newCannons)
		{
			addCannon(c.copy());
		}
	}
}
