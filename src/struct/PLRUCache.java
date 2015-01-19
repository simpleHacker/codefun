package struct;

import java.util.HashMap;
/**
 * LRU cache:
 * use two data structures here: HashMap and Double linked list;
 * to make the get() and set() to be O(1)
 * 
 * @author AI
 *
 */
public class PLRUCache {
	private class LinkedNode<T>{
		T value;
		LinkedNode<T> next;
		LinkedNode<T> pre;
		public LinkedNode(){
			next = null;
			pre = null;
		}
	}
	private class DLinkedList<T> {
		LinkedNode<T> top;
		LinkedNode<T> rear;
		int size;
		public DLinkedList(){
			size = 0;
			top = null;
			rear = null;
		}
		
		public void addFirst(LinkedNode<T> node){
			node.next = top;
			node.pre = null;
			if(top != null)
				top.pre = node;
			top = node;
			if(rear == null)
				rear = node;
			size++;
		}
		
		public void remove(LinkedNode<T> node){
			if(node.next != null)
				node.next.pre = node.pre;
			if(node.pre != null)
				node.pre.next = node.next;
			if(top == node) 
				top = node.next;
			if(rear == node)
				rear = node.pre;
			size--;
		}
		
		public void removeLast(){
			if(rear != null){
				remove(rear);
			}
		}
		
		public void showAll(){
			LinkedNode p = top;
			while(p != rear){
				System.out.print(p.value+" ");
				p = p.next;
			}
			if(p!= null)
				System.out.println(p.value);
		}
	}
	
	private DLinkedList<String> list;
	private HashMap<String,LinkedNode<String>> map;
	private final int capacity;
	
	
	public PLRUCache(int capicity){
		list = new DLinkedList<String>();
		map = new HashMap<String,LinkedNode<String>>();
		this.capacity = capicity;
	}
	
	public void visit(String key){
		LinkedNode<String> node = null;
		if(map.containsKey(key)){ //O(1)
			node = map.get(key);
			list.remove(node); //O(1)	
			list.addFirst(node); //O(1)
		}else{
			if(list.size == capacity){
				map.remove(list.rear.value);
				list.removeLast(); //O(1)
			}
			
			node = new LinkedNode<String>();
			node.value = key;
			list.addFirst(node); //O(1)
			map.put(key, node);
		}
	}
	
	// in order
	public void showCache(){
		list.showAll();
	}
	
}
