package ordering;

import java.util.Comparator;
import pro2.Edge;

public class OrderLexicographical extends EdgeOrdering{

    //file lexico
	//Order according to the alphabetical order of the subject.
	//Then according to the alphabetical order of the object.
	//Then alphabetical order of the label
	//O-S =>O-D=>O-L
	public OrderLexicographical() { 
	}
	
	@Override
	public int compare(Edge arg0, Edge arg1) {
		// Subject
		String src0 = (arg0.getSrc()).getLabel().toString();
		String src1 = (arg1.getSrc()).getLabel().toString();

		//Label: getting the whole label: labelName
		String lab0 = arg0.getLabel().toString();
		String lab1 = arg1.getLabel().toString();
		
		//Getting the Name
		//String lab0=label0.substring(5); 
		//String lab1=label1.substring(5);
					
		// Dest
		String dest0 = (arg0.getDest()).getLabel().toString();
		String dest1 = (arg1.getDest()).getLabel().toString();

		if(src0.compareTo(src1)==0) { // if src1=src2 
		 //Order according to the object
		if (dest0.compareTo(dest1)==0) { return (lab0.compareTo(lab1));}//order according to the label
		else{
				 return (dest0.compareTo(dest1));//order according to the object
				}
		}else {
				return (src0.compareTo(src1));//if src1 not equal src2
				}
		
				     
		}//end method
}//end class