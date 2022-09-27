/*
 * ArrayStack.java
 *
 * ordered collection with operations at one end
 *Kevin Ciardelli
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T>
{
	public static final int DEFAULT_CAPACITY = 10;

	private T [] collection;
	private int size;

	public ArrayStack()
	{
		this(DEFAULT_CAPACITY);
	}

	public ArrayStack(int capacity)
	{
		if (capacity < 0)
			throw new IllegalArgumentException("bag can't be negative size!");

		@SuppressWarnings("unchecked")
		T [] temp  = (T []) new Object[capacity];
		collection = temp;
		temp = null;
		size = 0;
	}

	/*
	 * adds to top of stack
	 */

	public void push(T item)
	{
		checkForNull(item);
		ensureSpace();
		collection[size] = item;
		size++;
	}

	/*
	 * removes from top of stack
	 */

	public T pop()
	{
		if (size == 0)
		{
			return null;
		}

		T removed = collection[size-1];
		collection[size-1] = null;
		size--;
		return removed;
	}

	/*
	 * returns top item
	 */

	public T peek()
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

			if (i < size -1)
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
