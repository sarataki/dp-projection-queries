import java.io.File;  
// Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.HashMap;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Vector;

public class Parser {
	  private static String fileName="C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//FullDataset//training.1600000.processed.noemoticon.csv"; // full dataset
	  //private static String fileName="C://Users//staki//Dropbox//My PC (eichler-doc-01)//Desktop//My files//PhD//May//Benjamin - Copy//testdata.manual.2009.06.14.csv"; // small set (498 lines
	 //private static String fileName="C:/tmp/trainingandtestdata/testdata.manual.2009.06.14.csv"; // small set (498 lines)
	//private static String fileName="C:/tmp/trainingandtestdata/training.1600000.processed.noemoticon.csv"; // full dataset

	// Using sentiment140 data set
	 
		public static Vector<Tuple> data = new Vector<Tuple>();
		public static HashMap<String, String[]> tweetReferences = new HashMap<String, String[]>(); // the list of people referenced in a tweet
		public static HashMap<String, String[]> references = new HashMap<String, String[]>();  // the list of people references by the user via a tweet (appear multiple times if several references)
		public static String[] atts = {"emotion", "id", "date", "query", "user", "text"};
	
	public static void parse() {

	Tuple t=new Tuple(atts);
	    try {
	        File myObj = new File(fileName);
	        Scanner myReader = new Scanner(myObj);
	        int currentLine = 0;
	        while (myReader.hasNextLine()) {
	        	// Lecture de la ligne et traitement des données compte tenu du format de fichier
	          String line = myReader.nextLine();
	          String[] values = line.split("\",\"");
	          values[0] = values[0].substring(1);
	          values[values.length-1] = values[values.length-1].substring(0, values[values.length-1].length()-1) ;
	          t = new Tuple(atts);
	          for(int i=0;i<Tuple.size();i++) {
	        	  System.out.print(values[i]+" -- ");
	        	  t.put(atts[i], values[i]);
	          }
        	  data.add(t);
	          // Création des références
	          System.out.println();
	          String[] referenceString = values[values.length-1].split("@");
	          String[] targets = new String[referenceString.length-1];
	          for(int i=1;i<referenceString.length;i++) {
	        	  try {
	        		  String handle = referenceString[i].split("\\s")[0]; // split on anything that is not a letter || or space (otherwise space is left ...)

	        		  try{
	        			  handle = handle.split("[^a-zA-Z0-9]")[0];
	        			  System.out.print(handle+" / ");
	        			  targets[i-1] = handle;
	        		  }
	        		  catch(Exception e) {
	        			  targets[i-1] = "error";
	        		  }
	        	  }
	        	  catch(Exception e) {
	        		  // if error here, it means the @ was part of an emoticon or something, and has nothing behind it. So we do nothing
	        	  }
	          }
	          // Store the tweet info
	          tweetReferences.put(data.elementAt(currentLine).get("id"), targets);
	          
	          // Manage the fact that the user has already referenced other people
	          String[] alreadyEntered = references.get(data.elementAt(currentLine).get("user"));
	          if(alreadyEntered==null) {
	        	  references.put(data.elementAt(currentLine).get("user"), targets);
	          }else if (targets.length>0){
	        	// merge old list with new list 
	        	  String[] newList = new String[alreadyEntered.length+targets.length];
	        	  for(int i=0;i<alreadyEntered.length;i++){
	        		  newList[i] = alreadyEntered[i];
	        	  }
	        	  for(int i=0;i<targets.length;i++){
	        		  newList[i+alreadyEntered.length] = targets[i];
	        	  }
	        	  references.put(data.elementAt(currentLine).get("user"), newList);
	          }
	          // Fin création des références
	          System.out.println();
	          currentLine++;
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    
	    // Lister les liens
	    for(int i=0;i<data.size();i++) {
	    	String user = data.elementAt(i).get("user");
	    	System.out.println(user);
	    	for(int j=0;j<references.get(user).length;j++) {
	    		System.out.println("\t "+j+" : "+references.get(user)[j]);
	    	}
		    System.out.println();
	    }
	    
	    // Lister les liens par tweet
	    for(int i=0;i<data.size();i++) {
	    	String id = data.elementAt(i).get("id");
	    	System.out.println(id);
	    	for(int j=0;j<tweetReferences.get(id).length;j++) {
	    		System.out.println("\t "+j+" : "+tweetReferences.get(id)[j]);
	    	}
		    System.out.println();
	    }
	    
	    System.out.println("Numer of lines : "+data.size()+" Number of tweets (should be the same) : "+tweetReferences.size()+" Number of users : "+references.size());
	}
	

}
