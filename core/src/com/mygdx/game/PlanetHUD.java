package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlanetHUD
{
	private Texture texture;
	
	private int screenWidth;
	private int screenHeight;
	
	//Fields for health bar
	private static float HEALTH_BAR_LENGTH = 150;
	private float HEALTH_BAR_X_POSITION;
	private float HEALTH_BAR_Y_POSITION; 
	private int HEALTH_BAR_HEIGHT;
	
	public PlanetHUD(Texture texture)
	{
		this.texture = texture;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		HEALTH_BAR_X_POSITION = 20;
		HEALTH_BAR_Y_POSITION = screenHeight - 20;
		HEALTH_BAR_HEIGHT = 50;
	}
	
	private void manageHealthBar(SpriteBatch batch, ShapeRenderer shapeRenderer)
	{
		float healthMultiplier = PlanetScreen.player.health / PlanetScreen.player.startingHealth;
		float rectangleWidth = HEALTH_BAR_LENGTH * healthMultiplier;
		
		//Need to create queue for shape renderer calls, cant run shapes on shaprenderer without first ending spritebatch
		//Use lambda expressions and store them in an array in the planetscreen class
		
	}
	

	public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer)
	{
		batch.draw(texture, 0, 0, PlanetScreen.screenWidth, PlanetScreen.screenHeight);
		manageHealthBar(batch, shapeRenderer);
    }
}
