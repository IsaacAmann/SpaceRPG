package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

public class HumanoidV2 extends Entity
{
  /*Extension of entity class that simulates a humanoid with four limbs
    Limbs are only drawn and not simulated with Box2D
    Limb width and height values should come from the sprites
  */
  //Movement fields
  public float jumpForce;
  public float walkForce;
  public float walkSpeed;

  //Game property fields
  public int health;

  //Sprites
  //Body sprite is already inherited from Entity class (field is named sprite)
  public Sprite rightArmSprite;
  public Sprite rightForearmSprite;
  public Sprite leftForearmSprite;
  public Sprite leftArmSprite;
  public Sprite rightLegSprite;
  public Sprite leftLegSprite;
  public Sprite headSprite;

  //Rotation of limbs relative to the body sprites
  public float rightArmRotation;
  public float leftArmRotation;
  public float rightLegRotation;
  public float leftLegRotation;
  public float rightForearmRotation;
  public float leftForearmRotation;

  //Offsets for positioning limb Sprites
  private float headYOffset;
  private float legYOffset;
  private float legXOffset;
  private float armXOffset;
  private float armYOffset;
  
    


  public HumanoidV2(Sprite bodySprite, Sprite armSprite, Sprite forearmSprite, Sprite legSprite, Sprite headSprite, int x, int y, int width, int height)
  {
    super(bodySprite, x, y, width, height);
    rightArmSprite = new Sprite();
    leftArmSprite = new Sprite();
    rightLegSprite = new Sprite();
    leftLegSprite = new Sprite();
    this.headSprite = new Sprite();
    rightForearmSprite = new Sprite();
    leftForearmSprite = new Sprite();
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
    //Requires copies of sprites as the sprite class contains position and rotation data
    
    this.headSprite.set(headSprite);
    rightArmSprite.set(armSprite);
    leftArmSprite.set(armSprite);
    rightLegSprite.set(legSprite);
    leftLegSprite.set(legSprite);
    leftForearmSprite.set(forearmSprite);
    rightForearmSprite.set(forearmSprite);

    headYOffset = -(this.sprite.getHeight() - headSprite.getHeight()/2);
    legYOffset = (this.sprite.getHeight());
    legXOffset = (this.sprite.getWidth()/2 - rightLegSprite.getWidth()/2);

    armXOffset = (this.sprite.getWidth()/2 - rightArmSprite.getWidth()/2);
    //armYOffset = (this.sprite.getHeight()/2);
    armYOffset = 0;
    //Initial position of Limbs
    Vector2 bodyPosition = new Vector2();
    bodyPosition.x = this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS;
    bodyPosition.y = this.body.getPosition().y * PlanetScreen.PIXELS_TO_METERS;
    rightArmSprite.setPosition(bodyPosition.x + armXOffset, bodyPosition.y - armYOffset);

  }

  @Override
  public void update()
  {
    super.update();
  }

  @Override
  public void draw(SpriteBatch batch)
  {
    this.sprite.setPosition((this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS)-sprite.getWidth()/2, (this.body.getPosition().y)* PlanetScreen.PIXELS_TO_METERS - sprite.getHeight());
    this.sprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    this.sprite.draw(batch);
    
    float bodyAngle = this.body.getAngle() * RADIANS_TO_DEGREES;
    float bodyX = this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS;
    float bodyY = this.body.getPosition().y * PlanetScreen.PIXELS_TO_METERS;
    //Position limb sprites based on body rotation

    rightArmSprite.draw(batch);
    //rightForearmSprite.draw(batch);
    leftArmSprite.draw(batch);
    //leftForearmSprite.draw(batch);
    //rightLegSprite.draw(batch);
    //leftLegSprite.draw(batch);
    headSprite.draw(batch);
  }

  
}
