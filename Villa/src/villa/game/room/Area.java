package villa.game.room;

import villa.game.tile.Tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Area
{
	private Tile[] area; //All the tiles in the area.
	private int width; //The width of the area.
	private int height; //The height of the area.
	
	/* Constructor */
	/**
	 * Creates a Area object which contains the tiles in the area.
	 * 
	 * @param area the tiles in the area
	 * @param width the width of the area
	 * @param height the height of the area
	 */
	public Area(int[] area, int width, int height)
	{
		this.area = new Tile[area.length];
		for(int i = 0; i < area.length; i++)
		{
			this.area[i] = new Tile(area[i]);
		}//rof
		
		this.width = width;
		this.height = height;
	}//END Area
	
	/* Tile Management */
	/**
	 * Returns the tile given the x and y of it.
	 * 
	 * @param x the column of the tile
	 * @param y the row of the tile
	 * @return the tile given by x and y
	 */
	public final Tile getTile(int x, int y)
	{
		return this.area[x + y * width];
	}//END getTile
	
	/**
	 * Returns the width of the area.
	 * 
	 * @return the width
	 */
	public final int width()
	{
		return this.width;
	}//END width
	
	/**
	 * Returns the height of the area.
	 * 
	 * @return the height
	 */
	public final int height()
	{
		return this.height;
	}//END height
	
	/* Render */
	/**
	 * Renders the tiles to the screen.
	 * 
	 * @param batch the spritebatch that is used to render images to the screen
	 * @param scalar the scalar to resize the image to the right size
	 */
	public void render(SpriteBatch batch, float scalar)
	{
		for(int j = 0; j < height; j++)
		{
			for(int i = 0; i < width; i++)
			{
				this.area[i].render(batch, i, j, scalar);
			}//rof
		}//rof
	}//END render
}//END Area

//EOF
