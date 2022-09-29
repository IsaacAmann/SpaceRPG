package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.lang.Math;

public class Player extends Entity
{
	private static float JUMP_FORCE = 25f;
	private static float WALK_FORCE = 1f;
	private static float WALK_SPEED = 5f;
	private static boolean isWalkingRight = true;
	public static boolean canJump = false;
	public int startingHealth;
	public int health;

	public Player(Sprite sprite, int x, int y, int width, int height, int health)
	{
		super(sprite, x, y, width, height);
		startingHealth = health;
		this.health = health;
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
			//check if player is knocked over by checking the rotation of the box2d body. Also only allow the player to get up if speed is reduced
			if((this.body.getAngle() > 1.4 || this.body.getAngle() < -1.4)  && Math.abs(this.body.getLinearVelocity().x) < 1)
			{
				//Might want to fix roation later and unfix it when player is damaged / hit with large force
				body.setTransform(body.getPosition(), 0);
			}
			else
			{
				//Only allow jumping if the player is upright
				if(!(this.body.getAngle() > 1.4 || this.body.getAngle() < -1.4))
				{
					body.applyForceToCenter(0,-JUMP_FORCE,true);
					canJump = false;
				}
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
