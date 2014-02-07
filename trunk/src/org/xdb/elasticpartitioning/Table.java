package org.xdb.elasticpartitioning;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Table {
	private String tableName;
	private Map<Table, List<ForeignKey>> fks;
	private long tableSize;

	public Table(String tableName) {
		this.tableName = tableName;
		fks = new HashMap<Table, List<ForeignKey>>();
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableName() {
		return tableName;
	}
	
	public void setTableSize(long tableSize) {
		this.tableSize = tableSize;
	}

	public long getTableSize() {
		return tableSize;
	}


	@Override
	public boolean equals(Object obj) {
		return this.tableName.equals(((Table) obj).tableName);
	}
	
	@Override
	public int hashCode() {
		return tableName.hashCode();
	}
	
	@Override
	public String toString() {
		return this.tableName;
	}
	
	public List<ForeignKey> getForeignKeys(Table content) {
		return fks.get(content);
	}
	
	public void setForeignKey(Table toTable, ForeignKey fk){
		if (fks.containsKey(toTable)){
			List<ForeignKey> attributes = fks.get(toTable);
			attributes.add(fk);
			fks.put(toTable, attributes);
		}
		else{
			List<ForeignKey> attributes = new ArrayList<ForeignKey>();
			attributes.add(fk);
			fks.put(toTable, attributes);
		}
	}
}
