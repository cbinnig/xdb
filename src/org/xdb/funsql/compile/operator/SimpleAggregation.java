package org.xdb.funsql.compile.operator;

import java.util.Vector;

import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class SimpleAggregation extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;
	
	private Vector<TokenAttribute> groupAttributes;
	private Vector<TokenAttribute> aggAttributes;
	private Vector<EnumAggregation> aggTypes;
	
	//constructors
	public SimpleAggregation(AbstractOperator child, int size) {
		super(child);
		
		this.groupAttributes = new Vector<TokenAttribute>(size);
		this.aggAttributes = new Vector<TokenAttribute>(size);
		this.aggTypes = new Vector<EnumAggregation>(size);
		
		this.type = EnumOperator.SIMPLE_AGGREGATION;
	}
	
	//getters and setters
	public void setGroupAttribute(int i, TokenAttribute attribute){
		this.groupAttributes.set(i, attribute);
	}
	
	public TokenAttribute getGroupAttribute(int i){
		return this.groupAttributes.get(i);
	}

	public Vector<TokenAttribute> getGroupAttributes() {
		return groupAttributes;
	}
	
	public void setAggregationAttribute(int i, TokenAttribute attribute){
		this.aggAttributes.set(i, attribute);
	}
	
	public TokenAttribute getAggregationAttribute(int i){
		return this.groupAttributes.get(i);
	}

	public Vector<TokenAttribute> getAggregationAttributes() {
		return this.aggAttributes;
	}
	
	public void setAggregationType(int i, EnumAggregation type){
		this.aggTypes.set(i, type);
	}
	
	public EnumAggregation getAggregationType(int i){
		return this.aggTypes.get(i);
	}

	public Vector<EnumAggregation> getAggregationTypes() {
		return this.aggTypes;
	}
	
	@Override
	public String toSqlString() {
		// TODO: generate sql
		return null;
	}

	@Override
	void accept(TreeVisitor v) {
		v.visitSimpleAggregation(this);
	}
}
