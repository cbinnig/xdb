package org.xdb.metadata;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.statement.EnumLanguage;

/*
CREATE TABLE "SYSTEM"."FUNCTION"
(
  "OID" bigint NOT NULL,
  "NAME" character varying(255) NOT NULL,
  "SCHEMA_OID" bigint NOT NULL,
  "LANGUAGE" bigint,
  "SOURCE" text,
 */
public class Function extends AbstractDatabaseObject {
	private static final long serialVersionUID = -8710467949818693877L;

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("FUNCTION");
	private static final String[] ATTRIBUTES = {"OID", "NAME", "SCHEMA_OID", "LANGUAGE", "SOURCE"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static Function prototype = new Function();
	
	private Long schemaOid=-1L;
	EnumLanguage language;
	private String source;
	
	
	//constructors
	private Function() {
		super();
		this.objectType = EnumDatabaseObject.FUNCTION;
	}

	public Function(long oid, String name, Long schemaOid, int language, String source) {
		super(oid, name);
		this.schemaOid = schemaOid;
		this.language = EnumLanguage.get(language);
		this.source = source;
		this.objectType = EnumDatabaseObject.FUNCTION;
	}
	
	public Function(String name, Long schemaOid, EnumLanguage language, String source) {
		super(++LAST_OID, name);
		this.schemaOid = schemaOid;
		this.language = language;
		this.source = source;
		this.objectType = EnumDatabaseObject.FUNCTION;
	}

	//getters and setters
	public static void LAST_OID(long LAST_OID) {
		Function.LAST_OID = LAST_OID;
	}
	
	public Long getSchemaOid() {
		return schemaOid;
	}

	public void setSchemaOid(Long schemaOid) {
		this.schemaOid = schemaOid;
	}

	public EnumLanguage getLanguage() {
		return language;
	}

	public void setLanguage(EnumLanguage language) {
		this.language = language;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	//helper methods
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String getAllAttributes() {
		return ALL_ATTRIBUTES;
	}
	
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
		insertSql.append(this.schemaOid);
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.language.ordinal());
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.source));
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
		hashKey.append(this.schemaOid);
		hashKey.append(AbstractToken.DOT);
		hashKey.append(name);
		return hashKey.toString();
	}
	
	@Override
	public Error checkObject() {
		Error lastError = super.checkObject();
		if (lastError != Error.NO_ERROR)
			return lastError;
		
		while (this.source != null) {
			String[] values = { this.source };
			int[] maxLength = { Integer.MAX_VALUE };
			for (int i = 0; i < values.length; ++i) {
				lastError = this.checkValueLength(values[i], maxLength[i]);
				if (lastError != Error.NO_ERROR)
					return lastError;
			}
		}
		return Error.NO_ERROR;
	}
}
