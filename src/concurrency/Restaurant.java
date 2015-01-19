package concurrency;

public interface Restaurant{
	public void registHost(Host host);
	public void serveGuest();
	public void emptyPlate();
}

public class MyRestaurant implements Restaurant{
	private static int waitNum;
	private static int spotNum;
	final static int MAX;
	private static Host host;
	
	public MyRestaurant(int max, int waitNum, int spotNum){
		MAX = max;
		this.waitNum = 0;
		this.spotNum = spotNum;
	}
	
	public void addHost(Host host){
		this.host = host;
	}
	
	public synchronized void serveGuest(){
		if(spotNum>0){
			spotNum--;
		}else{
			if(waitNum <= MAX)
				waitNum++;
			else
				host.notice();
		}
	}
	
	public synchronized void emptyPlate(){
		spotNum++;
		if(waitNum != 0) waitNum--;
		host.notice();
	}
}

interface Host{
//	public abstract void register(Restaurant rs);
	public abstract void notice();
}

class ResHost implements Host{
/*	public void register(Restaurant rs){
		rs.registHost(this);
	} */
	public void notice(){	
	}
}

