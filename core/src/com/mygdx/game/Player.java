package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;

public class Player extends Entity
{
  private static float JUMP_FORCE = 5f;
  private static float WALK_FORCE = 1f;

  public static boolean canJump = false;

  public Player(int x, int y, int width, int height)
  {
    super(PlanetScreen.testSprite, x, y, width, height);
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
  }
}
