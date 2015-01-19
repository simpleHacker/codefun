package mathematics;

import java.util.Arrays;

public class Permutation {
	
// recursive version of permut, call by permut("",items)
	public void permut(String prefix,String postfix){
		if(postfix.length() <= 1)
			System.out.println(prefix+postfix);
		else{
			for(int i=0;i<postfix.length();++i){
				String newStr = postfix.substring(0,i)+postfix.substring(i+1);
				permut(prefix+postfix.charAt(i),newStr);
			}
		}
	}

// calculate number of permut, the factorial of n
	public int noofpermut(String items){
		int no = 1;
		for(int i = 1; i<items.length();++i)
			no *= i;
		return no;
	}
	
// iterative version of permutation, bell algorithm
	public void bellpermut(char[] items){
		int count = 0;
		while(count < items.length){
			for(int i=0; i< items.length-1;++i){
				swap(items,i,i+1);
				printout(items);
				count++;
			}
			swap(items,0,1);
			printout(items);
			count++;
			for(int i=items.length-1;i>0;--i){
				swap(items,i, i-1);
				printout(items);
				count++;
			}
			swap(items,items.length-1,items.length-2);
			printout(items);
			count++;
		}
	}
	
	/**
	 * Basically, for each element from left to right,
	 *  you generate all the permutations of the remaining elements. 
	 *  You can do this recursively, (or iteratively if you like pain)
	 *  until you get to the last element at which point there is only one possible order.
	 * @param N
	 * @return
	 */
	public void permutate(char[] x, int i, int n){
	    if (i==n){
	        System.out.print(x);
	    } else{
	        for (int j=i; j<=n;j++){
	        	swap (x,i,j);
	        	permutate(x,i+1,n);
	        	swap (temp,i,j);
	        }
	    }
	}
	

// generate from a range of number, lexicographic-ordered algorithm described at Wikipedia
	public static int[][] generatePermutations(int N) {
	    int[][] a = new int[factorial(N)][N];
	    for (int i = 0; i < N; i++) a[0][i] = i;
	    for (int i = 1; i < a.length; i++) {
	        a[i] = Arrays.copyOf(a[i-1], N);
	        int k, l;
	        for (k = N - 2; a[i][k] >= a[i][k+1] && k>=0; k--);
	        for (l = N - 1; a[i][k] >= a[i][l] && l>=0; l--);
	        swap(a[i], k, l);
	        for (int j = 1; k+j < N-j; j++) swap(a[i], k+j, N-j);
	    }
	    return a;
	}
	
	
}
