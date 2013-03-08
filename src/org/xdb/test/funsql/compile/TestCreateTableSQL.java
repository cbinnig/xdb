package org.xdb.test.funsql.compile;

import junit.framework.Assert;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.test.CompileServerTestCase;

public class TestCreateTableSQL extends CompileServerTestCase {
	@Test
	public void testSimpleCreate() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		
		//create connection -> no error
		String createConnSql = 
				"CREATE CONNECTION \"testConnection\" " +
				"URL 'jdbc:mysql://127.0.0.1/xdb_tmp' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'XDB' ";
		
		AbstractServerStmt stmt = compiler.compile(createConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		String createTableStmt = 
				"CREATE TABLE \"testTable\"( "+
				"  A INT," +
				"  B VARCHAR " +
				") IN CONNECTION \"testConnection\"";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"testTable\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
	}
	
	@Test
	public void testSimpleCreatePartionedTable() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		
		//create connection -> no error
		String createConnSql1 = 
				"CREATE CONNECTION \"testConnection1\" " +
				"URL 'jdbc:mysql://127.0.0.1/xdb_tmp' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'XDB' ";
		
		String createConnSql2 = 
				"CREATE CONNECTION \"testConnection2\" " +
				"URL 'jdbc:mysql://127.0.0.1/xdb_tmp' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'XDB' ";
		
		AbstractServerStmt stmt = compiler.compile(createConnSql1);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		
		AbstractServerStmt stmt2 = compiler.compile(createConnSql2);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt2);
		this.execute(stmt2);
		
		
		String createTableStmt = 
				"CREATE TABLE \"testTable\"( "+
				"  A INT," +
				"  B VARCHAR" +
				") PARTIONED BY RANDOM ( A ) ( " +
				" \"P1\" IN CONNECTION  \"testConnection1\"," +
				" \"P2\" IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"testTable\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
	}
	
	@Test
	public void testSimpleCreateReplicatedTable() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		
		//create connection -> no error
		String createConnSql1 = 
				"CREATE CONNECTION \"testConnection1\" " +
				"URL 'jdbc:mysql://127.0.0.1/xdb_tmp' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'XDB' ";
		
		String createConnSql2 = 
				"CREATE CONNECTION \"testConnection2\" " +
				"URL 'jdbc:mysql://127.0.0.1/xdb_tmp' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'XDB' ";
		
		AbstractServerStmt stmt = compiler.compile(createConnSql1);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		
		AbstractServerStmt stmt2 = compiler.compile(createConnSql2);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt2);
		this.execute(stmt2);
		
		
		String createTableStmt = 
				"CREATE TABLE \"testTable\"( "+
				"  A INT," +
				"  B VARCHAR" +
				") REPLICATED IN CONNECTION \"testConnection1\"," +
				"\"testConnection2\";" ;
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"testTable\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
	}
}
