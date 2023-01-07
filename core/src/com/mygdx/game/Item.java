package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Item
{
	public boolean canDrop;
	public boolean canEquip;
	public boolean isDeployable;
	public int itemID;
	public boolean isStackable;
	public int maxStack;
	public TextureRegion texture;
	public int stack;
	
	public Item()
	{
		canDrop = false;
		canEquip = false;
		isDeployable = false;
		itemID = 0;
		isStackable = false;
		maxStack = 1;
		stack = 1;
		this.texture = null;
	}
	
}
