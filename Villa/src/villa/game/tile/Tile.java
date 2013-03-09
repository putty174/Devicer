package villa.game.tile;

import villa.game.draw.GameImage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile
{
	/* Identifier */
	private int tileID;
	
	/* Image */
	protected GameImage image; //The image of the tile.
	//Constants
	protected static final int IMAGE_SRCSIZE = 64;
	protected static final int IMAGE_DSTSIZE = 1;
	
	/* Construction */
	/**
	 * Creates a tile.
	 * 
	 * @param id the tile identifier
	 */
	public Tile(int id)
	{
		this.tileID = id;
	}//END Tile
	
	/* ID */
	/**
	 * @return the identifier of the tile
	 */
	public final int getID()
	{
		return this.tileID;
	}//END getType
	
	/* Image */
	/**
	 * Sets the image of the tile.
	 * 
	 * @param image the image/images to set the tile as
	 */
	public void set_image(Texture image)
	{
		this.image = new GameImage(
			image, //Image
			IMAGE_SRCSIZE,  IMAGE_SRCSIZE, //Image Source Area
			IMAGE_DSTSIZE, IMAGE_DSTSIZE //Image Destination Area
		);
	}//END set_image
	
	/* Render */
	/**
	 * Renders the tile to the screen.
	 * 
	 * @param batch the spritebatch that is used to render images to the screen
	 * @param x the x position to render the tile.
	 * @param y the y position to render the tile.
	 * @param scalar the scalar to resize the image to the right size
	 */
	public void render(SpriteBatch batch, int x, int y, float scalar)
	{
		this.image.render(batch, x * IMAGE_DSTSIZE, y * IMAGE_DSTSIZE, scalar);
	}//END render
}//END Tile

//EOF