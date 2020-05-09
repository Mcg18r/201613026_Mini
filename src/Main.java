
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
		sb.setXPosition(600);
		sb.setYPosition(0);
		sb.setPowerGenerated(2);
		sb.setName("39 Ferero Street");	
		IOHandling.writeBuilding(sb);
		
		SolarBuilding sb1 = new SolarBuilding ();
		sb1.setXPosition(300);
		sb1.setYPosition(100);
		sb1.setPowerGenerated(intRandom(0, 2000));
		sb1.setName("119 Kessel Street");
		IOHandling.writeBuildingToFile(sb1);
		
		SolarBuilding sb2 = new SolarBuilding ();
		sb2.setXPosition(900);
		sb2.setYPosition(100);
		sb2.setPowerGenerated(intRandom(0, 2000));
		sb2.setName("UNICEF");	
		IOHandling.writeBuildingToFile(sb2);
		
		SolarBuilding sb3 = new SolarBuilding ();
		sb3.setXPosition(150);
		sb3.setYPosition(200);
		sb3.setPowerGenerated(intRandom(0, 2000));
		sb3.setName("134 Amber Street");
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
		
		StandardBuilding st3 = new StandardBuilding();
		st3.setXPosition(1200);
		st3.setYPosition(300);
		st3.setPowerUsed(intRandom(0, 2000));
		st3.setName("Engen");
		IOHandling.writeBuildingToFile(st3);
		
		StandardBuilding st4 = new StandardBuilding();
		st4.setXPosition(1350);
		st4.setYPosition(400);
		st4.setPowerUsed(intRandom(0, 2000));
		st4.setName("Alcatraz");
		IOHandling.writeBuildingToFile(st4);
		
		StandardBuilding st5 = new StandardBuilding();
		st5.setXPosition(150);
		st5.setYPosition(500);
		st5.setPowerUsed(intRandom(0, 2000));
		st5.setName("Testing");
		IOHandling.writeBuildingToFile(st5);
		
		SolarBuilding sb4 = new SolarBuilding ();
		sb4.setXPosition(150);
		sb4.setYPosition(700);
		sb4.setPowerGenerated(160);
		sb4.setName("addthroughVetrex");	
		IOHandling.writeBuildingToFile(sb4);
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
		Vertex<Building> v8 = new Vertex<Building>(st4,intRandom(0, 2000));
		g.getVertices().add(v8);
		Vertex<Building> v9 = new Vertex<Building>(st5,intRandom(0, 2000));
		g.getVertices().add(v9);
		
		g.addVnE(v0, v1, 3, 0);
		g.addVnE(v0, v2, 2, 0);
		g.addVnE(v3, v9, 2, 0);
		g.addVnE(v1, v3, 2, 0);
		g.addVnE(v1, v4, 3, 0);
		g.addVnE(v2, v5, 3, 0);
		g.addVnE(v2, v6, 2, 0);
		g.addVnE(v6, v7, 3, 0);
		g.addVnE(v7, v8, 2, 0);
		
		IOHandling.writeGraph(g);
		/*------------------------------------------------------------------------*/
		
		/*--------------------CREATION OF EDGES-----------------------------------*/
//		Edge<Building> e1 = new Edge<Building>(60,v1,v2);
//		IOHandling.writeEdge(e1);
//		Edge<Building> e2 = new Edge<Building>(120,v2,v1);
//		IOHandling.writeEdgeToFile(e2);
//		Edge<Building> e3 = new Edge<Building>(320,v3,v2);
//		IOHandling.writeEdgeToFile(e3);
//		Edge<Building> e4 = new Edge<Building>(220,v4,v2);
//		IOHandling.writeEdgeToFile(e4);
		/*------------------------------------------------------------------------*/
		
		/*--------------------CREATING ARRAYLIST -----------------------------------*/
//		ArrayList<Edge<Building>> eList = new ArrayList<Edge<Building>>();
//		eList.add(e1);
//		eList.add(e2);
//		eList.add(e3);
//		eList.add(e4);
//		
//		ArrayList<Vertex<Building>> vList = new ArrayList<Vertex<Building>>();
//		vList.add(v1);
//		vList.add(v2);
//		vList.add(v3);
//		vList.add(v4);
		/*--------------------------------------------------------------------------*/
	
		/*--------------------CREATING GRAPH ---------------------------------------*/
//		System.out.println("_________________________________________________");
//		Graph<Building> graph = new Graph<Building> (vList, eList);
//		System.out.println(graph.toString());
//		System.out.println("_________________________________________________");
		/*--------------------------------------------------------------------------*/
		
		/*--------------------CREATING GRAPH WITH IOHANDLING -----------------------*/
//		
//		IOHandling.writeVertex(v0);													//added the vertices to file after the edges had been done else it didn't add properly
//		IOHandling.writeVertexToFile(v1);											//added the vertices to file after the edges had been done else it didn't add properly
//		IOHandling.writeVertexToFile(v2);
//		IOHandling.writeVertexToFile(v3);
//		IOHandling.writeVertexToFile(v4);
		
//		ArrayList<Edge<Building>> eeList = IOHandling.readEdge();
//		ArrayList<Vertex<Building>> vvList= IOHandling.readVertex();
//		System.out.println("--------------------this is the edges from file -----------------------");
//		System.out.println(eeList);
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the edges from system ---------------------");
//		System.out.println(eList);
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the vertices from file --------------------");
//		System.out.println(vvList);
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the vertices from system ------------------");
//		System.out.println(vList);
//		System.out.println("-----------------------------------------------------------------------");
//		//ArrayList<Graph<Building>> graph = IOHandling.readGraph();
//		Graph<Building> graph1 = new Graph<Building> (vList, eList);
//		IOHandling.writeGraph(graph1);
//		System.out.println("--------------------this is the graph from system ---------------------");
//		System.out.println(graph1.toString());
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the graph from File -----------------------");
//		System.out.println(g.toString());
//		System.out.println("-----------------------------------------------------------------------");
		/*--------------------------------------------------------------------------*/
		
		/*--------------------ADD INDIVIDUAL TO GRAPH ------------------------------*/
		/*Graph <Building> b = new Graph <Building> ();
		b.getVertices().add(v1);
		System.out.println(b.getVertices().toString());*/
		/*--------------------------------------------------------------------------*/
		
		
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