package concurrency;


import java.util.Arrays;

public class WriteAndReadEx {
	static void threadMessage(String message){		
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}
	
	
	private static class QueueWriter implements Runnable{
		
		static int count;
		ConQueue q;
		
		public QueueWriter(ConQueue q){
			this.count = 0;
			this.q = q;
		}
		
		public void run(){
			
			try{
					for(int i=0;i<10;++i){
						Thread.sleep(1000);
						threadMessage("enqueue: "+q.enQueue(count++));
					}
					threadMessage("I am done!");
			} catch(InterruptedException e){
				threadMessage("I am not done!");
			}
		}
	}
	
	private static class QueueDeleter implements Runnable{
		
		static int count;
		ConQueue q;
		
		public QueueDeleter(ConQueue q){
			this.count = 0;
			this.q = q;
		}
		
		public void run(){
			
			try{
				for(int i=0;i<2;++i){
					Thread.sleep(1000);
					threadMessage("before del: "+Arrays.toString(q.queue));
					threadMessage("dequeue: "+q.deQueue());	
				}
			} catch(InterruptedException e){
				threadMessage("I am not done!");
			}
		}
	}
	
	private static class QueueReader extends Thread{
		ConQueue q;
		public QueueReader(ConQueue q, String name){
			super(name);
			this.q = q;
		}
		
		public void run(){
			try{
				while(true){
					Thread.sleep(1000);
					threadMessage("read: "+Arrays.toString(q.queue));
				}
			}catch(InterruptedException e){
				threadMessage("I am not done!");
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		long patient = 1000 * 100;
		threadMessage("Starting write/read thread");
		long startTime = System.currentTimeMillis();
		ConQueue q = new ConQueue(10);
		Thread t1 = new Thread(new QueueWriter(q),"t1");
		Thread t2 = new Thread(new QueueWriter(q),"t2");
		Thread d1 = new Thread(new QueueDeleter(q),"d1");
		Thread d2 = new Thread(new QueueDeleter(q),"d2");
		Thread r1 = new QueueReader(q,"r1");
		
		r1.start();
		t1.start();
		d1.start();
		d2.start();
		t2.start();
		threadMessage("Waiting for MessageLoop thread to finish");
		
		while(t1.isAlive() || t2.isAlive() || d1.isAlive() || d2.isAlive()){
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
			if(System.currentTimeMillis() - startTime > patient && d1.isAlive()){
				d1.interrupt();
				d1.join();
			}
			if(System.currentTimeMillis() - startTime > patient && d2.isAlive()){
				d2.interrupt();
				d2.join();
			}
		}
		
		if(r1.isAlive()){
			r1.interrupt();
			r1.join();
		}
		threadMessage("Finally!");
	}
}
