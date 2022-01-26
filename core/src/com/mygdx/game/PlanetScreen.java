package com.mygdx.game;

import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Matrix4;
import java.util.ArrayList;

public class PlanetScreen implements Screen
{
	//constants
	private static final int SECTOR_LENGTH = 100;
	public static final float PIXELS_TO_METERS = 100f;
	private static final float DEFAULT_PLAYER_SPEED = 25f;
	private static final int TERRAIN_Y_LEVEL = 3;
	public static final float GRAVITY = 1f;

	//Camera and screen variables
	public static int screenWidth;
	public static int screenHeight;
	private OrthographicCamera camera;
	private game game;
	private Matrix4 debugMatrix;

	public static Player player;

	//Set up for map sectors
	private int numberSectors;
	private double sectorLength;

	//Sprites and textures
	public static Sprite testSprite;
	public Texture groundTexture;

	//Physics stuff
	public static World world;
	private ArrayList<Body> terrainSections;
	private TerrainPiece testTerrain;
	private Box2DDebugRenderer debugRenderer;
	//Entity stuff


	public PlanetScreen(final game gameObject)
	{
		game = gameObject;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		//Camera setup
		camera = new OrthographicCamera();
		camera.setToOrtho(true, screenWidth, screenHeight);
		//End Camera setup

		//Physics
		world = new World(new Vector2(0, GRAVITY), true);
		testSprite = game.textureAtlas.createSprite("stone");
		debugRenderer = new Box2DDebugRenderer();
		//Terrain setup
		groundTexture = new Texture(Gdx.files.internal("groundStone.png"));
		testTerrain = new TerrainPiece(groundTexture, 0, 2, SECTOR_LENGTH, TERRAIN_Y_LEVEL);

		//Entities
		player = new Player(3,0,16,16);
	}

	//DRAWING METHODS ----------------------------------------------------------------------
	private void drawTerrain(SpriteBatch batch)
	{
		//batch.draw(groundTexture, 0, screenHeight - TERRAIN_Y_LEVEL, SECTOR_LENGTH * PIXELS_TO_METERS, 200f, 0, 0, 200, TERRAIN_Y_LEVEL, false, false);
		testTerrain.draw(batch);
	}

	//INPUT METHODS ------------------------------------------------------------------------
	static class playerInput
	{
		static boolean up = false;
		static boolean down = false;
		static boolean left = false;
		static boolean right = false;
		static boolean fire = false;
	}

	private void manageInput()
	{
			if(Gdx.input.isKeyPressed(Input.Keys.W))
			{
				playerInput.up = true;
			}else
			{
				playerInput.up = false;
			}

			if(Gdx.input.isKeyPressed(Input.Keys.S))
			{
				playerInput.down = true;
			}else
			{
				playerInput.down = false;
			}

			if(Gdx.input.isKeyPressed(Input.Keys.A))
			{
				playerInput.left = true;
			}
			else
			{
				playerInput.left = false;
			}

			if(Gdx.input.isKeyPressed(Input.Keys.D))
			{
				playerInput.right = true;
			}
			else
			{
				playerInput.right = false;
			}
			if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
			{
				playerInput.fire = true;
			}
			else
			{
				playerInput.fire = false;
			}
	}

	//GAME OBJECT STUFF ----------------------------------------------------------------------

	//game loop method
	private void gameLoop()
	{
		manageInput();
		manageCamera();

	}

	private void manageCamera()
	{
		if(playerInput.right)
		{
			camera.translate(DEFAULT_PLAYER_SPEED, 0);
		}
		if(playerInput.left)
		{
			camera.translate(DEFAULT_PLAYER_SPEED * -1, 0);
		}
		if(playerInput.up)
		{
			camera.translate(0, -1 * DEFAULT_PLAYER_SPEED);
		}
		if(playerInput.down)
		{
			camera.translate(0, DEFAULT_PLAYER_SPEED);
		}
		System.out.println("camera x: " + camera.position.x + "camera y: " + camera.position.y);
		camera.update();
	}

	@Override
	public void render(float delta)
	{
		gameLoop();
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		ScreenUtils.clear(0, 0, 0, 1);

		game.batch.setProjectionMatrix(camera.combined);
		debugMatrix = game.batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);

		game.batch.begin();
			//testSprite.draw(game.batch);
			drawTerrain(game.batch);
			player.update();
			player.sprite.draw(game.batch);
		game.batch.end();

		debugRenderer.render(world, debugMatrix);
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
