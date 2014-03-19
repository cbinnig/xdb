package org.xdb.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	
	private static final String[] ATTRIBUTES = {"OID", "TABLE_OID", "PARTITION_NAME"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static long LAST_TEMP_OID = -1l;
	
	private long tableOid;
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	private static Partition prototype = new Partition();

	/**
	 * Copy Constructor
	 * @param toCopy
	 */
	public Partition(Partition toCopy){
		super(toCopy);

		this.tableOid = toCopy.tableOid;
		
		// Copy HashMap
		this.connections = new ArrayList<Connection>(toCopy.connections.size());
	
		for(Connection connection :toCopy.connections ){
			this.connections.add(new Connection(connection));
		}
	}
	
	private Partition(){
		super();
		this.objectType = EnumDatabaseObject.PARTITION;
	}
	
	public Partition(Long oid, long tableOid, String name) {
		super(oid, name);
		this.objectType = EnumDatabaseObject.PARTITION;
		this.tableOid = tableOid;
	}

	
	public Partition(long tableOid, String name){
		this(++LAST_OID, tableOid, name);
	}
	
	//constructor for temp partition
	public Partition(String name) {
		super(LAST_TEMP_OID--, name);
		this.objectType = EnumDatabaseObject.PARTITION;
	}
	
	//getter and setters
	public void addConnection(Connection connection){
		this.connections.add(connection);
	}
	
	public void addConnections(Collection<Connection> connections){
		for (Connection connection : connections) {
			this.addConnection(connection);
		}
	}
	
	public List<Connection> getConnections(){
		return this.connections;
	}
	
	@Override
	public String getAllAttributes() {
		return ALL_ATTRIBUTES;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public long getTableOid() {
		return tableOid;
	}

	public void setTableOid(long table_oid) {
		this.tableOid = table_oid;
	}

	//methods
	@Override
	public String hashKey() {
		StringBuffer hashKey = new StringBuffer();
		hashKey.append(this.tableOid);
		hashKey.append(AbstractToken.DOT);
		hashKey.append(name);
		return hashKey.toString();
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
		return value.toString();
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
		insertSql.append(this.tableOid);
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(AbstractToken.toSqlLiteral(this.name));
		insertSql.append(AbstractToken.RBRACE);
		return insertSql.toString();
	}
}
