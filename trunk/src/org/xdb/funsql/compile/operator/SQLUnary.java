package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.codegen.ReReNameExpressionVisitor;
import org.xdb.funsql.codegen.ReReNamePredicateVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.utils.SetUtils;
import org.xdb.utils.StringTemplate;

/**
 * Coarse-grained Operator which is used during optimization to combine several
 * fine-grained operators => Generate less SQL statements for one plan!
 * 
 * @author cbinnig
 * 
 */
public class SQLUnary extends AbstractUnaryOperator {

	private static final long serialVersionUID = 2611698550463734434L;

	// templates for SQL generation
	private final StringTemplate selectTemplate = new StringTemplate(
			"SELECT <SELECT> ");

	private final StringTemplate fromTemplate = new StringTemplate(
			"FROM (<<OP1>>) AS <OP1> ");
	private final StringTemplate fromTemplate2 = new StringTemplate(
			"FROM <<OP1>> AS <OP1> ");
	private final StringTemplate whereTemplate = new StringTemplate(
			" WHERE <WHERE> ");
	private final StringTemplate groupTemplate = new StringTemplate(
			"GROUP BY <GROUP> ");
	private final StringTemplate havingTemplate = new StringTemplate(
			" HAVING <HAVING>");

	// select clause
	private Vector<AbstractExpression> selectExpressions = new Vector<AbstractExpression>();
	private Vector<TokenIdentifier> selectAliases = new Vector<TokenIdentifier>();
	private Vector<AbstractExpression> aggExpressions = new Vector<AbstractExpression>();

	// where clause
	private AbstractPredicate wherePred;

	// having clause
	private AbstractPredicate havingPred;

	// group by clause
	private Vector<AbstractExpression> groupExpressions = new Vector<AbstractExpression>();

	// other info
	private Map<TokenIdentifier, AbstractExpression> replaceExprMap = new HashMap<TokenIdentifier, AbstractExpression>();
	private int countOps = 0;
	private boolean addedLastOp = true;

	public SQLUnary(AbstractCompileOperator child) {
		super(child);
		
		this.type = EnumOperator.SQL_UNARY;

		// initialize from child
		for (TokenAttribute att : child.getResult().getAttributes()) {
			SimpleExpression expr = new SimpleExpression(att);
			this.replaceExprMap.put(att.getName(), expr);
		}
	}

	public int countOperators() {
		return this.countOps;
	}

	//methods
	@Override
	public String toSqlString() {
		// check for missing info
		if (this.selectExpressions.size() == 0)
			return null;

		final HashMap<String, String> vars = new HashMap<String, String>();
		StringBuffer sqlStmt = new StringBuffer();

		// select clause
		final Vector<String> selExprVec = new Vector<String>(
				this.selectExpressions.size());
		final Vector<String> selAliasVec = new Vector<String>(
				this.selectAliases.size());
		int i = 0;
		while (i < this.selectExpressions.size()) {
			selExprVec.add(this.selectExpressions.get(i).toSqlString());
			selAliasVec.add(this.selectAliases.get(i).toSqlString());
			i++;
		}
		vars.put("SELECT", SetUtils.buildAliasString(selExprVec, selAliasVec));
		sqlStmt.append(selectTemplate.toString(vars));

		// from clause
		vars.clear();
		//whether use table or other complex template
		vars.put("OP1", getChild().getOperatorId().toString());
		if(getChild().getType().equals(EnumOperator.TABLE)){
			sqlStmt.append(fromTemplate2.toString(vars));
		}else {
			sqlStmt.append(fromTemplate.toString(vars));
		}

		

		// where clause
		sqlStmt.append(getWhereClause());
		// having clause

		sqlStmt.append(getHavingClause());
		// group-by clause

		sqlStmt.append(getGroupByClause());
		return sqlStmt.toString();
	}

	
	public String getHavingClause(){
		final HashMap<String, String> vars = new HashMap<String, String>();
		if (this.havingPred != null) {
			vars.put("HAVING", this.havingPred.toSqlString());
			return havingTemplate.toString(vars);
		}
		return "";
	}
	
	public String getWhereClause(){
		final HashMap<String, String> vars = new HashMap<String, String>();
		if (this.wherePred != null) { 
			vars.put("WHERE", this.wherePred.toSqlString());
			return whereTemplate.toString(vars);
		}
		return "";
	}
	
	public String getGroupByClause(){
		final HashMap<String, String> vars = new HashMap<String, String>();
		if (this.groupExpressions.size() > 0) {
			final Vector<String> groupExprVec = new Vector<String>(
					this.groupExpressions.size());
			for (AbstractExpression exp : this.groupExpressions) {
				groupExprVec.add(exp.toSqlString());
			}
			vars.put("GROUP", SetUtils.buildString(groupExprVec));
			return(groupTemplate.toString(vars));
		}
		return "";
	}
	/**
	 * Adds a selection operator to the combined operator
	 * 
	 * @param op
	 */
	public boolean addSelection(GenericSelection op) {
		if (this.selectExpressions.size() > 0 || this.havingPred != null) {
			this.addedLastOp = false;
			return this.addedLastOp;
		}
		this.countOps++;

		// change where info
		if (this.wherePred != null || this.aggExpressions.size() > 0) {
			AbstractPredicate havingPred2 = op.getPredicate();
			this.havingPred = havingPred2
					.replaceExpressions(this.replaceExprMap);
		} else {
			AbstractPredicate wherePred2 = op.getPredicate();
			this.wherePred = wherePred2.replaceExpressions(this.replaceExprMap);
		}
		
		return this.addedLastOp;
	}

	/**
	 * Adds a projection to the combined operator
	 * 
	 * @param op
	 */
	public boolean addProjection(GenericProjection op) {
		if (this.selectExpressions.size() > 0) {
			this.addedLastOp = false;
			return this.addedLastOp;
		}
		this.countOps++;

		// add select info
		int i = 0;
		Map<TokenIdentifier, AbstractExpression> newReplaceMap = new HashMap<TokenIdentifier, AbstractExpression>();
		for (TokenAttribute att : op.getResult().getAttributes()) {
			this.selectAliases.add(att.getName());
			AbstractExpression newExpr = op.getExpression(i)
					.replaceExpressions(this.replaceExprMap);
			this.selectExpressions.add(newExpr);
			newReplaceMap.put(att.getName(), newExpr);
			i++;
		}

		// exchange replace map
		this.replaceExprMap = newReplaceMap;
		
		return this.addedLastOp;
	}

	/**
	 * Adds a rename operator to the combined operator
	 * 
	 * @param op
	 */
	public boolean addRename(Rename op) {
		this.countOps++;
		
		// add select info
		int i = 0;
		this.selectAliases.clear();
		Map<TokenIdentifier, AbstractExpression> newReplaceMap = new HashMap<TokenIdentifier, AbstractExpression>();
		for (TokenAttribute att : op.getResult().getAttributes()) {
			TokenIdentifier oldAliasName = op.getChild().getResult()
					.getAttribute(i).getName();
			this.selectAliases.add(att.getName());
			newReplaceMap.put(att.getName(),
					this.replaceExprMap.get(oldAliasName));
			i++;
		}

		// exchange replace map
		this.replaceExprMap = newReplaceMap;
		
		return this.addedLastOp;
	}

	/**
	 * Adds an aggregation to the combined operator
	 * 
	 * @param op
	 */
	public boolean addAggregation(GenericAggregation op) {
		if (this.aggExpressions.size() > 0 || this.havingPred != null) {
			this.addedLastOp = false;
			return this.addedLastOp;
		}
		this.countOps++;

		// add aggregation info
		int i = 0;
		this.selectAliases.clear();
		Map<TokenIdentifier, AbstractExpression> newReplaceMap = new HashMap<TokenIdentifier, AbstractExpression>();
		while (i < op.getAggregationExpressions().size()) {
			TokenAttribute att = op.getResult().getAttribute(i);
			this.selectAliases.add(att.getName());
			AbstractExpression newExpr = op.getAggregationExpression(i)
					.replaceExpressions(this.replaceExprMap);
			this.aggExpressions.add(newExpr);
			newReplaceMap.put(att.getName(), newExpr);
			i++;
		}

		// add grouping info
		int j = 0;
		while (j < op.getGroupExpressions().size()) {
			TokenAttribute att = op.getResult().getAttribute(i);
			this.selectAliases.add(att.getName());
			AbstractExpression newExpr = op.getGroupExpression(j)
					.replaceExpressions(this.replaceExprMap);
			this.groupExpressions.add(newExpr);
			newReplaceMap.put(att.getName(), newExpr);
			i++;
			j++;
		}

		// exchange replace map
		this.replaceExprMap = newReplaceMap;
		
		return this.addedLastOp;
	}

	@Override
	public void renameAttributes(String oldChildId, String newChildId) {
		// Nothing to do
	}

	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		// Nothing to do
	}
	
	
	
	@Override
	public boolean renameOperator(HashMap<String, String> renamedAttributes,
			Vector<String> renamedOps) {
		boolean renamed = super.renameOperator(renamedAttributes,renamedOps);
		Error e;
		for(AbstractExpression expr: this.aggExpressions){
			ReReNameExpressionVisitor renameVisitor = new ReReNameExpressionVisitor(expr, renamedAttributes);
			e = renameVisitor.visit();
			//TODO Error handling
		}
		for(AbstractExpression expr: this.groupExpressions){
			ReReNameExpressionVisitor renameVisitor = new ReReNameExpressionVisitor(expr, renamedAttributes);
			e = renameVisitor.visit();
			//TODO Error handling
		}
		
		for(AbstractExpression expr: this.selectExpressions){
			ReReNameExpressionVisitor renameVisitor = new ReReNameExpressionVisitor(expr, renamedAttributes);
			e = renameVisitor.visit();
			//TODO Error handling
		}
		
		//rename predicates based on already renamed attributes
		ReReNamePredicateVisitor rPv ;
		if(this.wherePred != null) {
			rPv = new ReReNamePredicateVisitor(this.wherePred, renamedAttributes);
			rPv.visit();
		}

	
		if(this.havingPred != null) {
			rPv = new ReReNamePredicateVisitor(this.havingPred, renamedAttributes);
			rPv.visit();
		}

		return renamed;
	}

	//getters and setters
	public Vector<AbstractExpression> getSelectExpressions() {
		return selectExpressions;
	}

	public void setSelectExpressions(Vector<AbstractExpression> selectExpressions) {
		this.selectExpressions = selectExpressions;
	}

	public Vector<TokenIdentifier> getSelectAliases() {
		return selectAliases;
	}

	public void setSelectAliases(Vector<TokenIdentifier> selectAliases) {
		this.selectAliases = selectAliases;
	}

	public Vector<AbstractExpression> getAggExpressions() {
		return aggExpressions;
	}

	public void setAggExpressions(Vector<AbstractExpression> aggExpressions) {
		this.aggExpressions = aggExpressions;
	}

	public AbstractPredicate getWherePred() {
		return wherePred;
	}

	public void setWherePred(AbstractPredicate wherePred) {
		this.wherePred = wherePred;
	}

	public AbstractPredicate getHavingPred() {
		return havingPred;
	}

	public void setHavingPred(AbstractPredicate havingPred) {
		this.havingPred = havingPred;
	}

	public Vector<AbstractExpression> getGroupExpressions() {
		return groupExpressions;
	}

	public void setGroupExpressions(Vector<AbstractExpression> groupExpressions) {
		this.groupExpressions = groupExpressions;
	}

	public Map<TokenIdentifier, AbstractExpression> getReplaceExprMap() {
		return replaceExprMap;
	}

	public void setReplaceExprMap(
			Map<TokenIdentifier, AbstractExpression> replaceExprMap) {
		this.replaceExprMap = replaceExprMap;
	}
	
	

}
