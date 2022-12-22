package com.mygdx.game;

public class NPC
{
	//Location information, used when NPC's entity in the game world is loaded / unloaded
	//Can also be used to update NPC when it is involved in actions outside of players view
	public Planet planet;
	public float x;
	public float y;
	
	public NPC()
	{
		planet = null;
		x = 0;
		y = 0;
	}

}
