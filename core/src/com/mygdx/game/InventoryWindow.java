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
	public InventoryWindow(Texture texture)
	{
	    super("Inventory", new WindowStyle(new BitmapFont(), Color.RED, new TextureRegionDrawable(texture)));
	    //this.setDebug(true);
	    
		this.padTop(100);
		
		BitmapFont font = game.assets.get("libMono15.fnt", BitmapFont.class);
		//font.getData().setScale(.25f);
		
		
		leftPane = new Table();
		rightPane = new Table();
		bottomPane = new Table();
		
		statTitle = new Label("Player Stats", new LabelStyle(font, Color.BLACK));
		equipmentTitle = new Label("Equipment", new LabelStyle(font, Color.BLACK));
		
		this.add(leftPane);
		this.add(rightPane);
		this.row();
		this.add(bottomPane);
		
		leftPane.add(statTitle);
		rightPane.add(equipmentTitle);
		
		this.pack();
	}

}
