package objects;

import engine.DrawingPanel;


public interface Unit extends Surface {

	public int getX();
	public int getY();
	public void draw(DrawingPanel drawingPanel);
	public void tick(DrawingPanel drawingPanel);
	public boolean getDispose();
	public void isHit(int power);
	public void move();
	public void uponDeath();
	
}
