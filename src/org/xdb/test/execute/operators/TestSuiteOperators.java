package org.xdb.test.execute.operators;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteOperators extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteOperators.class.getPackage().getName() );
    suite.addTestSuite( TestComputeOperator.class );
    return suite;
  }
}