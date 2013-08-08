package org.xdb.test.tracker;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteTracker extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteTracker.class.getPackage().getName() );
    suite.addTestSuite( TestQueryTracker.class );
    suite.addTestSuite( TestPlanTranslation.class );
    suite.addTestSuite( TestSQLPlanTranslation.class );
    suite.addTestSuite( TestDistributed2ComputeNodes.class );
    return suite;
  }
}