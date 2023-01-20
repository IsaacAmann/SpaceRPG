package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.lang.Math;

public class Sector
{
	private static final int TERRAIN_Y_LEVEL = 6;
	public static final int SECTOR_LENGTH = 40;

	public float x;
	public float y; 
	public boolean isLoaded;
	private TerrainPiece terrain;
	public int sectorID;
	public ArrayList<Entity> entityList;
	
	public Sector(float x, float y)
	{
		this.x = x;
		this.y = y;
		isLoaded = false;
		entityList = new ArrayList<Entity>();
	}	
	
	//Create physics objects for stored entities and create sprites
	public void load(float newX)
	{
		float xDifference = this.x - newX;
		this.x = newX;
		//add difference between new x and old x to x positions of entities being loaded
		Entity currentEntity;
		for(int i = 0; i < this.entityList.size(); i++)
		{
			currentEntity = entityList.get(i);
			//currentEntity.y = -3;
			if(currentEntity.loaded == false)
				currentEntity.load(xDifference);
		}	
		terrain = new TerrainPiece(game.assets.get("groundStone.png", Texture.class), x, y, SECTOR_LENGTH, TERRAIN_Y_LEVEL);
		//create physics object
		isLoaded = true;
	}
	
	//Remove child entities from game world and save positions
	public void unload()
	{
		PlanetScreen.world.destroyBody(terrain.body);
		for(int i = 0; i < this.entityList.size(); i++)
			entityList.get(i).unload();
		terrain = null;
		isLoaded = false;
	}
	
	public void draw(SpriteBatch batch)
	{
		if(isLoaded)
			terrain.draw(batch);
	}
}
