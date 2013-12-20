package org.xdb.funsql.statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.xdb.metadata.PartitionAttribute;
import org.xdb.metadata.PartitionToConnection;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;
import org.xdb.metadata.TableToConnection;

public class CreateTableStmt extends AbstractServerStmt {
	// Tokens coming from compiler
	private TokenTable tTable;
	private Vector<TokenAttribute> tAttributes = new Vector<TokenAttribute>();
	private Vector<TokenDataType> tDataTypes = new Vector<TokenDataType>();
	private Vector<TokenAttribute> tPartAttributes = new Vector<TokenAttribute>();
	private Vector<TokenAttribute> tRefPartAttributes = new Vector<TokenAttribute>();
	private Vector<TokenIdentifier> tPartitions = new Vector<TokenIdentifier>();
	private Vector<TokenIdentifier> tConnections = new Vector<TokenIdentifier>();
	private HashMap<String, List<TokenIdentifier>> tPartToConnection = new HashMap<String, List<TokenIdentifier>>();

	// Variables to create table object
	private boolean partitioned = false;
	private long partitionCount = 0;
	private EnumPartitionType partitionType;
	private Schema schema = null;
	private Table table = null;
	private Vector<Connection> connections = new Vector<Connection>();
	private Vector<PartitionToConnection> partToConnections = new Vector<PartitionToConnection>();
	private Vector<PartitionAttribute> partitionAttributes = new Vector<PartitionAttribute>();
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
		List<TokenIdentifier> values = this.tPartToConnection.get(partition);
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

		// error handling: check if schema exists
		String schemaKey = this.tTable.getSchema().hashKey();
		this.schema = Catalog.getSchema(schemaKey);
		if (this.schema == null) {
			return Catalog.createObjectNotExistsErr(schemaKey,
					EnumDatabaseObject.SCHEMA);
		}

		// error handling: check if table already exists
		this.table = Catalog
				.getTable(this.tTable.hashKey(this.schema.getOid()));
		if (table != null) {
			return Catalog.createObjectAlreadyExistsErr(table);
		}
		
		// error handling: make sure that non-partitioned table has a connection
		if (!this.partitioned && this.tConnections.size()==0) {
			return this.createGenericCompileErr("Non-partitioned table "+this.tTable+" must specify a connection!");
		}

		// create table catalog object 
		this.table = new Table(this.tTable.getName().toString(),
				this.schema.getOid());
		this.table.setPartioned(this.partitioned);
		lastError = this.table.checkObject();
		if (lastError.isError())
			return lastError;

		// initialize attributes and add to table
		lastError = initAttributes();
		if (lastError.isError())
			return lastError;

		// initialize connection for non-partitioned table
		if (!this.partitioned) {
			for (TokenIdentifier tConnection : this.tConnections) {
				Connection connection = Catalog.getConnection(tConnection.hashKey());
				if (connection == null) {
					return Catalog.createObjectNotExistsErr(tConnection.getValue(),
							EnumDatabaseObject.CONNECTION);
				}
				this.connections.add(connection);
			}
			this.table.addConnections(this.connections);
		} 
		// initialize connection for partitioned table
		else {
			lastError = initPartitioning();
		}

		return lastError;
	}

	/**
	 * Initialize partitioning information of table object
	 * 
	 * @return
	 */
	private Error initPartitioning() {
		Error lastError = new Error();

		// partitioned call partition table constructor
		this.table.setPartitionCount(this.partitionCount);
		this.table.setPartitionType(this.partitionType);

		Set<TokenAttribute> tPartAtts = new HashSet<TokenAttribute>();
		
		// build partition details: HASH
		if (this.table.getPartitionType().isHash()) {
			for (TokenAttribute tPartAtt : this.tPartAttributes) {
				// error handling: duplicate partition attributes
				if (tPartAtts.contains(tPartAtt)) {
					return Catalog.createPartitionContainsDupAttErr(
							this.table.getName(), tPartAtt.getName().getValue());
				}
				// error handling: partition attribute not in table
				if (!this.tAttributes.contains(tPartAtt)) {
					return Catalog.createPartitionContainsWrongArgumentAttErr(
							this.table.getName(), tPartAtt.getName().getValue());
				}
				Attribute att = this.attributes.get(tPartAtt.getName().getValue());

				// add partition to table object
				PartitionAttribute partAtt = new PartitionAttribute(
						att.getOid());
				this.partitionAttributes.add(partAtt);
				this.table.addPartitionAttribute(partAtt);

				tPartAtts.add(tPartAtt);
			}
		}
		// build partition details: REF
		else if (this.table.getPartitionType().isReference()) {
			
			// error handling: wrong number of reference attributes
			if (this.tPartAttributes.size() != this.tRefPartAttributes.size()) {
				return this
						.createGenericCompileErr("Partitioning specification failure: Wrong number of referenced attributes");
			}
			
			// error handling: REF partitioning has not partition details
			if(this.tPartitions.size()>0){
				return this.createGenericCompileErr("Reference partitioning does not support partitioning specifications!");
			}
			
			String refTableName = null;
			int attIdx = 0;
			for (TokenAttribute tPartAtt : this.tPartAttributes) {
				TokenAttribute tr = this.tRefPartAttributes.get(attIdx++);
				String newRefTableName = tr.getTable().getName().getValue();

				// error handling: duplicate partition attributes
				if (tPartAtts.contains(tPartAtt)) {
					return Catalog.createPartitionContainsDupAttErr(
							this.table.getName(), tPartAtt.getName().getValue());
				}
				// error handling: partition attribute not in table
				if (!this.tAttributes.contains(tPartAtt)) {
					return Catalog.createPartitionContainsWrongArgumentAttErr(
							this.table.getName(), tPartAtt.getName().getValue());
				}
				// error handling: referenced table must be same for all cases
				if (refTableName != null
						&& !newRefTableName.equalsIgnoreCase(refTableName)) {
					return this
							.createGenericCompileErr("Partitioning specification failure: Only one table can be used as reference");
				} else {
					refTableName = newRefTableName;
				}
				
				// error handling: referenced table does not exist
				TokenTable tRefTable = new TokenTable(refTableName);
				Table refTable = Catalog.getTable(tRefTable.hashKey(this.schema
						.getOid()));
				if (refTable == null) {
					return Catalog.createObjectNotExistsErr(newRefTableName,
							EnumDatabaseObject.TABLE);
				}

				// error handling: referenced attribute does not exist
				Attribute att = attributes
						.get(tPartAtt.getName().getValue());
				Attribute referenceAtt = Catalog.getAttribute(tr
						.hashKey(refTable.getOid()));
				if (referenceAtt == null) {
					return Catalog.createPartitionContainsWrongArgumentAttErr(
							tr.getTable().getName().getValue(), tPartAtt.getName()
									.getValue());
				}

				// add partition to table object
				PartitionAttribute partAtt = new PartitionAttribute(
						att.getOid(), referenceAtt.getOid());
				this.partitionAttributes.add(partAtt);
				this.table.addPartitionAttribute(partAtt);
				this.table.setRefTableOid(refTable.getOid());

				tPartAtts.add(tPartAtt);
			}
		}

		// initialize table partitions: HASH 
		if (this.table.getPartitionType().isHash()) {
			TokenIdentifier tPartition = null;
			for (int i = 0; i < this.tPartitions.size(); i++) {

				tPartition = this.tPartitions.get(i);
				if (this.partitions.containsKey(tPartition.getValue())) {
					return this
							.createGenericCompileErr("Partitions must have unique names. Non unique partition name: "
									+ tPartition.getValue());
				}
				Partition part = new Partition(this.table.getOid(),
						tPartition.getValue());

				// put partition into hash map and add to table
				this.partitions.put(part.getName(), part);

				// get connections for partition
				List<TokenIdentifier> tconnections = this.tPartToConnection
						.get(this.tPartitions.get(i).getValue());
				
				// realize n to m Relationship of partitions to connections
				for (TokenIdentifier tokenIdentifier : tconnections) {
					// get connection for this partition
					Connection conn = Catalog.getConnection(tokenIdentifier
							.hashKey());
					if (conn == null) {
						return Catalog.createObjectNotExistsErr(
								tokenIdentifier.getValue(),
								EnumDatabaseObject.CONNECTION);
					}

					this.partToConnections.add(new PartitionToConnection(
							part.getOid(), conn.getOid()));
					part.addConnection(conn);
				}

				this.table.addPartition(part);
			}
		}
		// initialize table partitions: REF
		else if(this.table.getPartitionType().isReference()){
			Table refTable = Catalog.getTable(this.table.getRefTableOid());
			
			for(Partition refPart: refTable.getPartitions()){
				Partition part = new Partition(this.table.getOid(), refPart.getName());
				this.partitions.put(part.getName(), part);
				
				//add connections to part
				for(Connection conn: part.getConnections()){
					part.addConnection(conn);
					this.partToConnections.add(new PartitionToConnection(
							part.getOid(), conn.getOid()));
				}
				
				//add part to table
				this.table.addPartition(part);
			}
		}

		return lastError;
	}

	/**
	 * Initialize attributes of table object
	 * 
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
		for (PartitionAttribute partAtt : this.partitionAttributes) {
			lastError = Catalog.createPartitionAttribute(partAtt);
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
