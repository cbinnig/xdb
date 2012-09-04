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
	
	public AbstractServerStmt compile(String sql){
		FunSQLLexer lex = new FunSQLLexer(new ANTLRStringStream(sql));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

       	FunSQLParser parser = new FunSQLParser(tokens);
       	
        try {
            AbstractServerStmt statement =  parser.statement();
            this.lastError = statement.compile();
            
            if(lastError.isError())
            	return null;
            
            return statement;
            
        } catch (RecognitionException e)  {
        	String[] args = {e.getMessage()};
			this.lastError = new Error(EnumError.COMPILER_UNDEFINED_ERROR, args );
        }
        
        return null;
	}

	public Error getLastError() {
		return lastError;
	}
}
