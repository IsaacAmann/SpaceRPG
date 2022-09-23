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
	private int sectorsLoaded;
	
	//Reference to the loaded edge sectors, used to monitor when player is approaching the edge of what portion of the world is loaded 
	private Sector leftEdgeSector;
	private Sector rightEdgeSector;
	private int rightEdgeSectorIndex;
	private int leftEdgeSectorIndex;
	
	public Planet(int numberSectors)
	{
		sectors = new CircularArrayList<Sector>();
		this.numberSectors = numberSectors;
		sectorsLoaded = 0;
		
		//Fill sectors 
		for(int i = 0; i < numberSectors; i++)
		{
			Sector tempSector = new Sector(i*SECTOR_LENGTH, 0);
			tempSector.sectorID = i;
			sectors.add(tempSector);
		}
		//load first sector when creating planet object
		sectors.get(0).load(0);
		rightEdgeSector = sectors.get(0);
		leftEdgeSector = sectors.get(0);
		rightEdgeSectorIndex = 0;
		leftEdgeSectorIndex = 0;
	}
	
	public void update()
	{
		//monitor player position and load sectors 
		if(PlanetScreen.player.getBodyX() > rightEdgeSector.x)
		{
			float rightEdgeX = rightEdgeSector.x;
			rightEdgeSector = sectors.get(rightEdgeSectorIndex + 1);
			rightEdgeSectorIndex += 1;
			rightEdgeSector.load(rightEdgeX + SECTOR_LENGTH);
			System.out.println("Right Sector loaded, index: " + rightEdgeSectorIndex + " Sector ID: " + rightEdgeSector.sectorID);
		}
		
		if(PlanetScreen.player.getBodyX() < leftEdgeSector.x + SECTOR_LENGTH)
		{
			float leftEdgeX = leftEdgeSector.x;
			leftEdgeSector = sectors.get(leftEdgeSectorIndex - 1);
			leftEdgeSectorIndex -= 1;
			leftEdgeSector.load(leftEdgeX - SECTOR_LENGTH);
			System.out.println("Left Sector loaded, index: " + leftEdgeSectorIndex + " Sector ID: " + leftEdgeSector.sectorID);
		} 
		
		
		//Check the current edge sectors and see if they are out of range and need to be unloaded
		if(PlanetScreen.player.getBodyX() < rightEdgeSector.x - (2 * SECTOR_LENGTH))
		{
			float rightEdgeX = rightEdgeSector.x;
			int oldSectorID = rightEdgeSector.sectorID;
			rightEdgeSector.unload();
			rightEdgeSector = sectors.get(rightEdgeSectorIndex - 1);
			rightEdgeSectorIndex -= 1;
			System.out.println("Sector unloaded at: "+rightEdgeX+ " right edge sector at: " + rightEdgeSector.x + " Sector ID: " + oldSectorID);
			
		}
		
		if(PlanetScreen.player.getBodyX() > leftEdgeSector.x + (SECTOR_LENGTH*2))
		{
			float leftEdgeX = leftEdgeSector.x;
			int oldSectorID = leftEdgeSector.sectorID;
			leftEdgeSector.unload();
			leftEdgeSector = sectors.get(leftEdgeSectorIndex + 1);
			leftEdgeSectorIndex += 1;
			System.out.println("Sector unloaded at: " + leftEdgeX+" left edge sector at: " + leftEdgeSector.x + " Sector ID: " + oldSectorID);
		}
		
		/*
		 * //Load all sectors for debugging
		for(int i = 0; i < numberSectors; i++)
		{
			sectors.get(i).load();
		}
		*/
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
