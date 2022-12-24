package com.mygdx.game;
import java.util.HashMap;
import java.util.ArrayList;

//store world state, player state, persistent NPC's, map information
//should conatain all data that will be saved / loaded when saving is implemented
public class DataStore
{
	public HashMap<Integer, NPC> NPCMap;
	
	public HashMap<Integer, Planet> planetMap;
	
	public PlayerData playerData;
	
	public DataStore()
	{
		NPCMap = new HashMap<Integer, NPC>();
		planetMap = new HashMap<Integer, Planet>();
		playerData = new PlayerData();
	}
	
	//Player data store
	public class PlayerData
	{
		public PlayerData()
		{
			inventory = new ArrayList<Item>();	
		}
		
		public void addInventoryItem(Item item)
		{
			currentItems++;
			inventory.add(item);	
		}
		//currency amount in player's bank
		public double credits = 0;
		
		public Planet planet = null;
		public float x = 0;
		public float y = 0;
		public int currentItems = 0;
		
		public ArrayList<Item> inventory;
	}
	
	
}
