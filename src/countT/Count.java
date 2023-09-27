package countT;

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


public class Count {
	//How many users tweeted more than 25 times on initial graph?
	static final String inputFileName = "tweets.rdf";

	public static void main(String[] args) {
		System.out.println("Count");
		
//		****************** READ FILE **********************

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		//model.write(System.out);
		try {
			//Find how many users tweeted more than 25 tweets
			String queryString = "Select (count(?user) as ?countuser) WHERE {SELECT ?user (count(?tweet) as ?c) WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet} GROUP BY ?user HAVING (?c >25)}";
			System.out.println("--Q1:" + queryString);
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query, model);
			ResultSet results = qexec.execSelect();
			System.out.println("***********************");
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				Literal x2_literal = ((Literal) soln.get("countuser"));
				int x3 = x2_literal.getInt();
				System.out.println(x3);
			}
		} // end of try
		catch (NullPointerException e) {
			System.out.println("query returns null value ");
		}

	}// end of main
}// end of class
