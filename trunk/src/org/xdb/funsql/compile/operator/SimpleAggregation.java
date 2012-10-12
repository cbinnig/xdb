package org.xdb.funsql.compile.operator;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.funsql.compile.ITreeVisitor;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

public class SimpleAggregation extends AbstractUnaryOperator {

	private static final long serialVersionUID = 3800517017256774443L;
	
	private Vector<TokenAttribute> groupAttributes;
	private Vector<TokenAttribute> aggAttributes;
	private Vector<EnumAggregation> aggTypes;
	
	private final StringTemplate sqlTemplate = 
			new StringTemplate("SELECT <AGG_ATTRS>,<GROUP_ATTRS> FROM <<OP1>> AS <OP1>"+
					"GROUP BY <GROUP_ATTRS>");
	
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
		assert(aggAttributes.size() == aggTypes.size());
		
		//generate aggregation attribute list
		final StringBuffer builder = new StringBuffer();
		for(int i = 0; i < aggAttributes.size(); i++) {
			final int k = i; //needed for the anonymous class to work...
			if(i != 0)
				builder.append(",");
			
			builder.append(aggTypes.get(i).getSqlRepresentation().toString(
							new HashMap<String, String>() {{
								put("EXP", aggAttributes.get(k).toSqlString());
							}}
			));
		}
		
		
		return sqlTemplate.toString(new HashMap<String, String>() {{
			put("OP1", getChild().getOperatorId().toString());
			
			put("AGG_ATTRS", builder.toString());
			put("GROUP_ATTRS", SetUtils.stringifyAttrVec(groupAttributes));
		}});
	}

	@Override
	public boolean isPushDownAllowed(EnumPushDown pd) {
		switch(pd){
		case SELECTION_PUSHDOWN:
		case STOP_PUSHDOWN:
			return true;
		default:
			return false;
		}
	}

	@Override
	void accept(ITreeVisitor v) {
		v.visitSimpleAggregation(this);
	}
}
