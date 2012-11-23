package org.xdb.test.tracker;

import org.junit.Assert;
import org.junit.Test;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprOperator;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenIntegerLiteral;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Connection;
import org.xdb.metadata.Table;
import org.xdb.store.EnumStore;
import org.xdb.test.TestCase;
import org.xdb.tracker.MasterTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;

public class TestPlanTranslation extends TestCase {

	@Override
	/**
	 * Setup common statements (connect, drop, ...)
	 */
	public void setUp() {
		
	}
	
	
	@Test
	public void testSimpleSelect() throws Exception {
		/*
		 * Test Plan
		 *   INPUT:
		 *    tables: R ( a INT )
		 *    statement: SELECT a+1 FROM R WHERE a = 1
		 *   checked OUTPUT:
		 *    - existing plan object
		 *    - single MySQL Operator
		 */
		final CompilePlan plan = new CompilePlan();
		
		//setup statement
		//input table
		final TableOperator tableOp = new TableOperator(new TokenIdentifier("R"));
		//assign a connection for tracking purposes
		tableOp.setConnection(new Connection("INVALID_CONNECTION", "mysql://127.0.0.1/xdb", "user", "password", EnumStore.MYSQL));
		
		//select operator
		final GenericProjection selectOp = new GenericProjection(tableOp);
				
		//add ops to plan
		plan.addOperator(tableOp , false); 
		plan.addOperator(selectOp , true); //root
		
		//add calculation to select operator
		final ComplexExpression addExp = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
		addExp.setExpr1(new SimpleExpression(new TokenAttribute(tableOp.getOperatorId().toString(), "a")));
		addExp.addOp(EnumExprOperator.SQL_PLUS);
		addExp.addExpr2(new SimpleExpression(new TokenIntegerLiteral(1)));
		selectOp.addExpression(addExp);
		
		//add results
		final ResultDesc selectResult = new ResultDesc();
		selectResult.addAttribute(new TokenAttribute(tableOp.getOperatorId().toString(), "a_1"));
		selectResult.addType(EnumSimpleType.SQL_INTEGER);
		selectOp.addResult(selectResult);
		
		// same result as output/select, but w/o op table name
		final ResultDesc tableResult = new ResultDesc(1);
		tableResult.addAttribute(new TokenAttribute(tableOp.getOperatorId().toString(), "a"));
		tableResult.addType(EnumSimpleType.SQL_INTEGER);
		tableOp.addResult(tableResult);
		Table table = new Table("R", "R", "PUBLIC", 0L, 0L);
		table.addAttribute(new Attribute("a", EnumSimpleType.SQL_INTEGER, 0L));
		tableOp.setTable(table);
		
		QueryTrackerPlan qPlan = MasterTrackerNode.generateQueryTrackerPlan(plan);
		Assert.assertNotNull(qPlan);
		qPlan.traceGraph(this.getClass().getName());
		
		assertEquals(qPlan.getOperators().size(), 1);
		
	}
}
