package engine;

public class Event {
	private int tick;
	private byte code;
	/*0x01 = spawn enemy 
	 *0x02 = add Player Cannon
	 *
	 *
	 *
	 *0xFE = Win Game
	 *0xFF = reset tick counter
	 */
	private int[] data;
	public Event(int tick, byte code, int[] data)
	{
		this.tick = tick;
		this.code = code;
		this.data = data;
	}
	
	public Event(int tick, byte code, int data1, int data2, int data3, int data4)
	{
		this.tick = tick;
		this.code = code;
		this.data = new int[4];
		data[0] = data1;
		data[1] = data2;
		data[2] = data3;
		data[3] = data4;
	}
	
	public int getTime()
	{
		return tick;
	}
	public byte getCode()
	{
		return code;
	}
	public int[] getData()
	{
		return data;
	}
	/* Event Data Documentation
	 * 0x00 - Cause Game Over
	 * 
	 * 0x01 - Spawn Enemy:
	 * 	data[0] = Enemy ID
	 * 	data[1] = X
	 * 	data[2] = Y
	 * 	data[3] = special codes
	 * 
	 * 0x02 - Add Player Cannon
	 *  data[0] = Cannon ID
	 * 
	 * 0x03 - Destroy All Enemies and Remove All Bullets
	 * 
	 * 0x04 - Destroy ALL Enemies
	 * 
	 * 0x05 - Remove All Bullets
	 * 
	 * 0x06 - Reset Tick Counter
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
