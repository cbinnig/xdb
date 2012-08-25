package org.xdb.test.funsql.statement;

import org.junit.Test;
import org.xdb.funsql.statement.AbstractStatement;
import org.xdb.funsql.statement.CreateSchemaStmt;
import org.xdb.funsql.statement.DropSchemaStmt;
import org.xdb.test.MetadataServerTestCase;

public class TestCreateSchemaStmt extends MetadataServerTestCase {
	@Test
	public void testDuplicateCreate() {
		String schemaName = "TestSchema";
		
		//create schema 
		AbstractStatement stmt = new CreateSchemaStmt(schemaName);
		this.compileAndExecute(stmt);
		
		//create duplicate schema 
		stmt = new CreateSchemaStmt(schemaName);
		this.compileAndExecuteWithErr(stmt);
		
		//drop schema
		stmt = new DropSchemaStmt(schemaName);
		this.compileAndExecute(stmt);
	}
}
