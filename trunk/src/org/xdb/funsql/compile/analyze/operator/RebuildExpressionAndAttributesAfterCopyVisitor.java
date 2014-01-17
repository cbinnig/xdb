package org.xdb.funsql.compile.analyze.operator;

import java.util.HashMap;
import java.util.Map;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.expression.RenameExpressionAfterCopyVisitor;
import org.xdb.funsql.compile.analyze.predicate.RebuildPredicatesAfterCopyVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;

/**
 * TODO: delete this class
 * 
 */
public class RebuildExpressionAndAttributesAfterCopyVisitor extends
		AbstractTopDownTreeVisitor {
	private Map<AbstractCompileOperator, AbstractCompileOperator> oldToNewMap;
	private Map<String, String> oldToNewIDMap;
	private Error error = new Error();

	public RebuildExpressionAndAttributesAfterCopyVisitor(
			AbstractCompileOperator root,
			Map<AbstractCompileOperator, AbstractCompileOperator> oldToNewMap) {
		super(root);

		this.oldToNewMap = oldToNewMap;
		oldToNewIDMap = new HashMap<String, String>();
		for (AbstractCompileOperator oldOp : oldToNewMap.keySet()) {
			oldToNewIDMap.put(oldOp.getOperatorId().toString(),
					this.oldToNewMap.get(oldOp).getOperatorId().toString());
		}
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {

		String leftName = oldToNewIDMap.get(ej.getLeftTokenAttribute()
				.getTable().getName().getValue());
		String rightName = oldToNewIDMap.get(ej.getRightTokenAttribute()
				.getTable().getName().getValue());
		// if no new name found it wasn't renamed so continue
		ej.getLeftTokenAttribute().getTable().setName(leftName);
		ej.getRightTokenAttribute().getTable().setName(rightName);
		return error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		return error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		RebuildPredicatesAfterCopyVisitor rebuildVisitor;
		rebuildVisitor = new RebuildPredicatesAfterCopyVisitor(
				gs.getPredicate(), oldToNewIDMap);
		rebuildVisitor.visit();

		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation ga) {
		RenameExpressionAfterCopyVisitor reACV;
		for (AbstractExpression expr : ga.getAggregationExpressions()) {
			reACV = new RenameExpressionAfterCopyVisitor(expr, oldToNewIDMap);
			reACV.visit();
		}
		for (AbstractExpression expr : ga.getGroupExpressions()) {
			reACV = new RenameExpressionAfterCopyVisitor(expr, oldToNewIDMap);
			reACV.visit();
		}
		return error;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		RenameExpressionAfterCopyVisitor reACV;
		for (AbstractExpression expr : gp.getExpressions()) {
			reACV = new RenameExpressionAfterCopyVisitor(expr, oldToNewIDMap);
			reACV.visit();
		}
		return error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		return error;
	}

	@Override
	public Error visitRename(Rename ro) {

		return error;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		return error;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		return error;
	}
}
