package villa.loaders;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import villa.game.room.Area;
import villa.game.room.Room;

public class TextRoomLoader
{
	public static void main(String[] args)
	{
		load("data/rooms/test/testroom_01.vs");
	}//END main
	
	public static Room load(String roompath)
	{
		Room_Builder builder = new Room_Builder();
		load(builder, roompath);
		return builder.build();
	}//END load
	
	private static void load(Room_Builder builder, String roompath)
	{
		ArrayList<String> commands = null;
		try
		{
			//Extract commands
			Scanner in = new Scanner(new File(roompath));
			commands = getCommands(in);
			in.close();
			
			//Run commands
			runCommands(builder, commands);
		}//yrt
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}//hctac
	}//END load
	
	/* Commands */	
	private static class Room_Builder
	{
		public Map_Holder map;
		public HashMap<Character, Integer> tileIDHash = new HashMap<Character, Integer>();
		
		public Room build()
		{
			//Build Area
			int[] areaIDs = new int[this.map.area.length];
			for(int i = 0; i < areaIDs.length; i++)
			{
				Integer tileID = this.tileIDHash.get(this.map.area[i]);
				if(tileID == null)
				{
					tileID = 0;
				}//fi
				areaIDs[i] = tileID;
			}//rof
			Area area = new Area(areaIDs, this.map.width, this.map.height);
			
			//Construct Room
			Room room = new Room(area);
			
			//Return Room
			return room;
		}//END build
	}//END Room_Builder
	
	private static ArrayList<String> getCommands(Scanner in)
	{
		ArrayList<String> commands = new ArrayList<String>();
		
		in.useDelimiter(";");
		while(in.hasNext())
		{
			String command = in.next();
			command = command.replaceAll("\\s", "");
			commands.add(command);
		}//elihw
		
		return commands;
	}//END getCommands
	
	private static void runCommands(Room_Builder builder, ArrayList<String> commands)
	{		
		Iterator<String> iter = commands.iterator();
		while(iter.hasNext())
		{
			String command = iter.next();
			String[] tokens = command.split("<");
			command(builder, tokens);
		}//elihw
	}//END initCommands
	
	private static void command(Room_Builder builder, String[] command_tokens)
	{
		if(command_tokens[0].equals("createmap"))
		{
			command_createmap(builder, command_tokens[1]);
		}//fi
		else if (command_tokens[0].equals("tile_id"))
		{
			command_tileID(builder, command_tokens[1]);
		}//fi esle
	}//END command
	
	//Create Map
	private static class Map_Holder
	{
		public int width, height;
		public char[] area;
		
		public Map_Holder(int width, int height, char[] area)
		{
			this.width = width;
			this.height = height;
			this.area = area;
		}//END Map_Holder
	}//END class Map_Holder
	
	private static void command_createmap(Room_Builder builder, String param)
	{
		//Tokenize
		String[] tokens = param.split(",");
		//Extract
		int width = Integer.parseInt(tokens[0]);
		int height = Integer.parseInt((tokens[1]));
		char[] charArray = new char[width * height];
		for(int j = 0; j < height; j++)
		{
			for(int i = 0; i < width; i++)
			{
				charArray[i + j * width] = tokens[2].charAt(i + j * width);
				System.out.print(charArray[i + j * width]);
			}//rof
			System.out.println();
		}//rof
		Map_Holder map = new Map_Holder(width, height, charArray);
		
		builder.map = map;
	}//END command_createmap
	
	//Tile	
	private static void command_tileID(Room_Builder builder, String param)
	{
		String[] tokens = param.split(",");
		builder.tileIDHash.put(tokens[0].charAt(0), Integer.parseInt(tokens[1]));
		System.out.println(tokens[0] + " = " + tokens[1]);
	}//END command_tileID
}//END class TextRoomLoader

//EOF