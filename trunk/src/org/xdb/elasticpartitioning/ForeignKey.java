package org.xdb.elasticpartitioning;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ForeignKey {
	Map<String, String> attributeRelations;
	Table sourceTable;
	Table destTable;
	
	public ForeignKey(Table sourceTable, Table destTable) {
		attributeRelations = new HashMap<String, String>();
		this.sourceTable = sourceTable;
		this.destTable = destTable;
	}
	
	public void addRelations(String sourceAtt, String destAtt){
		attributeRelations.put(sourceAtt, destAtt);
	}
	
	public Map<String, String> getAttributeRelations() {
		return attributeRelations;
	}

	public Set<String> getSourceAttributeList() {
		return attributeRelations.keySet();
	}

}
