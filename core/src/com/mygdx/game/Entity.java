package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

public abstract class Entity
{
  public Body body;
  public Sprite sprite;
  public static final float RADIANS_TO_DEGREES = (180 / (float) Math.PI);

  public Entity(Sprite sprite, int x, int y, int width, int height)
  {
    this.sprite = sprite;

    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    bodyDef.position.set(x, y);

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(width/2 / PlanetScreen.PIXELS_TO_METERS, height/2 / PlanetScreen.PIXELS_TO_METERS);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.density = 1f;

    body = PlanetScreen.world.createBody(bodyDef);
    body.createFixture(fixtureDef);
    shape.dispose();
  }

  public void update()
  {

  }
  public void draw(SpriteBatch batch)
  {
    this.sprite.setPosition((this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS)-sprite.getWidth()/2, (this.body.getPosition().y * PlanetScreen.PIXELS_TO_METERS)-sprite.getHeight()/2);
    this.sprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    this.sprite.draw(batch);
  }
}
