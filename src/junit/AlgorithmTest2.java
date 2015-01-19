package junit;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import struct.StringOp;

public class AlgorithmTest2 extends TestCase{
	public void testLongestNonDupSubstr(){
		String str = "abadabcdeffghjua";
		String lg = "abcdef";
		StringOp so = new StringOp();
		assertEquals(lg,so.longestNonDupSubstr(str));
	}
}
