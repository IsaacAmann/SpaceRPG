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
import com.badlogic.gdx.graphics.Color;

public class PlanetScreen implements Screen
{
	//constants
	private static final int SECTOR_LENGTH = 40;
	public static final float PIXELS_TO_METERS = 100f;
	private static final float DEFAULT_PLAYER_SPEED = 25f;
	public static final int TERRAIN_Y_LEVEL = 6;
	public static final float GRAVITY = 9.8f;

	//Camera and screen variables
	public static int screenWidth;
	public static int screenHeight;
	private OrthographicCamera camera;
	private game game;
	private Matrix4 debugMatrix;
	public Vector3 playerPositionChange;
	public Vector2 backgroundPosition1;
	public Vector2 backgroundPosition2;
	//seperate camera for drawing hud elements directly to screen
	private OrthographicCamera hudCamera;

	public static Player player;
	public HumanoidV2 testHumanoid;
	public PlanetHUD planetHUD;


	//Set up for map sectors
	public Planet currentPlanet;

	//Sprites and textures
	public static Sprite testSprite;
	public Sprite playerSprite;
	public static Texture groundTexture;
	public Texture backgroundTexture;
	public Texture hudTexture;
	
	public ShapeCallContainer shapeCallContainer;
	public ShapeRenderer shapeRenderer;
	
	//Default humanoid Sprites
	public Sprite defaultHumanoidSprite;
	public Sprite defaultHumanoidHead;
	public Sprite defaultHumanoidBody;
	public Sprite defaultHumanoidLeg;
	public Sprite defaultHumanoidArm;
	public Sprite defaultHumanoidForearm;


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
		shapeRenderer = new ShapeRenderer();
		shapeCallContainer = new ShapeCallContainer();
		//Textures and Sprites
		testSprite = game.textureAtlas.createSprite("stone");
		playerSprite = game.textureAtlas.createSprite("player");
		playerSprite.flip(false,true);
		backgroundTexture = new Texture(Gdx.files.internal("planet1Background.png"));
		hudTexture = new Texture(Gdx.files.internal("planetHUDTexture.png"));
		//default humanoid sprites
		defaultHumanoidSprite = game.textureAtlas.createSprite("defaultHumanoidStanding1");
		defaultHumanoidHead = game.textureAtlas.createSprite("defaultHumanoidHead");
		defaultHumanoidBody = game.textureAtlas.createSprite("defaultHumanoidBody");
		defaultHumanoidArm = game.textureAtlas.createSprite("defaultHumanoidArm");
		defaultHumanoidForearm = game.textureAtlas.createSprite("defaultHumanoidForearm");
		defaultHumanoidLeg = game.textureAtlas.createSprite("defaultHumanoidLeg");

		defaultHumanoidHead.flip(false,true);
		defaultHumanoidBody.flip(false,true);
		defaultHumanoidArm.flip(false,true);
		defaultHumanoidForearm.flip(false,true);
		defaultHumanoidLeg.flip(false,true);
		defaultHumanoidSprite.flip(false,true);
		
		//Camera setup
		camera = new OrthographicCamera();
		camera.setToOrtho(true, screenWidth, screenHeight);
		hudCamera = new OrthographicCamera();
		hudCamera.setToOrtho(false, screenWidth, screenHeight);
		playerPositionChange = new Vector3(0,0,0);
		//End Camera setup

		//Terrain setup
		groundTexture = new Texture(Gdx.files.internal("groundStone.png"));
		//testTerrain = new TerrainPiece(groundTexture, 0, 0, SECTOR_LENGTH, TERRAIN_Y_LEVEL);

		//Physics
		world = new World(new Vector2(0, GRAVITY), true);
		testSprite = game.textureAtlas.createSprite("stone");
		debugRenderer = new Box2DDebugRenderer();

		//Planet / Sector setup
		currentPlanet = new Planet(100);
		
		

		//Entities
		player = new Player(defaultHumanoidSprite, 2,0,25,80, 100, 100);
		testHumanoid = new HumanoidV2(defaultHumanoidSprite, 3, 0, 25, 80);

		//Background
		backgroundPosition1 = new Vector2(0, -screenHeight);
		backgroundPosition2 = new Vector2(-screenWidth, -screenHeight);
		
		//HUD setup
		planetHUD = new PlanetHUD(hudTexture);

		//Contact Listener
		world.setContactListener(new ContactListener()
		{
			@Override
			public void beginContact(Contact contact)
			{
				
				System.out.println("Contact");
				if(contact.getFixtureA().getBody() == player.body || contact.getFixtureB().getBody() == player.body)
				{
					player.canJump = true;
				}
			}
			@Override
      public void endContact(Contact contact)
			{
      }

      @Override
      public void preSolve(Contact contact, Manifold oldManifold)
			{
      }

      @Override
      public void postSolve(Contact contact, ContactImpulse impulse)
			{
      }
		});
	}

	//DRAWING METHODS ----------------------------------------------------------------------
	private void drawTerrain(SpriteBatch batch)
	{
		//batch.draw(groundTexture, 0, screenHeight - TERRAIN_Y_LEVEL, SECTOR_LENGTH * PIXELS_TO_METERS, 200f, 0, 0, 200, TERRAIN_Y_LEVEL, false, false);
		//testTerrain.draw(batch);
	}
	private void drawBackground(SpriteBatch batch)
	{
		checkBackground(backgroundPosition1);
		checkBackground(backgroundPosition2);
		batch.draw(backgroundTexture, backgroundPosition1.x, backgroundPosition1.y, screenWidth, screenHeight);
		batch.draw(backgroundTexture, backgroundPosition2.x, backgroundPosition2.y, screenWidth, screenHeight);
	}
	private void checkBackground(Vector2 position)
	{
		float playerPosition = player.body.getPosition().x * PIXELS_TO_METERS;
		if(position.x - playerPosition < -screenWidth*1.5)
		{
			position.x += screenWidth*2;
		}
		if(playerPosition - position.x < -screenWidth*.5)
		{
			position.x -= screenWidth*2;
		}
	}

	//INPUT METHODS ------------------------------------------------------------------------
	public static class playerInput
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
		player.update();
		currentPlanet.update();
	}

	private void manageCamera()
	{
		/*
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
		*/
		camera.position.set(player.body.getPosition().x*PIXELS_TO_METERS,player.body.getPosition().y*PIXELS_TO_METERS - 150,0);
		//System.out.println("camera x: " + camera.position.x + "camera y: " + camera.position.y);
		camera.update();
	}


	@Override
	public void render(float delta)
	{
		gameLoop();
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		ScreenUtils.clear(0, 0, .5f, 0);
		manageCamera();
		game.batch.setProjectionMatrix(camera.combined);
		debugMatrix = game.batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS, PIXELS_TO_METERS, 0);

		game.batch.begin();
			//testSprite.draw(game.batch);
			drawTerrain(game.batch);
			drawBackground(game.batch);
			currentPlanet.draw(game.batch);
			player.draw(game.batch);
			testHumanoid.draw(game.batch);
		game.batch.end();
		debugRenderer.render(world, debugMatrix);
		//HUD render calls
		game.batch.setProjectionMatrix(hudCamera.combined);
		shapeRenderer.setProjectionMatrix(hudCamera.combined);
		game.batch.begin();
			//game.batch.draw(groundTexture, 0, 0, 200, 44);
			planetHUD.draw(game.batch, shapeRenderer, shapeCallContainer);
		game.batch.end();
		
		
		shapeCallContainer.execute(shapeRenderer);
		
		
		

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
