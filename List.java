/*
 * List.java
 *
 * interface for a list
 *
 * ordered collection with operations anywhere
 */

import java.util.Iterator;

public interface List<T> extends Iterable<T>
{
	void add (T item);
	boolean remove (T item);
	boolean contains (T item);
	int size();
	String toString();
	Iterator<T> iterator();

	// list-specific methods
	void add (int index, T item);
	T set (int index, T item);
	T get (int index);
	T remove (int index);
	int indexOf (T item);
}
