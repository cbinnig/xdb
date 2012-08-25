package org.xdb.funsql.statement;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;

public class DropTableStmt extends AbstractStatement {
	private TokenTable tTable;
	private Table table;

	//constructors
	public DropTableStmt() {
		super();
		this.statementType = EnumStatement.DROP_TABLE;
	}
	
	public DropTableStmt(String table) {
		this();

		this.tTable = new TokenTable(table);
	}

	//getters and setters
	public void setTable(TokenTable table){
		this.tTable=table;
	}
	
	public void setSchema(String schema) {
		this.tTable.setSchema(schema);
	}

	@Override
	public Error compile() {
		// check if table with same name and schema exists
		String schemaKey = this.tTable.getSchema().hashKey();
		Schema schema = Catalog.getSchema(schemaKey);
		String tableKey = this.tTable.hashKey(schema.getOid());
		this.table = Catalog.getTable(tableKey);
		if (this.table == null) {
			return Catalog.createObjectNotExistsErr(this.tTable.getName()
					.toString(), EnumDatabaseObject.TABLE);
		}

		return Error.NO_ERROR;

	}

	@Override
	public Error execute() {
		return Catalog.dropTable(this.table);
	}

}
