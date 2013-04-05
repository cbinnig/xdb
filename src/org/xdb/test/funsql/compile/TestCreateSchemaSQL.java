package org.xdb.test.funsql.compile;

import junit.framework.Assert;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.test.XDBTestCase;

public class TestCreateSchemaSQL extends XDBTestCase {
	@Test
	public void testSimpleCreate() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		
		//create schema -> no error
		String createSchemaSql = "CREATE SCHEMA testSchema";
		AbstractServerStmt stmt = compiler.compile(createSchemaSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		//create same schema -> error
		stmt = compiler.compile(createSchemaSql);
		this.assertError(compiler.getLastError());
		
		//drop schema -> no error
		String dropSchemaSql = "DROP SCHEMA testSchema";
		stmt = compiler.compile(dropSchemaSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
	}
}
