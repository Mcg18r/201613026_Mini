
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import FileHandling.IOHandling;
import GUI.NavPane;
import GUI.ViewPane;
import Object.Building;
import Object.SolarBuilding;
import Object.StandardBuilding;
import Stack.Stack;
import Stack.StackArr;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author 201613026
 *
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
    	//launch the JavaFX Application
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		setUp();
		
		Stage window =primaryStage;
		window.setTitle("2016113026	MiniProject");

		NavPane homePane= new NavPane (window);
		Scene homeScene = new Scene (homePane,800,800);
		window.setScene(homeScene);
		window.setMaximized(true);
		window.show();
		
	}

	public void setUp ()
	{ 
		Graph<Building> g = new Graph<Building>();
		/*--------------------CREATION OF BUILDINGS---------------------------------*/
		SolarBuilding sb = new SolarBuilding ();
		sb.setXPosition(750);
		sb.setYPosition(750);
		sb.setPowerGenerated(1500000);
		sb.setName("ESKOM");	
		IOHandling.writeBuilding(sb);
		
		SolarBuilding sb1 = new SolarBuilding ();
		sb1.setXPosition(300);
		sb1.setYPosition(100);
		sb1.setPowerGenerated(intRandom(0, 2000));
		sb1.setName("Arnot Power Station");
		IOHandling.writeBuildingToFile(sb1);
		
		SolarBuilding ps3 = new SolarBuilding ();
		ps3.setXPosition(750);
		ps3.setYPosition(1400);
		ps3.setPowerGenerated(intRandom(0, 2000));
		ps3.setName("Lethabo Power Station");
		IOHandling.writeBuildingToFile(ps3);
		
		
		StandardBuilding fs1 = new StandardBuilding ();
		fs1.setXPosition(600);
		fs1.setYPosition(1600);
		fs1.setPowerUsed(intRandom(0, 2000));
		fs1.setName("Harrismith");
		IOHandling.writeBuildingToFile(fs1);
		
		StandardBuilding fs2 = new StandardBuilding ();
		fs2.setXPosition(900);
		fs2.setYPosition(1600);
		fs2.setPowerUsed(intRandom(0, 2000));
		fs2.setName("Bethlehem");
		IOHandling.writeBuildingToFile(fs2);
		
		SolarBuilding ps4 = new SolarBuilding ();
		ps4.setXPosition(1000);
		ps4.setYPosition(1000);
		ps4.setPowerGenerated(intRandom(0, 2000));
		ps4.setName("Matimba Power Station");
		IOHandling.writeBuildingToFile(ps4);
		
		
		StandardBuilding l1 = new StandardBuilding ();
		l1.setXPosition(1200);
		l1.setYPosition(850);
		l1.setPowerUsed(intRandom(0, 2000));
		l1.setName("Mokopane");
		IOHandling.writeBuildingToFile(l1);
		
		StandardBuilding l2 = new StandardBuilding ();
		l2.setXPosition(1200);
		l2.setYPosition(1150);
		l2.setPowerUsed(intRandom(0, 2000));
		l2.setName("Polokwane");
		IOHandling.writeBuildingToFile(l2);
		
		
		SolarBuilding sb2 = new SolarBuilding ();
		sb2.setXPosition(900);
		sb2.setYPosition(100);
		sb2.setPowerGenerated(intRandom(0, 2000));
		sb2.setName("Kelvin Power Station");	
		IOHandling.writeBuildingToFile(sb2);
		
		SolarBuilding sb3 = new SolarBuilding ();
		sb3.setXPosition(150);
		sb3.setYPosition(200);
		sb3.setPowerGenerated(intRandom(0, 2000));
		sb3.setName("Johannesburg");
		IOHandling.writeBuildingToFile(sb3);
		
		SolarBuilding sb4 = new SolarBuilding ();
		sb4.setXPosition(150);
		sb4.setYPosition(200);
		sb4.setPowerGenerated(1200);
		sb4.setName("119 Kessel");
		IOHandling.writeBuildingToFile(sb3);
		
		StandardBuilding st = new StandardBuilding();
		st.setXPosition(450);
		st.setYPosition(200);
		st.setPowerUsed(intRandom(0, 2000));
		st.setName("FNB");
		IOHandling.writeBuildingToFile(st);
		
		StandardBuilding st1 = new StandardBuilding();
		st1.setXPosition(750);
		st1.setYPosition(200);
		st1.setPowerUsed(intRandom(0, 2000));
		st1.setName("ABSA");
		IOHandling.writeBuildingToFile(st1);
		
		StandardBuilding st2 = new StandardBuilding();
		st2.setXPosition(1050);
		st2.setYPosition(200);
		st2.setPowerUsed(intRandom(0, 2000));
		st2.setName("Standard Bank");
		IOHandling.writeBuildingToFile(st2);
		
		SolarBuilding st3 = new SolarBuilding();
		st3.setXPosition(1200);
		st3.setYPosition(300);
		st3.setPowerGenerated(1500);
		st3.setName("Engen");
		IOHandling.writeBuildingToFile(st3);
		
		/*------------------------------------------------------------------------*/
		
		/*--------------------CREATION OF VERTICES---------------------------------*/
		Vertex<Building> v0 = new Vertex<Building>(sb,intRandom(0, 2000));
		g.getVertices().add(v0);
		//IOHandling.writeVertex(v1); 						add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v1 = new Vertex<Building>(sb1,intRandom(0, 2000));
		g.getVertices().add(v1);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v2 = new Vertex<Building>(sb2,intRandom(0, 2000));
		g.getVertices().add(v2);
		//IOHandling.writeVertex(v1); 						add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v3 = new Vertex<Building>(sb3,intRandom(0, 2000));
		g.getVertices().add(v3);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v4 = new Vertex<Building>(st,intRandom(0, 2000));
		g.getVertices().add(v4);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v5 = new Vertex<Building>(st1,intRandom(0, 2000));
		g.getVertices().add(v5);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v6 = new Vertex<Building>(st2,intRandom(0, 2000));
		g.getVertices().add(v6);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v7 = new Vertex<Building>(st3,intRandom(0, 2000));
		g.getVertices().add(v7);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> vp = new Vertex<Building>(ps3,intRandom(0, 2000));
		g.getVertices().add(vp);
		Vertex<Building> vf1 = new Vertex<Building>(fs1,intRandom(0, 2000));
		g.getVertices().add(vf1);
		Vertex<Building> vf2 = new Vertex<Building>(fs2,intRandom(0, 2000));
		g.getVertices().add(vf2);
		
		Vertex<Building> vp1 = new Vertex<Building>(ps4,intRandom(0, 2000));
		g.getVertices().add(vp1);
		Vertex<Building> vl1 = new Vertex<Building>(l1,intRandom(0, 2000));
		g.getVertices().add(vl1);
		Vertex<Building> vl2 = new Vertex<Building>(l2,intRandom(0, 2000));
		g.getVertices().add(vl2);
		
		g.addVnE(v0, v1, 3, 0);
		g.addVnE(v0, v2, 2, 0);
		g.addVnE(v0, vp, 300, 900);
		g.addVnE(vp, vf1, 2, 0);
		g.addVnE(vp, vf2, 3, 0);
		
		g.addVnE(v0, vp1, 0, 900);
		g.addVnE(vp1, vl1, 2, 0);
		g.addVnE(vp1, vl2, 3, 0);
		g.addVnE(v1, v3, 2, 0);
		g.addVnE(v1, v4, 3, 0);
		g.addVnE(v2, v5, 3, 0);
		g.addVnE(v2, v6, 2, 0);
		g.addVnE(v6, v7, 3, 0);
		//g.addVnE(v7, v8, 2, 0);
		
		IOHandling.writeGraph(g);
		/*------------------------------------------------------------------------*/

		
		
	}
	public static double doubleRandom(double min, double max){
	    double x = (Math.random()*((max-min)+1))+min;
	    return x;
	}
	
	public static int intRandom(double min, double max){
	    int x = (int) ((int)(Math.random()*((max-min)+1))+min);
	    return x;
	}
	
}