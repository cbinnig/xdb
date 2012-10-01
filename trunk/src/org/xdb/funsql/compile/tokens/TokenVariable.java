package org.xdb.funsql.compile.tokens;

/**
 * variable token for function statement
 * @author lschmidt
 *
 */
public class TokenVariable extends AbstractToken{
	private static final long serialVersionUID = 68434583365038949L;
	private String name;

	public TokenVariable(String  name) {
		this.setName(name);
	}

	@Override
	public String toSqlString() {
		return this.name;
	}
	
	//getter and setter
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
