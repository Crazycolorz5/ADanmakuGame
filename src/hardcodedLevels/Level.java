package hardcodedLevels;

import java.util.ArrayList;

import objects.Player;

import cannonTypes.BulletShooter;
import cannonTypes.Cannon;
import enemyTypes.Enemy;
import engine.DrawingPanel;
import engine.EventHandler;


public abstract class Level {
	//Each level must:
	/*
	 * 1) define player cannons(play movement is fixed)
	 * 	1a) Bonus Power Ups
	 * 2) define enemies
	 * 	2a) enemy cannons
	 * 	2b) bullet patterns
	 * 3) Events, ie When does an enemy spawn
	 * 
	 * 4(opt) ) special cannons or enemies, ie bosses
	 * 
	 * 
	 */
	private DrawingPanel display;
	private ArrayList<BulletShooter> pcans;
	private ArrayList<BulletShooter> bcans;
	private ArrayList<Enemy> enemies;
	protected EventHandler events;
	
	protected int playerType;
	protected Player p;
	
	public Level(DrawingPanel dp)
	{
		enemies = new ArrayList<Enemy>();
		pcans = new ArrayList<BulletShooter>();
		bcans = new ArrayList<BulletShooter>();
		display = dp;
		events = new EventHandler();
		playerType = 1;
		
		loadPlayer();
		display.loadPlayer(p);
		
		loadEnemies();
		loadPlayerCannons();
		loadBonusCannons();
		loadEvents();
		
	}
	public Level(DrawingPanel dp, int shotType)
	{
		enemies = new ArrayList<Enemy>();
		pcans = new ArrayList<BulletShooter>();
		bcans = new ArrayList<BulletShooter>();
		display = dp;
		events = new EventHandler();
		playerType = shotType;
		
		loadPlayer();
		display.loadPlayer(p);
		
		loadEnemies();
		loadPlayerCannons();
		loadBonusCannons();
		loadEvents();
	}
	
	public void addEnemy(Enemy e)
	{
		enemies.add(e);
	}
	public void addPlayerCannon(Cannon p)
	{
		pcans.add(p);
	}
	public void addBonusCannon(Cannon p)
	{
		bcans.add(p);
	}
	public ArrayList<BulletShooter> getPlayerCannons()
	{
		return pcans;
	}
	public BulletShooter getBonusCannon(int index)
	{
		return bcans.get(index);
	}
	public Enemy getEnemyCopy(int index)
	{
		return enemies.get(index).copy(0,0);
	}
	public DrawingPanel getDisplay()
	{
		return display;
	}
	public EventHandler getEvents()
	{
		return events;
	}

	public abstract void loadEnemies();
	public abstract void loadPlayer();
	public abstract void loadPlayerCannons();
	public abstract void loadBonusCannons();
	public abstract void loadEvents();
}
