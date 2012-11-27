package org.xdb.funsql.compile.analyze.operator;

import java.util.Map;
import java.util.Vector;

import org.xdb.error.Error;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.FunctionCall;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
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
public class CreateResultVisitor extends AbstractBottomUpTreeVisitor {
	private Map<AbstractToken, EnumSimpleType> types;
	
	public CreateResultVisitor(AbstractCompileOperator root,
			Map<AbstractToken, EnumSimpleType> types) {
		super(root);
		this.types = types;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		Error e = new Error();
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
		return e;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		Error e = new Error();
		ResultDesc rDesc = gs.getChild().getResult(0).clone();

		for(TokenAttribute att: rDesc.getAttributes()){
			att.setTable(gs.getOperatorId().toString());
		}
		
		gs.addResult(rDesc);
		return e;
	}

	@Override
	public Error visitFunctionCall(FunctionCall fc) {
		Error e = new Error();
		// TODO Implement result creation for function call
		return e;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation ga) {
		Error e = new Error();
		ResultDesc rDesc = new ResultDesc();
		Vector<TokenIdentifier> aliases = ga.getAliases();
		
		for(TokenIdentifier alias: aliases){
			String attName = alias.getName();
			TokenAttribute att = new TokenAttribute(attName);
			EnumSimpleType type = this.types.get(att);
			att.setTable(ga.getOperatorId().toString());
			rDesc.addAttribute(att);
			rDesc.addType(type);
		}
		
		ga.addResult(rDesc);
		return e;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		Error e = new Error();
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
		return e;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		Error e = new Error();
		Table table = to.getTable();
		ResultDesc rDesc = new ResultDesc();
		
		for(Attribute attr: table.getAttributes()){
			String attName = ResultDesc.createResultAtt(to.getTableName(), attr.getName());
			TokenAttribute att = new TokenAttribute(attName);
			att.setTable(to.getOperatorId().toString());
			rDesc.addAttribute(att);
			rDesc.addType(attr.getDataType());
		}
		
		to.addResult(rDesc);
		return e;
	}
	
	@Override
	public Error visitRename(Rename ro) {
		Error e = new Error();
		return e;
	}
}
