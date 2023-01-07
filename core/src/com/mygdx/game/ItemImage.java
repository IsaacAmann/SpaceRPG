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

public class ItemImage extends Group
{
	public int inventoryTableX;
	public int inventoryTableY;
	private TextureRegionDrawable emptyTexture;
	public Image itemImage;
	private Label itemNumber;
	private int currentNumberItems;
	
	public ItemImage(TextureRegion texture,int inventoryTableX, int inventoryTableY)
	{
		super();
		this.currentNumberItems = 1;
		this.inventoryTableX = inventoryTableX;
		this.inventoryTableY = inventoryTableY;
		this.emptyTexture = new TextureRegionDrawable(texture);
		this.itemImage = new Image(texture);
		this.itemNumber = new Label(""+currentNumberItems, new LabelStyle(game.assets.get("default.fnt", BitmapFont.class), Color.BLACK));
		this.itemNumber.setVisible(false);
		this.addActor(itemImage);
		this.addActor(itemNumber);
		//Add listner for touchdown and touch up to handle dragging
		this.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
			{
				System.out.println("Touch down on inventory element at: ["+getInventoryTableX()+"]["+getInventoryTableY()+"]");
				//Case where no item is currently being moved, removes item reference from playerData and sets currentmoving item to that reference
				if(InventoryWindow.currentMovingItem == null)
				{
					InventoryWindow.currentMovingItem = game.dataStore.playerData.inventory[getInventoryTableX()][getInventoryTableY()];
					game.dataStore.playerData.inventory[getInventoryTableX()][getInventoryTableY()] = null;					
				}
				//Case where a blank tile is clicked on while holding an item
				else if(game.dataStore.playerData.inventory[getInventoryTableX()][getInventoryTableY()] == null)
				{
					game.dataStore.playerData.inventory[getInventoryTableX()][getInventoryTableY()] = InventoryWindow.currentMovingItem;
					InventoryWindow.currentMovingItem = null;
				}
				
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
	
	@Override
	public void act(float delta)
	{
		Item currentItem = game.dataStore.playerData.inventory[inventoryTableX][inventoryTableY];
		super.act(delta);
		if(currentItem != null)
		{
			itemImage.setDrawable(new TextureRegionDrawable(currentItem.texture));
			//update item number
			if(currentItem.stack > 1)
			{
				this.itemNumber.setText(currentItem.stack);
				this.itemNumber.setVisible(true);
			}
			else
			{
				this.itemNumber.setVisible(false);
			}
		}
		else
		{
			itemImage.setDrawable(emptyTexture);
		}
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
