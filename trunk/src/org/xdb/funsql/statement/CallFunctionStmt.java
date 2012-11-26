package org.xdb.funsql.statement;

import org.xdb.client.MasterTrackerClient;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.analyze.FunctionCache;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Function;
import org.xdb.metadata.Schema;

public class CallFunctionStmt extends AbstractServerStmt {
	private TokenFunction tFunction;
	private FunctionCache cache = FunctionCache.getCache();
	private CompilePlan fPlan;

	//constructors
	public CallFunctionStmt() {
		super();
		this.statementType = EnumStatement.CALL_FUNCTION;
	}
	
	public CallFunctionStmt(String function) {
		this();

		this.tFunction = new TokenFunction(function);
	}

	//getters and setters
	public void setFunction(TokenFunction function){
		this.tFunction=function;
	}
	
	public void setSchema(String schema) {
		this.tFunction.setSchema(schema);
	}

	@Override
	public Error compile() {
		// check if table with same name and schema exists
		String schemaKey = this.tFunction.getSchema().hashKey();
		Schema schema = Catalog.getSchema(schemaKey);
		String functionKey = this.tFunction.hashKey(schema.getOid());
		Function function = Catalog.getFunction(functionKey);
		if (function == null) {
			return Catalog.createObjectNotExistsErr(this.tFunction.getName()
					.toString(), EnumDatabaseObject.FUNCTION);
		}
		
		if(this.cache.hasPlan(functionKey)){
			this.fPlan = this.cache.getPlan(functionKey);
		}
		else{
			FunSQLCompiler compiler = new FunSQLCompiler();
			compiler.doSemanticAnalysis(false);
			compiler.doOptimize(true);
			
			CreateFunctionStmt fStmt = (CreateFunctionStmt) compiler.compile(function.getSource());
			if(compiler.getLastError().isError()){
				return compiler.getLastError();
			}
			this.fPlan = fStmt.getPlan();
		}
		
		return new Error();
	}

	@Override
	public Error execute() {
		MasterTrackerClient client = new MasterTrackerClient();
		Error err = client.executePlan(this.fPlan);
		if(err.isError())
			return err;
		
		return new Error();
	}

}
