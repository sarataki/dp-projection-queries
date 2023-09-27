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

public class Query023 {

	//Label Specific InDegree Distribution-[references]
	static final String inputFileName = "C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May///new.rdf";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Model model = ModelFactory.createDefaultModel();
			InputStream in = FileManager.get().open( inputFileName );
			if (in == null) {
			throw new IllegalArgumentException( "File: " + inputFileName + " not found");
			}
			model.read(in, "TURTLE");
			//compute indegree of nodes- nodes of indegree 0 are not listed- This will miss any sink or source nodes, 
			String queryString=" Select ?indegree (count(*) as ?count) WHERE{ SELECT ?o (count(*) as ?indegree) WHERE {?s <http://rdfanon.org/types#references> ?o }GROUP BY ?o ORDER BY DESC(?indegree)}Group By ?indegree ORDER BY (?indegree) ";
					
			System.out.println("--Q1:" + queryString);
			Query query = QueryFactory.create(queryString);
			QueryExecution qexec = QueryExecutionFactory.create(query, model);
			ResultSet results = qexec.execSelect() ;
			System.out.println("***********************");
			System.out.println("Label Specific Indegree---Count");
					for ( ; results.hasNext() ; )
					{
						QuerySolution soln = results.nextSolution() ;
						//RDFNode x1 = soln.get("o") ; 
						Literal x2_literal = ((Literal) soln.get("indegree"));
				        int x2 = x2_literal.getInt();	
				        Literal x3_literal = ((Literal) soln.get("count"));
				        int x3 = x3_literal.getInt();	  
						System.out.println(x2+" "+x3);
						//System.out.println(x1.toString()+" "+x2);
						
					}
					System.out.println("***********************");

				}
			}


