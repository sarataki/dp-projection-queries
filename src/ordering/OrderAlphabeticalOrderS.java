package ordering;

import java.util.Comparator;


import pro2.Edge;

public class OrderAlphabeticalOrderS extends EdgeOrdering {
	//file alphab
		//O-S =>O-L=>O-D
		//Order according to the alphabetical order of the subject.If equal subject, order acc to alpha order of the label
		//If equal labels, order acc to alpha order of the object
		@Override
		public int compare(Edge arg0, Edge arg1) {
			//If the return value of compareTo() is greater than 0, it has to be swapped in position
			//if lab0> lab1 then return value will be positive then swap lab0 and lab1
			// Subject
			String src0 = (arg0.getSrc()).getLabel().toString();
			String src1 = (arg1.getSrc()).getLabel().toString();

			//Label: getting the whole label: labelName
			String lab0 = arg0.getLabel().toString();
			String lab1 = arg1.getLabel().toString();
	
						
			// Dest
			String dest0 = (arg0.getDest()).getLabel().toString();
			String dest1 = (arg1.getDest()).getLabel().toString();

		   if(src0.compareTo(src1)==0) { // if subject1=subject2 
		     //Order according to the label
			   if (lab0.compareTo(lab1)==0) { return dest0.compareTo(dest1);}//order according to the object
			   else{
				   return lab0.compareTo(lab1);//order according to the label
			   }
		   }else {
			   return src0.compareTo(src1);//order acc o the subj
		   }
		     
		}//end method
	}//end class
