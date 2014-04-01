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
	
	private boolean doOptimize = true;
	private boolean doParallelize = true;
	private boolean doSemanticAnalysis = true; 
	private boolean doFaultTolerance = false; 
	
	//getters and setters
	public Error getLastError() {
		return lastError;
	}

	public void doParallelize(boolean doParallelize) {
		this.doParallelize = doParallelize;
	}
	
	public void doOptimize(boolean doOptimize) {
		this.doOptimize = doOptimize;
	}
	
	public void doSemanticAnalysis(boolean doSemanticAnalysis) {
		this.doSemanticAnalysis = doSemanticAnalysis;
	}

	//methods
	public AbstractServerStmt compile(String sql) {
		try {
			FunSQLLexer lex = new FunSQLLexer(new ANTLRStringStream(sql));
			CommonTokenStream tokens = new CommonTokenStream(lex);

			FunSQLParser parser = new FunSQLParser(tokens);
			AbstractServerStmt statement = parser.statement();
			if (statement==null || parser.failed()) {
				createGenericCompileErr("Statement could not be parsed (No details can be provided)!");
				return null;
			}
			
			//compile (including semantic analysis if requested)
			statement.doSemanticAnalysis(this.doSemanticAnalysis);
			
			this.lastError = statement.compile();
			if (lastError.isError())
				return null;

			//optimize if requested
			if(this.doOptimize){
				this.lastError = statement.optimize();
				if (lastError.isError())
					return null;
			}
			
			//parallelize if requested
			if(this.doParallelize){
				this.lastError = statement.parallelize();
				if (lastError.isError())
					return null;
			} 
		    
			// find the best materilaizations 
			if(this.doFaultTolerance){
				this.lastError = statement.applyFaultTolerance(); 
				if(lastError.isError()) 
					return null; 
			}
					
			return statement;

		} catch (RecognitionException e) {
			e.printStackTrace();
			String[] args = { e.toString() };
			this.lastError = new Error(EnumError.COMPILER_UNDEFINED_ERROR, args);
		}

		return null;
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
