package org.xdb.test.tracker;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteTracker extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( "org.xdb.test.execute.tracker" );
    suite.addTestSuite( TestQueryTracker.class );
    return suite;
  }
}