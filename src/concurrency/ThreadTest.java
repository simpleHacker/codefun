package concurrency;

import java.util.Stack;


public class ThreadTest {
	static void threadMessage(String message){		
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}
	
	class Queue{
		private int size;
		private int top;
		private int rear;
		private int[] queue;
		
		public Queue(int size){
			this.size = size;
			top = rear = 0;
			queue = new int[size];
		}
		
		public synchronized boolean enQueue(int data){
			if((rear+1) % size == top){ // full
				return false;
			}else{
				queue[rear] = data;
				rear = (rear+1) % size;
				return true;
			}
		}
		
		public synchronized Integer deQueue(){
			int elem;
			if(rear == top) return null;
			else{
				elem = queue[top];
				top = (top+1) % this.size;
				return elem;
			}
		}
		
		public Integer peek(){
			if(rear != top) return queue[top];
			else return null;
		}
	}
	
	private static class MessageLoop implements Runnable{
		private static int count=0;
		public void counter(){
			this.count++;
			threadMessage(":"+this.count);
		}
		public void print(){
			System.out.println(this.count);
		}
		public void run(){
			String importantInfo[] = {
	                "Mares eat oats",
	                "Does eat oats",
	                "Little lambs eat ivy",
	                "A kid will eat ivy too"
	        };
			
			try{
				for(int i=0;i<importantInfo.length;++i){
					counter();
					Thread.sleep(4000);
					threadMessage(importantInfo[i]);
				}
			} catch(InterruptedException e){
				threadMessage("I am not done!");
			}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		long patient = 1000 * 100;
		threadMessage("Starting MessageLoop thread");
		long startTime = System.currentTimeMillis();
		Thread t1 = new Thread(new MessageLoop(),"t1");
		t1.start();
		Thread t2 = new Thread(new MessageLoop(),"t2");
		t2.start();
		threadMessage("Waiting for MessageLoop thread to finish");
		
		while(t1.isAlive() && t2.isAlive()){
		//	threadMessage("Still waiting...");
		//	t1.join(1000);
		//	t2.join(500);
			if(System.currentTimeMillis() - startTime > patient && t1.isAlive()){
				t1.interrupt();
				t1.join();
			}
			if(System.currentTimeMillis() - startTime > patient && t2.isAlive()){
				t2.interrupt();
				t2.join();
			}
		}
		threadMessage("Finally!");
	}
}
