package org.xdb.metadata;

import org.xdb.funsql.compile.tokens.AbstractToken;

public class PartitionAttribute extends AbstractDatabaseObject{
	
	private static final long serialVersionUID = 4916057015536997054L;
	
	private static final String[] ATTRIBUTES = {"PART_ATT_OID", "REF_ATT_OID"};
	private static final String ALL_ATTRIBUTES = AbstractToken.toSqlIdentifierList(ATTRIBUTES);

	private static final String TABLE_NAME = AbstractToken.toSqlIdentifier("PARTITIONATTRIBUTES");
	private static final String PART_ATT_OID_NAME = AbstractToken.toSqlIdentifier(ATTRIBUTES[0]);
	
	private long part_att_oid;
	private Long ref_att_oid;
	
	private static PartitionAttribute prototype = new PartitionAttribute();
	
	
	public PartitionAttribute(PartitionAttribute toCopy){
		super(toCopy);
		this.part_att_oid = toCopy.part_att_oid;
		this.ref_att_oid = toCopy.ref_att_oid;
	}
	
	private PartitionAttribute(){
		super();
		this.objectType = EnumDatabaseObject.PARTITIONATTRIBUTES;
	}
	
	public PartitionAttribute(long part_att_oid) {
		this();
		this.part_att_oid = part_att_oid;
	}
	
	public PartitionAttribute( long part_att_oid, Long ref_att_oid) {
		this(part_att_oid);
		this.part_att_oid = part_att_oid;
	}
	
	public long getPart_att_oid() {
		return part_att_oid;
	}
	
	public Long getRef_att_oid() {
		return ref_att_oid;
	}
	
	@Override
	public String getName(){
		Attribute partAtt = Catalog.getAttribute(this.part_att_oid);
		return partAtt.getName();
	}
	
	@Override
	public String sqlInsert() {
		StringBuffer insertSql = new StringBuffer();
		insertSql.append(AbstractToken.INSERT); 
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.INTO);
		insertSql.append(AbstractToken.BLANK); 
		insertSql.append(METADATA_SCHEMA);
		insertSql.append(AbstractToken.DOT);
		insertSql.append(TABLE_NAME);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.LBRACE); 
		insertSql.append(ALL_ATTRIBUTES);
		insertSql.append(AbstractToken.RBRACE);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.VALUES);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.LBRACE); 
		insertSql.append(this.part_att_oid);
		insertSql.append(AbstractToken.COMMA);
		insertSql.append(this.ref_att_oid);
		insertSql.append(AbstractToken.RBRACE);
		return insertSql.toString();
	}
	
	public String sqlDelete(){
		StringBuffer selectSql = new StringBuffer();
		selectSql.append(AbstractToken.DELETE);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.FROM);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(METADATA_SCHEMA);
		selectSql.append(AbstractToken.DOT);
		selectSql.append(getTableName());
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.WHERE);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(PART_ATT_OID_NAME);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(AbstractToken.EQUAL1);
		selectSql.append(AbstractToken.BLANK);
		selectSql.append(this.part_att_oid);
		return selectSql.toString();
	}
	
	protected static String sqlSelectAll() {
		return prototype.internalSqlSelectAll();
	}
	
	protected static String sqlDeleteAll() {
		return prototype.interalSqlDeleteAll();
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	@Override
	public String getAllAttributes() {
		return ALL_ATTRIBUTES;
	}
	@Override
	public String hashKey() {
		StringBuffer hashKey = new StringBuffer();
		hashKey.append(this.part_att_oid);
		hashKey.append("->");
		hashKey.append(this.ref_att_oid);
		return hashKey.toString();
	}

}
