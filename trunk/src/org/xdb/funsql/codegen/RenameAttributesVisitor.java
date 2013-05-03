package org.xdb.funsql.codegen;

import java.util.HashMap;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;

/**
 * @author a.c.mueller
 * This class is a tree visitor that visits the complete compile plan before it's split into
 * tracker plans. The purpose of this plan is to rerename some operators which directly
 * access tables, in order so realize a table entering without subselects
 * 
 */
public class RenameAttributesVisitor extends AbstractBottomUpTreeVisitor {

	private Error e = new Error();
	//maps and lists for renaming, to identify already renamed ops and attributes
	private HashMap<String, String> renamedAttributes;
	private Vector<String> renamedOps;
	
	public RenameAttributesVisitor(AbstractCompileOperator root) {
		super(root);
		this.renamedOps = new Vector<String>();
		this.renamedAttributes = new HashMap<String, String>();
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {	 	
	
		//rename join Predicates
			
		//Rename ResultSet
		renameResultSet(ej);
		return e;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		renameResultSet(ej);
		return e;
	}


	@Override
	public Error visitGenericSelection(GenericSelection gs) {

		renameResultSet(gs);
		//rename predicates based on already renamed attributes
		ReReNamePredicateVisitor rPv = new ReReNamePredicateVisitor(gs.getPredicate(), renamedAttributes);

		return rPv.visit();
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation ga) {

		Vector<AbstractExpression> exprs = new  Vector<AbstractExpression>();
		exprs.addAll(ga.getAggregationExpressions());
		exprs.addAll(ga.getGroupExpressions());
		for(AbstractExpression expr: exprs){
			ReReNameExpressionVisitor renameVisitor = new ReReNameExpressionVisitor(expr, renamedAttributes);
			e = renameVisitor.visit();
			if(e.isError())
				return e;
		}

		return e;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {

		for(AbstractExpression expr: gp.getExpressions()){
			ReReNameExpressionVisitor renameVisitor = new ReReNameExpressionVisitor(expr, renamedAttributes);
			e = renameVisitor.visit();
			if(e.isError())
				return e;
		}

		return e;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		//rename Table Op
		renameTableOp(to);

		return e;
	}

	@Override
	public Error visitRename(Rename ro) {

		renameResultSet(ro);

		return e;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		renameResultSet(absOp);
		return e;
	}
	
	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		renameResultSet(absOp);
		
		return e;
	}
	

	/**
	 * This Method renames the result set of a given operator. The idiviual Attributes
	 * from the result set are only renamed when there is a need to, which means
	 * that the source op of the attribute is a table
	 * @param ej AbstractCompileOperator Opertor to rename the resultset for
	 */
	private void renameResultSet(AbstractCompileOperator ej) {
		// call operator method
		boolean renamed = ej.renameOperator(renamedAttributes,renamedOps);
		// if one element was renamed than add the opp to renamed ops
		if(renamed){
			this.renamedOps.add(ej.getOperatorId().toString());
		}
	}
	
	/**
	 * Renames the result set of a table operator to avoid necessary subselect renaming
	 * @param tableOp Table to Rename
	 */
	private void renameTableOp(TableOperator tableOp) {
		//TODO check for federated tables
		String newName= null;
		//add to renamed list
		this.renamedOps.add(tableOp.getOperatorId().toString());
		for(TokenAttribute tA : tableOp.getResult().getAttributes()){
			newName = removeTableNameFromName(tA.getName().getName(), tableOp.getTableName());
			//put to renamed Attributes for predicate and expression visitor
			renamedAttributes.put(tA.getName().getName(), newName);
			tA.setName(newName);
		}

	}
	
	/**
	 * This method remove the tableName from a given oldname variable
	 * @param oldName string from which the name needs to be removed
	 * @param tableName name to be removed
	 * @return oldName without Table in
	 */
	private String removeTableNameFromName(String oldName, String tableName){

		oldName = oldName.replaceFirst(tableName+"_", "");
		return oldName;
	}
	
	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		renameResultSet(deOp);
		return e;
	}
}