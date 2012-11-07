package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.analyze.Analyzer;
import org.xdb.funsql.compile.analyze.FunctionCache;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;

public class SelectStmt extends AbstractServerStmt {
	// select
	private Vector<AbstractExpression> tSelExpr = new Vector<AbstractExpression>();
	private Vector<TokenIdentifier> tSelAliases = new Vector<TokenIdentifier>();

	// from
	private Vector<TokenTable> tTables = new Vector<TokenTable>();
	private Vector<TokenIdentifier> tTableAliases = new Vector<TokenIdentifier>();

	// where
	private AbstractPredicate tWherePredicate;

	// group by
	private Vector<AbstractExpression> tGroupExpr = new Vector<AbstractExpression>();

	// having
	private AbstractPredicate tHavingPredicate;

	// compiler variables
	private HashMap<String, Schema> schemaSymbols = new HashMap<String, Schema>();
	private HashMap<String, Table> tableSymbols = new HashMap<String, Table>();
	private HashMap<String, Attribute> attSymbols = new HashMap<String, Attribute>();
	private HashMap<TokenAttribute, EnumSimpleType> attTypes = new HashMap<TokenAttribute, EnumSimpleType>();
	private Vector<Table>usedVariables = new Vector<Table>();
	
	private CompilePlan plan = new CompilePlan();
	FunctionCache fcache = FunctionCache.getCache();

	// temporary compiler variables
	private int lastInternalAlias = 0;
	private Identifier internalAlias = new Identifier("_INT_ALIAS");
	private HashMap<AbstractExpression, TokenIdentifier> internalAliases = new HashMap<AbstractExpression, TokenIdentifier>();
	private Vector<AbstractPredicate> selectionPreds = new Vector<AbstractPredicate>();
	private AbstractOperator lastOp = null;

	// constructors
	public SelectStmt() {
		this.statementType = EnumStatement.SELECT;
	}

	// getters and setters
	public void addSelExpression(AbstractExpression expr) {
		this.tSelExpr.add(expr);
		
		if(expr.isAttribute()){
			TokenAttribute att = expr.getAttribute();
			this.tSelAliases.add(att.getName());
		}
		else{
			this.tSelAliases.add(null);
		}
	}

	public Collection<AbstractExpression> getSelExpressions() {
		return this.tSelExpr;

	}

	public void setSelAlias(int i, TokenIdentifier alias) {
		this.tSelAliases.set(i, alias);
	}

	public Collection<TokenIdentifier> getSelAliases() {
		return this.tSelAliases;
	}

	public Vector<Table> getUsedVariables() {
		return usedVariables;
	}

	public void addGroupExpression(AbstractExpression expr) {
		this.tGroupExpr.add(expr);
	}

	public Collection<AbstractExpression> getGroupExpressions() {
		return this.tGroupExpr;

	}

	public void addTable(TokenTable table) {
		this.tTables.add(table);
		this.tTableAliases.add(table.getName());
	}

	public Collection<TokenTable> getTables() {
		return tTables;
	}

	public void setTableAlias(int i, TokenIdentifier alias) {
		this.tTableAliases.set(i, alias);
	}

	public Collection<TokenIdentifier> getTableAliases() {
		return this.tTableAliases;
	}

	public void setWherePredicate(AbstractPredicate tPredicate) {
		this.tWherePredicate = tPredicate;
	}

	public void setHavingPredicate(AbstractPredicate tPredicate) {
		this.tHavingPredicate = tPredicate;
	}

	public CompilePlan getPlan() {
		return plan;
	}

	@Override
	public Error compile() {
		Error err = new Error();
		// 1. Check tables and create table symbols
		err = checkTables();
		if (err.isError())
			return err;

		// 2. check select attributes
		err = checkSelectAttributes();
		if (err.isError())
			return err;

		// 3. check all attributes and and create attribute symbols
		err = checkAttributes();
		if (err.isError())
			return err;

		// 4. create canonical plan
		err = this.canonicalTransalation(this.plan);
		
		// 5. analyze plan
		Analyzer analyzer = new Analyzer(this.plan, this.attTypes);
		analyzer.analyze();
		
		//6. add plan to cache
		fcache.addPlan(this.plan);

		return err;
	}

	/**
	 * Generates alias for internal expressions
	 * 
	 * @param expr
	 */
	private Identifier generateInternalAlias(AbstractExpression expr) {
		Identifier internalAlias = this.internalAlias.clone().append(++this.lastInternalAlias);
		this.internalAliases.put(expr, new TokenIdentifier(internalAlias.toString()));
		return internalAlias;
	}

	/**
	 * Create compile plan
	 * 
	 * @param plan
	 * @return
	 */
	private Error canonicalTransalation(CompilePlan plan) {
		Error err = new Error();

		// from clause -> joins over tables
		err = createFromPlan();
		if (err.isError())
			return err;

		// where clause -> selection for remaining predicates
		for (AbstractPredicate predicate : this.selectionPreds) {
			GenericSelection selectOp = new GenericSelection(lastOp);
			selectOp.setPredicate(predicate);
			plan.addOperator(selectOp, false);
			lastOp = selectOp;
		}

		// select and group-by clause -> aggregation
		HashSet<AbstractExpression> aggExprs = new HashSet<AbstractExpression>();
		for (AbstractExpression selExpr : this.tSelExpr) {
			if (selExpr.isAggregation())
				aggExprs.add(selExpr);
		}

		if (this.tGroupExpr.size() > 0 || aggExprs.size() > 0) {
			GenericAggregation aggOp = new GenericAggregation(lastOp);

			for (AbstractExpression aggExpr : aggExprs) {
				aggOp.addAggregationExpression(aggExpr);
				Identifier internalAlias = generateInternalAlias(aggExpr);
				aggOp.addAlias(new TokenIdentifier(internalAlias.toString()));
			}

			for (AbstractExpression aggExpr : this.tHavingPredicate
					.getAggregations()) {
				aggOp.addAggregationExpression(aggExpr);
				Identifier internalAlias = generateInternalAlias(aggExpr);
				aggOp.addAlias(new TokenIdentifier(internalAlias.toString()));
			}

			for (AbstractExpression groupExpr : this.tGroupExpr) {
				aggOp.addGroupExpression(groupExpr);
				Identifier internalAlias = generateInternalAlias(groupExpr);
				aggOp.addAlias(new TokenIdentifier(internalAlias.toString()));
			}

			plan.addOperator(aggOp, false);
			lastOp = aggOp;
		}

		// having clause -> selection for having predicate
		if (this.tHavingPredicate != null) {
			GenericSelection selectOp = new GenericSelection(lastOp);
			selectOp.setPredicate(this.tHavingPredicate.replaceAliases(this.internalAliases));
			plan.addOperator(selectOp, false);
			lastOp = selectOp;
		}

		// select clause -> projection
		GenericProjection projectOp = new GenericProjection(lastOp);
		for (int i = 0; i < this.tSelExpr.size(); ++i) {
			AbstractExpression tExpr = this.tSelExpr.get(i);
			TokenIdentifier tAlias = this.tSelAliases.get(i);
			
			if (this.internalAliases.containsKey(tExpr)) {
				TokenIdentifier internalAlias = this.internalAliases.get(tExpr);
				SimpleExpression intExpr = new SimpleExpression(
						new TokenAttribute(internalAlias));
				projectOp.addExpression(intExpr);
			} 
			else{
				projectOp.addExpression(tExpr);
			}
			projectOp.addAlias(tAlias);
		}
		plan.addOperator(projectOp, true);

		return err;
	}

	/**
	 * Create join plan for from clause
	 * 
	 * @param lastOp
	 * @return
	 */
	private Error createFromPlan() {
		Error err = new Error();

		// no join required
		if (this.tTableAliases.size() == 1) {
			TokenIdentifier tTableAlias = this.tTableAliases.get(0);
			Table table = this.tableSymbols.get(tTableAlias.hashKey());
			TableOperator tableOp = new TableOperator(tTableAlias);
			tableOp.setTable(table);
			if(this.usedVariables != null&&this.usedVariables.contains(table)){		
					tableOp.setVar(true);
			}
			plan.addOperator(tableOp, false);
			if (this.tWherePredicate != null) {
				selectionPreds.addAll(this.tWherePredicate.splitAnd());
			}
			lastOp = tableOp;
		}
		// join required
		else {
			// check predicate
			if (this.tWherePredicate == null) {
				return FunSQLCompiler
						.createGenericCompileErr("Cartesian product not in SQL statement supported!");
			}

			Collection<AbstractPredicate> predicates = this.tWherePredicate
					.splitAnd();
			HashMap<String, TableOperator> tableOps = new HashMap<String, TableOperator>();

			// find all equi-join predicates
			int joinPreds = 0;
			for (AbstractPredicate predicate : predicates) {

				if (predicate.isEquiJoinPredicate()) {
					// found join predicate
					joinPreds++;

					// get join attributes
					TokenAttribute[] joinAtts = new TokenAttribute[2];
					int i = 0;
					for (TokenAttribute tAttr : predicate.getAttributes()) {
						joinAtts[i] = tAttr;
						i++;
					}

					TokenTable tLeftTable = joinAtts[0].getTable();
					TokenTable tRightTable = joinAtts[1].getTable();

					TableOperator leftTableOp = tableOps.get(tLeftTable
							.getName().hashKey());
					TableOperator rightTableOp = tableOps.get(tRightTable
							.getName().hashKey());

					// left table not yet in join path?
					boolean newLeftTable = false;
					if (leftTableOp == null) {
						Table leftTable = this.tableSymbols.get(tLeftTable
								.getName().hashKey());
						leftTableOp = new TableOperator(tLeftTable.getName());
						leftTableOp.setTable(leftTable);
						plan.addOperator(leftTableOp, false);
						tableOps.put(tLeftTable.getName().hashKey(),
								leftTableOp);
						newLeftTable = true;
					}

					// right table not yat in join path?
					boolean newRightTable = false;
					if (rightTableOp == null) {
						Table rightTable = this.tableSymbols.get(tRightTable
								.getName().hashKey());
						rightTableOp = new TableOperator(tRightTable.getName());
						rightTableOp.setTable(rightTable);
						plan.addOperator(rightTableOp, false);
						tableOps.put(tRightTable.getName().hashKey(),
								rightTableOp);
						newRightTable = true;
					}

					EquiJoin joinOp = null;
					// both tables L and R are new: L JOIN R
					if (newLeftTable && newRightTable) {
						joinOp = new EquiJoin(leftTableOp, rightTableOp,
								joinAtts[0], joinAtts[1]);
					}
					// only left table is new: LastOp JOIN L (to create left
					// deep plan)
					else if (newLeftTable) {
						joinOp = new EquiJoin(lastOp, leftTableOp, joinAtts[1],
								joinAtts[0]);
					}
					// only right table is new: LastOp JOIN R
					else {
						joinOp = new EquiJoin(lastOp, rightTableOp,
								joinAtts[0], joinAtts[1]);
					}
					plan.addOperator(joinOp, false);
					lastOp = joinOp;
				} else {
					// keep remaining predicates!
					selectionPreds.add(predicate);
				}
			}

			if (joinPreds + 1 != this.tTables.size()) {
				return FunSQLCompiler
						.createGenericCompileErr("Cartesian product not in SQL statement supported!");
			}

		}

		return err;
	}

	/**
	 * Check if all attributes exist in a table
	 * 
	 * @return
	 */
	private Error checkAttributes() {
		Error err = new Error();

		// 1. Select Clause
		for (AbstractExpression tExpr : this.tSelExpr) {
			for (TokenAttribute tAtt : tExpr.getAttributes()) {
				err = checkAttribute(tAtt);
				if (err.isError())
					return err;
			}
		}

		// 2. Where Clause
		if (this.tWherePredicate != null) {
			for (TokenAttribute tAtt : this.tWherePredicate.getAttributes()) {
				err = checkAttribute(tAtt);
				if (err.isError())
					return err;
			}
		}

		// 3. Group-By Clause
		for (AbstractExpression tExpr : this.tGroupExpr) {
			for (TokenAttribute tAtt : tExpr.getAttributes()) {
				err = checkAttribute(tAtt);
				if (err.isError())
					return err;
			}
		}

		// 4. Having Clause
		if (this.tHavingPredicate != null) {
			for (TokenAttribute tAtt : this.tHavingPredicate.getAttributes()) {
				err = checkAttribute(tAtt);
				if (err.isError())
					return err;
			}
		}

		return err;
	}

	/**
	 * Check if attribute exists in table
	 * 
	 * @param tAtt
	 * @return
	 */
	private Error checkAttribute(TokenAttribute tAtt) {
		Error err = new Error();

		TokenTable tTable = tAtt.getTable();

		// table name not given in attribute token
		if (tTable == null) {
			Attribute att = null;
			for (Table table : this.tableSymbols.values()) {
				Attribute tempAtt = table.getAttribute(tAtt.toSqlString());
				if (tempAtt != null && att != null) {
					return createAttNotUniqueErr(tAtt);
				} else if (tempAtt != null) {
					att = tempAtt;
					tTable = new TokenTable(table.getName());
					tAtt.setTable(tTable);
				}
			}

			if (att == null) {
				return this.createAttInNoTableErr(tAtt);
			}

			this.attSymbols.put(tAtt.hashKey(), att);
			this.attTypes.put(tAtt, att.getDataType());
		}
		// table name given in attribute token
		else {
			Table table = this.tableSymbols.get(tTable.getName().hashKey());

			// find attribute
			Attribute att = table.getAttribute(tAtt.getName().toSqlString());
			if (att == null) {
				return createAttNotInTableErr(tAtt, tTable);
			}
			this.attSymbols.put(tAtt.hashKey(), att);
			this.attTypes.put(tAtt, att.getDataType());
		}
		
		return err;
	}

	/**
	 * Check if select attributes have unique result names
	 * 
	 * @return
	 */
	private Error checkSelectAttributes() {
		Error err = new Error();
		HashSet<String> tempAtts = new HashSet<String>();

		// 1. Select Clause
		for (int i = 0; i < this.tSelExpr.size(); ++i) {
			AbstractExpression tExpr = this.tSelExpr.get(i);
			TokenIdentifier tExprAlias = this.tSelAliases.get(i);

			// attribute w/o aliasing
			TokenAttribute exprAtt = null;
			if (tExpr.isAttribute() && tExprAlias == null) {
				exprAtt = tExpr.getAttributes().iterator().next();

				// Check for duplicates based on attribute name
				if (tempAtts.contains(exprAtt.getName().hashKey())) {
					return this.createDuplicateAttNameErr(exprAtt.getName());
				}
				tempAtts.add(exprAtt.getName().hashKey());
			}
			// expression with alias
			else {
				// Check if alias is defined
				if (tExprAlias == null) {
					return createNoExprAliasErr(tExpr);
				}

				// Check for duplicates based on alias
				if (tempAtts.contains(tExprAlias.hashKey())) {
					return this.createDuplicateAttNameErr(tExprAlias);
				}
				tempAtts.add(tExprAlias.hashKey());
			}
		}
		return err;
	}

	/**
	 * Creates table symbols for compilation
	 * 
	 * @return
	 */
	private Error checkTables() {
		Error err = new Error();
		// create mapping of table names and aliases to catalog table objects
		for (int i = 0; i < this.tTables.size(); ++i) {
			TokenTable tTable = this.tTables.get(i);
			TokenIdentifier tTableAlias = this.tTableAliases.get(i);

			if (tTable.isVariable()) {
				Table table = null;
				
				// check if is in cache
				if(!fcache.isVarInCache(tTable))
					return this.createVariableNotDeclared(tTable);
				else{
					table = fcache.getTable("VAR_"+tTable.getName().toString());					
				}
				// check for duplicate variable names
				if (this.tableSymbols.containsKey(tTableAlias.hashKey())) {
					return createDuplicateTableNameErr(tTableAlias);
				}
				this.tableSymbols.put(tTableAlias.hashKey(), table);
				this.usedVariables.add(table);
			} else {
				TokenSchema tSchema = tTable.getSchema();
				Schema schema = Catalog.getSchema(tSchema.hashKey());

				// check for non existing schema names
				if (schema == null) {
					return Catalog.createObjectNotExistsErr(
							tSchema.toSqlString(), EnumDatabaseObject.SCHEMA);
				}
				this.schemaSymbols.put(schema.hashKey(), schema);

				Table table = Catalog.getTable(tTable.hashKey(schema.getOid()));

				// check for non existing table names
				if (table == null) {
					return Catalog.createObjectNotExistsErr(
							tTable.toSqlString(), EnumDatabaseObject.TABLE);
				}

				// check for duplicate table names
				if (this.tableSymbols.containsKey(tTableAlias.hashKey())) {
					return createDuplicateTableNameErr(tTableAlias);
				}
				this.tableSymbols.put(tTableAlias.hashKey(), table);
			}
		}
		return err;
	}

	/**
	 * Create compiler error when no unique table can be found for attribute
	 * 
	 * @param tAttribute
	 * @return
	 */
	private Error createAttNotUniqueErr(TokenAttribute tAttribute) {
		String args[] = { tAttribute.toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_ATTRIBUTE_NOT_UNIQUE,
				args);
		return error;
	}

	/**
	 * Create compile error when attribute is not in table
	 * 
	 * @param tAttribute
	 * @param tTable
	 * @return
	 */
	private Error createAttNotInTableErr(TokenAttribute tAttribute,
			TokenTable tTable) {
		String args[] = { tAttribute.getName().toSqlString(),
				tTable.getName().toSqlString() };
		Error error = new Error(
				EnumError.COMPILER_SELECT_ATTRIBUTE_NOT_IN_TABLE, args);
		return error;
	}

	/**
	 * Create compile error when attribute was not found in any table
	 * 
	 * @param tAttribute
	 * @return
	 */
	private Error createAttInNoTableErr(TokenAttribute tAttribute) {
		String args[] = { tAttribute.getName().toSqlString() };
		Error error = new Error(
				EnumError.COMPILER_SELECT_ATTRIBUTE_IN_NO_TABLE, args);
		return error;
	}

	/**
	 * Create compile error for duplicate table names in SelectStmt
	 * 
	 * @param tTable
	 * @return
	 */
	private Error createDuplicateTableNameErr(TokenIdentifier tTable) {
		String args[] = { tTable.toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_DUPLICATE_TABLE, args);
		return error;
	}

	/**
	 * Create compile error for duplicate attribute names in SelectStmt
	 * 
	 * @param tAttribute
	 * @return
	 */
	private Error createDuplicateAttNameErr(TokenIdentifier tAttribute) {
		String args[] = { tAttribute.toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_DUPLICATE_ATTRIBUTE,
				args);
		return error;
	}

	/**
	 * Create compile error for missing alias in expression which are simple
	 * attributes
	 * 
	 * @param tExprAlias
	 * @return
	 */
	private Error createNoExprAliasErr(AbstractExpression tExprAlias) {
		String args[] = { tExprAlias.toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_ALIAS_MISSING, args);
		return error;
	}
	
	/**
	 * Create compile error for missing variable in cache
	 * @param tv
	 * @return
	 */
	private Error createVariableNotDeclared(TokenTable tv) {
		String args[] = { tv.getName().toString() };
		Error error = new Error(EnumError.COMPILER_FUNCTION_VAR_NOT_DECLARED,
				args);
		return error;
	}

	@Override
	public Error execute() {
		Error err = new Error();

		return err;
	}

	@Override
	public String toString() {
		StringBuffer sqlValue = new StringBuffer();

		sqlValue.append(AbstractToken.SELECT);

		int i = 0;
		for (AbstractExpression tExpr : this.tSelExpr) {
			if (i != 0)
				sqlValue.append(AbstractToken.COMMA);

			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(tExpr);
			TokenIdentifier tExprAlias = this.tSelAliases.get(i);
			if (tExprAlias != null) {
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(AbstractToken.AS);
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(tExprAlias);
				sqlValue.append(AbstractToken.BLANK);
			}
			++i;
		}

		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(AbstractToken.FROM);
		sqlValue.append(AbstractToken.BLANK);

		i = 0;
		for (TokenTable tTable : this.tTables) {
			if (i != 0)
				sqlValue.append(AbstractToken.COMMA);

			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(tTable);

			TokenIdentifier tTableAlias = this.tTableAliases.get(i);
			if (tTableAlias != null) {
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(AbstractToken.AS);
				sqlValue.append(AbstractToken.BLANK);
				sqlValue.append(tTableAlias);
			}

			++i;
		}

		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(AbstractToken.WHERE);
		sqlValue.append(AbstractToken.BLANK);
		sqlValue.append(this.tWherePredicate.toString());

		return sqlValue.toString();
	}
}
