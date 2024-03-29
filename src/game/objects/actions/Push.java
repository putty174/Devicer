package game.objects.actions;

import game.objects.Device;
import game.objects.Enemy;
import game.objects.GameObject;
import game.objects.attributes.MovementAttributes;

import com.badlogic.gdx.math.Vector2;


public class Push extends Action
{
	private GameObject target; //The object the attacking object is attacking.
	private MovementAttributes movement; // Determines how the object moves.
	private float force; //The power of the push.
	private float direction; //The direction of the push.
	private float range; //The range the object will push.
	
	/* Constructor */
	/**
	 * Creates a command telling an object to attack another object.
	 * <p>
	 * The ID of attack is 2.
	 * 
	 * @param target the target of the attacker
	 * @param force the power of the push
	 * @param direction the direction the push is going in (in radians)
	 * @param range the range the object can push at
	 */
	public Push(GameObject target, MovementAttributes movement, float force, float direction, float range)
	{
		super(3);
		this.target = target;
		this.force = force;
		this.direction = direction;
		this.range = range;
		this.movement = movement;
	}//END Attack
	
	/* Act */
	@Override
	public void act(GameObject self)
	{
		if(
				(self.get_positionX() >= this.target.get_positionX() - this.range) && (self.get_positionX() <= this.target.get_positionX() + this.range)
				&&
				(self.get_positionY() >= this.target.get_positionY() - this.range) && (self.get_positionY() <= this.target.get_positionY() + this.range)
			)
		{
			//TODO Different things for different things.
			
			target.impact(this.force, this.direction);
			
			this.terminate();
		}//fi
		else
		{
			double direction = Math.atan2(this.target.get_positionY()  - self.get_positionY(), this.target.get_positionX()  - self.get_positionX());
			float xCompMax = (float)(movement.speed * Math.cos(direction));
			float yCompMax = (float)(movement.speed * Math.sin(direction));
			float xComp = (float)(movement.acceleation * Math.cos(direction));
			float yComp = (float)(movement.acceleation * Math.sin(direction));
			
			Vector2 v = self.get_velocity();
			
			if(xCompMax > 0)
			{
				if(v.x + xComp < xCompMax)
				{
					v.x += xComp;
				}//fi
				else
				{
					v.x = xCompMax;
				}//esle
			}//fi
			else
			{
				if(v.x + xComp > xCompMax)
				{
					v.x += xComp;
				}//fi
				else
				{
					v.x = xCompMax;
				}//esle
			}//esle
			
			if(yCompMax > 0)
			{
				if(v.y + yComp < yCompMax)
				{
					v.y += yComp;
				}//fi
				else
				{
					v.y = yCompMax;
				}//esle
			}//fi
			else
			{
				if(v.y + yComp > yCompMax)
				{
					v.y += yComp;
				}//fi
				else
				{
					v.y = yCompMax;
				}//esle
			}//esle
			
			self.set_velocity(v);
		}//esle
	}//END act
}//END class Attack

//EOF
