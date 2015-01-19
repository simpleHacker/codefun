package struct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Flatten a List<List<Integer>> in Java and implement the hasNext() and next() methods.
e.g. [[6,8],4] should return true when at 6, 8 and false at 4.
 */

// refer to http://algokoder.blogspot.com/2008/10/flatten-iterator.html

public class FlatternList<T> implements Iterator<T>, Iterable<T>{
	private List<List<T>> listoflist;
	private int elemindex = 0;
	private int listindex = 0;
	private List<T> curlist;
	private T next;
	
	public FlatternList(List listoflist){
		this.listoflist = listoflist;
		Object elem;
		if(listoflist.size() > 0){
			elem = listoflist.get(listindex++);
			if(elem instanceof List){
				curlist = (List) elem;
				next = curlist.get(elemindex++);
			} else{
				curlist = null;
				next = (T) elem;
			}
		}else{
			next = null;
		}
	}
	
	@Override
	public boolean hasNext(){
			if(next != null) return true;
			else return false;
	}
	
	@Override
	public T next(){
		T value = next;
		Object elem;
		if(next != null){
			if(curlist != null && elemindex < curlist.size()){
				next = curlist.get(elemindex++);
			}else{
				if(listindex < listoflist.size()){
					elem = listoflist.get(listindex++);
					if(elem instanceof List){
						curlist = (List) elem;
						elemindex = 0;
						next = curlist.get(elemindex++);
					} else{
						curlist = null;
						next = (T) elem;
					}
				}else{
					next = null;
				}
			}
		}
		return value;
	}
	
	@Override
	public void remove(){
		
	}
	
	@Override
	public Iterator<T> iterator(){
		return this;
	}
	
	public static void main(String[] args){
		//[[6,8],4,[1,9,6],5]
		List lol = new ArrayList();
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		l1.add(6); l1.add(8);
		lol.add(l1); 
		lol.add(4);
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		l2.add(1);l2.add(9);l2.add(6);
		lol.add(l2);
		lol.add(5);
		
		FlatternList flist = new FlatternList(lol);
		
		while(flist.hasNext()){
			System.out.print(flist.next());
		}
	}
}
