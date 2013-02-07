package org.xdb.funsql.compile.analyze.operator;

import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.expression.RenameExpressionVisitor;
import org.xdb.funsql.compile.analyze.predicate.RenamePredicateVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;

import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.TokenPair;

public class RenameOperatorVisitor extends AbstractBottomUpTreeVisitor {

	public RenameOperatorVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		TokenAttribute leftAtt = ej.getLeftTokenAttribute();
		leftAtt.setName(ResultDesc.createResultAtt(leftAtt.getTable().getName().toSqlString(), leftAtt.getName().toSqlString()));
		leftAtt.setTable(ej.getLeftChild().getOperatorId().toString());
		
		TokenAttribute rightAtt = ej.getRightTokenAttribute();
		rightAtt.setName(ResultDesc.createResultAtt(rightAtt.getTable().getName().toSqlString(), rightAtt.getName().toSqlString()));
		rightAtt.setTable(ej.getRightChild().getOperatorId().toString());
		
		return new Error();
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		RenamePredicateVisitor renameVisitor = new RenamePredicateVisitor(gs.getPredicate(), gs.getChild().getOperatorId());
		return renameVisitor.visit();
	}


	@Override
	public Error visitGenericAggregation(GenericAggregation ga) {
		Error e = new Error();
		Vector<AbstractExpression> exprs = new  Vector<AbstractExpression>();
		exprs.addAll(ga.getAggregationExpressions());
		exprs.addAll(ga.getGroupExpressions());
		
		for(AbstractExpression expr: exprs){
			RenameExpressionVisitor renameVisitor = new RenameExpressionVisitor(expr, ga.getChild().getOperatorId());
			e = renameVisitor.visit();
			if(e.isError())
				return e;
		}
		
		return e;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		Error e = new Error();
		
		for(AbstractExpression expr: gp.getExpressions()){
			RenameExpressionVisitor renameVisitor = new RenameExpressionVisitor(expr, gp.getChild().getOperatorId());
			e = renameVisitor.visit();
			if(e.isError())
				return e;
		}
		
		return e;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		Error e = new Error();
		return e;
	}
	
	@Override
	public Error visitRename(Rename ro) {
		Error e = new Error();
		return e;
	}
	
	@Override
	public Error visitSQLUnary(SQLUnary sqlOp) {
		String[] args = { "SQLUnary operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	@Override
	public Error visitSQLJoin(SQLJoin sj) {
		String[] args = { "SQLJoin operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		String[] args = { "SQLCombined operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}
	
}
