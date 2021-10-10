package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class game extends Game {
	public SpriteBatch batch;
	
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen(this));
	}

	public void render () {
		super.render();
	}
	
	public void dispose () {
		batch.dispose();
		
	}
}
