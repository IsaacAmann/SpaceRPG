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
		//Draw all items in player inventory in the corresponding slot (91 slots 7 rows 13 columns)
		for(int i = 0; i < game.dataStore.playerData.inventory.length; i++)
		{
			for(int j = 0; j < game.dataStore.playerData.inventory[i].length; j++)
			{
				if(game.dataStore.playerData.inventory[i][j] != null)
				{
					float x = i*43 + super.xPosition;
					float y = -j*43 + super.yPosition + super.height - 320;
					batch.draw(game.dataStore.playerData.inventory[i][j].texture, x, y, 43, 43);
				}
			}
		}
	}
}
