package org.xdb.funsql.compile.analyze;

import java.util.HashMap;
import java.util.Map;

import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.CheckOperatorVisitor;
import org.xdb.funsql.compile.analyze.operator.CreateResultVisitor;
import org.xdb.funsql.compile.analyze.operator.RenameOperatorVisitor;
import org.xdb.funsql.compile.operator.AbstractOperator;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.utils.Identifier;

public class Analyzer {
	private CompilePlan compilePlan;
	private HashMap<AbstractToken, EnumSimpleType> expTypes = new HashMap<AbstractToken, EnumSimpleType>();

	public Analyzer(CompilePlan compilePlan,
			Map<TokenAttribute, EnumSimpleType> attTypes) {
		super();
		this.compilePlan = compilePlan;
		this.expTypes.putAll(attTypes);
	}

	public void analyze() {
		// Check types
		for (Identifier rootId : compilePlan.getRootIds()) {
			AbstractOperator root = this.compilePlan.getOperators(rootId);

			CheckOperatorVisitor checkOpVisitor = new CheckOperatorVisitor(
					this.expTypes, root);
			checkOpVisitor.visit();
		}

		// Create result descriptions
		for (Identifier rootId : compilePlan.getRootIds()) {
			AbstractOperator root = this.compilePlan.getOperators(rootId);

			CreateResultVisitor resultDescVisitor = new CreateResultVisitor(
					root, expTypes);
			resultDescVisitor.visit();
		}

		//TODO: Do renaming after optimization => remove renaming from result creation and do all renaming here!
		// Rename attributes
		for (Identifier rootId : compilePlan.getRootIds()) {
			AbstractOperator root = this.compilePlan.getOperators(rootId);

			RenameOperatorVisitor renameVisitor = new RenameOperatorVisitor(
					root);
			renameVisitor.visit();
		}
	}
}
