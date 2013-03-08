package org.xdb.test.tpch;

import org.xdb.test.tpch.tracker.TestTPCHQ3;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteTPCH extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteTPCH.class.getPackage().getName() );
    suite.addTestSuite( TestTPCH.class );
    suite.addTestSuite( TestTPCHQ3.class );
    return suite;
  }
}