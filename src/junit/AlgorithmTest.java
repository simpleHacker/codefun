package junit;

import junit.framework.TestCase;
import struct.ArrayOp;

public class AlgorithmTest extends TestCase {

	int[] arr;
	ArrayOp ap;
	
	public void setUp(){
		int[] ar = {11,4,7,2,8,19,21,1,10,5,15};
		arr = ar;
		ap = new ArrayOp();
		System.out.println("initiate!");
	}
	
	public void testFindMaxDiff(){
		System.out.println("test case name: "+this.getName()); 
		assertEquals(19, ap.findMaxDiff(arr));
	}
	
	public void testFindMaxGap(){
		System.out.println("test case name: "+this.getName()); 
		assertEquals(arr.length-2,ap.findMaxGap(arr));
	}
	
}
