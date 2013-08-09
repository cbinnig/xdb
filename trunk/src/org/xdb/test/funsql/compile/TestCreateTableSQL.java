package org.xdb.test.funsql.compile;

import junit.framework.Assert;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.metadata.EnumPartitionType;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Table;
import org.xdb.test.XDBTestCase;

public class TestCreateTableSQL extends XDBTestCase {
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
				"CREATE TABLE \"testOrder\"( "+
				"  ORDERKEY INT," +
				"  CUSTKEY VARCHAR " +
				") IN CONNECTION \"testConnection\"";
		
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"testOrder\"";
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
				") PARTIONED BY HASH (A) ( " +
				" \"P1\" IN CONNECTION  \"testConnection1\"," +
				" \"P2\" IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		Table gottable = Catalog.getTable("1.testTable");
		
		Assert.assertTrue(gottable.getPartitions().size()!=0);
		
		Assert.assertEquals(gottable.getPartitionType(), EnumPartitionType.HASH);
		
		String dropConnSql = "DROP TABLE \"testTable\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
	}
	
	
	@Test
	public void testSimpleCreatePartionedTableHash() {
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
				"CREATE TABLE \"testTable1\"( "+
				"  T1_C1 INT," +
				"  T1_C2 VARCHAR," +
				"  T1_C3 INT" +
				") PARTIONED BY HASH ( T1_C1, T1_C2 ) ( " +
				" \"P1\" IN CONNECTION  \"testConnection1\"," +
				" \"P2\" IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		Table gottable = Catalog.getTable("1.testTable1");
		
		Assert.assertTrue(gottable.getPartitions().size() == 2);
		
		Assert.assertEquals(gottable.getPartitionType(), EnumPartitionType.HASH);
		
		//Assert.assertEquals(gottable.getListofPartDetails().size(), 2);

		
		String dropConnSql = "DROP TABLE \"testTable1\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
	}
	
	@Test
	public void testSimpleCreateCorruptPartionedTableHash() {
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
				") PARTIONED BY HASH ( A,B,C ) ( " +
				" \"P1\" IN CONNECTION  \"testConnection1\"," +
				" \"P2\" IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertError(compiler.getLastError());
		
	
		/*this.execute(stmt);
		
		Table gottable = Catalog.getTable("1.testTable");
		
		Assert.assertTrue(gottable.getPartitions().size()!=0);
		
		Assert.assertEquals(gottable.getPartitionType(), "HASH");
		
		Assert.assertEquals(gottable.getListofPartDetails().size(), 2);

		
		String dropConnSql = "DROP TABLE \"testTable\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);*/
	}
	
	@Test
	public void testSimpleCreatePartionedTableReference() {
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
				"CREATE TABLE \"T1\"( "+
				"  C1 INT," +
				"  C2 VARCHAR," +
				"  C3 INT" +
				") PARTIONED BY HASH ( C1, C2 ) ( " +
				" \"P1\" IN CONNECTION  \"testConnection1\"," +
				" \"P2\" IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		
		
		
		createTableStmt = 
				"CREATE TABLE \"T2\"( "+
				"  T2_C1 INT," +
				"  T2_C2 VARCHAR," +
				"  T2_C3 INT" +
				") PARTIONED BY REVERSE_REFERENCE ( T2_C1___T1__C1, T2_C2___T1__C2 ) ( " +
				" \"P1\" IN CONNECTION  \"testConnection1\"," +
				" \"P2\" IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"T2\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		dropConnSql = "DROP TABLE \"T1\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
	}
	
	
	@Test
	public void testCreateReplicatedPartionedTable() {
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
				") PARTIONED BY HASH ( A ) ( " +
				" \"P1\" REPLICATED IN CONNECTION  \"testConnection1\", \"testConnection2\", "+
				" \"P2\" REPLICATED IN CONNECTION  \"testConnection1\", \"testConnection2\" )";
		
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
