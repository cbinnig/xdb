/**
 * 
 */
package org.xdb.funsql.compile.analyze.operator;

import java.util.ArrayList;
import java.util.List;

import org.xdb.error.Error;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.AbstractJoinOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.metadata.Connection;

/**
 * Annotates each operator in compile plan with possible connections where
 * operator can be executed. For table operators the connections of the table
 * are used. For all other operators the connections of all inputs is
 * intersected. If the result is empty, then the connections of the left-most
 * operator are used.
 * 
 * @author Abdallah
 * 
 */
public class ConnectionAnnotationVisitor extends AbstractBottomUpTreeVisitor {

	public ConnectionAnnotationVisitor() {
		super();
	}

	public ConnectionAnnotationVisitor(AbstractCompileOperator root) {
		super(root);
	}

	/**
	 * intersects two lists of operators and makes sure that result is not empty
	 * 
	 * @param leftConns
	 * @param rightConns
	 * @return
	 */
	private List<Connection> intersectNotEmpty(List<Connection> leftConns,
			List<Connection> rightConns) {
		List<Connection> conns = new ArrayList<Connection>();
		for (Connection leftConn : leftConns) {
			if (rightConns.contains(leftConn))
				conns.add(leftConn);
		}
		if (conns.isEmpty())
			conns.addAll(leftConns);

		return conns;
	}

	/**
	 * Annotates a given abstract join operator with intersection of all input
	 * connections
	 * 
	 * @param abstractJoin
	 */
	private void annotateAbstractJoin(AbstractJoinOperator abstractJoin) {
		int i = 0;
		List<Connection> conns = null;
		for (AbstractCompileOperator op : abstractJoin.getChildren()) {
			if (i == 0) {
				conns = op.getWishedConnections();
			} else {
				List<Connection> conns2 = op.getWishedConnections();
				conns = this.intersectNotEmpty(conns, conns2);
			}
		}

		abstractJoin.addWishedConnections(conns);
	}

	@Override
	public Error visitEquiJoin(EquiJoin equiJoin) {
		List<Connection> leftConns = equiJoin.getRightChild()
				.getWishedConnections();
		List<Connection> rightConns = equiJoin.getRightChild()
				.getWishedConnections();
		List<Connection> conns = this.intersectNotEmpty(leftConns, rightConns);
		if (conns.isEmpty())
			conns.addAll(leftConns);

		equiJoin.addWishedConnections(conns);

		return new Error();
	}

	@Override
	public Error visitSQLJoin(SQLJoin sqlJoin) {
		annotateAbstractJoin(sqlJoin);
		return new Error();
	}

	@Override
	public Error visitSQLCombined(SQLCombined sqlCombined) {
		annotateAbstractJoin(sqlCombined);
		return new Error();
	}

	@Override
	public Error visitGenericSelection(GenericSelection selOp) {
		selOp.addWishedConnections(selOp.getChild().getWishedConnections());
		return new Error();
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation aggOp) {
		aggOp.addWishedConnections(aggOp.getChild().getWishedConnections());
		return new Error();
	}

	@Override
	public Error visitGenericProjection(GenericProjection projectOp) {
		projectOp.addWishedConnections(projectOp.getChild()
				.getWishedConnections());
		return new Error();
	}

	@Override
	public Error visitTableOperator(TableOperator tableOp) {
		tableOp.addWishedConnections(tableOp.getConnections());
		return new Error();
	}

	@Override
	public Error visitRename(Rename renameOp) {
		renameOp.addWishedConnections(renameOp.getChild()
				.getWishedConnections());
		return new Error();
	}

	@Override
	public Error visitSQLUnary(SQLUnary unaryOp) {
		unaryOp.addWishedConnections(unaryOp.getChild().getWishedConnections());
		return new Error();
	}
}
