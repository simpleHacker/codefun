package coding;

import java.util.HashMap;

public class Probability {

	/*
	 * There is an island which is represented by square matrix NxN.
	 * A person on the island is standing at any given co-ordinates (x,y). 
	 * He can move in any direction one step right, left, up, down on the island. 
	 * If he steps outside the island, he dies.
	 * Let island is represented as (0,0) to (N-1,N-1) 
	 * (i.e NxN matrix) & person is standing at given co-ordinates (x,y). 
	 * He is allowed to move n steps on the island (along the matrix). 
	 * What is the probability that he is alive after he walks n steps on the island?

	 * Write a psuedocode & then full code for function
	 * " float probabilityofalive(int x,int y, int n) ".
	 * 
	 * Approach : 
	 * I presume that the probabilities of move up/donw/left/right are equal(0.25).
	 * Then P(x, y, n, step) = 
	 * (P(x-1, y, n, step-1) + P(x+1, y, n, step-1) + P(x, y-1, n, step-1) + P(x, y+1, n, step-1)) / 4. 
	 */
	
	public static double probabilityOfAlive(int x, int y, int n)
	  {
	    if (x < 0 || x > (n - 1) || y < 0 || y > (n - 1) || n < 1) {return 0.0;}
	    return probabilityOfAlive(x, y, n, n, new HashMap<String, Double>());
	  }

	  private static double probabilityOfAlive(int x, int y, int n, int step, HashMap<String, Double> map)
	  {
	    if (0 == step) {return 1.0;}
	    // if the state is already calculated, return from HashMap
	    String key = "";
	    {
	      StringBuffer sb = new StringBuffer();
	      sb.append(x + ",");
	      sb.append(y + ",");
	      sb.append(step + ".");
	      key = sb.toString();
	    }
	    if (map.containsKey(key)) {return map.get(key);}
	    // calculate the probability of a state
	    double probability = 0.0;
	    if (x > 0) {probability += 0.25 * probabilityOfAlive(x - 1, y, n, step - 1, map);}
	    if (x < (n - 1)) {probability += 0.25 * probabilityOfAlive(x + 1, y, n, step - 1, map);}
	    if (y > 0) {probability += 0.25 * probabilityOfAlive(x, y - 1, n, step - 1, map);}
	    if (y < (n - 1)) {probability += 0.25 * probabilityOfAlive(x, y + 1, n, step - 1, map);}
	    // save the result to HashMap and return
	    map.put(key, probability); return probability;
	  }

	  public static void main(String[] args)
	  {
	    System.out.println("probability1 = " + probabilityOfAlive(0, 0, 1));
	    System.out.println("probability2 = " + probabilityOfAlive(0, 0, 2));
	    System.out.println("probability3 = " + probabilityOfAlive(0, 0, 3));
	  }
	  
	  /*
	   * Describe an algorithm to output a die roll (a random number from 1 to 6), given a
function that outputs a coin toss (a random number from 1 to 2). Each possible
outcome should be equally likely.
	   * 
	   */
	  
	  public int dieRoll(int coin){
		  int sum = 0, p;
		  for(int i=0;i<6;++i){
			  p = (int) (Math.random()*coin+1); // coin = 2
			  sum = sum + p; //(p-1)
		  }
		  return sum;
	  }
	
}
