package labInDD;

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
import pro2.BoundedTweeted;
import pro2.Edge;
import pro2.Graph;
import ordering.OrderLexicographical;

public class TweetQLtw2 {
	/* QlOutedge Privacy+ TQL- Ql=tweeted bounding tweeted-order doesn't matter here take Lexic
	 label Specific Indegree Distribution [tweeted]on projected graph */
	
	public static void main(String[] args) {
		System.out.println("TweetQltw2");
		Graph myG = new Graph("tweets.rdf");
		System.out.println("UNBOUNDED EDGES");
		BoundedTweeted mBG = new BoundedTweeted(myG, new OrderLexicographical(),Integer.parseInt(args[0]));

		System.out.println("SORTED BOUNDED EDGES IN DEG BOUND:");

		Model model = ModelFactory.createDefaultModel();
		ArrayList<Edge> t = mBG.getEdges();

		for (int i = 0; i < t.size(); i++) {
			Resource subject = (Resource) (t.get(i)).getSrc().getLabel();
			Property predicate = t.get(i).getLabel(); // get the predicate
			RDFNode object = (t.get(i).getDest()).getLabel();
			model.add(subject, predicate, object);
		}
		try {
			// label Specific Indegree Distribution [tweeted]
			String queryString = " Select ?indegree (count(*) as ?count) WHERE{ SELECT ?o (count(*) as ?indegree) WHERE {?s <http://rdfanon.org/types#tweeted> ?o }GROUP BY ?o ORDER BY DESC(?indegree)}Group By ?indegree ORDER BY (?indegree) ";
			System.out.println("--Q1:" + queryString);
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query, model);
			ResultSet results = qexec.execSelect();
			System.out.println("Label Specific Indegree---Count--Noise Count");
			System.out.println(args[0] + "  " + "Count" + "  " + args[1] + "  " + args[2] + "   " + args[3] + "  "
					+ args[4] + "  " + args[5] + "  " + args[6]);
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				// RDFNode x1 = soln.get("o") ;
				Literal x2_literal = ((Literal) soln.get("indegree"));
				int x2 = x2_literal.getInt();

				Literal x3_literal = ((Literal) soln.get("count"));
				int x3 = x3_literal.getInt();
				
				// perturb the count which is x3
				int gs=2*(Integer.parseInt(args[0]));
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
					// finalAnsw=initial+ average distance
					finalAns[j] = x3 + average[j];
				}
				
				/* truncate double to 2 decimal places. Math.floor(value * 100) / 100;*/
				System.out.println(x2 + "  " + x3 + "   "  + Math.floor(finalAns[1] * 100) / 100 + "  " + Math.floor(finalAns[2] * 100) / 100 + "  " + Math.floor(finalAns[3] * 100) / 100 + "  "
						+ Math.floor(finalAns[4] * 100) / 100 + "  " + Math.floor(finalAns[5] * 100) / 100 + "  " + Math.floor(finalAns[6] * 100) / 100 );
			} // end of for
		} // end of try
		catch (NullPointerException e) {
			System.out.println("query returns null value ");
		}

	}// end of main
}// end of class
