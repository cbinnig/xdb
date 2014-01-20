package org.xdb.test.tpch;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteTPCH extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteTPCH.class.getPackage().getName() );
    suite.addTestSuite( TestTPCH.class );
    suite.addTestSuite( TestTPCHParallel.class );
    return suite;
  }
}