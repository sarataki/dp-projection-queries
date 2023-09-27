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
import org.apache.jena.util.FileManager;

public class Query020 {
	//OutDegree Distribution
		static final String inputFileName = "C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//new.rdf";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
				Model model = ModelFactory.createDefaultModel();
				InputStream in = FileManager.get().open( inputFileName );
				if (in == null) {
				throw new IllegalArgumentException( "File: " + inputFileName + " not found");
				}
				model.read(in, "TURTLE");
				//model.write(System.out);
				//compute outdegree
				String queryString=" Select ?outdegree (count(*) as ?count) WHERE{ SELECT ?s  (count(*) as ?outdegree) WHERE{ ?s ?p ?o}Group By ?s ORDER BY DESC(?outdegree)} Group By ?outdegree ORDER BY (?outdegree)";	
				
				//total number of nodes in the graph 2675 nodes
				System.out.println("--Q1:" + queryString);
				Query query = QueryFactory.create(queryString);
				QueryExecution qexec = QueryExecutionFactory.create(query, model);
				ResultSet results = qexec.execSelect() ;
				System.out.println("***********************");
				System.out.println("Outdegree---Count");
				for ( ; results.hasNext() ; )
				{
					QuerySolution soln = results.nextSolution() ;
					//RDFNode x1 = soln.get("o") ; 
					Literal x2_literal = ((Literal) soln.get("outdegree"));
			        int x2 = x2_literal.getInt();	
			        Literal x3_literal = ((Literal) soln.get("count"));
			        int x3 = x3_literal.getInt();	 
			       
					System.out.println(x2+" "+x3);
					
				}
				System.out.println("***********************");

			}
		}


