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
	public CircularLinkedList<Sector> sectors;
	private int sectorsLoaded;
	
	//Reference to the loaded edge sectors, used to monitor when player is approaching the edge of what portion of the world is loaded 
	private Node<Sector> leftEdgeSector;
	private Node<Sector> rightEdgeSector;
	private int rightEdgeSectorIndex;
	private int leftEdgeSectorIndex;
	
	public Planet(int numberSectors)
	{
		sectors = new CircularLinkedList<Sector>();
		this.numberSectors = numberSectors;
		sectorsLoaded = 0;
		Sector[] tempSectorsArray = new Sector[numberSectors];
		//Fill sectors 
		for(int i = 0; i < numberSectors; i++)
		{
			Sector tempSector = new Sector(i*SECTOR_LENGTH, 0);
			tempSector.sectorID = i;
			tempSectorsArray[i] = tempSector;
			System.out.println("element: " + tempSectorsArray[i]);
		}
		//Create the linkedlist using the sector array
		sectors.createFromArray(tempSectorsArray);
		//load first sector when creating planet object
		System.out.println("head" +sectors.head);
		System.out.println(sectors.head.data);
		sectors.head.data.load(0);
		rightEdgeSector = sectors.head;
		leftEdgeSector = sectors.head;
	}
	
	public void update()
	{
		//monitor player position and load sectors 
		if(PlanetScreen.player.getBodyX() > rightEdgeSector.data.x)
		{
			float rightEdgeX = rightEdgeSector.data.x;
			System.out.println("Id before .next call :" + rightEdgeSector.data.sectorID);
			rightEdgeSector = rightEdgeSector.next;
			System.out.println("RightEdgeSectorAddress" + rightEdgeSector);
			rightEdgeSector.data.load(rightEdgeX + SECTOR_LENGTH);
			System.out.println("Right Sector loaded. Sector ID: " + rightEdgeSector.data.sectorID);
		}
		
		if(PlanetScreen.player.getBodyX() < leftEdgeSector.data.x + SECTOR_LENGTH)
		{
			float leftEdgeX = leftEdgeSector.data.x;
			leftEdgeSector = leftEdgeSector.previous;
			leftEdgeSector.data.load(leftEdgeX - SECTOR_LENGTH);
			System.out.println("Left Sector loaded, Sector ID: " + leftEdgeSector.data.sectorID);
		} 
		
		
		//Check the current edge sectors and see if they are out of range and need to be unloaded
		if(PlanetScreen.player.getBodyX() < rightEdgeSector.data.x - (2 * SECTOR_LENGTH))
		{
			float rightEdgeX = rightEdgeSector.data.x;
			int oldSectorID = rightEdgeSector.data.sectorID;
			rightEdgeSector.data.unload();
			rightEdgeSector = rightEdgeSector.previous;
			System.out.println("Sector unloaded at: "+rightEdgeX+ " right edge sector at: " + rightEdgeSector.data.x + " Sector ID: " + oldSectorID);
			
		}
		
		if(PlanetScreen.player.getBodyX() > leftEdgeSector.data.x + (SECTOR_LENGTH*2))
		{
			float leftEdgeX = leftEdgeSector.data.x;
			int oldSectorID = leftEdgeSector.data.sectorID;
			leftEdgeSector.data.unload();
			leftEdgeSector = leftEdgeSector.next;
			System.out.println("Sector unloaded at: " + leftEdgeX+" left edge sector at: " + leftEdgeSector.data.x + " Sector ID: " + oldSectorID);
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
		Node<Sector> currentSector = sectors.head;
		currentSector.data.draw(batch);
		while(currentSector != sectors.head)
		{
			currentSector.data.draw(batch);
		}
	}
}
