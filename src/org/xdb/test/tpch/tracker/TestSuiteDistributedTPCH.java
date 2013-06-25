package org.xdb.test.tpch.tracker;

import org.xdb.test.tpch.tracker.TestTPCHQ3;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteDistributedTPCH extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteDistributedTPCH.class.getPackage().getName() );
    suite.addTestSuite( TestTPCHQ1.class );
    suite.addTestSuite( TestTPCHQ2.class );
    suite.addTestSuite( TestTPCHQ3.class );
    suite.addTestSuite( TestTPCHQ4.class );
    suite.addTestSuite( TestTPCHQ5.class );
    suite.addTestSuite( TestTPCHQ6.class );
    suite.addTestSuite( TestTPCHQ7.class );
    suite.addTestSuite( TestTPCHQ8.class );
    suite.addTestSuite( TestTPCHQ9.class );
    suite.addTestSuite( TestTPCHQ10.class );
    suite.addTestSuite( TestTPCHQ11.class );
    suite.addTestSuite( TestTPCHQ12.class );
    //suite.addTestSuite( TestTPCHQ13.class );
    suite.addTestSuite( TestTPCHQ14.class );
    //suite.addTestSuite( TestTPCHQ15.class );
    suite.addTestSuite( TestTPCHQ16.class );
    suite.addTestSuite( TestTPCHQ17.class );
    //suite.addTestSuite( TestTPCHQ18.class );
    suite.addTestSuite( TestTPCHQ19.class );
    suite.addTestSuite( TestTPCHQ20.class );
    //suite.addTestSuite( TestTPCHQ21.class );
    //suite.addTestSuite( TestTPCHQ22.class );
    //suite.addTestSuite( TestTPCHBasketAnalysis.class );
    return suite;
  }
}