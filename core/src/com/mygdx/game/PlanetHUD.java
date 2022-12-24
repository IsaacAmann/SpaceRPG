package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;


public class PlanetHUD
{
	private Texture texture;
	
	private int screenWidth;
	private int screenHeight;
	
	//Fields for health bar
	private static float HEALTH_BAR_LENGTH = 280;
	private float HEALTH_BAR_X_POSITION;
	private float HEALTH_BAR_Y_POSITION; 
	private static float HEALTH_BAR_HEIGHT = 20;
	
	//Fields for energy bar
	private float ENERGY_BAR_X_POSITION;
	private float ENERGY_BAR_Y_POSITION;
	private float ENERGY_BAR_HEIGHT;
	
	//Window system
	public static InventoryWindow inventoryWindow;
	private ArrayList<MenuWindow> windows;
	
	
	public PlanetHUD(Texture texture)
	{
		this.texture = texture;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		//positions of health bar retrieved from image of hud
		HEALTH_BAR_X_POSITION = 6;
		HEALTH_BAR_Y_POSITION = screenHeight - HEALTH_BAR_HEIGHT - 686;
		
		//positionss of energy bar retreived from image of hud
		ENERGY_BAR_X_POSITION = 6;
		ENERGY_BAR_Y_POSITION = screenHeight - HEALTH_BAR_HEIGHT - 742;
		inventoryWindow = new InventoryWindow(screenWidth / 2 - 300, screenHeight / 2 - 300, 600, 600, Color.SCARLET, "Inventory", PlanetScreen.inventoryWindowTexture, false);
		windows = new ArrayList<MenuWindow>();
		
		windows.add(inventoryWindow);
	}
	
	private void manageHealthBar(ShapeCallContainer shapeCallContainer)
	{
		//Casting fields to float so that it gives floating point division instead
		float healthMultiplier = (float) PlanetScreen.player.health / (float) PlanetScreen.player.startingHealth;
		
		float rectangleWidth = HEALTH_BAR_LENGTH * healthMultiplier;
		//Cannot draw the shapes directly, in order to use the shape renderer, the spritebatch has to be ended. 
		//Used a container class to call all shapeRenderer calls in one batch to get around this
		//shapeCallContainer.addShapeCall(0, 0, 44, 44, Color.FIREBRICK);
		if(healthMultiplier > 0)
			shapeCallContainer.addShapeCall(HEALTH_BAR_X_POSITION, HEALTH_BAR_Y_POSITION, rectangleWidth, HEALTH_BAR_HEIGHT, Color.FIREBRICK);
	}
	
	private void manageEnergyBar(ShapeCallContainer shapeCallContainer)
	{
		float energyMultiplier = (float) PlanetScreen.player.energy / (float) PlanetScreen.player.startingEnergy;
		float rectangleWidth = HEALTH_BAR_LENGTH * energyMultiplier;
		if(energyMultiplier > 0)
			shapeCallContainer.addShapeCall(ENERGY_BAR_X_POSITION, ENERGY_BAR_Y_POSITION, rectangleWidth, HEALTH_BAR_HEIGHT, Color.BLUE);
	}
	

	public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, ShapeCallContainer shapeCallContainer)
	{
		batch.draw(texture, 0, 0, PlanetScreen.screenWidth, PlanetScreen.screenHeight);
		manageHealthBar(shapeCallContainer);
		manageEnergyBar(shapeCallContainer);
		
		for(int i = 0; i < windows.size(); i++)
		{
			windows.get(i).update(batch);
		}
    }
}
