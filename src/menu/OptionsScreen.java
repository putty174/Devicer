package menu;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OptionsScreen implements BaseState{
	Texture t = new Texture(Gdx.files.internal("data/optionScreen.png"));
	Button button1 = new Button("hi", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, this);
	Button button2 = new Button("hi", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3, this);
	StateManager state;
	static OptionsContainer opCon = new OptionsContainer();
	
	public OptionsScreen(StateManager state){
		this.state = state;
		create();		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(t, 0, 0);
		if(button1.render(batch)){
			opCon.optionsBool.put("hai", !opCon.optionsBool.get("hai"));
			try {
				opCon.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(opCon.optionsBool.get("hai"));
		}
		if(button2.render(batch)){
			state.currentState = state.posStates.get(0);
		}
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
