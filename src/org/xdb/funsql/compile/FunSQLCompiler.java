package org.xdb.funsql.compile;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.antlr.FunSQLLexer;
import org.xdb.funsql.compile.antlr.FunSQLParser;
import org.xdb.funsql.statement.AbstractServerStmt;

public class FunSQLCompiler {
	private Error lastError;

	public AbstractServerStmt compile(String sql) {
		try {
			FunSQLLexer lex = new FunSQLLexer(new ANTLRStringStream(sql));
			CommonTokenStream tokens = new CommonTokenStream(lex);

			FunSQLParser parser = new FunSQLParser(tokens);
			AbstractServerStmt statement = parser.statement();
			if (parser.failed()) {
				createGenericCompileErr("Statement could not be parsed (No details can be provided)!");
				return null;
			}

			this.lastError = statement.compile();

			if (lastError.isError())
				return null;

			return statement;

		} catch (RecognitionException e) {
			String[] args = { e.getMessage() };
			this.lastError = new Error(EnumError.COMPILER_UNDEFINED_ERROR, args);
		}

		return null;
	}

	public Error getLastError() {
		return lastError;
	}

	/**
	 * Create generic compiler error
	 * 
	 * @param msg
	 * @return
	 */
	public static Error createGenericCompileErr(String msg) {
		String args[] = { msg };
		Error error = new Error(EnumError.COMPILER_GENERIC, args);
		return error;
	}
}
