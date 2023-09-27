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

public class Query015 {

	static final String inputFileName = "C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//mytweets.rdf";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		//model.write(System.out);
		
//		****************** QUERY **********************
		//find the degree of each node ?x:
		String queryString = "SELECT ?x (count(*) as ?degree) WHERE {{?x ?p ?o}UNION {?s ?p2 ?x}} GROUP BY ?x order by desc(?degree) limit 10 "; 
		System.out.println("--Q1:" + queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect() ;
		System.out.println("***********************");
		for ( ; results.hasNext() ; )
		{
			QuerySolution soln = results.nextSolution() ;
			RDFNode x1 = soln.get("x") ;   
			Literal x2_literal = ((Literal) soln.get("degree"));
	        int x2 = x2_literal.getInt();
			System.out.println(x1.toString()+" "+x2);
		}
		System.out.println("***********************");

	}
}

