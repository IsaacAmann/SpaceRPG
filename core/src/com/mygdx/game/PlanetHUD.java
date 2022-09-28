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
	
	public PlanetHUD(Texture texture)
	{
		this.texture = texture;
	}

	public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer)
	{
		batch.draw(texture, 0, 0, PlanetScreen.screenWidth, PlanetScreen.screenHeight);
    }
}
