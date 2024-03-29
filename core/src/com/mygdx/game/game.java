package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class game extends Game {
	public SpriteBatch batch;
	public TextureAtlas textureAtlas;
	public static DataStore dataStore;
	public static AssetManager assets = new AssetManager();
	public static ItemFactory itemFactory;
	public static PlanetScreen planetScreen;
	
	public void create () {
		textureAtlas = new TextureAtlas("spaceRPGTextures.atlas");
		fillAssetManager();
		batch = new SpriteBatch();
		dataStore = new DataStore();
		//loading assets
		while(!assets.update())
		{
			System.out.println("Loading :%" + assets.getProgress());
		}
		itemFactory = new ItemFactory();
		planetScreen = new PlanetScreen(this);
		this.setScreen(planetScreen);
		
	}

	public void render () {
		super.render();
	}

	public void dispose () {
		batch.dispose();

	}
	
	private void fillAssetManager()
	{
		assets.load("spaceRPGTextures.atlas", TextureAtlas.class);
		assets.load("planetHUDTexture.png", Texture.class);
		assets.load("planet1Background.png", Texture.class);
		assets.load("testWindow.png", Texture.class);
		assets.load("inventoryWindowTexture.png", Texture.class);
		assets.load("groundStone.png", Texture.class);
		assets.load("libMono50.fnt", BitmapFont.class);
		assets.load("libMono15.fnt", BitmapFont.class);
		assets.load("default.fnt", BitmapFont.class);
		assets.load("uiskin.json", Skin.class);
		
	}
}
