/*
 * ArrayList.java
 *
 * ordered collection with operations at any position
 *
 * Kevin Ciardelli 4/11/22
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T>
{
	public static final int DEFAULT_CAPACITY = 10;

	private T [] collection;
	private int size;

	/*
	 * no-argument constructor
	 *
	 * size set to default value
	 */

	public ArrayList()
	{
		this(DEFAULT_CAPACITY);
	}

	/*
	 * argument constructor
	 *
	 * size specified by user
	 */

	public ArrayList(int capacity)
	{
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		collection = temp;
		temp = null;
		size = 0;
	}

	/*
	 * adds item to list
	 */

	public void add (T item)
	{
		checkForNull(item);
		ensureSpace();
		collection[size] = item;
		size++;
	}

	/*
	 * removes item from list
	 *
	 * returns true if item found in list
	 */

	public boolean remove (T item)
	{
		if (contains(item)==true)
		{
			for(int i=0; i< size; i++)
			{
				if(collection[i]==item)
				{
					collection[i]=collection[size-1];
					size--;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
	 * returns true if item is in list
	 */

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

	/* list-specific methods */

	/*
	 * adds item at specified index
	 */

	public void add (int index, T item)
	{
		checkForNull(item);
		checkIndex(index);
		ensureSpace();

		for (int i = size; i > index; i--)
		{
			collection[i] = collection[i-1];
		}

		collection[index] = item;
		size++;
	}

	/*
	 * replaces item at specified index with new value
	 *
	 * returns original value
	 */

	public T set (int index, T item)
	{
		checkForNull(item);
		checkIndex(index);
		T removed = collection[index];
		collection[index] = item;
		return removed;
	}

	/*
	 * removes item at specified index
	 *
	 * returns removed item
	 */

	public T remove (int index)
	{
		T item= get(index);
		if (contains(item)==true)
		{
			for(int i=0; i< size; i++)
			{
				if(collection[i]==item)
				{
					collection[i]=collection[size-1];
					size--;
				}
			}
		}
		return item;
	}

	/*
	 * returns item at specified index
	 */

	public T get (int index)
	{
		checkIndex(index);
		return collection[index];
	}

	/*
	 * returns index of specified item
	 *
	 * returns -1 if item not in list
	 */

	public int indexOf (T item)
	{
		for (int i = 0; i < size; i++)
		{
			if (item.equals(collection[i]))
			{
				return i;
			}
		}

		return -1;
	}

	/*
	 * returns size of list
	 */

	public int size()
	{
		return size;
	}

	/*
	 * returns string representation of list
	 */

	public String toString()
	{
		String s = "[";

		for (int i = 0; i < size; i++)
		{
			s+= collection[i];

			if (i!= size-1)
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

	/* helper methods */

	/*
	 * checks to make sure item isn't null
	 */

	private void checkForNull (T item)
	{
		if (item == null)
		{
			throw new IllegalArgumentException("null not a possible value!");
		}
	}

	/*
	 * checks to make sure index falls within list
	 */

	private void checkIndex (int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("index outside of list!");
		}
	}

	/*
	 * doubles size of array if full
	 */

	private void ensureSpace()
	{
		if (size == collection.length)
		{
			@SuppressWarnings("unchecked")
			T [] larger = (T[]) new Object[size*2];

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
