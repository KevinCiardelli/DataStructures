/*
 * array implementation of a map
 *
 * data stored by key, with no duplicate keys allowed
 *
 * --your name--
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayMap<K, V> implements Map<K, V>
{
	private class Pair
	{
		private K key;
		private V value;

		public Pair(K key, V value)
		{
			this.key = key;
			this.value = value;
		}

		public String toString()
		{
			return key + "=" + value;
		}
	}

	public static final int DEFAULT_CAPACITY = 10;

	private Pair [] collection;
	private int size;

	public ArrayMap()
	{
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public ArrayMap (int capacity)
	{
		collection = (Pair []) new ArrayMap.Pair[capacity];
		size = 0;
	}

	/*
	 * if key in map, replaces old value with new and returns old value
	 * otherwise adds key value pair and returns null
 	 */

	public V add (K key, V value)
	{
		checkForNull(key, value);

		for (int i = 0; i < size; i++)
		{
			if (key.equals(collection[i].key))
			{
				V oldValue = collection[i].value;
				collection[i].value = value;
				return oldValue;
			}
		}

		ensureSpace();
		collection[size] = new Pair(key, value);
		size++;
		return null;
	}

	/*
	 * if key in map, replaces old value with new and returns old value
	 * otherwise returns null
 	 */

	public V set (K key, V value)
	{
		checkForNull(key, value);

		for (int i = 0; i < size; i++)
		{
			if (key.equals(collection[i].key))
			{
				V oldValue = collection[i].value;
				collection[i].value = value;
				return oldValue;
			}
		}

		return null;
	}

	/* start of added code */

	/*
	 *  removes key value pair from map and returns value
	 *  if key not found, returns null
 	 */

	public V remove (K key)
	{
		if(get(key)!= null) /*grabs value corresponding to key*/
		{
			V res= get(key);

			for (int i = 0; i < size; i++)
			{
				if (key.equals(collection[i].key))
				{
					collection[i] = collection[size-1];
					collection[size-1] = null;
					size--;
				}
			}
			return res;
		}

		else
		{
			return null;
		}
	}

	/* end of added code */

	/*
	 *  returns value stored for key
	 *  if key not found, returns null
 	 */

	public V get (K key)
	{
		checkForNull(key);

		for (int i = 0; i < size; i++)
		{
			if (key.equals(collection[i].key))
			{
				return collection[i].value;
			}
		}

		return null;
	}

	/*
	 * returns true if key is in map
 	 */

	public boolean contains(K key)
	{
		checkForNull(key);

		for (int i = 0; i < size; i++)
		{
			if (key.equals(collection[i].key))
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
		if (size == 0)
		{
			return "{}";
		}

		String s = "{";

		for (int i = 0; i < size; i++)
		{
			s+= collection[i];
			if (i < size-1) s += ", ";
		}

		return s + "}";
	}

	/*
	 * returns iterator for traversing collection
	 */

	public Iterator<K> iterator()
	{
		return new ArrayIterator();
	}

	/* private helper methods */

	/*
	 *  checks whether key or value is null and if so, throws exception
	 */

	private void checkForNull (K key, V value)
	{
		if (key == null || value == null)
		{
			throw new IllegalArgumentException("Key/value can't be null");
		}
	}

	/*
	 *  checks whether key is null and if so, throws exception
	 */

	private void checkForNull (K key)
	{
		if (key == null)
		{
			throw new IllegalArgumentException("Key can't be null");
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
			Pair [] larger = (Pair []) new ArrayMap.Pair[size*2];

			for (int i = 0; i < size; i++)
			{
				larger[i] = collection[i];
			}

			collection = larger;
			larger = null;
		}
	}

	/* iterator class */
	private class ArrayIterator implements Iterator<K>
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

		public K next()
		{
			if (hasNext())
			{
				return collection[count++].key;
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
