package collisionalpha.game;

import java.io.File;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;


public class GameMain
{
	public static final int CONFIG_HEIGHT = 600;
	public static final int CONFIG_WIDTH = 960;
	
	public static void main(String[] args)
	{
		System.out.println(System.getProperty("user.dir"));
		File f = new File(System.getProperty("user.dir") + "/data");
		for(Object o : f.list()){
			System.out.println(o);
		}
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.height = CONFIG_HEIGHT;
		config.width = CONFIG_WIDTH;
		config.useGL20 = true;
		new LwjglApplication(new Game(), config);
	}//END main
}
