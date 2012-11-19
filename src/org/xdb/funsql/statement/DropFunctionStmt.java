package org.xdb.funsql.statement;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenFunction;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Function;
import org.xdb.metadata.Schema;

public class DropFunctionStmt extends AbstractServerStmt {
	private TokenFunction tFunction;
	private Function function;

	//constructors
	public DropFunctionStmt() {
		super();
		this.statementType = EnumStatement.DROP_FUNCTION;
	}
	
	public DropFunctionStmt(String function) {
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
		this.function = Catalog.getFunction(functionKey);
		if (this.function == null) {
			return Catalog.createObjectNotExistsErr(this.tFunction.getName()
					.toString(), EnumDatabaseObject.FUNCTION);
		}

		return Error.NO_ERROR;

	}

	@Override
	public Error execute() {
		return Catalog.dropFunction(this.function);
	}

}
