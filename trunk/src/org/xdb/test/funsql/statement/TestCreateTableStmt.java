package org.xdb.test.funsql.statement;

import org.junit.Test;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.funsql.statement.CreateConnectionStmt;
import org.xdb.funsql.statement.CreateTableStmt;
import org.xdb.funsql.statement.DropConnectionStmt;
import org.xdb.funsql.statement.DropTableStmt;
import org.xdb.metadata.Catalog;
import org.xdb.test.CompileServerTestCase;

public class TestCreateTableStmt extends CompileServerTestCase {

	@Test
	public void testSimpleCreate() {
		// create connection and table
		AbstractServerStmt stmt = new CreateConnectionStmt("TestConnection",
				"TestUrl", "TestUser", "TestPasswd", "POSTGRES");
		this.compileAndExecute(stmt);

		CreateTableStmt createTableStmt = new CreateTableStmt("TestTable",
				"SourceSchema", "SourceTable");
		createTableStmt.addAttribute("A");
		createTableStmt.addType("VARCHAR");
		createTableStmt.addConnection("TestConnection");
		this.compileAndExecute(createTableStmt);

		// reload catalog
		assertNoError(Catalog.unload());
		assertNoError(Catalog.load());

		// drop connection and table
		stmt = new DropTableStmt("TestTable");
		this.compileAndExecute(stmt);

		stmt = new DropConnectionStmt("TestConnection");
		this.compileAndExecute(stmt);
	}

	@Test
	public void testReplicatedCreate() {
		AbstractServerStmt stmt = new CreateConnectionStmt("TestConnection1",
				"TestUrl", "TestUser", "TestPasswd", "POSTGRES");
		this.compileAndExecute(stmt);

		AbstractServerStmt stmt2 = new CreateConnectionStmt("TestConnection2",
				"TestUrl", "TestUser", "TestPasswd", "POSTGRES");
		this.compileAndExecute(stmt2);

		CreateTableStmt createTableStmt = new CreateTableStmt("TestTable",
				"SourceSchema", "SourceTable");
		createTableStmt.addAttribute("A");
		createTableStmt.addType("VARCHAR");
		createTableStmt.addConnection("TestConnection1");
		createTableStmt.addConnection("TestConnection2");
		this.compileAndExecute(createTableStmt);

		// reload catalog
		assertNoError(Catalog.unload());
		assertNoError(Catalog.load());

		// drop connection and table
		stmt = new DropTableStmt("TestTable");
		this.compileAndExecute(stmt);

		stmt = new DropConnectionStmt("TestConnection1");
		this.compileAndExecute(stmt);
		stmt = new DropConnectionStmt("TestConnection2");
		this.compileAndExecute(stmt);
	}

}
