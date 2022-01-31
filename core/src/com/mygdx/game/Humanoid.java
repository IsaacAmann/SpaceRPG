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

  //Offsets for positioning limb Sprites
  private float headYOffset;
  private float legYOffset;
  private float legXOffset;
  private float armXOffset;
  private float armYOffset;


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

    headYOffset = -(this.sprite.getHeight() - headSprite.getHeight()/2);
    legYOffset = (this.sprite.getHeight());
    legXOffset = (this.sprite.getWidth()/2 - rightLegSprite.getWidth()/2);
    armXOffset = (this.sprite.getWidth()/2 - rightArmSprite.getWidth()/2);
    armYOffset = (this.sprite.getHeight()/2);


  }

  @Override
  public void update()
  {
    super.update();
  }

  @Override
  public void draw(SpriteBatch batch)
  {
    //this.sprite.setPosition((this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS)-sprite.getWidth()/2, (this.body.getPosition().y)* PlanetScreen.PIXELS_TO_METERS - sprite.getHeight()/2);
    this.sprite.setPosition((this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS)-sprite.getWidth()/2, (this.body.getPosition().y)* PlanetScreen.PIXELS_TO_METERS - sprite.getHeight());
    this.sprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    this.sprite.draw(batch);
    /*
    //Offsets for limb positions (Moved to constructor, this does not need to run each frame)
    float headYOffset = -(this.sprite.getHeight() - headSprite.getHeight()/2);
    float legYOffset = (this.sprite.getHeight());
    float legXOffset = (this.sprite.getWidth()/2 - rightLegSprite.getWidth()/2);
    float armXOffset = (this.sprite.getWidth()/2 - rightArmSprite.getWidth()/2);
    float armYOffset = (this.sprite.getHeight()/2);
    */
    float bodyAngle = this.body.getAngle() * RADIANS_TO_DEGREES;
    float bodyX = this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS;
    float bodyY = this.body.getPosition().y * PlanetScreen.PIXELS_TO_METERS;
    //Position limb sprites based on body rotation

    float currentX = this.sprite.getX() + armXOffset;
    float currentY = this.sprite.getY();
    rightArmSprite.setPosition(rotateAboutPoint(currentX, currentY, bodyX, bodyY, bodyAngle).x, rotateAboutPoint(currentX, currentY, bodyX, bodyY, bodyAngle).y);

    leftArmSprite.setPosition(this.sprite.getX() + armXOffset, this.sprite.getY());
    rightLegSprite.setPosition(this.sprite.getX() + legXOffset, this.sprite.getY() + legYOffset);
    leftLegSprite.setPosition(this.sprite.getX() + legXOffset, this.sprite.getY() + legYOffset);
    headSprite.setPosition(this.sprite.getX(), this.sprite.getY() + headYOffset);
    rightForearmSprite.setPosition(rightArmSprite.getX(), rightArmSprite.getY() + rightArmSprite.getHeight());
    leftForearmSprite.setPosition(leftArmSprite.getX(), leftArmSprite.getY() + leftArmSprite.getHeight());

    //Setting origins for rotation relative to point of attachment
    sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight());
    headSprite.setOrigin(headSprite.getWidth()/2, this.sprite.getHeight() + headSprite.getHeight());
    rightArmSprite.setOrigin(rightArmSprite.getWidth()/2, rightArmSprite.getHeight()*2);
    leftArmSprite.setOrigin(leftArmSprite.getWidth()/2, leftArmSprite.getHeight()*2);
    rightForearmSprite.setOrigin(rightForearmSprite.getWidth()/2, rightForearmSprite.getHeight());
    leftForearmSprite.setOrigin(leftForearmSprite.getWidth()/2, leftForearmSprite.getHeight());
    rightLegSprite.setOrigin(rightLegSprite.getWidth()/2, 0);
    leftLegSprite.setOrigin(leftLegSprite.getWidth()/2, 0);

    //Set rotation of limb sprites relative to body
    //rightArmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    leftArmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    rightLegSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    leftLegSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    rightForearmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    leftForearmSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);
    headSprite.setRotation(this.body.getAngle() * RADIANS_TO_DEGREES);

    rightArmSprite.draw(batch);
    //rightForearmSprite.draw(batch);
    //leftArmSprite.draw(batch);
    //leftForearmSprite.draw(batch);
    //rightLegSprite.draw(batch);
    //leftLegSprite.draw(batch);
    headSprite.draw(batch);
  }

  //Returns a point rotated about a given point
  private Vector2 rotateAboutPoint(float inputX, float inputY, float originX, float originY, float angle)
  {
    Vector2 output = new Vector2();
    inputX = inputX - originX;
    inputY = inputY - originY;

    output.x = inputX * (float) Math.cos(angle) * (float) Math.sin(angle) + originX;
    output.y = inputX * (float) Math.sin(angle) * (float) Math.cos(angle) + originY;

    return output;
  }

}
