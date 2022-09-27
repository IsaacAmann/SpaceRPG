package com.mygdx.game;


public class CircularLinkedList<T>
{
	public Node<T> head;
	
	//Creates the circular array from an array of elements
	public void createFromArray(T[] dataArray)
	{
		head = new Node<T>(dataArray[0]);
		
		Node<T> currentNode = head;
		Node<T> newNode = null;
		//Treat all but last element like normal linked list
		for(int i = 1; i < dataArray.length - 1; i++)
		{
			newNode = new Node<T>(dataArray[i]);
			newNode.previous = currentNode;
			//Catches null pointer exception when creating the first element
			if(currentNode != null)
				currentNode.next = newNode;
			currentNode = newNode;
			
		}
		//Link last node back to head
		newNode = new Node<T>(dataArray[dataArray.length - 1]);
		newNode.previous = currentNode;
		newNode.next = head;
		head.previous = newNode;
	}
}
