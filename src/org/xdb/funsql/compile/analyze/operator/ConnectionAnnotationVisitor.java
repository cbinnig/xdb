package org.xdb.funsql.compile.analyze.operator;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.AbstractJoinOperator;
import org.xdb.funsql.compile.operator.AbstractUnaryOperator;
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

/**
 * Annotates each operator in compile plan with possible connections where
 * operator can be executed. For table operators the connections of the table
 * are used. For all other operators the connections of all inputs is
 * intersected. If the result is empty, then the connections of the left-most
 * operator are used.
 * 
 * @author cbinnig
 *
 */
public class ConnectionAnnotationVisitor extends AbstractBottomUpTreeVisitor {

	public ConnectionAnnotationVisitor() {
		super();
	}

	public ConnectionAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}

	@Override
	public Error visitEquiJoin(EquiJoin equiJoin) {
		ResultDesc result = equiJoin.getResult();
		int partCnt = result.getPartitionCount();
		int childIdx = 0;
		if(equiJoin.getChild(childIdx).getResult().getPartitionCount()!=partCnt){
			childIdx = 1;
		}
			
		for(int i=0; i<result.getPartitionCount(); ++i){
			equiJoin.addWishedConnections(i, equiJoin.getChild(childIdx).getWishedConnections(i));
		}
		return new Error();
		
	}

	@Override
	public Error visitSQLJoin(SQLJoin sqlJoin) {
		return this.visitJoin(sqlJoin);
	}

	@Override
	public Error visitSQLCombined(SQLCombined sqlCombined) {
		return this.visitJoin(sqlCombined);
	}

	@Override
	public Error visitGenericSelection(GenericSelection selOp) {
		ResultDesc result = selOp.getResult();
		for(int i=0; i<result.getPartitionCount(); ++i){
			selOp.addWishedConnections(i ,selOp.getChild().getWishedConnections(i));
		}
		return new Error();
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation aggOp) {
		return this.visitUnaryOp(aggOp);
	}

	@Override
	public Error visitGenericProjection(GenericProjection projectOp) {
		return this.visitUnaryOp(projectOp);
	}

	@Override
	public Error visitRename(Rename renameOp) {
		return this.visitUnaryOp(renameOp);
	}

	@Override
	public Error visitSQLUnary(SQLUnary unaryOp) {
		return this.visitUnaryOp(unaryOp);
	}
	
	@Override
	public Error visitTableOperator(TableOperator tableOp) {
		ResultDesc result = tableOp.getResult();
		for(int i=0; i<result.getPartitionCount(); ++i){
			tableOp.addWishedConnections(i, tableOp.getConnections(i));
		}
		return new Error();
	}
	
	/**
	 * Visit unary operators
	 * @param op
	 * @return
	 */
	private Error visitUnaryOp(AbstractUnaryOperator op){
		ResultDesc result = op.getResult();
		for(int i=0; i<result.getPartitionCount(); ++i){
			op.addWishedConnections(i, op.getChild().getWishedConnections(i));
		}
		return new Error();
	}
	
	/**
	 * Visit join operators
	 * @param op
	 * @return
	 */
	private Error visitJoin(AbstractJoinOperator op) {
		ResultDesc result = op.getResult();
		int partCnt = result.getPartitionCount();
		int childIdx = 0;
		for(AbstractCompileOperator childOp : op.getChildren()){
			if(childOp.getResult().getPartitionCount()==partCnt)
				break;
			childIdx++;
		}
		for(int i=0; i<partCnt; ++i){
			op.addWishedConnections(i, op.getChild(childIdx).getWishedConnections(i));
		}
		return new Error();
	}
}
