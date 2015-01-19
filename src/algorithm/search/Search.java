package algorithm.search;

import java.util.ArrayDeque;
import java.util.Deque;

public class Search {

//	 @question, rotated sorted list, search a elem in O(log(n))	
//	 BSearch mutate version, also need to compare with two ends for next index updates 	
		public int search_in_rotation(int[] a, int l, int u, int x){
			while(l<=u){
				int m = (l+u)/2;
				if(a[l] < a[u]){
					if(x == a[m]) return m;
					else if(x < a[m]) u = m-1;
					else l = m+1;
				}else{
			// disorder, consider both sides for index updates		
					if(x < a[m] || x > a[l]) u = m-1;
					else l = m+1;
				}
			}
			return -1;
		}

// recursive approach:
		public int findInCycle(int[] arr, int low, int high, int data){
			if(low > high) return -1;
			else{
				int index = (low+high)/2;
				int mid = arr[index];
				if(data == mid) return index;
				else if(data < mid){
					if(mid > arr[high]){
						if(data <= arr[arr[high]]) return findInCycle(arr,mid+1,high,data);
						else return findInCycle(arr,low,mid-1,data);
					} else return findInCycle(arr,low,mid-1,data);
				} else {
					if(mid < arr[low]){
						if(data <= arr[high]) return findInCycle(arr,mid+1,high,data);
						else return findInCycle(arr,low,mid-1,data);
					} else return findInCycle(arr,mid+1,high,data);
				}
			}
		}
			
			
//		@question, find one from sorted string intersperesed with empty string
//		 skip the "" during binary search, keep the original middle to reduce scan times.	
			public int search_in_empty(String[] a, int l, int u, String x){
				while(l<=u){
					int m = (l+u)/2, tmp = m;
					while(a[tmp] == " " && tmp <= u)
						tmp += 1;
					if(tmp == u) u = m-1;
					else{
						if(x.compareTo(a[tmp]) == 0) return tmp;
						else if(x.compareTo(a[tmp]) < 0) u = m - 1;
						else l = tmp+1;
					}
				}
				return -1;
			}
// recursive version:
			public int searchInEmpty(String[] a, int l, int h, String data){
				int mid = (l+h)/2;
				if(l>h) return -1;
				else{	
					int temp = mid;
					while(a[temp].equals(" ") && temp >= l) temp--;
					if(temp < l) return searchInEmpty(a,mid+1,h,data);
					if(data.equals(a[temp])) return temp;
					else if(data.compareTo(a[temp])<0) return searchInEmpty(a,l,temp-1,data);
					else return searchInEmpty(a,mid+1,h,data);
				}
			}			
			
//			 @question, find one in sorted row and column matrix
//			 compare from first row last column
// O(m+n)
				public int[] find_in_matrix(int[][] a, int x, int rowlen, int columnlen){
					int i = 0;
					int j = columnlen -1;
					int[] pos = new int[2];
					while(i < rowlen && j >= 0){
						if(x == a[i][j]){
							pos[0] = i;
							pos[1] = j;
							return pos;
						}
						else if(x < a[i][j]) j--;
						else i++;
					}
					return null;
				}
		
// select the Nth elem in a array -- Linear selection: MedMed algorithm
// O(N)
		public int medMed(int[] items, int low, int high){
			int numMedians = (high - low+1)/5;
			int sublow=low, subhigh=low+4;
			int medianIdx = -1;
			for(int i=0;i<numMedians;++i){
				sublow = sublow+i*5;
				subhigh = subhigh+i*5;
				if(subhigh > high) subhigh = high;
				medianIdx = select(items,sublow,subhigh,2);
				swap(items,low+i,medianIdx);	
			}
			return select(items,low,low+numMedians,numMedians/2);
		}
		
		public int select(int[] items, int low, int high, int nth){
			int mid, idx, k;
			if(high-low+1 < 5)
				return findNth(items,nth);
			else{
				mid = medMed(items,low,high);
				swap(items,mid,low);
				idx = partition(items,low,high);
				k = idx-low+1;
				if(nth == k) return items[nth];
				else if(nth < k) return select(items,low, k-1, nth);
				else  return select(items,k+1,high,nth-k);
			}
		}
		
		// time complexity O(n)
		public static int selectKth(int[] arr, int k) {
			 if (arr == null || arr.length <= k)
			  throw new Error();
			 
			 int from = 0, to = arr.length - 1;
			 
			 // if from == to we reached the kth element
			 while (from < to) {
			  int r = from, w = to;
			  int mid = arr[(r + w) / 2];
			 
			  // stop if the reader and writer meets
			  while (r <= w) {
			 
			   if (arr[r] >= mid) { // put the large values at the end
			    int tmp = arr[w];
			    arr[w] = arr[r];
			    arr[r] = tmp;
			    w--;
			   } else { // the value is smaller than the pivot, skip
			    r++;
			   }
			   
			  }
			 
			  // if we stepped up (r++) we need to step one down
			  if (arr[r] > mid)
			   r--;
			 
			  // the r pointer is on the end of the first k elements
			  if(k == r) return arr[r];
			  else if (k < r) {
			   to = r-1;
			  } else {
			   from = r + 1;
			  }
			 }
			 return -1;
		}
		
/** Design an algorithm that, given a list of n elements in an array, 
 * finds all the elements that appear more than n/3 times in the list. 
 * The algorithm should run in linear time. (n >=0 ) You are expected to use 
 * comparisons and achieve linear time. No hashing/excessive space/ 
 * and don't use standard linear time deterministic selection algo
 */
		
/*		count[0] = 1; count[1] = 1; count[2] = 1;
 * 		for A[i] in A:
            if A[i] == Num[0]:
                      Count[0]+=2
                      Count[1]-- if count[1] != 0
                      Count[2]-- if count[2] != 0
            else if A[i] == Num[1]:
                      Count[0]--;
                      Count[1]+=2;
                      Count[2]--;
            else if A[i] == Num[2]:
                      Count[0]--;
                      Count[1]--;
                      Count[2]+=2;
            else
                      Count[0]--;
                      Count[1]--;
                      Count[2]--;
            		  if Count[0] == 0:
                                  Num[0] = A[i];
                                  Count[0] = 2;
                      else if Count[1] == 0:
                                  Num[1] = A[i];
                                  Count[1] = 2;
                      else if Count[2] == 0:
                                  Num[2] = A[i];
                                  Count[2] = 2;
  // must check after voting procedure                                
        	if count[0] != 0 : check(Num[0], A)
        	if count[1] != 0 : check(Num[1], A)
        	if count[2] != 0 : check(Num[2], A)
			
  			
   count[i] cannot be less than 0; so add check for count[i]--, then they initially can be 0;                               
As far as number X is more than n/3, it will has count as m*2 - (n-m) > 0, 
m is the appear time of X. So X will stay in the parameter when the algorithm finished.                                  
                                  */
		
	/*
	 * select the median in two sorted array in equal size:
	 * 1, Examine the middle element of each array, 
	 *2, and throw out the lower half of the array with the smaller element 
	 *(since all those must be less than ½ the numbers) and 
	 *3, throw out the upper half of the array with the larger element 
	 *(since all those must be greater than ½ the numbers).  
	 *4, Now both arrays are still the same size.  
	 *5, Repeat 1 until you have two elements left.  
	 *This is your median.  
	 *Each step, you eliminate half of the numbers, so it should have a runtime of O(logn).
	 */	
		
		public int findMedian(int[] a, int lowa, int higha, int[] b, int lowb, int highb){
			if((higha-lowa)<=2  && (highb-lowb)<=2){
				if(a[lowa] >= b[lowb] && b[highb] >= a[higha]) return (a[lowa]+a[higha])/2;
				else if(a[lowa] <= b[lowb] && b[highb] <= a[higha]) return (b[lowb]+b[highb])/2;
				else if(a[lowa]>b[highb] || a[higha]>b[highb]) return (a[lowa]+b[highb])/2;
				else return (a[higha]+b[lowb])/2;
			}else{
				int mida = (higha-lowa)/2;
				int midb = (highb-lowb)/2;
				if(a[mida] == b[midb]) return (a[mida]+b[midb])/2;
				else if(a[mida] > b[midb]) return findMedian(a,0, mida,b,midb,highb);
				else return findMedian(a,mida,higha,b,0,midb);
			}
		}
		
	/*
	 * Step2: What if the lengths are not equal?
	 * The smartest solution I’ve found is padding. 
	 * It is best to think of median as a special case of the select problem, 
	 * where given a set and an index k, you need to find the kth smallest element. 
	 * Median is simply Select(n/2) when n is even, and fully defined by Select(\floor(n/2)) 
	 * and Select (\Ceiling(n/2)) when n is odd. 
	 * Now there is a simple O(log n) algorithm for the Select problem on the union of 
	 * two equal sorted arrays, that throws out the appropriate halves of the two 
	 * lists recursively.
	 * If you want a simpler reduction to equal sized lists, 
	 * pad the shorter list with an equal number of positive and negative infinities. 
	 * If you need an odd number of pads (ie: when the source lists have an odd total), 
	 * do it with positive infinity and (since the set size was odd) 
	 * return the lesser of the two retuned values.
	 * 
	 */
		
		// find the the kth number on the union of two sorted arrays
		// O(log(m) + log(n))
		// i+j = k-1; B[j-1]<A[i]<B[h]:A[i], ...
		public int selectKth(int[] a, int lowa, int higha, int[] b, int lowb, int highb, int k){
			
			if(lowa > higha || lowb>highb || k>(higha-lowa+highb-lowb+2) || k<0) return -1;
			int lena = higha - lowa+1;
			int lenb = higha - lowb+1;
			int i = (int)(((double)lena) /(lena+lenb)*(k-1));
			int j = k-1-i;
			int Ai_1 = (i==0)?Integer.MIN_VALUE:a[lowa+i-1];
			int Bj_1 = (j==0)?Integer.MIN_VALUE:b[lowb+j-1];
			int Ai = (i==lena)?Integer.MAX_VALUE:a[lowa+i];
			int Bj = (j==lenb)?Integer.MAX_VALUE:b[lowb+j];
			
			if(Ai>Bj_1 && Bj>Ai) return Ai;
			else if(Bj>Ai_1 && Bj<Ai) return Bj;
			
			if(Bj > Ai) return selectKth(a,lowa+i+1,higha,b,lowb,lowb+j, k-i-1);
			else return selectKth(a,lowa,lowa+i,b,lowb+j+1,highb,k-j-1);
		
		}
		
		/**
		 *  find the max element in each sliding window
		 *  O(N)
		 * @param A
		 * @param n
		 * @param w
		 * @param B
		 */
		public void maxSlidingWindow(int[] A, int n, int w, int[] B){
			Deque<Integer> d = new ArrayDeque<Integer>();
			for(int i=0;i<3;++i){
				while(!d.isEmpty() && A[i] > A[d.peekLast()])
					d.pollLast();
				d.offerLast(i);
			}
			for(int i=w;i<n;++i){
				B[i-w] = A[d.peekFirst()];
				
				while(!d.isEmpty() && A[i] > A[d.peekLast()])
					d.pollLast();
				while(!d.isEmpty() && d.peekFirst() < i-w)
					d.pollFirst();
				d.offerLast(i);
			}
			B[n-w] = A[d.peekFirst()];
		}
}
