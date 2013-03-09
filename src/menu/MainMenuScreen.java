package menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements BaseState {
	
	Texture bgArt;
	Button button1;
	StateManager state;

	public MainMenuScreen(StateManager state){
		create();
		this.state = state;
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		bgArt = new Texture(Gdx.files.internal("data/backgrounds/testBG.png"));		
		button1 = new Button("hi", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, this);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		bgArt.dispose();
		button1.dispose();
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(bgArt, 0, 0);
		if(button1.render(batch)){
			state.currentState = state.posStates.get(1);
		}
	}
}
