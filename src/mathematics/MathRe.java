package mathematics;

import java.util.ArrayList;
import java.util.Arrays;

public class MathRe {

	/*
	 * hallway locker problem (open, close)
	 * 
	 * Approach I. normal way;
	 * Approach II. only odd number of factors number get open
	 * Approach III. only perfect square number has odd number of factors
	 */
	public int findOpen(int n){
		int[] array = new int[n];
		int flag = 0;
		for(int i=0;i<n;++i){
			flag ^= 1;
			for(int j=i;j<n;j += (i+1))
				array[j] = flag;
		}			
		int count = 0;
		for(int i=0;i<n;++i){
			count += array[i];
		}
		return count;
	}
	
	public int findOpen2(int n){
		int count = 0;
		for(int i=0; i<n; ++i){
			if(oddFactor(i)) count++;
		}
		return count;
	}
	
	public boolean oddFactor(int m){
		int count = 0;
		for(int i=2;i<Math.sqrt(m);++i){
			if(m % i == 0) count++; 
		}
		if(count % 2 == 1) return true;
		else return false;
	}
// more less computations	
	public int findOpen3(int n){
		int count = 0;
		for(int i=1;i*i<=100;++i){
			count++; 
		}
		return count;
	}
	
/**
 * add without arithmetic operation
 */	
	public int add(int a, int b){
		if(b == 0) return a;
		else{
			int sum = a^b;
			int carry = (a&b)<<1;
			return add(sum,carry);
		}
	}

/**
 * shuffle the card to make 52! possiblity equally
 * generate one, mark it unused by switch it with front element
 */	
	public void shuffleCard(int[] cards){
		int temp, index;
		for(int i=0; i<cards.length;i++){
			index = (int) (Math.random()*(cards.length-i))+i;
			temp = cards[i];
			cards[i] = cards[index];
			cards[index] = temp;
		}
	}
	

	
/**
 * generate a set of m interger from an array of size n in equally likely pick-up---same as fuffleCard()
 */	
	
	
/**
 * count the number of 2 in 0..n
 * Approach: 
 * 1, list some count in some range, try to find simple rule
 * 2, program
 */	
	public int count2(int n){
		int count = 0;
		int digit = 0;
		int rest;
		int k;
		for(int i=n;i>0 ;i=n/10, digit++){
			k = i % 10;
			rest = n / 10^digit;
			if(digit==0 && k >=2) count += 1; 
			else{
				if(k==2) count += k*digit*(10^digit-1) + rest+1;
				else if(k<2) count += digit*10^(digit-1);
				else count += k*digit*10^(digit-1) + 10^(digit-1);
			}	
		}
		return count;
	}
	
/**
 * find the prime number up to n
 * 
 * Sieve of Eratosthenes
 */	
	public void findPrime(int n){
		boolean[] prime = new boolean[n];
		Arrays.fill(prime,true);
		
		prime[0] = prime[1] = false;
		
		for(int i=4;i<n;i=i+2)
			prime[i] = false;
		
		int limit = (int) Math.sqrt(n);
		for(int i=3;i<limit;i=i+2)
			if(prime[i])
				for(int j=i*i;j<n;j+=i)
					prime[j]=false;
	}
	
	/**
	 * triangle path sum minimum
	 * [1]
	 * [2,3]
	 * [4,5,6]
	 * ...
	 * 
	 * using bottom up approach
	 * 
	 * @param triangle
	 * @return
	 */
	
	public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int len = triangle.size();
        if(len == 1) return triangle.get(0).get(0);
        int[] sum = new int[len];
        for(int i=0;i<len;++i)
            sum[i] = triangle.get(len-1).get(i);
        for(int i=len-2;i>=0;--i){
            ArrayList<Integer> row = triangle.get(i);
        	for(int j=0;j<i+1;++j){
        	    sum[j] = min(sum[j],sum[j+1])+row.get(j);
        	}
        }
        return sum[0];
    }
    
}
