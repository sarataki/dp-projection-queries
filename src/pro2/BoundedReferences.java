package pro2;
import java.util.ArrayList;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//import pro2.Edge;
import ordering.EdgeOrdering;


public class BoundedReferences extends BoundDegree {

		/**
		 * Set of labels to be considered (QL)
		 * Default value is the singleton "http://rdfanon.org/types#tweeted"
		 * http://rdfanon.org/types#references
		 */
	    Set<String> labels = new HashSet<>(Arrays.asList("http://rdfanon.org/types#tweeted"));
	   //Set<String> labels = new HashSet<>(Arrays.asList("http://rdfanon.org/types#references"));
		public BoundedReferences() {
		}
		
		/**
		 * Construct a ql out degree graph with the default value for QL
		 * @param graph the initial graph
		 * @param order the order used to sort the edges
		 * @param b the bound
		 */
		public BoundedReferences(Graph graph, EdgeOrdering order, int b) {
			super(graph, order, b);
		}
		
		/**
		 * Construct a ql out degree graph
		 * @param graph the initial graph
		 * @param order the order used to sort the edges
		 * @param b the bound
		 * @param labels the set QL of labels to consider
		 */
		public BoundedReferences(Graph graph, EdgeOrdering order, int b, Set<String> labels) {
			super(graph, order, b, false);
			this.labels = labels;
			this.initEdges(graph.getEdges(), order);
		}
		
		
		
		/*public boolean violateBound(Edge e) {
			boolean isQl = labels.contains(e.getLabel().toString());
			return (!isQl || constraint.get(e.getSrc())<bound);
			}*/
			
		@Override
		public boolean violateBound(Edge e) {
			if ( !e.getLabel().toString().equals("http://rdfanon.org/types#references")) return false;
			if (constraint.get(e.getSrc()) < bound && e.getLabel().toString().equals("http://rdfanon.org/types#references")) { return false;}
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
	    }
		
	}
