package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Entity
{
	private static float JUMP_FORCE = 25f;
	private static float WALK_FORCE = 1f;
	private static float WALK_SPEED = 5f;
	private static boolean isWalkingRight = true;
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
			System.out.println("Rotation: " + body.getAngle());
			if(this.body.getAngle() > 1.4 || this.body.getAngle() < -1.4)
			{
				
				body.setTransform(body.getPosition(), 0);
			}
			else
			{
				body.applyForceToCenter(0,-JUMP_FORCE,true);
				canJump = false;
			}
		}
		if(PlanetScreen.playerInput.right && body.getLinearVelocity().x <= WALK_SPEED)
		{
			body.applyForceToCenter(WALK_FORCE,0,true);
			if(isWalkingRight != true)
				super.sprite.flip(true,false);
			isWalkingRight = true;
		}
		if(PlanetScreen.playerInput.left && body.getLinearVelocity().x >= -WALK_SPEED)
		{
			body.applyForceToCenter(-WALK_FORCE,0,true);
			if(isWalkingRight == true)
				super.sprite.flip(true,false);
			isWalkingRight = false;
		}
	}
	public double getBodyX()
	{
		return body.getPosition().x;
	}
	
	public double getBodyY()
	{
		return body.getPosition().y;
	}
}
