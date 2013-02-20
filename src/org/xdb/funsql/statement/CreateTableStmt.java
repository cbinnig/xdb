package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.HashMap;
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
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;

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
	private Vector<TokenIdentifier> tpartitionConnections = new Vector<TokenIdentifier>();

	private Connection connection = null;
	private Schema schema = null;
	private Table table = null;
	private HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
	private HashMap<String, Partition> partitions = new HashMap<String, Partition>();

	//Constructors
	public CreateTableStmt() {
		this.statementType = EnumStatement.CREATE_TABLE;
	}
	
	public CreateTableStmt(String table, String sourceSchema,
			String sourceTable, String connection) {
		this();
		this.tTable = new TokenTable(table);
		this.tSourceTable = new TokenTable(sourceTable);
		this.tConnection = new TokenIdentifier(connection);
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
	
	public void addConnection(TokenIdentifier c2){
		this.tpartitionConnections.add(c2);
	}
	public void addAttribute(String attribute) {
		this.tAttributes.add(new TokenAttribute(attribute));
	}
	
	public void addAttribute(TokenAttribute attribute) {
		this.tAttributes.add(attribute);
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

	public void setConnection(TokenIdentifier tConnection) {
		this.tConnection = tConnection;
	}

	@Override
	public Error compile() {
		//check if connection exists
		if(this.partitionMethod!=null) this.partioned = true;
		//quick hack 
		if (this.tConnection!=null) {
		String connectionKey = this.tConnection.hashKey();
		this.connection = Catalog.getConnection(connectionKey);
		if (this.connection == null) {
			return Catalog.createObjectNotExistsErr(this.tConnection.getName(),
					EnumDatabaseObject.CONNECTION);
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
			//check Error
			lastError = this.table.checkObject();
			if(lastError!=Error.NO_ERROR)
				return lastError;
			
			//check table Partition
			Partition newPart =null;
			TokenTable  sourceTable = null;
			String connectionKey;
			Connection tempConnection;
			for(int i = 0; i <  this.tpartitionTables.size(); i ++){
				//get connection for this partition
				connectionKey = this.tpartitionConnections.get(i).hashKey();
				tempConnection = Catalog.getConnection(connectionKey);
				if (tempConnection == null) {
					return Catalog.createObjectNotExistsErr(this.tConnection.getName(),
							EnumDatabaseObject.CONNECTION);
				}
				
				// Partition was twice in so throw error
				sourceTable = this.tpartitionTables.get(i);
				if(this.partitions.containsKey(sourceTable.getName().toString())){
					return Catalog.createTableContainsDupAttErr(this.table.getName(), sourceTable.getName().toString());
				}
				newPart = new Partition(this.tSourceTable.getName().toString(),  this.tSourceTable.getSchema().getName().toString(), sourceTable.getName().toString(), this.table.getOid(), sourceTable.getName().toString(),tempConnection.getOid());
		
				//put on hashmap and add to table
				this.partitions.put(sourceTable.getName().toString(), newPart);
				this.table.addPartition(newPart);
			}
		}else {
			//not partioned so call standard constructor for table
			this.table = new Table(this.tTable.getName().toString(),
					this.tSourceTable.getName().toString(), 
					this.tSourceTable.getSchema().getName().toString(),
					this.schema.getOid(), this.connection.getOid());
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
		
		for(Partition partition: this.partitions.values()){
			lastError = Catalog.createPartition(partition);
			if(lastError!=Error.NO_ERROR)
				return lastError;
		}
		
		return Error.NO_ERROR;
	}
}
