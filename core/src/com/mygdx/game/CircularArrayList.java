package com.mygdx.game;

import java.util.ArrayList;

public class CircularArrayList<T> extends ArrayList<T>
{
	@Override
	public T get(int index)
	{
		if(index >= 0)
		{
			return super.get(index % size());
		}
		else
		{
			int negativeIndex = (index % size()) + size();
			System.out.println("index: " + index + " Actual index: " + negativeIndex);
			System.out.println("modulus operation: " + index % size());
						if(index % size() == 0)
			{
				
				return super.get(negativeIndex - 1);
			}
			else
			{
				return super.get(negativeIndex);
			}
			//return super.get((index % size()) + size());
		}
	}
}
