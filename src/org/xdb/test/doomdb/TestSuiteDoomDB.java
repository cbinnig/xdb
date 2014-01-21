package org.xdb.test.doomdb;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteDoomDB extends TestSuite
{
  public static Test suite()
  {
    TestSuite suite = new TestSuite( TestSuiteDoomDB.class.getPackage().getName() );
    suite.addTestSuite( TestDoomDB.class );
    return suite;
  }
}