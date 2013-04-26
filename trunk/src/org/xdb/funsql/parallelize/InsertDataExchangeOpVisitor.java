package org.xdb.funsql.parallelize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractTopDownTreeVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class InsertDataExchangeOpVisitor extends AbstractTopDownTreeVisitor {

	private CompilePlan compileplan;
	private Error error = new Error();;

	public InsertDataExchangeOpVisitor(AbstractCompileOperator root,
			CompilePlan cp) {
		super(root);
		this.compileplan = cp;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {

		// extract Partition Info
		TokenAttribute taleft = ej.getLeftTokenAttribute();
		Set<TokenAttribute> partionColumnsLeft = new HashSet<TokenAttribute>();
		partionColumnsLeft.add(taleft);
		PartitionInfo ptLeft = new PartitionInfo(partionColumnsLeft,
				EnumPartitionType.HASH, 0);

		TokenAttribute taright = ej.getRightTokenAttribute();
		Set<TokenAttribute> partionColumnsRight = new HashSet<TokenAttribute>();
		partionColumnsRight.add(taright);
		PartitionInfo ptRight = new PartitionInfo(partionColumnsRight,
				EnumPartitionType.HASH, 0);

		// create repartitioning operators
		// left side
		DataExchangeOperator leftDe = new DataExchangeOperator(
				ej.getLeftChild(), ej.getLeftChild().getResult());
		
		//remove EJ from Parent
		ej.getLeftChild().removeParent(ej);
		ej.setLeftChild(leftDe);
		leftDe.addParent(ej);

		PartitionInfo ptLeftNo = new PartitionInfo(
				EnumPartitionType.NO_PARTITION, 0);
		leftDe.addPartitionCandiate(ptLeft);
		leftDe.addPartitionCandiate(ptLeftNo);
		this.compileplan.addOperator(leftDe, false);
		
		//ej.getLeftChild().setParent(ej, leftDe);

		// right side
		DataExchangeOperator rightDe = new DataExchangeOperator(
				ej.getRightChild(), ej.getRightChild().getResult());
		ej.getRightChild().removeParent(ej);
		ej.setRightChild(rightDe);
		rightDe.addParent(ej);
		
		PartitionInfo ptRightNo = new PartitionInfo(
				EnumPartitionType.NO_PARTITION, 0);
		rightDe.addPartitionCandiate(ptRight);
		rightDe.addPartitionCandiate(ptRightNo);
		
		this.compileplan.addOperator(rightDe, false);


		return error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		return error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {

		// get partition info

		Set<TokenAttribute> partitionColumns = new HashSet<TokenAttribute>();
		for (AbstractExpression absE : sa.getGroupExpressions()) {
			for (TokenAttribute tokenAttribute : absE.getAttributes()) {
				partitionColumns.add(tokenAttribute);
			}
		}
		// build any sufficient subSet
		// Set<Set<TokenAttribute>> powerSetOfPartitionColumns =
		// getPowerSet(partitionColumns);
		Set<Set<TokenAttribute>> candidates = new HashSet<Set<TokenAttribute>>();
		Set<TokenAttribute> cset;
		for (TokenAttribute ta : partitionColumns) {
			cset = new HashSet<TokenAttribute>();
			cset.add(ta);
			candidates.add(cset);
		}

		DataExchangeOperator dataExchange = new DataExchangeOperator(
				sa.getChild(), sa.getChild().getResult());
		PartitionInfo pi = null;
		for (Set<TokenAttribute> subset : candidates) {
			pi = new PartitionInfo(subset, EnumPartitionType.HASH, 0);
			dataExchange.addPartitionCandiate(pi);
		}

		pi = new PartitionInfo(EnumPartitionType.NO_PARTITION);
		dataExchange.addPartitionCandiate(pi);

		this.compileplan.addOperator(dataExchange, false);
		// rebuild tree structure
		sa.setChild(dataExchange);
		dataExchange.addParent(sa);

		return error;
	}

	public Set<Set<TokenAttribute>> getPowerSet(Set<TokenAttribute> originalSet) {
		Set<Set<TokenAttribute>> sets = new HashSet<Set<TokenAttribute>>();
		if (originalSet.isEmpty()) {
			sets.add(new HashSet<TokenAttribute>());
			return sets;
		}
		List<TokenAttribute> list = new ArrayList<TokenAttribute>(originalSet);
		TokenAttribute head = list.get(0);
		Set<TokenAttribute> rest = new HashSet<TokenAttribute>(list.subList(1,
				list.size()));
		for (Set<TokenAttribute> set : getPowerSet(rest)) {
			Set<TokenAttribute> newSet = new HashSet<TokenAttribute>();
			newSet.add(head);
			newSet.addAll(set);
			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		// Not relevant
		return error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {

		// Not relevant
		return error;
	}

	@Override
	public Error visitRename(Rename ro) {
		// Not relevant
		return error;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		return error;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		return error;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		String[] args = { "DataExchange operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

}
