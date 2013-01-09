package algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Applications {
// cracker sort.1
	public void mergeAB(int[] A, int[] B, int Aend){
		int idx = A.length-1;
		int i,j;
		for(i=Aend, j=B.length-1;j>=0 && i>=0;){
			A[idx--] = A[i] >= B[j]?A[i--]:B[j--];
		}
		while(i>=0){
			A[idx--] = A[i];
		}
		while(j>=0){
			A[idx--] = B[j];
		}
	}

// cracker sort.2	
	public void anagramSort(String[] array){
		Hashtable<String,ArrayList<String>> table = new Hashtable<String,ArrayList<String>>();
		for(int i=0;i<array.length;++i){
			String temp = stringSort(array[i].toCharArray());
			if(table.containsKey(temp))
				table.get(temp).add(array[i]);
			else{
				ArrayList<String> list = new ArrayList<String>();
				list.add(array[i]);
				table.put(temp, list);
			}
		}
		
		ArrayList<String> sublist;
		String[] arrayl = table.keySet().toArray(array);
		Arrays.sort(arrayl);
		int p = 0;
		for(int i=0;i<arrayl.length;++i){
			 sublist = table.get(arrayl[i]);
			 for(String elem : sublist)
				 array[p++] = elem;
		}
	}
	
	public String stringSort(char[] item){
		StringBuffer newstr = new StringBuffer();
		Arrays.sort(item);
		for(int i=0;i<item.length;++i)
			newstr.append(item[i]);
		return newstr.toString();
	}
// cracker sort.3	
	
}
