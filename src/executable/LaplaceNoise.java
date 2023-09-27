package executable;
import org.apache.commons.math3.distribution.LaplaceDistribution;

public class LaplaceNoise {

	 //The Laplacian Distribution used to introduce perturbations
		private LaplaceDistribution distrib;
		
		/** Getter of distrib  @return the laplace distribution used for perturbation*/
		public LaplaceDistribution getDistrib(){
		return distrib;
		}
		
		//Creates the  Pertubator @param beta the scale of laplacian distribution (position is 0)
		//Parameters: mu - location parameter & beta - scale parameter (must be positive)
		//Constructor of this class
		public LaplaceNoise(double beta){
		this.distrib = new LaplaceDistribution(0, beta);
		}
		
		
		
		//version1: return perturbated int +abs
		/** Perturb a value using  laplacian distribution @param val the integer to perturb  @return perturbated val >0*/
		public int perturbate(int val){
		int toSend = val + (int) Math.round(distrib.sample());
		//We round it since we want an integer.
		//Because we wanted an integer > 0
		//if(toSend>1) return toSend;
		//else return 1;
		return Math.abs(toSend);}
		
		
		
		//version 2: return perturbated value: double+abs
		public double perturb(int val){
			double toSend = val + distrib.sample();
			return Math.abs(toSend);}
			
		//ADD a method to be able to perturb double
		public double perturbd(double val){
			double toSend = val + distrib.sample();
			return Math.abs(toSend);}
		
		
		//small test
	   public static void main (String[] args) {
		  LaplaceNoise l=new LaplaceNoise(2/0.4);
		  int c=l.perturbate(3);
		  System.out.println(c); 
		  
	   }
	   }