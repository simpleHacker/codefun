package struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Matrix {

	/**
	 * I, printout matrix element in cycle way
	 * approach: 
	 * 1, list the elements on the first cycle
	 * a[0][j=0..n-2]
	 * a[i=0..m-2][n-1]
	 * a[m-1][j=n-1..1]
	 * a[i=m-1..1][0]
	 * j,i from 0 to n,m, then from n,m to 0,0
	 * k=1,l=1 ->
	 * a[0][j=0..n-k-1]
	 * a[i=0..m-l-1][n-1]
	 * a[m-1][j=n-k..k]
	 * a[i=m-1..l][0]
	 * 2, find the rule for index changing when access each elements
	 * after first cycle, treating the rest matrix is similar to treat original
	 * matrix, but scale reduced as a[m-2][n-2].
	 * So the rest problem T(m,n) = T(m-2,n-2)+O(m+n) by recursive way
	 * Also we can use loop way, in loop way, the boundary limits changing by K and L
	 * 3, figure out use which way to express this rule in algorithm
	 * recursive and iterative
	 * 4, check the final case for cycle print:
	 * none, 1*1, n*1, 1*n
	 */
	
	public void cyclePrint(int[][] a, int n0, int nn, int m0, int mm){
		int k=1, l=1, i=0, j=0;
		int n = nn-n0+1;
		int m = mm-m0+1;
		while(k<= Math.ceil(n/2) && l<=Math.ceil(m/2)){
			System.out.println("Cycle "+k+" :");
			for(;j<n-k;++j) System.out.print(a[i][j]+" ");
			for(;i<m-l;++i) System.out.print(a[i][j]+" ");
			for(;j>=k;--j) System.out.print(a[i][j]+" ");
			for(;i>=l;--i) System.out.print(a[i][j]);
			i++;j++;k++;l++;
			System.out.println();
		}
	}
	public boolean cyclePrint_R(int[][] a, int n0, int nn, int m0, int mm){
		int i=m0, j=n0;
		int n = nn-n0+1;
		int m = mm-m0+1;
		if(n0<=nn && m0<= mm){
			System.out.println("Cycle "+(n0+1)+" :");
			for(;j<nn-1;++j) System.out.print(a[i][j]+" ");
			for(;i<mm-1;++i) System.out.print(a[i][j]+" ");
			for(;j>=n0+1;--j) System.out.print(a[i][j]+" ");
			for(;i>=m0+1;--i) System.out.print(a[i][j]);
			System.out.println();
			return cyclePrint_R(a,n0+1,nn-1,m0+1,mm-1);
		}else{
			return true;
		}
	}
		
	/**
	 * II. print matrix of set of diagnores (top-left to right-down)
	 * 1, rules for index of elements on diagnores
	 * i+j = k, k = 0..m+n
	 * 2, way:
	 * iterative, seperate for upper triangle and bottom triangle
	 * for clear logic.
	 * optimize can merge them togather by:
	 * flag = 0;
	 * for(int k = 0;k<n+m-1;++k){
	 * 		if(k>n-1) flag++;
	 * 		for(int i = flag;i<n-flag && k-i>=0;++i)
				System.out.print(a[i][k-i]+" ");
			System.out.println();
		}
	 */
	
	public void diagnorePrint(int[][] a, int n, int m){
	// upper left triangle set	
		for(int k = 0;k<n;++k){
			System.out.println("Diagnore "+(k+1)+" :");
			for(int i = 0;i<n && k-i>=0;++i)
				System.out.print(a[i][k-i]+" ");
			System.out.println();
		}
	// right bottom triangle set	
		for(int k = m;k<n+m-1;++k){
			System.out.println("Diagnore "+(k+1)+" :");
			for(int j = m-1;j>=0 && k-j>=0;--j)
				System.out.print(a[k-j][j]+" ");
			System.out.println();
		}
	}
	
	/**
	 * III. print matrix in "U" way, row U is same as column U
	 * 1, list element at the begining printout
	 * a[0..n-1][0]
	 * a[n-1..0][1]
	 * 2, rule for index changing
	 * i = 0..n-1, then decease to 0
	 * j just increase by 1 each step
	 */
	
	public void uPrint(int[][] a, int n, int m){
		for(int j=0;j<m;j++){
			System.out.println("U "+(j % 2)+" :");
			for(int i=0;i<n;++i) System.out.print(a[i][j]+" ");
			j++;System.out.println();
			for(int i=n-1;i>=0;--i) System.out.print(a[i][j]+" ");
			System.out.println();
		}
	}
	
//	 set row and columns in a matrix that cell is 0
	public void setZero(int[][] matrix){
		// use an row array record, which row has 0 cell
		// use a column array record which column has 0 cell
		// then in a nested loop, set the row and columns with 0 cell all to 0
	}
	
	
	/**
	 * find the maximum sum of submatrix O(n^2)
	 * 
	 * Approach I: O(n^3)
	 * sum each elements in same columns into a array[columns];
	 * then find the largest sum of this 1-D array.O(n)
	 * 
	 * Approach II:
	 * 這道題可以轉为为求一維數組裏相應的最大值。

	首先用兩個數組L[ ]， R[ ]分別記錄某個點左邊和右邊比它本身大的範圍。

	比如數列：3 2 4 1（標號從1開始）

	則L[2] = 1，R[2] = 3，表示數組1到3的範圍內的數都不比第二個數小。

	處理完之後枚舉每一個點，則每個點的值为V[i] = (R[i]-L[i]+1)*a[i]。

	則V[2] = (R[2]-L[2]+1)*a[2] = (3-1+1)*2=6，表示以第二個數为中心的點所能形成的最大矩陣为6

	將R的點取值为0，F的點取值为1。則題目要求01矩陣的最大全1子矩陣。

	假設一01矩陣如下：

	1011

	0011

	1011

	1101

	則按列將其轉化为如下矩陣：

	1011

	0022

	1033

	2004

	即某個點的值表示該點向上有多少個連續的1。

	然後對於每一行進行前一道題的運算，再取其最大值即为最終結果。
	 */
		
		public int largestSubSum(int[] arr){
			int maxsum = 0;
			int tempsum = 0;
			for(int i=0;i<arr.length;++i){
				if(tempsum+arr[i]>0){
					tempsum += arr[i];
					if(tempsum > maxsum)
						maxsum = tempsum;
				}else{
					tempsum = 0;
				}
			}
			return maxsum;
		}
		
		private int maxSubMatrix(int n,int[][] array){
	          int i,j,k,max=0,sum=-100000000;
	          
	          for(i=0;i<array.length;i++){
	        	  int b[]=new int[array[0].length];
	                for(j=i;j<array.length;j++)//把第i行到第j行相加,对每一次相加求出最大值
	                {
	                    for(k=0;k<b.length;k++){
	                            b[k]+=array[j][k];
	                    }
	                    max=largestSubSum(b);  
	                    if(max>sum){
	                       sum=max;
	                    }
	                }
	          }
	          return sum;
	    }
		
	/**
	 * find the largest submatrix that includes all 1s
	 * Approach:
	 * Generate an array C where each element 
	 * represents the number of 1s above and including it, up until the first 0.
	 * 
	 * We want to find the row R, and left, right indices l , r 
	 * that maximizes (r-l+1)*min(C[R][l..r]). 
	 * Here is an algorithm to inspect each row in O(cols) time:

	 * Maintain a stack of pairs (h, i), 
	 * where C[R][i-1] < h ≤ C[R][i]. 
	 * At any position cur, we should have h=min(C[R][i..cur]) for all pairs (h, i) on the stack.
	 
	 append 0 to the end of your array
	max = 0, temp = 0
	for i = 1 to array.size do
	  if array[i] = 1 then
	    ++temp
	  else
	    if temp > max then
	      max = temp
	    temp = 0
	    
	max = 0
	for i = 1 to M.numLines do
	  for j = i to M.numLines do
	    temp = 0
	    for k = 1 to M.numCols do
	      if S[j][k] - S[i-1][k] = j - i + 1 then
	        temp += j - i + 1
	      else
	        if temp > max then
	          max = temp
	        temp = 0
	 
	 */	
			
	/*Rainfall bassin coverage problem
	 * 1, water will flow to the neighboring cell with the lowest altitude.
	 * 2, output basin size in descending order
	 * 
	 * My approach : O(M*N)
	 */	
		public int[] findBasin(int[][] land){
			
			int rlen = land.length,clen = land[0].length;
			int[] none = {};
			if(rlen == 0) return none;
			ArrayList<Integer> sizes = new ArrayList<Integer>();
			String[][] track = new String[rlen][clen];
			ArrayList<HashSet<String>> sets = new ArrayList<HashSet<String>>();
			HashSet<String> set;
		// create track info	
			for(int i=0;i<rlen;++i)
				for(int j=0;j<clen;++j){
					track[i][j] = lowestNeib(land,i,j);
				}
		// partition to set	
			int flag = 0;
			for(int i=0;i<rlen;++i)
				for(int j=0;j<clen;++j){
					flag = 0;
					for(int k=0;k<sets.size();++k){
						set = sets.get(k);
						if(set.contains(track[i][j])){ 
							set.add(i+","+j);
							flag = 1;
						}
					}
					if(flag == 0){
						set = new HashSet<String>();
						if(checkSelf(track[i][j],i,j)) set.add(track[i][j]);
						else{
							set.add(track[i][j]);
							set.add(i+","+j);
						}
					}
				}
		// get size of each set and sort into a array
			int[] array = new int[sets.size()];
			for(int k=0;k<sets.size();++k){
				array[k]= sets.get(k).size();
			}
			Arrays.sort(array);
			
			for(int k = array.length;k>=0;--k){
				System.out.print(array[k]+" ");
			}
			return array;
		}
//		
		public String lowestNeib(int[][] land,int i, int j){
			int ui = i-1, di=i+1;
			int lj = j-1, rj = j+1;
			int min = land[i][j];
			int mini=i,minj=j;
			
			if(ui>=0)
				if(land[ui][j]<min){
					min = land[ui][j];
					mini = ui;minj=j;
				}
			if(di<land.length)
				if(land[di][j]<min){
					min = land[di][j];
					mini = di;minj=j;
				}
			if(lj>=0)
				if(land[i][lj]<min){
					min = land[i][lj];
					mini = i;minj=lj;
				}
			if(rj<=land[0].length)
				if(land[i][rj]<min){
					min = land[i][rj];
					mini = i;minj=rj;
				}
			
			return mini+","+minj;
		}
		
		public boolean checkSelf(String str, int i, int j){
			
			String[] inds = str.split(",");
			if(Integer.parseInt(inds[0]) != i) return false;
			if(Integer.parseInt(inds[1]) != j) return false;
			return true;
		}
	
}
