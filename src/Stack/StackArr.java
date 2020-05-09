package Stack;

import java.util.ArrayList;
import java.util.EmptyStackException;

import javafx.scene.Node;

public class StackArr<T> implements Stack<T> {
	private ArrayList<T> list = new ArrayList<>(); //an empty list
	public StackArr() {
		
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public void push(T t) {
		list.add(0, t);
		//list.addFirst(t);
		
	}

	@Override
	public T top() throws EmptyStackException {
		return list.get(0);
		//return list.first();
	}

	@Override
	public T pop() throws EmptyStackException {
		// TODO Auto-generated method stub
		return list.remove(0);
		//return list.removeFirst();
	}
	

}

