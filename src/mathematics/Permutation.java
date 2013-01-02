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

// calculate number of permut	
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

// generate from a range of number, lexicographic-ordered algorithm described at Wikipedia
	public static int[][] generatePermutations(int N) {
	    int[][] a = new int[factorial(N)][N];
	    for (int i = 0; i < N; i++) a[0][i] = i;
	    for (int i = 1; i < a.length; i++) {
	        a[i] = Arrays.copyOf(a[i-1], N);
	        int k, l;
	        for (k = N - 2; a[i][k] >= a[i][k+1]; k--);
	        for (l = N - 1; a[i][k] >= a[i][l]; l--);
	        swap(a[i], k, l);
	        for (int j = 1; k+j < N-j; j++) swap(a[i], k+j, N-j);
	    }
	    return a;
	}
	
	
}
