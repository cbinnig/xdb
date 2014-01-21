package org.xdb.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.xdb.test.doomdb.TestSuiteDoomDB;
import org.xdb.test.execute.operators.TestSuiteOperators;
import org.xdb.test.funsql.compile.TestSuiteSQL;
import org.xdb.test.funsql.statement.TestSuiteStmt;
import org.xdb.test.server.TestSuiteServer;
import org.xdb.test.tpch.TestSuiteTPCH;
import org.xdb.test.tpch.tracker.TestSuiteDistributedTPCH;
import org.xdb.test.tracker.TestSuiteTracker;
import org.xdb.test.system.TestSuiteSystem;

public class TestSuiteXDB extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite(TestSuiteXDB.class.getPackage()
				.getName());
		suite.addTest(TestSuiteOperators.suite());
		suite.addTest(TestSuiteTracker.suite());
		suite.addTest(TestSuiteStmt.suite());
		suite.addTest(TestSuiteSQL.suite());
		suite.addTest(TestSuiteServer.suite());
		suite.addTest(TestSuiteSystem.suite());
		suite.addTest(TestSuiteTPCH.suite());
		suite.addTest(TestSuiteDistributedTPCH.suite());
		suite.addTest(TestSuiteDoomDB.suite());
		return suite;
	}
}