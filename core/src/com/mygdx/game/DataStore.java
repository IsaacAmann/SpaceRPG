package com.mygdx.game;
import java.util.HashMap;

//store world state, player state, persistent NPC's, map information
//should conatain all data that will be saved / loaded when saving is implemented
public class DataStore
{
	public HashMap<Integer, NPC> NPCMap;
	
	public DataStore()
	{
		NPCMap = new HashMap<Integer, NPC>();
	}
	
	//Player data store
	public static class playerData
	{
		//currency amount in player's bank
		public double credits = 0;
		
	}
	
	
	
}
