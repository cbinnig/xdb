package org.xdb.test.tracker;

import org.junit.Assert;
import org.junit.Test;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprOperator;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.EnumAggregation;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.predicate.EnumCompOperator;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenIntegerLiteral;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Connection;
import org.xdb.metadata.Table;
import org.xdb.store.EnumStore;
import org.xdb.test.XDBTestCase;
import org.xdb.tracker.QueryTrackerNode;
import org.xdb.tracker.QueryTrackerPlan;

public class TestPlanTranslation extends XDBTestCase {

	private QueryTrackerNode qTracker;
	
	@Override
	/**
	 * Setup common statements (connect, drop, ...)
	 */
	public void setUp() {
		super.setUp();
		
		this.qTracker = this.qTrackerServer.getNode();
	}
	
	@Test
	public void testSimplePlanTransition() throws Exception {
		/*
		 * Test Plan
		 *   INPUT:
		 *    tables: R ( a INT, b INT ), S ( c INT )
		 *    statement: SELECT b, c FROM R INNER JOIN S ON a = c WHERE b > 20
		 */
		final CompilePlan plan = new CompilePlan();
		
		// tables
		final TableOperator tableOpR = new TableOperator(new TokenIdentifier("R"));
		final TableOperator tableOpS = new TableOperator(new TokenIdentifier("S"));
		
		tableOpR.setConnection(new Connection("INVALID_CONNECTION", "mysql://127.0.0.1/xdbR", "user", "password", EnumStore.MYSQL));
		tableOpS.setConnection(new Connection("INVALID_CONNECTION", "mysql://127.0.0.1/xdbS", "user", "password", EnumStore.MYSQL));
		
		plan.addOperator(tableOpR, false);
		plan.addOperator(tableOpS, false);
		
		// table fields
		final TokenAttribute attributeRA = new TokenAttribute(tableOpR.getOperatorId().toString(), "a");
		final TokenAttribute attributeRB = new TokenAttribute(tableOpR.getOperatorId().toString(), "b");
		final TokenAttribute attributeSC = new TokenAttribute(tableOpS.getOperatorId().toString(), "c");
		
		// equi join
		final EquiJoin joinOp = new EquiJoin(tableOpR, tableOpS, attributeRA, attributeSC);
		plan.addOperator(joinOp, false);
		
		// select: a > 20
		final GenericSelection selectOp = new GenericSelection(joinOp);
		final SimplePredicate selectionPred = new SimplePredicate();
		selectionPred.setExpr1(new SimpleExpression(attributeRB));
		selectionPred.setComp(EnumCompOperator.SQL_GREATER_THAN);
		selectionPred.setExpr2(new SimpleExpression(new TokenIntegerLiteral(20)));
		selectOp.setPredicate(selectionPred);
		plan.addOperator(selectOp, false);
		
		final TokenAttribute joinAttributeRA = new TokenAttribute(joinOp.getLeftChild().getOperatorId().toString(), "a");
		final TokenAttribute joinAttributeRB = new TokenAttribute(joinOp.getOperatorId().toString(), "b");
		final TokenAttribute joinAttributeSC = new TokenAttribute(joinOp.getRightChild().getOperatorId().toString(), "c");
		
		// projection
		final GenericProjection projectionOp = new GenericProjection(selectOp);
		plan.addOperator(projectionOp, true);
		
		final TokenAttribute projectionAttributeB = new TokenAttribute(projectionOp.getOperatorId().toString(), "b");
		final TokenAttribute projectionAttributeC = new TokenAttribute(projectionOp.getOperatorId().toString(), "c");
		
		//add results
		projectionOp.addExpression(new SimpleExpression(joinAttributeRB));
		projectionOp.addExpression(new SimpleExpression(joinAttributeSC));
		final ResultDesc projResult = new ResultDesc(2);
		projResult.addAttribute(projectionAttributeB);
		projResult.addType(EnumSimpleType.SQL_INTEGER);
		projResult.addAttribute(projectionAttributeC);
		projResult.addType(EnumSimpleType.SQL_INTEGER);
		projectionOp.setResult(projResult);
		
		final ResultDesc selectResult = new ResultDesc(1);
		selectResult.addAttribute(attributeRB);
		selectResult.addType(EnumSimpleType.SQL_INTEGER);
		selectOp.setResult(selectResult);
		
		
		final ResultDesc joinResult = new ResultDesc(3);
		joinResult.addAttribute(joinAttributeRA);
		joinResult.addType(EnumSimpleType.SQL_INTEGER);
		joinResult.addAttribute(joinAttributeRB);
		joinResult.addType(EnumSimpleType.SQL_INTEGER);
		joinResult.addAttribute(joinAttributeSC);
		joinResult.addType(EnumSimpleType.SQL_INTEGER);
		joinOp.setResult(joinResult);
		
		final ResultDesc tableRResult = new ResultDesc(2);
		tableRResult.addAttribute(attributeRA);
		tableRResult.addType(EnumSimpleType.SQL_INTEGER);
		tableRResult.addAttribute(attributeRB);
		tableRResult.addType(EnumSimpleType.SQL_INTEGER);
		tableOpR.setResult(tableRResult);
		
		final Table tableR = new Table("R", 0L, 0L);
		tableR.addAttribute(new Attribute("a", EnumSimpleType.SQL_INTEGER, 0L));
		tableR.addAttribute(new Attribute("b", EnumSimpleType.SQL_INTEGER, 0L));
		tableOpR.setTable(tableR);
		
		final ResultDesc tableSResult = new ResultDesc(1);
		tableSResult.addAttribute(attributeSC);
		tableSResult.addType(EnumSimpleType.SQL_INTEGER);
		tableOpS.setResult(tableSResult);
		
		final Table tableS = new Table("S", 0L, 0L);
		tableS.addAttribute(new Attribute("c", EnumSimpleType.SQL_INTEGER, 0L));
		tableOpS.setTable(tableS);
		
		Error annotation = QueryTrackerNode.annotateCompilePlan(plan);
		assertNoError(annotation);
		
		QueryTrackerPlan qPlan = qTracker.generateQueryTrackerPlan(plan).getObject1();
		Assert.assertNotNull(qPlan);
		qPlan.tracePlan(this.getClass().getName());
		
		assertEquals(1, qPlan.getTrackerOperators().size());
	}
	
	
	@Test
	public void testSimpleProjection() throws Exception {
		/*
		 * Test Plan
		 *   INPUT:
		 *    tables: R ( a INT )
		 *    statement: SELECT a+1 FROM R
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
		
		final TokenAttribute tableResultA = new TokenAttribute(tableOp.getOperatorId().toString(), "a");
		
		//add calculation to select operator
		final ComplexExpression addExp = new ComplexExpression(EnumExprType.ADD_EXPRESSION);
		addExp.setExpr1(new SimpleExpression(tableResultA));
		addExp.addOp(EnumExprOperator.SQL_PLUS);
		addExp.addExpr2(new SimpleExpression(new TokenIntegerLiteral(1)));
		selectOp.addExpression(addExp);
		
		//add results
		final ResultDesc selectResult = new ResultDesc(1);
		selectResult.addAttribute(new TokenAttribute(tableOp.getOperatorId().toString(), "a_1"));
		selectResult.addType(EnumSimpleType.SQL_INTEGER);
		selectOp.setResult(selectResult);
		
		// same result as output/select, but w/o op table name
		final ResultDesc tableResult = new ResultDesc(1);
		tableResult.addAttribute(tableResultA);
		tableResult.addType(EnumSimpleType.SQL_INTEGER);
		tableOp.setResult(tableResult);
		Table table = new Table("R", 0L, 0L);
		table.addAttribute(new Attribute("a", EnumSimpleType.SQL_INTEGER, 0L));
		tableOp.setTable(table);
		
		Error annotation = QueryTrackerNode.annotateCompilePlan(plan);
		assertNoError(annotation);
		
		QueryTrackerPlan qPlan = qTracker.generateQueryTrackerPlan(plan).getObject1();
		Assert.assertNotNull(qPlan);
		qPlan.tracePlan(this.getClass().getName());
		
		assertEquals(1, qPlan.getTrackerOperators().size());
		
	}
	
	@Test
	public void testSimpleSelection() throws Exception {
		/*
		 * Test Plan
		 *   INPUT:
		 *    tables: R ( a INT )
		 *    statement: SELECT a FROM R WHERE a > 1
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
		final GenericSelection selectOp = new GenericSelection(tableOp);
				
		//add ops to plan
		plan.addOperator(tableOp , false); 
		plan.addOperator(selectOp , true); //root
		
		final TokenAttribute attributeA = new TokenAttribute(tableOp.getOperatorId().toString(), "a");
		
		//add calculation to select operator
		final SimplePredicate selPred = new SimplePredicate();
		selPred.setExpr1(new SimpleExpression(attributeA));
		selPred.setComp(EnumCompOperator.SQL_GREATER_THAN);
		selPred.setExpr2(new SimpleExpression(new TokenIntegerLiteral(1)));
		selectOp.setPredicate(selPred);
		
		//add results
		final ResultDesc selectResult = new ResultDesc(1);
		selectResult.addAttribute(attributeA);
		selectResult.addType(EnumSimpleType.SQL_INTEGER);
		selectOp.setResult(selectResult);
		
		// same result as output/select, but w/o op table name
		final ResultDesc tableResult = new ResultDesc(1);
		tableResult.addAttribute(attributeA);
		tableResult.addType(EnumSimpleType.SQL_INTEGER);
		tableOp.setResult(tableResult);
		Table table = new Table("R", 0L, 0L);
		table.addAttribute(new Attribute("a", EnumSimpleType.SQL_INTEGER, 0L));
		tableOp.setTable(table);
		
		Error annotation = QueryTrackerNode.annotateCompilePlan(plan);
		assertNoError(annotation);
		
		QueryTrackerPlan qPlan = qTracker.generateQueryTrackerPlan(plan).getObject1();
		Assert.assertNotNull(qPlan);
		qPlan.tracePlan(this.getClass().getName());
		
		assertEquals(1, qPlan.getTrackerOperators().size());	
	}
	
	@Test
	public void testSimpleAggregation() throws Exception {
		/*
		 * Test Plan
		 *   INPUT:
		 *    tables: R ( a INT, b INT )
		 *    statement: SELECT SUM(a),b FROM R GROUP BY b
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
		final GenericAggregation selectOp = new GenericAggregation(tableOp);
				
		//add ops to plan
		plan.addOperator(tableOp , false); 
		plan.addOperator(selectOp , true); //root
		
		final TokenAttribute attributeA = new TokenAttribute(tableOp.getOperatorId().toString(), "a");
		final TokenAttribute attributeB = new TokenAttribute(tableOp.getOperatorId().toString(), "b");
		
		//add calculation to select operator
		final AggregationExpression aggExp = new AggregationExpression();
		aggExp.setExpression(new SimpleExpression(attributeA));
		aggExp.setAggregation(EnumAggregation.SUM);
		selectOp.addAggregationExpression(aggExp);
		selectOp.addGroupExpression(new SimpleExpression(attributeB));
		
		//add results
		final ResultDesc selectResult = new ResultDesc(1);
		selectResult.addAttribute(new TokenAttribute(tableOp.getOperatorId().toString(), "a_TESTSUM"));
		selectResult.addType(EnumSimpleType.SQL_INTEGER);
		selectResult.addAttribute(attributeB);
		selectResult.addType(EnumSimpleType.SQL_INTEGER);
		selectOp.setResult(selectResult);
		
		// same result as output/select, but w/o op table name
		final ResultDesc tableResult = new ResultDesc(2);
		tableResult.addAttribute(attributeA);
		tableResult.addType(EnumSimpleType.SQL_INTEGER);
		tableResult.addAttribute(attributeB);
		tableResult.addType(EnumSimpleType.SQL_INTEGER);
		tableOp.setResult(tableResult);
		Table table = new Table("R", 0L, 0L);
		table.addAttribute(new Attribute("a", EnumSimpleType.SQL_INTEGER, 0L));
		table.addAttribute(new Attribute("b", EnumSimpleType.SQL_INTEGER, 0L));
		tableOp.setTable(table);
		
		Error annotation = QueryTrackerNode.annotateCompilePlan(plan);
		assertNoError(annotation);
		
		QueryTrackerPlan qPlan = qTracker.generateQueryTrackerPlan(plan).getObject1();
		Assert.assertNotNull(qPlan);
		qPlan.tracePlan(this.getClass().getName());
		
		assertEquals(1, qPlan.getTrackerOperators().size());
		
	}
	
	@Test
	public void testEquiJoin() throws Exception {
		/*
		 * Test Plan
		 *   INPUT:
		 *    tables: R ( a INT, b INT ), S ( a INT, c INT) 
		 *    statement: SELECT R.a,R.b,R.c FROM R INNER JOIN S ON R.a = S.a
		 *   checked OUTPUT:
		 *    - existing plan object
		 *    - single MySQL Operator
		 *   notes:
		 *    - EquiJoin does not support simultaneous projection
		 */
		final CompilePlan plan = new CompilePlan();
		
		//setup statement
		//input table
		final TableOperator lTableOp = new TableOperator(new TokenIdentifier("R"));
		final TableOperator rTableOp = new TableOperator(new TokenIdentifier("S"));
		//assign a connection for tracking purposes
		lTableOp.setConnection(new Connection("INVALID_CONNECTION", "mysql://127.0.0.1/xdbR", "user", "password", EnumStore.MYSQL));
		rTableOp.setConnection(new Connection("INVALID_CONNECTION", "mysql://127.0.0.1/xdbS", "user", "password", EnumStore.MYSQL));
		
		//add tableOps to plan (gen id), create join attributes, create join op, add join op to plan
		
		plan.addOperator(lTableOp, false); //root 
		plan.addOperator(rTableOp, false); //root
		
		final TokenAttribute lAttributeA = new TokenAttribute(lTableOp.getOperatorId().toString(), "a");
		final TokenAttribute lAttributeB = new TokenAttribute(lTableOp.getOperatorId().toString(), "b");
		final TokenAttribute rAttributeA = new TokenAttribute(rTableOp.getOperatorId().toString(), "a");
		final TokenAttribute rAttributeC = new TokenAttribute(rTableOp.getOperatorId().toString(), "c");
		
		//select operator
		final EquiJoin joinOp = new EquiJoin(lTableOp, rTableOp, lAttributeA, rAttributeA);
		plan.addOperator(joinOp, false);
		
		final TokenAttribute jAttributeAl = new TokenAttribute(joinOp.getOperatorId().toString(), "a_L");
		final TokenAttribute jAttributeAr = new TokenAttribute(joinOp.getOperatorId().toString(), "a_R");
		final TokenAttribute jAttributeB  = new TokenAttribute(joinOp.getOperatorId().toString(), "b");
		final TokenAttribute jAttributeC  = new TokenAttribute(joinOp.getOperatorId().toString(), "c");
		
		final GenericProjection projOp = new GenericProjection(joinOp);
		plan.addOperator(projOp, true);
		
		final TokenAttribute pAttributeA = new TokenAttribute(projOp.getOperatorId().toString(), "a");
		final TokenAttribute pAttributeB = new TokenAttribute(projOp.getOperatorId().toString(), "b");
		final TokenAttribute pAttributeC = new TokenAttribute(projOp.getOperatorId().toString(), "c");
		
		//add results
		projOp.addExpression(new SimpleExpression(jAttributeAl));
		projOp.addExpression(new SimpleExpression(jAttributeB));
		projOp.addExpression(new SimpleExpression(jAttributeC));
		final ResultDesc projResult = new ResultDesc(3);
		projResult.addAttribute(pAttributeA);
		projResult.addType(EnumSimpleType.SQL_INTEGER);
		projResult.addAttribute(pAttributeB);
		projResult.addType(EnumSimpleType.SQL_INTEGER);
		projResult.addAttribute(pAttributeC);
		projResult.addType(EnumSimpleType.SQL_INTEGER);
		projOp.setResult(projResult);
		
		
		final ResultDesc joinResult = new ResultDesc(4);
		joinResult.addAttribute(jAttributeAl);
		joinResult.addType(EnumSimpleType.SQL_INTEGER);
		joinResult.addAttribute(jAttributeAr);
		joinResult.addType(EnumSimpleType.SQL_INTEGER);
		joinResult.addAttribute(jAttributeB);
		joinResult.addType(EnumSimpleType.SQL_INTEGER);
		joinResult.addAttribute(jAttributeC);
		joinResult.addType(EnumSimpleType.SQL_INTEGER);
		joinOp.setResult(joinResult);
		
		final ResultDesc lTableResult = new ResultDesc(2);
		lTableResult.addAttribute(lAttributeA);
		lTableResult.addType(EnumSimpleType.SQL_INTEGER);
		lTableResult.addAttribute(lAttributeB);
		lTableResult.addType(EnumSimpleType.SQL_INTEGER);
		lTableOp.setResult(lTableResult);
		final Table lTable = new Table("R", 0L, 0L);
		lTable.addAttribute(new Attribute("a", EnumSimpleType.SQL_INTEGER, 0L));
		lTable.addAttribute(new Attribute("b", EnumSimpleType.SQL_INTEGER, 0L));
		lTableOp.setTable(lTable);
		
		final ResultDesc rTableResult = new ResultDesc(2);
		rTableResult.addAttribute(rAttributeA);
		rTableResult.addType(EnumSimpleType.SQL_INTEGER);
		rTableResult.addAttribute(rAttributeC);
		rTableResult.addType(EnumSimpleType.SQL_INTEGER);
		rTableOp.setResult(rTableResult);
		final Table rTable = new Table("S", 0L, 0L);
		rTable.addAttribute(new Attribute("a", EnumSimpleType.SQL_INTEGER, 0L));
		rTable.addAttribute(new Attribute("c", EnumSimpleType.SQL_INTEGER, 0L));
		rTableOp.setTable(rTable);
		
		Error annotation = QueryTrackerNode.annotateCompilePlan(plan);
		assertNoError(annotation);
		
		QueryTrackerPlan qPlan = qTracker.generateQueryTrackerPlan(plan).getObject1();
		Assert.assertNotNull(qPlan);
		qPlan.tracePlan(this.getClass().getName());
		
		assertEquals(1, qPlan.getTrackerOperators().size());
	}
}
