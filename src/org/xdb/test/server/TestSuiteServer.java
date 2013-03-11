package org.xdb.test.server;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteServer extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteServer.class.getPackage().getName() );
    suite.addTestSuite( TestCompileServer.class );
    return suite;
  }
}