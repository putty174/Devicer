package collisionalpha.game.objects;

import java.util.ArrayList;

import collisionalpha.game.objects.actions.Attack;
import collisionalpha.game.objects.actions.Goto;

import com.badlogic.gdx.graphics.Texture;

public class Enemy extends GameObject
{
	GameObject device;
	public boolean stunned = false;

	public Enemy(GameObject device, int objectID, float posX, float posY, float mass,
			float friction, float hitWidth, float hitHeight, float hitX,
			float hitY, int[] colliders, boolean isSolid, float touchRadius,
			boolean isTouchable, float drawWidth, float drawHeight,
			Texture sprites, int srcWidth, int srcHeight) {
		super(objectID, posX, posY, mass, friction, hitWidth, hitHeight, hitX, hitY,
				colliders, isSolid, touchRadius, isTouchable, drawWidth, drawHeight,
				sprites, srcWidth, srcHeight);
		this.device = device;
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void update(float dt, ArrayList<GameObject> objects)
	{
		super.update(dt, objects);
		
		
		this.action_queue.clear();
		if(!stunned)
		{
			this.action_queue.add_action(new Goto(device.get_positionX(), device.get_positionY()));
		}//fi
		else
		{
			if(this.get_vMagnitude() <= 0)
			{
				this.stunned = false;
				this.isSolid = true;
			}
		}
	}
	
	public void stun()
	{
		this.stunned = true;
		this.isSolid = false;
		this.action_queue.clear();
	}
	
	@Override
	public void behavior_collision(GameObject collider)
	{
		float direction = (float)Math.atan2(- this.get_positionY() + collider.get_positionY(),
				- this.get_positionX() + collider.get_positionX());
		float vmag = this.get_vMagnitude()/(collider.get_mass() * 10);
		if(vmag > collider.get_vMagnitude())
		{
			float xcomp = (float)(vmag * Math.cos(direction));
			float ycomp = (float)(vmag * Math.sin(direction));
			collider.set_velocity(this.get_momentum().div(collider.get_mass()*10).add(xcomp, ycomp));
		}//fi
	}//END behavior_collision
}
