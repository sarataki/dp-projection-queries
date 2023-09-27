package PER;

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
//this to compute PER metric in the original graph
//The query is MaxOutDegree that we want to work with and study it under after projection 
//Query: compute the number of edges in the original graph
public class MaxOut {
	static final String inputFileName = "tweets.rdf";
	public static void main(String[] args) {
//		****************** READ FILE **********************
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		
//		****************** QUERY **********************
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
      	        
      	        