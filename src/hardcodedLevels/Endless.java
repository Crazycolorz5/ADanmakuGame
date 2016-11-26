package hardcodedLevels;

import java.awt.Color;

import objects.Angle;
import objects.Player;
import bulletModifiers.RandomModifier;
import bulletModifiers.RotaryModifier;
import bulletTypes.AcceleratingBullet;
import bulletTypes.Bullet;
import bulletTypes.HomingBullet;
import bulletTypes.Laser;
import bulletTypes.StraightBullet;
import bulletTypes.TargetBullet;
import cannonTypes.LaserCannon;
import cannonTypes.NormalCannon;
import cannonTypes.RadialCannon;
import enemyTypes.ChaseEnemy;
import enemyTypes.Enemy;
import enemyTypes.LineEnemy;
import engine.DrawingPanel;
//import engine.Engine;

public class Endless extends Level {

	public Endless(DrawingPanel dp)
	{
		super(dp);
	}

	public Endless(DrawingPanel p, int shot) {
		super(p, shot);
	}

	@Override
	public void loadEnemies() {
		Enemy e;
		NormalCannon c;
		
		//To Implement: Difficulties.
		//This is lunatic :P
		//Normal is commented out below.
		//Enemy 0
		e = new ChaseEnemy(0,0,getDisplay().getPlayer().getCenter()/*,Color.GREEN*/,65);
		c = new RadialCannon(4, 3.6, 10);
		c.addBullet(new TargetBullet(c, getDisplay().getPlayer().getCenter()));
		c.setModifier(new RotaryModifier(new Angle(Math.PI/32)));
		e.addCannon(c);
		e.setSpeed(1.5+Math.random()/2);
		addEnemy(e);
		
		//Enemy 1
		e = new ChaseEnemy(0,0, getDisplay().getPlayer().getCenter(),82,1.8);
		c = new RadialCannon(5, 2.65, 14);
		c.addBullet(new TargetBullet(c, getDisplay().getPlayer().getCenter()));
		c.setModifier(new RotaryModifier(new Angle(Math.PI/32)));
		e.addCannon(c);
		e.setSpeed(1.5+Math.random()/2);
		addEnemy(e);
		
		//Enemy 2
		e = new LineEnemy(0,0,40,3.2,Math.PI*7/6);
		c = new NormalCannon(12, 3.8);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*7/4)));//21/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*11/6)));//22/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*5/3)));//20/12
		e.addCannon(c);
		addEnemy(e);
		
		//Enemy 3		
		e = new LineEnemy(0,0,40,3.2,Math.PI*11/6);
		c = new NormalCannon(9, 3.0);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*5/4)));//15/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*7/6)));//14/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*4/3)));//16/12
		e.addCannon(c);
		addEnemy(e);
		
		
		//Enemy 4
		e = new LineEnemy(0,0,40,1.3,Math.PI*4/3);
		c = new RadialCannon(30, 2.3, 4);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*21/16)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*5/4)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*19/16)));
		e.addCannon(c);
		addEnemy(e);
		
		//Enemy 5		
		e = new LineEnemy(0,0,40,1.3,Math.PI*5/3);
		c = new RadialCannon(30, 2.3, 4);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*27/16)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*7/4)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*29/16)));
		e.addCannon(c);
		addEnemy(e);	
		
		
		
		//Normal
		/*
		//Enemy 0
		e = new ChaseEnemy(0,0,getDisplay().getPlayer().getCenter(),65);
		c = new RadialCannon(7, 4, 3);
		c.addBullet(new TargetBullet(c, getDisplay().getPlayer().getCenter()));
		c.setModifier(new RotaryModifier(new Angle(Math.PI/16)));
		e.addCannon(c);
		e.setSpeed(1.5+Math.random()/2);
		addEnemy(e);
		
		//Enemy 1
		e = new ChaseEnemy(0,0, getDisplay().getPlayer().getCenter(),82,1.8);
		c = new RadialCannon(9, 3.45, 3);
		c.addBullet(new TargetBullet(c, getDisplay().getPlayer().getCenter()));
		c.setModifier(new RotaryModifier(new Angle(Math.PI/16)));
		e.addCannon(c);
		e.setSpeed(1.5+Math.random()/2);
		addEnemy(e);
		
		//Enemy 2
		e = new LineEnemy(0,0,40,3.2,Math.PI*7/6);
		c = new NormalCannon(12, 3);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*7/4)));//21/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*11/6)));//22/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*5/3)));//20/12
		e.addCannon(c);
		addEnemy(e);
		
		//Enemy 3		
		e = new LineEnemy(0,0,40,3.2,Math.PI*11/6);
		c = new NormalCannon(9, 2.2);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*5/4)));//15/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*7/6)));//14/12
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*4/3)));//16/12
		e.addCannon(c);
		addEnemy(e);
		
		
		//Enemy 4
		e = new LineEnemy(0,0,40,1.3,Math.PI*4/3);
		c = new NormalCannon(30, 2.3);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*21/16)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*5/4)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*19/16)));
		e.addCannon(c);
		addEnemy(e);
		
		//Enemy 5		
		e = new LineEnemy(0,0,40,1.3,Math.PI*5/3);
		c = new NormalCannon(30, 2.3);
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*27/16)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*7/4)));
		c.addBullet(new StraightBullet(c, new Angle(Math.PI*29/16)));
		e.addCannon(c);
		addEnemy(e);
		*/
	}

	@Override
	public void loadPlayerCannons() {
		//Player Cannons
		NormalCannon p;
		Bullet b;
		
		if(playerType==1)
		{
			//"Speed"
			//Add 4 straightforward NormalCannons
			p = new NormalCannon(-6, 3, 3, 3.8, true);
			b = new AcceleratingBullet(p, new Angle(Math.PI/2), Color.CYAN, 4, .5);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.05236));
			addPlayerCannon(p);
			
			p = new NormalCannon(6, 3, 3, 3.8, true);
			b = new AcceleratingBullet(p, new Angle(Math.PI/2), Color.CYAN, 4, .5);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.05236));
			addPlayerCannon(p);			
			
			p = new NormalCannon(-14, 3, 4, 3, true);
			b = new AcceleratingBullet(p, new Angle(Math.PI/2), Color.CYAN, 3, .4);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.05236));
			addPlayerCannon(p);
			
			p = new NormalCannon(14, 3, 4, 3, true);
			b = new AcceleratingBullet(p, new Angle(Math.PI/2), Color.CYAN, 3, .4);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.05236));
			addPlayerCannon(p);
			
			//Add 2 slanted NormalCannons - Slant at 5 degrees
			p = new NormalCannon(9, 11, 6, 11, true);
			b = new StraightBullet(p, new Angle(17*Math.PI/36), Color.CYAN,5,9);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));//5 degrees on each side/10degrees
			addPlayerCannon(p);
			
			p = new NormalCannon(-9, 11, 6, 11, true);
			b = new StraightBullet(p, new Angle(19*Math.PI/36), Color.CYAN,5,9);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));
			addPlayerCannon(p);
			
			//Homing bullets, for utility.
			p = new NormalCannon(0,-4, 14, 5.0, true);
			b = new HomingBullet(p, new Color(0,102,255), 9, 3, getDisplay().getEnemies(), Math.PI/2, Math.PI/36);
			p.addBullet(b);
			addPlayerCannon(p);
		}
		else if(playerType==2)
		{
			//"Penetrate"
			//1 powerful center Laser
			LaserCannon l;
			l = new LaserCannon(0, 10, true, 200, 40, .3);
			b = new Laser(l, 8, 3, true, Color.CYAN);
			l.setLaserCopy((Laser)b);
			addPlayerCannon(l);
			//2 less powerful, but constant, side lasers
			l = new LaserCannon(-24, 6, true, 0, 0, 0);
			b = new Laser(l, 4, 1, true, Color.CYAN);
			l.setLaserCopy((Laser)b);
			addPlayerCannon(l);

			l = new LaserCannon(24, 6, true, 0, 0, 0);
			b = new Laser(l, 4, 1, true, Color.CYAN);
			l.setLaserCopy((Laser)b);
			addPlayerCannon(l);
			
			l = new LaserCannon(-42, 6, true, 0, 0, 0);
			b = new Laser(l, 4, 1, true, Color.CYAN);
			l.setLaserCopy((Laser)b);
			addPlayerCannon(l);

			l = new LaserCannon(42, 6, true, 0, 0, 0);
			b = new Laser(l, 4, 1, true, Color.CYAN);
			l.setLaserCopy((Laser)b);
			addPlayerCannon(l);
			
			//1 NormalCannon
			p = new NormalCannon(-6, 3, 6, 9, true);
			b = new StraightBullet(p, new Angle(Math.PI/2), Color.CYAN, 6);
			p.addBullet(b);
			addPlayerCannon(p);
			
			//10-degree slanted cannons
			p = new NormalCannon(9, 11, 6, 9, true);
			b = new StraightBullet(p, new Angle(8*Math.PI/18), Color.CYAN,5);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));
			addPlayerCannon(p);
			
			p = new NormalCannon(-9, 11, 6, 9, true);
			b = new StraightBullet(p, new Angle(10*Math.PI/18), Color.CYAN,5);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));
			addPlayerCannon(p);
			
			//Homing bullets, for utility.
			p = new NormalCannon(0,-4, 16, 5.0, true);
			b = new HomingBullet(p, new Color(0,102,255), 8, 3, getDisplay().getEnemies(), Math.PI/2, Math.PI/36);
			p.addBullet(b);
			addPlayerCannon(p);
		}
		else if(playerType==3)
		{
			//"Spread"
			//Add 2 straightforward NormalCannons
			p = new NormalCannon(-10, 3, 6, 3.5, true);
			b = new AcceleratingBullet(p, new Angle(Math.PI/2), Color.CYAN, 5, .5);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.05236));
			addPlayerCannon(p);
			
			p = new NormalCannon(10, 3, 6, 3.5, true);
			b = new AcceleratingBullet(p, new Angle(Math.PI/2), Color.CYAN, 5, .5);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.05236));
			addPlayerCannon(p);
			
			//Add 2 slanted NormalCannons - Slant at 15 degrees
			p = new NormalCannon(9, 11, 8, 6, true);
			b = new StraightBullet(p, new Angle(5*Math.PI/12), Color.CYAN,5,7);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));//5 degrees on each side/10degrees
			addPlayerCannon(p);
			
			p = new NormalCannon(-9, 11, 8, 6, true);
			b = new StraightBullet(p, new Angle(7*Math.PI/12), Color.CYAN,5,7);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));
			addPlayerCannon(p);
			
			//2 slanted at 30...
			p = new NormalCannon(9, 12, 9, 6, true);
			b = new StraightBullet(p, new Angle(Math.PI/3), Color.CYAN,6,7);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));//5 degrees on each side/10degrees
			addPlayerCannon(p);
			
			p = new NormalCannon(-9, 12, 9, 6, true);
			b = new StraightBullet(p, new Angle(2*Math.PI/3), Color.CYAN,6,7);
			p.addBullet(b);
			p.setModifier(new RandomModifier(.1745));
			addPlayerCannon(p);
			
			//And 2 on the sides that home in
			p = new NormalCannon(-10,-4, 7, 8.6, true);
			b = new HomingBullet(p, new Color(0,102,255), 3, 3, getDisplay().getEnemies(), Math.PI/2, Math.PI/36);
			p.addBullet(b);
			addPlayerCannon(p);
			
			p = new NormalCannon(10,-4, 7, 8.6, true);
			b = new HomingBullet(p, new Color(0,102,255), 3, 3, getDisplay().getEnemies(), Math.PI/2, Math.PI/36);
			p.addBullet(b);	
			addPlayerCannon(p);	
		}		
	}

	@Override
	public void loadBonusCannons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadEvents() {
		events = new EndlessEventHandler();		
	}

	@Override
	public void loadPlayer() {
		p = new Player(300, 600);
		if(playerType==1)
		{
			//"Speed"
			p.setSpeeds(2.8, 6.2, 6.2);
		}
		else if(playerType==2)
		{
			//"Penetrating"
			p.setSpeeds(1.7, 4.2, 4.4);
		}
		else
		{
			//"Spread"
			p.setSpeeds(2.0, 3.6, 5.0);
		}
	}
	
	
}
