package struct;

import java.util.Hashtable;

public class ArrayString {

// check all unique character string
	public void checkUnique(String a){
		//map each charater to a bit array, or boolean array
		// when set bit, found it has already been set, return false
		// else return true
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
	
// replace all the same char in a string with another string or char
	public void replaceChar(char[] str){
		// first, calculate the lengh of the new string
		// reallocate the new size of memeory to the str
		// put the '\0' to the end of new memory
		// scan the string from the end, move the char back to the right place
		// if get space, replace with three chars '%20', care the order
	}
	
// set row and columns in a matrix that cell is 0
	public void setZero(int[][] matrix){
		// use an row array record, which row has 0 cell
		// use a column array record which column has 0 cell
		// then in a nested loop, set the row and columns with 0 cell all to 0
	}
	
// if one sring is rotation of another string, use one call to issubString()
// also this is used to judge if the two is inter-rotations	
	public boolean checkRotation(String a, String b){
		String c = a+b;
		if(c.indexOf(b) != -1) return true;
		else return false;
	}
	
	
}
