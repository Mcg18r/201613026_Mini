package GUI;

import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph;

import FileHandling.IOHandling;
import Object.Building;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class GraphPane extends GridPane{

	private TableView<Graph<Building>> table = new TableView<Graph<Building>>();
	private ObservableList<Graph<Building>> data;
	
	/**
	 * Graphpane class
	 */
	public GraphPane() {
		// TODO Auto-generated constructor stub() {
		tableLoad();
		setupGUI();
	}
	
	/**
	 * setup the GUI interface for the graph pane
	 */
	public void setupGUI()
	{
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
	}
	/**
	 * load the table with data from graphs
	 */
	@SuppressWarnings("unchecked")
	public void tableLoad()
	{
		IOHandling i =new IOHandling();
		ArrayList<Graph<Building>> gTest=new ArrayList<Graph<Building>>();
		gTest = i.readGraph();
		
		for(Graph<Building> v: gTest)
			{
			System.out.println("************************ THIS IS Graph ************************");
			//System.out.println(b.getValue().getxPosition());
			//System.out.println(b.getValue().getyPosition());
			//System.out.println(((SolarBuilding) b.getValue()).getPowerGenerated());
			System.out.println("************************\n"+v+ "\n************************");
			}
	
		data = FXCollections.observableList(gTest);
		table.setItems(data);

		//ObservableList<Barcode> data = FXCollections.observableArrayList(Barcode.get(0));
		TableColumn<Graph<Building>, String> valueCol = new TableColumn<Graph<Building>, String>("TYPE");
		valueCol.setCellValueFactory(new PropertyValueFactory<Graph<Building>, String>("type"));
		
		TableColumn<Graph<Building>, String> weightCol = new TableColumn<Graph<Building>, String>("VERTICES");
		weightCol.setCellValueFactory(new PropertyValueFactory<Graph<Building>, String>("vertices"));

		TableColumn<Graph<Building>, String> edgeCol = new TableColumn<Graph<Building>, String>("EDGES");
		edgeCol.setCellValueFactory(new PropertyValueFactory<Graph<Building>, String>("edges"));

		table.getColumns().setAll(valueCol,weightCol, edgeCol);
		table.setPrefWidth(450);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
