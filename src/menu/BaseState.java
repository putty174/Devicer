package menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface BaseState {
	
	public void render(SpriteBatch batch);
	public void create();
	public void dispose();
	
}
