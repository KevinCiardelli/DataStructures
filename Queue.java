/*
 * Queue.java
 *
 * interface for a queue
 *
 * ordered collection with operations at opposite ends
 *Kevin Ciardelli
 */

import java.util.Iterator;

public interface Queue<T> extends Iterable<T>
{
	void addLast (T item);
	T removeFirst();
	T getFirst();
	boolean contains (T item);
	int size();
	String toString();
	Iterator<T> iterator();
}
