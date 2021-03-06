package org.xdb.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

	private static final String TABLE_NAME = AbstractToken
			.toSqlIdentifier("TABLE");

	private static final String[] ATTRIBUTES = { "OID", "NAME", "SCHEMA_OID",
			"PART_CNT", "PART_TYPE", "REF_TABLE_OID" };
	private static final String ALL_ATTRIBUTES = AbstractToken
			.toSqlIdentifierList(ATTRIBUTES);
	private static long LAST_OID = 0;
	private static long LAST_TEMP_OID = -1l;

	private static Table prototype = new Table();

	private Long schemaOid = -1l;
	private Map<Long, Attribute> attributes = new TreeMap<Long, Attribute>();
	private List<Connection> connections = new ArrayList<Connection>();
	private List<Partition> partitions = new ArrayList<Partition>();

	// parameters for partitioning
	private EnumPartitionType partitionType = EnumPartitionType.NO_PARTITION;
	private Map<Long, PartitionAttribute> partitionAtts = new TreeMap<Long, PartitionAttribute>();
	private boolean partioned = false;
	private Long partitionCnt = 1l;
	private Long refTableOid = null;

	// constants
	public static final String PART_PREFIX = "P";

	/**
	 * Copy Constructor
	 * 
	 * @param toCopy
	 */
	public Table(Table toCopy) {
		super(toCopy);

		this.partioned = toCopy.partioned;
		this.schemaOid = toCopy.schemaOid;
		this.refTableOid = toCopy.refTableOid;

		for (Attribute attribute : toCopy.attributes.values()) {
			this.attributes.put(attribute.getOid(), attribute);
		}
		for (Partition partition : toCopy.partitions) {
			this.partitions.add(partition);
		}

		for (Connection connection : toCopy.connections) {
			this.connections.add(connection);
		}

	}

	public Table() {
		super();
		this.objectType = EnumDatabaseObject.TABLE;
	}

	/**
	 * Constructor used to initialize a partioned table with different
	 * 
	 * @param oid
	 * @param name
	 * @param sourceName
	 * @param sourceSchema
	 * @param schemaOid
	 */
	public Table(Long oid, String name, Long schemaOid) {
		super(oid, name);
		this.schemaOid = schemaOid;
		this.objectType = EnumDatabaseObject.TABLE;
	}

	public Table(Long oid, String name, Long schemaOid, Long connectionID) {
		super(oid, name);

		this.schemaOid = schemaOid;
		this.objectType = EnumDatabaseObject.TABLE;

	}

	public Table(Long oid, String name, Long schemaOid, Long partitionCnt,
			String partType, Long refTableOid) {
		super(oid, name);

		this.objectType = EnumDatabaseObject.TABLE;
		this.schemaOid = schemaOid;
		this.partitionCnt = partitionCnt;
		this.partitionType = (partType != null) ? EnumPartitionType
				.valueOf(partType) : null;
		this.refTableOid = refTableOid;

	}

	public Table(String name, Long schemaOid) {
		this(++LAST_OID, name, schemaOid);
	}

	public Table(String name, Long schemaOid, Long connectionID) {
		this(++LAST_OID, name, schemaOid, connectionID);
	}

	public Table(String name) {
		super(LAST_TEMP_OID--, name);
	}

	public static void LAST_OID(long LAST_OID) {
		Table.LAST_OID = LAST_OID;
	}

	public boolean isTemp() {
		return this.oid < 0;
	}

	public Long getSchemaOid() {
		return schemaOid;
	}

	public void setSchemaOid(Long schemaOid) {
		this.schemaOid = schemaOid;
	}

	public List<Connection> getConnections(){
		return this.connections;
	}

	public void addConnection(Connection connection) {
		this.connections.add(connection);
	}

	public void addConnections(Collection<Connection> connections) {
		for (Connection connection : connections) {
			this.addConnection(connection);
		}
	}

	public void addAttribute(Attribute attribute) {
		this.attributes.put(attribute.getOid(), attribute);
	}

	public void addAttributes(Collection<Attribute> atts) {
		for (Attribute att : atts) {
			this.addAttribute(att);
		}
	}

	public Collection<Attribute> getAttributes() {
		return attributes.values();
	}

	public Collection<PartitionAttribute> getPartitionAttributes() {
		return partitionAtts.values();
	}

	public void addPartition(Partition partition) {
		this.partitions.add(partition);
	}

	public void addPartitionAttribute(PartitionAttribute partitionAttributes) {
		this.partitionAtts.put(partitionAttributes.getPart_att_oid(),
				partitionAttributes);
	}

	public List<Partition> getPartitions() {
		return partitions;
	}

	public Partition getPartition(int partNum){
		return this.partitions.get(partNum);
	}
	
	public Attribute getAttribute(String name) {
		for (Attribute att : this.attributes.values()) {
			if (att.getName().equals(name)) {
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

	public EnumPartitionType getPartitionType() {
		return partitionType;
	}

	public long getRefTableOid() {
		return refTableOid;
	}

	public void setPartitionType(EnumPartitionType partitionType) {
		this.partitionType = partitionType;
	}

	public void setRefTableOid(Long refTableOid) {
		this.refTableOid = refTableOid;
	}

	public void setPartitionCount(long partitionCount) {
		this.partitionCnt = partitionCount;
	}

	public long getPartitionCount() {
		return this.partitionCnt;
	}

	public void setPartioned(boolean partioned) {
		this.partioned = partioned;
	}

	public Table getRefTable() {
		return Catalog.getTable(this.refTableOid);
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
		insertSql.append(this.schemaOid);
		insertSql.append(AbstractToken.COMMA);
		// PART_CNT
		insertSql.append(this.partitionCnt);
		// PART_TYPE
		insertSql.append(AbstractToken.COMMA);
		String partitionType = (this.partitionType == null) ? AbstractToken.NULL
				: AbstractToken.toSqlLiteral(this.partitionType.toString());
		insertSql.append(partitionType);
		// REF_TABLE_OID
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.refTableOid);

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

	public String attsToDDL() {
		StringBuffer tableBuffer = new StringBuffer(AbstractToken.LBRACE);

		Iterator<Attribute> attIter = this.attributes.values().iterator();
		while (attIter.hasNext()) {
			Attribute att = attIter.next();
			tableBuffer.append(att.getName());
			tableBuffer.append(AbstractToken.BLANK);
			tableBuffer.append(att.getDataType().toString());

			if (attIter.hasNext()) {
				tableBuffer.append(AbstractToken.COMMA);
				tableBuffer.append(AbstractToken.BLANK);
			}
		}

		tableBuffer.append(AbstractToken.RBRACE);

		return tableBuffer.toString();
	}
}
