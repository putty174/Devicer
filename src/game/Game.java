package game;

import game.controls.Controller;
import game.object.spawner.Spawner;
import game.objects.Device;
import game.objects.Enemy;
import game.objects.GameObject;
import game.objects.Player;
import game.room.Room;
import graphics.GraphicsManager;

import java.util.Random;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements ApplicationListener
{
	float i = 0, j = 16;
	//TODO Testing
	
	Spawner spawner;
	
	Player testobj;
	Room testroom;
	int[] testcolliders = {0,1,2};
	float time = 0;
	GameObject worldbox;
	float rate = 2;
	float wait = rate;
	Random generator = new Random();
	boolean gameIsOver;
	GameOverState gos;
	Texture t;
	/* Controls */
	private Controller controller = new Controller();
	
	private GraphicsManager g;
	
	/* ApplicationListener */
	@Override
	public void create()
	{
		g = new GraphicsManager();
		testobj = new Player(0, 90, 90, 1, 10000, 50, 20, 0, -30, true, 60, true, 75, 125, g.ID(0), 300, 500);
		gameIsOver = false;
		testroom = new Room(g.ID(100), testobj);
		Device box = new Device(400,400,g.ID(1), testroom, g.ID(2));
		worldbox = box;
		gos = new GameOverState(g.ID(100));
		testroom.add_object(box);
		spawner = new Spawner(testroom, box, g.ID(3), 5, 0.1f, 0.1f);
		
		testroom.add_object(testobj);
		
		this.controller.add_controllable(testroom);
		
		Gdx.input.setInputProcessor(this.controller);
	}//END create

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		g.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render() {
		
		if(this.worldbox.isGone()){
			gameIsOver = gos.update();
			
			SpriteBatch spritebatch = new SpriteBatch();
			spritebatch.begin();
			gos.render(spritebatch);
			spritebatch.end();
			spritebatch.dispose();
			if(!gameIsOver){
				create();
				System.out.println("gets here");
			}
			else{
				return;
			}
			
		}
		
		/* Get DT */
		float dt = Gdx.graphics.getDeltaTime();
		time += dt;
		System.out.println("Total Time: " + time);
		
		/* Update */
		gameIsOver = testroom.update(dt);
		spawner.update(dt);
		
		/* Render */
		//Draw
		SpriteBatch spritebatch = new SpriteBatch();
		spritebatch.begin();
		testroom.render(spritebatch);
		spritebatch.end();
		spritebatch.dispose();
	}//END render

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}//END class Game

//EOF