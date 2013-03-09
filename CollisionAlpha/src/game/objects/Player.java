package game.objects;

import game.objects.actions.Attack;
import game.objects.actions.Goto;
import game.objects.actions.Push;
import game.objects.attributes.AttackAttributes;

import java.util.ArrayList;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends AnimatedObject
{
	/* Stats */
	private AttackAttributes attack = new AttackAttributes();
	
	/* Animation */
	private static final int ANIMATION_IDLE = 0;
	private static final int ANIMATION_MOVING = 1;
	
	//Facing Angle
	private float facing_angle = 0;
	private int lastFacingIndex = 0;
	private float heading_X, heading_Y;
	
	/* Touch */
	private GameObject target = null;
	
	/* Constructor */
	/**
	 * Constructs a player object for a game.
	 * 
	 * @param objectID the identifier for the object
	 * @param posX the x position of the object in space
	 * @param posY the y position of the object in space
	 * @param mass how massive the object is
	 * @param friction the magnitude of the friction force applied to the object
	 * @param hitWidth the width of the hitbox
	 * @param hitHeight the height of the hitbox
	 * @param hitX the x offset of the hitbox
	 * @param hitY the y offset of the hitbox
	 * @param colliders the things this object will collide and interact with
	 * @param isSolid whether or not the object can be moved through
	 * @param isTouchable if the object is touchable or not
	 * @param drawWidth the width that the object is drawn at
	 * @param drawWidth the width that the object is drawn at
	 * @param drawHeight the height that the object is drawn at
	 * @param sprites the set of sprites to be used in drawing the object
	 * @param srcWidth the width of a sprite
	 * @param srcHeight the height of a sprite
	 */
	public Player(int objectID, float posX, float posY, float mass, float friction, float hitWidth, float hitHeight, float hitX, float hitY, boolean isSolid, float touchRadius, boolean isTouchable, float drawWidth, float drawHeight,
			Texture sprites, int srcWidth, int srcHeight)
	{
		super(objectID, posX, posY, mass, friction, hitWidth, hitHeight, hitX, hitY, isSolid, touchRadius, isTouchable, drawWidth, drawHeight, sprites, srcWidth, srcHeight);
		
		this.screenBound = true;
		
		/* Stats */
		this.movement.speed = 300;
		this.movement.acceleation = 300;
		this.attack.damage = 1;
		this.attack.power = 500;
		this.attack.range = 60;
		
		/* Animations */
		//Idle
		for(int i = 0; i < 8; i++)
		{
			this.add_animation(0, i, 4, 5, true);
		}//rof
		
		//Moving
		for(int i = 0; i < 8; i++)
		{
			this.add_animation(0, i, 4, 10, true);
		}//rof
		
		this.momentum_collision(new Vector2(200,200));
		
		this.directionBasedAnimation(ANIMATION_IDLE);
	}//END Player
	
	/* Input */
	public void input_touchDown(int x, int y, int pointer, int button, GameObject target)
	{
		this.target = target;
		
		if(target == null)
		{
			if(button == 0)
			{
				this.heading_X = x;
				this.heading_Y = y;
				this.action_queue.clear();
				this.action_queue.add_action(new Goto(x, y, this.movement));
				if(this.animation_state != ANIMATION_MOVING)
				{
					this.directionBasedAnimation(ANIMATION_MOVING);
				}//fi
			}//fi
		}//fi
	}//END input_touch
	
	public void input_touchUp(int x, int y, int pointer, int button)
	{
		if(this.target != null && this.target != this)
		{
			float direction = (float)(Math.atan2(y - this.target.get_positionY(), x - this.target.get_positionX()));
			this.heading_X = this.target.get_positionX();
			this.heading_Y = this.target.get_positionY();
			System.out.println(direction);
			this.action_queue.clear();
			switch (this.target.getID())
			{
				case 1: //Is the device
					this.action_queue.add_action(new Push(this.target, this.movement, 500, direction, this.attack.range));
					break;
				default: //Enemy
					this.action_queue.add_action(new Attack(this.target,this.movement, this.attack));
					break;
			}
			if(this.animation_state != ANIMATION_MOVING)
			{
				this.directionBasedAnimation(ANIMATION_MOVING);
			}//fi
		}//fi
	}
	
	/* Collision */
	@Override
	public void behavior_collision(GameObject collider)
	{
		switch (collider.getID())
		{
			case 1: //Collision with device
				double direction = this.get_velocityDirection() * Math.PI / 180;
				collider.impact(this.get_vMagnitude() * this.mass / 10, direction);
				break;
			case 2: //Collision with exp
				collider.terminate();
				break;
		}//hctiws
	}//END behavior_collision
	
	/* Animation */
	private int get_facingAngleIndex()
	{
		return (int)((this.facing_angle + 22.5)%360)/(45);
	}//END get_facingAngleIndex
	
	/**
	 * Sets the animation based on the direction the object is facing.
	 * 
	 * @param animationID The ID of the set of animations.
	 */
	public void directionBasedAnimation(int animationID)
	{
		this.animation_state = animationID;
		this.set_animation(animationID * 8 + this.get_facingAngleIndex());
	}//END directionBasedAnimation
	
	/* Update */
	@Override
	public void update(float dt, ArrayList<GameObject> objects)
	{
		this.facing_angle = 180 + (float)(Math.atan2(this.get_positionY() - this.heading_Y,
				this.get_positionX() - this.heading_X) * 180 / Math.PI);
		int newFacing = this.get_facingAngleIndex();
		boolean change;
		if (newFacing != this.lastFacingIndex)
		{
			this.lastFacingIndex = newFacing;
			change = true;
		}//fi
		else
		{
			change = false;
		}//esle

		
		if(this.action_queue.get_actionID() == 0 && this.animation_state == ANIMATION_MOVING)
		{
			this.directionBasedAnimation(ANIMATION_IDLE);
		}//fi
		else if(this.action_queue.get_actionID() == 1 && change)
		{
			this.directionBasedAnimation(ANIMATION_MOVING);
		}//fi esle
		
		super.update(dt, objects);
	}//END update
	
	@Override
	protected void update_animationState()
	{
		
	}//END update_animationState
}//END class Player

//EOF
