package engine;

import javax.swing.*;

import bulletTypes.Bullet;
import enemyTypes.Enemy;

import objects.Player;
import objects.Position;
import objects.Unit;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingPanel extends JPanel /*implements Runnable*/{
	/**
	 * 
	 */
	private JFrame myFrame;
	private static final long serialVersionUID = 1L;
	public static int pWidth = 700;
	public static int pHeight = 800;
	//public static final int DELAYS_PER_YIELD = 2;
	public long period = 16666667L;   //nanoseconds
	//public static final int period_milli = 16; //milliseconds
	private boolean isPaused;
	private boolean gameOver;
	private Graphics dbg;
	private Image dbImage = null;
	private Player thePlayer;
	private final int BORDER=14;
	private ArrayList<Unit> enemies = new ArrayList<Unit>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Bullet> Pbullets = new ArrayList<Bullet>();
	private Engine gameEngine;
	
	
	public int getBORDER() {
		return BORDER;
	}

	public DrawingPanel(){
		setBackground(Color.blue);
		setPreferredSize(new Dimension(pWidth,pHeight));
		this.setSize(pWidth, pHeight);
		this.setMinimumSize(getPreferredSize());
		this.setMaximumSize(getMinimumSize());
		
		this.setFocusable(true);
		requestFocus();
		addKeyListener(new keyboardInput());

		//thePlayer = new Player(300,600);
		
		gameEngine = new Engine(this);
	}
	
	public void loadPlayer(Player p)
	{
		thePlayer = p;
	}
	
	public void pause(){
		isPaused = !isPaused;
	}

	//put image on graphic
	public void paintScreen(){
		Graphics g;
		try{
			g = this.getGraphics();
			if(g != null && dbImage != null)
			{
				g.drawImage(dbImage,0,0,null);
				Toolkit.getDefaultToolkit().sync();
				g.dispose();
			}
		}
		catch(Exception e){
			System.out.print("Graphics context error" + e);
		}
	}
	
	//put graphic 0n screen
	public void gameRender(){
		if(dbImage == null)
		{
			dbImage = createImage(this.getWidth(),this.getHeight());
			if(dbImage == null)
			{
				System.out.print("dbImage is still null");
				return;
			}
			else
			{
				dbg = dbImage.getGraphics();	
			}
		}
		dbg.setColor(Color.white);
		dbg.fillRect(0, 0, this.getWidth(), this.getHeight());
		dbg.setColor(Color.BLACK);
		for(int i=0;i<BORDER;i++){
			dbg.drawRect(0+i, 0+i, this.getWidth()-2*i, this.getHeight()-2*i);
		}
		drawGameElements();
	}
	
	//get width on Panel
	public static int getpWidth() {
		return pWidth;
	}
	//setWidth of pannel
	public static void setpWidth(int pWidth) {
		DrawingPanel.pWidth = pWidth;
	}

	//get panel hegiht
	public static int getpHeight() {
		return pHeight;
	}
	//set panel heght
	public static void setpHeight(int pHeight) {
		DrawingPanel.pHeight = pHeight;
	}

	public boolean isXOpen(double x) {
		//is on grid
		boolean isInBounds = (x>=BORDER && x<=this.getWidth()-BORDER);
		
		//&& for all possible obstacles
		return isInBounds;
	}
	public boolean isYOpen(double y) {
		//is on grid
		boolean isInBounds = (y>=BORDER && y<=this.getHeight()-BORDER);
		
		//&& for all possible obstacles
		return isInBounds;
	}
	public boolean isXOpenBullet(double x) {
		//is on grid
		boolean isInBounds = (x>=0 && x<=this.getWidth());
		
		//&& for all possible obstacles
		return isInBounds;
	}
	public boolean isYOpenBullet(double y) {
		//is on grid
		boolean isInBounds = (y>=0 && y<=this.getHeight());
		
		//&& for all possible obstacles
		return isInBounds;
	}
	
	public boolean isValidPosition(Unit unit)
	{
		return isXOpen(unit.getX()+unit.surfaceSize())&&isXOpen(unit.getX()-unit.surfaceSize())&&isYOpen(unit.getY()+unit.surfaceSize())&&isYOpen(unit.getY()-unit.surfaceSize());
		
	}
	public boolean isValidPosition(Player p)
	{
		return isXOpen(p.getX()+p.getRadius())&&isXOpen(p.getX()-p.getRadius())&&isYOpen(p.getY()+p.getRadius())&&isYOpen(p.getY()-p.getRadius());
		
	}
	public boolean isValidPositionBullet(Bullet u)
	{
		return isXOpenBullet(u.getX()+u.surfaceSize())&&isXOpenBullet(u.getX()-u.surfaceSize())&&isYOpenBullet(u.getY()+u.surfaceSize())&&isYOpenBullet(u.getY()-u.surfaceSize());
		
	}
	public Image getDbImage() {
		return dbImage;
	}

	public void setDbImage(Image dbImage) {
		this.dbImage = dbImage;
	}

	private void drawGameElements(){
		//Layers top-->bottom:
		//hitbox --> ebullets --> pbullets --> enemies --> player
		thePlayer.draw(this);
		for(Unit e:enemies) {
			e.draw(this);
		}
		for(Bullet b:Pbullets) {
			b.draw(this);
		}
		for(Bullet b:bullets) {
			b.draw(this);
		}
		if(thePlayer.isSlow())
		{
			thePlayer.getCenter().draw(this);
		}
	}
	
	public void gameUpdate(){
		if(!isPaused && !gameOver){
			thePlayer.move();
			if(!isValidPosition(thePlayer))
			{
				setAtBorder(thePlayer);
			}
			thePlayer.tick(this);
			for(int c = 0; c<Pbullets.size(); c++) {
				Pbullets.get(c).move();
				if(!isValidPositionBullet(Pbullets.get(c)))
				{
					Pbullets.remove(c);
					c--;
				}
				else
					Pbullets.get(c).tick(this);
			}
			for(int c = 0; c<enemies.size(); c++) {
				enemies.get(c).move();
				if(((Enemy)(enemies.get(c))).isOnSurface(thePlayer))
				{
					playerIsHit(/*enemies.get(c)*/);
				}
				if(!isValidPosition(enemies.get(c)))
				{
					if(enemies.get(c).getDispose())
					{
						enemies.remove(c);
						c--;
					}
					else
					{
						setAtBorder(enemies.get(c));
						enemies.get(c).tick(this);						
					}
				}
				else
					enemies.get(c).tick(this);
				for(int d = 0; d<Pbullets.size()&&c>=0; d++)
				{
					//System.out.println("c=" + c + " d=" + d);
					if(Pbullets.get(d).isOnSurface(enemies.get(c)))
					{
						if(Pbullets.get(d).hasCollided(enemies.get(c)))
						{
							Pbullets.get(d).uponDeath();
							Pbullets.remove(d);
							d--;
						}
						if(((Enemy) (enemies.get(c))).dead())
						{
							enemies.get(c).uponDeath();
							enemies.remove(c);
							c--;
						}
					}
				}
			}
			for(int c = 0; c<bullets.size(); c++) {
				bullets.get(c).move();
				if(bullets.get(c).isOnSurface(thePlayer))
				{
					bullets.get(c).uponDeath();
					playerIsHit(/*bullets.get(c)*/);
				}
				if(!isValidPosition(bullets.get(c).getSurfaceCenter()))
				{
					bullets.remove(c);
					c--;
				}
				else
					bullets.get(c).tick(this);
			}
		}
	}
	
	public void playerIsHit(/*Unit b*/) {
		// TODO Auto-generated method stub
		thePlayer.setSlow(); //See hitbox for debugging
		gameEngine.lose();
		//System.out.println("Game Over!");
		//System.out.println("You survived for " + gameEngine.getTickResets() 
		//		+ " tick resets and " + gameEngine.getTicks() + " ticks!");
		//System.out.println("Period: " + period);
		new RestartWindow(220,120, this);
	}
	public void setAtBorder(Unit u) {
		//(x>=BORDER && x<=this.getWidth()-2*BORDER)
		if(u.getSurfaceCenter().getXcoord()-u.surfaceSize()<BORDER)
			u.getSurfaceCenter().setX(BORDER+u.surfaceSize());
		else if(u.getSurfaceCenter().getXcoord()+u.surfaceSize()>this.getWidth()-BORDER)
			u.getSurfaceCenter().setX(this.getWidth()-BORDER-u.surfaceSize());
		if(u.getSurfaceCenter().getYcoord()-u.surfaceSize()<BORDER)
			u.getSurfaceCenter().setY(BORDER+u.surfaceSize());
		else if(u.getSurfaceCenter().getYcoord()+u.surfaceSize()>this.getHeight()-BORDER)
			u.getSurfaceCenter().setY(this.getHeight()-BORDER-u.surfaceSize());
	}
	public void setAtBorder(Player p) {
		//(x>=BORDER && x<=this.getWidth()-2*BORDER)
		if(p.getCenter().getXcoord()-p.getRadius()<BORDER)
			p.getCenter().setX(BORDER+p.getRadius());
		else if(p.getCenter().getXcoord()+p.getRadius()>this.getWidth()-BORDER)
			p.getCenter().setX(this.getWidth()-BORDER-p.getRadius());
		if(p.getCenter().getYcoord()-p.getRadius()<BORDER)
			p.getCenter().setY(BORDER+p.getRadius());
		else if(p.getCenter().getYcoord()+p.getRadius()>this.getHeight()-BORDER)
			p.getCenter().setY(this.getHeight()-BORDER-p.getRadius());
	}
	public void randomizeUnitPosition(Unit u)
	{	
		do{
			u.getSurfaceCenter().setY((int) (Math.random()*(getWidth()-getBORDER())+getBORDER()));
			u.getSurfaceCenter().setX((int) (Math.random()*(getHeight()-getBORDER())+getBORDER()));
		}while(!isValidPosition(u.getSurfaceCenter()));
	}
	public void randomizeUnitPositionAtTop(Unit u) {
		do{
		u.getSurfaceCenter().setY((int) (getBORDER()+u.surfaceSize()));
		u.getSurfaceCenter().setX((int) (Math.random()*(getHeight()-getBORDER())+getBORDER()));
		}while(!isValidPosition(u.getSurfaceCenter()));
	}
	public void addEnemy(Enemy e, int x, int y)
	{
		e.setX(x);
		e.setY(y);
		setAtBorder(e);
		enemies.add(e);
	}
	public void addEnemyBullet(Bullet b) {
		bullets.add(b);
	}
	public void addPlayerBullet(Bullet b) {
		Pbullets.add(b);
	}
	
	private boolean isValidPosition(Position position) {
		return isXOpen(position.getXcoord())&&isYOpen(position.getYcoord());
	}

	
	
	//up:38
	//down:40
	//left: 37
	//right: 39
	//left shift:16
	//space: 32
	
	public void gameReset() {
		
	}

	private class keyboardInput implements KeyListener {
		boolean leftPressed=false;
		boolean rightPressed=false;
		boolean upPressed=false;
		boolean downPressed=false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_ESCAPE)
				gameEngine.stop();
			else if(keyCode==38&&!upPressed){
				thePlayer.moveUp();
				upPressed = true;
			} else if(keyCode==39&&!rightPressed) {
				thePlayer.moveRight();
				rightPressed = true;
			} else if(keyCode==40&&!downPressed){
				thePlayer.moveDown();
				downPressed = true;
			} else if(keyCode==37&&!leftPressed) {
				thePlayer.moveLeft();
				leftPressed = true;
			} else if(keyCode==16) {
				thePlayer.setSlow();
			} else if(keyCode==32||keyCode==90) {
				thePlayer.setShooting();
			}
			//System.out.println(keyCode);
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			if(keyCode == KeyEvent.VK_ESCAPE)
				gameEngine.start();
			else if(keyCode==38){
				thePlayer.moveDown();
				upPressed = false;
			} else if(keyCode==39) {
				thePlayer.moveLeft();
				rightPressed = false;
			} else if(keyCode==40){
				thePlayer.moveUp();
				downPressed = false;
			} else if(keyCode==37) {
				thePlayer.moveRight();
				leftPressed = false;
			} else if(keyCode==16) {
				thePlayer.setFast();
			} else if(keyCode==32||keyCode==90) {
				thePlayer.setNotShooting();
			}
			
		}
	
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	}
	public void startEngine()
	{
		Thread gameThread = new Thread(gameEngine);
		gameThread.start();
		gameEngine.start();
	}
	public Player getPlayer()
	{
		return thePlayer;
	}
	public ArrayList<Unit> getEnemies()
	{
		return enemies;
	}
	public Engine getEngine()
	{
		return gameEngine;
	}
	/*
	private void wait(double n){
	    long t0,t1;
	    t0=System.currentTimeMillis();
	    do{
	        t1=System.currentTimeMillis();
	    }
	    while (t1-t0<n*10);
	}*/
	public void setPeriod(long period)
	{
		this.period = period;
		gameEngine.setPeriod(period);
	}
	public Graphics getDBG()
	{
		return dbg;
	}
	public void setFrame(JFrame f)
	{
		this.myFrame = f;
	}
	public void dispose()
	{
		myFrame.dispose();
	}
	public void destroyAllEnemies()
	{
		int iSize = enemies.size();
		for(int i=0; i<iSize; i++)
			enemies.remove(0);
	}
	public void removeAllBullets()
	{
		int iSize = bullets.size();
		for(int i=0; i<iSize; i++)
			bullets.remove(0);
	}	
}
