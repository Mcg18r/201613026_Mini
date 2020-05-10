package GUI;

import java.util.ArrayList;
import java.util.List;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import FileHandling.IOHandling;
import FileHandling.StringHandling;
import Object.Building;
import Object.SolarBuilding;
import Object.StandardBuilding;
import Styles.backg;
import Styles.button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
 *Vertex Pane class
 */
public class VertexPane extends GridPane{

	private Button btnAddVertex;
	
	//VARIABLES FOR BUILDING
	private int xPos;
	private int yPos;
	private int power;
	private String name;
	private Building b;
	private int tempChoice;
	private TableView<Vertex<Building>> table = new TableView<Vertex<Building>>();
	private ObservableList<Vertex<Building>> data;
	@SuppressWarnings("unused")
	private ObservableList<Building> dataBuildings;
	
	/**
	 * vertex class load
	 */
	public VertexPane() {
		// TODO Auto-generated constructor stub() {
		tableLoad();
		setupGUI();
		
		btnAddVertex.setOnAction(e->{
			Stage window = new Stage();
			
			TextInputDialog input = new TextInputDialog();
			tempChoice = 0;
			input.setTitle("Choose Building");
			input.setHeaderText("From File:\t 1\n Create New:\t 2");
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
				ChooseBuildingFromFile(window);			
			}
			else if(tempChoice==2)
			{
				addNewBuilding(window);
				
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
	 * setup GUI for vertex pane
	 */
	public void setupGUI()
	{
		button btn = new button();
		
		btnAddVertex = btn.btnSet(btnAddVertex,"Add A Vertex","Adds a Vertex");
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
		add(btnAddVertex,1,1,2,1);
			
	}
	
	/**
	 * load vertex data for table
	 */
	@SuppressWarnings("unchecked")
	public void tableLoad()
	{

		ArrayList<Vertex<Building>> vTest=new ArrayList<Vertex<Building>>();
		ArrayList<Graph<Building>> gTest=new ArrayList<Graph<Building>>();
		gTest = IOHandling.readGraph();
		
		for(Graph<Building> g: gTest)
		{
			List<Vertex<Building>> vL=g.getVertices();
			for(Vertex<Building> vb: vL)
			{
				vTest.add(vb);
			}
		}
	
		data = FXCollections.observableList(vTest);
		table.setItems(data);

		//ObservableList<Barcode> data = FXCollections.observableArrayList(Barcode.get(0));
		TableColumn<Vertex<Building>, String> valueCol = new TableColumn<Vertex<Building>, String>("VALUE");
		valueCol.setCellValueFactory(new PropertyValueFactory<Vertex<Building>, String>("value"));
		
		TableColumn<Vertex<Building>, String> weightCol = new TableColumn<Vertex<Building>, String>("WEIGHT");
		weightCol.setCellValueFactory(new PropertyValueFactory<Vertex<Building>, String>("weight"));

		TableColumn<Vertex<Building>, String> edgeCol = new TableColumn<Vertex<Building>, String>("EDGES");
		edgeCol.setCellValueFactory(new PropertyValueFactory<Vertex<Building>, String>("edges"));

		table.getColumns().setAll(valueCol,weightCol, edgeCol);
		table.setPrefWidth(450);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/**
	 * function to get buildings from file
	 */
	public void getBuildings()
	{
		ArrayList<Building> building = new ArrayList<Building>();
		building = IOHandling.readBuilding();
		
		for(Building b: building)
			{
				System.out.println(b.toString());
			}
	
		dataBuildings = FXCollections.observableList(building);
	}
	
	/**
	 * function to choose building from file
	 * @param window
	 * @return
	 */
	public Building ChooseBuildingFromFile(Stage window)
	{
		b = new Building ();
	getBuildings();
	GridPane pane = new GridPane();
	pane.setAlignment(Pos.CENTER);
	pane.setHgap(10);
	pane.setVgap(10);
	pane.setPadding(new Insets(25,25,25,25));
	
	Text SceneTitle = new Text("Choose Building");
	SceneTitle.setFont(Font.font("Tamoha", FontWeight.NORMAL, 20));
	pane.add(SceneTitle, 0, 0, 2, 1);
	
    Scene scene = new Scene(pane,400,400);
    window.setScene(scene);
    window.show();
    
    Button btnOk = new Button("Select Buildings");
    HBox HBtn = new HBox (10);
    HBtn.setAlignment(Pos.BOTTOM_RIGHT);
    HBtn.getChildren().add(btnOk);
    
    ChoiceBox<Building> choice = new ChoiceBox<Building>(dataBuildings);
    pane.add(choice, 3, 1);
    

    
    btnOk.setOnAction(i->{
    	b = (Building) choice.getValue();
    	String strB= b.toString();
    	String temp = StringHandling.strFind(strB, "t:SolarB");
    	System.out.println("this is temp"+temp);
    	
    	if(temp.equals("t:SolarB"))
    	{
    		
    		SolarBuilding sb = (SolarBuilding) b;
    		Vertex<Building> v=buildingToVertex(b, sb.getPowerGenerated());
    		AddVertexToGraph(v);
    	}else
    	{
    		StandardBuilding sb = (StandardBuilding) b;
    		Vertex<Building> v=buildingToVertex(b, sb.getPowerUsed());
    		AddVertexToGraph(v);
    	}
    	if(this.getChildren().contains(table))
		{
			table.refresh();
		}else
		{
			add(table, 1, 0, 2, 1);
		}
    	window.close();
    });
    pane.add(HBtn, 1, 5);
    Background bg = backg.setBackground("file:src/Images/backgroundFile.jpg");
	pane.setBackground(bg);
	return b;
}
	
	/**
	 * function to create new building and make a vertex
	 * @param window
	 */
	public void addNewBuilding(Stage window)
	{
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
		
		TextArea txtName = new TextArea();
		TextArea txtPowerUsed = new TextArea();
		TextArea txtPowerGenerated = new TextArea();
		TextArea txtxPos = new TextArea();
		TextArea txtyPos = new TextArea();
		
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
			
			name = txtName.getText();
		
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
					tableLoad();
					IOHandling.writeBuildingToFile(s);
					
					
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
					tableLoad();
					Vertex<Building> v=buildingToVertex(s, power);
					AddVertexToGraph(v);
					IOHandling.writeBuildingToFile(s);
					
				}catch (NumberFormatException ex) {
					Alert alert = new Alert(AlertType.ERROR, "Please enter valid  Integer");
					alert.showAndWait();
				}
			}
			
			if(this.getChildren().contains(table))
			{
				table.refresh();
			}else
			{
				add(table, 1, 0, 2, 1);
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
		gList = IOHandling.readGraph();
		System.out.println(gList.size());
		for (Graph<Building> gs: gList)
		{
			gs.getVertices().add(vertex);
			IOHandling.writeGraph(gs);
		}
		return g;
	}
}

