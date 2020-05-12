package GUI;

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
import Object.SolarBuilding;
import Object.StandardBuilding;
import Styles.Arrow;
import Styles.backg;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author 201613026
 *Class for View Pane 
 */
public class ViewPane extends GridPane{
	/*---------------------- DECLARATIONS--------------------------------------------*/
	private Stage drawWindow = new Stage();					//new stage to display the graph
	private ScrollPane sp = new ScrollPane();				//scroll pane that adds a scroll bar
	private Group root = new Group();						//group the vertices and edges to display
	private ContextMenu contextMenu = new ContextMenu();	//instantiate the menu
	private int tempChoice = 0;
	@SuppressWarnings("rawtypes")
	private Node nodeCheap;
	private Vertex<Building> vFrom;
	private Vertex<Building> vTo;
	
	private double spX;
	private double spY;
	IOHandling i =new IOHandling();
	
	ArrayList<Graph<Building>> gList;						//gList used to store the graph
	List<Vertex<Building>> vList;							//vList used to store the vertices
	List<Edge<Building>> eList;								//eList used to store the edges 
	/*-------------------------------------------------------------------------------*/
	
	/**
	 * View Pane class
	 */
	@SuppressWarnings("rawtypes")
	public ViewPane() {
		
		/*---------------------- SETUP MENU FOR RIGHT CLICK-----------------------------*/
		//ContextMenu contextMenu = new ContextMenu();				//instantiate the menu
        MenuItem menuAddVertex = new MenuItem("ADD VERTEX");		//create the menu items
        MenuItem menuAddEdge = new MenuItem("ADD EDGE");		//create the menu items
        MenuItem menuCheapest = new MenuItem("Find Cheapest Path");//create the menu items
        
        contextMenu.getItems().addAll(menuAddVertex,menuAddEdge, menuCheapest);	//add the items to the menu
        
        Background bg = backg.setBackground("file:src/Images/backgroundFile.jpg");
		sp.setBackground(bg);
		sp.setOnContextMenuRequested(a->{
			//display on right click
			spX=a.getScreenX();
			spY=a.getScreenY();
			contextMenu.show(sp, a.getScreenX(), a.getScreenY());	//display it at the mouse coordinates	
			System.out.println("mouse cooridinates x"+ spX);
			System.out.println("mouse cooridinates y"+ spY);
		});
		menuAddEdge.setOnAction(e->{
			if(vFrom==null)
			{
				Alert alert = new Alert(AlertType.ERROR, "Please choose a From vertex");
				alert.showAndWait();
				
			}else if(vTo==null)
			{
				Alert alert = new Alert(AlertType.ERROR, "Please choose a To vertex");
				alert.showAndWait();
			}else
			{
				ChooseVetexFromGraph();
			}
		});
		menuAddVertex.setOnAction(e->{
			
			Stage window = new Stage();
			
			TextInputDialog input = new TextInputDialog();
			tempChoice = 0;
			input.setTitle("Building Choose");
			input.setHeaderText("Building From File:\t 1\n Exit:\t 2");
			input.setContentText("Option:");
			input.showAndWait().ifPresent(f->{
				try {
					tempChoice = Integer.parseInt(input.getEditor().getText());
				}
				catch (NumberFormatException ex) {

					Alert alert = new Alert(AlertType.ERROR,"Please enter a valid Integer");
					alert.showAndWait();
				}catch(Exception ex)
				{
					Alert alert = new Alert(AlertType.ERROR,"Invalid Entry");
					alert.showAndWait();
				}
				
			});
			if(tempChoice==1)
			{
				addNewBuilding(window);
				
			}
			else if(tempChoice==2)
			{
				window.close();
				
			}
			else
			{
				Alert alert = new Alert(AlertType.ERROR,"Please enter a valid choice");
				alert.showAndWait();
			}
			
			System.out.println(tempChoice);
			
		});
		menuCheapest.setOnAction(e->{
			for (int i=0;i<vList.size(); i++)
			{
				System.out.println("other vertex: "+vList.get(i));
			}
			System.out.println("root Vertex "+vList.get(1));
			System.out.println("end Vertex "+vList.get(5));
			
			Stack<Node> nodeStack=NodeSet(vList.get(0));
			Node node = new Node();
			int last =nodeStack.size();
			nodeCheap=nodeStack.get(last-1);
			int count = 0;
			while(!nodeStack.isEmpty())
			{
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
		    	
		    	System.out.println(count);
		    	if (count==0) 
		    	{
		    		System.out.println("HERE");
		    	}
		    	count++;
			}
			String n = nodeCheap.getData().toString();
			String strX = StringHandling.strFind(n,"getName\\(\\)=.+]");
	    	
	    	String []s=strX.split("getName\\(\\)=");
	    	String sName=s[1];
			Alert alert = new Alert(AlertType.INFORMATION,sName);
			alert.setHeaderText("Most Efficient Station");
			alert.showAndWait();
			
		});
		
		/*-------------------------------------------------------------------------------*/
		
		/*--------------------READING DATA TO ARRAY LIST---------------------------------*/
		gList = i.readGraph();								//read graph from file
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
		drawWindow.setMaximized(true);
		drawWindow.setScene(scene);									//add scene to window
		drawWindow.show();											//show window	
		/*-------------------------------------------------------------------------------*/
	
	}
	
	/**
	 * function to draw a circle
	 * @param x
	 * @param y
	 * @return
	 */
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
	public Circle drawVertex(Vertex<Building> vertex)
	{
		ContextMenu contextMenuVertex = new ContextMenu();
		MenuItem menuFrom = new MenuItem("Select as From vertex");
		MenuItem menuTo = new MenuItem("Select as To vertex");
        MenuItem menuViewInfo = new MenuItem("View Info");
        contextMenu.hide();
        contextMenuVertex.getItems().addAll(menuViewInfo, menuFrom,menuTo);
        
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
				
				menuFrom.setOnAction(f->{
					vFrom = vertex;
					Alert a= new Alert(AlertType.INFORMATION,"Vertex:\n"+vFrom.getValue().getName()+" \n SuccessFully Added");
					a.showAndWait();
					
				});
				
				menuTo.setOnAction(f->{
					vTo = vertex;
					Alert a= new Alert(AlertType.INFORMATION,"Vertex:\n"+vTo.getValue().getName()+" \n SuccessFully Added");
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
			Text t = SetText(v);
			
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
			root.getChildren().add(t);
		}			
		for (Edge<Building> e: eList)
		{
			Group a = new Group ();
			a = drawArrowEdge(e);
			root.getChildren().add(a);
			
		}
	}

	/**
	 * function to return a stack from my Nodes class
	 * @param root
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  Stack<Node> NodeSet(Vertex<Building> root)
	{
		Nodes Nodes = new Nodes();
		Stack<Node> nodeStack = Nodes.nodeAlgorythm(root);
		return nodeStack;
		
		
	}
	
	/**
	 * function to set Text of vertex
	 * @param vertex
	 * @return
	 */
	public Text SetText(Vertex<Building> vertex)
	{
		int Xpos= vertex.getValue().getXPosition();
		int Ypos= vertex.getValue().getYPosition();
		String name= vertex.getValue().getName();
		
		Text t = new Text(Xpos-55,Ypos-55,name);
		t.setFill(Color.BLACK);
		return t;
		
	}
	
	/**
	 * function to create new building and make a vertex
	 * @param window
	 */
	public void addNewBuilding(Stage window)
	{
		IOHandling ih = new IOHandling();
		GridPane pane = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(10);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(40);
	    ColumnConstraints column3 = new ColumnConstraints();
	    column3.setPercentWidth(40);
	    ColumnConstraints column4 = new ColumnConstraints();
	    column4.setPercentWidth(10);
	     
	    pane.getColumnConstraints().addAll(column1, column2, column3, column4);
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));

		Text SceneTitle = new Text("BUILDING'S");
		SceneTitle.setFont(Font.font("Tamoha", FontWeight.NORMAL, 20));
		pane.add(SceneTitle, 0, 0, 2, 1);

		Scene scene = new Scene(pane, 600, 600);
		window.setScene(scene);
		window.show();

		CheckBox solarB = new CheckBox();
		//solarB.setSelected(true);
		Label lblBuildingType = new Label("BuildingType: ");
		//ChoiceBox<String> c = new ChoiceBox<String>();
		//c.getItems().addAll(choices);

		Button btnOk = new Button("Generate Building");
		HBox HBtn = new HBox(10);
		HBtn.setAlignment(Pos.BOTTOM_RIGHT);
		HBtn.getChildren().add(btnOk);

		Label lblName = new Label("Name: ");
		Label lblPowerUsed = new Label("Power Used: ");
		Label lblPowerGenerated = new Label("PowerGenerated: ");
		Label lblxPos = new Label("X Position: ");
		Label lblyPos = new Label("Y Position: ");
		
		TextField txtName = new TextField();
		TextField txtPowerUsed = new TextField();
		TextField txtPowerGenerated = new TextField();
		TextField txtxPos = new TextField(Integer.toString((int)spX));
		TextField txtyPos = new TextField(Integer.toString((int)spY));
		
		solarB.setSelected(true);
		solarB.setText("Solar Builidng");
		lblPowerUsed.setDisable(true);
		txtPowerUsed.setDisable(true);
		
		solarB.setOnAction(t -> {
			if (solarB.isSelected())
			{
				solarB.setText("Solar Builidng");
				lblPowerUsed.setDisable(true);
				txtPowerUsed.setDisable(true);
				lblPowerGenerated.setDisable(false);
				txtPowerGenerated.setDisable(false);
			}
			else
			{
				solarB.setText("Standard Builidng");
				lblPowerUsed.setDisable(false);
				txtPowerUsed.setDisable(false);
				lblPowerGenerated.setDisable(true);
				txtPowerGenerated.setDisable(true);
			}
		});

		btnOk.setOnAction(i -> {
			
			String name = txtName.getText();
		
			int power;
			int xPos;
			int yPos;
			if(solarB.isSelected())
			{
				try{
				
					power = Integer.parseInt(txtPowerGenerated.getText());
					xPos = Integer.parseInt(txtxPos.getText());
					yPos = Integer.parseInt(txtyPos.getText());
					SolarBuilding s = new SolarBuilding();
					
					s.setXPosition(xPos);
					s.setYPosition(yPos);
					s.setPowerGenerated(power);
					s.setName(name);
					Vertex<Building> v=buildingToVertex(s, power);
					AddVertexToGraph(v);
					ih.writeBuildingToFile(s);
					
					
				}catch (NumberFormatException ex) {
					Alert alert = new Alert(AlertType.ERROR, "Please enter valid  Integer");
					alert.showAndWait();
				}
			}
			else
			{
				try{
					power = Integer.parseInt(txtPowerUsed.getText());
					xPos = Integer.parseInt(txtxPos.getText());
					yPos = Integer.parseInt(txtyPos.getText());
					StandardBuilding s = new StandardBuilding();
					s.setXPosition(xPos);
					s.setYPosition(yPos);
					s.setName(name);
					Vertex<Building> v=buildingToVertex(s, power);
					AddVertexToGraph(v);
					ih.writeBuildingToFile(s);
					
				}catch (NumberFormatException ex) {
					Alert alert = new Alert(AlertType.ERROR, "Please enter valid  Integer");
					alert.showAndWait();
				}
			}
			window.close();
		});
		Background bg = backg.setBackground("file:src/Images/backgroundFile.jpg");
		pane.setBackground(bg);
		pane.add(lblBuildingType, 1, 1);
		pane.add(solarB, 2, 1);

		pane.add(lblName, 1, 2);
		pane.add(txtName, 2, 2);

		pane.add(lblPowerGenerated, 1, 3);
		pane.add(txtPowerGenerated, 2, 3);

		pane.add(lblPowerUsed, 1, 4);
		pane.add(txtPowerUsed, 2, 4);
		
		pane.add(lblxPos, 1, 5);
		pane.add(txtxPos, 2, 5);

		pane.add(lblyPos, 1, 6);
		pane.add(txtyPos, 2, 6);
		pane.add(HBtn, 1, 7);
	}

	/**
	 * function to create vertex from building
	 * @param build
	 * @param weight
	 * @return
	 */
	public Vertex<Building> buildingToVertex(Building build, int weight)
	{
		Vertex<Building> v0 = new Vertex<Building>(build,weight);
		return v0;
	}
	
	/**
	 * function to add vertex to graph file
	 * @param vertex
	 * @return
	 */
	public Graph<Building> AddVertexToGraph(Vertex<Building> vertex)
	{
		Graph<Building> g = new Graph<Building>();
		ArrayList<Graph<Building>> gList = new ArrayList<Graph<Building>>();
		gList = i.readGraph();
		System.out.println(gList.size());
		for (Graph<Building> gs: gList)
		{
			gs.getVertices().add(vertex);
			IOHandling.writeGraph(gs);
		}
		return g;
	}

	/**
	 * function to add edges by right clicking on it
	 */
	private void ChooseVetexFromGraph() 
	{
		Stage window = new Stage();
		GridPane pane = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
	     column1.setPercentWidth(10);
	     ColumnConstraints column2 = new ColumnConstraints();
	     column2.setPercentWidth(40);
	     ColumnConstraints column3 = new ColumnConstraints();
	     column3.setPercentWidth(40);
	     ColumnConstraints column4 = new ColumnConstraints();
	     column4.setPercentWidth(10);
	     
	     pane.getColumnConstraints().addAll(column1, column2, column3, column4);
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25,25,25,25));
		
		Text SceneTitle = new Text("Vertex Choices:");
		SceneTitle.setFont(Font.font("Tamoha", FontWeight.NORMAL, 20));
		pane.add(SceneTitle, 0, 0, 2, 1);

		Label lblFromChoice = new Label("From Choice:\t ");
		Label lblToChoice = new Label("To Choice:\t ");
		
	    Scene scene = new Scene(pane,400,400);
	    window.setScene(scene);
	    window.show();
	    
	    Button btnOk = new Button("Select Vertices");
	    HBox HBtn = new HBox (10);
	    HBtn.setAlignment(Pos.BOTTOM_RIGHT);
	    HBtn.getChildren().add(btnOk);
	    
	    TextArea FromChoice = new TextArea(vFrom.toString());
	    FromChoice.setDisable(true);
	    TextArea ToChoice = new TextArea(vTo.toString());
	    ToChoice.setDisable(true);
	    
	    btnOk.setOnAction(i->{
	    	int w=vFrom.getWeight();
	    	int wto=vTo.getWeight();
	    	AddEdgeToGraph(vFrom, vTo, w, wto);
	    	window.close();
	    	
	    });
	    Background bg = backg.setBackground("file:src/Images/backgroundFile.jpg");
		pane.setBackground(bg);
	    pane.add(lblFromChoice, 1, 1);
	    pane.add(FromChoice, 2, 1);
	    pane.add(lblToChoice, 1, 2);
	    pane.add(ToChoice, 2, 2);
	    pane.add(HBtn, 2, 3);
	
		
	}
	
	/**
	 * add the selected vertex to graph
	 * @param vFrom
	 * @param vTo
	 */
	public void AddEdgeToGraph(Vertex<Building> vFrom,Vertex<Building> vTo, int w, int wto)
	{
		ArrayList<Graph<Building>> gList = new ArrayList<Graph<Building>>();
		gList = i.readGraph();
		System.out.println("this is graph size"+gList.size());
		for (Graph<Building> gs: gList)
		{
			System.out.println("we got here");
			gs.getVertices().add(vTo);
			gs.getVertices().add(vFrom);
			gs.addVnE(vFrom, vTo, w, wto);
			IOHandling.writeGraph(gs);
		}
	}
}
