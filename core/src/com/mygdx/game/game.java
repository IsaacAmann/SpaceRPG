package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class game extends Game {
	public SpriteBatch batch;
	public TextureAtlas textureAtlas;
	public static DataStore dataStore;


	public void create () {
		textureAtlas = new TextureAtlas("spaceRPGTextures.atlas");
		batch = new SpriteBatch();
		dataStore = new DataStore();
		this.setScreen(new PlanetScreen(this));
	}

	public void render () {
		super.render();
	}

	public void dispose () {
		batch.dispose();

	}
}
