package formationSandBox;

import game.draw.GraphicsManager;
import game.objects.Player;
import game.room.Room;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FormationTest implements ApplicationListener{

	GraphicsManager g;
	Room room;
	FormationSpawner form;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		g = new GraphicsManager();
		room = new Room(new Texture(Gdx.files.internal("data/optionScreen.png")), 
				new Player(0, 90, 90, 1, 10000, 50, 20, 0, -30, true, 60, true, 75, 125, g.ID(0), 300, 500));
		form = new FormationSpawner(room);
		form.addFormation(new Formation(g.ID(3), Formation.Type.LINE, 5, true, true, 5, room));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		g.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		SpriteBatch batch = new SpriteBatch();
		form.update();
		room.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		room.render(batch);
		batch.end();
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
