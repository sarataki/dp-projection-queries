package ordering;
import pro2.Edge;

public class Priority2Labels extends EdgeOrdering {
	/* Giving priority to 2 labels to be at the beginning of the ordering
    O-L=> O-S =>O-D Case1: if both labels=d
	Case2: giving priority to have d labels at the beginning
    case3:if both labels are not d
	 if they are equal O-S=> O-D
     if the labels are not equal order acc to the alph of the labels*/

	String priority1;
	String priority2;

	public Priority2Labels(String priority1, String priority2) {
		this.priority1 = new String(priority1);
		this.priority2 = new String(priority2);
	}

	@Override
	public int compare(Edge arg0, Edge arg1) {
		// Subject
		String src0 = (arg0.getSrc()).getLabel().toString();
		String src1 = (arg1.getSrc()).getLabel().toString();

		// Label
		String lab0 = arg0.getLabel().toString();
		String lab1 = arg1.getLabel().toString();

		// Dest
		String dest0 = (arg0.getDest()).getLabel().toString();
		String dest1 = (arg1.getDest()).getLabel().toString();

		// Case1: if label1=label2=d=priority1
		if (lab0.compareTo(priority1) == 0 && lab1.compareTo(priority1) == 0) { // if label1=label2=d
			// Order according to the subject
			if (src0.compareTo(src1) == 0) {
				return (dest0.compareTo(dest1));
			} // order according to the object
			else { // not equal subjects
				return (src0.compareTo(src1));// order according to the subject
			}
		} // end if label1=label2=d
		
		
		
		// Case1: if label1=label2=priority2
		if (lab0.compareTo(priority2) == 0 && lab1.compareTo(priority2) == 0) { // if label1=label2=d
			// Order according to the subject
			if (src0.compareTo(src1) == 0) {
				return (dest0.compareTo(dest1));
			} // order according to the object
			else { // not equal subjects
				return (src0.compareTo(src1));// order according to the subject
			}
		} // end if label1=label2=priority2

		// Case2: giving priority to have priority1 over priority2
		else if (lab0.compareTo(priority1) == 0 && lab1.compareTo(priority2) == 0) { // priority1 label is already at
																						// the beginning so do not swap;
																						// priority1 and priority2
			return -1;
		} else if (lab0.compareTo(priority2) == 0 && lab1.compareTo(priority1) == 0) {// priority2 and priority1 so put
																						// priority1 label at the
																						// beginning so swap

			return 1;
		}
		
		
		// Case3: giving priority to have priority1 labels at the beginning
		else if (lab0.compareTo(priority1) == 0 && lab1.compareTo(priority1) != 0) { // priority1 label is already at the beginning so do not
																		// swap
			return -1;
		} else if (lab0.compareTo(priority1) != 0 && lab1.compareTo(priority1) == 0) {// not priority1 and priority1 so put priority1 label at the beginning so
																		// swap
			return 1;
		}

		
		// Case4: giving priority to have priority2 labels at the beginning
		else if (lab0.compareTo(priority2) == 0 && lab1.compareTo(priority2) != 0) { // priority2 label is already at
																						// the beginning so do not
			// swap
			return -1;
		} else if (lab0.compareTo(priority2) != 0 && lab1.compareTo(priority2) == 0) {// not priority2 and priority2 so
																						// put priority2 label at the
																						// beginning so
			// swap
			return 1;
		}

		
		// Case5: if both labels are not priority1 nor priority2
		else if (lab0.compareTo(lab1) == 0 && src0.compareTo(src1) == 0) {
			return (dest0.compareTo(dest1));
		} // order acc to the dest
		else if (lab0.compareTo(lab1) == 0) {// order acc to the source
			return (src0.compareTo(src1));
		} 
		else { // both labels are not equal and not d then O-L
			return lab0.compareTo(lab1);
		}

	}// end method

}
