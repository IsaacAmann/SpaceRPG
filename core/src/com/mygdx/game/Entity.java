package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

public abstract class Entity
{
  public Body body;
  public TextureRegion textureRegion;
  public static final float RADIANS_TO_DEGREES = (180 / (float) Math.PI);
  public float width;
  public float height;
  public float x;
  public float y;
  public Sector currentSector;
  public boolean loaded;
  
  public float oldBodyX;
  public float oldBodyY;

	public Entity(TextureRegion textureRegion, float x, float y, int width, int height, Sector currentSector)
	{
		this.textureRegion = new TextureRegion(textureRegion);
		/*
		this.width = textureRegion.getRegionWidth();
		this.height = textureRegion.getRegionHeight();
		*/
		
		this.width = width;
		this.height = height;
		
		this.x = x;
		this.y = y;
		this.loaded = true;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(this.width/2 / PlanetScreen.PIXELS_TO_METERS, this.height/2 / PlanetScreen.PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;

		body = PlanetScreen.world.createBody(bodyDef);
		body.setUserData(this);
		body.createFixture(fixtureDef);
		shape.dispose();
		
		this.currentSector = currentSector;
		if(currentSector != null)
			currentSector.entityList.add(this);
	}

	public void update()
	{

	}
	//Delete physics body and remove all references to the object
	public void destroy()
	{
		PlanetScreen.entityRemoveList.add(this);
		//PlanetScreen.world.destroyBody(this.body);
		//this.body = null;
		currentSector.entityList.remove(this);
		
	}
	public void unload()
	{
		this.oldBodyX = this.body.getPosition().x;
		this.oldBodyY = this.body.getPosition().y;
		PlanetScreen.world.destroyBody(this.body);
		this.body = null;
		System.out.print("unLoaded entity: x: " + x + " y: " + y);
		this.loaded = false;
	}
	
	public void load(float xDifference)
	{
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(oldBodyX - xDifference, oldBodyY - 1);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2 / PlanetScreen.PIXELS_TO_METERS, height/2 / PlanetScreen.PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;

		body = PlanetScreen.world.createBody(bodyDef);
		body.setUserData(this);
		body.createFixture(fixtureDef);
		shape.dispose();
		System.out.print("Loaded entity: x: " + body.getPosition().x + " y: " + body.getPosition().y);
		this.loaded = true;
	}
	
	public void handleCollision(Entity otherEntity)
	{
		//System.out.println(this + "Contacted with: " + otherEntity);
	}
	
	public void draw(SpriteBatch batch)
	{
		if(this.body != null)
		{
			//x = this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS - width;
			//y = this.body.getPosition().y * PlanetScreen.PIXELS_TO_METERS - height/2;
		
			x = this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS;
			y = this.body.getPosition().y * PlanetScreen.PIXELS_TO_METERS;

			//batch.draw(textureRegion, x, y, width/2, height/2, width, height, 1, 1, this.body.getAngle() * RADIANS_TO_DEGREES);  
			batch.draw(textureRegion, x - width/2, y - height/2, width/2, height/2, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), 1, 1, this.body.getAngle() * RADIANS_TO_DEGREES);
		
			//Check if the entity has left the bounds of its current sector
			//Leaving right edge case
			if(currentSector != null)
			{
				if(this.body.getPosition().x > currentSector.x + currentSector.SECTOR_LENGTH)
				{
					Sector nextSector = game.planetScreen.getCurrentPlanet().sectors.getFromIndex(currentSector.sectorID).next.data;
					currentSector.entityList.remove(this);
					nextSector.entityList.add(this);
					this.currentSector = nextSector;
					System.out.println("Moved entity to next Sector new sectorID: " + currentSector.sectorID);
				}
				//Leaving left edge case
				if(this.body.getPosition().x < currentSector.x)
				{
					Sector nextSector = game.planetScreen.getCurrentPlanet().sectors.getFromIndex(currentSector.sectorID).previous.data;
					currentSector.entityList.remove(this);
					nextSector.entityList.add(this);
					this.currentSector = nextSector;
					System.out.println("Moved entity to next Sector new sectorID: " + currentSector.sectorID);
				}
			}
		}
	}
}
