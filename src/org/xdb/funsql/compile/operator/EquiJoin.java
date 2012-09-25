package org.xdb.funsql.compile.operator;

import java.util.HashMap;

import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.utils.StringTemplate;

public class EquiJoin extends AbstractBinaryOperator {
	private static final long serialVersionUID = -7557353401586940253L;

	private TokenAttribute leftTokenAttribute;
	private TokenAttribute rightTokenAttribute;
	
	private StringTemplate sqlTemplate = new StringTemplate(
			"SELECT * FROM `<<OP1>>` AS `<OP1>` INNER JOIN `<<OP2>>` AS `<OP2>`" +
			"ON `<OP1>`.`<LOP1>` = `<OP2>`.`<LOP2>`");

	//constructors
	public EquiJoin(AbstractOperator leftChild, AbstractOperator rightChild,
			TokenAttribute leftTokenAttribute, TokenAttribute rightTokenAttribute) {
		super(leftChild, rightChild);
		
		this.leftTokenAttribute = leftTokenAttribute;
		this.rightTokenAttribute = rightTokenAttribute;
		this.type = EnumOperator.EQUI_JOIN;
	}

	//getters and setters
	public TokenAttribute getLeftTokenAttribute() {
		return leftTokenAttribute;
	}

	public void setLeftTokenAttribute(TokenAttribute leftTokenAttribute) {
		this.leftTokenAttribute = leftTokenAttribute;
	}

	public TokenAttribute getRightTokenAttribute() {
		return rightTokenAttribute;
	}

	public void setRightTokenAttribute(TokenAttribute rightTokenAttribute) {
		this.rightTokenAttribute = rightTokenAttribute;
	}

	@Override
	public String toSqlString() {
		return sqlTemplate.toString(new HashMap<String, String>() {{
			put("OP1", getLeftChild().getOperatorId().toString());
			put("OP2", getRightChild().getOperatorId().toString());
			
			put("LOP1", getLeftTokenAttribute().toSqlString());
			put("LOP2", getRightTokenAttribute().toSqlString());
		}});
	}
}
