package org.xdb.funsql.compile;

import java.util.Collection;

import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.SimpleAggregation;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.EnumOperandType;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.metadata.Attribute;

/**
 * aggregates information(=attributes) about the nodes(=operators) of a CompilePlan
 * 
 * @author lschmidt
 */
public class AggregateInformationVisitor implements TreeVisitor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitEquiJoin(org.xdb.funsql.compile
	 * .operator.EquiJoin)
	 */
	@Override
	public void visitEquiJoin(EquiJoin ej) {
		// TODO Auto-generated method stub

	}

	/*
	 * no attribute reduction by using a 'where'-clause
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitGenericSelection(org.xdb.funsql
	 * .compile.operator.GenericSelection)
	 */
	@Override
	public void visitGenericSelection(GenericSelection gs) {
		int k= 0;
		for(int i = 0; i<gs.getSourceOperators().size(); i++){
			for(int j= 0; j<gs.getSourceOperators().get(i).getResultNumber(); j++){
				gs.setResult(k,gs.getSourceOperators().get(i).getResult(i));
				k++;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitFunctionCall(org.xdb.funsql.compile
	 * .operator.FunctionCall)
	 */
	@Override
	public void visitFunctionCall(FunctionCall fc) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitSimpleAggregation(org.xdb.funsql
	 * .compile.operator.SimpleAggregation)
	 */
	@Override
	public void visitSimpleAggregation(SimpleAggregation sa) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * explicit selection of attributes
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitSimpleProjection(org.xdb.funsql
	 * .compile.operator.GenericProjection)
	 */
	@Override
	public void visitGenericProjection(GenericProjection sp) {
		AbstractExpression ae = null;
		Collection<TokenAttribute> ca = null;
		for(int i=0; i< sp.getExpressions().size(); i++){
			ae = sp.getExpression(i);
			ca.addAll(ae.getAttributes());
		}				
		this.setResultDesc(ca, sp);

	}

	/*
	 * attributes from table have to be added to the result desc
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitTableOperator(org.xdb.funsql.
	 * compile.operator.TableOperator)
	 */
	@Override
	public void visitTableOperator(TableOperator to) {
		Collection<Attribute> ca = to.getTable().getAttributes();
		Collection<TokenAttribute> vAtts = null;
		while (ca.iterator().hasNext()) {
			Attribute a = ca.iterator().next();
			TokenAttribute ta = new TokenAttribute();
			ta.setName(a.getName());
			ta.setName(a.getName());
			ta.setTable(to.getTableName());
			ta.setType(EnumOperandType.ATTRIBUTE);
			vAtts.add(ta);
		}
		this.setResultDesc(vAtts, to);
	}

	/**
	 * sets the result desc of the abstractoperator
	 * @param col
	 * @param ao
	 */
	private void setResultDesc(Collection<TokenAttribute> col,
			AbstractOperator ao) {
		ResultDesc rd = new ResultDesc(col.size());
		int i = 0;
		for (TokenAttribute a : col) {
			rd.setAttribute(i, a);
			i++;
		}
		ao.setResult(i, rd);
	}
}
