package game.objects;

import com.badlogic.gdx.graphics.Texture;

public class XP extends GameObject
{

	public XP(float posX, float posY, Texture sprites, float speed, float direction)
	{
		super(
				2, //ID
				posX, posY, //Position
				1, //Mass
				400, //Friction
				32, 32, //Hit Height and Width
				0, 0, //Hit x and y offset
				false, //Solid or not
				0, //Touch Radius
				false, //Touchable
				32, 32, //Draw width and height
				sprites, //Spritesheet
				64, 64 //srcwidth and height
				);
		
		double dir = direction * Math.PI / 180;
		float xComp = (float)(speed * Math.cos(dir));
		float yComp = (float)(speed * Math.sin(dir));
		this.set_velocity(xComp, yComp);
	}//END XP
}//END class XP
