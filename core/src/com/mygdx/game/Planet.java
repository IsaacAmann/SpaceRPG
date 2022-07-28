package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Planet
{
	private static final int SECTOR_LENGTH = 40;
	public static final float PIXELS_TO_METERS = 100f;
	
	private int numberSectors;
	public CircularArrayList<Sector> sectors;
	
	public Planet(int numberSectors)
	{
		sectors = new CircularArrayList<Sector>();
		this.numberSectors = numberSectors;
		
		//Fill sectors ArrayList
		for(int i = 0; i < numberSectors; i++)
		{
			Sector tempSector = new Sector(i*SECTOR_LENGTH, 0);
			sectors.add(tempSector);
		}
	}
	
	public void update()
	{
		//monitor player position and load sectors 
		
		//Load all sectors for debugging
		for(int i = 0; i < numberSectors; i++)
		{
			sectors.get(i).load();
		}
	}
	
	public void draw(SpriteBatch batch)
	{
		//Render loaded sectors
		for(int i = 0; i < numberSectors; i++)
		{
			if(sectors.get(i).isLoaded)
			{
				sectors.get(i).draw(batch);
			}
		}
	}
}
