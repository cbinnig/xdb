package org.xdb.metadata;

import org.xdb.funsql.compile.tokens.AbstractToken;

public class PartitionToConnection extends AbstractDatabaseObject {

	private static final long serialVersionUID = 3279427982175338162L;

	
	private static final String TABLE_NAME = AbstractToken
			.toSqlIdentifier("PARTITIONTOCONNECTION");
	private static final String[] ATTRIBUTES = { "P_OID", "C_OID" };
	private static final String ALL_ATTRIBUTES = AbstractToken
			.toSqlIdentifierList(ATTRIBUTES);

	private Long partition_oid;
	private Long connection_oid;
	
	private static PartitionToConnection prototype = new PartitionToConnection();
	
	
	public PartitionToConnection() {
		super();
		this.objectType = EnumDatabaseObject.PARTITIONTOCONNECTION;
	}
	
	

	public PartitionToConnection(Long partion_oid, Long connection_oid) {
		super();
		this.partition_oid = partion_oid;
		this.connection_oid = connection_oid;
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
		insertSql.append(this.partition_oid);
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
		return partition_oid+"."+connection_oid;
	}
	
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
		selectSql.append("P_OID");
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.EQUAL1);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(this.getPartition_oid());
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.AND);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append("C_OID");
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.EQUAL1);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(this.getConnection_oid());
		return selectSql.toString();
	}

	// getters and setters

	public Long getPartition_oid() {
		return partition_oid;
	}



	public void setPartition_oid(Long partition_oid) {
		this.partition_oid = partition_oid;
	}



	public Long getConnection_oid() {
		return connection_oid;
	}



	public void setConnection_oid(Long connection_oid) {
		this.connection_oid = connection_oid;
	}

	
	protected static String sqlDeleteAll() {
		return prototype.interalSqlDeleteAll();
	}
	
	protected static String sqlSelectAll() {
		return prototype.internalSqlSelectAll();
	}
}
