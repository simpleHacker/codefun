package struct;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Multiple stack system
 * @author AI
 *
 */
public class Stacks {
	int[] arrays;
	ArrayList<ArrayList<Integer>> stacks;
	int[] freelist;
	int top;
	int fullsize;
	
	public Stacks(int size){
		arrays = new int[size];
		stacks = new ArrayList<ArrayList<Integer>>(size);
		for(int i=0;i<size;++i){
			ArrayList<Integer> stack = new ArrayList<Integer>();
			stacks.add(stack);
		}
		freelist = new int[size];
		for(int i=0;i<size;++i)
			freelist[i] = i;
		fullsize = size;
		top = 0;
	}
	
	public Integer pop(int no){
		if(no > stacks.size()){
			System.out.println("stack "+no+" is not existed!");
			return -1;
		}
		ArrayList<Integer> stack = stacks.get(no-1);
		int index;
		if(!stack.isEmpty()){
			index = stack.remove(stack.size()-1);
			freelist[top] = index;
			top++;
			return arrays[index];
		}else{
			System.out.println("stack "+no+" is empty!");
			return null;
		}
	}
	
	public boolean push(int elem, int no){
		ArrayList<Integer> stack = stacks.get(no-1);
		int index;
		if(top < 0){
			System.out.println("no memroy for operation!");
			return false;
		}
		else{
			index = freelist[top];
			top--;
			stack.add(index);
			arrays[index] = elem;
			return true;
		}
	}
	
	public boolean empty(int no){
		if(top == fullsize) return true;
		else return false;
	}
	
// only use 2 stack and pop peek to sort an stack.	
	// worst: O(n^2), Best: O(n)
	public Stack<Integer> stackSort(Stack<Integer> array){
		Stack<Integer> s1 = new Stack<Integer>();
		s1.push(array.pop());
		int r;
		while(!array.isEmpty()){
			r = array.pop();
			while(r>s1.peek() && !s1.isEmpty()){
				array.push(s1.pop());
			}
			s1.push(r);
		}
		return s1;
	}
}

class StackMin extends Stack<Integer>{
	private Stack<Integer> s2;
	private int min;
	
	public void stackSort(){
		Stack<Integer> s1 = new Stack<Integer>();
		s1.push(super.pop());
		int r;
		while(!super.isEmpty()){
			r = super.pop();
			while(r<s1.peek() && !s1.isEmpty()){
				super.push(s1.pop());
			}
			s1.push(r);
		}
		while(!s1.isEmpty()){
			super.push(s1.pop());
		}
	}
	
	public void push(int value){
		super.push(value);
		stackSort();
	}
	
	public Integer pop(){
		int value = super.pop();
		return value;
	}
	
	public int min(){
		if(s2.isEmpty())
			return Integer.MAX_VALUE;
		else
			return super.peek();
	}
}

//Hanoi
class Tower{
	// moveDisks(n-1, buffer, destination)
	// moveTopto(destination)
	// buffer.moveDisks(n-1,destination,this)
}

// two stacks implement one queue
class MyQueue{
	// lazy way to pop and peek, only when second stack is empty, 
	// transfer all from s1 to s2
}

