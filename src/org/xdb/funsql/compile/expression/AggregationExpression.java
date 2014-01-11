package org.xdb.funsql.compile.expression;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xdb.funsql.compile.operator.EnumAggregation;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenStar;
import org.xdb.utils.StringTemplate;

public class AggregationExpression extends AbstractExpression {
	
	private static final long serialVersionUID = -2944669612051753256L;
	private EnumAggregation agg = EnumAggregation.NO_AGG;
	private AbstractExpression expr;
	
	
	public AggregationExpression(AggregationExpression toCopy){
		this();
		
		this.agg = toCopy.agg;
		this.expr =  toCopy.expr.deepCopy();
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
	public static AggregationExpression createCountExpr(){
		AggregationExpression countExpr = new AggregationExpression();
		countExpr.setAggregation(EnumAggregation.CNT);
		countExpr.setExpression(new SimpleExpression(new TokenStar()));
		return countExpr;
	}
	
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
	public Set<AggregationExpression> getAggregations() {
		Set<AggregationExpression> aggExprs = new HashSet<AggregationExpression>();
		aggExprs.add(this);
		return aggExprs;
	}
	
	@Override
	public String toSqlString() {
		String sqlValue = this.expr.toSqlString();
		if(this.agg == EnumAggregation.NO_AGG){
			return sqlValue;
		}
		
		HashMap<String, String> vars = new HashMap<String, String>();
		vars.put(StringTemplate.KEY_EXP, sqlValue);
		return this.agg.getSqlTemplate().toString(vars);
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
	public AbstractExpression replaceAttribtues(
			Map<TokenIdentifier, AbstractExpression> exprs) {
		if(exprs.containsKey(this)){
			return exprs.get(this);
		}
		else{
			this.expr = this.expr.replaceAttribtues(exprs);
			return this;
		}
	}

	@Override
	public AbstractExpression replaceExpressions(
			Map<AbstractExpression, AbstractExpression> exprs) {
		this.expr = this.expr.replaceExpressions(exprs);
		return this;
	}
}
