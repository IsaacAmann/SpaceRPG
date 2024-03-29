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
			//inventory = new ArrayList<Item>();
			inventory = new Item[7][13];
		}
		
		public boolean addInventoryItem(Item item)
		{
			//Attempt to place the item in the inventory array, return false if it was not added
			boolean output = false;
			
			//If item is stackable, attempt to stack it first with existing items
			if(item.isStackable)
			{
				for(int i = 0; i < inventory.length && output == false; i++)
				{
					for(int j = 0; j < inventory[i].length; j++)
					{
						if(inventory[i][j] != null)
						{
							//Check if itemID matches
							if(item.itemID == inventory[i][j].itemID)
							{
								//Check if the slot can hold the item 
								if(inventory[i][j].stack + item.stack <= inventory[i][j].maxStack)
								{
									//add the stack of the item to the slot
									inventory[i][j].stack += item.stack;
									output = true;
									break;
								}
							}	
						}
					}
				}
			}
			//Find and place in any empty slot
			for(int i = 0; i < inventory.length && output == false; i++)
			{
				for(int j = 0; j < inventory[i].length; j++)
				{
					if(inventory[i][j] == null)
					{
						inventory[i][j] = item;
						output = true;
						break;
					}
				}
			}
			return output;
		}
		//currency amount in player's bank
		public double credits = 0;
		
		public Planet planet = null;
		public float x = 0;
		public float y = 0;
		public int currentItems = 0;
		
		public Item inventory[][];
	}
	
	
}
