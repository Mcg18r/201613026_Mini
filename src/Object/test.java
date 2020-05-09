package Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import FileHandling.IOHandling;
import FileHandling.StringHandling;
import Nodal.Node;
import Nodal.Nodes;
import Object.Building;
import Styles.Arrow;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author 201613026
 *
 */
public class test extends GridPane{
	/*---------------------- DECLARATIONS--------------------------------------------*/
	private Stage drawWindow = new Stage();					//new stage to display the graph
	private ScrollPane sp = new ScrollPane();				//scroll pane that adds a scroll bar
	private Group root = new Group();						//group the vertices and edges to display
	private ContextMenu contextMenu = new ContextMenu();				//instantiate the menu
	
	ArrayList<Graph<Building>> gList;						//gList used to store the graph
	List<Vertex<Building>> vList;							//vList used to store the vertices
	List<Edge<Building>> eList;								//eList used to store the edges 
	/*-------------------------------------------------------------------------------*/
	
	public test() {
		
		/*---------------------- SETUP MENU FOR RIGHT CLICK-----------------------------*/
		//ContextMenu contextMenu = new ContextMenu();				//instantiate the menu
        MenuItem menuAddVertex = new MenuItem("ADD VERTEX");		//create the menu items
        MenuItem menuAddEdge = new MenuItem("ADD EDGE");
        MenuItem menuCheapest = new MenuItem("Find Cheapest");//create the menu items
        
        contextMenu.getItems().addAll(menuAddVertex, menuAddEdge, menuCheapest);	//add the items to the menu
        
		sp.setOnContextMenuRequested(a->{							//display on right click
			contextMenu.show(sp, a.getScreenX(), a.getScreenY());	//display it at the mouse coordinates		
		});
		menuCheapest.setOnAction(e->{
			for (int i=0;i<vList.size(); i++)
			{
				System.out.println("other vertex: "+vList.get(i));
			}
			System.out.println("root Vertex "+vList.get(1));
			System.out.println("end Vertex "+vList.get(5));
			
			Stack<Node> nodeStack=NodeSet(vList.get(0), vList.get(5));
			Node node = new Node();
			while(!nodeStack.isEmpty())
			{
				
				
				//System.out.println("this is me popping in required spot: " +nodeStack.pop().getData().toString());
				node = nodeStack.pop();
				String nString= node.getData().toString();
				System.out.println("this is me popping in required spot: " +nString);
		    	
		    	String strX = StringHandling.strFind(nString,"getxPosition\\(\\)=\\d+");
		    	String strY = StringHandling.strFind(nString,"getyPosition\\(\\)=\\d+");
		    	
		    	String []sX=strX.split("getxPosition\\(\\)=");
		    	String []sY=strY.split("getyPosition\\(\\)=");
		    	
		    	int xPos = Integer.parseInt(sX[1]);
		    	int yPos = Integer.parseInt(sY[1]);
		    	
		    	drawCircle(xPos, yPos);
		    	System.out.println("xPos: "+xPos);
		    	System.out.println("yPos: "+yPos);
			}
		});
		/*-------------------------------------------------------------------------------*/
		
		/*--------------------READING DATA TO ARRAY LIST---------------------------------*/
		gList = IOHandling.readGraph();								//read graph from file
		for (Graph<Building> g: gList)								//loop through graph
		{
			vList=g.getVertices();									//add graph vertices to list
			eList=g.getEdges();										//add graph edges to list
		}
		/*-------------------------------------------------------------------------------*/
		
		drawGraph();	
		
		/*------------------------ADD ROOT TO SCROLLPANE--------------------------------------*/
		sp.setContent(root);										//add root to scroll Pane first
		Scene scene = new Scene(sp,800,800);						//add scroll Pane to the scene
		drawWindow.setScene(scene);									//add scene to window
		drawWindow.show();											//show window	
		/*-------------------------------------------------------------------------------*/
	
	}
	public Circle drawCircle(int x, int y)
	{
        
		Circle circle = new Circle(x, y, 70);	
		
		circle.setOpacity(0.5);
		circle.setFill(Color.BLUE);
		root.getChildren().add(circle);
		return circle;
	}
	
	/**
	 * Function that takes in a vertex and returns a circle
	 * @param vertex
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Circle drawVertex(Vertex<Building> vertex)
	{
		ContextMenu contextMenuVertex = new ContextMenu();
		MenuItem menuWorkout = new MenuItem("Workout traversal");
        MenuItem menuViewInfo = new MenuItem("View Info");
        contextMenuVertex.getItems().addAll(menuWorkout, menuViewInfo);
        
		Circle circle = new Circle(vertex.getValue().getXPosition(), vertex.getValue().getYPosition(), 50);	
		int Xpos= vertex.getValue().getXPosition();
		int Ypos= vertex.getValue().getYPosition();
		String name= vertex.getValue().getName();
		String type= vertex.getValue().getType();
		String X= Integer.toString(Xpos);
		String Y = Integer.toString(Ypos);
		
		circle.setOnContextMenuRequested(e->{
			{
				contextMenuVertex.show(circle, e.getScreenX(), e.getScreenY());
				
				menuViewInfo.setOnAction(f->{
					Alert a= new Alert(AlertType.INFORMATION,"Name:\t"+name+"\n"+"X Position:\t"+X+"\n"+"Y Position:\t"+Y+"\n");
					a.setHeaderText("Type:\t"+type+"\n");
					a.showAndWait();
				});
			}
		});
		
		return circle;
	}
	
	/**
	 * Function that takes in a edge and returns a line between Vertices
	 * @param edge
	 * @return
	 */
	public Line drawLineEdge(Edge<Building> edge)
	{
		Line line = new Line(edge.getFromVertex().getValue().getXPosition(), edge.getFromVertex().getValue().getYPosition(), 
				edge.getToVertex().getValue().getXPosition(), edge.getToVertex().getValue().getYPosition());
		
		return line;
	}
	
	/**
	 * Function that takes in a edge and returns an arrow between Vertices
	 * @param edge
	 * @return
	 */
	public Group drawArrowEdge(Edge<Building> edge)
	{
		Group stack = new Group();
		String cost = Integer.toString(edge.getCost());
		Text t= new Text(cost);
	
		
		Arrow arrow = new Arrow();
		double radius = 50;
		
		int startX = edge.getFromVertex().getValue().getXPosition();
		int startY = edge.getFromVertex().getValue().getYPosition();
		int endX = edge.getToVertex().getValue().getXPosition();
		int endY = edge.getToVertex().getValue().getYPosition();
		
		double angle = Math.atan2(endY - startY, endX - startX) * 180 / 3.14;

	    double height = endY - startY;
	    double width = endX - startX;
	    double length = Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));
	    
	    double midX = (startX+endX)/2;
	    double midY = (startY+endY)/2;
	    
	    double subtractWidth = radius * width / length;
	    double subtractHeight = radius * height / length;

	    setRotate(angle - 90);
	    
	    arrow.setStartX(startX+ subtractWidth);
	    arrow.setStartY(startY+ subtractHeight);
	    arrow.setEndX(endX - subtractWidth);
	    arrow.setEndY(endY - subtractHeight);

	    if((edge.getFromVertex().getValue().getYPosition()-edge.getToVertex().getValue().getYPosition())>0)
	    {
//	    	t.setX(midX-20);
//	 		t.setY(midY-20);
//	 	    t.setFill(Color.BLUE);
//	 	    t.setFont(new Font("Arial", 20));
	    }
	    else
	    {
	    	t.setX(midX - 20);
	 		t.setY(midY - 20);
	 	    t.setFill(Color.GREEN);
	 	    t.setFont(new Font("Arial",20));
	    }
	    
		stack.getChildren().addAll(arrow,t);
		return stack;
	}

	/**
	 * function that draws the graph onto a new window
	 */
	public void drawGraph ()
	{
		for (Vertex<Building> v: vList)
		{
			String type= v.getValue().getType();
			
			Circle c = drawVertex(v);
			
			if(type=="Solar Building")
			{
				c.setFill(Color.GREEN);
			}
			else if (type=="Standard Building")
			{
				c.setFill(Color.RED);
			}
			else
			{
				c.setFill(Color.CYAN);
			}
			
			root.getChildren().add(c);
		}			
		for (Edge<Building> e: eList)
		{
			Group a = new Group ();
			a = drawArrowEdge(e);
			root.getChildren().add(a);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  Stack<Node> NodeSet(Vertex<Building> root, Vertex<Building> end)
	{
		Nodes Nodes = new Nodes();
		Stack<Node> nodeStack = Nodes.nodeAlgorythm(root, end);
		return nodeStack;
		
		
	}
	
	
}
