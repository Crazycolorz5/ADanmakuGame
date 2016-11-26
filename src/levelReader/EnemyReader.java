package levelReader;

import java.util.ArrayList;

import cannonTypes.Cannon;


public class EnemyReader {
	//SpawnTime(fr),SpawnLoc(x),Type,Speed,Radius,num_of_cannons,Can1Name,Can2Name,Can3Name
	//Type - 1=Chase 2=Line 
	CannonReader cans;
	String levelName;
	ArrayList<Cannon> cannons;
	
	public EnemyReader(String levelName)
	{
		this.levelName = levelName;
		cans = new CannonReader(levelName);
		cannons = new ArrayList<Cannon>();
	}
	
	public void assembleCannons()
	{
		
	}
}
