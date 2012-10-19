package org.xdb.funsql.compile.analyze;

import java.util.Map;
import java.util.Vector;

import org.xdb.funsql.compile.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Table;

/**
 * Builds result description for operators in a compile plan
 * @author cbinnig
 *
 */
public class CreateResultDesc extends AbstractBottomUpTreeVisitor {
	private Map<AbstractExpression, EnumSimpleType> types;
	
	public CreateResultDesc(AbstractOperator root,
			Map<AbstractExpression, EnumSimpleType> types) {
		super(root);
		this.types = types;
	}

	@Override
	public void visitEquiJoin(EquiJoin ej) {
		ResultDesc leftDesc = ej.getLeftChild().getResult(0).clone();
		ResultDesc rightDesc = ej.getRightChild().getResult(0).clone();
		
		//copy from right to left
		for(TokenAttribute att: rightDesc.getAttributes()){
			leftDesc.addAttribute(att);
		}
		for(EnumSimpleType type: rightDesc.getTypes()){
			leftDesc.addType(type);
		}
		
		//set new table names
		for(TokenAttribute att: leftDesc.getAttributes()){
			att.setTable(ej.getOperatorId().toString());
		}
		
		ej.addResult(leftDesc);
	}

	@Override
	public void visitGenericSelection(GenericSelection gs) {
		ResultDesc rDesc = gs.getChild().getResult(0).clone();

		for(TokenAttribute att: rDesc.getAttributes()){
			att.setTable(gs.getOperatorId().toString());
		}
		
		gs.addResult(rDesc);
	}

	@Override
	public void visitFunctionCall(FunctionCall fc) {
		// TODO Implement result creation for function call
	}

	@Override
	public void visitGenericAggregation(GenericAggregation ga) {
		ResultDesc rDesc = new ResultDesc();
		Vector<AbstractExpression> exprs = ga.getAggregationExpressions();
		exprs.addAll(ga.getGroupExpressions());
		Vector<TokenIdentifier> aliases = ga.getAliases();
		
		for(int i=0; i<exprs.size(); ++i){
			AbstractExpression expr = exprs.get(i);
			EnumSimpleType type = this.types.get(expr);
			TokenIdentifier alias = aliases.get(i);
			
			String attName = alias.getName();
			TokenAttribute att = new TokenAttribute(attName);
			att.setTable(ga.getOperatorId().toString());
			rDesc.addAttribute(att);
			rDesc.addType(type);	
		}
		
		ga.addResult(rDesc);
	}

	@Override
	public void visitGenericProjection(GenericProjection gp) {
		ResultDesc rDesc = new ResultDesc();
		Vector<AbstractExpression> exprs = gp.getExpressions();
		Vector<TokenIdentifier> aliases = gp.getAliases();
		
		for(int i=0; i<exprs.size(); ++i){
			AbstractExpression expr = exprs.get(i);
			EnumSimpleType type = this.types.get(expr);
			TokenIdentifier alias = aliases.get(i);
			
			String attName = alias.getName();
			TokenAttribute att = new TokenAttribute(attName);
			att.setTable(gp.getOperatorId().toString());
			rDesc.addAttribute(att);
			rDesc.addType(type);	
		}
		
		gp.addResult(rDesc);
	}

	@Override
	public void visitTableOperator(TableOperator to) {
		Table table = to.getTable();
		ResultDesc rDesc = new ResultDesc();
		
		for(Attribute attr: table.getAttributes()){
			String attName = this.createResultAtt(to.getTableName(), attr.getName());
			TokenAttribute att = new TokenAttribute(attName);
			att.setTable(to.getOperatorId().toString());
			rDesc.addAttribute(att);
			rDesc.addType(attr.getDataType());
		}
		
		to.addResult(rDesc);
	}

	private String createResultAtt(String table, String att){
		StringBuffer name = new StringBuffer(table);
		name.append(AbstractToken.UNDERSCORE);
		name.append(att);
		return name.toString();
	}
}
