package coding;

public class BitOps {

	public int copyBits(int n, int m, int i, int j){
		int tmp = ~0;
		int left = tmp<<j; //..1110000000
		int right = 1<<(i-1) - 1; // 0000000111..
		int mask = left|right; // ..1110000000111..
		return (n&mask) | (m<<i);
	}
	
	public boolean printNumBits(String n){
		int index = n.indexOf('.');
		int intpart = Integer.parseInt(n.substring(0, index));
		double decpart = Double.parseDouble(n.substring(index,n.length()));
		
		String intbits = "";
		while(intpart != 0){
			intbits += intpart%2; // intbits += intpart & 1
			intpart >>= 1;
		}
		
		String decbits = "";
		while(decpart > 0){
			if(decbits.length() > 32) return false;
			if(decpart == 1){
				decbits = 1+ decbits;
				break;
			}
			if(decpart*2 >= 1){
				decbits = 1 + decbits;
				decpart = decpart*2 - 1;
			}else{
				decbits = 0 + decbits;
				decpart = decpart*2;
			}
		}
		System.out.println(intbits+"."+decbits);
		return true;
	}
	
// find the largest below number and smallest up number with same num 1s
	public boolean getBit(int n, int index){
		return ((n&(1<<index))>0);
	}
	public int setBit(int n, int index, boolean value){
		if(value){
			return n | (1<<index);
		} else{
			int mask = ~(1 << index);
			return n & mask;
		}
	}
	
	public int find_smallest(int m){
	// find first 1
		if(m<=0) return -1;
		int index = 0;
		int count = 0;
		while(!getBit(m,index)) index++;
		while(getBit(m,index)) {
			index++;
			count++;
		}
		
		m = setBit(m,index,true);
		index--;
		setBit(m,index,false);
		
		count--;
		for(int i=index-1; i>=count;--i){
			m = setBit(m,i,false);
		}
		for(int i=count-1;i>=0;--i){
			m = setBit(m,i,true);
		}
		return m;
	}
	
	public int find_largest(int m){
		if(m<=0) return -1;
		int index = 0;
		int count = 0;
		// find first zero
		while(getBit(m,index)) index++;
		//turn off next 1
		while(!getBit(m,index)){
			index++;
			count++;
		}
		m = setBit(m,index,false);
		//turn on previous 0
		index--;
		setBit(m,index,true);
		// set ones one by one right after the up one just set
		count--;
		for(int i=index-1;i>=count;--i)
			m = setBit(m,i,true);
		// set zero one by one right at the lowest bit
		for(int i=count-1;i>=0;--i)
			m = setBit(m,i,false);
		return m;
	}
}
