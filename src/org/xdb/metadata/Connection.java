package org.xdb.metadata;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.store.EnumStore;

public class Connection extends AbstractDatabaseObject {
/*
CREATE TABLE "SYSTEM"."CONNECTION"
(
  "OID" bigint NOT NULL,
  "NAME" character varying(255) NOT NULL,
  "URL" character varying(1023) NOT NULL,
  "USER" character varying(255) NOT NULL,
  "PASSWD" character varying(255) NOT NULL,
  "STORE" integer NOT NULL
*/
	
	private static final long serialVersionUID = 8877966550656112235L;

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("CONNECTION");
	private static final String[] ATTRIBUTES = {"OID", "NAME", "URL", "USER", "PASSWD", "STORE"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static Connection prototype = new Connection();
	
	private String url;
	private String user;
	private String passwd;
	private EnumStore store;
	
	private Connection(){
		super();
		this.objectType = EnumDatabaseObject.CONNECTION;
	}
	
	public Connection(long oid, String name, String url, String user, String passwd, int store) {
		super(oid, name);
		this.url = url;
		this.user = user;
		this.passwd = passwd;
		this.store = EnumStore.get(store);
		this.objectType = EnumDatabaseObject.CONNECTION;
	}

	public Connection(String name, String url, String user, String passwd, EnumStore store) {
		super(++LAST_OID, name);
		this.url = url;
		this.user = user;
		this.passwd = passwd;
		this.store = store;
		this.objectType = EnumDatabaseObject.CONNECTION;
	}
	
	public static void LAST_OID(long LAST_OID) {
		Connection.LAST_OID = LAST_OID;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public EnumStore getStore() {
		return store;
	}

	public void setStore(EnumStore store) {
		this.store = store;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getAllAttributes() {
		return ALL_ATTRIBUTES;
	}
	
	@Override
	public String sqlInsert() {
		StringBuffer insertSql = new StringBuffer();
		insertSql.append(AbstractToken.INSERT); 
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.INTO);
		insertSql.append(AbstractToken.BLANK); 
		insertSql.append(METADATA_SCHEMA);
		insertSql.append(AbstractToken.DOT);
		insertSql.append(TABLE_NAME);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.LBRACE); 
		insertSql.append(ALL_ATTRIBUTES);
		insertSql.append(AbstractToken.RBRACE);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.VALUES);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.LBRACE);
		insertSql.append(this.oid);
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.name));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.url));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.user));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.passwd));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.store.ordinal());
		insertSql.append(AbstractToken.RBRACE);
		return insertSql.toString();
	}

	protected static String sqlDeleteAll() {
		return prototype.interalSqlDeleteAll();
	}
	
	protected static String sqlSelectAll() {
		return prototype.internalSqlSelectAll();
	}

	protected static String sqlSelectMaxOid() {
		return prototype.internalSqlSelectMaxOid();
	}
	
	@Override
	public String hashKey(){
		return this.name;
	}
	
	@Override
	public Error checkObject(){
		Error lastError = super.checkObject();
		if(lastError != Error.NO_ERROR)
			return lastError;
		
		String[] values = {this.url, this.user, this.passwd};
		int[] maxLength = {1023, 255, 255};
		for(int i=0; i<values.length; ++i){
			lastError = this.checkValueLength(values[i], maxLength[i]);
			if(lastError != Error.NO_ERROR)
				return lastError;
		}
		return Error.NO_ERROR;
	}
	
	public String toConnectionString() throws URISyntaxException {
		final URI urlObject = new URI(url);
		return urlObject.getScheme() + "://" + user + ":" + passwd + 
				"@" + urlObject.getHost() + ":" + (urlObject.getPort() == -1 ? 3306 : urlObject.getPort()) + urlObject.getPath();
	}
}
