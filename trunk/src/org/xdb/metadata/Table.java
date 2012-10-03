package org.xdb.metadata;

import java.util.Collection;
import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;

/*
CREATE TABLE "SYSTEM"."TABLE"
(
  "OID" bigint NOT NULL,
  "NAME" character varying(255) NOT NULL,
  "SOURCE_NAME" character varying(255) NOT NULL,
  "SOURCE_SCHEMA" character varying(255) NOT NULL,
  "SCHEMA_OID" bigint NOT NULL,
  "CONNECTION_OID" bigint NOT NULL
)
 */
public class Table extends AbstractDatabaseObject {
	private static final long serialVersionUID = 1756002524307128228L;

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("TABLE");
	private static final String[] ATTRIBUTES = {"OID", "NAME", "SOURCE_NAME", "SOURCE_SCHEMA", "SCHEMA_OID", "CONNECTION_OID"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static Table prototype = new Table();
	
	private String sourceName;
	private String sourceSchema;
	private Long schemaOid;
	private Long connectionOid;
	private HashMap<Long, Attribute> attributes = new HashMap<Long, Attribute>(); 
	
	private Table(){
		super();
		this.objectType = EnumDatabaseObject.TABLE;
	}
	
	public Table(Long oid, String name, String sourceName, String sourceSchema, Long schemaOid, Long connectionOid) {
		super(oid, name);
		this.sourceName = sourceName;
		this.sourceSchema = sourceSchema;
		this.schemaOid = schemaOid;
		this.connectionOid = connectionOid;
		this.objectType = EnumDatabaseObject.TABLE;
	}

	public Table(String name, String sourceName, String sourceSchema, Long schemaOid, Long connectionOid) {
		super(++LAST_OID, name);
		this.sourceName = sourceName;
		this.sourceSchema = sourceSchema;
		this.schemaOid = schemaOid;
		this.connectionOid = connectionOid;
		this.objectType = EnumDatabaseObject.TABLE;
	}
	
	public static void LAST_OID(long LAST_OID) {
		Table.LAST_OID = LAST_OID;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceSchema() {
		return this.sourceSchema;
	}

	public void setSourceSchema(String sourceSchema) {
		this.sourceSchema = sourceSchema;
	}

	public Long getSchemaOid() {
		return schemaOid;
	}
	
	public void setSchemaOid(Long schemaOid) {
		this.schemaOid = schemaOid;
	}

	public Long getConnectionOid() {
		return connectionOid;
	}
	
	public void setConnectionOid(Long connectionOid) {
		this.connectionOid = connectionOid;
	}
	
	public void addAttribute(Attribute attribute){
		this.attributes.put(attribute.getOid(), attribute);
	}
	

	public void addAttributes(Collection<Attribute> atts){
		for(Attribute att: atts){
			this.addAttribute(att);
		}
	}
	
	public Collection<Attribute> getAttributes() {
		return attributes.values();
	}
	
	public Attribute getAttribute(String name) {
		for(Attribute att: this.attributes.values()){
			if(att.getName().equals(name)){
				return att;
			}
		}
		
		return null;
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
		insertSql.append(AbstractToken.toSqlLiteral(this.sourceName));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.sourceSchema));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.schemaOid);
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.connectionOid);
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
		StringBuffer hashKey = new StringBuffer();
		hashKey.append(this.schemaOid);
		hashKey.append(AbstractToken.DOT);
		hashKey.append(name);
		return hashKey.toString();
	}
	
	@Override
	public Error checkObject(){
		Error lastError = super.checkObject();
		if(lastError != Error.NO_ERROR)
			return lastError;
		
		String[] values = {this.sourceName, this.sourceSchema};
		int[] maxLength = {255, 255};
		for(int i=0; i<values.length; ++i){
			lastError = this.checkValueLength(values[i], maxLength[i]);
			if(lastError != Error.NO_ERROR)
				return lastError;
		}
		return Error.NO_ERROR;
	}
}
