package coding;

import java.util.ArrayList;
import java.util.Arrays;

public class BitApp {

	
//	find the only appearing odd number's element in an array	
	public int findodd(int array[]){
		int result=array[0];
		for(int i=1;i<array.length;++i){
			result = array[i]^result;
		}
		return result;
	}
	
//	 1GB memory	to find number missing in 4billion ints
	public void checkMissNum(int[] nums){
		int size = 1000000000/2;
		Byte[] barray = new Byte[size];
		for(int i=0;i<nums.length;++i){
			barray[nums.length/8] |= 1 << (nums.length%8);
		}
		for(int i=0;i<nums.length;++i){
			for(int j=0;j<8;++j){
				if((barray[i] & (1 << j)) == 0) 
					System.out.println(i*8+j);
			}
		}
	}
	
	/**
	 * 4KB memory to find duplicate number in N, N = 32000
	 * 4KB = 4000*8 bits -- international system unit base 10
	 * or > 32000bits --- base 2 MS
	 * 
	 * @param array
	 */
	public void findDup(int[] array){
		Byte[] barray = new Byte[4000];
		Arrays.fill(barray, 0);
		for(int i=0;i<array.length;++i){
			int slot = array[i]/8;
			int bits = 1<< (array[i] % 8);
			if((barray[slot] & bits) == 0)
				barray[slot] |= bits;
			else
				System.out.println(array[i]);
		}
	}
	
}
