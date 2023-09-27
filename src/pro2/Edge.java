package pro2;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

public class Edge {

	private final Node src;
	private final Node dest;
	private final Property label;
	

	
	public Edge(Property label, Node src, Node dest) {
		this.label=label;
		this.src=src;
		this.dest = dest;
	}


	public Node getSrc() {
		return src;
	}


	public Node getDest() {
		return dest;
	}
	
	public Property getLabel() {
		return this.label;
	}

}
