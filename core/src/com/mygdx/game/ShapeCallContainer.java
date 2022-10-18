package com.mygdx.game;
import java.util.LinkedList;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;



//Used for queueing up ShapeRenderer calls so that they can be called in order after SpriteBatch has ended
public class ShapeCallContainer
{
	//only including cases for rectangle calls, thats likely the only one I will use
	private class ShapeCall 
	{
		public String shapeType;
		public float x;
		public float y;
		public float width;
		public float height;
		public Color color;
		
		public ShapeCall(float x, float y, float width, float height, Color color)
		{
			this.shapeType = shapeType;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;	
			this.color = color;
		}
		public void draw(ShapeRenderer shapeRenderer)
		{
			shapeRenderer.setColor(this.color);
			shapeRenderer.rect(x, y, width, height);
		}
	}
	
	private LinkedList<ShapeCall> queue;
	public ShapeCallContainer()
	{
		queue =  new LinkedList<ShapeCall>();
		
	}
	
	public void addShapeCall(float x, float y, float width, float height, Color color)
	{
		ShapeCall tempShapeCall = new ShapeCall(x, y, width, height, color);
		queue.add(tempShapeCall);
	}
	
	public void execute(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		
		ShapeCall tempShapeCall = queue.poll();
		while(tempShapeCall != null)
		{
			tempShapeCall.draw(shapeRenderer);
			tempShapeCall = queue.poll();
		}
		
		shapeRenderer.end();
	}
}
