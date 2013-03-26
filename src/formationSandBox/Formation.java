package formationSandBox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import game.draw.GraphicsManager;
import game.objects.Device;
import game.objects.Enemy;
import game.room.Room;

public class Formation {
	
	public enum Type{
		LINE, WEDGE, COLUMN, STAR, SCATTER, RANDOM;
	}
	
	GraphicsManager g = new GraphicsManager();
	Type t;
	Texture tex;
	int num;
	Enemy[] enemies;
	boolean top, side;
	float spawnDT;
	Room r;
	
	public Formation(Texture tex, Formation.Type t, int num, boolean top, boolean side, float dt, Room r){
		this.t = Type.LINE;
		this.tex = tex;
		this.num = num;		
		this.top = top;
		this.side = side;
		this.spawnDT = dt;
		enemies = new Enemy[num];
		setFormation();
	}
	
	private void setFormation(){
		if(t == Type.LINE){
			setLine();
		}
		else if (t == Type.COLUMN){
			
		}
	}
	
	private void setLine(){
		for(int i = 0; i < enemies.length; i ++){
			enemies[i] = new Enemy(new Device(400,400,g.ID(1), r, g.ID(2)), 3,
					i * Gdx.graphics.getWidth() / (enemies.length - 1),
					50, .5f, .5f, 50, 50, 0, 0, true, 
					120, true, 70, 70, tex, 150, 150);
		}
	}
	
	private void setColumn(){
		for(int i = 0; i < enemies.length; i ++){
			
		}
	}
	
	
}
