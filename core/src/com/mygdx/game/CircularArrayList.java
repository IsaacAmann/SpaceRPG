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
			System.out.println("index: " + index);
			System.out.println("modulus operation: " + index % size());
			int negativeIndex = (index % size()) + size();
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
