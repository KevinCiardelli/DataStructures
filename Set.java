/*
 * interface for a set
 *
 * unordered collection with no duplicates allowed
 */

import java.util.Iterator;

public interface Set<T> extends Iterable<T> 
{
	/*
	 * adds item to collection
	 *
	 * returns true if item added, false if already present
	 */

	boolean add (T item);

	boolean remove (T item);
	T removeRandom();
	T getRandom();
	boolean contains (T item);
	int size();
	String toString();
	Iterator<T> iterator();
}
