package org.xdb.funsql.statement;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenStringLiteral;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Connection;
import org.xdb.store.EnumStore;

public class CreateConnectionStmt extends AbstractStatement {
	private TokenIdentifier tConnection;
	private TokenStringLiteral tURL;
	private TokenStringLiteral tUser;
	private TokenStringLiteral tPasswd;
	private TokenStringLiteral tStore;
	private EnumStore store;
	private Connection connection;

	// Constructors
	public CreateConnectionStmt() {
		this.statementType = EnumStatement.CREATE_CONNECTION;
	}

	public CreateConnectionStmt(String connection, String url, String user,
			String passwd, String store) {
		this();

		this.tConnection = new TokenIdentifier(connection);
		this.tURL = new TokenStringLiteral(url);
		this.tUser = new TokenStringLiteral(user);
		this.tPasswd = new TokenStringLiteral(passwd);
		this.store = EnumStore.get(store);
		this.tStore = new TokenStringLiteral(store);
	}

	//getters and setters
	public void setConnection(TokenIdentifier tConnection) {
		this.tConnection = tConnection;
	}
	
	public TokenIdentifier getConnection() {
		return tConnection;
	}
	
	public void setURL(TokenStringLiteral tURL) {
		this.tURL = tURL;
	}

	public void setUser(TokenStringLiteral tUser) {
		this.tUser = tUser;
	}

	public void setPasswd(TokenStringLiteral tPasswd) {
		this.tPasswd = tPasswd;
	}
	
	public void setStore(TokenStringLiteral tStore) {
		this.store = EnumStore.get(tStore.getValue());
		this.tStore = tStore;
	}

	public TokenStringLiteral getURL() {
		return tURL;
	}

	public TokenStringLiteral getUser() {
		return tUser;
	}

	public TokenStringLiteral getPasswd() {
		return tPasswd;
	}

	public EnumStore getStore() {
		return store;
	}

	@Override
	public Error compile() {
		// check if connection with same name already exists
		Connection conn = Catalog.getConnection(this.tConnection.hashKey());
		if (conn != null) {
			return Catalog.createObjectAlreadyExistsErr(conn);
		}
		
		if(this.store==null){
			return EnumStore.createStoreNotExistsErr(this.tStore.getValue());
		}

		this.connection = new Connection(this.tConnection.toString(),
				this.tURL.toString(), this.tUser.toString(),
				this.tPasswd.toString(), store);
		return connection.checkObject();
	}

	@Override
	public Error execute() {
		return Catalog.createConncection(this.connection);
	}
}
