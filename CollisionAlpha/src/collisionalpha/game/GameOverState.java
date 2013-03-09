package collisionalpha.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import collisionalpha.game.controls.Controller;

public class GameOverState {
	
	Controller controller = new Controller();
	Texture gameOverImage;
	
	public GameOverState(Texture t){
		gameOverImage = t;
	}
	
	public boolean update(){
		if(Gdx.input.justTouched()){
			return false;
		}
		return true;
	}
	
	public void render(SpriteBatch sprites){
		sprites.draw(this.gameOverImage, 0, 0);
	}
}
