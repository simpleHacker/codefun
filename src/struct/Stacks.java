package struct;

import java.util.ArrayList;

public class Stacks {
	int[] arrays;
	ArrayList<ArrayList<Integer>> stacks;
	int[] freelist;
	int top;
	int fullsize;
	
	public Stacks(int size){
		arrays = new int[size];
		stacks = new ArrayList<ArrayList<Integer>>(3);
		for(int i=0;i<size;++i){
			ArrayList<Integer> stack = new ArrayList<Integer>();
			stacks.add(stack);
		}
		freelist = new int[size];
		for(int i=0;i<size;++i)
			freelist[i] = i;
		top = fullsize = size;
	}
	
	public int pop(int no){
		if(no > 3){
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
			return -1;
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
}
