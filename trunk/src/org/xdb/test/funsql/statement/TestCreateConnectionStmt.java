package org.xdb.test.funsql.statement;

import org.junit.Test;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.funsql.statement.CreateConnectionStmt;
import org.xdb.funsql.statement.DropConnectionStmt;
import org.xdb.test.XDBTestCase;

public class TestCreateConnectionStmt extends XDBTestCase {
	@Test
	public void testDuplicateCreate() {
		String connectionName = "TestConnection";
		
		//drop connection 
		AbstractServerStmt stmt = new DropConnectionStmt(connectionName);
		this.compileAndExecuteWithErr(stmt);		
				
		//create connection 
		stmt = new CreateConnectionStmt(connectionName, "TestUrl", "TestUser", "TestPasswd", "POSTGRES");
		this.compileAndExecute(stmt);
		
		//create duplicate connection 
		stmt = new CreateConnectionStmt(connectionName, "TestUrl", "TestUser", "TestPasswd", "POSTGRES");
		this.compileAndExecuteWithErr(stmt);
		
		//drop connection
		stmt = new DropConnectionStmt(connectionName);
		this.compileAndExecute(stmt);
	}
}
