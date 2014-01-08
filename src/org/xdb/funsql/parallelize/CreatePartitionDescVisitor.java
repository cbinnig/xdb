package org.xdb.funsql.parallelize;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.PartitionDesc;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.ResultDesc;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.metadata.EnumPartitionType;
import org.xdb.metadata.PartitionAttribute;
import org.xdb.utils.Identifier;

/**
 * Annotates each operator in a compile plan with partitioning info
 * 
 * @author cbinnig
 * 
 */
public class CreatePartitionDescVisitor extends AbstractBottomUpTreeVisitor {
	// hold partitioning description per operator
	private Map<Identifier, Set<PartitionDesc>> op2partDesc = new HashMap<Identifier, Set<PartitionDesc>>();
	private CompilePlan cPlan;

	public CreatePartitionDescVisitor(CompilePlan cPlan,
			AbstractCompileOperator root) {
		super(root);
		this.cPlan = cPlan;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation ga) {
		Error err = new Error();
		if (this.containsPartDescs(ga.getOperatorId()))
			return err;

		Identifier childId = ga.getChild().getOperatorId();
		Set<PartitionDesc> childPartDescs = this.getPartDescs(childId);
		Collection<AbstractExpression> groupExprs = ga.getGroupExpressions();

		boolean doRepartition = this.isPartDescCompatible(childPartDescs,
				groupExprs);

		if (!doRepartition) {
			// create new partitioning description
			PartitionDesc childPartDesc = childPartDescs.iterator().next();
			EnumPartitionType rePartType = EnumPartitionType
					.getMaterializeType();
			PartitionDesc rePartDesc = new PartitionDesc(rePartType,
					childPartDesc.getPartitionNumber());

			// add pre-aggregation
			GenericAggregation preAggOp = new GenericAggregation(ga);
			preAggOp.setParent(0, ga);
			preAggOp.setChild(ga.getChild());
			this.cPlan.addOperator(preAggOp, false);
			preAggOp.renameTableOfAttributes(ga.getOperatorId().toString(),
					preAggOp.getOperatorId().toString());

			preAggOp.getResult().materialize(true);
			preAggOp.getResult().repartition(true);
			preAggOp.getResult().setPartitionDesc(rePartDesc);

			ga.setChild(preAggOp);

			this.setPartDesc(ga.getOperatorId(), rePartDesc);
			this.setPartDesc(preAggOp.getOperatorId(), rePartDesc);
		} else {
			this.addPartDescs(ga.getOperatorId(), childPartDescs);
		}

		return err;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		Error err = new Error();
		if (this.containsPartDescs(ej.getOperatorId()))
			return err;

		Identifier leftId = ej.getLeftChild().getOperatorId();
		Identifier rightId = ej.getRightChild().getOperatorId();
		Set<PartitionDesc> leftPartDescs = this.getPartDescs(leftId);
		Set<PartitionDesc> rightPartDescs = this.getPartDescs(rightId);
		Set<PartitionDesc> joinPartDescs = new HashSet<PartitionDesc>();

		boolean doRepartition = true;

		// check if both inputs are compatible
		if ((this.isPartDescCompatible(leftPartDescs,
				ej.getLeftTokenAttribute()) || this.isPartDescCompatible(
				rightPartDescs, ej.getRightTokenAttribute()))
				&& this.isPartDescCompatible(leftPartDescs, rightPartDescs)) {
			doRepartition = false;
		}

		// do re-partition if both inputs are not compatible
		if (doRepartition) {
			ResultDesc leftResult = ej.getLeftChild().getResult();
			leftResult.materialize(true);
			leftResult.repartition(true);

			PartitionDesc rightPartDesc = rightPartDescs.iterator().next();
			EnumPartitionType leftRePartType = EnumPartitionType
					.getMaterializeType();
			PartitionDesc leftRePartDesc = new PartitionDesc(leftRePartType,
					rightPartDesc.getPartitionNumber());
			leftRePartDesc.addPartAttributes(ej.getLeftTokenAttribute());
			leftResult.setPartitionDesc(leftRePartDesc);
			joinPartDescs.add(leftRePartDesc);
			joinPartDescs.addAll(rightPartDescs);
		}
		// don't re-partition if both inputs are compatible
		else {
			joinPartDescs.addAll(leftPartDescs);
			joinPartDescs.addAll(rightPartDescs);
		}

		// add partition specification to operator
		this.addPartDescs(ej.getOperatorId(), joinPartDescs);
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		Error err = new Error();
		if (this.containsPartDescs(gs.getOperatorId()))
			return err;

		Set<PartitionDesc> partDescs = this.getPartDescs(gs.getChild()
				.getOperatorId());

		this.addPartDescs(gs.getOperatorId(), partDescs);
		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		Error err = new Error();
		if (this.containsPartDescs(gp.getOperatorId()))
			return err;

		Set<PartitionDesc> partDescs = this.getPartDescs(gp.getChild()
				.getOperatorId());

		this.addPartDescs(gp.getOperatorId(), partDescs);
		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		Error err = new Error();
		if (this.containsPartDescs(to.getOperatorId()))
			return err;

		PartitionDesc partDesc = new PartitionDesc();
		if (to.isPartitioned()) {
			partDesc.setPartitionType(to.getPartitionType());
			for (PartitionAttribute partAtt : to.getPartitionAttributes()) {
				String partAttName = ResultDesc.createResultAtt(
						to.getTableName(), partAtt.getName());
				String tableName = to.getOperatorId().toString();
				TokenAttribute tPartAtt = new TokenAttribute(tableName,
						partAttName);
				partDesc.addPartAttributes(tPartAtt);
			}
			partDesc.setPartNumber(to.getPartitionCount());
			partDesc.setTableName(to.getTableName());

			if (to.getPartitionType().isReference()) {
				partDesc.setRefTableName(to.getRefTableName());
			}
		}
		this.setPartDesc(to.getOperatorId(), partDesc);
		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		Error err = new Error();
		if (this.containsPartDescs(ro.getOperatorId()))
			return err;

		Set<PartitionDesc> partDescs = this.getPartDescs(ro.getChild()
				.getOperatorId());

		this.addPartDescs(ro.getOperatorId(), partDescs);
		return err;
	}

	@Override
	public Error visitSQLUnary(SQLUnary sqlOp) {
		String[] args = { "SQLUnary operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		String[] args = { "SQLJoin operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		String[] args = { "SQLCombinedOperators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	private void setPartDesc(Identifier opId, PartitionDesc partDesc) {
		if (!this.op2partDesc.containsKey(opId)) {
			this.op2partDesc.put(opId, new HashSet<PartitionDesc>());
		}
		PartitionDesc newPartDesc = new PartitionDesc(partDesc);
		newPartDesc.renameTable(opId);
		this.op2partDesc.get(opId).clear();
		this.op2partDesc.get(opId).add(newPartDesc);
		this.cPlan.getOperator(opId).getResult()
				.setPartitionCount(partDesc.getPartitionNumber());
	}

	private void addPartDescs(Identifier opId, Set<PartitionDesc> partDescs) {
		boolean removeNoPartition = false;
		if (partDescs.size() > 1)
			removeNoPartition = true;

		Set<PartitionDesc> newPartDescs = new HashSet<PartitionDesc>(
				partDescs.size());
		int partitionNumber = 1;
		for (PartitionDesc partDesc : partDescs) {
			partitionNumber = partDesc.getPartitionNumber();
			if (removeNoPartition && !partDesc.isPartitioned())
				continue;

			PartitionDesc newPartDesc = new PartitionDesc(partDesc);
			newPartDesc.renameTable(opId);
			newPartDescs.add(newPartDesc);
		}
		this.cPlan.getOperator(opId).getResult()
				.setPartitionCount(partitionNumber);
		this.op2partDesc.put(opId, newPartDescs);
	}

	private Set<PartitionDesc> getPartDescs(Identifier opId) {
		return this.op2partDesc.get(opId);
	}

	private boolean containsPartDescs(Identifier opId) {
		return this.op2partDesc.containsKey(opId);
	}

	private boolean isPartDescCompatible(Set<PartitionDesc> partDescs,
			Collection<AbstractExpression> groupExprs) {
		// if no grouping is needed
		if (groupExprs.size() == 0)
			return true;

		// get first group by attribute
		AbstractExpression groupExpr = groupExprs.iterator().next();
		if (!groupExpr.isAttribute())
			return false;

		// check if partition description is compatible with first group by
		// attribute
		TokenAttribute groupAtt = groupExpr.getAttribute();
		for (PartitionDesc partDesc : partDescs) {
			if (partDesc.isCompatible(groupAtt))
				return true;
		}

		return false;
	}

	private boolean isPartDescCompatible(Set<PartitionDesc> partDescs,
			TokenAttribute joinAtt) {
		for (PartitionDesc partDesc : partDescs) {
			if (partDesc.isCompatible(joinAtt))
				return true;
		}

		return false;
	}

	private boolean isPartDescCompatible(Set<PartitionDesc> lPartDescs,
			Set<PartitionDesc> rPartDescs) {
		for (PartitionDesc lPartDesc : lPartDescs) {
			for (PartitionDesc rPartDesc : rPartDescs) {
				if (lPartDesc.isCompatible(rPartDesc))
					return true;
			}
		}
		return false;
	}
}
