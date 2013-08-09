package org.xdb.funsql.statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenDataType;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenReferencePartition;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.funsql.parallelize.PartitionAttributeSet;
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
	private TokenTable tTable;

	private Vector<TokenAttribute> tAttributes = new Vector<TokenAttribute>();
	private Vector<TokenDataType> tDataTypes = new Vector<TokenDataType>();

	private boolean partioned = false;
	private long partitionCount = 0;
	private EnumPartitionType partitionType;
	private Long refTableOid = null;

	private Vector<TokenAttribute> tpartitionAttributes = new Vector<TokenAttribute>();
	private Vector<TokenReferencePartition> referencePartitionTokens = new Vector<TokenReferencePartition>();
	private Vector<TokenTable> tpartitionTables = new Vector<TokenTable>();
	private Vector<TokenIdentifier> tConnections = new Vector<TokenIdentifier>();
	private Vector<Connection> connections = new Vector<Connection>();
	private Vector<PartitionToConnection>  partToConnections = new Vector<PartitionToConnection>();

	private HashMap<String,List<TokenIdentifier>>tpartitionToConnection = new HashMap<String, List<TokenIdentifier>>();
	private Schema schema = null;
	private Table table = null;
	
	private Vector<PartitionAttributes> partitionAttributes = new Vector<PartitionAttributes>();
	private HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
	private HashMap<String, Partition> partitions = new HashMap<String, Partition>();

	//Constructors
	public CreateTableStmt() {
		this.statementType = EnumStatement.CREATE_TABLE;
	}

	public CreateTableStmt(String table) {
		this();
		this.tTable = new TokenTable(table);
	}

	//getters and setters

	public void addPartition(String partition){
		this.tpartitionTables.add(new TokenTable(partition));
	}

	public void setPartitionType(String type){
		this.partitionType = EnumPartitionType.valueOf(type);
	}

	public void addPartitionColumn(String column){
		partitionCount++;
		if (this.partitionType.equals(EnumPartitionType.HASH))
			this.tpartitionAttributes.add(new TokenAttribute(column));
		else if (this.partitionType.isReferencePartition()){
			// TODO Erfan: For now, we expect the input to be in the following format:
			// partitionCol___referenceTable__partitionTable
			// it's dirty, but it's function since I fix the parser
			int i1 = column.indexOf("___");
			String partitionColumn = column.substring(0, i1);
			String referenceInfo = column.substring(i1+3);
			int i2 = referenceInfo.indexOf("__");
			String referenceTable = referenceInfo.substring(0, i2);
			String referenceColumn = referenceInfo.substring(i2+2);
			TokenTable tTableRef = new TokenTable(referenceTable);
			String schemaId = tTableRef.getSchema().hashKey();			
			String refTableName = (new TokenTable(referenceTable)).hashKey(Catalog.getSchema(schemaId).getOid());
			
			if (this.refTableOid == null)
				// This is the first reference, therefore needs to be set.
				this.refTableOid = Catalog.getTable( refTableName).getOid();
			else // the current reference table must be the same as previous references
				if (! Catalog.getTable( refTableName).getOid().equals(this.refTableOid))
					// TODO: Error handling
					;
			this.referencePartitionTokens.add(
					new TokenReferencePartition(
							this.tTable,
							new TokenAttribute(partitionColumn),
							new TokenTable(referenceTable),
							new TokenAttribute(referenceColumn)));
		}		
	}

	public void addPConnection(String partition, TokenIdentifier conn){
		List<TokenIdentifier> values = this.tpartitionToConnection.get(partition);

		if(values == null){
			values = new ArrayList<TokenIdentifier>();
		}
		if(! values.contains(conn)){
			values.add(conn);
			this.tpartitionToConnection.put(partition, values);
		}else {
			//TODO error handling
		}

	}
	public void addAttribute(String attribute) {
		this.tAttributes.add(new TokenAttribute(attribute));
	}

	public void addAttribute(TokenAttribute attribute) {
		this.tAttributes.add(attribute);
	}

	public void addConnection(TokenIdentifier ti){
		this.tConnections.add(ti);
	}

	public void addConnection(String connec){
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
	/*
	public void setSourceTable(TokenTable tSourceTable) {
		this.tSourceTable = "MUST_BE_FIXED";
	}
	 */

	@Override
	public Error compile() {
		if(this.partitionType!=null) 
			this.partioned = true;

		//check if schema exists
		String schemaKey = this.tTable.getSchema().hashKey();
		this.schema = Catalog.getSchema(schemaKey);
		if (this.schema == null) {
			return Catalog.createObjectNotExistsErr(schemaKey,
					EnumDatabaseObject.SCHEMA);
		}

		//check if table with same name already exists
		this.table = Catalog.getTable(this.tTable.hashKey(this.schema.getOid()));
		if(table != null){
			return Catalog.createObjectAlreadyExistsErr(table);
		}

		// init error
		Error lastError;
		//check which table type partitioned or not partioned
		//call related constructor
		
		this.table = new Table(this.tTable.getName().toString(), this.schema.getOid());
		this.table.setPartioned(this.partioned);
		
		if (! this.partioned) {
			//not partitioned, then call standard constructor for table
			String connectionKey;
			for (TokenIdentifier cti : this.tConnections){
				connectionKey = cti.hashKey();
				Connection connection = Catalog.getConnection(connectionKey);
				this.connections.add(connection);
				if (connection == null) {
					return Catalog.createObjectNotExistsErr(cti.getName(),
							EnumDatabaseObject.CONNECTION);
				}
			}
			
			
			// add connections to Table
			this.table.addConnections(this.connections);
			lastError = this.table.checkObject();
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}

		//check attributes
		for(int i=0; i<this.tAttributes.size(); ++i){
			TokenAttribute tAttribute = this.tAttributes.get(i);
			TokenDataType tDataType = this.tDataTypes.get(i);

			String attName = tAttribute.getName().toString();
			if(this.attributes.containsKey(attName)){
				return Catalog.createTableContainsDupAttErr(this.table.getName(), attName);
			}

			if(tDataType==null){
				return EnumSimpleType.createTypeNotExistsErr(tAttribute.getName().toString());
			}

			Attribute attribute = new Attribute(tAttribute.getName().toString(), tDataType.getDataType() , this.table.getOid());
			attributes.put(attName, attribute);

			this.table.addAttributes(attributes.values());

			lastError = attribute.checkObject();
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}
		
		if(this.partioned){
			//partitioned call partition table constructor
			this.table.setPartitionCount(this.partitionCount);
			this.table.setPartitionType(this.partitionType);
			this.table.setRefTableOid(this.refTableOid);

			Vector<TokenAttribute> scannedpartitionAttributes = new Vector<TokenAttribute>();
			//build up Partition Details

			if (this.table.getPartitionType().equals(EnumPartitionType.HASH)){
				for(TokenAttribute ta : this.tpartitionAttributes){
					//if duplicate throw error
					if(scannedpartitionAttributes.contains(ta)){
						return Catalog.createPartitionContainsDupAttErr(this.table.getName(), ta.getName().getName());
					}

					if(	!this.tAttributes.contains(ta)){
						return Catalog.createPartitionContainsDupAttErr(this.table.getName(), ta.getName().getName());
					}
					Attribute partitionAtt = attributes.get(ta.getName().getName());
					PartitionAttributes partAtts = new PartitionAttributes(partitionAtt.getOid(), null);
					
					this.partitionAttributes.add(partAtts);
					this.table.addPartitionAttribute(partAtts);
					
					scannedpartitionAttributes.add(ta);
				}
			}
			else if (this.table.getPartitionType().isReferencePartition()){
				for(TokenReferencePartition tr : this.referencePartitionTokens){
					//if duplicate throw error
					if(scannedpartitionAttributes.contains(tr.getPartitionAttribute())
							|| !this.tAttributes.contains(tr.getPartitionAttribute())){
						return Catalog.createPartitionContainsDupAttErr(
								tr.getPartitionTable().getName().toString(),
								tr.getPartitionAttribute().getName().toString());
					}
					Attribute partitionAtt = attributes.get(tr.getPartitionAttribute().getName().getName());
					Attribute referenceAtt = Catalog.getAttribute(tr.getReferenceTable().getName().getName(),
							tr.getReferenceAttribute().getName().getName());
					
					HashMap<String, Attribute> attributesByName = Catalog.attributesByName;

					PartitionAttributes partAtts = new PartitionAttributes(partitionAtt.getOid(), referenceAtt.getOid());
					this.partitionAttributes.add(partAtts);
					this.table.addPartitionAttribute(partAtts);

					scannedpartitionAttributes.add(tr.getPartitionAttribute());
				}
			}
			
			//check Error
			lastError = this.table.checkObject();
			if(lastError!=Error.NO_ERROR)
				return lastError;

			//check table Partition
			Partition newPart =null;
			TokenTable  partitionTable = null;
			Connection tempConnection;
			for(int i = 0; i <  this.tpartitionTables.size(); i ++){

				partitionTable = this.tpartitionTables.get(i);
				if(this.partitions.containsKey(partitionTable.getName().toString())){
					return Catalog.createTableContainsDupAttErr(this.table.getName(), partitionTable.getName().toString());
				}
				newPart = new Partition(this.table.getOid(), partitionTable.getName().toString());

				//put on hashmap and add to table
				this.partitions.put(partitionTable.getName().toString(), newPart);

				//get connections for partition

				List<TokenIdentifier> tconnections = this.tpartitionToConnection.get(this.tpartitionTables.get(i).getName());
				// realize n to m Relationship of partitions to connections
				for (TokenIdentifier tokenIdentifier : tconnections) {
					//get connection for this partition
					tempConnection = Catalog.getConnection(	tokenIdentifier.hashKey());
					if (tempConnection == null) {
						return Catalog.createObjectNotExistsErr(tokenIdentifier.getName(),
								EnumDatabaseObject.CONNECTION);
					}

					this.partToConnections.add(new PartitionToConnection(newPart.getOid(), tempConnection.getOid()));
					newPart.addConnection(tempConnection);
				}

				this.table.addPartition(newPart);

			}
		}

		return Error.NO_ERROR;
	}

	@Override
	public Error execute() {
		//add table
		Error lastError = Catalog.createTable(table);
		if(lastError!=Error.NO_ERROR)
			return lastError;

		//add attributes
		for(Attribute attribute: this.attributes.values()){
			lastError = Catalog.createAttribute(attribute);
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}
		
		//add partition
		for(Partition partition: this.partitions.values()){
			lastError = Catalog.createPartition(partition);
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}
		
		//add partitionAttributes
		for(PartitionAttributes partAtts: this.partitionAttributes){
			lastError = Catalog.createPartitionAttributes(partAtts);
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}
		
		// Partition to Connection
		for(PartitionToConnection partoCo : this.partToConnections){
			lastError = Catalog.createPartitionToConnection(partoCo);
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}

		//add Table To Connection
		TableToConnection taToCo = null;
		for(Connection connection: this.connections){
			taToCo = new TableToConnection(table.getOid(), connection.getOid());
			lastError = Catalog.createTableToConnection(taToCo);
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}

		return Error.NO_ERROR;
	}
}
