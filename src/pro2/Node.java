package pro2;

import org.apache.jena.rdf.model.RDFNode;

public class Node {

	RDFNode label;

	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(RDFNode label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	public RDFNode getLabel() {
		return this.label;
	}


}