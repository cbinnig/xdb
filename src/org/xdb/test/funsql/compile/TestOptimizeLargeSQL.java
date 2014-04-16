package org.xdb.test.funsql.compile;

import org.junit.Test;
import org.xdb.Config;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.optimize.Optimizer;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.funsql.statement.SelectStmt;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.test.TestCase;
import org.xdb.test.XDBTestCase;

public class TestOptimizeLargeSQL extends XDBTestCase {

	@Test
	public void testComplexOptimizer() {
		FunSQLCompiler compiler = new FunSQLCompiler();
		compiler.doOptimize(false);

		// create connection -> no error
		String dropConnSql = "DROP CONNECTION \"testConnection\"";
		AbstractServerStmt stmt = compiler.compile(dropConnSql);
		if (stmt != null)
			this.execute(stmt);

		String createConnSql = "CREATE CONNECTION \"testConnection\" "
				+ "URL 'jdbc:mysql://127.0.0.1/xdb_tmp' " + "USER 'xroot' "
				+ "PASSWORD 'xroot' " + "STORE 'XDB' ";

		stmt = compiler.compile(createConnSql);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);

		// create table
		String dropTableSql = "DROP TABLE \"R\"";
		stmt = compiler.compile(dropTableSql);
		if (stmt != null)
			this.execute(stmt);

		String compString = "CREATE TABLE \"R\" ( ";
		compString += "  ID INT," + "  BID INT," + "  CID INT," + "  DID INT, ";
		compString += "  A INT," + "  B VARCHAR," + "  C INT";
		compString += ") IN CONNECTION \"testConnection\"";

		stmt = compiler.compile(compString);
		this.assertNoError(compiler.getLastError());
		TestCase.assertNotNull(stmt);
		this.execute(stmt);

		// compile select
		SelectStmt selectStmt = (SelectStmt) compiler
				.compile("SELECT A1.ID, B1.A, C1.B, D1.B AS important "
						+ "FROM R AS A1, R AS B1, R AS C1, R AS D1 "
						+ "WHERE A1.BID=B1.ID AND A1.CID=C1.ID AND A1.DID=D1.ID "
						+ "AND D1.B = 9 AND D1.A = 3");
		this.assertNoError(compiler.getLastError());

		//extend plan to have two root operators
		GenericProjection proj = (GenericProjection) selectStmt.getPlan()
				.getRootOp(0);

		GenericSelection sel1 = (GenericSelection) proj.getChild();
		GenericSelection sel2 = (GenericSelection) sel1.getChild();
		EquiJoin join = (EquiJoin) sel2.getChild();

		GenericSelection sel = new GenericSelection(join);
		GenericProjection proj2 = new GenericProjection(sel);
		for (AbstractExpression exp : proj.getExpressions()) {
			proj2.addExpression(exp);
		}

		selectStmt.getPlan().addOperator(sel, false);
		selectStmt.getPlan().addOperator(proj2, true);

		AbstractPredicate pred = sel2.getPredicate().clone();
		sel.setPredicate(pred);
		selectStmt.getPlan().tracePlan(this.getClass().getName());

		ResultDesc desc = new ResultDesc();
		for (TokenAttribute att : sel2.getResult().getAttributes()) {
			TokenAttribute attNew = new TokenAttribute();
			attNew.setName(att.getName());
			attNew.setTable(att.getTable());
			attNew.setType(att.getType());
			desc.addAttribute(attNew);
		}
		for (int i = 0; i < sel2.getResult().getTypes().size(); i++) {
			EnumSimpleType type = sel2.getResult().getType(i);
			desc.setType(i, type);
		}

		sel.setResult(desc);

		sel.setChild(join);

		proj2.getParents().removeAllElements();
		proj2.setResult(proj.getResult());
		proj2.setChild(sel);

		//Optimize plan
		Optimizer opti = new Optimizer(selectStmt.getPlan());
		opti.optimize(Config.OPTIMIZER_ACTIVE_RULES_FUNCTION);

		selectStmt.getPlan()
				.tracePlan(this.getClass().getName() + "Optimized");
	}

}
