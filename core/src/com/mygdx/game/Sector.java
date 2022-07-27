package com.mygdx.game;

public class Sector
{
	private static final int TERRAIN_Y_LEVEL = 6;
	public float x;
	public float y; 
	
	public Sector(float x, float y)
	{
		this.x = x;
		this.y = y;
	}	
	
	//Create physics objects for stored entities and create sprites
	public void load()
	{
		//update physics world cooridinates
		
		//create physics object
	}
	
	//Remove child entities from game world and save positions
	public void unload()
	{
		
	}
}
