package villa.game.room;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import villa.game.object.GameObject;

public class ObjectsHolder
{
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private HashSet<GameObject> spawn = new HashSet<GameObject>();
	
	/* Object Management */
	/**
	 * @return all the objects in the holder
	 */
	public ArrayList<GameObject> get_objects()
	{
		return this.objects;
	}//END get_objects
	
	/**
	 * Adds an object to the holder to later be spawned.
	 * 
	 * @param obj the object to add
	 */
	public void add_object(GameObject obj)
	{
		this.spawn.add(obj);
	}//END add_obj

	/**
	 * Adds the objects from the spawn list to the main list.
	 */
	private void spawn_objects()
	{
		Iterator<GameObject> iter = this.spawn.iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			this.objects.add(obj);
			iter.remove();
		}//elihw
	}//END spawn_objects
	
	/* Render */
	/**
	 * Renders all the objects in the holder.
	 * 
	 * @param batch the sprite batch used to render objects to the screen
	 * @param scalar used to scale things to the right proportions
	 */
	public void render(SpriteBatch batch, float scalar)
	{
		Iterator<GameObject> iter = this.objects.iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			obj.render(batch, scalar);
		}//elihw
	}//END render
	
	/* Update */
	/**
	 * Updates all the objects in the room.
	 * 
	 * @param dt the time that has passed
	 */
	public void update(float dt)
	{
		this.spawn_objects();
		this.step_objects(dt);
		this.step_end_objects();
	}//END update
	
	/**
	 * Updates the objects in the holder.
	 * 
	 * @param dt the time that has passed
	 */
	private void step_objects(float dt)
	{
		Iterator<GameObject> iter = this.objects.iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			obj.step(dt, this);
		}//elihw
	}//END step_objects
	
	/**
	 * Does the ending updates of the objects in the holder.
	 */
	private void step_end_objects()
	{
		Iterator<GameObject> iter = this.objects.iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			obj.step_end();
		}//elihw
	}//END step_end_objects
}
