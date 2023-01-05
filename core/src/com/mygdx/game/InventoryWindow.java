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

public class InventoryWindow extends Window
{
	private Table leftPane;
	private Table rightPane;
	private Table bottomPane;
	
	private Label statTitle;
	private Label equipmentTitle;
	private Label itemTitle;
	
	public static Image draggingItemImage;
	
	private static ItemImage itemImages[][];
	public static Item currentMovingItem = null;
	
	public InventoryWindow(Skin skin)
	{
	    //super("Inventory", new WindowStyle(new BitmapFont(), Color.RED, new TextureRegionDrawable(texture)));
	    super("Inventory", skin);
	    
	    this.setDebug(true);
	    
		this.padTop(100);
		this.left().top();
		TextureAtlas atlas = game.assets.get("spaceRPGTextures.atlas", TextureAtlas.class);
		//BitmapFont font = game.assets.get("libMono15.fnt", BitmapFont.class);
		BitmapFont font = game.assets.get("default.fnt", BitmapFont.class);
		font.getData().setScale(.8f);
		
		//Create array for ItemImages and fill Array 
		itemImages = new ItemImage[game.dataStore.playerData.inventory.length][game.dataStore.playerData.inventory[0].length];
		for(int i = 0; i < itemImages.length; i++)
		{
			for(int j = 0; j < itemImages[i].length; j++)
			{
				itemImages[i][j] = new ItemImage(atlas.findRegion("blankItem"), i, j);
			}
		}
		
		leftPane = new Table();
		rightPane = new Table();
		bottomPane = new Table();
		
		statTitle = new Label("Player Stats", new LabelStyle(font, Color.BLACK));
		equipmentTitle = new Label("Equipment", new LabelStyle(font, Color.BLACK));
		//itemTitle = new Label("Items", new LabelStyle(font, Color.BLACK));
		
		//Left, right, bottom pane setup
		this.add(leftPane).width(400).height(200);
		this.add(rightPane).width(300).height(200);
		this.row();
		this.add(bottomPane).width(700).colspan(2);
		//Left Pane elements
		leftPane.add(statTitle);
		
		//Right Pane elements
		rightPane.add(equipmentTitle);
		
		//Bottom Pane elements
		//bottomPane.add(itemTitle);
		//add ItemImages to table
		for(int i = 0; i < itemImages.length; i++)
		{
			for(int j = 0; j < itemImages[i].length; j++)
			{
				bottomPane.add(itemImages[i][j]).width(32).height(32);
			}
			bottomPane.row();
		}
		
		//draggingItemImage that follows mouse when item is being dragged
		draggingItemImage = new Image(atlas.findRegion("blankItem"));
		draggingItemImage.setVisible(false);
		PlanetHUD.stage.addActor(draggingItemImage);
		
		
		this.setPosition(Gdx.graphics.getWidth()/2 - 350, Gdx.graphics.getHeight()/2);
		this.pack();
	}

	@Override
	public void act(float delta)
	{
		super.act(delta);
		if(currentMovingItem != null)
		{
			draggingItemImage.setDrawable(new TextureRegionDrawable(currentMovingItem.texture));
			draggingItemImage.setVisible(true);
			
		}
		draggingItemImage.toFront();
		//update inventory data in table
		
	}
}
