package indeg;

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

import executable.LaplaceNoise;

public class Test10 {
	//Compute Indegree distribution on initial graph
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
		//model.write(System.out);
		
//		****************** QUERY **********************
		 try {
	            String queryString=" Select ?indegree (count(*) as ?count) WHERE{ SELECT ?o (count(*) as ?indegree) WHERE {?s ?p ?o }GROUP BY ?o ORDER BY DESC(?indegree)}Group By ?indegree ORDER BY (?indegree) ";
	      		System.out.println("--Q1:" + queryString);
	      		Query query = QueryFactory.create(queryString);
	      		QueryExecution qexec = QueryExecutionFactory.create(query, model);
	      		ResultSet results = qexec.execSelect() ;
	      		System.out.println("***********************");
	      		System.out.println("InDegree Distribution: Indegree---Count");
	      		for ( ; results.hasNext() ; )
	      		{
	      			QuerySolution soln = results.nextSolution() ;
	     			Literal x2_literal = ((Literal) soln.get("indegree"));
	     	        int x2 = x2_literal.getInt();	  
	     	        
	     	        Literal x3_literal = ((Literal) soln.get("count"));
	     	        int x3 = x3_literal.getInt();	  
					System.out.println(x2+" "+x3); 
	      		}//end of for
	             }//end of try
	             catch (NullPointerException e) {
	                System.out.println("query returns null value ");}
	}//end of main	
	}//end of class