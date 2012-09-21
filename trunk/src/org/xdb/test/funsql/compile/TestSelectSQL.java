package org.xdb.test.funsql.compile;

import org.junit.Test;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.statement.SelectStmt;
import org.xdb.test.CompileServerTestCase;

public class TestSelectSQL extends CompileServerTestCase {
	@Test
	public void testCompileSelect() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		
		compiler.compile("SELECT A FROM R");
		this.assertNoError(compiler.getLastError());
		
		SelectStmt stmt = (SelectStmt)compiler.compile("SELECT A FROM R WHERE B=1");
		System.out.println(stmt.getPredicate());
		this.assertNoError(compiler.getLastError());
		
		stmt = (SelectStmt)compiler.compile("SELECT A FROM R WHERE B=C");
		System.out.println(stmt.getPredicate());
		this.assertNoError(compiler.getLastError());
		
		stmt = (SelectStmt)compiler.compile("SELECT A FROM R WHERE B=C OR D=E");
		System.out.println(stmt.getPredicate());
		this.assertNoError(compiler.getLastError());
		
		stmt = (SelectStmt)compiler.compile("SELECT A FROM R WHERE B=C AND D=E");
		System.out.println(stmt.getPredicate());
		this.assertNoError(compiler.getLastError());
		
		stmt = (SelectStmt)compiler.compile("SELECT A FROM R WHERE B=C OR D=E AND F=G");
		System.out.println(stmt.getPredicate());
		this.assertNoError(compiler.getLastError());
	}
}
