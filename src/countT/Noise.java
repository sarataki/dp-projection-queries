package countT;
import executable.LaplaceNoise;


public class Noise {

	public static void main(String[] args) {
        double[] qN= new double[100];
        double sum=0;
		// distance to store summation of distances from perturbed to initial answer
		double distance=0;
		
		for(int j=0; j<100; j++) {
	    for (int i = 0; i < 100; i++) {
	    	    //Try with Outdegree bound=2, epsilon=0.01
				LaplaceNoise l = new LaplaceNoise(560/100); //Laplace(GS/epsilon)
				
				// perturbed= initial answer of the query on projected graph+ noise
				double perturbed = l.perturb(560);
                System.out.println("Perturbated value is "+perturbed);
                
				// Distance from perturbed to initial answer of the query on projected graph
				double dis = Math.abs(perturbed -560);
				distance = distance + dis;
				
		} // end of first for

		double average= distance / 100;
		System.out.println("Average is "+average);
		// finalAnsw= initial answer on projected graph+ average distance
	    double finalAns= 560+ average;
		qN[j]=finalAns;
		System.out.println("value is "+qN[j] );
		}
		
		
		for(int j=0; j<100; j++) {
			//sum += Math.abs(qN[j]-qR);
	       sum+= Math.abs(qN[j]-551);
	      //Here we have 100 qN
		}
		
		System.out.println("Sum S= " +Math.floor(sum* 100) / 100 );
		System.out.println("The final avg answer "+ sum/100);
		
}// end of main
}// end of class
