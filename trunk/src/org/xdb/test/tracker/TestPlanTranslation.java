package org.xdb.test.tracker;

import org.junit.Assert;
import org.junit.Test;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.AbstractTokenOperand;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.funsql.statement.SelectStmt;
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
		 *    statement: SELECT a FROM R
		 *   checked OUTPUT:
		 *    - existing plan object
		 *    - single MySQL Operator
		 */
		CompilePlan plan = new CompilePlan();
		
		//setup statement
		//input table
		TableOperator tableOp = new TableOperator(new TokenIdentifier("R"));
		//assign a connection for tracking purposes
		tableOp.setConnection(new Connection("INVALID_CONNECTION", "url", "user", "password", EnumStore.MYSQL));
		
		//select operator
		AbstractOperator selectOp = new GenericSelection(tableOp);
				
		//add ops to plan
		plan.addOperator(tableOp , false); 
		plan.addOperator(selectOp , true); //root
		
		//add results
		ResultDesc selectResult = new ResultDesc();
		selectResult.addAttribute(new TokenAttribute(tableOp.getOperatorId().toString(), "a"));
		selectResult.addType(EnumSimpleType.SQL_INTEGER);
		selectOp.addResult(selectResult);
		
		// same result as output/select, but w/o op table name
		ResultDesc tableResult = new ResultDesc(1);
		tableResult.setAttribute(0, new TokenAttribute("a"));
		tableResult.setType(0, EnumSimpleType.SQL_INTEGER);
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
