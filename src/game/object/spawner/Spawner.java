package game.object.spawner;

import game.controls.GameTimer;
import game.objects.Enemy;
import game.objects.GameObject;
import game.room.Room;

import java.util.HashSet;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class Spawner
{
	private Random generator = new Random();
	private Room room;
	private GameObject box;
	private Texture texture;
	private int spawned_amount = 3;
	
	/* Interval */
	private GameTimer interval;
	private float interval_min;
	private float interval_decr;
	
	/* Constructor */
	public Spawner(Room room, GameObject box, Texture texture, float interval, float interval_min, float interval_decr)
	{
		this.room = room;
		this.box = box;
		this.texture = texture;
		this.interval = new GameTimer(interval);
		this.interval_min = interval_min;
		this.interval_decr = interval_decr;
	}//END Spawner
	
	/* Interval */
	public float get_interval()
	{
		return this.interval.get_interval();
	}//END get_interval	
	
	/* Spawn */
	private void spawn()
	{
		/*
		for(int i = 0; i < this.spawned_amount; i++)
		{
			boolean top = generator.nextBoolean();
			boolean side = generator.nextBoolean();
			int randomX = (int)(generator.nextFloat() * 200);
			int randomY = (int)(generator.nextFloat() * 200);
			float xSpawn = (top ? (side ? -100-randomX : Gdx.graphics.getWidth()+100+randomX) : generator.nextInt(Gdx.graphics.getWidth())+100+randomX);
			float ySpawn = (top ? generator.nextInt(Gdx.graphics.getHeight()+100+randomY) : (side ? -100-randomY : Gdx.graphics.getHeight())+100+randomY);
			GameObject enemy = new Enemy(box,3, xSpawn, ySpawn, 1f, 300, 50, 50, 0, 0, 
					true, 120, true, 70, 70, texture, 150, 150);
			room.add_object(enemy);
			System.out.println("Spawn!!!");
		}//rof
		this.spawned_amount += 0.3;
		*/
		boolean top = generator.nextBoolean();
		boolean side = generator.nextBoolean();
		int randomX = (int)(generator.nextFloat() * 200);
		int randomY = (int)(generator.nextFloat() * 200);
		float xSpawn = (top ? (side ? -100-randomX : Gdx.graphics.getWidth()+100+randomX) : generator.nextInt(Gdx.graphics.getWidth())+100+randomX);
		float ySpawn = (top ? generator.nextInt(Gdx.graphics.getHeight()+100+randomY) : (side ? -100-randomY : Gdx.graphics.getHeight())+100+randomY);
		GameObject enemy = new Enemy(box,3, xSpawn, ySpawn, 1f, 300, 50, 50, 0, 0, 
				true, 120, true, 70, 70, texture, 150, 150);
		room.add_object(enemy);
	}//END spawn
	
	
	/* Update */
	public void update(float dt)
	{
		this.interval.update_timer(dt);
		if(this.interval.isDone())
		{
			this.interval.set_interval(this.interval.get_interval() - this.interval_decr);
			if(this.interval.get_interval() < this.interval_min)
			{
				this.interval.set_interval(this.interval_min);
			}//fi
			
			this.spawn();
			this.interval.reset_timer();
		}//fi
	}//END update
}//END class Spawner
