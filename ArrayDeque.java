/*
 * ArrayDeque.java
 *
 * array implementation of a queue
 *
 * items shifted when adding/removing
 *
 * Kevin Ciardelli
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>
{
	public static final int DEFAULT_CAPACITY = 10;

	private T [] collection;
	private int size;

	public ArrayDeque()
	{
		this(DEFAULT_CAPACITY);
	}

	public ArrayDeque(int capacity)
	{
		if (capacity < 0)
			throw new IllegalArgumentException("bag can't be negative size!");

		@SuppressWarnings("unchecked")
		T [] temp  = (T []) new Object[capacity];
		collection = temp;
		temp = null;
		size = 0;
	}


	public void addFirst(T item){


		checkForNull(item);
		ensureSpace();
		for(int i=0; i<size; i++){
			collection[i+1]=collection[i];
		}
		collection[0]=item;
		size++;
	}

	public void addLast(T item)
	{
		checkForNull(item);
		ensureSpace();
		collection[size] = item;
		size++;
	}

	public T removeFirst()
	{
		if(size==0)
		{
			return null;
		}
		T removed= collection[0];
		for(int i=0; i<size; i++)
		{
			collection[i] = collection[i+1];
		}
		size--;
		return removed;
	}

	public T removeLast()
	{
		if (size == 0)
		{
			return null;
		}

		T removed = collection[size-1];
		size--;
		return removed;
	}

	public T getFirst()
	{
		if (size == 0)
		{
			return null;
		}

		return collection[0];
	}

	public T getLast()
	{
		if (size == 0)
		{
			return null;
		}

		return collection[size-1];
	}

	public boolean contains (T item)
	{
		for (int i = 0; i < size; i++)
		{
			if (item.equals(collection[i]))
			{
				return true;
			}
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

		for (int i = 0; i < size; i++)
		{
			s+= collection[i];

			if (i < size-1)
			{
				s+= ", ";
			}
		}

		return s + "]";
	}

	public Iterator<T> iterator()
	{
		return new ArrayIterator();
	}

	private void checkForNull(T item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("item can't be null");
		}
	}

	private void ensureSpace()
	{
		if (size == collection.length)
		{
			@SuppressWarnings("unchecked")
			T [] larger  = (T []) new Object [size * 2];

			for (int i = 0; i < size; i++)
			{
				larger[i] = collection[i];
			}

			collection = larger;
			larger = null;
		}
	}

	private class ArrayIterator implements Iterator<T>
	{
		private int count;

		public ArrayIterator()
		{
			count = 0;
		}

		public boolean hasNext()
		{
			return count < size;
		}

		public T next()
		{
			if (hasNext())
			{
				return collection[count++];
			}

			else
			{
				throw new NoSuchElementException("off end of list");
			}
		}

		public void remove()
		{
			throw new UnsupportedOperationException("remove not implemented");
		}
	}
}
