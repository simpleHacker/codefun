package struct;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class StringOp {
	
	// find minimum windows in S contains T
	// O(N)
	public boolean findMiniWin(String S, String T){
		int[] hasFound = new int[256];
		Arrays.fill(hasFound, 0);
		
		int[] needToFind = new int[256];
		Arrays.fill(needToFind, 0);
		for(int i=0;i<T.length();++i){
			needToFind[T.charAt(i)]++; 
		}
		
		int count = 0;
		
		int minWin = Integer.MAX_VALUE;
		int minBegin=-1;
		int minEnd=-1;
		
		for(int begin=0, end=0;end<S.length();++end){
			if(needToFind[S.charAt(end)] == 0) continue;
			hasFound[S.charAt(end)]++;
			if(hasFound[S.charAt(end)] < needToFind[S.charAt(end)])
				count++;
			if(count == T.length()){
				while(needToFind[S.charAt(begin)]==0 || hasFound[S.charAt(begin)] > needToFind[S.charAt(begin)]){
					if(hasFound[S.charAt(begin)] > needToFind[S.charAt(begin)]) 
						hasFound[S.charAt(begin)]--;
					begin++;
				}
				
				if(begin-end+1 < minWin){
					minBegin = begin;
					minEnd = end;
					minWin = begin - end+1;
				}
				
				begin++;
			}
		}
		
		System.out.println("min window: "+minWin+"; from "+minBegin+" to "+minEnd);
		return count==T.length()?true:false;
	}
	
//	 check all unique character string
	public boolean checkUnique(String a){
		//map each charater to a bit array, or boolean array
		// when set bit, found it has already been set, return false
		// else return true
		
		BitSet set = new BitSet(52);
		for(int i=0;i<a.length();++i){
			char c = a.charAt(i);
			if(c >= 'A' && !set.get(26+c-'A')) set.set(26+c-'A');
			else if(c<'A' && !set.get(c-'a')) set.set(c-'a');
			else return false;
		}
		return true;
	}
	
// check if two strings are anagrams
	public boolean checkAnagrams(String a, String b){
		if(a.length() != b.length()) return false;
		Hashtable<Character,Integer> table = new Hashtable<Character,Integer>(a.length());
		int count;
		for(int i = 0;i<a.length();++i){
			char p = a.charAt(i);
			if(table.containsKey(p))
				count = table.get(p);
			else
				count = 0;
			table.put(p, count);	
		}
		
		for(int i=0;i<b.length();++i){
			char p = b.charAt(i);
			if(!table.containsKey(p))
				return false;
			else{
				count = table.get(p);
				count--;
				if(count < 0) return false;
				table.put(p, count);
			}
		}
		
		return true;
	}
	
/*
 * generate all legal anagram for a word
 * 
 * Approach I: need dic saved in trie
 * 1, save the word in a set
 * loop
 * 2, pick one char in set and check with dic
 * 3, if legal, remove the char from set, recursive with rest set
 * until no char in set, and if next point-to is leaf in trie, print and return
 * need save the current check pointer in dic and pass to recursion
 * need a already string save so-far legal sub-word
 * 
 * Approach II: need dic small, use hash
 * 1, for all words in dic with the lens as the target, 
 * map to hashtable with all character sorted already, keep in list
 * 2, sort the target and look up in hashtable, and return the list or null
 */ 
	public void findAllAnagram(String w){
		w = w.toLowerCase();
		int[] array = new int[26];

		
		int head;
		Dic p;
		for(int i=0;i<w.length();++i){
			array[w.charAt(i)-'a'] += 1;
		}
		for(int i=0;i<26;++i){
			if(array[i] > 0){
				head = i;
				ArrayList<Integer> path = new ArrayList<Integer>(w.length());
				int[] temp = array.clone();
				temp[i]--;
				p = get(head); 
				checkDic(temp,head,p,w.length()-1, path.add(head));
			}
		}
	}
	public void checkDic(int[] array, int head, Dic p, int len, ArrayList<Integer> word){
		HashSet<Integer> set;
		if(len == 0) {
			print(word);
			return;
		}
		if(p.contain(head)){
			set = p.getChildren(head);
			for(int elem : set){
				int i = checkElem(array,elem);
				if(i>=0){
					array[i]--;
					p = p.get(i);
					checkDic(array,i,p,len--,word.add(i));
				}
			}
		}
		return;
	}
	
//	 if one sring is rotation of another string, use one call to issubString()
//	 also this is used to judge if the two is inter-rotations	
		public boolean checkRotation(String a, String b){
			String c = a+a;
			if(c.indexOf(b) != -1) return true;
			else return false;
		}
		
		/*
		 * find the longest common subsequence
		 * Approach I: recursive 
		 * Approach II: DP and iterative O(nm)
		 */	
			public int lcs_rec(String s1, String s2){
				if(s1.length() == 0 || s2.length() == 0)
					return 0;
				else if(s1.charAt(0) == s2.charAt(0))
					return 1+lcs_rec(s1.substring(1),s2.substring(1));
				else return Math.max(lcs_rec(s1,s2.substring(1)), lc_inter(s1.substring(1),s2));
			}
			
//		 can set a max to record the longest sequence
		public int lc_inter(String s1, String s2){
				int m = s1.length();
				int n = s2.length();
				int[][] c = new int[m+1][n+1];
				char[][] b = new char[m+1][n+1];
				for(int i=0;i<m+1;++i)
					c[i][0] = 0;
				for(int i=0;i<n+1;++i)
					c[0][i] = 0;
				for(int i=1;i<m+1;++i)
					for(int j=1;j<n+1;++j){
						if(s1.charAt(i-1) == s1.charAt(j-1)){
							c[i][j] = c[i-1][j-1]+1;
							b[i][j] = 'D';
						} else {
							if(c[i-1][j] > c[i][j-1]){
								c[i][j] = c[i-1][j];
								b[i][j] = 'U';
							}else{
								c[i][j] = c[i][j-1];
								b[i][j] = 'L';
							}
						}	
					}
				print_LCS(b,s1,m,n);
				return c[m][n];
			}
			
//		 construct the LCS
		public void print_LCS(char[][] b, String X, int i,int j){
				if (i == 0 || j == 0)
					return;
				if (b[i][j] == 'D'){
					print_LCS(b, X, i-1, j-1);
					System.out.println(X.charAt(i));
				} else if (b[i][j] == 'U')
					print_LCS(b, X, i-1, j);
				else print_LCS(b, X, i, j-1);
			}	
			
//		  find continueous one = find common subString
			/* 最长公共子串 DP 
			int dp[30][30];
			 
			void LCS_dp(char * X, int xlen, char * Y, int ylen)
			{
			    maxlen = maxindx = 0;
			    for(int i=0;i<m+1;++i)
					dp[i][0] = 0;
				for(int i=0;i<n+1;++i)
					dp[0][i] = 0;
			    for(int i = 1; i < xlen+1; ++i)
			    {
			        for(int j = 1; j < ylen+1; ++j)
			        {
			            if(X[i-1] == Y[j-1])
			            {
			                if(i && j)
			                {
			                    dp[i][j] = dp[i-1][j-1] + 1;
			                }
			                
			                if(dp[i][j] > maxlen)
			                {
			                    maxlen = dp[i][j];
			                    maxindx = i + 1 - maxlen;
			                }
			            }
			        }
			    }
			    outputLCS(X,maxindx,maxlen);
			}*/
			
			/*
			 * find the longest increasing subsequence in an array
			 * O(nlogn)
			 * L = 0
			 for i = 1, 2, ... n:
			    binary search for the largest positive j ≤ L
			      such that X[M[j]] < X[i] (or set j = 0 if no such value exists)
			    P[i] = M[j]
			    if j == L or X[i] < X[M[j+1]]:
			       M[j+1] = i
			       L = max(L, j+1)
			 */	
		String reverseStr(String s){
				
				String ns = "";
				for(int i=0; i<s.length();++i){
					ns = s.charAt(i) + ns;
				}
				return ns;
		}
//			 sort an array of strings to make anagram close to each other.
		class AnagramComparator implements Comparator<String> {
			public String sortChars(String s){
					char[] content = s.toCharArray();
					Arrays.sort(content);
					return new String(content);
			}
				
			public int compare(String s1, String s2){
					return sortChars(s1).compareTo(sortChars(s2));
			}
//			 Arrays.sort(array,new AnagramComparator());
		}
		
		/**
		 * find the longest sequence without duplicate characters
		 * 
		 * 
		 * 
		 * @param str
		 * @return
		 */
		public String longestNonDupSubstr(String str){
			
			Map<Character,Integer> map = new LinkedHashMap<Character,Integer>();
			int maxLen = 0;
			int maxHead = 0;
			int len = 0;
			int head = 0;
			for(int i=0;i<str.length();++i){
				char c = str.charAt(i);
				if(map.containsKey(c)){
					if(map.get(c) > head){
						len = i - head;
						if(len > maxLen) {
							maxHead = head;
							maxLen = len;
						}
						head = map.get(c)+1;
					}	
				} 
				map.put(c, i);
			}
			return str.substring(maxHead, maxHead+maxLen);
		}
}
