package Stack;

import java.util.EmptyStackException;

/**
 *a collection of objects that are inserted and removed according to the LIFO. 
 *Although similar in purpose, this interface differs from java.util.stack.
 * @param <T>
 * @param <T>
 */
public interface Stack<T> {

	/**
	 * returns the number of elements in the stack.
	 * @return the number of elements in the stack.
	 */
	public int size();
	/**
	 * test whether the stack is empty.
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty();
	/**
	 * inserts an element at the top of the stack.
	 * @param t - the element to be inserted.
	 */
	public void push(T t);
	/**
	 * returns, but does not remove the element on top of the stack
	 * @return the top element in the stack
	 */
	public T top() throws EmptyStackException;
	/**
	 * Removes and returns the top element in the stack
	 * @return the element removed from the stack (null if empty)
	 */
	public T pop() throws EmptyStackException;

}
