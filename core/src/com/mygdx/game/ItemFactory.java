package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class ItemFactory
{
	private static TextureAtlas atlas = game.assets.get("spaceRPGTextures.atlas", TextureAtlas.class);
	public Item getItem(int itemID)
	{
		Item createdItem = null;
		switch(itemID)
		{
			case 0:
				createdItem = new Stone();
			break;
			
			default:
				createdItem = null;
		}
		return createdItem;
	}
	
	
	//Base item classes
	
	
	
	//Specific item classes
	private class Stone extends ResourceItem
	{
		public Stone()
		{
			super(atlas.findRegion("stoneItemImage"));
			this.itemID = 0;
		}
	}
	
}
