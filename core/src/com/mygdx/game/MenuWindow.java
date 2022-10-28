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

public class MenuWindow
{
	public float xPosition;
	public float yPosition;
	public float width;
	public float height;
	public Color backgroundColor;
	public String menuLabel;
	
	private BitmapFont titleFont;
	
	private int screenWidth;
	private int screenHeight;
	
	private Texture windowTexture;
	
	public MenuWindow(float x, float y, float width, float height, Color backgroundColor, String menuLabel, Texture texture)
	{
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		xPosition = x;
		yPosition = y;
		this.width = width;
		this.height = height;
		this.backgroundColor = backgroundColor;
		this.menuLabel = menuLabel;
		
		titleFont = new BitmapFont();
		titleFont.setColor(Color.BLACK);
		windowTexture = texture;
	}
	
	public void update(SpriteBatch batch)
	{
		//shapeCallContainer.addShapeCall(xPosition, yPosition, width, height, backgroundColor);
		//May need to queue font calls with shapes as well, no way to overlay the text and control what is drawn first.
		batch.draw(windowTexture, xPosition, yPosition, 600, 600);
		titleFont.draw(batch, menuLabel, xPosition, yPosition + height);
	}
}
