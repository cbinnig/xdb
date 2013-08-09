package org.xdb.funsql.compile.tokens;

import java.io.Serializable;

import org.xdb.Config;

/**
 * 
 * @author Erfan Zamanian
 *
 */
public class TokenReferencePartition extends AbstractToken {
	private static final long serialVersionUID = 8546480910845519464L;
	
	private TokenTable partitionTable;
	private TokenAttribute partitionAttribute;
	private TokenTable referenceTable;
	private TokenAttribute referenceAttribute;
	
	public TokenReferencePartition(TokenReferencePartition toCopy){
		this.partitionTable = new TokenTable(toCopy.partitionTable);
		this.referenceTable = new TokenTable(toCopy.referenceTable);
		this.partitionAttribute = new TokenAttribute(toCopy.partitionAttribute);
		this.referenceAttribute = new TokenAttribute(toCopy.referenceAttribute);
	}
	
	public TokenReferencePartition(TokenTable partitionTable, TokenAttribute partitionAttribute, TokenTable referenceTable, TokenAttribute referenceAttribute){
		this.partitionTable = partitionTable;
		this.partitionAttribute = partitionAttribute;
		this.referenceTable = referenceTable;
		this.referenceAttribute = referenceAttribute;
	}
	
	public TokenTable getPartitionTable() {
		return partitionTable;
	}
	public TokenAttribute getPartitionAttribute() {
		return partitionAttribute;
	}
	public TokenTable getReferenceTable() {
		return referenceTable;
	}
	public TokenAttribute getReferenceAttribute() {
		return referenceAttribute;
	}
	

	
	
	@Override
	public String toSqlString() {
		StringBuffer buffer = new StringBuffer();
		if (this.partitionTable != null) {
			buffer.append(this.partitionTable.getName().toString());
			buffer.append(AbstractToken.DOT);
		}
		
		buffer.append(this.partitionAttribute.toString());
		
		if (this.referenceTable != null) {
			buffer.append(this.referenceTable.getName().toString());
			buffer.append(AbstractToken.DOT);
		}
		
		buffer.append(this.referenceAttribute.toString());
		
		return buffer.toString();
	}


}
