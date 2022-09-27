/*
 * ArraySet.java
 *
 * unordered collection with no duplicates allowed
 *
 * Kevin Ciardelli
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArraySet<T> implements Set<T>
{
	public static final int DEFAULT_CAPACITY = 10;

	/* instance variables */
	private T [] collection;
	private int size;

	/* constructors */
	public ArraySet()
	{
		this(DEFAULT_CAPACITY);
	}

	public ArraySet(int capacity)
	{
		@SuppressWarnings("unchecked")
		T [] temp  = (T []) new Object[capacity];
		collection = temp;
		temp = null;
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
		checkForNull(item);
		if(contains(item)==true){
			check = false;
		}
		else
		{
			ensureSpace();
			collection[size] = item;
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
		for (int i = 0; i < size; i++)
		{
			if (item.equals(collection[i]))
			{
				collection[i] = collection[size-1];
				collection[size-1] = null;
				size--;
				return true;
			}
		}

		return false;
	}

	/* start of code to copy */

	/*
	 * removes and returns random item
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
	public T get(int i)
	{
		return collection[i];
	}
	public T getRandom()
	{
		if (size == 0)
		{
			return null;
		}

		return collection[(int)(Math.random()*size)];
	}

	/*
	 * returns true if item is in collection
	 */

	public boolean contains(T item)
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

	/*
	 * returns iterator for traversing collection
	 */

	public Iterator<T> iterator()
	{
		return new ArrayIterator();
	}

	/* private helper methods */

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

	/*
	 * doubles size of array if at capacity
	 */

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

	/* iterator class */
	private class ArrayIterator implements Iterator<T>
	{
		/* instance variable */
		private int count;

		/*
		 * constructor
		 */

		public ArrayIterator()
		{
			count = 0;
		}

		/*
		 * returns true if another item exists
		 */

		public boolean hasNext()
		{
			return count < size;
		}

		/*
		 * returns next item and moves iterator forward
		 */

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

		/*
		 * removes next item
		 */

		public void remove()
		{
			throw new UnsupportedOperationException("remove not implemented");
		}
	}
}
