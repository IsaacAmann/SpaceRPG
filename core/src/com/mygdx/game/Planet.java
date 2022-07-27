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
			Sector tempSector = new Sector();
			sectors.add(tempSector);
		}
	}
	
	public void update()
	{
		
	}
	
	public void draw(SpriteBatch batch)
	{
		
	}
}
