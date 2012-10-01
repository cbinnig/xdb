package org.xdb.funsql.statement;

import java.util.Collection;
import java.util.Vector;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.predicate.AbstractPredicate;
import org.xdb.funsql.compile.predicate.ComplexPredicate;
import org.xdb.funsql.compile.predicate.SimplePredicate;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.metadata.Attribute;
import org.xdb.metadata.Catalog;
import org.xdb.metadata.Table;
import org.xdb.utils.Identifier;

public class SelectStmt extends AbstractServerStmt {
	private Vector<AbstractExpression> tExpressions = new Vector<AbstractExpression>();
	private Vector<TokenIdentifier> tExprAliases = new Vector<TokenIdentifier>();	
	private Vector<TokenTable> tTables = new Vector<TokenTable>();
	private Vector<TokenIdentifier> tTableAliases = new Vector<TokenIdentifier>();
	
	private AbstractPredicate tPredicate;

	// constructors
	public SelectStmt() {
		this.statementType = EnumStatement.SELECT;
	}

	
	//getters and setters
	public void addExpression(AbstractExpression expr){
		this.tExpressions.add(expr);
		this.tExprAliases.add(null);

	}

	
	public Collection<AbstractExpression> getExpressions() {
		return this.tExpressions;

	}

	public void setExpressionAlias(int i, TokenIdentifier alias){
		this.tExprAliases.set(i, alias);
	}
	
	public Collection<TokenIdentifier> getExpressionAliases() {
		return this.tExprAliases;
	}


	public void addTable(TokenTable table){
		this.tTables.add(table);
		this.tTableAliases.add(null);
	}

	public Collection<TokenTable> getTables() {
		return tTables;
	}

	public void setTableAlias(int i, TokenIdentifier alias){
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

	@Override
	public Error compile() {
		// check tables
		for (int i = 0; i < this.tTables.size(); i++) {
			TokenTable ttable = this.tTables.elementAt(i);
			Table table = Catalog.getTable(ttable.hashKey(Catalog.getSchema(
					ttable.getSchema().hashKey()).getOid()));
			if (table == null) {
				return Catalog
						.createObjectNotExistsErr(ttable.toString(), null);// TODO:
																			// EnumType?
			}
			Error lastError = table.checkObject();
			if (lastError != Error.NO_ERROR)
				return lastError;

			// check attributes
			for (int j = 0; j < this.tExpressions.size(); j++) {//???
				AbstractExpression tAttribute = this.tExpressions.get(i);//???
				Attribute attribute = Catalog.getAttribute(tAttribute
						.hashCode());// TODO: hashCode == Oid?
				lastError = attribute.checkObject();
				if (lastError != Error.NO_ERROR)
					return lastError;
			}

			// general checks okay:
			Vector<TableOperator> tableOperators = new Vector<TableOperator>();
			TableOperator to = new TableOperator(ttable);
			tableOperators.add(to);
		}

		// generate tree and check
		Identifier i = new Identifier("CompilePlan");
		CompilePlan cp = new CompilePlan(i);
		if (tPredicate.getClass().toString().equals("SimplePredicate")) {
			SimplePredicate sPredicate = (SimplePredicate) this.tPredicate;
			Error e = this.canonicalTransformation(sPredicate, cp);
			if (e != Error.NO_ERROR)
				return e;
		} else if (tPredicate.getClass().toString().equals("ComplexPredicate")) {
			ComplexPredicate cPredicate = (ComplexPredicate) this.tPredicate;
			Error e = this.canonicalTransformation(cPredicate, cp);
			if (e != Error.NO_ERROR)
				return e;
		}

		return null;
	}

	private Error canonicalTransformation(SimplePredicate sp, CompilePlan plan) {
		EquiJoin ej = checkGroupingforJoin(sp, this.tTables);
		plan.addOperator(ej, false);
		//TODO
		return null;
	}

	private Error canonicalTransformation(ComplexPredicate cp, CompilePlan plan) {
		//TODO
		return null;

	}

	private EquiJoin checkGroupingforJoin(SimplePredicate sp,
			Vector<TokenTable> paramTables) {		
		TableOperator table1 = null;
		TableOperator table2 = null;
		EquiJoin eJoin = null;
		TokenAttribute operand1 = null;
		TokenAttribute operand2 = null;
		
		// Simple Expression (for example: A.a =B.b)
		if (sp.getExpr1().getClass().toString().equals("SimpleExpression")) {
			SimpleExpression exp1 = (SimpleExpression) sp.getExpr1();
			if (exp1.getOper().getClass().toString().equals("TokenAttribute")) {
				operand1 = (TokenAttribute) exp1.getOper();
				for (int i = 0; i < paramTables.size(); i++) {
					if (operand1.getTable().equals(paramTables.get(i))) {
						table1 = new TableOperator(paramTables.get(i));
					}
				}
			} else if (exp1.getOper().getClass().toString()
					.equals("TokenLiteral")) {
				// not important for Join, "normal" where condition
				return null;
			} else {
				return null;
			}
			if (sp.getExpr2().getClass().toString().equals("SimpleExpression")) {
				SimpleExpression exp2 = (SimpleExpression) sp.getExpr2();
				if (exp2.getOper().getClass().toString()
						.equals("TokenAttribute")) {
					operand2 = (TokenAttribute) exp2.getOper();
					for (int i = 0; i < paramTables.size(); i++) {
						if (operand2.getTable().equals(paramTables.get(i))) {
							table2 = new TableOperator(paramTables.get(i));
						}
					}
				} else if (exp2.getOper().getClass().toString()
						.equals("TokenLiteral")) {
					// not important for Join, "normal" where condition
					return null;
				} else {
					return null;
				}
			}
			eJoin = new EquiJoin(table1, table2, operand1, operand2);
			return eJoin;
		}
		
		// Complex Expression
		else if (sp.getExpr1().getClass().toString()
				.equals("ComplexExpression")) {
			ComplexExpression exp1 = (ComplexExpression) sp.getExpr1();
			//TODO
		}
		return null;
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
		
		int i=0;
		for(AbstractExpression tExpr: this.tExpressions){
			if(i!=0)
				sqlValue.append(AbstractToken.COMMA);
			
			sqlValue.append(AbstractToken.BLANK);	
			sqlValue.append(tExpr);
			TokenIdentifier tExprAlias = this.tExprAliases.get(i);
			if(tExprAlias!=null){
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
		for(TokenTable tTable: this.tTables){
			if(i!=0)
				sqlValue.append(AbstractToken.COMMA);
			
			sqlValue.append(AbstractToken.BLANK);
			sqlValue.append(tTable);
			
			TokenIdentifier tTableAlias = this.tTableAliases.get(i);
			if(tTableAlias!=null){
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


	public void addAttribute(TokenAttribute att1) {
		// TODO Auto-generated method stub
		
	}
}
