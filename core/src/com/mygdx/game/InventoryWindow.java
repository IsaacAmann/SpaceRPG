package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class InventoryWindow extends MenuWindow
{
	public InventoryWindow(float x, float y, float width, float height, Color backgroundColor, String menuLabel, Texture texture, boolean visible)
	{
		super(x, y, width, height, backgroundColor, menuLabel, texture, visible);
	
	}
	
	@Override
	public void update(SpriteBatch batch)
	{
		super.update(batch);	
	}
}
