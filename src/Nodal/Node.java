package Nodal;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    //private List<Node<T>> children = new ArrayList<Node<T>>();
    Node<T> parent = null;
    T data = null;
    boolean visited=false;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
        this.visited=false;
    }

    public Node() {
		// TODO Auto-generated constructor stub
	}


    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public void removeParent() {
        this.parent = null;
    }
    public void setVisited(Boolean visited)
    {
    	this.visited=visited;
    }
    public boolean getVisited()
    {
    	return this.visited;
    }
}