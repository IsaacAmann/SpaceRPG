package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

public class Humanoid extends Entity
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


  public Humanoid(Sprite bodySprite, Sprite armSprite, Sprite forearmSprite, Sprite legSprite, Sprite headSprite, int x, int y, int width, int height)
  {
    super(bodySprite, x, y, width, height);
    rightArmSprite = new Sprite();
    leftArmSprite = new Sprite();
    rightLegSprite = new Sprite();
    leftLegSprite = new Sprite();
    this.headSprite = new Sprite();
    rightForearmSprite = new Sprite();
    leftForearmSprite = new Sprite();
    //Requires copies of sprites as the sprite class contains position and rotation data
    this.headSprite.set(headSprite);
    rightArmSprite.set(armSprite);
    leftArmSprite.set(armSprite);
    rightLegSprite.set(legSprite);
    leftLegSprite.set(legSprite);
    leftForearmSprite.set(forearmSprite);
    rightForearmSprite.set(forearmSprite);
    //Setting origins of sprites for rotation


  }

  @Override
  public void update()
  {
    super.update();
  }

  @Override
  public void draw(SpriteBatch batch)
  {
    this.sprite.setPosition((this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS)-sprite.getWidth()/2, ((this.body.getPosition().y)* PlanetScreen.PIXELS_TO_METERS - (this.height/4))-sprite.getHeight()/2);
    this.sprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    this.sprite.draw(batch);
    //Offsets for limb positions
    float headYOffset = -(this.sprite.getHeight());
    float legYOffset = (this.sprite.getHeight());
    float legXOffset = (this.sprite.getWidth()/2);
    //Setting origins of limbs for rotation



    //Position limb sprites
    rightArmSprite.setPosition(this.sprite.getX(), this.sprite.getY());
    leftArmSprite.setPosition(this.sprite.getX(), this.sprite.getY());
    rightLegSprite.setPosition(this.sprite.getX() + legXOffset, this.sprite.getY() + legYOffset);
    leftLegSprite.setPosition(this.sprite.getX() + legXOffset, this.sprite.getY() + legYOffset);
    headSprite.setPosition(this.sprite.getX(), this.sprite.getY() + headYOffset);
    rightForearmSprite.setPosition(this.sprite.getX(), this.sprite.getY());
    leftForearmSprite.setPosition(this.sprite.getX(), this.sprite.getY());

    headSprite.setOrigin(headSprite.getWidth()/2, this.sprite.getHeight());
    rightArmSprite.setOrigin(rightArmSprite.getWidth()/2, -rightArmSprite.getHeight());

    //Set rotation of limb sprites
    rightArmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    leftArmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    rightLegSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    leftLegSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    rightForearmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    leftForearmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    headSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);

    rightArmSprite.draw(batch);
    rightForearmSprite.draw(batch);
    leftArmSprite.draw(batch);
    leftForearmSprite.draw(batch);
    rightLegSprite.draw(batch);
    leftLegSprite.draw(batch);
    headSprite.draw(batch);
  }

}
