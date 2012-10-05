package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.FunSQLCompiler;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.SimpleProjection;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.EnumOperandType;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenSchema;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.EnumDatabaseObject;
import org.xdb.metadata.Schema;
import org.xdb.metadata.Table;

public class SelectStmt extends AbstractServerStmt {

	private Vector<AbstractExpression> tExpressions = new Vector<AbstractExpression>();
	private Vector<TokenIdentifier> tExprAliases = new Vector<TokenIdentifier>();

	private Vector<TokenTable> tTables = new Vector<TokenTable>();
	private Vector<TokenIdentifier> tTableAliases = new Vector<TokenIdentifier>();
	private AbstractPredicate tPredicate;
	
	private HashMap<String, Schema> schemaSymbols = new HashMap<String, Schema>();
	private HashMap<String, Table> tableSymbols = new HashMap<String, Table>();
	private HashMap<String, Attribute> attSymbols = new HashMap<String, Attribute>();
	private CompilePlan plan = new CompilePlan();
	private Vector<AbstractPredicate> selectionPreds = new Vector<AbstractPredicate>();
	private AbstractOperator lastOp = null;
	

	// constructors
	public SelectStmt() {
		this.statementType = EnumStatement.SELECT;
	}

	// getters and setters
	public void addExpression(AbstractExpression expr) {
		this.tExpressions.add(expr);
		this.tExprAliases.add(null);
	}

	public Collection<AbstractExpression> getExpressions() {
		return this.tExpressions;

	}

	public void setExpressionAlias(int i, TokenIdentifier alias) {
		this.tExprAliases.set(i, alias);
	}

	public Collection<TokenIdentifier> getExpressionAliases() {
		return this.tExprAliases;
	}

	public void addTable(TokenTable table) {
		this.tTables.add(table);
		this.tTableAliases.add(null);
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

	public AbstractPredicate getPredicate() {
		return tPredicate;
	}

	public void setPredicate(AbstractPredicate tPredicate) {
		this.tPredicate = tPredicate;
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

		// 3. check attributes and and create attribute symbols
		err = checkAttributes();
		if (err.isError())
			return err;

		// 4. Create plan
		err = this.canonicalTransalation(plan);

		return err;
	}

	/**
	 * Create compile plan
	 * 
	 * @param plan
	 * @return
	 */
	private Error canonicalTransalation(CompilePlan plan) {
		Error err = new Error();

		//from clause
		err = createFromPlan();
		if(err.isError())
			return err;
		
		//where clause
		for(AbstractPredicate predicate: this.selectionPreds){
			GenericSelection selectOp = new GenericSelection(lastOp);
			selectOp.setPredicate(predicate);
			plan.addOperator(selectOp, false);
			lastOp = selectOp;
		}
		
		//select clause
		SimpleProjection projectOp = new SimpleProjection(lastOp, this.tExpressions.size());
		for(int i=0; i < this.tExpressions.size(); ++i){
			AbstractExpression tExpr = this.tExpressions.get(i);
			
			if(tExpr.getType()==EnumExprType.SIMPLE_EXPRESSION){
				projectOp.setExpression(i, (SimpleExpression)tExpr);
			}
			else{
				return createExprNotSupportedErr(tExpr);
			}
			
		}
		plan.addOperator(projectOp, true);
		
		return err;
	}

	/**
	 * Create join plan for from clause
	 * @param lastOp
	 * @return
	 */
	private Error createFromPlan(){
		Error err = new Error();

		//no join required
		if(this.tTables.size()==1){
			TokenTable tTable = tTables.get(0);
			Table table = this.tableSymbols.get(tTable.getName().hashKey());
			TableOperator tableOp = new TableOperator(tTable);
			tableOp.setTable(table);
			plan.addOperator(tableOp, false);
			selectionPreds.addAll(this.tPredicate.splitAnd());
			lastOp = tableOp;
		}
		//equi join required
		else{
			Collection<AbstractPredicate> predicates = this.tPredicate.splitAnd();
			HashMap<String, TableOperator> tableOps = new HashMap<String, TableOperator>();
			
			//find all equi-join predicates
			int joinPreds = 0;
			for(AbstractPredicate predicate: predicates){
				
				if(predicate.isEquiJoinPredicate()){
					//found join predicate
					joinPreds++;
					
					//get join attributes
					TokenAttribute[] joinAtts = new TokenAttribute[2];
					int i=0;
					for(TokenAttribute tAttr: predicate.getAttributes()){
						joinAtts[i] = tAttr;
						i++;
					}
					
					TokenTable tLeftTable = joinAtts[0].getTable();
					TokenTable tRightTable = joinAtts[1].getTable();
					
					TableOperator leftTableOp = tableOps.get(tLeftTable.getName().hashKey());
					TableOperator rightTableOp = tableOps.get(tRightTable.getName().hashKey());
					
					//left table not yet in join path?
					boolean newLeftTable = false;
					if(leftTableOp==null){
						Table leftTable = this.tableSymbols.get(tLeftTable.getName().hashKey());
						leftTableOp = new TableOperator(tLeftTable);
						leftTableOp.setTable(leftTable);
						plan.addOperator(leftTableOp, false);
						tableOps.put(tLeftTable.getName().hashKey(), leftTableOp);
						newLeftTable = true;
					}
					
					//right table not yat in join path?
					boolean newRightTable = false;
					if(rightTableOp==null){
						Table rightTable = this.tableSymbols.get(tRightTable.getName().hashKey());
						rightTableOp = new TableOperator(tRightTable);
						rightTableOp.setTable(rightTable);
						plan.addOperator(rightTableOp, false);
						tableOps.put(tRightTable.getName().hashKey(), rightTableOp);
						newRightTable = true;
					}
					
					EquiJoin joinOp = null;
					//both tables L and R are new: L JOIN R
					if(newLeftTable && newRightTable){
						joinOp = new EquiJoin(leftTableOp, rightTableOp, joinAtts[0], joinAtts[1]);
					}
					//only left table is new: LastOp JOIN L (to create left deep plan)
					else if(newLeftTable){
						joinOp = new EquiJoin(lastOp, leftTableOp, joinAtts[1], joinAtts[0]);
					}
					//only right table is new: LastOp JOIN R
					else{
						joinOp = new EquiJoin(lastOp, rightTableOp, joinAtts[0], joinAtts[1]);
					}
					plan.addOperator(joinOp, false);
					lastOp = joinOp;
				}
				else{
					//keep remaining predicates!
					selectionPreds.add(predicate);
				}
			}
			
			if(joinPreds+1 != this.tTables.size()){
				return FunSQLCompiler.createGenericCompileErr("Cartesian product not in SQL statement supported!");
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
		for (AbstractExpression tExpr : this.tExpressions) {
			for (TokenAttribute tAtt : tExpr.getAttributes()) {
				err = checkAttribute(tAtt);
				if (err.isError())
					return err;
			}
		}

		// 2. Where Clause
		if (this.tPredicate != null) {
			for (TokenAttribute tAtt : this.tPredicate.getAttributes()) {
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
			
			if(att==null){
				return this.createAttInNoTableErr(tAtt);
			}
			
			this.attSymbols.put(tAtt.getName().hashKey(), att);
		}
		// table name given in attribute token
		else {
			Table table = this.tableSymbols.get(tTable.getName().hashKey());

			// find attribute
			Attribute att = table.getAttribute(tAtt.getName().toSqlString());
			if (att == null) {
				return createAttNotInTableErr(tAtt, tTable);
			}
			this.attSymbols.put(tAtt.getName().hashKey(), att);
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
		HashSet<String> selectAtts = new HashSet<String>();

		// 1. Select Clause
		for (int i = 0; i < this.tExpressions.size(); ++i) {
			AbstractExpression tExpr = this.tExpressions.get(i);
			TokenIdentifier tExprAlias = this.tExprAliases.get(i);

			// Check aliasing
			TokenAttribute exprAtt = null;
			if (tExpr.getType() == EnumExprType.SIMPLE_EXPRESSION) {
				SimpleExpression tSimpleExpr = (SimpleExpression) tExpr;

				if (tSimpleExpr.getOper().getType() == EnumOperandType.ATTRIBUTE) {
					exprAtt = (TokenAttribute) tSimpleExpr.getOper();
				}
			}
			// Alias for complex expression is missing
			if (exprAtt == null && tExprAlias == null) {
				return createNoExprAliasErr(tExpr);
			}
			// Check for duplicates based on attribute name
			else if (exprAtt != null && tExprAlias == null) {
				if (selectAtts.contains(exprAtt.getName().hashKey())) {
					return this.createDuplicateAttNameErr(exprAtt.getName());
				}
				selectAtts.add(exprAtt.getName().hashKey());
			}
			// Check for duplicates based on alias
			else {
				if (selectAtts.contains(tExprAlias.hashKey())) {
					return this.createDuplicateAttNameErr(tExprAlias);
				}
				selectAtts.add(tExprAlias.hashKey());
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

			TokenSchema tSchema = tTable.getSchema();
			Schema schema = Catalog.getSchema(tSchema.hashKey());

			// check for non existing schema names
			if (schema == null) {
				return Catalog.createObjectNotExistsErr(tSchema.toSqlString(),
						EnumDatabaseObject.SCHEMA);
			}
			this.schemaSymbols.put(schema.hashKey(), schema);

			Table table = Catalog.getTable(tTable.hashKey(schema.getOid()));

			// check for non existing table names
			if (table == null) {
				return Catalog.createObjectNotExistsErr(tTable.toSqlString(),
						EnumDatabaseObject.TABLE);
			}

			// check for duplicate table names
			if (tTableAlias != null) {
				if (this.tableSymbols.containsKey(tTableAlias.hashKey())) {
					return createDuplicateTableNameErr(tTableAlias);
				}
				this.tableSymbols.put(tTableAlias.hashKey(), table);
			}
			else{
				if (this.tableSymbols.containsKey(tTable.getName().hashKey())) {
					return createDuplicateTableNameErr(tTable.getName());
				}
				this.tableSymbols.put(tTable.getName().toSqlString(), table);
			}
		}
		return err;
	}
	
	/**
	 * Create compiler error when expression is not supported
	 * @param expr
	 * @return
	 */
	private Error createExprNotSupportedErr(AbstractExpression expr) {
		String args[] = { expr.toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_EXPRESSION_NOT_SUPPORTED, args);
		return error;
	}
	
	/**
	 * Create compiler error when no unique table can be found for attribute
	 * 
	 * @param tAttribute
	 * @return
	 */
	private Error createAttNotUniqueErr(TokenAttribute tAttribute) {
		String args[] = { tAttribute.toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_ATTRIBUTE_NOT_UNIQUE, args);
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
		String args[] = { tAttribute.getName().toSqlString(), tTable.getName().toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_ATTRIBUTE_NOT_IN_TABLE, args);
		return error;
	}

	/**
	 * Create compile error when attribute was not found in any table
	 * @param tAttribute
	 * @return
	 */
	private Error createAttInNoTableErr(TokenAttribute tAttribute) {
		String args[] = { tAttribute.getName().toSqlString() };
		Error error = new Error(EnumError.COMPILER_SELECT_ATTRIBUTE_IN_NO_TABLE, args);
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
		for (AbstractExpression tExpr : this.tExpressions) {
			if (i != 0)
				sqlValue.append(AbstractToken.COMMA);

			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(tExpr);
			TokenIdentifier tExprAlias = this.tExprAliases.get(i);
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
		sqlValue.append(this.tPredicate.toString());

		return sqlValue.toString();
	}
}
