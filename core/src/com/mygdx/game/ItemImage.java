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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ItemImage extends Image
{
	public int inventoryTableX;
	public int inventoryTableY;
	
	public ItemImage(TextureRegion texture,int inventoryTableX, int inventoryTableY)
	{
		super(texture);
		this.inventoryTableX = inventoryTableX;
		this.inventoryTableY = inventoryTableY;
		
		//Add listner for touchdown and touch up to handle dragging
		this.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				System.out.println("Touch down on inventory element at: ["+getInventoryTableX()+"]["+getInventoryTableY()+"]");
				return true;
			}
			
			/*
			public void touchUp (InputEvent event, float x, float y, int pointer, int button)
			{
				System.out.println("Touch up on inventory element at: ["+getInventoryTableX()+"]["+getInventoryTableY()+"]");
			}
			*/
			
		});
	}
	
	public int getInventoryTableX()
	{
		return inventoryTableX;
	}
	
	public int getInventoryTableY()
	{
		return inventoryTableY;
	}
}
