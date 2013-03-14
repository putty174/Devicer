package graphics;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GraphicsManager {
	
	private HashMap<Integer, Texture> imgs = new HashMap<Integer, Texture>();
	
	
	//Constructor takes no arguments and puts all FileHandle objects into a HashMap to a corresponding ID.
	//IDs should match those of the character, device, and monsters.
	//Backgrounds start at 100.
	public GraphicsManager(){
		imgs.put(0, new Texture(Gdx.files.internal("data/ness_walk.png")));
		imgs.put(1, new Texture(Gdx.files.internal("data/greenball.png")));
		imgs.put(2, new Texture(Gdx.files.internal("data/FritoLaySmall.png")));
		imgs.put(3, new Texture(Gdx.files.internal("data/Sprite-example.png")));	
		imgs.put(100, new Texture(Gdx.files.internal("data/backgrounds/grass.png")));
	}
	
	//Input object ID to get Texture for it.
	public Texture ID(int ID){
		return imgs.get(ID);
	}
	
	public void dispose(){
		for(Texture t : imgs.values()){
			t.dispose();
		}
	}
	
}
