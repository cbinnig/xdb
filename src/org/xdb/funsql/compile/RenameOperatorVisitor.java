package org.xdb.funsql.compile;

import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.SimpleAggregation;
import org.xdb.funsql.compile.operator.TableOperator;

/**
 * Renames the operators and attributes for code execution
 * 
 * @author lschmidt
 * 
 */
public class RenameOperatorVisitor implements ITreeVisitor {

	/* (non-Javadoc)
	 * @see org.xdb.funsql.compile.ITreeVisitor#visitEquiJoin(org.xdb.funsql.compile.operator.EquiJoin)
	 */
	@Override
	public void visitEquiJoin(EquiJoin ej) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xdb.funsql.compile.ITreeVisitor#visitGenericSelection(org.xdb.funsql.compile.operator.GenericSelection)
	 */
	@Override
	public void visitGenericSelection(GenericSelection gs) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xdb.funsql.compile.ITreeVisitor#visitFunctionCall(org.xdb.funsql.compile.operator.FunctionCall)
	 */
	@Override
	public void visitFunctionCall(FunctionCall fc) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xdb.funsql.compile.ITreeVisitor#visitSimpleAggregation(org.xdb.funsql.compile.operator.SimpleAggregation)
	 */
	@Override
	public void visitSimpleAggregation(SimpleAggregation sa) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xdb.funsql.compile.ITreeVisitor#visitGenericProjection(org.xdb.funsql.compile.operator.GenericProjection)
	 */
	@Override
	public void visitGenericProjection(GenericProjection gp) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.xdb.funsql.compile.ITreeVisitor#visitTableOperator(org.xdb.funsql.compile.operator.TableOperator)
	 */
	@Override
	public void visitTableOperator(TableOperator to) {
		// TODO Auto-generated method stub

	}

}
