package junit;

import junit.framework.TestResult;
import junit.framework.TestSuite;

public class UnitTestSuite{
	public static void main(String[] args){
		TestSuite ts = new TestSuite();
		ts.addTestSuite(AlgorithmTest.class);
		ts.addTestSuite(AlgorithmTest2.class);
		TestResult tr = new TestResult();
		ts.run(tr);
		
	    System.out.println(tr.failureCount());
	    System.out.println(tr.wasSuccessful());
	}
}
