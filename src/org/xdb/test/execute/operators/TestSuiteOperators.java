package org.xdb.test.execute.operators;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteOperators extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( "org.xdb.test.execute.operators" );
    suite.addTestSuite( TestComputeOperator.class );
    return suite;
  }
}