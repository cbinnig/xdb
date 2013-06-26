package org.xdb.funsql.statement;

import java.io.File;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;

public class LoadDataInfileStmt extends AbstractServerStmt {
	
	Vector<String> partitions = new Vector<String>();
	String fileName = null;
	private TokenTable tTable;
	private Table table;

	
	public LoadDataInfileStmt(){
		
	}

	public void setTokenPartition(String partition) {
		if(!partitions.contains(partition)){
			this.partitions.add(partition);
		}
	}

	public void setTokenFilename(String filename) {
		this.fileName = filename;		
	}

	public void setTokenTable(TokenTable table) {
		this.tTable = table;
	}

	@Override
	public Error compile() {
		//Checks (Filename vorhanden? Table existent?...)
		
		File f = new File(fileName);
		if(!f.exists()) {
			String[] args = {"File with name "+fileName+ "not exists!"};
			Error fail = new Error(EnumError.COMPILER_GENERIC, args);		//File Not Found Error
			return fail;
//			return Catalog.createObjectNotExistsErr(fileName, EnumDatabaseObject.);
		}
		
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

		Error err = Catalog.executeInfileStmt(this.fileName, this.tTable.getName().toString());
		return err;
	}

}
