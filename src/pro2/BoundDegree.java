package pro2;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import ordering.EdgeOrdering;

//class to bound max degree of a graph
/**
*Algorithm to bound Degree of a graph
*Input: Graph, Bound degree, set of edges
*first sort the edges according to a specific sort
*then start inserting edges whenever inserting edges do not violate our condition of bound
*/
public class BoundDegree extends Graph {

	protected int bound; //bound degree of the graph
	
	/**
	 * Storing the value related to the constraint for each node (i.e. here the degree)
	 */
	protected Map<Node, Integer> constraint;
	
	
	public BoundDegree() {
		// TODO Auto-generated constructor stub
	}


	/**Algorithm to bound degree of the graph Input: Graph to project,bound degree,EdgeOrder allowing to sort edges
	 * Construct a bounded graph with edge addition
	 * @param graph initial graph
	 * @param order the order used to sort the edges
	 * @param b bound used for constraint checking
	 * 
	 * */
	public BoundDegree(Graph graph, EdgeOrdering order, int b) {
		this(graph, order, b, true);	
	}
	

	/**Algorithm to bound degree of the graph Input: Graph to project,bound degree,EdgeOrder allowing to sort edges
	 * Construct a bounded graph with edge addition
	 * @param graph initial graph
	 * @param order the order used to sort the edges
	 * @param b bound used for constraint checking
	 * @param init whether the edges should be initialized
	 * */
	public BoundDegree(Graph graph, EdgeOrdering order, int b, boolean init) {
		super(graph.getNodes());//get nodes of the graph return HashSet<Node> 
		this.bound = b; //degree
		if(init) initEdges(graph.getEdges(), order);
	}//constructor
	
	/**
	 * fill the graph with edges while respecting the constraint.
	 * @param edges initial edges to be re-inserted
	 * @param order order used to sort edges
	 */
	public void initEdges(ArrayList<Edge> edges, EdgeOrdering order) {
		//getting the edges return ArrayList<Edge>
		ArrayList<Edge> sortedEdges = edges;
		
		sortedEdges.sort(order);//Sorting the edges according to the order in the class that extends  abstract class EdgeOrder
		
		
		//Init the map, all node are of deg 0
		constraint= new HashMap<Node,Integer>();
		for(Node n : this.getNodes()) constraint.put(n, 0);
		
		//Iterating over the sorted edges and trying to add them
		for(Edge e : sortedEdges) if(!violateBound(e)) {
			addEdge(e);

		}
	}
	
	/**
	 * Test whether the addition of some edge would lead to a bound violation.
	 * @param e edge whose addition to test
	 * @return true iff we can't add the edge
	 */
	public boolean violateBound(Edge e) {
		return (this.constraint.get(e.getSrc()) <bound && this.constraint.get(e.getDest()) <bound);
	}
	
	/**
	 * Add an edge to the set of edges and update the contraint data structure
	 */
	
    public void addEdge(Edge e) {
    	super.getEdges().add(e);
		constraint.put(e.getDest(),constraint.get(e.getDest())+1);
		constraint.put(e.getSrc(),constraint.get(e.getSrc())+1);	
    }
	

}
