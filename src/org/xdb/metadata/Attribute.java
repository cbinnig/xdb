package org.xdb.metadata;

import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.types.EnumSimpleType;

public class Attribute extends AbstractDatabaseObject{
/*	
 CREATE TABLE "SYSTEM"."ATTRIBUTE"
(
"OID" bigint NOT NULL,
"NAME" character varying(255) NOT NULL,
"TYPE" integer NOT NULL,
"TABLE_OID" bigint NOT NULL
)
*/
	private static final long serialVersionUID = -2974066829425473308L;

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("ATTRIBUTE");
	private static final String[] ATTRIBUTES = {"OID", "NAME", "TYPE", "TABLE_OID"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static Attribute prototype = new Attribute();

	private EnumSimpleType dataType;
	private long tableOid;

	private Attribute() {
		super();
		this.objectType = EnumDatabaseObject.ATTRIBUTE;
	}

	public Attribute(long oid, String name, int dataType, long tableOid) {
		super(oid, name);
		this.dataType = EnumSimpleType.get(dataType);
		this.tableOid = tableOid;
		this.objectType = EnumDatabaseObject.ATTRIBUTE;
	}
	
	public Attribute(String name, EnumSimpleType dataType, long tableOid) {
		super(++LAST_OID, name);
		this.dataType = dataType;
		this.tableOid = tableOid;
		this.objectType = EnumDatabaseObject.ATTRIBUTE;
	}
	
	public static void LAST_OID(long LAST_OID) {
		Attribute.LAST_OID = LAST_OID;
	}

	public EnumSimpleType getDataType() {
		return dataType;
	}

	public void setDataType(EnumSimpleType type) {
		this.dataType = type;
	}

	public long getTableOid() {
		return tableOid;
	}

	public void setTableOid(long tableOid) {
		this.tableOid = tableOid;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public static String getTableNameStatic() {
		return Attribute.prototype.getTableName();
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
		insertSql.append(this.dataType.ordinal());
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.tableOid);
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
	public String hashKey() {
		StringBuffer hashKey = new StringBuffer();
		hashKey.append(this.tableOid);
		hashKey.append(AbstractToken.DOT);
		hashKey.append(name);
		return hashKey.toString();
	}
}
