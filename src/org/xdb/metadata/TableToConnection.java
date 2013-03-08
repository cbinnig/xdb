package org.xdb.metadata;

import java.util.Collection;
import java.util.HashMap;
import org.xdb.funsql.compile.tokens.AbstractToken;

/**
 * Class that represents a relationship relation between the connection and the
 * table relation in order to allow N to M Relationships
 * 
 * @author a.c.mueller
 * 
 */
public class TableToConnection extends AbstractDatabaseObject {

	private static final long serialVersionUID = -2341486004909008455L;
	/*
	 * CREATE TABLE `TABLETOCONNECTION` ( `T_OID` bigint NOT NULL, `C_OID`
	 * bigint NOT NULL );
	 */

	private static final String TABLE_NAME = AbstractToken
			.toSqlIdentifier("TABLETOCONNECTION");
	private static final String[] ATTRIBUTES = { "T_OID", "C_OID" };
	private static final String ALL_ATTRIBUTES = AbstractToken
			.toSqlIdentifierList(ATTRIBUTES);

	private HashMap<Long, Table> tables = new HashMap<Long, Table>();
	private HashMap<Long, Connection> connections = new HashMap<Long, Connection>();

	private Long table_oid;
	private Long connection_oid;

	private static  TableToConnection prototype = new  TableToConnection();
	public TableToConnection(){
		super();
		this.objectType = EnumDatabaseObject.TABLETOCONNECTION;
	}
	
	public TableToConnection(Long table_oid, Long connection_oid) {
		super();
		this.table_oid = table_oid;
		this.connection_oid = connection_oid;
		this.objectType = EnumDatabaseObject.TABLETOCONNECTION;
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
		insertSql.append(this.table_oid);
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.connection_oid);
		insertSql.append(AbstractToken.RBRACE);

		return insertSql.toString();
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
	public String hashKey() {
		String hash = table_oid + "." + connection_oid;
		return hash;
	}

	public void addTable(Long oid, Table table) {
		this.tables.put(oid, table);
	}

	public void addConnection(Long oid, Connection connection) {
		this.connections.put(oid, connection);
	}

	// getters setter
	public Collection<Table> getTables() {
		return tables.values();
	}

	public void setTables(HashMap<Long, Table> tables) {
		this.tables = tables;
	}

	public Collection<Connection> getConnections() {
		return connections.values();
	}

	public void setConnections(HashMap<Long, Connection> connections) {
		this.connections = connections;
	}

	public Long getTable_oid() {
		return table_oid;
	}

	public Long getConnection_oid() {
		return connection_oid;
	}

	protected static String sqlDeleteAll() {
		return prototype.interalSqlDeleteAll();
	}
	
	protected static String sqlSelectAll() {
		return prototype.internalSqlSelectAll();
	}
	//Override because no OID is used for Relationship relations
	public String sqlDelete(){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(AbstractToken.DELETE);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.FROM);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(METADATA_SCHEMA);
		selectSql.append(AbstractToken.DOT);
		selectSql.append(getTableName());
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.WHERE);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append("T_OID");
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.EQUAL1);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(this.getTable_oid());
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.AND);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append("C_OID");
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.EQUAL1);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(this.getConnection_oid());
		System.out.println(selectSql.toString());
		return selectSql.toString();
	}
}