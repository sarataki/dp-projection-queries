package UserRef;

import java.io.InputStream;
import org.apache.jena.vocabulary.RDF;
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

public class UserRef {
	//Find how many users each user has referenced (when >0)
	static final String inputFileName = "tweets.rdf";
	//static final String inputFileName ="C://Users/staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//2nd year//Oct//new.rdf";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("User Ref");
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
//		****************** QUERY **********************	
		try {
			// case1: Find how many users each user has referenced (when >0)
			String queryString = "SELECT ?user (count(?referencedUser) as ?c) WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet. ?tweet <http://rdfanon.org/types#references> ?referencedUser.} GROUP BY ?user HAVING (?c > 0) ORDER BY (?c)"; 
			System.out.println("--Q1:" + queryString);
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query, model);
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				
				RDFNode x1 = soln.get("user") ;     
				Literal x2_literal = ((Literal) soln.get("c"));
		        int x2 = x2_literal.getInt();
				System.out.println(x1.toString()+" "+x2);

			}
		} // end of try
		catch (NullPointerException e) {
			System.out.println("query returns null value ");
		}

	}// end of main
}// end of class
