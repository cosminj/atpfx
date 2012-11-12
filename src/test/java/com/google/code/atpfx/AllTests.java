package com.google.code.atpfx;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.google.code.atpfx");
		//$JUnit-BEGIN$
		//suite.addTestSuite(MyBasicTests.class);
		suite.addTestSuite(InternalDataFormatTests.class);
		//$JUnit-END$
		return suite;
	}

}
