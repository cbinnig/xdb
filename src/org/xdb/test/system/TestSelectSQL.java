package org.xdb.test.system;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.funsql.statement.SelectStmt;
import org.xdb.test.TestCase;
import org.xdb.test.XDBTestCase;

public class TestSelectSQL extends XDBTestCase {
	@Test
	public void testSimpleSelect() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		compiler.doOptimize(false);
		
		// create connection -> no error
		String dropConnSql = "DROP CONNECTION \"testConnection\"";
		AbstractServerStmt stmt = compiler.compile(dropConnSql);
		if (stmt != null)
			this.execute(stmt);

		String createConnSql = "CREATE CONNECTION \"testConnection\" "
				+ "URL 'jdbc:mysql://127.0.0.1/xdb_test' " + "USER 'xroot' "
				+ "PASSWORD 'xroot' " + "STORE 'XDB' ";

		stmt = compiler.compile(createConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);

		//create table
		String dropTableSql = "DROP TABLE \"R\"";
		stmt = compiler.compile(dropTableSql);
		if (stmt != null)
			this.execute(stmt);
		
		String createTableStmt = "CREATE TABLE \"R\"( " + "  A INT,"
				+ "  B VARCHAR," + "  C INT"
				+ ") IN CONNECTION \"testConnection\"";

		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);

		//execute select
		SelectStmt selectStmt = (SelectStmt) compiler
				.compile("SELECT R1.A+1 AS A1 FROM R AS R1 WHERE R1.C=1");
		this.assertNoError(compiler.getLastError());
		this.assertNoError(selectStmt.execute());
	}
}
