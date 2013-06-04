package org.xdb.funsql.parallelize;

import java.util.HashSet;
import java.util.Set;

import org.xdb.funsql.compile.tokens.TokenAttribute;

public class PartitionAttributeSet{
	Set<TokenAttribute> attributeSet = new HashSet<TokenAttribute>(); 
	
	public Set<TokenAttribute> getAttributeSet(){
		return attributeSet;
	}
	
	public void addAttribute(TokenAttribute tokenAtt){
		attributeSet.add(tokenAtt);
	}
	
	// should be used instead of clone method. This method makes a duplicate of each TokenAttribute belonged to the object
	public PartitionAttributeSet deepCopy() {
		PartitionAttributeSet newCopy = new PartitionAttributeSet();
		//for (TokenAttribute tokenAttribute : this.getAttributeSet())
		//	newCopy.attributeSet.add(tokenAttribute.clone());
		// instead of two lines above, we can use the deepcopy feature of Sets/Maps/Lists
		newCopy.attributeSet = new HashSet<TokenAttribute>(this.getAttributeSet());
		return newCopy;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PartitionAttributeSet))
			return false;
		
		PartitionAttributeSet pas = (PartitionAttributeSet) obj;
		
		if (this.attributeSet.size() != pas.getAttributeSet().size()) return false;
		
		// TODO: Very dirty fix. Must be fixed, or anyone on earth will die (, one day)
		//TokenAttribute ta1 = new TokenAttribute("ORDERS", "O_ORDERKEY");
		//TokenAttribute ta2 = new TokenAttribute("LINEORDERS", "L_ORDERKEY");
		//if ((pas.getAttributeSet().contains(ta1) || pas.getAttributeSet().contains(ta2)) &&
		//		(this.getAttributeSet().contains(ta1) || this.getAttributeSet().contains(ta2))) return true;
				
		return (pas.getAttributeSet().containsAll(this.getAttributeSet()));
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (TokenAttribute att : this.getAttributeSet())
			sb.append(att.getName().toString() + ", ");
		sb.append("], ");
		
		return sb.toString();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		PartitionAttributeSet copyPAS =  (PartitionAttributeSet) super.clone();
		copyPAS.attributeSet = new HashSet<TokenAttribute>(this.attributeSet);
		return copyPAS;		
	}
}