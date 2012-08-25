package org.xdb.funsql.statement;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Connection;
import org.xdb.metadata.EnumDatabaseObject;

public class DropConnectionStmt extends AbstractStatement {
	private TokenIdentifier tConnection;
	private Connection connection;
	
	//Constructors
	public DropConnectionStmt(){
		this.statementType = EnumStatement.DROP_CONNECTION;
	}
	
	public DropConnectionStmt(String connection){
		this();
		this.tConnection = new TokenIdentifier(connection);
	}
	
	//getters and setters
	public void setConnection(TokenIdentifier tConnection) {
		this.tConnection = tConnection;
	}
	
	@Override
	public Error compile() {
		this.connection = Catalog.getConnection(this.tConnection.hashKey());
		
		if (this.connection == null) {
			return Catalog.createObjectNotExistsErr(this.tConnection.getName()
					.toString(), EnumDatabaseObject.CONNECTION);
		}
		
		return Error.NO_ERROR;
	}

	@Override
	public Error execute() {
		return Catalog.dropConncection(this.connection);
	}

}
