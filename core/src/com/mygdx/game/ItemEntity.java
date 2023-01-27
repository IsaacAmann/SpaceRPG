package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

//Created by Item objects when deployed into the world
//Able to be picked up / destroyed

public class ItemEntity extends Entity
{
	public Item item;
	
	public ItemEntity(float x, float y, TextureRegion texture, Item item)
	{
		super(texture, x, y, 15, 15, game.planetScreen.getCurrentPlanet().sectors.getFromIndex(0).data);
		this.item = item;
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{
		super.draw(batch);
	}
}
