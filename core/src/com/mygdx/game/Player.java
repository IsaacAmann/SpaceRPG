package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Entity
{
  private static float JUMP_FORCE = 25f;
  private static float WALK_FORCE = 1f;
  private static float WALK_SPEED = 5f;

  public static boolean canJump = false;

  public Player(Sprite sprite, int x, int y, int width, int height)
  {
    super(sprite, x, y, width, height);
  }

  @Override
  public void update()
  {
    super.update();
    moveFromKeyboard();
  }

  private void moveFromKeyboard()
  {
    if(PlanetScreen.playerInput.up && canJump)
    {
      body.applyForceToCenter(0,-JUMP_FORCE,true);
      canJump = false;
    }
    if(PlanetScreen.playerInput.right && body.getLinearVelocity().x <= WALK_SPEED)
    {
      body.applyForceToCenter(WALK_FORCE,0,true);
    }
    if(PlanetScreen.playerInput.left && body.getLinearVelocity().x >= -WALK_SPEED)
    {
      body.applyForceToCenter(-WALK_FORCE,0,true);
    }
  }
}
