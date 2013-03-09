package menu;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StateManager implements ApplicationListener{

	BaseState currentState;
	ArrayList<BaseState> posStates;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		posStates = new ArrayList<BaseState>();
		posStates.add(new MainMenuScreen(this));
		posStates.add(new OptionsScreen(this));
		currentState = posStates.get(0);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		currentState.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
		currentState.render(batch);
		batch.end();
		batch.dispose();
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
