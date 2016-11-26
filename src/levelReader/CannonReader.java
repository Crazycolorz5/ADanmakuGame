package levelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import bulletTypes.BulletTemplate;

public class CannonReader {

	//Format is:
	//
	//Cannon_Name,Bullet_Data,Period,Speed
	//
	private ArrayList<String> names;
	private BulletReader bullets;
	private String fileName;
	private ArrayList<ArrayList<BulletTemplate>> bulletData;
	private ArrayList<Integer> periods;
	private ArrayList<Double> shootSpeeds;
	
	public CannonReader(String name)
	{
		fileName = name += "_cannons";
		names = new ArrayList<String>();
		bullets = new BulletReader(name);
		bulletData = new ArrayList<ArrayList<BulletTemplate>>();
		periods = new ArrayList<Integer>();
		shootSpeeds = new ArrayList<Double>();
		
		File f = new File(fileName);
		FileInputStream fis;
		try {
			fis = new FileInputStream(f);
			InputStreamReader fin = new InputStreamReader(fis);
			Scanner sin = new Scanner(fin);
			
			sin.useDelimiter(",");
		
			do
			{
				names.add(sin.next());
				bulletData.add(bullets.readData(sin.next()));
				periods.add(sin.nextInt());
				shootSpeeds.add(sin.nextDouble());
			}while(fin.ready());
			sin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int searchFor(String name) throws Exception
	{
		for(int i=0; i<names.size(); i++)
			if(names.get(i).equals(name))
				return i;
		throw new Exception();
	}
	
	public ArrayList<BulletTemplate> getBullets(int index)
	{
		return bulletData.get(index);
	}
	public int shootPeriod(int index)
	{
		return periods.get(index);
	}
	public double shootSpeed(int index)
	{
		return shootSpeeds.get(index);
	}
}
