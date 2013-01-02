package algorithm.search;

public class Search {

//	 @question, rotated sorted list, search a elem in O(log(n))	
//	 also need to compare with two ends for next index updates 	
		public int search_in_rotation(int[] a, int l, int u, int x){
			while(l<=u){
				int m = (l+u)/2;
				if(a[l] < a[u]){
					if(x == a[m]) return m;
					else if(x < a[m]) u = m-1;
					else l = m+1;
				}else{
			// disorder, consider both sides for index updates		
					if(x < a[m]) u = m-1;
					else if (x > a[l]) u = m-1;
					else l = m+1;
				}
			}
			return -1;
		}
		
}
