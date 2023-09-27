package pro2;

import ordering.EdgeOrdering;

public class BoundedTR extends BoundDegree {

	public BoundedTR() {
		
	}
	public BoundedTR (Graph graph, EdgeOrdering order, int b) {
		super(graph, order, b);
	}
	
	@Override
	public boolean violateBound(Edge e) {
		if ( !e.getLabel().toString().equals("http://rdfanon.org/types#references") && !e.getLabel().toString().equals("http://rdfanon.org/types#tweeted") ) return false;
		if (constraint.get(e.getSrc()) < bound && e.getLabel().toString().equals("http://rdfanon.org/types#references")) { return false;}
		if (constraint.get(e.getSrc()) < bound && e.getLabel().toString().equals("http://rdfanon.org/types#tweeted")) { return false;}
		else { return true;}

	}

	/**
	 * Add an edge to the set of edges and update the contraint data structure
	 * @param e the edge to be inserted
	 */
	@Override
    public void addEdge(Edge e) {
    	super.getEdges().add(e);
    	//if(labels.contains(e.getLabel().toString())) constraint.put(e.getSrc(),constraint.get(e.getSrc())+1);	
    	if(e.getLabel().toString().equals("http://rdfanon.org/types#references")) constraint.put(e.getSrc(),constraint.get(e.getSrc())+1);	
    	if(e.getLabel().toString().equals("http://rdfanon.org/types#tweeted")) constraint.put(e.getSrc(),constraint.get(e.getSrc())+1);	
    }
	
}