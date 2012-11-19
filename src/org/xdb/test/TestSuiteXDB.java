package org.xdb.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.xdb.test.execute.operators.TestSuiteOperators;
import org.xdb.test.funsql.compile.TestSuiteSQL;
import org.xdb.test.funsql.statement.TestSuiteStmt;
import org.xdb.test.server.TestSuiteServer;
import org.xdb.test.tracker.TestSuiteTracker;

public class TestSuiteXDB extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteXDB.class.getPackage().getName() );
    suite.addTest(TestSuiteOperators.suite());
    suite.addTest(TestSuiteTracker.suite());
    suite.addTest(TestSuiteStmt.suite());
    suite.addTest(TestSuiteSQL.suite());
    suite.addTest(TestSuiteServer.suite());
    return suite;
  }
}