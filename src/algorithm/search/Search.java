package algorithm.search;

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
		
//		@question, find one from sorted string intersperesed with empty string
//		 skip the "" during binary search, keep the original middle to reduce scan times.	
			public int search_in_empty(String[] a, int l, int u, String x){
				while(l<=u){
					int m = (l+u)/2, tmp = m;
					while(a[tmp] == "" && tmp < u)
						tmp += 1;
					if(tmp == u) u = m-1;
					else{
						if(x.compareTo(a[tmp]) == 0) return tmp;
						else if(x.compareTo(a[tmp]) > 0) u = m - 1;
						else l = tmp+1;
					}
				}
				return -1;
			}
			
//			 @question, find one in sorted row and column matrix
//			 compare from first row last column
				public int[] find_in_matrix(int[][] a, int x, int rowlen, int columnlen){
					int i = rowlen-1;
					int j = columnlen -1;
					int[] pos = new int[2];
					while(i >0 && j > 0){
						if(x == a[i][j]) return {i,j};
						else if(x < a[i][j]) i--;
						else j++;
					}
					return null;
				}
		
// select the Nth elem in a array -- Linear selection: MedMed algorithm
		public int medMed(int[] items, int low, int high){
			int numMedians = (high - low+1)/5;
			int sublow, subhigh;
			int medianIdx = -1;
			for(int i=0;i<numMedians;++i){
				sublow = low+i*5;
				subhigh = high+i*5;
				if(subhigh > high) subhigh = high;
				medianIdx = select(items,sublow,subhigh,2);
				swap(items,low+i,medianIdx);	
			}
			return select(items,low,low+numMedians,numMedians/2);
		}
		
		public int select(int[] items, int low, int high, int nth){
			int mid, idx, k;
			if(high-low+1 < 5)
				findNth(items,nth);
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
		
		public static int selectKth(int[] arr, int k) {
			 if (arr == null || arr.length <= k)
			  throw new Error();
			 
			 int from = 0, to = arr.length - 1;
			 
			 // if from == to we reached the kth element
			 while (from < to) {
			  int r = from, w = to;
			  int mid = arr[(r + w) / 2];
			 
			  // stop if the reader and writer meets
			  while (r < w) {
			 
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
			  if (k <= r) {
			   to = r;
			  } else {
			   from = r + 1;
			  }
			 }
			 
			 return arr[k];
		}
		
/** Design an algorithm that, given a list of n elements in an array, 
 * finds all the elements that appear more than n/3 times in the list. 
 * The algorithm should run in linear time. (n >=0 ) You are expected to use 
 * comparisons and achieve linear time. No hashing/excessive space/ 
 * and don't use standard linear time deterministic selection algo
 */
		
/*		for A[i] in A:
            if A[i] == Num[0]:
                      Count[0]+=2;
                      Count[1]--;
                      Count[2]--;
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
                                  
As far as number X is more than n/3, it will has count as m*2 - (n-m) > 0, 
m is the appear time of X. So X will stay in the parameter when the algorithm finished.                                  
                                  */
		
}
