package Nodal;

public class Node<T> {
   
    Node<T> parent = null;
    T data = null;
    boolean visited=false;

    /**
     * Node constructor
     * @param data
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * Node constructor
     * @param data
     * @param parent
     */
    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
        this.visited=false;
    }

    /**
     *Default Node constructor
     */
    public Node() {
		// TODO Auto-generated constructor stub
	}

    /**
     * getData function
     * @return data
     */
    public T getData() {
        return this.data;
    }

    /**
     * function to set Data
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * function to save root (not used)
     * @return isRoot
     */
    public boolean isRoot() {
        return (this.parent == null);
    }

    /**
     * Function to remove Parent
     */
    public void removeParent() {
        this.parent = null;
    }
    
    /**
     * function to setVisited
     * @param visited
     */
    public void setVisited(Boolean visited)
    {
    	this.visited=visited;
    }
    
    /**
     * function to getVisited
     * @return
     */
    public boolean getVisited()
    {
    	return this.visited;
    }
}