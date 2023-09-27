package PER;

import java.util.ArrayList;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

import executable.LaplaceNoise;
import ordering.OrderLexicographical;
import pro2.BoundedTweeted;
import pro2.Edge;
import pro2.Graph;

public class MaxOutTweet {
	/*Compute maxOutdegree of nodes after projection. GS=D
	 * QLOutedgePrivacy+ TQL (BoundQLOutdegree)- QL=tweeted
	Projection while bounding max QLoutDegree of nodes- EdgeOrdering: OrderLexicographical*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("MaxOutDegree- MaxOutTweet");
		Graph myG = new Graph("tweets.rdf");
		System.out.println("UNBOUNDED EDGES");

		System.out.println("SORTED UNBOUNDED EDGES Accroding to the lexicographical order");
		// according to the lexicographical order
		BoundedTweeted mBG = new BoundedTweeted(myG, new OrderLexicographical(), Integer.parseInt(args[0]));

		System.out.println("SORTED BOUNDED EDGES IN DEG BOUND:");
		// mBG.printEdges(); //printing added edges

		Model model = ModelFactory.createDefaultModel();
		ArrayList<Edge> t = mBG.getEdges();

		for (int i = 0; i < t.size(); i++) {
			Resource subject = (Resource) (t.get(i)).getSrc().getLabel();
			Property predicate = t.get(i).getLabel(); // get the predicate
			RDFNode object = (t.get(i).getDest()).getLabel();
			model.add(subject, predicate, object);
		}

		 try {
             String queryString=" Select (COUNT(*) as ?Triples) WHERE{?s ?p ?o }";	
      		System.out.println("--Q1:" + queryString);
      		Query query = QueryFactory.create(queryString);
      		QueryExecution qexec = QueryExecutionFactory.create(query, model);
      		ResultSet results = qexec.execSelect() ;
      		System.out.println("***********************");
      		for ( ; results.hasNext() ; )
      		{
      			QuerySolution soln = results.nextSolution() ;
     			Literal x2_literal = ((Literal) soln.get("Triples"));
     	        int x2 = x2_literal.getInt();	
     	         
     	        System.out.println(x2);
      	        System.out.println("***********************");
      		}
         }//end of try
         catch (NullPointerException e) {
            System.out.println("query returns null value ");}
    
}//end of main	
}//end of class