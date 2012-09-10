package org.xdb.funsql.compile.operator;

import org.xdb.funsql.compile.tokens.TokenTable;
import org.xdb.store.EnumStore;

public class TableOperator extends AbstractOperator {
	private static final long serialVersionUID = 997138204723229392L;

	//attributes
	private TokenTable table;
	protected EnumStore sourceId = null;
	
	//constructors
	public TableOperator(TokenTable table){
		this.table = table;
		this.type = EnumOperator.TABLE;
	}

	//getters and setters
	public void setSourceId(EnumStore sourceId){
		this.sourceId = sourceId;
	}
	
	public EnumStore getSourceId() {
		return sourceId;
	}
	public TokenTable getTable() {
		return table;
	}

	public void setTable(TokenTable table) {
		this.table = table;
	}
}
