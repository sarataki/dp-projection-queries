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

import executable.LaplaceNoise;


public class CountLap {
	//initial graph+Lap noise; arg[0] is the first epsilon value.
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
				int gs = 1;
				
				// distance to store summation of distances from perturbed to initial answer
				double[] distance = new double[7];
				for (int j = 0; j < 6; j++) {
					for (int i = 0; i < 100; i++) {
						LaplaceNoise l = new LaplaceNoise(gs / Float.parseFloat(args[j]));
						// perturbed= initial answer x3+ noise
						double perturbed = l.perturb(x3);

						// Distance from perturbed to initial answer
						double dis = Math.abs(perturbed - x3);
						distance[j] = distance[j] + dis;
					}
				} // end of first for

				double[] average = new double[6];
				for (int j = 0; j < 6; j++) {
					average[j] = distance[j] / 100;
				}
				double[] finalAns = new double[6];
				for (int j = 0; j < 6; j++) {
					// finalAnsw= initial+ average distance
					finalAns[j] = x3 + average[j];
				}	
				System.out.println(x3 + "   " + Math.floor(finalAns[0] * 100) / 100 + "  " + Math.floor(finalAns[1] * 100) / 100 + "  " + Math.floor(finalAns[2] * 100) / 100 + "  "
						+ Math.floor(finalAns[3] * 100) / 100 + "  " + Math.floor(finalAns[4] * 100) / 100 + "  " + Math.floor(finalAns[5] * 100) / 100 );
			}
		} // end of try
		catch (NullPointerException e) {
			System.out.println("query returns null value ");
		}

	}// end of main
}// end of class
