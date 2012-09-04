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
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;

public class CreateTableStmt extends AbstractServerStmt {
	private TokenTable tTable;
	private TokenTable tSourceTable;
	private TokenIdentifier tConnection;

	private Vector<TokenAttribute> tAttributes = new Vector<TokenAttribute>();
	private Vector<TokenDataType> tDataTypes = new Vector<TokenDataType>();

	private Connection connection = null;
	private Schema schema = null;
	private Table table = null;
	private HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();

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
		String connectionKey = this.tConnection.hashKey();
		this.connection = Catalog.getConnection(connectionKey);
		if (this.connection == null) {
			return Catalog.createObjectNotExistsErr(this.tConnection.getName(),
					EnumDatabaseObject.CONNECTION);
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
		
		//check table
		this.table = new Table(this.tTable.getName().toString(),
				this.tSourceTable.getName().toString(), 
				this.tSourceTable.getSchema().getName().toString(),
				this.schema.getOid(), this.connection.getOid());
		Error lastError = this.table.checkObject();
		if(lastError!=Error.NO_ERROR)
			return lastError;
				
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
		
		return Error.NO_ERROR;
	}
}
