package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

public class StringMatch {
	
	// find the Largest common prefix of N strs
	public String LCP(String[] strs){
		if(strs == null) return "";
		if(strs.length == 1) return strs[0];
		
		int p = 0;
		
		String str = strs[0];
		int max = str.length();
		for(int i=1;i<strs.length;++i){
			for(int j=0;j<max && j<strs[i].length();++j){
				if(str.charAt(j) != strs[i].charAt(j)){
					max = j;
					break;
				}
					
			}
		}
		
		return str.substring(0, max);
	}

	/*
	 * Return a shortest prefix of <code>word</code> that is <em>not</em> a prefix of any word in the <code>list</code>

e.g.
word: cat, it has 4 prefixes: “”, “c”, “ca”, “cat”
list: alpha, beta, cotton, delta, camera
Result is “cat”

	 * Approach I: match the c with each string in list, 
	 * and record the longest match len;
	 * 
	 * Approach II: build Digital search tree, use child-sibling tree
	 * O(list.len*), build needs O(all words len) extra space, O(c.len*list.len) time
	 * O(word.len)
	 */
	
// O(c.len*list.len)	
	public String findPrefix(String c, String[] list){
		int len, max=0;
		for(int i=0;i<list.length;++i){
			len = 0;
			String elem = list[i];
			for(int j=0;j<c.length();++j){
				if(c.charAt(j) == elem.charAt(j)) len++;
				else break;
			}
			if(len > max) max = len;
		}
		if(max < c.length()) return c.substring(0, max+1);
		else return c;
	}
	
	/*
	 * Given the English alphabet, 'a' through 'z' (lowercase), 
	 * and an imaginary onscreen keyboard with the letters laid out in 6 rows and 5 columns:

	a b c d e
	f g h i j
	k l m n o
	p q r s t
	u v w x y
	z

	 * Using a remote control - (up - 'u', down 'd', left 'l', right 'r' and enter '!'), 
	 * write a function that given a word will produce the sequence of key presses 
	 * required to type out the word on the onscreen keyboard. 
	 * The function should return the sequence string. 
	 * 
	 * Approach:
	 * 1, find the coordinates of a char of the word in this board;
	 * 2, move from current coordinates to wantted one, and record move in sequence
	 */
	
	public String moveSequence(String c, int x, int y){
		StringBuffer seq = new StringBuffer();
		for(int i=0;i<c.length();++i){
			int diff = c.charAt(i)-'a';
			int posx = diff/5;
			int posy = diff % 5;
			
			int x_diff = x - posx;
			int y_diff = y - posy;
		// when firstly up and down move, the "z" is not a problem for moving to left	
			if(y <=4){
				if(x_diff <0) move(-x_diff, seq, 'r');
				else move(x_diff,seq, 'l');
				if(y_diff < 0) 
					move(-y_diff, seq, 'd');
				else move(y_diff, seq, 'u');
			} else{
				if(y_diff > 0) 
					move(y_diff, seq, 'u');
				if(x_diff <0) move(x_diff, seq, 'r');
			}			
			x = posx;
			y = posy;
		}
		seq.append('!');
		return seq.toString();
	}
	
	public void move(int step, StringBuffer seq, char m){
		while(step-- > 0){
			seq.append(m);
		}
	}
	
	/*
	 * You are given a dictionary, in the form of a file that contains one word per line. 
	 * E.g.,
		abacus
		deltoid
		gaff
		giraffe
		microphone
		reef
		qar
		You are also given a collection of letters. E.g., {a, e, f, f, g, i, r, q}.
	 * The task is to find the longest word in the dictionary that can be spelled with the collection of
	 * letters. For example, the correct answer for the example values above is “giraffe”. 
	 * (Note that “reef” is not a possible answer, because the set of letters contains only one “e”.)
	 * 
	 * Appraoch: O(N), N is the sum len of all words in dic
	 */
	
	public void findLongestWord(String[] dics, char[] collect){
		int[] col = new int[26];
		int[] temp;
		
		for(int i=0;i<col.length;++i){
			col[i] = 0;
		}
		// put collection character in array in alphabeta order as index increasing
		// also record each char's count info in slot
		for(int i=0;i<collect.length;++i){
			col[collect[i]-'a']++;
		}
		int index, flag, max = 0, pos;
		
		// take each word from dic, and check with collection array
		for(int i=0; i<dics.length;++i){
			temp = col.clone();
			String word = dics[i];
			if(word.length()>collect.length) continue;
			flag = 0;
			for(int j=0; j<word.length();++j){
				index = word.charAt(j)-'a';
				if(temp[index] == 0){
					flag = 1;
					break;
				}
				else{
					temp[index]--;
				}
			}
			if(flag == 0 && word.length() > max){
				max = word.length();
				pos = i;
			}
		}
	}
	
/**
 * transform from one word to another, inter-words in the path must be legal
 * output is path. O(26^4), all possible words in the same lens been generated or checked.
 * getOneEditedWord() take O(n*26)
 * Totally, maybe better than O(26^4), because some words stoped by check its legalty.
 * 
 */	
	boolean transform(String w1, String w2, ArrayList<String> path){ // DFS

	    if(w1.equals(w2)) {
	    	printPath(path);
	    	return true;
	    }
	    path.add(w1);
	    boolean state;
	    for(String w : getOneEditedWord(w1, path)){
	        path.add(w);
	        state = transform(w,w2,path);
	        if(state) return true; // this keep the path reserved
	        path.remove(w);

	    }
	    return false;
	}

	ArrayList<String> getOneEditedWord(String w, ArrayList<String> path){
	    ArrayList<String> list = new ArrayList<String>();
	    char[] word;
	    String temp;
	    for(int i=0;i<w.length();++i){
	        for(char c='A';c<='Z';++c){
	            if(c != w.charAt(i)){
	                word = w.toCharArray();
	                word[i] = c;
	                temp = new String(word);
	                if(isLegal(temp) && !path.contains(temp)) list.add(new String(word));   
	            }
	        }

	    }
	    return list;
	}
	
	/**
	 * find the longest substring parlindro
	 * using DP : [http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html]
	 * O(N)
	 */
	public String preprocess(String arr){
		if(arr == null) return "[]";
		StringBuilder ss = new StringBuilder(arr.length());
		ss.append('[');
		if(arr.length() != 0) {
			for(int i =0;i<arr.length();++i){
				ss.append('#').append(arr.charAt(i));
			}
			ss.append('#');
		}
		ss.append(']');
		return ss.toString();
	}
	
	public String findLP(String arr){
		String tar = preprocess(arr);
		if(tar.equals("[]")) return "";
		int len = tar.length();
		
		int R = 0, C=0;
		int[] P = new int[len];
		Arrays.fill(P,0);
		
		for(int i=1;i<len-2;++i){
			int ii = 2*C-i;
			
			P[i] = (R >i)?min(R-i,P[ii]):0;
			// extend the radius around the center
			while(tar.charAt(i+1+P[i]) == tar.charAt(i-1-P[i]))
				P[i]++;
			
			if(i+P[i] > R){
				R = i+P[i];
				C = i;
			}
		}
		int maxlen = 0;
		int cindex = 0;
		for(int i=0;i<len;++i){
			if(P[i] > maxlen) {
				maxlen = P[i];
				cindex = i;
			}
		}
		return arr.substring((cindex-1-maxlen)/2, (cindex+maxlen)/2);		
	}
	
//	 O(str.length()*pattern.length)
	/**
	 * replace all pattern strings in str, continous pattern to be onw
	 */
	public boolean isMatch(String str, String pattern, int p){
		for(int i=0;i<pattern.length();++i){
			if(p<str.length() && str.charAt(p) == pattern.charAt(i))
				p++;
			else {
				return false;
			}
		}
		return true;
	}
	public String replace(String str, String pattern){
		boolean match;
		String newStr="";
		for(int i=0;i<str.length();++i){
			int p = i;
			match = false;
			// deal with continuous matching case
			while(isMatch(str,pattern,p)){
				match = true;
				i += pattern.length();
			}
			
			if(match) newStr += 'X';
			else newStr += str.charAt(i);
		}
		return newStr;
	}
	
}
