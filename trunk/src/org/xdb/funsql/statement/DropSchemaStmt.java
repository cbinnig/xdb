package org.xdb.funsql.statement;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Schema;

public class DropSchemaStmt extends AbstractStatement {
	private TokenSchema tSchema;
	private Schema schema;

	// Constructors
	public DropSchemaStmt(String schema) {
		this.tSchema = new TokenSchema(schema);
		this.statementType = EnumStatement.DROP_SCHEMA;
	}

	public DropSchemaStmt() {
		this.statementType = EnumStatement.DROP_SCHEMA;
	}

	// getters and setters
	public void setSchema(TokenSchema tSchema) {
		this.tSchema = tSchema;
	}
	
	public TokenSchema getSchema() {
		return tSchema;
	}

	@Override
	public Error compile() {
		this.schema = Catalog.getSchema(this.tSchema.hashKey());

		if (this.schema == null) {
			return Catalog.createObjectNotExistsErr(this.tSchema.getName()
					.toString(), EnumDatabaseObject.SCHEMA);
		}

		return Error.NO_ERROR;
	}

	@Override
	public Error execute() {
		return Catalog.dropSchema(this.schema);
	}

}
