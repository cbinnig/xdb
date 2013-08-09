package org.xdb.funsql.statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenDataType;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Connection;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.EnumPartitionType;
import org.xdb.metadata.Partition;
import org.xdb.metadata.PartitionAttributes;
import org.xdb.metadata.PartitionToConnection;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;
import org.xdb.metadata.TableToConnection;

public class CreateTableStmt extends AbstractServerStmt {
	//Tokens coming from compiler
	private TokenTable tTable;
	private Vector<TokenAttribute> tAttributes = new Vector<TokenAttribute>();
	private Vector<TokenDataType> tDataTypes = new Vector<TokenDataType>();
	private Vector<TokenAttribute> tPartAttributes = new Vector<TokenAttribute>();
	private Vector<TokenAttribute> tRefPartAttributes = new Vector<TokenAttribute>();
	private Vector<TokenIdentifier> tPartitions = new Vector<TokenIdentifier>();
	private Vector<TokenIdentifier> tConnections = new Vector<TokenIdentifier>();
	private HashMap<String, List<TokenIdentifier>> tPartToConnection = new HashMap<String, List<TokenIdentifier>>();

	//Variables to create table object
	private boolean partitioned = false;
	private long partitionCount = 0;
	private EnumPartitionType partitionType;
	private Schema schema = null;
	private Table table = null;
	private Vector<Connection> connections = new Vector<Connection>();
	private Vector<PartitionToConnection> partToConnections = new Vector<PartitionToConnection>();
	private Vector<PartitionAttributes> partitionAttributes = new Vector<PartitionAttributes>();
	private HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
	private HashMap<String, Partition> partitions = new HashMap<String, Partition>();

	// Constructors
	public CreateTableStmt() {
		this.statementType = EnumStatement.CREATE_TABLE;
	}

	public CreateTableStmt(String table) {
		this();
		this.tTable = new TokenTable(table);
	}

	// getters and setters
	public void addPartition(String partition) {
		partitionCount++;
		this.tPartitions.add(new TokenIdentifier(partition));
	}

	public void setPartitionType(String type) {
		this.partitionType = EnumPartitionType.getValue(type);
		this.partitioned = !this.partitionType
				.equals(EnumPartitionType.NO_PARTITION);
	}

	public void addPartitionAttribute(TokenAttribute tAtt) {
		this.tPartAttributes.add(tAtt);
	}

	public void addRefPartitionAttribute(TokenAttribute tAtt) {
		this.tRefPartAttributes.add(tAtt);
	}

	public void addPConnection(String partition, TokenIdentifier conn) {
		List<TokenIdentifier> values = this.tPartToConnection
				.get(partition);
		if (values == null) {
			values = new ArrayList<TokenIdentifier>();
			this.tPartToConnection.put(partition, values);
		}

		// add connection to list
		if (!values.contains(conn))
			values.add(conn);
	}

	public void addAttribute(String attribute) {
		this.tAttributes.add(new TokenAttribute(attribute));
	}

	public void addAttribute(TokenAttribute attribute) {
		this.tAttributes.add(attribute);
	}

	public void addConnection(TokenIdentifier ti) {
		this.tConnections.add(ti);
	}

	public void addConnection(String connec) {
		this.addConnection(new TokenIdentifier(connec));
	}

	public void addType(TokenDataType type) {
		this.tDataTypes.add(type);
	}

	public void addType(String type) {
		this.tDataTypes.add(new TokenDataType(type));
	}

	public void addType(String type, int maxLength) {
		this.tDataTypes.add(new TokenDataType(type, maxLength));
	}

	public TokenTable getTable() {
		return tTable;
	}

	public TokenIdentifier getConnection() {
		return tConnections.get(0);
	}

	public Collection<TokenAttribute> getAttributes() {
		return tAttributes;
	}

	public Collection<TokenDataType> getDataTypes() {
		return tDataTypes;
	}

	public void setTable(TokenTable tTable) {
		this.tTable = tTable;
	}

	@Override
	public Error compile() {
		Error lastError = new Error();

		// check if schema exists
		String schemaKey = this.tTable.getSchema().hashKey();
		this.schema = Catalog.getSchema(schemaKey);
		if (this.schema == null) {
			return Catalog.createObjectNotExistsErr(schemaKey,
					EnumDatabaseObject.SCHEMA);
		}

		// check if table already exists
		this.table = Catalog
				.getTable(this.tTable.hashKey(this.schema.getOid()));
		if (table != null) {
			return Catalog.createObjectAlreadyExistsErr(table);
		}

		// create table object and check table object
		this.table = new Table(this.tTable.getName().toString(),
				this.schema.getOid());
		this.table.setPartioned(this.partitioned);
		lastError = this.table.checkObject();
		if (lastError.isError())
			return lastError;

		// initialize attributes
		lastError = initAttributes();
		if (lastError.isError())
			return lastError;

		// more initializations
		if (!this.partitioned) {
			// add connections to Table
			String connectionKey;
			for (TokenIdentifier cti : this.tConnections) {
				connectionKey = cti.hashKey();
				Connection connection = Catalog.getConnection(connectionKey);
				this.connections.add(connection);
				if (connection == null) {
					return Catalog.createObjectNotExistsErr(cti.getValue(),
							EnumDatabaseObject.CONNECTION);
				}
			}
			this.table.addConnections(this.connections);
		} else {
			// initialize partitioning 
			lastError = initPartitioning();
		}
		
		return lastError;
	}

	/**
	 * Initialize partitioning information of table object
	 * @return
	 */
	private Error initPartitioning() {
		Error lastError = new Error();

		// partitioned call partition table constructor
		this.table.setPartitionCount(this.partitionCount);
		this.table.setPartitionType(this.partitionType);

		// build partition details
		Vector<TokenAttribute> scannedpartitionAttributes = new Vector<TokenAttribute>();
		if (this.table.getPartitionType().equals(EnumPartitionType.HASH)) {
			for (TokenAttribute ta : this.tPartAttributes) {
				// error handling
				if (scannedpartitionAttributes.contains(ta)) {
					return Catalog.createPartitionContainsDupAttErr(
							this.table.getName(), ta.getName().getValue());
				}

				if (!this.tAttributes.contains(ta)) {
					return Catalog.createPartitionContainsWrongArgumentAttErr(
							this.table.getName(), ta.getName().getValue());
				}
				Attribute partitionAtt = attributes.get(ta.getName().getValue());

				// add partition to table object
				PartitionAttributes partAtts = new PartitionAttributes(
						partitionAtt.getOid(), null);
				this.partitionAttributes.add(partAtts);
				this.table.addPartitionAttribute(partAtts);

				scannedpartitionAttributes.add(ta);
			}
		} else if (this.table.getPartitionType().isReferencePartition()) {
			if (this.tPartAttributes.size() != this.tRefPartAttributes
					.size()) {
				return this
						.createGenericCompileErr("Partitioning specification failure: Wrong number of referenced attributes");
			}

			String refTableName = null;
			int attIdx = 0;
			for (TokenAttribute ta : this.tPartAttributes) {
				TokenAttribute tr = this.tRefPartAttributes.get(attIdx++);
				String newRefTableName = tr.getTable().getName().getValue();

				// error handling
				if (scannedpartitionAttributes.contains(ta)) {
					return Catalog.createPartitionContainsDupAttErr(
							this.table.getName(), ta.getName().getValue());
				}
				if (!this.tAttributes.contains(ta)) {
					return Catalog.createPartitionContainsWrongArgumentAttErr(
							this.table.getName(), ta.getName().getValue());
				}
				if (refTableName != null
						&& !newRefTableName.equalsIgnoreCase(refTableName)) {
					return this
							.createGenericCompileErr("Partitioning specification failure: Only one table can be used as reference");
				} else {
					refTableName = newRefTableName;
				}
				TokenTable tRefTable = new TokenTable(refTableName);
				Table refTable = Catalog.getTable(tRefTable.hashKey(this.schema
						.getOid()));
				if (refTable == null) {
					return Catalog.createObjectNotExistsErr(newRefTableName,
							EnumDatabaseObject.TABLE);
				}

				Attribute partitionAtt = attributes.get(ta.getName().getValue());
				Attribute referenceAtt = Catalog.getAttribute(tr
						.hashKey(refTable.getOid()));
				if (referenceAtt == null) {
					return Catalog.createPartitionContainsWrongArgumentAttErr(
							tr.getTable().getName().getValue(), ta.getName()
									.getValue());
				}

				// add partition to table object
				PartitionAttributes partAtts = new PartitionAttributes(
						partitionAtt.getOid(), referenceAtt.getOid());
				this.partitionAttributes.add(partAtts);
				this.table.addPartitionAttribute(partAtts);
				this.table.setRefTableOid(refTable.getOid());

				scannedpartitionAttributes.add(ta);
			}
		}

		// initialize table partitions
		Partition newPart = null;
		TokenIdentifier tPartition = null;
		Connection tempConnection;
		for (int i = 0; i < this.tPartitions.size(); i++) {

			tPartition = this.tPartitions.get(i);
			if (this.partitions
					.containsKey(tPartition.getValue())) {
				return Catalog.createTableContainsDupAttErr(this.table
						.getName(), tPartition.getValue());
			}
			newPart = new Partition(this.table.getOid(), tPartition
					.getValue());

			// put partition into hash map and add to table
			this.partitions.put(tPartition.getValue(), newPart);

			// get connections for partition
			List<TokenIdentifier> tconnections = this.tPartToConnection
					.get(this.tPartitions.get(i).getValue());
			// realize n to m Relationship of partitions to connections
			for (TokenIdentifier tokenIdentifier : tconnections) {
				// get connection for this partition
				tempConnection = Catalog.getConnection(tokenIdentifier
						.hashKey());
				if (tempConnection == null) {
					return Catalog.createObjectNotExistsErr(
							tokenIdentifier.getValue(),
							EnumDatabaseObject.CONNECTION);
				}

				this.partToConnections.add(new PartitionToConnection(newPart
						.getOid(), tempConnection.getOid()));
				newPart.addConnection(tempConnection);
			}

			this.table.addPartition(newPart);
		}

		return lastError;
	}

	/**
	 * Initialize attributes of table object
	 * @return
	 */
	private Error initAttributes() {
		Error lastError = new Error();

		// check attributes
		for (int i = 0; i < this.tAttributes.size(); ++i) {
			TokenAttribute tAttribute = this.tAttributes.get(i);
			TokenDataType tDataType = this.tDataTypes.get(i);

			String attName = tAttribute.getName().toString();
			if (this.attributes.containsKey(attName)) {
				return Catalog.createTableContainsDupAttErr(
						this.table.getName(), attName);
			}

			if (tDataType == null) {
				return EnumSimpleType.createTypeNotExistsErr(tAttribute
						.getName().toString());
			}

			Attribute attribute = new Attribute(
					tAttribute.getName().toString(), tDataType.getDataType(),
					this.table.getOid());
			attributes.put(attName, attribute);

			this.table.addAttributes(attributes.values());

			lastError = attribute.checkObject();
			if (lastError.isError())
				return lastError;
		}
		return lastError;
	}

	@Override
	public Error execute() {
		// add table
		Error lastError = Catalog.createTable(table);
		if (lastError != Error.NO_ERROR)
			return lastError;

		// add attributes
		for (Attribute attribute : this.attributes.values()) {
			lastError = Catalog.createAttribute(attribute);
			if (lastError != Error.NO_ERROR)
				return lastError;
		}

		// add partition
		for (Partition partition : this.partitions.values()) {
			lastError = Catalog.createPartition(partition);
			if (lastError != Error.NO_ERROR)
				return lastError;
		}

		// add partitionAttributes
		for (PartitionAttributes partAtts : this.partitionAttributes) {
			lastError = Catalog.createPartitionAttributes(partAtts);
			if (lastError != Error.NO_ERROR)
				return lastError;
		}

		// Partition to Connection
		for (PartitionToConnection partoCo : this.partToConnections) {
			lastError = Catalog.createPartitionToConnection(partoCo);
			if (lastError != Error.NO_ERROR)
				return lastError;
		}

		// add Table To Connection
		TableToConnection taToCo = null;
		for (Connection connection : this.connections) {
			taToCo = new TableToConnection(table.getOid(), connection.getOid());
			lastError = Catalog.createTableToConnection(taToCo);
			if (lastError != Error.NO_ERROR)
				return lastError;
		}

		return Error.NO_ERROR;
	}
}
