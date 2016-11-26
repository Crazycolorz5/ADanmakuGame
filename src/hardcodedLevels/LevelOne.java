package hardcodedLevels;

import java.awt.Color;
import java.util.ArrayList;

import objects.Angle;
import objects.Player;
import objects.Position;

import bulletModifiers.RandomModifier;
import bulletModifiers.RotaryModifier;
//import bulletTypes.AcceleratingBullet;
import bulletTypes.Bullet;
import bulletTypes.HomingBullet;
import bulletTypes.Laser;
import bulletTypes.StraightBullet;
import bulletTypes.TargetBullet;
import cannonTypes.BulletShooter;
//import cannonTypes.Cannon;
import cannonTypes.LaserCannon;
import cannonTypes.LimitedCannon;
import cannonTypes.NormalCannon;
import cannonTypes.RadialCannon;
import cannonTypes.StandardCannon;
import enemyTypes.*;
import engine.DrawingPanel;
import engine.Event;

public class LevelOne extends Level{

	public LevelOne(DrawingPanel dp) {
		super(dp);
		
	}

	public LevelOne(DrawingPanel p, int shot) {
		super(p, shot);
	}

	@Override
	public void loadEnemies() {
		Enemy e;
		StandardCannon sc;
		//NormalCannon nc;
		Bullet b;
		//Define Enemy types:
		
		//Enemy 0
		e = new LineEnemy(10, 2.5, Math.PI*7/6);
		sc = new LimitedCannon(6, new RadialCannon(30, 3.6, 3, new Angle(Math.PI/18), new Angle(Math.PI/-18)));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter());
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
		
		//Enemy 1
		e = new LineEnemy(10, 2.5, Math.PI*11/6);
		sc = new LimitedCannon(6, new RadialCannon(30, 3.6, 3, new Angle(Math.PI/18), new Angle(Math.PI/-18)));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter());
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
		//Enemy 2
		e = new TargetEnemy(130, 6, new Position(350, 300));
		sc = new LimitedCannon(18, new RadialCannon(14, 3.3, 32));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter(), false);
		sc.addBullet(b);/*
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter(), new Angle(Math.PI/6), false);
		sc.addBullet(b);
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter(), new Angle(Math.PI/-6), false);
		sc.addBullet(b);*/
		e.addCannon(sc);
		this.addEnemy(e);
		
		//Enemy 3
		e = new LineEnemy(22, 3, Math.PI);
		sc = new RadialCannon(6, 4.2, 5, new Angle(Math.PI/12), new Angle(Math.PI/-6));
		sc.setModifier(new RotaryModifier(new Angle(Math.PI/6)));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter(), false);
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
		//Enemy 4
		e = new MoveThenStopEnemy(34, 4, new Angle(Math.PI*3/2), 30);
		e.setDelay(45);
		sc = new RadialCannon(13, 4.2, 6, new Angle(Math.PI/6), new Angle(Math.PI/6*-7/2));
		b = new StraightBullet(sc, new Angle(Math.PI*3/2));
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
		//Enemy 5
		e = new LineEnemy(22, 3, Math.PI);
		sc = new RadialCannon(22, 2.6, 3, new Angle(Math.PI/8), new Angle(Math.PI/-4));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter());
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
		//Enemy 6
		e = new LineEnemy(22, 3, 0);
		sc = new RadialCannon(22, 2.6, 3, new Angle(Math.PI/8), new Angle(7*Math.PI/4));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter());
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
		//Enemy 7
		e = new LineEnemy(7, 2.3, 7*Math.PI/6);
		sc = new RadialCannon(9, 3.2, 3, new Angle(Math.PI/6), new Angle(Math.PI/-6));
		sc.setModifier(new RandomModifier(Math.PI/4));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter());
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
		//Enemy 8
		e = new LineEnemy(7, 2.3, 11*Math.PI/6);
		sc = new RadialCannon(9, 3.2, 3, new Angle(Math.PI/6), new Angle(Math.PI/-6));
		sc.setModifier(new RandomModifier(Math.PI/4));
		b = new TargetBullet(sc, getDisplay().getPlayer().getCenter());
		sc.addBullet(b);
		e.addCannon(sc);
		this.addEnemy(e);
		
	}

	@Override
	public void loadPlayerCannons() {
		//Player Cannons
		LaserCannon l;
		NormalCannon nc;
		Bullet b;
		
		//LASER
		//l = new LaserCannon(0, 10, true, 120, 80, .75);
		//b = new Laser(l, 6, 2, true, Color.CYAN);
		//l.setLaserCopy((Laser)b);
		//addPlayerCannon(l);
		
		//Main Cannon
		nc = new NormalCannon(0, 4, 5, 9.5, true);
		b = new StraightBullet(nc, new Angle(Math.PI/2), Color.CYAN, 5);
		nc.addBullet(b);
		//nc.setModifier(new RandomModifier(.02618));
		addPlayerCannon(nc);
		
		//Two slightly angled cannons
		nc = new NormalCannon(4, 2, 7, 8.0, true);
		b = new StraightBullet(nc, new Angle(Math.PI*17/36), Color.CYAN, 4);
		nc.addBullet(b);
		nc.setModifier(new RandomModifier(.02618));
		addPlayerCannon(nc);
		
		nc = new NormalCannon(-4, 2, 7, 8.0, true);
		b = new StraightBullet(nc, new Angle(Math.PI*19/36), Color.CYAN, 4);
		nc.addBullet(b);
		nc.setModifier(new RandomModifier(.02618));
		addPlayerCannon(nc);
		
		//Homing Bullets
		nc = new NormalCannon(0, 0, 50, 6, true);
		b = new HomingBullet(nc, new Color(0,102,255), 8, 3, getDisplay().getEnemies(), 0, 3*Math.PI/2);
		nc.addBullet(b);
		nc.setModifier(new RandomModifier(Math.PI*2));
		addPlayerCannon(nc);
	}

	@Override
	public void loadBonusCannons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadEvents() {
		//Events
		ArrayList<Event> myEvents = new ArrayList<Event>();
		
		myEvents.add(new Event(0, (byte) 0x01,0, DrawingPanel.getpWidth(),0,0x04));
		myEvents.add(new Event(0, (byte) 0x01,1, 0,0,0x04));
		
		myEvents.add(new Event(135, (byte) 0x01,0, DrawingPanel.getpWidth(),0,0x04));
		myEvents.add(new Event(135, (byte) 0x01,1, 0,0,0x04));
		
		myEvents.add(new Event(150, (byte) 0x01,2, 350, 1, 0));
		
		myEvents.add(new Event(230, (byte) 0x01,0, DrawingPanel.getpWidth(),0,0x04));
		myEvents.add(new Event(230, (byte) 0x01,1, 0,0,0x04));
		
		myEvents.add(new Event(300, (byte) 0x01,3, 699, 120, 0x04));//3
		myEvents.add(new Event(315, (byte) 0x01,3, 699, 120, 0x04));
		myEvents.add(new Event(330, (byte) 0x01,3, 699, 120, 0x04));
		myEvents.add(new Event(345, (byte) 0x01,3, 699, 120, 0x04));
		myEvents.add(new Event(360, (byte) 0x01,3, 699, 120, 0x04));
		myEvents.add(new Event(375, (byte) 0x01,3, 699, 120, 0x04));
		myEvents.add(new Event(390, (byte) 0x01,3, 699, 120, 0x04));
		myEvents.add(new Event(405, (byte) 0x01,3, 699, 120, 0x04));
		myEvents.add(new Event(420, (byte) 0x01,3, 699, 120, 0x04));
		
		myEvents.add(new Event(565, (byte) 0x01,4, 100, 1, 0x04));
		myEvents.add(new Event(570, (byte) 0x01,4, 200, 1, 0x04));
		myEvents.add(new Event(575, (byte) 0x01,4, 300, 1, 0x04));
		myEvents.add(new Event(580, (byte) 0x01,4, 400, 1, 0x04));
		myEvents.add(new Event(585, (byte) 0x01,4, 500, 1, 0x04));
		myEvents.add(new Event(590, (byte) 0x01,4, 600, 1, 0x04));
		
		myEvents.add(new Event(1300, (byte) 0x03,0,0,0,0));
		
		myEvents.add(new Event(1301, (byte) 0x01,5, 699, 140, 0x04));
		myEvents.add(new Event(1301, (byte) 0x01,6, 1, 140, 0x04));
		myEvents.add(new Event(1330, (byte) 0x01,5, 699, 140, 0x04));
		myEvents.add(new Event(1330, (byte) 0x01,6, 1, 140, 0x04));
		myEvents.add(new Event(1360, (byte) 0x01,5, 699, 140, 0x04));
		myEvents.add(new Event(1360, (byte) 0x01,6, 1, 140, 0x04));
		myEvents.add(new Event(1390, (byte) 0x01,5, 699, 140, 0x04));
		myEvents.add(new Event(1390, (byte) 0x01,6, 1, 140, 0x04));
		
		myEvents.add(new Event(1310, (byte) 0x01,7, 699, 1, 0x04));
		myEvents.add(new Event(1310, (byte) 0x01,8, 1, 1, 0x04));
		
		myEvents.add(new Event(2500, (byte) 0x00, 0, 0, 0, 0));
		
		
		this.getEvents().addEvents(myEvents);
	}

	@Override
	public void loadPlayer() {
		//TODO -- Copied from Endless
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
