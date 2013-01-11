package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

import org.xdb.client.MasterTrackerClient;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.analyze.Analyzer;
import org.xdb.funsql.compile.analyze.FunctionCache;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.funsql.optimize.Optimizer;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Connection;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;
import org.xdb.store.EnumStore;
import org.xdb.utils.FlagElem;
import org.xdb.utils.FlaggedElemVector;
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
	private HashMap<String, Table> varSymbols = new HashMap<String, Table>();
	private Vector<String> usedVariables = new Vector<String>();

	private CompilePlan plan = new CompilePlan();
	FunctionCache fcache = FunctionCache.getCache();

	// temporary compiler variables
	private int lastInternalAlias = 0;
	private Identifier internalAlias = new Identifier("_INT_ALIAS");
	private HashMap<AbstractExpression, TokenIdentifier> internalAliases = new HashMap<AbstractExpression, TokenIdentifier>();
	private Vector<AbstractPredicate> selectionPreds = new Vector<AbstractPredicate>();
	private AbstractCompileOperator lastOp = null;

	// constructors
	public SelectStmt() {
		this.statementType = EnumStatement.SELECT;
	}

	// getters and setters
	public void addVarSymbols(Map<String, Table> varSymbols) {
		this.varSymbols.putAll(varSymbols);
	}

	public void addSelExpression(AbstractExpression expr) {
		this.tSelExpr.add(expr);

		if (expr.isAttribute()) {
			TokenAttribute att = expr.getAttribute();
			this.tSelAliases.add(att.getName());
		} else {
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

	public Vector<String> getUsedVariables() {
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

		return err;
	}

	/**
	 * Generates alias for internal expressions
	 * 
	 * @param expr
	 */
	private Identifier generateInternalAlias(AbstractExpression expr) {
		Identifier internalAlias = this.internalAlias.clone().append(
				++this.lastInternalAlias);
		this.internalAliases.put(expr,
				new TokenIdentifier(internalAlias.toString()));
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

			if (this.tHavingPredicate != null) {
				for (AbstractExpression aggExpr : this.tHavingPredicate
						.getAggregations()) {
					aggOp.addAggregationExpression(aggExpr);
					Identifier internalAlias = generateInternalAlias(aggExpr);
					aggOp.addAlias(new TokenIdentifier(internalAlias.toString()));
				}
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
			selectOp.setPredicate(this.tHavingPredicate
					.replaceAliases(this.internalAliases));
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
			} else {
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
			TableOperator tableOp = new TableOperator(tTableAlias);

			// add meta data to plan
			err = this.addTableToPlan(tableOp);
			if (err.isError())
				return err;

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
	
			//init new data structure with helper classes
			AbstractPredicate predicate_t = null;
			//convert HashSet into vector
			Vector<AbstractPredicate> predicates_vector = new Vector<AbstractPredicate>();
			Vector<AbstractCompileOperator> joinOps = new Vector<AbstractCompileOperator>();
			
			for (AbstractPredicate predicate : predicates) {
				predicates_vector.add(predicate);
			}
			//init necessary 
			FlagElem<AbstractPredicate> f_predicate;
			FlaggedElemVector<AbstractPredicate> f_predicates_vector = new FlaggedElemVector<AbstractPredicate>(
					predicates_vector);
			
			int index = 0;
			//checker for first element
			boolean first = true;
			while (f_predicates_vector.hasfalseElems()) {
				// get the flagged predicate
				f_predicate = f_predicates_vector.getFlaggedElem(index);
				
				// check wether this predicate was already used then continue
				if (f_predicate.isFlag()) {
					index = (index + 1) % f_predicates_vector.getSize();
					continue;
				}
				// get predicate without flag
				predicate_t = f_predicate.getElem();
				if (predicate_t.isEquiJoinPredicate()) {
					// found join predicate

					// get join attributes
					TokenAttribute[] joinAtts = new TokenAttribute[2];
					int i = 0;
					for (TokenAttribute tAttr : predicate_t.getAttributes()) {
						joinAtts[i] = tAttr;
						i++;
					}

					TokenTable tLeftTable = joinAtts[0].getTable();
					TokenTable tRightTable = joinAtts[1].getTable();

					TableOperator leftTableOp = tableOps.get(tLeftTable
							.getName().hashKey());
					TableOperator rightTableOp = tableOps.get(tRightTable
							.getName().hashKey());

					// now check when if last op is null and is equijoin
					// operator
					// if no table operators exist for those elements there is no need for checking for
					// joint ops because the table was never accessed and consequently no conflict can arise
					
					if (leftTableOp == null && rightTableOp == null) {
						// case one both are not accessed yet
						// if first ok when not continue
						if (!first) {
							index = (index + 1) % f_predicates_vector.getSize();
							continue;
						}
						first = false;
					} else if ((leftTableOp == null && rightTableOp != null)
							|| (leftTableOp != null && rightTableOp == null)) {
						// case two one is already accessed
						if (joinOps.size() != 0) {
							// check wether a suitable join is available
							// find the highest level join op for a a already accessed Table
							boolean tester = true;
							AbstractCompileOperator op;
							// Could also be done recusive in seperate method
							if (leftTableOp != null) {
								op = leftTableOp;
							} else {
								op = rightTableOp;
							}
							while (tester) {
								if (op.getParents().size() == 0) {
									break;
								} else {
									op = op.getParents().get(0);
								}
							}
							lastOp = op;
						}// size Checker
					} else {
						// case three both are accessed

						// predicate is not a join predicate any more, but a
						// selection
						f_predicates_vector.setUsed(index);
						selectionPreds.add(predicate_t);
						index = (index + 1) % f_predicates_vector.getSize();
						continue;

					}// used check

					// set this predicate ot be userd
					f_predicates_vector.setUsed(index);

					// join pred is used so increment it for last check
					joinPreds++;
					// left table not yet in join path?
					boolean newLeftTable = false;
					if (leftTableOp == null) {
						leftTableOp = new TableOperator(tLeftTable.getName());

						err = this.addTableToPlan(leftTableOp);
						if (err.isError())
							return err;

						tableOps.put(tLeftTable.getName().hashKey(),
								leftTableOp);
						newLeftTable = true;
					}

					// right table not yet in join path?
					boolean newRightTable = false;
					if (rightTableOp == null) {
						rightTableOp = new TableOperator(tRightTable.getName());

						err = this.addTableToPlan(rightTableOp);
						if (err.isError())
							return err;

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
					//add a lastOp to list
					joinOps.add(joinOp);
					lastOp = joinOp;

				} else {
					// keep remaining predicates!
					selectionPreds.add(predicate_t);
					f_predicates_vector.setUsed(index);
				}
				index = (index + 1) % f_predicates_vector.getSize();
			}
		
			if (joinPreds + 1 != this.tTables.size()) {
				return FunSQLCompiler
						.createGenericCompileErr("Cartesian product not in SQL statement supported!");
			}

		}

		return err;
	}

	/**
	 * Adds table and connection info to plan for table operator
	 * 
	 * @param tableOp
	 * @param tTableAlias
	 */
	private Error addTableToPlan(TableOperator tableOp) {
		// set table
		Table table = this.tableSymbols.get(tableOp.getTokenTable().hashKey());
		tableOp.setTable(table);

		// set connection
		if (!table.isTemp()) {
			Connection conn = Catalog.getConnection(table.getConnectionOid());
			tableOp.setConnection(conn);
			if (!conn.getStore().equals(EnumStore.XDB)) {
				String args[] = { "Store of type " + conn.getStore()
						+ " not supported" };
				return new Error(EnumError.COMPILER_GENERIC, args);
			}
		}

		// add table op to plan
		this.plan.addOperator(tableOp, false);

		return new Error();
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
			Table table = null;

			if (tTable.isVariable()) {
				if (!this.varSymbols.containsKey(tTable.hashKey())) {
					return createVariableNotDeclared(tTable);
				}

				table = this.varSymbols.get(tTable.hashKey());
				this.usedVariables.add(tTable.hashKey());
			} else {
				TokenSchema tSchema = tTable.getSchema();
				Schema schema = Catalog.getSchema(tSchema.hashKey());

				// check for non existing schema names
				if (schema == null) {
					return Catalog.createObjectNotExistsErr(
							tSchema.toSqlString(), EnumDatabaseObject.SCHEMA);
				}
				this.schemaSymbols.put(schema.hashKey(), schema);

				table = Catalog.getTable(tTable.hashKey(schema.getOid()));

				// check for non existing table names
				if (table == null) {
					return Catalog.createObjectNotExistsErr(
							tTable.toSqlString(), EnumDatabaseObject.TABLE);
				}
			}
			// check for duplicate table names
			if (this.tableSymbols.containsKey(tTableAlias.hashKey())) {
				return createDuplicateTableNameErr(tTableAlias);
			}
			this.tableSymbols.put(tTableAlias.hashKey(), table);
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
	 * Create compile error for missing variable
	 * 
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
	public Error optimize() {
		Error err = new Error();
		Optimizer opti = new Optimizer(this.plan);
		opti.optimize();
		return err;
	}

	@Override
	public Error execute() {
		MasterTrackerClient client = new MasterTrackerClient();
		Error err = client.executePlan(this.plan);
		if (err.isError())
			return err;

		return new Error();
	}
}
