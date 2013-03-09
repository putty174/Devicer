package villa.game.draw;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ImageManager
{
	ArrayList<Texture> images = new ArrayList<Texture>();
	
	/* Image Management */
	/**
	 * Loads images, assigning an image ID starting from 0 based on
	 * the order the images where added.
	 * 
	 * @param path the path(s) of the images to add.
	 */
	public void load_image(String... path)
	{
		for(int i = 0; i < path.length; i++)
		{
			Texture image = new Texture(Gdx.files.internal(path[i]));
			this.images.add(image);
		}//rof
	}//END load_image
	
	/**
	 * Returns the texture or image given an identifier.
	 * 
	 * @param id the identifier of the texture
	 * @return the texture or image
	 */
	public Texture get_image(int id)
	{
		return this.images.get(id);
	}//END get_image
	
	/**
	 * Removes all images stored in this and disposes of the textures.
	 */
	public void dispose()
	{
		Iterator<Texture> iter = this.images.iterator();
		while(iter.hasNext())
		{
			Texture image = iter.next();
			image.dispose();
			iter.remove();
		}//elihw
	}//END dispose
}
