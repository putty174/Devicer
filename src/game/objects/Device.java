package game.objects;

import game.controls.GameTimer;
import game.room.Room;

import java.util.ArrayList;


import com.badlogic.gdx.graphics.Texture;

public class Device extends GameObject {

	private GameTimer timer;
	private Texture exp_sprites;
	private Room room;
	
	public Device(float posX, float posY, Texture sprites, Room room, Texture exp_sprites)
	 {
		super(
				1, //ID
				posX, posY, //Position
				1, //Mass
				400, //Friction
				58, 58, //Hit Height and Width
				0, 0, //Hit x and y offset
				true, //Solid or not
				60, //Touch Radius
				true, //Touchable
				64, 64, //Draw width and height
				sprites, //Spritesheet
				64, 64 //srcwidth and height
				);
		
		this.exp_sprites = exp_sprites;
		this.screenBound = true;
		this.room = room;
		this.timer = new GameTimer(8);
		this.health.max = 10;
		this.health.current = 10;
	}//END Device
	
	@Override
	public void behavior_collision(GameObject collider)
	{
		if(collider.isSolid)
		{
			double direction = Math.atan2(this.get_positionY() - collider.get_positionY(),
					this.get_positionX() - collider.get_positionX());
			float xComp = (float)(30 * collider.get_mass() / this.get_mass() * Math.cos(direction));
			float yComp = (float)(30 * collider.get_mass() / this.get_mass() *  Math.sin(direction));
			this.add_velocity(xComp, yComp);
		}//fi
	}//END behavior_collision
	
	@Override
	public void update(float dt, ArrayList<GameObject> objects)
	{
		if(this.health.current <= 0)
		{
			this.terminate();
		}//fi
		
		float dir = (float)(360 * Math.random());
		
		XP exp1 = new XP(this.get_positionX(), this.get_positionY(), this.exp_sprites, 300, dir);
		XP exp2 = new XP(this.get_positionX(), this.get_positionY(), this.exp_sprites, 300, dir - 120);
		XP exp3 = new XP(this.get_positionX(), this.get_positionY(), this.exp_sprites, 300, dir + 120);
		
		this.timer.update_timer(dt);
		if(this.timer.isDone())
		{
			this.room.spawn_object(exp1);
			this.room.spawn_object(exp2);
			this.room.spawn_object(exp3);
			this.timer.reset_timer();
		}//fi
		
		super.update(dt, objects);
	}

}
