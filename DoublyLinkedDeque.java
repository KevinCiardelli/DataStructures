/*
 * DoublyLinkedDeque.java
 *
 * linked implementation of a deque
 *
 * uses previous pointer to access penultimate node
 *
 * Kevin Ciardelli
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedDeque<T> implements Deque<T>
{
	private class Node
	{
		private T data;
		private Node next;
		private Node prev;

		public Node(T item)
		{
			data = item;
			next = null;
			prev = null;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public DoublyLinkedDeque()
	{
		head = null;
		tail = null;
		size = 0;
	}

	public void addFirst(T item)
	{
		checkForNull(item);
		Node newNode = new Node(item);
		if (size == 0)
		{
			head= newNode;
			tail = newNode;
		}

		else
		{
			newNode.next= head;
			head.prev = newNode;
			head= newNode;
		}
		size++;
	}

	/* copy from queue, and update to connect new node to lefthand neighbor */

	public void addLast(T item)
	{
		checkForNull(item);
		Node newNode= new Node(item);
		if(size==0)
		{
			head=newNode;
			tail=newNode;
		}
		else
		{
			newNode.prev=tail;
			tail.next= newNode;
			tail=newNode;
		}
		size++;

	}

	public T removeFirst()
	{
		if (size == 0)
		{
			return null;
		}

		T removed = head.data;
		head = head.next;

		if (size == 1)
		{
			tail = null;
		}

		else
		{
			head.prev = null;
		}

		size--;
		return removed;
	}

	/* to be implemented */

	public T removeLast()
	{
		if (size == 0)
		{
			return null;
		}
		T removed = tail.data;
		if (size==1){
			head=null;
			tail=null;
			size--;
			return removed;
		}

		tail= tail.prev;
		tail.next=null;
		size--;
		return removed;
	}

	public T getFirst()
	{
		if (size == 0)
		{
			return null;
		}

		return head.data;
	}

	public T getLast()
	{
		if (size == 0)
		{
			return null;
		}

		return tail.data;
	}

	public boolean contains (T item)
	{
		Node current = head;

		while (current!= null)
		{
			if (item.equals(current.data))
			{
				return true;
			}

			current = current.next;
		}

		return false;
	}

	public int size()
	{
		return size;
	}

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

	public Iterator<T> iterator()
	{
		return new LinkedIterator();
	}

	private void checkForNull(T item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null not a possible value!");
		}
	}

	private class LinkedIterator implements Iterator<T>
	{
		private Node position;

		public LinkedIterator()
		{
			position = head;
		}

		public boolean hasNext()
		{
			return position!= null;
		}

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

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
