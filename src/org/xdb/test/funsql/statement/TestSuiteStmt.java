package org.xdb.test.funsql.statement;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteStmt extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteStmt.class.getPackage().getName() );
    suite.addTestSuite( TestCreateConnectionStmt.class );
    suite.addTestSuite( TestCreateSchemaStmt.class );
    suite.addTestSuite( TestCreateTableStmt.class );
    return suite;
  }
}