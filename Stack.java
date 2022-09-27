/*
 * Stack.java
 *
 * interface for a stack
 *
 * ordered collection with operations at one end
 *Kevin Ciardelli
 */

import java.util.Iterator;

public interface Stack<T> extends Iterable<T>
{
	void push (T item);
	T pop();
	T peek();
	int size();
	boolean contains (T item);
	String toString();
	Iterator<T> iterator();
}
