package mathematics;

import java.util.Random;

public class RandomApp {

	/*
	 * Design and implement a class to generate random numbers in 
	 * an arbitrary probability distribution given by an array of integer weights, 
	 * i.e. for int[] w return a number, n, 
	 * from 0 to w.length - 1 with probability w[n] / sum(w). 
	 * Using an existing random number generator with a uniform distribution is permitted. 
	 * 
	 * public int nextInt(int n) 
	 * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) 
	 * and the specified value (exclusive), 
	 * drawn from this random number generator's sequence. 
	 * The general contract of nextInt is that one int value in the 
	 * specified range is pseudorandomly generated and returned.
	 * All n possible int values are produced with (approximately) equal probability. 
	 * 
	 * Parameters: n - the bound on the random number to be returned. Must be positive.
	 * Returns:
	 * the next pseudorandom, uniformly distributed int value between 0 (inclusive) and n (exclusive) from this random number generator's sequence
	 * Throws:
	 * IllegalArgumentException - if n is not positive
	 */
	
	int[] assist;
	public void convertP(int[] w){
		int sum = 0;
		for(int i=0; i<w.length;++i){
			sum += w[i];
		}
		assist = new int[sum];
		int k = 0;
		for(int j=0;j<w.length;++j)
			for(int i=0;i<w[j];++i)
				assist[k++] = j;	
	}
	
	public int next(){
		Random ran = new Random();
		int i = ran.nextInt(assist.length);
		return assist[i];
	}
	
	/**
	 * second cumulative propobility way
	 */
	public char randomchar(char[] A, double[] W)
	{
		double temp = 0;
		double random = Math.random()*N*sum(W); // N 0 is the least elem in W to be a integer
				
		for(int i=0; i<A.length; i++)
		{
			temp += W[i];
			if(random < temp*N)
				return A[i];
		}
		return ' ';
	}
	
	/*
	 * The idea is to create a prob. distribution out of the given prob. 
	 * density and then choose the random numbers out of it.
	 * Here is the example. For a given Prod. density, 
	 * say Den = [1,2,4,5,1,3], the distribution is cumulative sum of the array. 
	 * So, Dist = [1,3,7,12,13,16].
	 * Now, generate a uniform random number between 0 and Dist[n] = 16. 
	 * The number to be returned is the index of whichever interval 
	 * the generate random number fell into. Lets say, we generated 10. 
	 * 10 lies in [7,12], so the return value is index(12) = 3. 
	 * 
	 */
	int rand_dist(int[] den, int n) //den -> density function and n-> length of den
	{
		int i;
		int[] dist = new int[n];
		dist[0] = den[0];
		for(i = 1; i<den.length;i++){
			dist[i] = dist[i-1]+den[i];
		}
		Random ran = new Random();
		int ran_num = Math.floor(ran.nextInt(dist[n-1])); //Assume rand returns values from 1 to MAX_INT.
		for(i=0;i<n;i++){
			if(rand_num <= dist[i]) return(i);
		}
	}
	
	/**
	 * Rejection sampling
	 * 
	 * use random() that generate number between 1..5, to generate random number in 1..7
	 * first generate a larger range of number, then from it pick one, treat it to smaller number
	 */
		public int rand7(){
			while(true){
				int num = (int) 5*(random()-1)+(random()-1);
				if(num < 21) return num%7+1;
			}
		}
}
