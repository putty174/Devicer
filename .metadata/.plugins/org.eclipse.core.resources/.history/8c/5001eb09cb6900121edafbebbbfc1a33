package uci.vgdc.team4;

import java.awt.Point;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor{
	public int hp;
	public float speed;
	public Vector2 position;
	public Vector2 velocity;
	public Sprite sheet;
	
	public Entity(int hp, float speed, Vector2 position, Vector2 velocity, Sprite sheet, int sWidth, int sHeight){
		this.hp = hp;
		this.speed = speed;
		this.position = position;
		this.velocity = velocity;
		this.sheet = sheet;
	}
	
	public abstract void update(float dt);
	public abstract void render(SpriteBatch batch);
	public abstract void create();
	public abstract void dispose();
}
