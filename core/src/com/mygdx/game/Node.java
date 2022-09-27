package com.mygdx.game;


public class Node<T>
	{
		public Node<T> next;
		public Node<T> previous;
		public T data;
		
		public Node(T data)
		{
			this.data = data;
		}
	}
	
