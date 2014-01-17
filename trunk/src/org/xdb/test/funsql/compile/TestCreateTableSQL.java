package org.xdb.test.funsql.compile;

import org.junit.Test;
import org.xdb.Config;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.EnumPartitionType;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;
import org.xdb.test.TestCase;
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
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		String createTableStmt = 
				"CREATE TABLE \"testOrder\"( "+
				"  ORDERKEY INT," +
				"  CUSTKEY VARCHAR " +
				") IN CONNECTION \"testConnection\"";
		
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"testOrder\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
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
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		
		AbstractServerStmt stmt2 = compiler.compile(createConnSql2);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt2);
		this.execute(stmt2);
		
		
		String createTableStmt = 
				"CREATE TABLE \"testTable1\"( "+
				"  T1_C1 INT," +
				"  T1_C2 VARCHAR," +
				"  T1_C3 INT" +
				") PARTIONED BY HASH ( T1_C1, T1_C2 ) ( " +
				" P0 IN CONNECTION  \"testConnection1\"," +
				" P1 IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		Schema defaultSchema = Catalog.getSchema(Config.COMPILE_DEFAULT_SCHEMA);
		TokenTable tTable = new TokenTable("testTable1");
		Table gottable = Catalog.getTable(tTable.hashKey(defaultSchema.getOid()));
		TestCase.assertTrue(gottable.getPartitions().size() == 2);
		TestCase.assertEquals(gottable.getPartitionType(), EnumPartitionType.HASH);
		
		String dropConnSql = "DROP TABLE \"testTable1\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
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
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		
		AbstractServerStmt stmt2 = compiler.compile(createConnSql2);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt2);
		this.execute(stmt2);
		
		
		String createTableStmt = 
				"CREATE TABLE \"testTable\"( "+
				"  A INT," +
				"  B VARCHAR" +
				") PARTIONED BY HASH ( A,B,C ) ( " +
				"P1 IN CONNECTION  \"testConnection1\"," +
				"P2 IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertError(compiler.getLastError());
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
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		AbstractServerStmt stmt2 = compiler.compile(createConnSql2);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt2);
		this.execute(stmt2);
		
		//create tables
		String createTableStmt = 
				"CREATE TABLE \"T1\"( "+
				"  C1 INT," +
				"  C2 VARCHAR," +
				"  C3 INT" +
				") PARTIONED BY HASH ( C1, C2 ) ( " +
				"P0 IN CONNECTION  \"testConnection1\"," +
				"P1 IN CONNECTION  \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		createTableStmt = 
				"CREATE TABLE \"T2\"( "+
				"  T2_C1 INT," +
				"  T2_C2 VARCHAR," +
				"  T2_C3 INT" +
				") PARTIONED BY REF ( T2_C1 REFERENCES T1.C1, T2_C2 REFERENCES T1.C2 )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
			
		Schema defaultSchema = Catalog.getSchema(Config.COMPILE_DEFAULT_SCHEMA);
		TokenTable tTable = new TokenTable("T2");
		Table gotTable = Catalog.getTable(tTable.hashKey(defaultSchema.getOid()));
		TestCase.assertTrue(gotTable.getPartitions().size() == 2);
		TestCase.assertEquals(gotTable.getPartitionType(), EnumPartitionType.REF);
		
		createTableStmt = 
				"CREATE TABLE \"T3\"( "+
				"  T3_C1 INT," +
				"  T3_C2 VARCHAR," +
				"  T3_C3 INT" +
				") PARTIONED BY RREF ( T3_C1 REFERENCES T2.T2_C1, T3_C2 REFERENCES T2.T2_C2 )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		tTable = new TokenTable("T3");
		gotTable = Catalog.getTable(tTable.hashKey(defaultSchema.getOid()));
		TestCase.assertTrue(gotTable.getPartitions().size() == 2);
		TestCase.assertEquals(gotTable.getPartitionType(), EnumPartitionType.RREF);
		
		String dropConnSql = "DROP TABLE \"T1\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		dropConnSql = "DROP TABLE \"T2\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		dropConnSql = "DROP TABLE \"T3\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
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
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		
		AbstractServerStmt stmt2 = compiler.compile(createConnSql2);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt2);
		this.execute(stmt2);
		
		
		String createTableStmt = 
				"CREATE TABLE \"testTable\"( "+
				"  A INT," +
				"  B VARCHAR" +
				") REPLICATED IN CONNECTION \"testConnection1\"," +
				"\"testConnection2\";" ;
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"testTable\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
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
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		
		AbstractServerStmt stmt2 = compiler.compile(createConnSql2);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt2);
		this.execute(stmt2);
		
		
		String createTableStmt = 
				"CREATE TABLE \"testTable\"( "+
				"  A INT," +
				"  B VARCHAR" +
				") PARTIONED BY HASH ( A ) ( " +
				"P0 REPLICATED IN CONNECTION  \"testConnection1\", \"testConnection2\", "+
				"P1 REPLICATED IN CONNECTION  \"testConnection1\", \"testConnection2\" )";
		
		stmt = compiler.compile(createTableStmt);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP TABLE \"testTable\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);
	}
}
