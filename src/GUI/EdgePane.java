package GUI;

import java.util.ArrayList;
import java.util.List;
import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import FileHandling.IOHandling;
import FileHandling.StringHandling;
import Object.Building;
import Styles.backg;
import Styles.button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author 201613026
 *Edge Pane class
 */
public class EdgePane extends GridPane{

	ArrayList<Edge<Building>> eTest=new ArrayList<Edge<Building>>();
	private Vertex<Building> vFrom;
	private Vertex<Building> vTo;//This is to store the edges into an arraylist
	private TableView<Edge<Building>> table = new TableView<Edge<Building>>();
	private ObservableList<Vertex<Building>> dataVertices;
	private ObservableList<Edge<Building>> data;
	
	Stage windowChart;
	
	Button btnbarGraph;
	private Button btnAddEdge;
	private int tempChoice;
	
	
	/**
	 * 
	 */
	public EdgePane() {
		tableLoad();
		setupGUI();
		
		btnbarGraph.setOnAction(e->{
			barGraph();
		});
		btnAddEdge.setOnAction(e->{
			Stage window = new Stage();
			
			TextInputDialog input = new TextInputDialog();
			tempChoice = 0;
			input.setTitle("Building Choose");
			input.setHeaderText("From File:\t 1\n Exit:\t 2");
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
				ChooseVetexFromFile(window);			
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
	}
	
	/**
	 * get a list of vertices from graph
	 */
	public void getVertex()
	{
		IOHandling i =new IOHandling();
		List<Vertex<Building>>v = null;
	
		ArrayList<Graph<Building>> g = i.readGraph();
		for(Graph<Building> gl:g)
		{
			v=gl.getVertices();
		}
	
		dataVertices = FXCollections.observableList(v);
	}

	/**
	 * choose vertex from a list of created vertices
	 * @param window
	 */
	private void ChooseVetexFromFile(Stage window) 
	{
		getVertex();
		
		
		
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
		
		Text SceneTitle = new Text("Choose Vertex");
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
	    
	    ChoiceBox<Vertex<Building>> FromChoice = new ChoiceBox<Vertex<Building>>(dataVertices);
	    FromChoice.setMaxHeight(300);
	    FromChoice.setMaxWidth(300);
	    ChoiceBox<Vertex<Building>> ToChoice = new ChoiceBox<Vertex<Building>>(dataVertices);
	    ToChoice.setMaxHeight(300);
	    ToChoice.setMaxWidth(300);
	    
	    btnOk.setOnAction(i->{
	    	vFrom=FromChoice.getValue();
	    	vTo=ToChoice.getValue();
	    	
	    	
	    	//Edge <Building> e = new Edge<Building>(5, vFrom, vTo);
	    	//Edge <Building> f = new Edge<Building>(0,vTo, vFrom);
	    	AddEdgeToGraph(vFrom, vTo);
	    	//AddEdgeToGraph(f);
	    	
	    	//System.out.println(e.toString());
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
	public void AddEdgeToGraph(Vertex<Building> vFrom,Vertex<Building> vTo)
	{
		IOHandling i =new IOHandling();
		ArrayList<Graph<Building>> gList = new ArrayList<Graph<Building>>();
		gList = i.readGraph();
		System.out.println("this is graph size"+gList.size());
		for (Graph<Building> gs: gList)
		{
			gs.getVertices().add(vTo);
			gs.getVertices().add(vFrom);
			gs.addVnE(vFrom, vTo, 12, 13);
			IOHandling.writeGraph(gs);
		}
	}

	/**
	 * a layout for a the edge pane
	 */
	public void setupGUI()
	{
		button btn = new button();
		
		btnbarGraph = btn.btnSet(btnbarGraph,"Bar Graph","view edge's in graph");
		btnAddEdge = btn.btnSet(btnAddEdge,"Add Edge","Add edges to graph");
		
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
		ColumnConstraints column1 = new ColumnConstraints();
	     column1.setPercentWidth(10);
	     ColumnConstraints column2 = new ColumnConstraints();
	     column2.setPercentWidth(40);
	     ColumnConstraints column3 = new ColumnConstraints();
	     column3.setPercentWidth(40);
	     ColumnConstraints column4 = new ColumnConstraints();
	     column4.setPercentWidth(10);
	     
	     this.getColumnConstraints().addAll(column1, column2, column3, column4);
	     this.setAlignment(Pos.CENTER);
		if(this.getChildren().contains(table))
		{
			table.refresh();
		}else
		{
			add(table, 1, 0, 2, 1);
		}
		add(btnbarGraph,1,1,2,1);
		add(btnAddEdge,1,2,2,1);
	}
	
	/**
	 * load the table data
	 */
	@SuppressWarnings("unchecked")
	public void tableLoad()
	{
		IOHandling i =new IOHandling();
		//ArrayList<Edge<Building>> eTest=new ArrayList<Edge<Building>>();
		ArrayList<Graph<Building>> gTest=new ArrayList<Graph<Building>>();
		
		gTest = i.readGraph();
		for(Graph<Building> g: gTest)
		{
			List<Edge<Building>> eL=g.getEdges();
			for(Edge<Building> eb: eL)
			{
				eTest.add(eb);
			}
		}
	
		data = FXCollections.observableList(eTest);
		table.setItems(data);

		//ObservableList<Barcode> data = FXCollections.observableArrayList(Barcode.get(0));
		TableColumn<Edge<Building>, String> valueCol = new TableColumn<Edge<Building>, String>("COST");
		valueCol.setCellValueFactory(new PropertyValueFactory<Edge<Building>, String>("cost"));
		
		TableColumn<Edge<Building>, String> weightCol = new TableColumn<Edge<Building>, String>("FROM VERTEX");
		weightCol.setCellValueFactory(new PropertyValueFactory<Edge<Building>, String>("fromVertex"));

		TableColumn<Edge<Building>, String> edgeCol = new TableColumn<Edge<Building>, String>("TO VERTEX");
		edgeCol.setCellValueFactory(new PropertyValueFactory<Edge<Building>, String>("toVertex"));

		table.getColumns().setAll(valueCol,weightCol, edgeCol);
		table.setPrefWidth(450);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/**
	 * construct a bar graph for edges
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void barGraph ()
	{
		int cost=0;
		windowChart=new Stage();
		windowChart.close();
		windowChart.setTitle("Chart");
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Vertex");       
        yAxis.setLabel("Value");
        

    	XYChart.Series series = new XYChart.Series();
	    series.setName("My portfolio");
	    
	    //populating the series with data
	    for (Edge<Building> e: eTest)
		{
		    
	    	String edge = e.toString();
	    	
	    	String edgeFromName = StringHandling.strFind(edge,"getName\\(\\)=(\\d|\\w|\\s)+(]|,|\\()");
	    	String []line=edgeFromName.split("getName\\(\\)=");
	    	String from = line[1];
	    	String to = line[2];
	    	System.out.println("From: "+from);
	    	System.out.println("To: "+to);
	    	cost=e.getCost();
		    series.getData().add(new XYChart.Data(from+"->\n"+to, cost));
		}
	    bc.getData().add(series);
	    Scene scene  = new Scene(bc,800,600);
	    windowChart.setScene(scene);
	    windowChart.show();
	}
	

}
