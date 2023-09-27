package outdeg;

import java.io.InputStream;


import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class MaxRef {
	// Compute Max label Specific Outdegree [references]
	static final String inputFileName = "tweets.rdf";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		****************** READ FILE **********************
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		
//		****************** QUERY **********************
		 try {
             String queryString=" Select (Max(?outdegree) as ?maxoutdegree) WHERE{ Select ?outdegree (count(*) as ?count) WHERE{ SELECT ?s  (count(*) as ?outdegree) WHERE{ ?s <http://rdfanon.org/types#references> ?o}Group By ?s ORDER BY DESC(?outdegree)} Group By ?outdegree ORDER BY (?outdegree)}";	
      		System.out.println("--Q1:" + queryString);
      		Query query = QueryFactory.create(queryString);
      		QueryExecution qexec = QueryExecutionFactory.create(query, model);
      		ResultSet results = qexec.execSelect() ;
      		System.out.println("***********************");
      		System.out.println("Max Outdegree of Ref is: ");
      		for ( ; results.hasNext() ; )
      		{
      			QuerySolution soln = results.nextSolution() ;
     			Literal x2_literal = ((Literal) soln.get("maxoutdegree"));
     	        int x2 = x2_literal.getInt();	
     	         
     	        System.out.println(x2);
      	        System.out.println("***********************");
      		}
         }//end of try
         catch (NullPointerException e) {
            System.out.println("query returns null value ");}
    
}//end of main	
}//end of class
      	        
      	        