package villa.game.object;

import java.util.ArrayList;

import villa.game.room.ObjectsHolder;
import villa.math.VMath;

import com.badlogic.gdx.math.Vector2;

public class MobileObject extends GameObject
{
	private Vector2 velocity = new Vector2();
	
	/**
	 * Creates an object for the game.
	 * 
	 * @param x the x starting position
	 * @param y the y starting position
	 * @param hitWidth the width of the hitbox
	 * @param hitHeight the height of the hitbox
	 */
	public MobileObject(float x, float y, float hitWidth, float hitHeight)
	{
		super(x, y, hitWidth, hitHeight);
	}//END MobileObject
	
	/* Movement */
	//Jump
	/**
	 * Jumps to a position given the offset and checks if it collides too.
	 * 
	 * @param colliders the objects that this object could collide with
	 * @param jX the x jump
	 * @param jY the y jump
	 */
	private void jump(ArrayList<GameObject> colliders, float jX, float jY)
	{
		//Jump Y
		Vector2 nextpos = new Vector2(this.getPosX(), this.getPosY() + jY);
		if(!this.collide(colliders, nextpos)) //If has not collided into a solid object.
		{
			this.setPos(nextpos);
		}//fi
		
		//Jump X
		nextpos = new Vector2(this.getPosX() + jX, this.getPosY());
		if(!this.collide(colliders, nextpos)) //If has not collided into a solid object.
		{
			this.setPos(nextpos);
		}//fi
	}//END jump
	
	/**
	 * Jumps to a position given the offset and checks if it collides too.
	 * 
	 * @param colliders the objects that this object could collide with
	 * @param j the jump vector
	 */
	private void jump(ArrayList<GameObject> colliders, Vector2 j)
	{
		this.jump(colliders, j.x, j.y);
	}//END jump
	
	//Velocity
	public float get_speed()
	{
		return (float)VMath.magnitude(this.get_vX(), this.get_vY());
	}//END get_speed
	
	/**
	 * @return the x velocity of the object
	 */
	public float get_vX()
	{
		return this.velocity.x;
	}//END get_vX
	
	/**
	 * @return the y velocity of the object
	 */
	public float get_vY()
	{
		return this.velocity.y;
	}//END get_vY
	
	/**
	 * @return a copy of the velocity vector of the object
	 */
	public Vector2 get_velocity()
	{
		return this.velocity.cpy();
	}//END get_velocity
	
	/**
	 * Sets the velocity of the object.
	 * 
	 * @param vX the x velocity
	 * @param vY the y velocity
	 */
	public void set_velocity(float vX, float vY)
	{
		this.velocity.set(vX, vY);
	}//END set_velocity
	
	/**
	 * Sets the velocity of the object.
	 * 
	 * @param v the velocity vector
	 */
	public void set_velocity(Vector2 v)
	{
		this.velocity.set(v);
	}//END set_velocity
	
	/* Update */
	@Override
	public void step(float dt, ObjectsHolder objects)
	{
		this.jump(objects.get_objects(), this.velocity);
	}//END step
}//END MobileObject