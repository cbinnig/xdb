package org.xdb.funsql.compile;

import java.util.Collection;
import java.util.Vector;

import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.EnumExprType;
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
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Table;

/**
 * aggregates information(=attributes) about the nodes(=operators) of a
 * CompilePlan
 * 
 * @author lschmidt
 */
public class CreateResultDescVisitor implements ITreeVisitor {

	/*
	 * Visiting the EquiJoin means to add the attributes of the operators
	 * children to the operator's ResultDesc. (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitEquiJoin(org.xdb.funsql.compile
	 * .operator.EquiJoin)
	 */
	@Override
	public void visitEquiJoin(EquiJoin ej) {
		int i = 0;
		// the number of ResultDescs has to be one
		Vector<TokenAttribute> ta = null;
		while (i < ej.getLeftChild().getResultNumber()) {
			ta.addAll(ej.getLeftChild().getResult(i).getAttributes());
			i++;
		}
		i = 0;
		while (i < ej.getRightChild().getResultNumber()) {
			ta.addAll(ej.getRightChild().getResult(i).getAttributes());
			i++;
		}
		this.setResultDesc(ta, ej);
	}

	/*
	 * No attribute reduction by using a 'where'-clause: Adds the attributes of
	 * the operator's children ('SourceOperators') to the operator's ResultDesc.
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitGenericSelection(org.xdb.funsql
	 * .compile.operator.GenericSelection)
	 */
	@Override
	public void visitGenericSelection(GenericSelection gs) {
		int k = 0;
		for (int i = 0; i < gs.getSourceOperators().size(); i++) {
			for (int j = 0; j < gs.getSourceOperators().get(i)
					.getResultNumber(); j++) {
				gs.setResult(k, gs.getSourceOperators().get(i).getResult(i));
				k++;
			}
		}
	}

	/*
	 * FunctionCall is the only Operator with eventually more than one ResultDesc
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitFunctionCall(org.xdb.funsql.compile
	 * .operator.FunctionCall)
	 */
	@Override
	public void visitFunctionCall(FunctionCall fc) {
		for(AbstractOperator source: fc.getSourceOperators()){
			Vector<TokenAttribute> ta = source.getResult(0).getAttributes();
			//TODO
			
		}
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
		Vector<TokenAttribute> tokenatts=sa.getGroupAttributes();
		for(int i= 0; i<sa.getAggregationAttributes().size();i++){
			TokenAttribute ta = new TokenAttribute();
			ta.setDataType(sa.getAggregationType(i).getType());
			ta.setName(sa.getAggregationType(i).getType().toString()+"_"+sa.getAggregationAttributes().get(i).getName());
			ta.setTable(sa.getAggregationAttributes().get(i).getTable());
			tokenatts.add(ta);
		}		
		this.setResultDesc(tokenatts, sa); 	
	}

	/*
	 * explicit selection of attributes (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitSimpleProjection(org.xdb.funsql
	 * .compile.operator.GenericProjection)
	 */
	@Override
	public void visitGenericProjection(GenericProjection gp) {
		AbstractExpression ae = null;
		Vector<TokenAttribute> ca = null;
		for (int i = 0; i < gp.getExpressions().size(); i++) {
			if (gp.getExpression(i).getType().equals(EnumExprType.SIMPLE_EXPRESSION)) {
				ae = gp.getExpression(i);
				ca.addAll(ae.getAttributes());
			} else {// ComplexExpression
				ae = gp.getExpression(i);
				ca.addAll(ae.getAttributes());// aggregated attributes of every
												// expression
				// TODO: reduced attributes (f.ex.: a+b)
			}
		}
		this.setResultDesc(ca, gp);

	}

	/*
	 * attributes from table have to be added to the result desc (non-Javadoc)
	 * 
	 * @see
	 * org.xdb.funsql.compile.TreeVisitor#visitTableOperator(org.xdb.funsql.
	 * compile.operator.TableOperator)
	 */
	@Override
	public void visitTableOperator(TableOperator to) {
		Collection<Attribute> ca = to.getTable().getAttributes();
		TokenTable tt = to.getTableName();
		Collection<TokenAttribute> cta = null;
		while (ca.iterator().hasNext()) {
			Attribute a = ca.iterator().next();
			TokenAttribute ta = new TokenAttribute();
			ta.setName(a.getName());
			ta.setName(a.getName());
			ta.setTable(tt);
			ta.setType(EnumOperandType.ATTRIBUTE);//is attribute
			ta.setDataType(a.getDataType());//has type string/integer/...
			cta.add(ta);
		}
		this.setResultDesc(cta, to);
	}


	/**
	 * Sets the ResultDesc of the AbstractOperator.
	 * 
	 * @param col
	 *            Collection<{@link TokenAttribute}>:TokenAttributes to be added
	 *            to the ResultDesc
	 * @param ao
	 *            {@link AbstractOperator}: the operator in this visitor which
	 *            the attributes have to be added to
	 */
	private void setResultDesc(Collection<TokenAttribute> col,
			AbstractOperator ao) {
		ResultDesc rd = new ResultDesc(col.size());
		int i = 0;
		for (TokenAttribute a : col) {
			rd.setAttribute(i, a);			
			TokenTable ttable = a.getTable();
			Table table = Catalog.getTable(ttable.toString());
			Attribute tempAtt = table.getAttribute(a.toSqlString());
			rd.setType(i, tempAtt.getDataType());
			i++;
		}
		ao.setResult(i, rd);
	}

	@Override
	public void visit(AbstractOperator absOp) {
		// TODO Auto-generated method stub
	}
}
