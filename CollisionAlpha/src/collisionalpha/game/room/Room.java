package collisionalpha.game.room;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import collisionalpha.game.controls.Controllable;
import collisionalpha.game.objects.GameObject;
import collisionalpha.game.objects.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Room implements Controllable
{
	/* Background */
	private Texture background;
	
	/* Objects */
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private HashSet<GameObject> spawn = new HashSet<GameObject>();
	private Player player;
	public int monsterCount;

	/* Constructor */
	public Room(Texture background, Player player)
	{
		this.background = background;
		this.player = player;
	}//END Room
	
	/* Object Management */
	/**
	 * Adds an object to the Room's list of objects.
	 * 
	 * @param obj the object to add
	 */
	public void add_object(GameObject obj)
	{
		this.objects.add(obj);
	}//END add_object
	
	/**
	 * Adds an object to the Room's list of objects to spawn.
	 * 
	 * @param obj the object to add
	 */
	public void spawn_object(GameObject obj)
	{
		this.spawn.add(obj);
	}//END spawn_object
	
	/* Input */
	@Override
	public void input_touchDown(int x, int y, int pointer, int button)
	{
		Iterator<GameObject> iter = this.objects.iterator();
		
		GameObject target = null;
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			boolean touchedObject = obj.touch(x, y);
			if(touchedObject && (target == null || obj.get_position().dst(x,y) < target.get_position().dst(x,y)))
			{
				target = obj;
			}//fi
		}//elihw
		this.player.input_touchDown(x, y, pointer, button, target);
	}//END input_touch
	
	@Override
	public void input_touchUp(int x, int y, int pointer, int button)
	{
		this.player.input_touchUp(x, y, pointer, button);
	}//END input_touchUp
	
	/* Update */
	/**
	 * Updates all the objects in the room.
	 * 
	 * @param dt the difference in time between this and the last cycle
	 */
	public boolean update(float dt)
	{
		monsterCount = objects.size() - 2;
		boolean gameIsLost = false;
		
		/* Spawn Objects */
		Iterator<GameObject> iter = this.spawn.iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			this.objects.add(obj);
			iter.remove();
		}//elihw
		
		/* Update Objects */
		iter = this.objects.iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			obj.update(dt, this.objects);
			obj.endUpdate();				
			if(obj.isGone())
			{
				iter.remove();
			}//fi			
		}//elihw
		return gameIsLost;
	}//END Update
	
	/* Draw */
	public void render(SpriteBatch spritebatch)
	{
		//Render Background
		spritebatch.draw(this.background, 0, 0);
		
		//Render Objects
		Iterator<GameObject> iter = this.objects.iterator();
		while(iter.hasNext())
		{
			GameObject obj = iter.next();
			obj.render(spritebatch);
		}//elihw
	}//END render
}
