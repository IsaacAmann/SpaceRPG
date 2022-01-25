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
  private Texture texture;
  public Body body;
  public int x;
  public int y;
  public float width;
  public float height;

  public TerrainPiece(Texture texture, int x, int y, float width, float height)
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
    edgeShape.set(x, y, x + width, y);
    fixtureDef.shape = edgeShape;

    body = PlanetScreen.world.createBody(bodyDef);
    body.createFixture(fixtureDef);

    edgeShape.dispose();
  }

  public void draw(SpriteBatch batch)
  {
    //batch.draw(groundTexture, 0, screenHeight - TERRAIN_Y_LEVEL, SECTOR_LENGTH * PIXELS_TO_METERS, 200f, 0, 0, 200, TERRAIN_Y_LEVEL, false, false);
    batch.draw(texture, x, y, width, height, 0, 0, 256, 256, false, false);

  }

  public void update()
  {

  }
}
