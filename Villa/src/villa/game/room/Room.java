package villa.game.room;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Room
{
	//Tiles
	private Area area;
	
	//Objects
	private ObjectsHolder objects = new ObjectsHolder();
	
	/* Constructor */
	/**
	 * Creates a room object that holds the information of the currently loaded area.
	 * 
	 * @param area the tiles in the area.
	 */
	public Room(Area area)
	{
		this.area = area;
	}//END Room
	
	/* Render */
	/**
	 * Renders everything in the map
	 * 
	 * @param batch the spritebatch that is used to render images to the screen
	 * @param scalar the scalar to resize the image to the right size
	 */
	public void render(SpriteBatch batch, float scalar)
	{
		//Render Tiles
		this.area.render(batch, scalar);
		//Render Objects
		this.objects.render(batch, scalar);
	}//END render
	
	/* Update */
	/**
	 * Updates everything in the room.
	 * 
	 * @param dt the time that has passed.
	 */
	public void update(float dt)
	{
		this.objects.update(dt);
	}//END update
}
