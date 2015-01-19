package coding;

import java.util.List;

public class Recursion {
	
	// find all possible coin combination
	public int findCoins(int n, int cent, List<Integer> list){
		int nextCent = 0;;
		switch(cent){
		case 25:
			nextCent = 10;
			break;
		case 10:
			nextCent = 5;
			break;
		case 5:
			nextCent = 1;
			break;
		case 1:
			return 1;
		}
		int ways = 0;
		for(int i=0;i*cent<n;i++){
			list.add(cent);
			ways += findCoins(n-i*cent,nextCent,list);
		}
		return ways;
	}
}
