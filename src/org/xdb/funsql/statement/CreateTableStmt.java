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
import org.xdb.metadata.Partition;
import org.xdb.metadata.PartitionToConnection;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;
import org.xdb.metadata.TableToConnection;

public class CreateTableStmt extends AbstractServerStmt {
	private TokenTable tTable;
	private TokenTable tSourceTable;
	private TokenIdentifier tConnection;

	private Vector<TokenAttribute> tAttributes = new Vector<TokenAttribute>();
	private Vector<TokenDataType> tDataTypes = new Vector<TokenDataType>();

	private String partitionMethod;
	private boolean partioned = false;
	
	private Vector<TokenAttribute> tpartitionAttributes = new Vector<TokenAttribute>();
	private Vector<TokenTable> tpartitionTables = new Vector<TokenTable>();
	private Vector<TokenIdentifier> tConnections = new Vector<TokenIdentifier>();
	private Vector<Connection> connections = new Vector<Connection>();
	private Vector<PartitionToConnection>  partToConnections = new Vector<PartitionToConnection>();
	
	private HashMap<String,List<TokenIdentifier>>tpartitionToConnection = new HashMap<String, List<TokenIdentifier>>();
	private Schema schema = null;
	private Table table = null;
	private HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
	private HashMap<String, Partition> partitions = new HashMap<String, Partition>();

	//Constructors
	public CreateTableStmt() {
		this.statementType = EnumStatement.CREATE_TABLE;
	}
	
	public CreateTableStmt(String table, String sourceSchema,
			String sourceTable) {
		this();
		this.tTable = new TokenTable(table);
		this.tSourceTable = new TokenTable(sourceTable);
	}

	//getters and setters
	
	public void addPartition(String partition){
		this.tpartitionTables.add(new TokenTable(partition));
	}
	
	public void setPartitionMethod(String method){
		this.partitionMethod=method;
	}
	
	public void addPartitionColumn(String column){
		this.tpartitionAttributes.add(new TokenAttribute(column));
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

	public TokenTable getSourceTable() {
		return tSourceTable;
	}

	public TokenIdentifier getConnection() {
		return tConnection;
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

	public void setSourceTable(TokenTable tSourceTable) {
		this.tSourceTable = tSourceTable;
	}


	@Override
	public Error compile() {
		//check if connection exists
		if(this.partitionMethod!=null) this.partioned = true;
		//quick hack 
		//not partioned build up Table To Connection
		if(! this.partioned){
			String connectionKey;
			for (TokenIdentifier cti : this.tConnections){
				connectionKey = cti.hashKey();
				Connection connection = Catalog.getConnection(connectionKey);
				this.connections.add(connection);
				if (connection == null) {
					return Catalog.createObjectNotExistsErr(this.tConnection.getName(),
							EnumDatabaseObject.CONNECTION);
				}
				
			}
		}

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
		//check which table type partioned or not partioned
		//call related constructor
		if(this.partioned){
			//partitioned call partion table constructor
			this.table = null;
			this.table = new Table(this.tTable.getName().toString(), 	this.tSourceTable.getName().toString(), this.tSourceTable.getSchema().getName().toString(), this.schema.getOid());
			this.table.setPartioned(true);
			this.table.setPartitionType(this.partitionMethod);
			
			Vector<TokenAttribute> scannedpartitionAttributes = new Vector<TokenAttribute>();
			//build up Partition Details
			String partdetails = "";
			for(TokenAttribute ta : this.tpartitionAttributes){
				//if duplicate throw error
				if(scannedpartitionAttributes.contains(ta)){
					return Catalog.createPartitionContainsDupAttErr(this.table.getName(), ta.getName().getName());
				}
				
				if(	!this.tAttributes.contains(ta)){
					return Catalog.createPartitionContainsDupAttErr(this.table.getName(), ta.getName().getName());
				}
				scannedpartitionAttributes.add(ta);
				
				partdetails = partdetails +	" " + ta.getName();
			}
			
			
			this.table.setPartitionDetails(partdetails.trim());
			//check Error
			lastError = this.table.checkObject();
			if(lastError!=Error.NO_ERROR)
				return lastError;
			
			//check table Partition
			Partition newPart =null;
			TokenTable  sourceTable = null;
			Connection tempConnection;
			for(int i = 0; i <  this.tpartitionTables.size(); i ++){
				
				sourceTable = this.tpartitionTables.get(i);
				if(this.partitions.containsKey(sourceTable.getName().toString())){
					return Catalog.createTableContainsDupAttErr(this.table.getName(), sourceTable.getName().toString());
				}
				newPart = new Partition(this.tSourceTable.getName().toString(),  this.tSourceTable.getSchema().getName().toString(), sourceTable.getName().toString(), this.table.getOid(), sourceTable.getName().toString());
		
				//put on hashmap and add to table
				this.partitions.put(sourceTable.getName().toString(), newPart);
				this.table.addPartition(newPart);
				
				//get connections for partition
				
				List<TokenIdentifier> tconnections = this.tpartitionToConnection.get(this.tpartitionTables.get(i).getName());
				// realize n to m Relationship of partitions to connections
				for (TokenIdentifier tokenIdentifier : tconnections) {
					//get connection for this partition
					tempConnection = Catalog.getConnection(	tokenIdentifier.hashKey());
					if (tempConnection == null) {
						return Catalog.createObjectNotExistsErr(this.tConnection.getName(),
								EnumDatabaseObject.CONNECTION);
					}
					
					this.partToConnections.add(new PartitionToConnection(newPart.getOid(), tempConnection.getOid()));
				}
			}
		}else {
			//not partioned so call standard constructor for table
			this.table = new Table(this.tTable.getName().toString(),
					this.tSourceTable.getName().toString(), 
					this.tSourceTable.getSchema().getName().toString(),
					this.schema.getOid());
			this.table.setPartioned(false);
			// add connections to Table
			this.table.addConnections(this.connections);
			lastError = this.table.checkObject();
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}
		
		//check table
		
				
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
