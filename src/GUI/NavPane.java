package GUI;

import Styles.backg;
import Styles.button;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
	

/**
 * @author 201613026
 *A class that handles the navigationg GUI
 */
public class NavPane extends GridPane 
{

	//BUTTONS USED FOR THE INTERFACE
	private Button btnView;
	private Button btnVertex;
	private Button btnEdge;
	private Button btnBuilding;
	private Button btnGraph;
	
	private VBox gridBox= new VBox ();;
	
	Stage window;

	/**
	 * default load for NavigationPane
	 * @param window
	 */
	public NavPane(Stage window) {	
		this.window = window;
		setMain();
		setupGUI();
	}

	private void setMain() {
		Label lbl = new Label("RATIONING \nLOAD SHEDDING");
		gridBox.getChildren().add(lbl);
		lbl.setMinWidth(600);
		lbl.setMinHeight(600);
		lbl.setStyle(	"-fx-font-size: 12px;\r\n" + 
				"    -fx-font-size: 128px;\r\n" + 
				"    -fx-font-weight: bold;\r\n" + 
				"    -fx-text-fill: #333333;\r\n" + 
				"    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );\r\n" 
				);
		
	}

	/**
	 * A METHOD THAT HOLDS THE SETTING UP OF ALL THE BUTTONS
	 */
	private void buttonFunctions() {
		//shows the graph view
		btnView.setOnAction(e->{
			
			gridBox.getChildren().clear();
			ViewPane movPane = new ViewPane ();
			gridBox.getChildren().add(movPane);
			setMain();
		});
		
		btnGraph.setOnAction(e->{
			
			gridBox.getChildren().clear();
			GraphPane movPane = new GraphPane();
			gridBox.getChildren().add(movPane);
		});
		
		
		//shows the list of vertices in the graph
		btnVertex.setOnAction(e->{
			
			gridBox.getChildren().clear();
			VertexPane movPane = new VertexPane ();
			gridBox.getChildren().add(movPane);
		});
		
		//shows the list of edges in a graph
		btnEdge.setOnAction(e->{
			
			gridBox.getChildren().clear();
			EdgePane movPane = new EdgePane ();
			gridBox.getChildren().add(movPane);
		});
		
		btnBuilding.setOnAction(e->{
			
			gridBox.getChildren().clear();
			BuildingPane movPane = new BuildingPane ();
			gridBox.getChildren().add(movPane);
		});
	}

	/**
	 * GUI setup for the navigation pane
	 */
	private void setupGUI() 
	{
		button btn = new button();
		btnView = btn.btnSet(btnView,"VIEW","View all in graph");
		btnVertex = btn.btnSet(btnVertex,"VIEW VERTICES","View the vertices in graph");
		btnEdge = btn.btnSet(btnEdge,"VIEW EDGE'S","View the edge's in graph");
		btnBuilding = btn.btnSet(btnBuilding,"VIEW BUILDING'S","View the buildings");
		btnGraph = btn.btnSet(btnGraph,"VIEW GRAPH","View the graphs");
		
		VBox btnBox = new VBox ();
		btnBox.getChildren().addAll(btnView, btnVertex, btnEdge,btnBuilding, btnGraph);
		gridBox.setAlignment(Pos.BOTTOM_CENTER);
		
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
		
		Text lblMainUsage = new Text("Main Usage");
		lblMainUsage.setFont(Font.font ("Verdana", FontWeight.BOLD, 40));
		lblMainUsage.setFill(Color.WHITE);
		
		Text lblAdd = new Text("Add Information");
		lblAdd.setFont(Font.font ("Verdana", FontWeight.BOLD, 40));
		lblAdd.setFill(Color.WHITE);
		
		Text lblAddOther = new Text("Other");
		lblAddOther.setFont(Font.font ("Verdana", FontWeight.BOLD, 40));
		lblAddOther.setFill(Color.WHITE);
		
		ColumnConstraints column1 = new ColumnConstraints();
	     column1.setPercentWidth(5);
	     ColumnConstraints column2 = new ColumnConstraints();
	     column2.setPercentWidth(25);
	     ColumnConstraints column3 = new ColumnConstraints();
	     column3.setPercentWidth(65);
	     ColumnConstraints column4 = new ColumnConstraints();
	     column4.setPercentWidth(5);
	     
	     this.getColumnConstraints().addAll(column1, column2, column3, column4);
	     this.setAlignment(Pos.CENTER);
//	     
	     add(gridBox,2,1);
	     add(btnBox,1,1);
		
		buttonFunctions();
		
		Background b = backg.setBackground("file:src/Images/backgroundLogin.jpg");
		this.setBackground(b);		
	}

	
}
