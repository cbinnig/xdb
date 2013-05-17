package org.xdb.funsql.compile.expression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.xdb.funsql.compile.operator.EnumAggregation;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.utils.StringTemplate;

public class AggregationExpression extends AbstractExpression {
	
	private static final long serialVersionUID = -2944669612051753256L;
	private EnumAggregation agg = EnumAggregation.NO_AGG;
	private AbstractExpression expr;
	
	
	public AggregationExpression(AggregationExpression toCopy){
		
		this.expr =  expr.deepCopy();
	}
	
	public AggregationExpression(){
		super();
		
		this.type = EnumExprType.AGG_EXPRESSION;
	}
	
	//getters and setters
	public EnumAggregation getAggregation() {
		return agg;
	}

	public void setAggregation(String aggregation) {
		this.agg = EnumAggregation.valueOfSql(aggregation);
	}
	
	public void setAggregation(EnumAggregation aggregation) {
		this.agg = aggregation;
	}

	public AbstractExpression getExpression() {
		return expr;
	}

	public void setExpression(AbstractExpression expression) {
		this.expr = expression;
	}
	
	//methods
	
	@Override
	public Collection<TokenAttribute> getAttributes() {
		return this.expr.getAttributes();
	}

	@Override
	public boolean isAttribute() {
		if(this.agg == EnumAggregation.NO_AGG){
			return this.expr.isAttribute();
		}
		return false;
	}

	@Override
	public String toSqlString() {
		String sqlValue = this.expr.toSqlString();
		if(this.agg == EnumAggregation.NO_AGG){
			return sqlValue;
		}
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put(StringTemplate.KEY_EXP, sqlValue);
		return this.agg.getSqlRepresentation().toString(vars);
	}

	@Override
	public boolean isAggregation() {
		return true;
	}

	@Override
	public TokenAttribute getAttribute() {
		return null;
	}

	@Override
	public int size() {
		return 1;
	}
	
	@Override
	public AbstractExpression clone() {
		AggregationExpression expr = new AggregationExpression();
		if(this.expr!=null)
			expr.expr = this.expr.clone();
		expr.agg = this.agg;
		
		return expr;
	}

	@Override
	public AbstractExpression replaceExpressions(
			Map<TokenIdentifier, AbstractExpression> exprs) {
		
		this.expr = this.expr.replaceExpressions(exprs);
		return this;
		
	}
}
