package Nodal;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import Object.Building;


public class DFSNode<T extends Comparable<T>> {

	private Vertex<T> root;
	private Vertex<T> end;
	public DFSNode (Vertex<T> root, Vertex<T> end)
	{
		this.root=root;
		this.end=end;
	}
	
	public DFSNode()
	{
		root=null;
		end=null;
	}
	public Stack<T> nodeAlgorythm (Vertex<T> root, Vertex<T> end)
	{
		List<Node<T>> nodeList = new ArrayList<Node<T>>();
		ArrayList<Edge<T>> edge = null;
		Node<T> rootNode = new Node<T>();

		rootNode.parent = null;
		rootNode.data = root.getValue();
		rootNode.visited=true;
		System.out.println("this is the root node data "+rootNode.data +"\n");
		nodeList.add(rootNode);

		int counter = 0;
		do
		{
			System.out.println("--------------------------this is the start--------------------------");
			edge= new ArrayList<Edge<T>>();
			Vertex<T> tempVert = null;
			double cost = Double.MAX_VALUE;
			
			//loop through edges and find the min vertex to take
			for(Edge<T> e : root.getEdges())
			{
				if((e.getFromVertex().equals(root)))
				{
					edge.add(e); 
				}
				else
				{
					System.out.println("this is a from edge");
				}
			}
			for(Edge<T> e : edge)
			{	
				System.out.println(e.toString());
				
				if(e.getCost() < cost)
					{
					// this is setting the first node to go somewhere
					if(counter==0)
					{
						tempVert = e.getToVertex();
						
					}
					//this checks if it is going to go to go backwards
					else if(e.getToVertex().getValue().equals(nodeList.get(counter-1).data))
					{
						System.out.println("its the same vertex");
					}
					else
					{
						tempVert = e.getToVertex();
						cost = e.getCost();
					}
						
						//System.out.println("this is the get To Vertex Data:\n "+ tempVert+"\n");
						
				}
			}
			System.out.println("this is the get To Vertex Data:\n "+ tempVert+"\n");
			//create a temp node for the cheapest vertex
			Node<T> tempNode = new Node<T>();
			System.out.println("tempNode created \n");
			System.out.println("this is the nodes.getcounter:\n "+nodeList.get(0)+"\n");
			tempNode.parent = nodeList.get(counter);
			System.out.println("this is the tempNode Parent:\n "+tempNode+"\n");
			tempNode.data = tempVert.getValue();
			System.out.println("this is the tempNode data:\n "+tempNode.data+"\n");

			System.out.println("Before the node set: "+"\n");
			System.out.println("Counter: "+counter+"\n");
			
			//nodeList.set(counter, tempNode);
			nodeList.add(tempNode);
			System.out.println("after the node  set: "+"\n");
			root = tempVert;
			//nodeList.add(tempVert);
			counter++;
			System.out.println("--------------------------this is the end--------------------------");
			
		}while(!root.equals(end)||end.equals(null));
		
		for (Node<T> n: nodeList)
		{
			System.out.println(n.data);
		}
		
		//create the stack
		//Node stackNode = new Node();
		System.out.println(nodeList.size());
		Node <T> stackNode = nodeList.get(nodeList.size()-1);
		Stack nodeStack = new Stack();
		while(stackNode.parent != null)
		{
			System.out.println(stackNode.getData().toString());
			nodeStack.push(stackNode);
			stackNode = stackNode.parent;
			
		}

		return nodeStack;
	}
}
