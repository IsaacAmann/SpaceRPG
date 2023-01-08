package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ResourceItem extends Item
{
	public ResourceItem(TextureRegion texture)
	{
		super();
		canDrop = true;
		isStackable = true;
		maxStack = 100;
		this.texture = texture;
	}	
	
	public ResourceItem(ResourceItem item)
	{
		super(item);
		
	}
	
	@Override
	public ResourceItem clone()
	{
		return new ResourceItem(this);
	}
}
