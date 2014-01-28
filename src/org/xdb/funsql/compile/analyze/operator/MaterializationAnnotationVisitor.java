package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
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
 * Sets materialization flag for each operator. If an operator is root
 * (parents.size==0) or if an operator has more than 1 parent, then the flag is
 * set o true
 * 
 * @author cbinnig
 * 
 */
public class MaterializationAnnotationVisitor extends
		AbstractBottomUpTreeVisitor {

	public MaterializationAnnotationVisitor() {
		super();
	}

	public MaterializationAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visitEquiJoin(EquiJoin equiJoin) {
		applyGlobalMaterializeRules(equiJoin);
		return new Error();
	}

	@Override
	public Error visitSQLJoin(SQLJoin sqlJoin) {
		applyGlobalMaterializeRules(sqlJoin);
		return new Error();
	}

	@Override
	public Error visitGenericSelection(GenericSelection selOp) {
		applyGlobalMaterializeRules(selOp);
		return new Error();
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation aggOp) {
		applyGlobalMaterializeRules(aggOp);
		return new Error();
	}

	@Override
	public Error visitGenericProjection(GenericProjection projectOp) {
		applyGlobalMaterializeRules(projectOp);

		if (projectOp.getChild().isAggregation()) {
			projectOp.getResult().materialize(true);
		}
		return new Error();
	}

	@Override
	public Error visitTableOperator(TableOperator tableOp) {
		applyGlobalMaterializeRules(tableOp);
		return new Error();
	}

	@Override
	public Error visitRename(Rename renameOp) {
		applyGlobalMaterializeRules(renameOp);
		return new Error();
	}

	@Override
	public Error visitSQLUnary(SQLUnary unaryOp) {
		applyGlobalMaterializeRules(unaryOp);
		return new Error();
	}

	@Override
	public Error visitSQLCombined(SQLCombined sqlCombined) {
		applyGlobalMaterializeRules(sqlCombined);
		return new Error();
	}

	/**
	 * Force materialize if - an operator has no parent (i.e, it is a root) an
	 * operator has > 1 parents
	 * 
	 * @param op
	 */
	protected void applyGlobalMaterializeRules(final AbstractCompileOperator op) {
		if (op.getParents().size() != 1) {
			op.getResult().materialize(true);
		}
	}
}
