package struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class ArrayOp {
	
	/**
	 * Given an array of n elements which contains elements from 0 to n-1, 
	 * with any of these numbers appearing any number of times. 
	 * Find these repeating numbers in O(n) and using only constant memory space.
	 * 
	 * @param str
	 */ 
	public void findDup(int[] arr){
		int size = arr.length;
		for(int i=0;i<size;++i){
			if(arr[Math.abs(arr[i])] > 0){
				arr[arr[i]] = -arr[arr[i]];
			}else
				System.out.print(arr[i]+" ");
			
		}
	}
	
// replace all the same char in a string with another string or char
	public void replaceChar(char[] str){
		// first, calculate the lengh of the new string
		// reallocate the new size of memeory to the str
		// put the '\0' to the end of new memory
		// scan the string from the end, move the char back to the right place
		// if get space, replace with three chars '%20', care the order
	}

	// find the longest increasing sequence
	// O(nlogn)
	public void findLIS(int[] a){
		int[] m = new int[a.length]; // save location
		int[] p = new int[a.length]; // save previous elem index in the sequence
		
		m[0] = 0;
		p[0] = -1;
		
		int max = 0, index=-1;
		for(int i=0;i<a.length;++i){
			index = binaryS(a[i], m, max); // (find the index of elem in m[] that larger than a[i])-1 
			if(index == -1){
				m[0] = i; 
			}else{
				p[i] = m[index];
			//	if(j == max || a[i]<a[m[j+1]]){
				m[index+1] = i;
				max = Math.max(max,index+1);
			//	}
			}
		}
		
		for(int i = m[max];i>=0;i=p[i]){
			System.out.println(a[i]);
		}
	}
	
	
	
	/**
	 *  facebook-interview-questions

Given an virtual 4x4 boggle board, and some 4 letter words, determine if the words are in the board
ex.

S M E F
R A T D
L O N I
K A F B

STAR- no
TONE- no
NOTE- yes
SAND- yes
etc.
	 * 
	 * Approach:
	 * my: turn it to a graph, and check if exist a path by BFS O(m) with branch cut
	 * 
	 * main approach: matrix operation
	 * pair: int, int
	 * need to keep all possible path in list, and check each one when not succeed
	 * also need to retro back the visited[][] value when failed
	 */
	
	public Pair checkNeis(int i, int j, char[][] board, char c){
		int ui = i-1, di = i+1;
		int lj = j-1, rj = j+1; 
		String t; 
		Pair p = new Pair();
		if(ui>=0){
			if(!visited[ui][j] && board[ui][j] == c){
				visited[ui][j] = true;
				p.i = ui; p.j = j;
				return p;
			}else
			if(lj>=0 && !visited[ui][j] && board[ui][j] == c){
				visited[ui][lj] = true;
				p.i = ui; p.j = lj;
				return p;
			}else
			if(rj>=0 && !visited[ui][rj] && board[ui][rj] == c){
				visited[ui][rj] = true;
				p.i = ui; p.j = rj;
				return p;
			}
		}
		if(di < n){
			if(!visited[di][j] && board[di][j] == c){
				visited[di][j] = true;
				p.i = di; p.j = j;
				return p;
			}else
			if(lj>=0 && !visited[di][j] && board[di][j] == c){
				visited[di][lj] = true;
				p.i = di; p.j = lj;
				return p;
			}else
			if(rj>=0 && !visited[di][rj] && board[di][rj] == c){
				visited[di][rj] = true;
				p.i = di; p.j = rj;
				return p;
			}
		}
		if(lj>=0 && !visited[i][j] && board[i][j] == c){
			visited[i][lj] = true;
			p.i = i; p.j = lj;
			return p;
		}else
		if(rj>=0 && !visited[i][rj] && board[i][rj] == c){
			visited[i][rj] = true;
			p.i = i; p.j = rj;
			return p;
		}
		return null;
	}
	
	public boolean checkWord(char[][] board, String word, int n, int m){
		char c = word.charAt(0);
		int row=-1, col=-1;
		boolean[][] visited = new boolean[n][m];
		for(int i=0;i<n;++i)
			for(int j=0;j<m;++j){
				visited[i][j] = false;
				if(c == board[i][j]){
					row = i; col = j;
					break;
				}
			}
		if(row == -1){
			System.out.println("no such word");
			return false;
		}
		Pair p;
		for(int i=1;i<word.length();++i){
			c = word.charAt(i);
			p = checkNeis(row, col, board, c);
			if(p == null) return false;
			row = p.i;col = p.j;
		}
		return true;
	}
	
	/* Question 1:
	 * You have k lists of sorted integers. 
	 * Find the smallest range that includes at least one number from each of the k lists.
	For example,
	List 1: [4, 10, 15, 24, 26]
	List 2: [0, 9, 12, 20]
	List 3: [5, 18, 22, 30]

	The smallest range here would be [20, 24] as it contains 24 from list 1, 20 from list 2, 
	and 22 from list 3.
	 *
	 * approach I:
	 * 1, make pointers points to the first elem of each list
	 * 2, compare them and record the range as min if the range less than min
	 * 3, move the pointer/index of smallest elem forward by 1 step
	 * 4, goto 2 until the smallest elem pointer reach the end of the list
	 * 5, output the range
	 * 
	 * O(n*k), n is the len of the smallest-pointer-reach-end list
	 * 
	 * Application: 
	 * Given X number of search terms, write an algorithm that will 
	 * return the smallest substring from an article that contains all of the search terms.
	 * 
	 */
	
	/**
	 * find all pairs of integers within an array which sum to a specific value
	 * how to handle the the duplicates
	 * 
	 * Approach I: hashtable
	 * Approach II: no extra space
	 */
	
	public void findPair(int[] array, int value){
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>(2*array.length);
		int a;
		for(int i=0;i<array.length;++i){
			if(table.contains(value-array[i]))
				if(table.get(value-a) > 0){
					System.out.println(a+" and "+(value-a));
					table.put(value-a,table.get(value-a)-1);
					continue;
				}
			if(!table.contains(array[i])){
				a = table.get(array[i]);
				table.put(array[i],a+1);
			}else
				table.put(array[i],1);
		}
	}
	

	
	public void findPair2(int[] array, int value){
		Arrays.sort(array);
		int first = 0;
		int end = array.length-1;
		while(first < end){
			int s = array[first]+array[end];
			if(s == value) {
				System.out.println(array[first]+" and "+array[end]);
				first++;
				end--;
			}
			else if(s < value){
				first++;
			} else end--;
		}
	}
	
	/**
	 * following coins: half dollar, quarters,dime, nickel and penny. 
	 * Print all the possible combinations of coins that will equal to one dollar.
	 * 
	 * using search and back tracking way. 
	 * 
	 * @param list
	 * @param sum
	 * @param array  store all kinds of coins, and also sorted for branch searching cut
	 * @param i
	 */
	public void findDollar(List<Integer> list, int sum, int[] array, int i){
		if(i > array.length) return;
		else{
			if(sum+array[i] == 100){
				list.add(array[i]);
				System.out.println(list);
			}else if(sum+array[i] < 100){
				list.add(array[i]);
				findDollar(list,sum+array[i],array,i+1);
				list.remove(i);
				findDollar(list,sum,array,i+1);
			}else return;
		}
	}
	
//	 find the max diff of elements in order of appearance
// O(N)	
	public int findMaxDiff(int[] arr){
		if(arr == null) return -1;
		if(arr.length <= 1) return 0;
		int min = arr[0];
		int maxd = -1;
		int minInd = 0;
		int maxInd = 0;
		for(int i=1;i<arr.length;++i){
			if(arr[i]-min > maxd){
				maxInd = i;
				maxd = arr[i] - min;
			}
			if(arr[i] < min){
				minInd = i;
				min = arr[i];
			}
		}
		return maxd;
	}
	
	/**
	 * find the max index diff by A[i] < A[j], j>i
	 * O(N)
	 * 
	 * @param arr
	 * @return
	 */
	public int findMaxGap(int[] arr){
		// create min array
		int[] min = new int[arr.length];
		min[0] = arr[0];
		for(int i=1;i<arr.length;++i){
			min[i] = arr[i] < min[i-1]?arr[i]:min[i-1];
		}
		
		// create max array
		int[] max = new int[arr.length];
		max[arr.length-1] = arr[arr.length-1];
		for(int i=arr.length-2;i>=0;--i){
			max[i] = arr[i] > max[i+1]?arr[i]:max[i+1];
		}
		
		// find the max gap
		int maxDiff = 0;
		int i = 0, j = 0;
		int startj;
		while(j<arr.length){
			while(max[j] > min[i] && j < arr.length) j++;
			if(j-i > maxDiff) maxDiff = j-i;
			if(j == arr.length) break;
			j++;
			i++;
		}
		return maxDiff;
	}      
}
