package formationSandBox;

import game.objects.Enemy;
import game.room.Room;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class FormationSpawner{
	
	Queue<Formation> f;
	Formation nextToSpawn;
	float timeElapsed;
	Room r;
	
	public FormationSpawner(Room r){
		f = new LinkedList<Formation>();
		this.r = r;
	}
	
	public void addFormation(Texture t, Formation.Type type, int amount, boolean top, boolean side, float dt){
		addFormation(new Formation(t, type, amount, top, side, dt, r));
	}
	
	public void addFormation(Formation formation){
		f.add(formation);
	}
	
	public boolean update(){
		if(f.isEmpty() && nextToSpawn == null){
			return false;
		}
		if(nextToSpawn == null){
			nextToSpawn = f.remove();
		}
		timeElapsed += Gdx.graphics.getDeltaTime();
		System.out.println(timeElapsed);
		if(timeElapsed >= nextToSpawn.spawnDT){
			spawn();
		}
		return true;
	}
	
	public void spawn(){
		for(Enemy e : nextToSpawn.enemies){
			r.add_object(e);
		}
		timeElapsed = 0;
		nextToSpawn = null;
	}
}
