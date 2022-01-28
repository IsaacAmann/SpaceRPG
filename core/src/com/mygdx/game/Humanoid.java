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
  //Extension of entity class that simulates a humanoid with four limbs

  //Movement fields
  public float jumpForce;
  public float walkForce;
  public float walkSpeed;

  //Game property fields
  public int health;

  //Sprites
  //Body sprite is already inherited from Entity class (field is named sprite)
  public Sprite rightArmSprite;
  public Sprite leftArmSprite;
  public Sprite rightLegSprite;
  public Sprite leftLegSprite;


  public Humanoid(Sprite bodySprite, Sprite armSprite, Sprite legSprite, int x, int y, int width, int height)
  {
    super(bodySprite, x, y, width, height);
    //Requires copies of sprites as the sprite class contains position and rotation data
    rightArmSprite = armSprite;
    leftArmSprite.set(armSprite);
    rightLegSprite = legSprite;
    leftLegSprite.set(legSprite);

  }

  @Override
  public void update()
  {
    super.update();
  }

  @Override
  public void draw(SpriteBatch batch)
  {
    super.draw(batch);
    rightArmSprite.setPosition(this.sprite.getX(), this.sprite.getY());
    leftArmSprite.setPosition(this.sprite.getX(), this.sprite.getY());
    rightLegSprite.setPosition(this.sprite.getX(), this.sprite.getY());
    leftLegSprite.setPosition(this.sprite.getX(), this.sprite.getY());
  }

}
