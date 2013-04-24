package org.xdb.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
  "PART" BOOLEAN NOT NULL,
  "PART_TYPE" character varying(255),
  "PART_DETAILS" character varying(255),
  "CONNECTION_OID" bigint
)
 */
public class Table extends AbstractDatabaseObject {
	private static final long serialVersionUID = 1756002524307128228L;

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("TABLE");
	
	private static final String[] ATTRIBUTES = {"OID", "NAME", "SOURCE_NAME", "SOURCE_SCHEMA", "SCHEMA_OID","PART","PART_TYPE","PART_DETAILS"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static long LAST_TEMP_OID = -1l;
	
	private static Table prototype = new Table();
	
	private String sourceName;
	private String sourceSchema;
	
	private Long schemaOid=-1l;

	private HashMap<Long, Attribute> attributes = new HashMap<Long, Attribute>(); 
	private HashMap<Long, Partition> partitions = new HashMap<Long, Partition>();
	private HashMap<Long, Connection> connections = new HashMap<Long, Connection>();
	
	//parameters for partitioning 
	private String partitionType;
	private String partitionDetails;
	private boolean partioned;
	

	/**
	 * Copy Constructor
	 * @param toCopy
	 */
	public Table(Table toCopy){
		super(toCopy);
		this.partioned = toCopy.partioned;
		this.partitionDetails = toCopy.partitionDetails;
		this.partitionDetails = toCopy.partitionDetails;
		
		this.sourceName = toCopy.sourceName;
		this.sourceSchema = toCopy.sourceSchema;
		
		this.schemaOid = toCopy.schemaOid;
		
		for(Attribute attribute : toCopy.attributes.values()){
			this.attributes.put(attribute.getOid(), attribute);	
		}
		for(Partition partition : toCopy.partitions.values()){
			this.partitions.put(partition.getOid(), partition);
		}
		
		for(Connection connection: toCopy.connections.values()){
			this.connections.put(connection.getOid(), connection);
		}
		
	}

	private Table(){
		super();
		this.objectType = EnumDatabaseObject.TABLE;
	}
	

	
	/**
	 * Constructor used to initialize a partioned table with different 
	 * @param oid
	 * @param name
	 * @param sourceName
	 * @param sourceSchema
	 * @param schemaOid
	 */
	public Table(Long oid, String name, String sourceName, String sourceSchema, Long schemaOid) {
		super(oid, name);
		this.sourceName = sourceName;
		this.sourceSchema = sourceSchema;
		this.schemaOid = schemaOid;
		this.objectType = EnumDatabaseObject.TABLE;
	}
	
	public Table(Long oid, String name, String sourceName, String sourceSchema, Long schemaOid, Long connectionID) {
		super(oid, name);
		this.sourceName = sourceName;
		this.sourceSchema = sourceSchema;
		this.schemaOid = schemaOid;
		this.objectType = EnumDatabaseObject.TABLE;

	}

	public Table(String name, String sourceName, String sourceSchema, Long schemaOid) {
		this(++LAST_OID, name, sourceName, sourceSchema, schemaOid);
	}
	
	public Table(String name, String sourceName, String sourceSchema, Long schemaOid, Long connectionID) {
		this(++LAST_OID, name, sourceName, sourceSchema, schemaOid, connectionID );
	}
	
	
	public Table(String name) {
		super(LAST_TEMP_OID--, name);
	}
	
	public static void LAST_OID(long LAST_OID) {
		Table.LAST_OID = LAST_OID;
	}
	
	public boolean isTemp(){
		return this.oid<0;
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
		// not replicated

		for(Connection connec : this.connections.values()){
			return connec.getOid();
		}
		return (long) -1;
	}
	

	
	private void addConnection(Long connectionOid, Connection connection) {
		this.connections.put(connectionOid, connection);
	}
	
	public void addConnection(Connection connection){
		this.addConnection(connection.getOid(), connection);
	}
	
	public void addConnections(Collection<Connection> connections){
		for (Connection connection : connections) {
			this.addConnection(connection);
		}
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
	
	public void addPartition(Partition partition){
		this.partitions.put(partition.getOid(), partition);
	}
	
	public Collection<Partition> getPartitions(){
		return this.partitions.values();
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
	
	public String getPartitionType() {
		return partitionType;
	}



	public String getPartitionDetails() {
		return partitionDetails;
	}



	public void setPartitionType(String partitionType) {
		this.partitionType = partitionType;
	}



	public void setPartitionDetails(String partitionDetails) {
		this.partitionDetails = partitionDetails;
	}
	
	public List<String> getListofPartDetails(){
		List<String> values = new ArrayList<String>();
		String[] splitted = this.partitionDetails.split(" ");
		for (String elem : splitted) {
			values.add(elem);
		}
		return values;
	}



	public void setPartioned(boolean partioned) {
		this.partioned = partioned;
	}



	public boolean isPartioned() {
		return partioned;
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
		//PART
		insertSql.append(AbstractToken.toSqlLiteral(this.partioned));
		//PART_TYPE
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.partitionType));
		//PART_DETAILS
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.partitionDetails));
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
	
	public String toSqlString() {
		StringBuffer tableBuffer = new StringBuffer(AbstractToken.LBRACE);
		
		Iterator<Attribute> attIter = this.attributes.values().iterator();
		while(attIter.hasNext()){
			Attribute att = attIter.next();
			tableBuffer.append(att.getName());
			tableBuffer.append(AbstractToken.BLANK);
			tableBuffer.append(att.getDataType().toString());
			
			if(attIter.hasNext()){
				tableBuffer.append(AbstractToken.COMMA);
				tableBuffer.append(AbstractToken.BLANK);
			}
		}
		
		tableBuffer.append(AbstractToken.RBRACE);
		
		return tableBuffer.toString();
	}
}
