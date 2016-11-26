package objects;

import java.awt.*;

import engine.DrawingPanel;

public class ObjCenter extends Position {
	
	private Color myColor;
	private float diameter = 1;

	public ObjCenter(int x, int y)
	{
		super(x,y);
		myColor = Color.BLACK;
	}
	public ObjCenter(int x, int y, Color c)
	{
		super(x,y);
		myColor = c;
	}
	public void setColor(Color c)
	{
		myColor = c;
	}
	public Color getColor()
	{
		return myColor;
	}
	public float getPointRadius() {
		return diameter/2;
	}
	public float getPointDiameter() {
		return diameter;
	}
	public void draw(DrawingPanel dp)
	{
		dp.getDBG().setColor(getColor());
		dp.getDBG().drawLine(getX(),getY(),getX(),getY()); //ObjCenter is just 1 point
		//dp.getDBG().fillOval((int)Math.round(x-getPointRadius()), (int)Math.round(y-getPointRadius()), Math.round(diameter), Math.round(diameter));
	}
}