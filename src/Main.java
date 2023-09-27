import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import com.github.jsonldjava.core.RDFDataset.Literal;

public class Main {
	
	public static void main(String[] args) {
		FileOutputStream fs;
		Model m = RDFGraphCreator.getRDFGraph();
		try {
			fs = new FileOutputStream("C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//FullDataset//dataset.rdf");

			m.write(fs);
			fs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/*String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX anon: <http://rdfanon.org/types#> SELECT ?a WHERE {?a rdf:type  anon:tweet}"; //<x:urn:rdfanon/tweet>
		//String queryString = "SELECT ?a ?b WHERE {?a <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?b}";
		//String queryString = "SELECT ?a WHERE {?a <urn:rdfanon/tweeted> ?b.}";
		System.out.println("--Q1:" + queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query,m);
		    ResultSet results = qexec.execSelect() ;
		    for ( ; results.hasNext() ; )
		    {
		      QuerySolution soln = results.nextSolution() ;
		      RDFNode x = soln.get("a") ;       // Get a result variable by name.
		      //Literal r = (Literal) soln.getLiteral("b") ; // Get a result variable - must be a resource
		     // System.out.println("r:"+r.getValue() + " x:"+x.toString());
		      System.out.println(" x:"+x.toString());
		      //Literal l = soln.getLiteral("VarL") ;   // Get a result variable - must be a literal
		    } */
	}  
}
