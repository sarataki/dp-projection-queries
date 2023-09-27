package executable;

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
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

public class Query012 {
	// case 1:Find how many users each user has referenced (when >0)
	// case 2:Find how many distinct users each user has referenced (when >0). just add DISTINCT--(count (DISTINCT ?referencedUser)
	static final String inputFileName = "C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//mytweets.rdf";
	public static void main(String[] args) {
		
//		****************** READ FILE **********************

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		model.write(System.out);
		
//		****************** QUERY **********************
		// case1: Find how many users each user has referenced (when >0)
		String queryString = "SELECT ?user (count(?referencedUser) as ?c) WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet. ?tweet <http://rdfanon.org/types#references> ?referencedUser.} GROUP BY ?user HAVING (?c > 0) ORDER BY (?c)"; 
		System.out.println("--Q1:" + queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect() ;
		System.out.println("***********************");
		for ( ; results.hasNext() ; )
		{
			QuerySolution soln = results.nextSolution() ;
			RDFNode x1 = soln.get("user") ;     
			Literal x2_literal = ((Literal) soln.get("c"));
	        int x2 = x2_literal.getInt();
			System.out.println(x1.toString()+" "+x2);
		}
		System.out.println("***********************");

	}
}
