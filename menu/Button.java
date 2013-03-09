package collisionalpha.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
	
	Texture t = new Texture(Gdx.files.internal("data/button.png"));
	Rectangle container;
	String text;
	float x, y;
	BaseState screen;
	
	public Button(String text, float x, float y, BaseState screen){
		this.text = text;
		this.x = x - (t.getWidth() / 2);
		this.y = y;
		container = new Rectangle(x - t.getWidth() / 2, (Gdx.graphics.getHeight()-y) - t.getHeight(), t.getWidth(), t.getHeight());
		this.screen = screen;
	}
	
	public boolean render(SpriteBatch batch){
		if(Gdx.input.justTouched()){
			if(container.contains(Gdx.input.getX(), Gdx.input.getY())){
				System.out.println("DONE");
				return true;
			}
		}
		batch.draw(t, x, y);
		return false;
	}
	
	public void dispose(){
		t.dispose();
	}
	
}
