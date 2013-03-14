package game;

import game.controls.Controller;
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
	
	/* GraphicsManager */
	private GraphicsManager g;
	
	/* ApplicationListener */
	@Override
	public void create()
	{
		g = new GraphicsManager();
		testobj = new Player(0, 90, 90, 1, 10000, 32, 20, 0, -15, true, 60, true, 32, 50, g.ID(0), 32, 50);
		gameIsOver = false;
		testroom = new Room(g.ID(100), testobj);
		Device box = new Device(400,400,g.ID(1), testroom, g.ID(2));
		worldbox = box;
		gos = new GameOverState(g.ID(100));
		testroom.add_object(box);
		
		testroom.add_object(testobj);
		
		this.controller.add_controllable(testroom);
		
		Gdx.input.setInputProcessor(this.controller);
	}//END create

	@Override
	public void dispose()
	{
		g.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	private GameObject addMonster()
	{
		boolean top = generator.nextBoolean();
		boolean side = generator.nextBoolean();
		float xSpawn = (top ? (side ? 0 : Gdx.graphics.getWidth()) : generator.nextInt(Gdx.graphics.getWidth()));
		float ySpawn = (top ? generator.nextInt(Gdx.graphics.getHeight()) : (side ? 0 : Gdx.graphics.getHeight()));
		GameObject enemy = new Enemy(worldbox,3, xSpawn, ySpawn, 1f, 300, 32, 32, 0, 0, 
				true, 60, true, 32, 32, g.ID(3), 32, 32);
		return enemy;
	}
	@Override
	public void render() {
		
		if(gameIsOver){
			gameIsOver = gos.update();
			
			SpriteBatch spritebatch = new SpriteBatch();
			spritebatch.begin();
			gos.render(spritebatch);
			spritebatch.end();
			spritebatch.dispose();
			if(!gameIsOver){
				create();
			}
			else{
				return;
			}
			
		}
		/* Get DT */
		float dt = Gdx.graphics.getDeltaTime();
		time += dt;
		System.out.println("Total Time: " + time);
		if (time > wait && testroom.monsterCount < 10)
		{
			System.out.println("New Enemy Spawned");
			wait = time + rate;
			testroom.add_object(addMonster());
		}
		
		/* Update */
		gameIsOver = testroom.update(dt);
		
		/* Render */
		//Clear
		Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
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