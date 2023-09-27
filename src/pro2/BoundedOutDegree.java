package pro2;

import java.util.ArrayList;

import java.util.HashSet;
import ordering.EdgeOrdering;


public class BoundedOutDegree extends BoundDegree{

	public BoundedOutDegree() {
		// TODO Auto-generated constructor stub
	}

	public BoundedOutDegree (Graph graph, EdgeOrdering order, int b) {
		super(graph, order, b);
		// TODO Auto-generated constructor stub
	}


	
	/**
	 * Test whether the addition of some edge would lead to a bound violation.
	 * @param e edge whose addition to test
	 * @return true iff we can't add the edge
	 */
	@Override
	public boolean violateBound(Edge e) {
		return !(this.constraint.get(e.getSrc()) <bound );
	}
	
	/**
	 * Add an edge to the set of edges and update the contraint data structure
	 */
	@Override
    public void addEdge(Edge e) {
    	super.getEdges().add(e);
		constraint.put(e.getSrc(),constraint.get(e.getSrc())+1);	
    }
	
	
}