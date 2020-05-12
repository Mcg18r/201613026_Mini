package FileHandling;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import Object.Building;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * @author 201613026
 *A Class to handling all my database files
 */
public class IOHandling {

	private File GetFileFromJar(String FilePath) {
		File tempFile = new File(FilePath);
		if (!tempFile.exists()) {
			InputStream in = getClass().getResourceAsStream(FilePath);
			tempFile = createFileFromStream(in, FilePath);
			if (tempFile == null) {
				System.err.println("The file is null");
				return null;
			} else {
				System.out.println("The file exists");
			}

		}
		return tempFile;
	
	}
	
	public File createFileFromStream(InputStream fileStream, String FilePath) {
		File file = new File(FilePath);
		try {
			Files.copy(fileStream, file.toPath());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return file;
	}
	/**
	 * method that reads the building file and return an ArrayList of Buildings
	 * @return
	 */
	@SuppressWarnings("null")
	public ArrayList<Building> readBuilding()
	{
		ArrayList<Building> build = new ArrayList<Building>();
		try
		{
			File f = GetFileFromJar("data/Building.dat");
			//File f = new File("data/Building.dat");
			//InputStream is = this.getClass().getResourceAsStream( "data/Building.dat" );;
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			//ObjectInputStream os = new ObjectInputStream(is);
//			while(os!=null)
//			{
//				Building building = (Building) os.readObject();
//				build.add(building);
//				System.out.println(building);
//			}
//			os.close();
			while(ois!=null)
			{
				Building building = (Building) ois.readObject();
				build.add(building);
				//System.out.println(building);
			}
			ois.close();
			
		}catch (EOFException e)
		{
			System.out.println("End OF File");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
	        e.printStackTrace();
	    }
		return build;
	}
	
	
	/**
	 * Will write a building into a file by overwriting the file
	 * @param building
	 */
	public void writeBuilding(Building building)
	{
		try {
			FileOutputStream fos = new FileOutputStream("data/Building.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(building);
			System.out.println("Done");
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Will write a building into a file by appending file
	 * @param building
	 */
	public void writeBuildingToFile(Building building)
	{
		ArrayList<Building> build= readBuilding();
		
		boolean con=true;
		
		
		if(build.size()>=1)
		{
			
			for(Building b:build)
			{
				if(b.equals(building))
				{
					System.out.println("Building already exists");
					con = false;
				}
			}
		}
		if(con!=false)
		{
			File f = GetFileFromJar("data/Building.dat");
			//File f = new File("data/Building.dat");
			FileOutputStream fos;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);
				
				build.add(building);
				
				for (Building b:build)
				{
						oos.writeObject(b);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(oos!=null)
				{
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
			
		}
		
	}

	/**
	 * method that reads the vertex file and return an ArrayList of vertices
	 * @return
	 */
	@SuppressWarnings("null")
	public static ArrayList<Vertex<Building>> readVertex()
	{
		ArrayList<Vertex<Building>> build = new ArrayList<Vertex<Building>>();
		try
		{
			File f = new File("data/Vertex.dat");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while(ois!=null)
			{
				@SuppressWarnings("unchecked")
				Vertex<Building> vertex = (Vertex<Building>) ois.readObject();
				build.add(vertex);
				//System.out.println(vertex);
			}
			ois.close();
			
		}catch (EOFException e)
		{
			System.out.println("End OF File");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
	        e.printStackTrace();
	    }
		return build;
	}
	
	/**
	 * Will write a vertex to a file by overwriting file
	 * @param vertex
	 */
	public static void writeVertex(Vertex<Building> vertex)
	{
		try {
			FileOutputStream fos = new FileOutputStream("data/Vertex.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(vertex);
			System.out.println("Done");
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO Check that the Vertex added is not a duplicate
	 * Will write a vertex into a file by appending file
	 * @param vertex
	 */
	public static void writeVertexToFile(Vertex<Building> vertex)
	{
		ArrayList<Vertex<Building>> ver= readVertex();
		
		boolean con=true;
		
		if(ver.size()>=1)
		{
			
			for(Vertex<Building> b:ver)
			{
				if(b.equals(vertex))
				{
					System.out.println("Vertex already exists");
					con = false;
				}
			}
		}
		if(con!=false)
		{
			File f = new File("data/Vertex.dat");
			FileOutputStream fos;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);
				
				ver.add(vertex);
				
				for (Vertex<Building> b:ver)
				{
						oos.writeObject(b);
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(oos!=null)
				{
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
			
		}
	}
	
	/**
	 * method that reads the Edge file and returns an ArrayList of Edges
	 * @return
	 */
	@SuppressWarnings("null")
	public static ArrayList<Edge<Building>> readEdge() 
	{
		ArrayList<Edge<Building>> build = new ArrayList<Edge<Building>>();
		try
		{
			File f = new File("data/Edge.dat");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while(ois!=null)
			{
				@SuppressWarnings("unchecked")
				Edge<Building> edge = (Edge<Building>) ois.readObject();
				build.add(edge);
				//System.out.println(edge);
			}
			ois.close();
			
		}catch (EOFException e)
		{
			System.out.println("End OF File");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
	        e.printStackTrace();
	    }
		return build;
		}

	/**
	 * Will write a Edge into a file by overwriting file
	 * @param edge
	 */
	public static void writeEdge(Edge<Building> edge)
	{
		try {
			FileOutputStream fos = new FileOutputStream("data/Edge.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(edge);
			System.out.println("Done");
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO Check that the edge added is not a duplicate
	 * Will write a edge into a file by appending file
	 * @param edge
	 */
	public static void writeEdgeToFile(Edge<Building> edge) 
	{
		ArrayList<Edge<Building>> ed= readEdge();
		
		boolean con=true;
		if(ed.size()>=1)
		{
			
			for(Edge<Building> b:ed)
			{
				if(b.equals(edge))
				{
					System.out.println("Edge already exists");
					con = false;
				}
			}
		}
		if(con!=false)
		{
			File f = new File("data/Edge.dat");
			FileOutputStream fos;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);
				
				ed.add(edge);
				
				for (Edge<Building> b: ed)
				{
						oos.writeObject(b);
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(oos!=null)
				{
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		}
	}

	/** method that reads the Edge file and returns an ArrayList of Edges
	 * @return
	 */
	@SuppressWarnings("null")
	public ArrayList<Graph<Building>> readGraph() 
	{
		ArrayList<Graph<Building>> build = new ArrayList<Graph<Building>>();
		try
		{
			File f = GetFileFromJar("data/Graph.dat");
			//File f = new File("data/Graph.dat");
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while(ois!=null)
			{
				@SuppressWarnings("unchecked")
				Graph<Building> graph = (Graph<Building>) ois.readObject();
				build.add(graph);
				//System.out.println(graph);
			}
			ois.close();
			
		}catch (EOFException e)
		{
			System.out.println("End OF File");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e) {
	        e.printStackTrace();
	    }
		return build;
		}

	/**
	 * Will write a Graph into a file by overwriting file
	 * @param graph
	 */
	public static void writeGraph(Graph<Building> graph)
	{
		try {
			FileOutputStream fos = new FileOutputStream("data/Graph.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(graph);
			System.out.println("Done");
			// closing resources
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO Check that the graph added is not a duplicate
	 * Will write a graph into a file by appending file
	 * @param graph
	 */
	public  void writeGraphToFile(Graph<Building> graph) 
	{
		ArrayList<Graph<Building>> build= readGraph();
		
		boolean con=true;
		if(build.size()>=1)
		{
			
			for(Graph<Building> b:build)
			{
				if(b.equals(graph))
				{
					System.out.println("Edge already exists");
					con = false;
				}
			}
		}
		File f = new File("data/Graph.dat");
		boolean exists = f.exists();
		
		if(con!=false && exists )
		{
			FileOutputStream fos;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);
				
				build.add(graph);
				
				for (Graph<Building> b: build)
				{
						oos.writeObject(b);
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(oos!=null)
				{
					try {
						oos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
		}else
		{
			try 
			{
				FileOutputStream fos = new FileOutputStream("data/Graph.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				// write object to file
				oos.writeObject(graph);
				System.out.println("Done");
				// closing resources
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

	
