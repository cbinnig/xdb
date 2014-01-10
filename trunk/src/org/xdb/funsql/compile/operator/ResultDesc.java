package org.xdb.funsql.compile.operator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.Config;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.types.EnumSimpleType;

/**
 * The ResultDesc represents a number of attributes and their types which belong
 * to an operator in a compile plan.
 * 
 * @author
 * 
 */
public class ResultDesc implements Serializable, Cloneable {
	private static final long serialVersionUID = 8819533773334264233L;

	// attribute
	private Vector<TokenAttribute> attributes;// attributes
	private Vector<EnumSimpleType> types; // types

	private boolean materialize = false;
	private boolean repartition = false;
	private PartitionDesc partDesc = null;
	protected int partitionCnt = 1;

	// constructors
	public ResultDesc() {
		this.attributes = new Vector<TokenAttribute>();
		this.types = new Vector<EnumSimpleType>();
	}

	public ResultDesc(int size) {
		this.attributes = new Vector<TokenAttribute>(size);
		this.types = new Vector<EnumSimpleType>(size);
	}

	// getters and setters
	public void setAttribute(int i, TokenAttribute attribute) {
		this.attributes.add(i, attribute);
	}

	public void addAttribute(TokenAttribute attribute) {
		this.attributes.add(attribute);
	}

	public int getNumAttributes() {
		return this.attributes.size();
	}

	public TokenAttribute getAttribute(int i) {
		return this.attributes.get(i);
	}

	public Vector<TokenAttribute> getAttributes() {
		return this.attributes;
	}

	public void addType(EnumSimpleType type) {
		this.types.add(type);
	}

	public void setType(int i, EnumSimpleType type) {
		this.types.add(i, type);
	}

	public EnumSimpleType getType(int i) {
		return this.types.get(i);
	}

	public Vector<EnumSimpleType> getTypes() {
		return this.types;
	}
	
	public boolean materialize() {
		return materialize;
	}

	public void materialize(boolean materialize) {
		this.materialize = materialize;
	}

	public boolean repartition() {
		return this.repartition;
	}

	public void repartition(boolean repartition) {
		this.repartition = repartition;
	}

	public void setPartitionDesc(PartitionDesc partDesc) {
		this.partDesc = partDesc;
	}

	public PartitionDesc getRePartitionDesc() {
		return this.partDesc;
	}

	public int getPartitionCount() {
		return this.partitionCnt;
	}

	public void setPartitionCount(int cnt) {
		this.partitionCnt = cnt;
	}

	// methods
	public Map<AbstractToken, EnumSimpleType> createAttribute2TypeMap() {
		Map<AbstractToken, EnumSimpleType> exprTypes = new HashMap<AbstractToken, EnumSimpleType>();
		for(int i=0; i<this.attributes.size(); ++i){
			exprTypes.put(this.attributes.get(i), this.types.get(i));
		}
		return exprTypes;
	}
	
	public String toSqlString() {
		StringBuffer tableBuffer = new StringBuffer(AbstractToken.LBRACE);
		for (int i = 0; i < getNumAttributes(); i++) {
			if (i != 0) {
				tableBuffer.append(AbstractToken.COMMA);
				tableBuffer.append(AbstractToken.BLANK);
			}

			tableBuffer.append(getAttribute(i).getName().toSqlString());
			tableBuffer.append(AbstractToken.BLANK);
			tableBuffer.append(getType(i));
		}

		tableBuffer.append(AbstractToken.RBRACE);
		// tableBuffer.append(" ENGINE=MEMORY ");
		if (this.repartition) {
			tableBuffer.append(AbstractToken.BLANK);
			tableBuffer.append(this.partDesc.toSqlString());
		}

		return tableBuffer.toString();
	}

	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		if (Config.TRACE_COMPILE_PLAN_HEADER_RESULT_SCHEMA) {
			value.append("Attributes: ");
			value.append(this.attributes);
			value.append(AbstractToken.NEWLINE);
			value.append("Types: ");
			value.append(this.types);
			value.append(AbstractToken.NEWLINE);
		}

		if (Config.TRACE_COMPILE_PLAN_HEADER_RESULT_PARTITIONING) {
			value.append("Partitions: ");
			value.append(this.partitionCnt);
			if (this.repartition && this.partDesc != null) {
				value.append(AbstractToken.NEWLINE);
				value.append("Re-Partitioning: ");
				value.append(this.partDesc);
				value.append(AbstractToken.NEWLINE);
			}
		}
		return value.toString();
	}

	@Override
	public ResultDesc clone() {
		ResultDesc rDesc = new ResultDesc();
		for (TokenAttribute att : this.attributes) {
			TokenAttribute newAtt = new TokenAttribute(att.getName().clone());
			if (att.getTable() == null)
				System.err.println(att);
			else
				newAtt.setTable(att.getTable().getName().clone());
			rDesc.attributes.add(newAtt);
		}

		for (EnumSimpleType type : this.types) {
			rDesc.types.add(type);
		}
		return rDesc;
	}

	public static String createResultAtt(String table, String att) {
		StringBuffer name = new StringBuffer(table);
		name.append(AbstractToken.UNDERSCORE);
		name.append(att);
		return name.toString();
	}

	public int size() {
		return this.attributes.size();
	}
}
