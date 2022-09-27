/*
 * LinkedSet.java
 *
 * unordered collection with no duplicates allowed
 *
 * Kevin Ciardelli
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedSet<T> implements Set<T>
{
	/*
	 * inner class to represent node
	 */

	private class Node
	{
		/* instance variables */
		private T data;
		private Node next;

		/* constructor */
		public Node(T item)
		{
			data = item;
			next = null;
		}
	}

	/* instance variables */
	private Node head;
	private int size;

	/*
	 * constructor
	 */

	public LinkedSet()
	{
		head = null;
		size = 0;
	}

	/* start of code to modify */

	/*
	 * adds item to collection
	 *
	 * returns true if item added, false if already present
	 */

	public boolean add(T item)
	{
		boolean check = true;
		if (item == null)
		{
			throw new IllegalArgumentException("null not a possible value!");
		}
		if(contains(item)==true){
			check = false;
		}
		else{
			Node newNode = new Node (item);
			newNode.next = head;
			head = newNode;
			size++;
		}
		return check;
	}

	/* end of code to modify */

	/*
	 * removes specified item
	 *
	 * returns true if removed, false if not found
	 */

	public boolean remove(T item)
	{
		Node current = head;

		for (int i = 0; i < size; i++)
		{
			if (item.equals(current.data))
			{
				current.data = head.data;
				head = head.next;
				size--;
				return true;
			}

			current = current.next;
		}

		return false;
	}

	/* start of code to copy */

	/*
	 * removes random item and returns it
	 */

	public T removeRandom()
	{
		T item = getRandom();
		remove(item);
		return item;
	}

	/* end of code to copy */

	/*
	 * returns random item
	 */

	public T getRandom()
	{
		if (size == 0)
		{
			return null;
		}

		int random = (int)(Math.random()*size);
		Node current = head;

		for (int i = 0; i < random; i++)
		{
			current = current.next;
		}

		return current.data;
	}

	/*
	 * returns true if item is in collection, false otherwise
	 */

	public boolean contains(T item)
	{
		Node current = head;

		while (current != null)
		{
			if (item.equals(current.data))
			{
				return true;
			}

			current = current.next;
		}

		return false;
	}

	/*
	 * returns size of collection
	 */

	public int size()
	{
		return size;
	}

	/*
	 * returns string representation of contents of collection
	 */

	public String toString()
	{
		String s = "[";
		Node current = head;

		while (current != null)
		{
			s += current.data;

			if (current.next != null)
			{
				s += ", ";
			}

			current = current.next;
		}

		return s + "]";
	}

	/*
	 * returns iterator for traversing collection
	 */

	public Iterator<T> iterator()
	{
		return new LinkedIterator();
	}

	/* private helper method */

	/*
 	 * throws exception if item is null
	 */

	public void checkForNull(T item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null not a possible value!");
		}
	}

	/* iterator class */
	private class LinkedIterator implements Iterator<T>
	{
		/* instance variables */
		private Node position;

		/* constructor */
		public LinkedIterator()
		{
			position = head;
		}

		/*
		 * returns true if another item exists
		 */

		public boolean hasNext()
		{
			return position!= null;
		}

		/*
		 * returns next item and moves iterator forward
		 */

		public T next()
		{
			if (hasNext())
			{
				T item = position.data;
				position = position.next;
				return item;
			}

			else
			{
				throw new NoSuchElementException ("Off end of list");
			}
		}

		/*
		 * removes next item
		 */

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
