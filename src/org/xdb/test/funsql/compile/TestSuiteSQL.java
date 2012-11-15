package org.xdb.test.funsql.compile;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteSQL extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( "org.xdb.test.funsql.compile" );
    suite.addTestSuite( TestCreateConnectionSQL.class );
    suite.addTestSuite( TestCreateSchemaSQL.class );
    suite.addTestSuite( TestCreateTableSQL.class );
    suite.addTestSuite( TestSelectSQL.class );
    suite.addTestSuite( TestOptimizeSQL.class );
    suite.addTestSuite( TestOptimizeLargeSQL.class );
    suite.addTestSuite( TestCreateFunctionSQL.class);
    return suite;
  }
}