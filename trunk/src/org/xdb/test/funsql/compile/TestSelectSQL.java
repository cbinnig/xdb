package org.xdb.test.funsql.compile;

import junit.framework.Assert;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.funsql.statement.SelectStmt;
import org.xdb.test.CompileServerTestCase;

public class TestSelectSQL extends CompileServerTestCase {
	@Test
	public void testSimpleSelect() {
		FunSQLCompiler compiler = new FunSQLCompiler();

		// create connection -> no error
		String dropConnSql = "DROP CONNECTION \"testConnection\"";
		AbstractServerStmt stmt = compiler.compile(dropConnSql);
		if (stmt != null)
			this.execute(stmt);

		String createConnSql = "CREATE CONNECTION \"testConnection\" "
				+ "URL 'jdbc:mysql://127.0.0.1/xdb_tmp' " + "USER 'xroot' "
				+ "PASSWORD 'xroot' " + "STORE 'XDB' ";

		stmt = compiler.compile(createConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);

		//create table
		String dropTableSql = "DROP CONNECTION \"R\"";
		stmt = compiler.compile(dropTableSql);
		if (stmt != null)
			this.execute(stmt);
		
		String createTableStmt = "CREATE TABLE \"R\"( " + "  A INT,"
				+ "  B VARCHAR," + "  C INT"
				+ ") IN CONNECTION \"testConnection\"";

		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);

		//execute select
		SelectStmt selectStmt = (SelectStmt) compiler
				.compile("SELECT R1.A+1 AS A1 FROM R AS R1 WHERE R1.C=1");
		this.assertNoError(compiler.getLastError());
		selectStmt.getPlan().traceGraph(this.getClass().getName());
		
		selectStmt = (SelectStmt) compiler
				.compile("SELECT R1.A AS A1, R2.A AS A2 "
						+ "FROM R AS R1, R AS R2 "
						+ "WHERE R1.B=R2.B AND R1.C=1");
		this.assertNoError(compiler.getLastError());
		selectStmt.getPlan().traceGraph(this.getClass().getName());
		
		selectStmt = (SelectStmt) compiler
				.compile("SELECT R1.A AS A1, R2.A AS A2 "
						+ "FROM R AS R1, R AS R2, R AS R3 "
						+ "WHERE R1.B=R2.B AND R2.B=R3.B AND R1.C=1");
		this.assertNoError(compiler.getLastError());
		selectStmt.getPlan().traceGraph(this.getClass().getName());
		
		
		selectStmt = (SelectStmt) compiler
				.compile("SELECT SUM(A) AS S, B "
						+ "FROM R "
						+ "GROUP BY B "
						+ "HAVING AVG(A)>=1");
		this.assertNoError(compiler.getLastError());
		selectStmt.getPlan().traceGraph(this.getClass().getName());
	}

	public void noTestCompileSelect() {
		FunSQLCompiler compiler = new FunSQLCompiler();

		compiler.compile("SELECT A+1 AS A FROM R");
		this.assertNoError(compiler.getLastError());

		SelectStmt stmt = (SelectStmt) compiler
				.compile("SELECT A FROM R WHERE B=1");
		System.out.println(stmt);
		this.assertNoError(compiler.getLastError());

		stmt = (SelectStmt) compiler.compile("SELECT A FROM R WHERE B=C");
		System.out.println(stmt);
		this.assertNoError(compiler.getLastError());

		stmt = (SelectStmt) compiler
				.compile("SELECT A FROM R WHERE B=C OR D=E");
		System.out.println(stmt);
		this.assertNoError(compiler.getLastError());

		stmt = (SelectStmt) compiler
				.compile("SELECT (A+1*C)*B FROM R WHERE B=C AND D=E");
		System.out.println(stmt);
		this.assertNoError(compiler.getLastError());

		stmt = (SelectStmt) compiler
				.compile("SELECT A, B AS Y FROM R, S AS T WHERE B=C OR D=E AND F=G");
		System.out.println(stmt);
		this.assertNoError(compiler.getLastError());

		stmt = (SelectStmt) compiler
				.compile("SELECT A FROM R WHERE ( B=C OR D=E ) AND F=G");
		System.out.println(stmt);
		this.assertNoError(compiler.getLastError());

		stmt = (SelectStmt) compiler
				.compile("SELECT A FROM R WHERE (B+A*C*D)=1");
		System.out.println(stmt);
		this.assertNoError(compiler.getLastError());

	}
}
