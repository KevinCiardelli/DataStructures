/*
 * Deque.java
 *
 * interface for a deque
 *
 * ordered collection with operations at both ends
 */

import java.util.Iterator;

public interface Deque<T> extends Iterable<T>
{
	void addFirst (T item);
	void addLast (T item);
	T removeFirst();
	T removeLast();
	T getFirst();
	T getLast();
	boolean contains (T item);
	int size();
	String toString();
	Iterator<T> iterator();
}
