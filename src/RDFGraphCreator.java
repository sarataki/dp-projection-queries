import org.apache.jena.rdf.model.*;




import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.query.* ;
import org.apache.jena.reasoner.*;



public class RDFGraphCreator {

	public static Model getRDFGraph() {
		Parser.parse();
		//String NS = "urn:x-hp-jena:eg/";
		//public static String[] atts = {"emotion", "id", "date", "query", "user", "text"};
		String NS = "http://rdfanon.org/types#";
		//String NS = "";
		Model rdfsExample = ModelFactory.createDefaultModel();
		Property tweeted = rdfsExample.createProperty(NS+"tweeted");
		Property timestamp = rdfsExample.createProperty(NS+"timestamp");
		Property hasName = rdfsExample.createProperty(NS+"name");
		Property hasEmotion = rdfsExample.createProperty(NS+"emotion");
		Property references = rdfsExample.createProperty(NS+"references");
		Property hasText = rdfsExample.createProperty(NS+"text");
		
		//New inserted
		Property queryterm = rdfsExample.createProperty(NS+"queryterm");
		
		System.out.println("****************************************");
		System.out.println("GENERATING RDF MODEL");
		System.out.println("****************************************");
	
	    for(int i=0;i<Parser.data.size();i++) {
	    	String id = Parser.data.elementAt(i).get("id");
	    	String text = Parser.data.elementAt(i).get("text");
	    	String user = Parser.data.elementAt(i).get("user");
	    	String date = Parser.data.elementAt(i).get("date");
	    	String emotion = Parser.data.elementAt(i).get("emotion");
	    	
	    	//mew inserted
	    	String query= Parser.data.elementAt(i).get("query");
	    	
	    	Resource tweet = rdfsExample.createResource(NS+id);
	    	Resource tweetType = rdfsExample.createResource(NS+"tweet");
	    	rdfsExample.add(tweet, RDF.type, tweetType);
	    	rdfsExample.add(tweet, timestamp, date);
	    	rdfsExample.add(tweet, hasEmotion, emotion);
	    	rdfsExample.add(tweet, hasText, text);
	    	
	    	rdfsExample.add(tweet, queryterm, query);
	    	Resource userResource = rdfsExample.createResource(NS+user);
	    	rdfsExample.add(userResource, tweeted, tweet);
	    	rdfsExample.add(userResource, hasName, user);
	    	//rdfsExample.add(userResource, RDF.type, NS+"person");
	    	Resource person = rdfsExample.createResource(NS+"person");
	    	rdfsExample.add(userResource, RDF.type, person);
	    	
	    	//tweet.addProperty(RDF.type, NS+"tweet");
	    	

	    	for(int j=0;j<Parser.tweetReferences.get(id).length;j++) {
	    		rdfsExample.add(tweet, references, NS+Parser.tweetReferences.get(id)[j]);
	    	}

	    }
			
	    rdfsExample.write(System.out, "TURTLE");
	    //rdfsExample.write(System.out, "RDF/XML");
		return rdfsExample;
	}
}
