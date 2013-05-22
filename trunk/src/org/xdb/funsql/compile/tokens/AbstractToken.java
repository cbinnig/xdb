package org.xdb.funsql.compile.tokens;

import java.io.Serializable;

public abstract class AbstractToken implements Serializable{
	private static final long serialVersionUID = -5919010803270387112L;
	
	public static final String DATE = "DATE";
	
	public static final String BLANK = " ";
	public static final String UNDERSCORE = "_";
	public static final String DOT = ".";
	public static final String COMMA = ",";
	public static final String LBRACE = "(";
	public static final String RBRACE = ")";
	public static final String SEMI = ";";
	
	public static final String EQUAL1 = "=";
	public static final String EQUAL2 = "=";
	public static final String NOT_EQUAL1 = "!=";
	public static final String NOT_EQUAL2 = "!=";
	public static final String GREATER_THAN = ">";
	public static final String LESS_THAN = "<";
	public static final String GREATER_EQUAL = ">=";
	public static final String LESS_EQUAL = "<=";
	public static final String LIKE = "LIKE";
	
	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final String NOT = "NOT";
	
	public static final String PLUS = "+";
	public static final String MINUS = "-";
	public static final String MULT = "*";
	public static final String DIV = "/";
	
	public static final String QUOTE = "'";
	public static final String SINGLE_QUOTE = "'";
	public static final String DOUBLE_QUOTE = "\"";
	public static final String MYSQL_QUOTE = "`";
	
	public static final String DELETE = "DELETE";
	public static final String INSERT = "INSERT";
	public static final String SELECT = "SELECT";
	public static final String FROM = "FROM";
	public static final String WHERE = "WHERE";
	public static final String INTO = "INTO";
	public static final String VALUES = "VALUES";
	public static final String MAX = "MAX";
	public static final String AS = "AS";
	
	public static final String CALL = "CALL";
	public static final String VAR = "VAR";
		
	public static final String NEWLINE = "\n";
	
	@Override
	public boolean equals(Object o){
		return this.toString().equals(o.toString());
	}
	
	@Override
	public int hashCode(){
		return toSqlString().hashCode();
	}
	
	@Override
	public String toString(){
		return this.toSqlString();
	}
	
	/**
	 * SQL representation of token
	 * @return
	 */
	public abstract String toSqlString();
	
	//encode boolean as 0 or 1
	public static String toSqlLiteral(boolean value){
		if(!value) return "0";
		return "1";
	}
	public static String toSqlLiteral(String value){
		
		if(value !=null) {
		StringBuffer literal = new StringBuffer();
		literal.append(SINGLE_QUOTE);
		literal.append(value);
		literal.append(SINGLE_QUOTE);
		return literal.toString();
		} else {
			return null;
		}
	}
	
	public static String toSqlIdentifier(String value){
		StringBuffer literal = new StringBuffer();
		literal.append(MYSQL_QUOTE);
		literal.append(value);
		literal.append(MYSQL_QUOTE);
		return literal.toString();
	}
	
	public static String toSqlIdentifierList(String[] ids){
		StringBuffer idList = new StringBuffer();
		for(int i=1; i<=ids.length; ++i){
			String id = ids[i-1];
			idList.append(AbstractToken.toSqlIdentifier(id));
			if(i<ids.length){
				idList.append(AbstractToken.COMMA);
			}
		}
		return idList.toString();
		
	}
}
