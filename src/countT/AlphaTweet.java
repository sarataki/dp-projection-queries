package countT;

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
import ordering.OrderAlphabeticalOrderS;
import pro2.BoundedOutDegree;
import pro2.Edge;
import pro2.Graph;

public class AlphaTweet {
	//How many users tweeted more than 25 times
  //Outedge Privacy+TO Bound outdegree of nodes- OrderAlphabet

	public static void main(String[] args) {
		System.out.println("AlphaTweet");
		Graph myG = new Graph("tweets.rdf");
		System.out.println("UNBOUNDED EDGES");

		// according to the Alphab
		BoundedOutDegree mBG = new BoundedOutDegree(myG, new OrderAlphabeticalOrderS(), Integer.parseInt(args[0]));

		Model model = ModelFactory.createDefaultModel();
		ArrayList<Edge> t = mBG.getEdges();

		for (int i = 0; i < t.size(); i++) {
			Resource subject = (Resource) (t.get(i)).getSrc().getLabel();
			Property predicate = t.get(i).getLabel(); // get the predicate
			RDFNode object = (t.get(i).getDest()).getLabel();
			model.add(subject, predicate, object);
		}
		try {
			//Find how many users tweeted more than 200 tweets
			String queryString = "Select (count(?user) as ?countuser) WHERE {SELECT ?user (count(?tweet) as ?c) WHERE {?user <http://rdfanon.org/types#tweeted> ?tweet} GROUP BY ?user HAVING (?c >25)}";
			System.out.println("--Q1:" + queryString);
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query, model);
			ResultSet results = qexec.execSelect();
			System.out.println("***********************");
			System.out.println("Count of users on projected graph without noise--Count of users with noise ");
			System.out.println("Outdegree bound=" + args[0] + "  " + "epsilons=" + "  " + args[1] + "  " + args[2]
					+ "   " + args[3] + "  " + args[4] + "  " + args[5] + "  " + args[6]);
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				Literal x2_literal = ((Literal) soln.get("countuser"));
				int x3 = x2_literal.getInt();

				// Noise Additon part: GS=1
				int gs = 1;

				// distance to store summation of distances from perturbed to initial answer
				double[] distance = new double[7];
				for (int j = 1; j < 7; j++) {
					for (int i = 0; i < 100; i++) {
						LaplaceNoise l = new LaplaceNoise(gs / Float.parseFloat(args[j]));
						// perturbed= initial answer x3+ noise
						double perturbed = l.perturb(x3);

						// Distance from perturbed to initial answer
						double dis = Math.abs(perturbed - x3);
						distance[j] = distance[j] + dis;
					}
				} // end of first for

				double[] average = new double[7];
				for (int j = 1; j < 7; j++) {
					average[j] = distance[j] / 100;
				}
				double[] finalAns = new double[7];
				for (int j = 1; j < 7; j++) {
					// finalAnsw= initial+ average distance
					finalAns[j] = x3 + average[j];
				}	
				System.out.println(x3 + "   " + Math.floor(finalAns[1] * 100) / 100 + "  " + Math.floor(finalAns[2] * 100) / 100 + "  " + Math.floor(finalAns[3] * 100) / 100 + "  "
						+ Math.floor(finalAns[4] * 100) / 100 + "  " + Math.floor(finalAns[5] * 100) / 100 + "  " + Math.floor(finalAns[6] * 100) / 100 );
			}
		} // end of try
		catch (NullPointerException e) {
			System.out.println("query returns null value ");
		}

	}// end of main
}// end of class
