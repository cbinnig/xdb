package org.xdb.test.funsql.compile;

import junit.framework.Assert;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.AbstractStatement;
import org.xdb.test.CompileServerTestCase;

public class TestCreateConnectionSQL extends CompileServerTestCase {
	@Test
	public void testSimpleCreate() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		
		//create connection -> no error
		String createConnSql = 
				"CREATE CONNECTION \"testConnection\" " +
				"URL 'jdbc:postgresql://127.0.0.1/xdb' " +
				"USER 'xroot' " +
				"PASSWORD 'xroot' " +
				"STORE 'POSTGRES' ";
		
		AbstractStatement stmt = compiler.compile(createConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);
		
		String dropConnSql = "DROP CONNECTION testConnection";
		stmt = compiler.compile(dropConnSql);
		this.assertError(compiler.getLastError());
		
		dropConnSql = "DROP CONNECTION \"testConnection\"";
		stmt = compiler.compile(dropConnSql);
		this.assertNoError(compiler.getLastError());
		Assert.assertNotNull(stmt);
		this.execute(stmt);

	}
}
