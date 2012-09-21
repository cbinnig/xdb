package org.xdb.metadata;

import java.io.Serializable;

import org.xdb.Config;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;


public abstract class AbstractDatabaseObject implements Serializable {
	
	private static final long serialVersionUID = 6375185891980942689L;

	protected static final String METADATA_SCHEMA = AbstractToken.toSqlIdentifier(Config.METADATA_SCHEMA);
	protected static final String OID_NAME = AbstractToken.toSqlIdentifier(Config.METADATA_OID_NAME);
	
	protected Long oid;
	protected String name;
	protected EnumDatabaseObject objectType;
	
	protected AbstractDatabaseObject(){	
	}
	
	public AbstractDatabaseObject(Long oid, String name) {
		super();
		
		this.oid = oid;
		this.name = name;
	}
	
	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public EnumDatabaseObject getObjectType() {
		return objectType;
	}

	public void setObjectType(EnumDatabaseObject objectType) {
		this.objectType = objectType;
	}

	protected Error checkValueLength(String value, int maxLength){
		if(value.length()>maxLength){
			return Catalog.createObjectValueTooLongErr(value, this.objectType);
		}
		return Error.NO_ERROR;
	}
	
	
	protected String interalSqlDeleteAll(){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(AbstractToken.DELETE);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.FROM);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(METADATA_SCHEMA);
		selectSql.append(AbstractToken.DOT);
		selectSql.append(getTableName());
		return selectSql.toString();
	}
	
	protected String internalSqlSelectAll(){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(AbstractToken.SELECT);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(getAllAttributes());
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.FROM);
		selectSql.append(METADATA_SCHEMA);
		selectSql.append(AbstractToken.DOT);
		selectSql.append(getTableName());
		return selectSql.toString();
	}
	
	protected String internalSqlSelectMaxOid(){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(AbstractToken.SELECT);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.MAX);
		selectSql.append(AbstractToken.LBRACE);
		selectSql.append(OID_NAME);
		selectSql.append(AbstractToken.RBRACE);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.FROM);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(METADATA_SCHEMA);
		selectSql.append(AbstractToken.DOT);
		selectSql.append(getTableName());
		return selectSql.toString();
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
		selectSql.append(OID_NAME);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.EQUAL1);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(this.oid);
		return selectSql.toString();
	}
	
	public Error checkObject(){
		return this.checkValueLength(name, 255);
	}
	
	public abstract String sqlInsert();
	
	public abstract String getTableName();

	public abstract String getAllAttributes();
	
	public abstract String hashKey();
}
