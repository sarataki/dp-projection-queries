package pro2;

import java.io.BufferedReader;



import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;

public class Graph {


	protected HashSet<Node> nodes = new HashSet<Node>();

   
	 
	private ArrayList<Edge> edges = new ArrayList<Edge>();

	public Graph() {
		// TODO Auto-generated constructor stub
	}

	public Graph(HashSet<Node> nodes) {
		this.nodes = nodes;
	}
	/**
	 * Load a graph from a file where a line is an edge, srcLabel + "	" + edgeLabel + "	" destLabel (SNAP format + an edge label
	 * @param file representing a graph
	 */
	//Load the model from a file
	public Graph(String inputFileName) {
		Node src;
		Node dest;
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		//System.out.println("Reading the file");
		model.read(in, "TURTLE");
		//System.out.println("Here");
		
		//model.write(System.out);
		// list the statements in the Model
		StmtIterator iter = model.listStatements();

		// print out the predicate, subject and object of each statement
		while (iter.hasNext()) {
		    Statement stmt      = iter.nextStatement();  // get next statement
		    RDFNode  subject   = stmt.getSubject();     // get the subject
		    Property  predicate = stmt.getPredicate();   // get the predicate
		    RDFNode   object    = stmt.getObject();      // get the object
		    
		    
		    //adding the src ('tis a set, no need to check for existence)
			src = this.addNode(subject);
			//adding the dest
			dest = this.addNode(object);		
			
			//adding the edge
			this.addEdge(predicate, src, dest);
		}//end of while*/
	}//end of constructor
	

	
	public HashSet<Node> getNodes() {
		return nodes;
	}

	public void setNodes(HashSet<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	/**
	 * Print all the edges
	 */
	public void printEdges() {
		for(Edge e : edges) System.out.println((e.getSrc()).getLabel().toString() + " " + e.getLabel() + " " + (e.getDest()).getLabel());
	}
	
	//Create a node and add it to the graph @param label label of the node to create @return the created node
	 
	public Node addNode(RDFNode label) {
		Node n = new Node(label);
		nodes.add(n);
		return n;
	}
	

	
	public Edge addEdge(Property label, Node src, Node dest) {
		Edge e = new Edge(label, src, dest);
		edges.add(e);
		return e;
	}
	
	

}

