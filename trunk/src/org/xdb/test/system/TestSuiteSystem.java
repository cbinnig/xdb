package org.xdb.test.system;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSystem extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteSystem.class.getPackage().getName() );
    suite.addTestSuite( TestCallFunctionSQL.class );
    suite.addTestSuite( TestSelectSQL.class );
    return suite;
  }
}