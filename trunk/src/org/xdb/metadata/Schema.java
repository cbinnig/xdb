package org.xdb.metadata;

import org.xdb.funsql.compile.tokens.AbstractToken;


public class Schema extends AbstractDatabaseObject {
/*
CREATE TABLE "SYSTEM"."SCHEMA"
(
  "OID" bigint NOT NULL,
  "NAME" character varying(255) NOT NULL,
)
*/

	private static final long serialVersionUID = -7060587426003109894L;

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("SCHEMA");
	private static final String[] ATTRIBUTES = {"OID", "NAME"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long START_OID = 2;
	private static long LAST_OID = START_OID;
	private static Schema prototype = new Schema();

	private Schema() {
		super();
		this.objectType = EnumDatabaseObject.SCHEMA;
	}
	
	public Schema(long oid, String name) {
		super(oid, name);
		this.objectType = EnumDatabaseObject.SCHEMA;
	}
	
	public Schema(String name) {
		super(++LAST_OID, name);
		this.objectType = EnumDatabaseObject.SCHEMA;
	}
	
	public static void LAST_OID(long LAST_OID) {
		Schema.LAST_OID = LAST_OID;
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
		insertSql.append(AbstractToken.RBRACE);
		return insertSql.toString();
	}

	protected static String sqlDeleteAll() {
		StringBuffer deleteSql = new StringBuffer(prototype.interalSqlDeleteAll());
		deleteSql.append(AbstractToken.BLANK);
		deleteSql.append(AbstractToken.WHERE);
		deleteSql.append(AbstractToken.BLANK);
		deleteSql.append(OID_NAME);
		deleteSql.append(AbstractToken.GREATER_EQUAL);
		deleteSql.append(START_OID);
		return deleteSql.toString();
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
}
