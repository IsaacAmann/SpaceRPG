package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlanetScreen implements Screen
{
	//constants
	private static final int SECTOR_LENGTH = 500;
	private static final float PIXELS_TO_METERS = 100f;


	//Camera and screen variables
	private static int screenWidth;
	private static int screenHeight;
	private OrthographicCamera camera;
	private game game;

	//Set up for map sectors
	private int numberSectors;
	private double sectorLength;

	//Sprites and textures
	private Sprite testSprite;
	private Texture groundTexture;


	public PlanetScreen(final game gameObject)
	{
		game = gameObject;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		//Camera setup
		camera = new OrthographicCamera();
		camera.setToOrtho(true, screenWidth, screenHeight);
		//End Camera setup

		testSprite = game.textureAtlas.createSprite("stone");
		groundTexture = new Texture(Gdx.files.internal("groundStone.png"));
	}

	@Override
	public void render(float delta)
	{
		ScreenUtils.clear(0, 0, 0, 1);
		game.batch.begin();
		testSprite.draw(game.batch);
		game.batch.end();
	}

	@Override
	public void dispose()
	{

	}

	@Override
	public void resize(int width, int height)
	{

	}

	@Override
	public void show()
	{

	}

	@Override
	public void hide()
	{

	}

	@Override
	public void pause()
	{

	}

	@Override
	public void resume()
	{

	}

}
