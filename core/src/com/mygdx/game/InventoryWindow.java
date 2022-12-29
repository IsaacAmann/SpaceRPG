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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;


public class InventoryWindow extends MenuWindow
{
	private Item currentDraggedItem;
	
	public InventoryWindow(float x, float y, float width, float height, Color backgroundColor, String menuLabel, Texture texture, boolean visible)
	{
		super(x, y, width, height, backgroundColor, menuLabel, texture, visible);
		
	}
	
	private void mouseInventoryMovement(Vector3 mouse)
	{
		if(mouse.x >= xPosition && mouse.x <= xPosition + width && mouse.y >= yPosition && mouse.y <= yPosition + height)
		{
			//System.out.println("hit " + "x: " + mouse.x + ", " + mouse.y + " " + xPosition + " " + yPosition	);
			if(PlanetScreen.playerInput.mouseDown)
			{
				int i = (int)mouse.y / 45;
				int j = (int)mouse.x / 45;
				System.out.println("Inventory ref: x: " + i + " y: " + j);
				//game.dataStore.playerData.inventory[i][j] = null;
			}
		}
	}
	
	@Override
	public void update(SpriteBatch batch)
	{
		super.update(batch);
		//Draw all items in player inventory in the corresponding slot (91 slots 7 rows 13 columns)
		if(visible)
		{
			for(int i = 0; i < game.dataStore.playerData.inventory.length; i++)
			{
				for(int j = 0; j < game.dataStore.playerData.inventory[i].length; j++)
				{
					if(game.dataStore.playerData.inventory[i][j] != null)
					{
						float x = j*45 + super.xPosition;
						float y = -i*45 + super.yPosition + super.height - 320 - 6;
						batch.draw(game.dataStore.playerData.inventory[i][j].texture, x, y, 43, 43);
					}
				}
			}
			mouseInventoryMovement(PlanetScreen.playerInput.hudMouse);
		}
	}
}
