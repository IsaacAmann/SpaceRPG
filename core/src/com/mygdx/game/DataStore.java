package com.mygdx.game;
import java.util.HashMap;
import java.util.ArrayList;

//store world state, player state, persistent NPC's, map information
//should conatain all data that will be saved / loaded when saving is implemented
public class DataStore
{
	public HashMap<Integer, NPC> NPCMap;
	
	public HashMap<Integer, Planet> planetMap;
	
	
	
	public DataStore()
	{
		NPCMap = new HashMap<Integer, NPC>();
		planetMap = new HashMap<Integer, Planet>();
	}
	
	//Player data store
	public static class playerData
	{
		//currency amount in player's bank
		public double credits = 0;
		
		public Planet planet = null;
		public float x = 0;
		public float y = 0;
		
		public ArrayList<Item> inventory = new ArrayList<Item>();
	}
	
	
}
