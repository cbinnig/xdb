package org.xdb.funsql.statement;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Schema;

public class CreateSchemaStmt extends AbstractStatement {
	private TokenSchema tSchema;
	private Schema schema;
	
	//Constructors
	public CreateSchemaStmt() {
		this.statementType = EnumStatement.CREATE_SCHEMA;
	}
	
	public CreateSchemaStmt(String name) {
		this();
		this.tSchema = new TokenSchema(name);
	}

	//getters and setters
	public void setSchema(TokenSchema tSchema) {
		this.tSchema = tSchema;
	}
	
	public TokenSchema getSchema() {
		return tSchema;
	}

	@Override
	public Error compile() {
		// check if schema with same name already exists
		Schema schema = Catalog.getSchema(this.tSchema.hashKey());
		if (schema != null) {
			return Catalog.createObjectAlreadyExistsErr(schema);
		}
		
		this.schema = new Schema(this.tSchema.toString());
		return this.schema.checkObject();
	}

	@Override
	public Error execute() {
		return Catalog.createSchema(schema);
	}

}
