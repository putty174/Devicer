package villa.game.tile;



public class Soil extends Tile
{
	private boolean isWet; //Whether or not the soil is wet or not.
	
	/* Constructor */
	public Soil(int id)
	{
		super(id);
		// TODO Auto-generated constructor stub
	}//END Soil

	/* Soil Properties */
	/**
	 * Wets the soil.
	 */
	public final void wet()
	{
		this.isWet = true;
	}//END wet
	
	/**
	 * Dries the soil.
	 */
	public final void dry()
	{
		this.isWet = false;
	}//END dry
	
	/**
	 * Tells whether the soil is wet or not.
	 * 
	 * @return true if is wet, false if dry.
	 */
	public final boolean isWet()
	{
		return this.isWet;
	}//END isWet
}//END class Soil

//EOF