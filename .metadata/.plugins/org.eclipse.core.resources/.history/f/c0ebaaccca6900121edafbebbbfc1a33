package uci.vgdc.team4;


import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Game implements ApplicationListener{

	
	Texture tex;
	Texture tex2;
	Sound effect;
	Player p;
	
	GameTimer timer;

	ArrayList<Enemy> Enemies = new ArrayList<Enemy>();
	ArrayList<XP> XP = new ArrayList<XP>();
	
	@Override
	public void create() {		
		// TODO Auto-generated method stub
		this.timer = new GameTimer(120);
		timer.start();
		
		tex = new Texture(Gdx.files.internal("trunk/DesktopProject/data/back.jpg"));
		effect = Gdx.audio.newSound(Gdx.files.internal("trunk/DesktopProject/data/sound.wav"));
		p = new Player(1, 100, new Vector2(0,0), new Vector2(0,0), new Sprite(new Texture(Gdx.files.internal("trunk/DesktopProject/data/bama.jpg"))), 50, 50);
		Controller controller = new Controller(Gdx.graphics.getHeight());
		controller.addControllable(p);
		Gdx.input.setInputProcessor(controller);
		
		Gdx.app.log("create()", "I CAN DRAW DOG");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		tex.dispose();
		effect.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		/* Get DT */
		float dt = Gdx.graphics.getDeltaTime();
		
		/* Update */
		timer.update(dt);

		/* Sort */
		Collections.sort(Enemies, new EntityComparator());
		Collections.sort(XP, new EntityComparator());
		
		/* Draw */
		
		//Comment out anything that has to do with p if you need to run.
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		SpriteBatch sprites = new SpriteBatch();
		sprites.begin();
		sprites.draw(tex, 0, 0);
		p.render(sprites);
		sprites.end();
		
		p.update(dt);
		
		
		if(Gdx.input.justTouched()){
			effect.play(.7f);
		}
		
		
		System.out.println(timer.time());
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
