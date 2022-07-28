package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sector
{
	private static final int TERRAIN_Y_LEVEL = 6;
	private static final int SECTOR_LENGTH = 40;

	public float x;
	public float y; 
	public boolean isLoaded;
	private TerrainPiece terrain;
	
	public Sector(float x, float y)
	{
		this.x = x;
		this.y = y;
		isLoaded = false;
	}	
	
	//Create physics objects for stored entities and create sprites
	public void load()
	{
		//update physics world cooridinates (loading when rolling over in the circular array should alter coordinates)
		if(terrain == null)
		{	
			terrain = new TerrainPiece(PlanetScreen.groundTexture, x, y, SECTOR_LENGTH, TERRAIN_Y_LEVEL);
		}
		//create physics object
		isLoaded = true;
	}
	
	//Remove child entities from game world and save positions
	public void unload()
	{
		terrain = null;
	}
	
	public void draw(SpriteBatch batch)
	{
		terrain.draw(batch);
	}
}
