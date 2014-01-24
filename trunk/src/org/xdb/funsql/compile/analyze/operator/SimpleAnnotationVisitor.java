/**
 * 
 */
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
 * set the wished connections list and the naive materialization strategy 
 * @author Abdallah 
 *
 */
public class SimpleAnnotationVisitor extends AbstractAnnotationVisitor { 


	/**
	 * @param root
	 */
	public SimpleAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}


	@Override
	public Error visitEquiJoin(EquiJoin equiJoin) {
		applyGlobalMaterializeRules(equiJoin);
		
		equiJoin.addWishedConnections(equiJoin.getRightChild().getWishedConnections());
		equiJoin.addWishedConnections(equiJoin.getLeftChild().getWishedConnections());
		
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLJoin(SQLJoin sqlJoin) {
		applyGlobalMaterializeRules(sqlJoin);
		
		for (AbstractCompileOperator op : sqlJoin.getChildren()) {
			sqlJoin.addWishedConnections(op.getWishedConnections());   
			
		}
		return Error.NO_ERROR;
	}


	@Override
	public Error visitGenericSelection(GenericSelection selOp) {
		applyGlobalMaterializeRules(selOp);
		
		selOp.addWishedConnections(selOp.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation aggOp) {
		applyGlobalMaterializeRules(aggOp);
		
		aggOp.addWishedConnections(aggOp.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitGenericProjection(GenericProjection projectOp) {
		applyGlobalMaterializeRules(projectOp);
		
		projectOp.addWishedConnections(projectOp.getChild().getWishedConnections());
		if(projectOp.getChild().isAggregation()){
			projectOp.getResult().materialize(true);
		}
		return Error.NO_ERROR;
	}


	@Override
	public Error visitTableOperator(TableOperator tableOp) {
		applyGlobalMaterializeRules(tableOp);
		
		tableOp.addWishedConnections(tableOp.getConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitRename(Rename renameOp) {
		applyGlobalMaterializeRules(renameOp);
		
		renameOp.addWishedConnections(renameOp.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLUnary(SQLUnary unaryOp){
		applyGlobalMaterializeRules(unaryOp);
		unaryOp.addWishedConnections(unaryOp.getChild().getWishedConnections());
		return Error.NO_ERROR;
	}


	@Override
	public Error visitSQLCombined(SQLCombined sqlCombined) {
		applyGlobalMaterializeRules(sqlCombined);
		for (AbstractCompileOperator op : sqlCombined.getChildren()) {
			sqlCombined.addWishedConnections(op.getWishedConnections());   
			
		}
		return Error.NO_ERROR;
	}
}
