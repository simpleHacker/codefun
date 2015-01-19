package algorithm.sort;

import interview.Today;
import interview.Today.FComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
		Arrays.sort(item);
		return new String(item);
	}
// cracker sort.3	
	
	/**
	 * sort words in order of its frequency (first) and word lexical order (secondary)
	 */
	class FComparator implements Comparator<String>{
		Map<String,Integer> base;
		public FComparator(Map<String,Integer> base){
			this.base = base;
		}
		
		public int compare(String a, String b){
			if(base.get(a) > base.get(b)){
				return -1;
			} else if(base.get(a) < base.get(b)){
				return 1;
			} else{
				if(a.compareTo(b) > 0) return 1;
				else return -1;
			}
		}	
	}
	
	public void wSort(String[] list){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		FComparator fc = new FComparator(map);
		TreeMap<String,Integer> sortMap = new TreeMap<String,Integer>(fc);
		
		int count;
		for(String elem : list){
			if(map.containsKey(elem)){
				count = map.get(elem);
				map.put(elem, count+1);
			}else{
				map.put(elem, 1);
			}
		}
		
		sortMap.putAll(map);
		
		for(Map.Entry<String, Integer> elem : sortMap.entrySet()){
			
			System.out.println(elem.getKey()+":"+elem.getValue());
		}
		/*for(String key : sortMap.keySet()){
			System.out.println(key+":"+sortMap.get(key));
		}*/
	}
	
	public void wordsSort(String[] args){
		String[] list = {"apple","peal","orange","nut","peal","apple","banana","nut","apple"};
		wSort(list);
	}
	
	/**
	 * sort the words in descending order occurence frequency 
	 * @param wordsInfo word map with frequncy information
	 */
	public void printWordInDescendingFreqOrder(Map<String, Integer> wordsInfo){
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(wordsInfo.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<String,Integer>>(){
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String,Integer> o2){
				int value = (o2.getValue()).compareTo(o1.getValue());
				if(value > 0){
					return 1;
				}else if(value < 0){
					return -1;
				} else{
					return o1.getKey().compareTo(o2.getKey());
				}	
			}
		});
		
		for(Map.Entry<String, Integer> elem : list){
			System.out.println(elem.getKey()+":"+elem.getValue());
		}
	}
	
	/**
	 * merge two interval list 
	 * {(1,3), (4,6), (7,10)}+{(2,4)} = 
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public ArrayList<Pair> mergeInterval(Pair[] p1, Pair[] p2){
		ArrayList<Pair> p = new ArrayList<Pair>();
		int i=0,j=0;
		while(i<p1.length && j<p2.length){
			switch(compare(p1[i],p2[j])){
				case 1: p.add(p2[j]);j++;break;
				case -1: p.add(p1[i]);i++;break
				case -2: p2[j] = join(p1[i],p2[j], -1);i++;break;
				case 2: p1[i] = join(p1[i],p2[j],1);j++;break;
			}
		}
		while(i<p1.length) p.add(p1[i++]);
		while(j<p2.length) p.add(p2[j++]);
		return p;
	}
	
	public int compare(Pair p1, Pair p2){
		if(p1.e1 <= p2.e2 && p1.e2 <= p2.e2) return -2;
		else if(p1.e1 <= p2.e2 && p1.e2 >= p2.e2) return 2;
		else if(p1.e2 < p2.e1) return -1;
		else return 1;
	}
	
	public void join(Pair p1, Pair p2, int mark){
		if(mark > 0){
			p1.e1 = min(p1.e1,p2.e1);
		}else{
			p2.e1 = min(p1.e1,p2,e1);
		}
	}
	
	
}
