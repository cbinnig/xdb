package org.xdb.test.costmodel;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteCostModel extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteCostModel.class.getPackage().getName() );
    suite.addTestSuite( CostModelTest.class );
    return suite;
  }
}