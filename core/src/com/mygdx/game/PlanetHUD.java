package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;



public class PlanetHUD
{
	private Texture texture;
	private TextureAtlas textureAtlas;
	
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
	
	//Stage for new window system
	public static Stage stage;
	
	//Scene2d ui elements
	public static Window inventoryWindow;
	
	
	public PlanetHUD(Texture texture)
	{
		this.texture = texture;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		
		textureAtlas = game.assets.get("spaceRPGTextures.atlas", TextureAtlas.class);
		
		stage = new Stage(new StretchViewport(screenWidth, screenHeight));
		fillUIElements();
		//positions of health bar retrieved from image of hud
		HEALTH_BAR_X_POSITION = 6;
		HEALTH_BAR_Y_POSITION = screenHeight - HEALTH_BAR_HEIGHT - 686;
		
		//positionss of energy bar retreived from image of hud
		ENERGY_BAR_X_POSITION = 6;
		ENERGY_BAR_Y_POSITION = screenHeight - HEALTH_BAR_HEIGHT - 742;
		
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
	
	private void fillUIElements()
	{
		Table mainTable = new Table();
		mainTable.setFillParent(true);
		
		stage.addActor(mainTable);
		
		//InventoryWindow inventoryWindow = new InventoryWindow(game.assets.get("inventoryWindowTexture.png", Texture.class));
		inventoryWindow = new InventoryWindow(game.assets.get("uiskin.json", Skin.class));
		stage.addActor(inventoryWindow);
	}

	public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer, ShapeCallContainer shapeCallContainer)
	{
		//batch.draw(texture, 0, 0, PlanetScreen.screenWidth, PlanetScreen.screenHeight);
		manageHealthBar(shapeCallContainer);
		manageEnergyBar(shapeCallContainer);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
    }
    
    public static void resize(int width, int height)
    {
		stage.getViewport().update(width, height, true);
	}
}
