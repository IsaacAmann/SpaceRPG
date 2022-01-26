package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TerrainPiece
{
  private static final float PIXELS_TO_METERS = PlanetScreen.PIXELS_TO_METERS;
  private Texture texture;
  public Body body;
  public float x;
  public float y;
  public float width;
  public float height;

  public TerrainPiece(Texture texture, float x, float y, float width, float height)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;

    this.texture = texture;
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set(x,y);
    FixtureDef fixtureDef = new FixtureDef();

    EdgeShape edgeShape = new EdgeShape();
    edgeShape.set(-width/2, y, width/2, y);
    //edgeShape.set()
    fixtureDef.shape = edgeShape;

    body = PlanetScreen.world.createBody(bodyDef);
    body.createFixture(fixtureDef);

    edgeShape.dispose();
  }

  public void draw(SpriteBatch batch)
  {
    //batch.draw(groundTexture, 0, screenHeight - TERRAIN_Y_LEVEL, SECTOR_LENGTH * PIXELS_TO_METERS, 200f, 0, 0, 200, TERRAIN_Y_LEVEL, false, false);
    //batch.draw(texture, x/PlanetScreen.PIXELS_TO_METERS, y/PlanetScreen.PIXELS_TO_METERS, width, height, 0, 0, 256, 256, false, false);
    //batch.draw(texture, body.getPosition().x*PIXELS_TO_METERS, (body.getPosition().y)*PIXELS_TO_METERS, width*PIXELS_TO_METERS, height*PIXELS_TO_METERS, 0, 0, 256, 256, false, false);
    //batch.draw(texture, body.getPosition().x*PIXELS_TO_METERS, (body.getPosition().y)*PIXELS_TO_METERS, width*PIXELS_TO_METERS, height*PIXELS_TO_METERS);
    batch.draw(texture, (x - width/2)*PIXELS_TO_METERS, y * PIXELS_TO_METERS, width*PIXELS_TO_METERS, height*PIXELS_TO_METERS);

  }

  public void update()
  {

  }
}
