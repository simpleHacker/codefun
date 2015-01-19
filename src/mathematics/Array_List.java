package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Array_List {

	/**
	 * DP version Fib number, O(n)
	 * 
	 * @param n
	 * @return the nth fib number
	 */
	int Fib(int n){
		int[] arr = new int[n];
		arr[0] = 0;
		arr[1] = 1;
		for(int i=2;i<n;++i){
			arr[i] = arr[i-1]+arr[i-2];
		}
		return arr[n];
	}
	
	/*
	 * print a number in letters
	 */
	
	public void wordprint(int num){
		String arr[][]={{"zero","one","two","three","four","five","six","seven","eight","nive"},
				{"ten","elevine","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nighteen"},
				{"twenty","thirty","fourty","fifty","sixty","seventy","eighty","nighty"}};
		String snum = String.valueOf(num);
		if(snum.length() == 1){
			System.out.print(arr[0][num]);
		}else if(snum.length() == 2){
			if(snum.charAt(0) == '1') System.out.print(arr[1][num%10]);
			else { 
				if(snum.charAt(1) == '0') System.out.print(arr[2][num/10-2]);
				else System.out.print(arr[2][num/10-2]+"-"+arr[0][num%10]);
			}
		}
	}
	
	/*
	 * convert roman number to int
	 */	
		public int Roman2Int(String s) 
		{
			if(s.length()==0)return 0;
			Map<Character,Integer> tab = new HashMap<Character,Integer>();
	//		unordered_map<char,int> table;
	//		Map<Character,Integer> map = new HashMap<Character,Integer>();
			tab.put('I', 1);
			tab.put('V', 5);
			tab.put('X', 10);
			tab.put('L', 50);
			tab.put('C',100);
			tab.put('D', 500);
			tab.put('M', 1000);
			int ans = tab.get(s.charAt(s.length()-1));
			for(int i=s.length()-2;i>=0;i--)
			{
				if(tab.get(s.charAt(i)) < tab.get(s.charAt(i+1)))
					ans -= tab.get(s.charAt(i));
				else
					ans += tab.get(s.charAt(i));
			}
			return ans;
		}


	
/*
 *  facebook-interview-questions

Given a function KNOWS(A,B), which returns 1 if A knows B (and not necessarily the other way around) and 0 if A does not know B.

A Celebrity is one who does not know anyone,
and one who is known by everybody.

For a list of N people, find all celebrities in linear time.
 */	
	
	public int findCelebrity(boolean[][] knows, int n){
		int can = 0;
		for(int i=1;i<n;++i){
			if(knows[can][i] && !knows[i][can]) can = i;
			else if(knows[can][i] && knows[i][can]) can = ++i;
			else if(!knows[can][i] && !knows[i][can]) can = ++i;
		}
		if(can == n) return -1;
		for(int i=0;i<n;++i){
			if(i == can) continue;
			if(!knows[i][can] || knows[can][i]) return -1;
		}
		return can;
	}

	
/** Deck shuffle
 * Given an array of distinct integers, give an algorithm to randomly reorder the
integers so that each possible reordering is equally likely. In other words, given a
deck of cards, how can you shuffle them such that any permutation of cards is
equally likely?

Good answer: Go through the elements in order, swapping each element with a
random element in the array that does not appear earlier than the element. This
takes O(n) time.
 */	
	public void deckShuffle(int[] deck){
		int temp;
		int d;
		for(int i=0; i<deck.length;++i){
			temp = deck[i];
			d = i+ (int) Math.random()*(deck.length-i);
			deck[i] = deck[d];
			deck[d] = temp;
		}
	}
	
/**
 *  google-interview-questions

How do you find the greatest 1000 elements in a list of a million elements? 
No other information given. What would be the runtime? 
Hint: You can do better than O(n log 1000). 

Approach: minimum heap with 1000 slot, no swap, only adjust 
 */	
	

}
