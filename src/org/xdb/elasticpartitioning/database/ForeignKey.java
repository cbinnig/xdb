package org.xdb.elasticpartitioning.database;

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
	
	public ForeignKey(ForeignKey original){
		this(original.sourceTable, original.destTable);
		for (String sourceAtt: original.attributeRelations.keySet())
			this.attributeRelations.put(sourceAtt, original.attributeRelations.get(sourceAtt));
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
	@Override
	public int hashCode() {
		return attributeRelations.hashCode();
	}
	
	/**
	 * If we are sure that the foreignkey has only one attribute, then calling this method is easier.
	 * @return
	 */
	public String getFirstSourceAttribute(){
		for (String sourceAttribute: attributeRelations.keySet())
			return sourceAttribute;
		return null;
	}
	
	public String getFirstTargetAttribute(){
		for (String sourceAttribute: attributeRelations.keySet())
			return attributeRelations.get(sourceAttribute);
		return null;
	}

}
