package executable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class Query010 {
	//what is the general sentiment of the tweets (all tweets) of a specific person (Here we take person MamiYessi) ?
	static final String inputFileName = "C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//mytweets.rdf";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//****************** READ FILE **********************
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
		throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}
		model.read(in, "TURTLE");
		//model.write(System.out);
		
		HashMap <Integer, Integer> h = new HashMap<Integer, Integer>();    
		List <Integer> sentiment=new ArrayList<Integer>();
		
		String queryString1 = "SELECT ?emotion (Count(?tweet) As ?ctweet ) WHERE { <http://rdfanon.org/types#MamiYessi> <http://rdfanon.org/types#tweeted> ?tweet. ?tweet <http://rdfanon.org/types#emotion> ?emotion. }Group By ?emotion";	
		QueryExecution qexec = QueryExecutionFactory.create(queryString1, model);
		ResultSet results = qexec.execSelect() ;
		System.out.println("***********************");
		for ( ; results.hasNext() ; )
		{
			QuerySolution soln = results.nextSolution() ;
			//RDFNode x1 = soln.get("user") ;     
			Literal x2_literal = ((Literal) soln.get("emotion"));
			Literal x3_literal = ((Literal) soln.get("ctweet"));
			int x2 = x2_literal.getInt();	
			int x3 = x3_literal.getInt();	
			//Query Output: emotion counttweet
			h.put(x2, x3);
		}
		System.out.println("***********************");
		
		
		 // Checking for the emptiness of Map to avoid no suchElement Exception
		if (h.isEmpty()==false) {
		// using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<Integer,Integer> entry : h.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                             ", Value = " + entry.getValue());}
        
        //getting max count 
        int maxValueInMap=Collections.max(h.values());
        //System.out.println(maxValueInMap);
        
       //what is the sentiment of the tweets of a specific person
        
        for (Map.Entry<Integer,Integer> entry : h.entrySet()) {
        	 if (entry.getValue().equals(maxValueInMap)) {
        		sentiment.add (entry.getKey());
        	 }}
        
        
        //Iterate through the sentiment
        System.out.println("The sentiment of the tweets of this  person in general is");
      
        for(int i=0; i<sentiment.size();i++) {
        System.out.println(sentiment.get(i));}
		}
}
}
