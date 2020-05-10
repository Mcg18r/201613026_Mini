package GUI;

import java.util.ArrayList;

import FileHandling.IOHandling;
import Object.Building;
import Object.SolarBuilding;
import Object.StandardBuilding;
import Styles.button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BuildingPane extends GridPane{

	private Button btnAddBuilding;
	
	//VARIABLES FOR BUILDING
	private int xPos;
	private int yPos;
	private int power;
	private String name;
	
	private TableView<Building> table = new TableView<Building>();
	private ObservableList<Building> data;
	
	/**
	 * the default load for pane
	 */
	public BuildingPane() {
		tableLoad();
		setupGUI();
		
		btnAddBuilding.setOnAction(e->{
			Stage window = new Stage();
			final Text actionTarget = new Text();

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
			Label lblBuildingType = new Label("BuildingType: ");

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
						s.setPowerUsed(power);
						s.setName(name);
						tableLoad();
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
			});
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
			pane.add(actionTarget, 1, 8);
		});
	}
	/**
	 * 
	 */
	/**
	 * 
	 */
	public void setupGUI()
	{
		button btn = new button();
		
		btnAddBuilding = btn.btnSet(btnAddBuilding,"Add A Building","Adds a Building to system");
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

		add(btnAddBuilding,1,1,2,1);
	}
	/**
	 * Loads the table from the graph file
	 */
	@SuppressWarnings("unchecked")
	public void tableLoad()
	{

		ArrayList<Building> building = new ArrayList<Building>();
		building = IOHandling.readBuilding();
		
		for(Building b: building)
			{
				System.out.println(b.toString());
			}
	
		data = FXCollections.observableList(building);
		table.setItems(data);

		TableColumn<Building, String> nameCol = new TableColumn<Building, String>("name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Building, String>("name"));
		
		TableColumn<Building, String> type = new TableColumn<Building, String>("TYPE");
		type.setCellValueFactory(new PropertyValueFactory<Building, String>("type"));

		TableColumn<Building, String> xPos = new TableColumn<Building, String>("X POSTION");
		xPos.setCellValueFactory(new PropertyValueFactory<Building, String>("xPosition"));
		
		TableColumn<Building, String> yPos = new TableColumn<Building, String>("Y POSITION");
		yPos.setCellValueFactory(new PropertyValueFactory<Building, String>("yPosition"));
		
		TableColumn<Building, String> pow = new TableColumn<Building, String>("POWER");
		
		TableColumn<Building, String> powGen = new TableColumn<Building, String>("Generated");
		powGen.setCellValueFactory(new PropertyValueFactory<Building, String>("powerGenerated"));
		
		TableColumn<Building, String> powUsed = new TableColumn<Building, String>("Used");
		powUsed.setCellValueFactory(new PropertyValueFactory<Building, String>("powerUsed"));

		pow.getColumns().addAll(powGen, powUsed);

		table.getColumns().setAll(type,nameCol, xPos, yPos,pow);
		table.setPrefWidth(450);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
