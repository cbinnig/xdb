package org.xdb.test.funsql.statement;

import org.junit.Test;
import org.xdb.funsql.statement.AbstractStatement;
import org.xdb.funsql.statement.CreateConnectionStmt;
import org.xdb.funsql.statement.DropConnectionStmt;
import org.xdb.test.CompileServerTestCase;

public class TestCreateConnectionStmt extends CompileServerTestCase {
	@Test
	public void testDuplicateCreate() {
		String connectionName = "TestConnection";
		
		//create connection 
		AbstractStatement stmt = new CreateConnectionStmt(connectionName, "TestUrl", "TestUser", "TestPasswd", "POSTGRES");
		this.compileAndExecute(stmt);
		
		//create duplicate connection 
		stmt = new CreateConnectionStmt(connectionName, "TestUrl", "TestUser", "TestPasswd", "POSTGRES");
		this.compileAndExecuteWithErr(stmt);
		
		//drop connection
		stmt = new DropConnectionStmt(connectionName);
		this.compileAndExecute(stmt);
	}
}
