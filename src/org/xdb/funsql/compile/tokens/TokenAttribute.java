package org.xdb.funsql.compile.tokens;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

/**
 * 
 * @author A.C.Mueller, Erfan Zamanian
 *
 */
public class TokenAttribute extends AbstractTokenOperand{
	private static final long serialVersionUID = -846135086164968356L;
	
	// attributes
	private TokenIdentifier name;
	private TokenTable table = null;
	
	// constructors
	public TokenAttribute() {
		super(EnumOperandType.ATTRIBUTE);
	}
	
	public TokenAttribute(TokenAttribute toCopy){
		super(EnumOperandType.ATTRIBUTE);
		this.name = new TokenIdentifier(toCopy.name);
		if(toCopy.table!= null){
			this.table = new TokenTable(toCopy.table);
		}

	}
	
	public TokenAttribute(String name) {
		this();
		this.name = new TokenIdentifier(name);
	}
	
	public TokenAttribute(String table, String name) {
		this(name);
		this.table = new TokenTable(table);
	}
	
	public TokenAttribute(TokenIdentifier name) {
		this();
		this.name = name;
	}
	
	// getters and setters
	public TokenIdentifier getName() {
		return name;
	}
	
	public TokenTable getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = new TokenTable(table);
	}
	
	public void resetTable(){
		this.table = null;
	}
	
	public void setTable(TokenIdentifier table) {
		this.table = new TokenTable(table);
	}
	
	public void setName(String name) {
		this.name = new TokenIdentifier(name);
	}
	
	public void setTable(TokenTable table) {
		this.table = table;
	}
	
	public void setName(TokenIdentifier name) {
		this.name = name;
	}
	
	//helper methods
	public boolean renameAttribute(Map<String, String> renamedAttributes){
		if (renamedAttributes.containsKey(this.getName().getValue())) {
			String newName = renamedAttributes.get(this.getName().getValue());
			this.setName(newName);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	@Override
	public String toSqlString() {
		StringBuffer buffer = new StringBuffer();
		if (this.table != null) {
			buffer.append(this.table.getName().toString());
			buffer.append(AbstractToken.DOT);
		}
		
		buffer.append(this.name.toString());
		
		return buffer.toString();
	}
	
	@Override
	public boolean isAttribute() {
		return true;
	}
	
	@Override
	public boolean isLiteral() {
		return false;
	}
	
	@Override
	public TokenAttribute clone(){
		TokenAttribute att = new TokenAttribute(this);
		return att;
	}
	
	public String hashKey(){
		StringBuffer key = new StringBuffer();
		key.append(this.name.hashKey());
		return key.toString();
	}
	
	public String hashKey(long tableOid){
		StringBuffer key = new StringBuffer();
		key.append(tableOid);
		key.append(AbstractToken.DOT);
		key.append(this.name.hashKey());
		return key.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TokenAttribute)) return false;
		TokenAttribute ta = (TokenAttribute) o;
		boolean namesIdentical = this.getName().getValue().equals(ta.getName().getValue());
		
		// Since the "compile" method of "CreateTableStmt" calls this function without setting the table name, we should be ready for that.
		if (this.getTable() == null || ta.getTable() == null){
			return namesIdentical;
		}
		else {
			boolean tableNamesIdentical = this.getTable().getName().equals(ta.getTable().getName());
			return namesIdentical && tableNamesIdentical;
			//return namesIdentical;
		}
	}
	
	@Override
	// hashCode is overridden in order to map equal TokenAttributes to the same bucket.
	public int hashCode() {
		int hash =  this.getName().getValue().hashCode();
		return hash;
	}
	

	/**
	 * Renames all attributes
	 * @param atts
	 * @param newTable
	 * @param renameMap
	 */
	public static void rename(Collection<TokenAttribute> atts, String newTable, Map<TokenIdentifier,TokenIdentifier> renameMap){
		for(TokenAttribute att: atts){
			att.getTable().setName(newTable);
			att.setName(renameMap.get(att.getName()));
		}
	}
	
	/**
	 * Renames all attributes in a given list using a new table name
	 * @param atts
	 * @param tableName
	 */
	public static void renameTable(Collection<TokenAttribute> atts, String newTable){
		for(TokenAttribute att: atts){
			att.getTable().setName(newTable);
		}
	}
	
	/**
	 * Renames all attributes in a given list using a new table name
	 * @param atts
	 * @param tableName
	 */
	public static void renameTable(Collection<TokenAttribute> atts, String oldTable, String newTable){
		for(TokenAttribute att: atts){
			if(att.getTable().getName().toString().equals(oldTable))
				att.getTable().setName(newTable);
		}
	}
	
	/**
	 * clones a list of attributes
	 * @param atts
	 * @return
	 */
	public static Collection<TokenAttribute> clone(Collection<TokenAttribute> atts){
		Vector<TokenAttribute> newAtts = new Vector<TokenAttribute>(atts.size());
	
		for(TokenAttribute att: atts){
			newAtts.add(new TokenAttribute(att));
		}
		return newAtts;
	}

	
	
}
