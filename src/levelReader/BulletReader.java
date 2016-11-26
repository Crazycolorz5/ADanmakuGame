package levelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import bulletTypes.BulletTemplate;

public class BulletReader {
	//Bullet_Type,Bullet_Radius...
	//Type 1 = targeted at player
	//Type 2 = targeted w/ constant angle
	//if type 2, include angle in radians
	
	String levelName;
	
	public BulletReader(String name)
	{
		levelName = name;
	}
	public ArrayList<BulletTemplate> readData(String name)
	{
		File f = new File(levelName+"_"+name);
		ArrayList<BulletTemplate> bulletData = new ArrayList<BulletTemplate>();
		try {
			FileInputStream fis = new FileInputStream(f);
			InputStreamReader fin = new InputStreamReader(fis);
			Scanner sin = new Scanner(fin);
			sin.useDelimiter(",");
			ArrayList<BulletTemplate> bullets = new ArrayList<BulletTemplate>();
			BulletTemplate temp;
			int type;
			double angle;
			int radius;
			do
			{
				type = sin.nextInt();
				if(type==1)
				{
					radius = sin.nextInt();
					temp = new BulletTemplate(type, radius);
					bullets.add(temp);
				}
				if(type==2)
				{
					angle = sin.nextDouble();
					radius = sin.nextInt();
					temp = new BulletTemplate(type, angle, radius);
					bullets.add(temp);
				}
			}while(fin.ready());
			sin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bulletData;
	}
}
