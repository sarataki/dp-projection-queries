package ordering;
import pro2.Edge;

public class PriorityLabel extends EdgeOrdering {
	// Giving priority to a certain label to be at the beginning of the ordering
	// O-L=> O-S =>O-D Case1: if both labels=d
	// Case2: giving priority to have d labels at the beginning
///case3:if both labels are not d
	// if they are equal O-S=> O-D
	// if the labels are not equal order acc to the alph of the labels

	String p;

	public PriorityLabel(String priority) {
		this.p = new String(priority);
	}
	// String p=new String("labeld"); //priority to have d labels at the beginning

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

		// Case1: if label1=label2=d
		if (lab0.compareTo(p) == 0 && lab1.compareTo(p) == 0) { // if label1=label2=d
			// Order according to the subject
			if (src0.compareTo(src1) == 0) {
				return (dest0.compareTo(dest1));
			} // order according to the object
			else { // not equal subjects
				return (src0.compareTo(src1));// order according to the subject
			}
		} // end if label1=label2=d

		// Case2: giving priority to have d labels at the beginning
		else if (lab0.compareTo(p) == 0 && lab1.compareTo(p) != 0) { // d label is already at the beginning so do not
																		// swap
			return -1;
		} else if (lab0.compareTo(p) != 0 && lab1.compareTo(p) == 0) {// not d and d so put d label at the beginning so
																		// swap
			return 1;
		}

		// Case3: if both labels are not d
		else if (lab0.compareTo(lab1) == 0 && src0.compareTo(src1) == 0) {
			return (dest0.compareTo(dest1));
		} // order acc to the dest
		else if (lab0.compareTo(lab1) == 0) {
			return (src0.compareTo(src1));
		} // order acc to the source
		else { // both labels are not equal and not d
				// O-L
			return lab0.compareTo(lab1);
		}

	}// end method

}
