package executable;

import java.io.IOException;
import java.io.InputStream;


import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

public class Query04 {
	//
	//Find the user(s) who tweeted the most
	
	static final String inputFileName = "C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//mytweets.rdf";
	final static String queryString1 = "SELECT (MAX(?c) As ?most ) WHERE{ SELECT ?user (count(?tweet) as ?c) WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet} Group By ?user}";	
	final static String queryString2 = "SELECT ?user WHERE{ SELECT ?user (count(?tweet) as ?c) WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet}Group By ?user Having(?c=?most)}";	
	
	//final static String queryString2 = "SELECT ?user ?name WHERE{ SELECT ?user (count(?tweet) as ?c) WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet. }Group By ?user Having(?c=?most)}";	
	public static void useInitialBindingsFromQuerySolution(Model model) throws IOException {
        System.out.println( "== useInitialBindingsFromQuerySolution ==" );
        // execute the query that finds a binding for ?most.  There should be just one 
        // query solution in the result set.
        final ResultSet results = QueryExecutionFactory.create( queryString1, model ).execSelect();
        //results contain 3
        final QuerySolution solution = results.next();
        
        
        // Use the single query solution from the result set as initial bindings for
        // the second query (which uses the variable ?most).
        //final ResultSet addressResults = QueryExecutionFactory.create(queryString2, model, solution ).execSelect();
        //ResultSetFormatter.out( addressResults );
        
      QueryExecution qexec= QueryExecutionFactory.create(queryString2, model, solution );
      ResultSet results1 = qexec.execSelect() ;
		System.out.println("***********************");
		for ( ; results1.hasNext() ; ) {
		QuerySolution soln = results1.nextSolution() ;
		
		//retrieve the user
        RDFNode x1 = soln.get("user") ; 
        System.out.println(x1.toString());
        
    }
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		//model.write(System.out);
		
		useInitialBindingsFromQuerySolution(model);
		
		
	}
}
