package villa.game.draw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameImage
{
	private Sprite sprite; //The image to draw.
	private int srcWidth, srcHeight; //The area of where cut the image out.
	private float dstWidth, dstHeight; //The area to draw.
	
	/* Constructor */
	public GameImage(Texture image, int srcWidth, int srcHeight, float dstWidth, float dstHeight)
	{
		this.sprite = new Sprite(image, srcWidth, srcHeight);
		this.srcWidth = srcWidth;
		this.srcHeight = srcHeight;
		this.dstWidth = dstWidth;
		this.dstHeight = dstHeight;
	}//END GameImage
	
	/* Image */
	/**
	 * Sets the sprite.
	 * 
	 * @param x the column the sprite is in.
	 * @param y the row the sprite is in.
	 */
	public final void set_sprite(int x, int y)
	{
		this.sprite.setRegion(x * this.srcWidth, y * this.srcWidth, this.srcWidth, this.srcHeight);
	}//END set_sprite
	
	/**
	 * Sets the destination rectangle's height and width.
	 * 
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 */
	public final void set_dst(float width, float height)
	{
		this.dstWidth = width;
		this.dstHeight = height;
	}//END set_dst
	
	/* Render */
	/**
	 * Renders the image to the screen.
	 * 
	 * @param batch the sprite batch.
	 * @param x the x position of the image.
	 * @param y the y position of the image.
	 * @param scalar the scalar that is used to scale objects to the right proportions
	 */
	public void render(SpriteBatch batch, float x, float y, float scalar)
	{
		//Setup
		this.sprite.setSize(this.dstWidth * scalar, this.dstHeight * scalar);
		this.sprite.setPosition(x, y);
		
		//Render
		this.sprite.draw(batch);
	}//END render
}//END GameImage
