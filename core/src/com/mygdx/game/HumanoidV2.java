package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.lang.Math;

public class HumanoidV2 extends Entity
{
  //Movement fields
  public float jumpForce;
  public float walkForce;
  public float walkSpeed;

  //Game property fields
  public int health;
  public int startingHealth;

  //Sprites
  //Body sprite is already inherited from Entity class (field is named sprite)

  public HumanoidV2(TextureRegion textureRegion, float x, float y, int width, int height, Sector currentSector)
  {
    super(textureRegion, x, y, width, height, currentSector);
   
    //Requires copies of sprites as the sprite class contains position and rotation data
    
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
	if(this.body != null)
	{
		float bodyAngle = this.body.getAngle() * RADIANS_TO_DEGREES;
		float bodyX = this.body.getPosition().x * PlanetScreen.PIXELS_TO_METERS;
		float bodyY = this.body.getPosition().y * PlanetScreen.PIXELS_TO_METERS;
	}
  }

  
}
