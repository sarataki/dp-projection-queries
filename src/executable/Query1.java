package executable;
import org.apache.jena.rdf.model.*;

import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.query.* ;
import org.apache.jena.reasoner.*;

import java.io.*;
public class Query1 {
	
	static final String inputFileName = "C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//Benjamin - Copy//tweets_small.rdf";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		****************** READ FILE **********************

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		model.write(System.out);
		
//		****************** QUERY **********************
		//Select the user and what he/she tweeted
      	String queryString = "SELECT ?user ?tweet WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet}";
		System.out.println("--Q1:" + queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect() ;
		System.out.println("***********************");
		for ( ; results.hasNext() ; )
		{
			QuerySolution soln = results.nextSolution() ;
			RDFNode x1 = soln.get("user") ;     
			RDFNode x2 = soln.get("tweet") ;     
			System.out.println(x1.toString()+" "+x2.toString());
		}
		System.out.println("***********************");

	}
}

