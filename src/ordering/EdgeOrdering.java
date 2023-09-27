package ordering;

import java.util.Comparator;


import pro2.Edge;


 // Abstract class root of all the other edge orders.

public abstract class EdgeOrdering implements Comparator<Edge> {

	
	public abstract int compare(Edge arg0, Edge arg1);
}
