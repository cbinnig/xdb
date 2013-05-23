package org.xdb.metadata;

import java.util.Collection;
import java.util.HashMap;

import org.xdb.funsql.compile.tokens.AbstractToken;

	/*
	/CREATE TABLE `PARTITION`(
			`OID` bigint NOT NULL,
			`TABLE_OID` bigint NOT NULL,
			`SOURCE_NAME` character varying(255) NOT NULL,
			`SOURCE_SCHEMA` character varying(255) NOT NULL,
			`SOURCE_PARTITION_NAME` character varying(255) NOT NULL,
			`PARTITION_NAME` character varying(255) NOT NULL
		);
		*/
/**
 * Represent the metadata table Partition
 * @author a.c.mueller
 *
 */


public class Partition extends AbstractDatabaseObject {

	private static final long serialVersionUID = 4916057015536997054L;

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("PARTITION");
	
	private static final String[] ATTRIBUTES = {"OID", "TABLE_OID", "SOURCE_NAME", "SOURCE_SCHEMA", "SOURCE_PARTITION_NAME","PARTITION_NAME"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static long LAST_TEMP_OID = -1l;
	
	private String source_name;
	private String source_schema;
	private String source_partition_name;
	private long table_oid;
	
	private HashMap<Long, Connection> connections = new HashMap<Long, Connection>();
	
	private static Partition prototype = new Partition();

	/**
	 * Copy Constructor
	 * @param toCopy
	 */
	public Partition(Partition toCopy){
		super(toCopy);
		this.source_name = toCopy.source_name;
		this.source_schema = toCopy.source_schema;
		this.source_partition_name = toCopy.source_partition_name;
		this.table_oid = toCopy.table_oid;
		
		// Copy HashMap
		this.connections = new HashMap<Long, Connection>();
	
		for(Connection connection :toCopy.connections.values() ){
			this.connections.put(connection.getOid(), new Connection(connection));
		}
	}
	
	private Partition(){
		super();
		this.objectType = EnumDatabaseObject.PARTITION;
	}
	
	public Partition(Long oid, String source_name, String source_schema,
			String source_partition_name, long table_oid, String partition_name) {
		super(oid, partition_name);

		this.source_name = source_name;
		this.source_schema = source_schema;
		this.source_partition_name = source_partition_name;
		this.table_oid = table_oid;
		
	
	}

	
	public Partition( String source_name, String source_schema,
			String source_partition_name, long table_oid, String partition_name){
		this(++LAST_OID, source_name, source_schema,source_partition_name, table_oid, partition_name);
	}
	
	//Tmp constructor
	public Partition(String name) {
		super(LAST_TEMP_OID--, name);
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
		insertSql.append(this.table_oid);
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.source_name));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.source_schema));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.source_partition_name));
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.name));
		insertSql.append(AbstractToken.RBRACE);
		return insertSql.toString();
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
	
	//getter and setters
	
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}


	public Long getConnectionOid() {
		// not replicated

		for(Connection connec : this.connections.values()){
			return connec.getOid();
		}
		return (long) -1;
	}


	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}

	public String getSource_schema() {
		return source_schema;
	}

	public void setSource_schema(String source_schema) {
		this.source_schema = source_schema;
	}

	public String getSource_partition_name() {
		return source_partition_name;
	}

	public void setSource_partition_name(String source_partition_name) {
		this.source_partition_name = source_partition_name;
	}

	public long getTable_oid() {
		return table_oid;
	}

	public void setTable_oid(long table_oid) {
		this.table_oid = table_oid;
	}

	@Override
	public String getAllAttributes() {
		return ALL_ATTRIBUTES;
	}

	@Override
	public String hashKey() {
		StringBuffer hashKey = new StringBuffer();
		hashKey.append(this.table_oid);
		hashKey.append(AbstractToken.DOT);
		hashKey.append(name);
		return hashKey.toString();
	}
	
	//init methods
	protected static String sqlDeleteAll() {
		return prototype.interalSqlDeleteAll();
	}
	
	protected static String sqlSelectAll() {
		return prototype.internalSqlSelectAll();
	}

	protected static String sqlSelectMaxOid() {
		return prototype.internalSqlSelectMaxOid();
	}
	
	public static void LAST_OID(long LAST_OID) {
		Partition.LAST_OID = LAST_OID;
	}
	
	public boolean isTemp(){
		return this.oid<0;
	}
	
	@Override
	public String toString(){
		StringBuffer value = new StringBuffer();
		value.append(this.getName());
		value.append(" in connection: ");
		value.append(this.getConnectionOid());
		return value.toString();
	}

}
