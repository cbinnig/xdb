package org.xdb.test.funsql.statement;

import junit.framework.Assert;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.funsql.statement.LoadDataInfileStmt;
import org.xdb.test.XDBTestCase;


public class TestLoadDataInfileStmt extends XDBTestCase {
	
	@Test
	public void testSimpleLoadDataInfile() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		compiler.doOptimize(false);
		
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
		String dropTableSql = "DROP TABLE \"R\"";
		stmt = compiler.compile(dropTableSql);
		if (stmt != null)
			this.execute(stmt);
		
		String createTableStmt = "CREATE TABLE \"R\"( " + "  Aaa INT,"
				+ "  Bbb VARCHAR," + "  Ccc INT"
				+ ") IN CONNECTION \"testConnection\"";

		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);

		//execute load data infile
		LoadDataInfileStmt loadDataInfileStmt = (LoadDataInfileStmt) compiler
					.compile("LOAD DATA INFILE \"sql/test.tbl\" INTO TABLE R");

		System.out.println(compiler.getLastError());
		this.assertNoError(compiler.getLastError());
		
		loadDataInfileStmt.execute();
		this.assertNoError(compiler.getLastError());
		
	}

}
