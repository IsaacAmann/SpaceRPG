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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Matrix4;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.*;




import com.strongjoshua.console.*;


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
	public static Texture groundTexture;
	public Texture backgroundTexture;
	public Texture hudTexture;
	
	//Window textures
	public static Texture testWindowTexture;
	public static Texture inventoryWindowTexture;
	
	public ShapeCallContainer shapeCallContainer;
	public ShapeRenderer shapeRenderer;
	
	//Default humanoid Sprites
	public static TextureRegion defaultHumanoidSprite;
	
	//Physics stuff
	public static World world;
	private ArrayList<Body> terrainSections;
	private TerrainPiece testTerrain;
	private Box2DDebugRenderer debugRenderer;
	//Entity stuff
	
	//Stores Entity objects, should unload to another array list within the sector when sector is unloaded
	//When loading from sector, entity objects should be pushed into this list
	public ArrayList<Entity> loadedEntities;
	
	
	//debug console 
	public GUIConsole console;


	public PlanetScreen(final game gameObject)
	{
		game = gameObject;
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		shapeRenderer = new ShapeRenderer();
		shapeCallContainer = new ShapeCallContainer();
		//Textures and Sprites
		//playerSprite = game.textureAtlas.findRegion("player");
		//playerSprite.flip(false,true);
		backgroundTexture = new Texture(Gdx.files.internal("planet1Background.png"));
		hudTexture = new Texture(Gdx.files.internal("planetHUDTexture.png"));
		//default humanoid sprites
		defaultHumanoidSprite = game.textureAtlas.findRegion("defaultHumanoidStanding1");
		defaultHumanoidSprite.flip(false,true);
		
		//Camera setup
		camera = new OrthographicCamera();
		camera.setToOrtho(true, screenWidth, screenHeight);
		hudCamera = new OrthographicCamera();
		hudCamera.setToOrtho(false, screenWidth, screenHeight);
		playerPositionChange = new Vector3(0,0,0);
		//End Camera setup

		//HUD setup
		planetHUD = new PlanetHUD(hudTexture);

		//Terrain setup
		groundTexture = new Texture(Gdx.files.internal("groundStone.png"));
		//testTerrain = new TerrainPiece(groundTexture, 0, 0, SECTOR_LENGTH, TERRAIN_Y_LEVEL);
		
		//Loading textures for different windows
		testWindowTexture = new Texture(Gdx.files.internal("testWindow.png"));
		inventoryWindowTexture = new Texture(Gdx.files.internal("inventoryWindowTexture.png"));

		//Physics
		world = new World(new Vector2(0, GRAVITY), true);
		debugRenderer = new Box2DDebugRenderer();

		//Planet / Sector setup
		currentPlanet = new Planet(100);
		
		
		//Input processor
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new GameInputProcessor());
		multiplexer.addProcessor(planetHUD.stage);
		//Gdx.input.setInputProcessor(new GameInputProcessor());
		Gdx.input.setInputProcessor(multiplexer);
		
		
		//console setup
		console = new GUIConsole(true);
		console.setSizePercent(100f, 100f);
		console.setPosition(50, 50);
		console.setVisible(true);
		console.setCommandExecutor(new ConsoleCommandExecutor());
		console.setDisplayKeyID(Input.Keys.GRAVE);
		
		//Entities
		player = new Player(defaultHumanoidSprite, 2,0,25,80, 100, 100);
		//Test inventory item
		ResourceItem testItem = new ResourceItem(defaultHumanoidSprite);
		for(int i=0; i < 40; i++)
		game.dataStore.playerData.addInventoryItem(testItem);
		testHumanoid = new HumanoidV2(defaultHumanoidSprite, 3, 0, 25, 80);

		//Background
		backgroundPosition1 = new Vector2(0, -screenHeight);
		backgroundPosition2 = new Vector2(-screenWidth, -screenHeight);
		

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
		static boolean mouseDown = false;
		static Vector3 mouse = new Vector3(0,0,0);
		static Vector3 mouseReal = new Vector3(0,0,0);
		static Vector3 hudMouse = new Vector3(0,0,0);
	}

	//GAME OBJECT STUFF ----------------------------------------------------------------------

	//game loop method
	private void gameLoop()
	{
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

	public class GameInputProcessor extends InputAdapter
	{
		@Override
			public boolean mouseMoved(int x, int y)
			{
				playerInput.mouseReal.set(x, y, 0);
				hudCamera.unproject(playerInput.hudMouse.set(x, y, 0));
				camera.unproject(playerInput.mouse.set(x,y,0));
				//System.out.println("Real mouse cords x: " + playerInput.mouseReal.x + " y: " + playerInput.mouseReal.y + " Game Cords: x: " + playerInput.mouse.x + " y: " + playerInput.mouse.y);
				return false;
			}
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button)
			{
				if(button == Input.Buttons.LEFT)
				{
					//Mouse cords handled by mouseMoved instead
					//camera.unproject(playerInput.mouse.set(screenX, screenY, 0));
					//playerInput.mouseReal.set(screenX, screenY, 0);
					playerInput.mouseDown = true;
					
					
					return true;
				}
				
				return false;
			}
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button)
			{
				if(button == Input.Buttons.LEFT)
				{
					playerInput.mouseDown = false;
					return true;
				}
				return false;
			}
			@Override
			public boolean keyDown(int keycode)
			{
				switch(keycode)
				{
					case Input.Keys.W:
						playerInput.up = true;
					break;
					
					case Input.Keys.A:
						playerInput.left = true;
					break;
					
					case Input.Keys.S:
						playerInput.down = true;
					break;
					
					case Input.Keys.D:
						playerInput.right = true;
					break;
					
					case Input.Keys.E:
						//PlanetHUD.inventoryWindow.toggleVisible();
					break;
					
					case Input.Keys.GRAVE:
						
					break;
				}
				return false;
			}
			@Override
			public boolean keyUp(int keycode)
			{
				switch(keycode)
				{
					case Input.Keys.W:
						playerInput.up = false;
					break;
					
					case Input.Keys.A:
						playerInput.left = false;
					break;
				
					case Input.Keys.S:
						playerInput.down = false;
					break;
					
					case Input.Keys.D:
						playerInput.right = false;
					break;
				}
				return false;
			}
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
		console.refresh();
		console.draw();
		
		

	}

	@Override
	public void dispose()
	{

	}

	@Override
	public void resize(int width, int height)
	{
		//screenWidth = Gdx.graphics.getWidth();
		//screenHeight = Gdx.graphics.getHeight();
		//hudCamera.setToOrtho(false, screenWidth, screenHeight);
		//camera.setToOrtho(true, screenWidth, screenHeight);
		
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

	public class ConsoleCommandExecutor extends CommandExecutor
	{
		public void spawnHuman()
		{
			testHumanoid = new HumanoidV2(defaultHumanoidSprite, 3, 0, 25, 80);
		}
		public void fling(float force)
		{
			player.body.applyTorque(force,true);
		}
		
		public void printScreenDimensions()
		{
			console.log("screen Width: " + Gdx.graphics.getWidth() + " Screen Height: " + Gdx.graphics.getHeight());
		}
		
		public void displayInventory()
		{
			for(int i = 0; i < game.dataStore.playerData.inventory.length; i++)
			{
				for(int j = 0; j < game.dataStore.playerData.inventory[i].length; j++)
				{
					if(game.dataStore.playerData.inventory[i][j] != null)
						console.log(i + ", " + j + " ItemID =  " + game.dataStore.playerData.inventory[i][j].itemID);
				}
			}
		}
	}
}
