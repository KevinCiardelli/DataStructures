/*
 * HashMap.java
 *
 * implements a map using a hash table
 *
 * Kevin Ciardelli PS9 4/20/22
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map<K, V>
{
	private class Node
	{
		private K key;
		private V value;
		private Node next;

		public Node(K key, V value)
		{
			this.key = key;
			this.value = value;
			next = null;
		}

		public String toString()
		{
			return key + "=" + value;
		}
	}

	public static final int DEFAULT_CAPACITY = 19;
	public static final double LOAD_FACTOR = 1.0;

	private Node [] collection;
	private int size;

	public HashMap()
	{
		this(DEFAULT_CAPACITY);
	}

	public HashMap (int capacity)
	{
		@SuppressWarnings("unchecked")
		Node [] temp = (Node[]) new HashMap.Node[capacity];
		collection = temp;
		temp = null;
		size = 0;
	}

	public V add (K key, V value)
	{
		checkForNull(key, value);
		int index = getHashCode(key);
		Node current = collection[index];

		while (current != null)
		{
			if (key.equals(current.key))
			{
				V removed = current.value;
				current.value = value;
				return removed;
			}

			current = current.next;
		}

		ensureSpace();
		Node newest = new Node(key, value);
		newest.next = collection[index];
		collection[index] = newest;
		size++;
		return null;
	}

	public V set (K key, V value)
	{
		checkForNull(key, value);
		ensureSpace();
		int index = getHashCode(key);
		Node current = collection[index];

		while (current != null)
		{
			if (key.equals(current.key))
			{
				V removed = current.value;
				current.value = value;
				return removed;
			}

			current = current.next;
		}

		return null;
	}

	public V remove (K key)
	{
		checkForNull(key);
		int index = getHashCode(key);

		Node current = collection[index];
		Node replace = collection[index];

		while(current != null){
			if(key.equals(current.key))
			{
				V removed = current.value;
				current.key=replace.key;
				current.value=replace.value;
				collection[index]= collection[index].next;
				size--;
				return removed;
			}
			current = current.next;
		}
		return null;
	}

	public boolean contains(K key)
	{
		checkForNull(key);
		int index = getHashCode(key);
		Node current = collection[index];

		while (current != null)
		{
			if (key.equals(current.key))
			{
				return true;
			}

			current = current.next;
		}

		return false;
	}

	public V get(K key)
	{
		checkForNull(key);
		int index = getHashCode(key);
		Node current = collection[index];

		while (current != null)
		{
			if (key.equals(current.key))
			{
				return current.value;
			}

			current = current.next;
		}

		return null;
	}

	public int size()
	{
		return size;
	}

	public String toString()
	{
		String s = "";

		for (int i = 0; i < collection.length; i++)
		{
			Node current = collection[i];
			String n = String.format("%2d", i);
			if (current == null) s+= n + "\t" + " " + "\n";

			else
			{
				s+= n + "\t" + current + " ";

				while (current.next != null)
				{
					current = current.next;
					s+= current + " ";
				}

				s+= "\n";
			}
		}

		return s;
	}

	public Iterator<K> iterator()
	{
        return new HashIterator();
    }

	private void ensureSpace()
	{
		if (size/collection.length * 1. >= LOAD_FACTOR)
		{
			Node [] original = collection;
			@SuppressWarnings("unchecked")
			Node [] larger = (Node[]) new HashMap.Node[nextPrime(size*2)];
			collection = larger;

			for (int i = 0; i < original.length; i++)
			{
				Node current = original[i];
				while (current!= null)
				{
					K key = current.key;
					V value = current.value;
					int index = getHashCode(key);
					Node newest = new Node (key, value);
					newest.next = collection[index];
					collection [index] = newest;
					current = current.next;
				}
			}

			original = null;
			larger = null;
		}
	}

	private static int nextPrime (int n)
	{
		boolean prime = false;

		while (!prime)
		{
			n++;
			prime = true;
			for (int i = 2; i < n; i++)
			{
				if (n % i == 0)
				{
					prime = false;
					break;
				}
			}
		}

		return n;
	}

	private void checkForNull (K key, V value)
	{
		if (key == null || value == null)
		{
			throw new IllegalArgumentException("Key/value can't be null");
		}
	}

	private void checkForNull (K key)
	{
		if (key == null)
		{
			throw new IllegalArgumentException("Key can't be null");
		}
	}

	private int getHashCode (K key)
	{
		int index = key.hashCode() % collection.length;

		if (index < 0)
		{
			index += collection.length;
		}

		return index;
	}

	private class HashIterator implements Iterator<K>
	{
		private Node position;
		private int bucket;
		private int count;

		public HashIterator()
		{
			position = collection[0];
			bucket = 0;
			count = 0;
		}

		public boolean hasNext()
		{
			return count < size;
		}

		public K next()
		{
			if (hasNext())
			{
				while (position == null)
				{
					position = collection[++bucket];
				}

				K removed = position.key;
				position = position.next;
				count++;
				return removed;
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
