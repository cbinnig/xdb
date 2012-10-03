package org.xdb.funsql.compile.checks;

import org.xdb.funsql.compile.TreeVisitor;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.EquiSelection;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.SimpleAggregation;
import org.xdb.funsql.compile.operator.SimpleProjection;
import org.xdb.funsql.compile.operator.TableOperator;

/**
 * CheckOperatorVisitor checks the Operators and their meta data
 * @author lschmidt
 *
 */
public class CheckOperatorVisitor implements TreeVisitor{

	@Override
	public void visitEquiJoin(EquiJoin ej) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEquiSelection(EquiSelection es) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFunctionCall(FunctionCall fc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSimpleAggregation(SimpleAggregation sa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitSimpleProjection(SimpleProjection sp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTableOperator(TableOperator to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitGenericSelection(GenericSelection es) {
		// TODO Auto-generated method stub
		
	}

}
