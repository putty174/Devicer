package game.objects;

import game.objects.actions.Attack;
import game.objects.attributes.AttackAttributes;

import java.util.ArrayList;


import com.badlogic.gdx.graphics.Texture;

public class Enemy extends AnimatedObject
{
	AttackAttributes attack = new AttackAttributes();
	private float cooldown = 0;
	GameObject device;
	public boolean stunned = false;
	byte track;

	public Enemy(GameObject device, int objectID, float posX, float posY, float mass,
			float friction, float hitWidth, float hitHeight, float hitX,
			float hitY, boolean isSolid, float touchRadius,
			boolean isTouchable, float drawWidth, float drawHeight,
			Texture sprites, int srcWidth, int srcHeight) {
		super(objectID, posX, posY, mass, friction, hitWidth, hitHeight, hitX, hitY,
				isSolid, touchRadius, isTouchable, drawWidth, drawHeight,
				sprites, srcWidth, srcHeight);
		this.device = device;
		/* Stats */
		this.attack.damage = 1;
		this.attack.power = 200;
		this.attack.range = 70;
		this.movement.speed = 100;
		this.movement.speedcap = 300;
		this.movement.acceleation = 10;
		this.health.current = 1;
		this.health.max = 3;
		
		this.add_animation(0, 0, 8, 5, true);
		this.set_animation(0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(float dt, ArrayList<GameObject> objects)
	{
		super.update(dt, objects);
		
		if(getHp() <= 0){
			this.terminate();
		}
		
		if(!stunned)
		{
			if(this.action_queue.get_actionID() == 0)
			{
				if(this.cooldown <= 0)
				{
						this.cooldown = 0.6f;
						this.action_queue.clear();
						this.action_queue.add_action(new Attack(device, movement, attack));
				}//fi
				else
				{
					this.cooldown -= dt;
				}//esle
			}//fi
		}//fi
		else
		{
			this.unstun();
		}//esle
	}//END update
	
	public void stun()
	{
		this.isSolid = false;
		this.stunned = true;
		this.action_queue.clear();
	}
	
	public void unstun()
	{
		this.isSolid = true;
		this.stunned = false;
	}
	
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
			
			if(this.isColliding(collider, this.get_position()))
			{
				if(collider.isSolid)
				{
					this.stun();
				}//fi
			}//fi
		}//fi
		
		if(collider.getID() == 2)
		{
			collider.terminate();
			this.setHp(this.getHp() + 1);
			this.movement.speed += 25;
		}
	}//END behavior_collision
}
