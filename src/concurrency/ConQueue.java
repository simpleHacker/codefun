package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConQueue {

	private int size;
	private int top;
	private int rear;
	public int[] queue;
	
	private final Lock lock1, lock2;
	
	public ConQueue(int s){
		size = s;
		top=rear = 0;
		queue = new int[s];
		lock1 = new ReentrantLock();
		lock2 = new ReentrantLock();
	}
	
	public Boolean enQueue(int data){
		lock1.lock();
		try{
			if((rear+1) % size == top){
				return false;
			}else{
				queue[rear] = data;
				rear = (rear+1) % size;
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		} finally{
			lock1.unlock();
		}
	}
	
	public Integer deQueue(){
		lock2.lock();
		int temp;
		
		try{
			if(top == rear){
				return null;
			}else{
				temp = queue[top];
				top = (top+1) % size;
				return temp;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		} finally{
			lock2.unlock();
		}
	}
	
	public Integer peek(){
		if(rear != top) return queue[top];
		else return null;
	}
	
}
