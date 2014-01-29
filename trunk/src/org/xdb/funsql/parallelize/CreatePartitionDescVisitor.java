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
import org.xdb.funsql.compile.analyze.operator.CheckOperatorVisitor;
import org.xdb.funsql.compile.analyze.operator.CreateResultVisitor;
import org.xdb.funsql.compile.expression.AbstractExpression;
import org.xdb.funsql.compile.expression.AggregationExpression;
import org.xdb.funsql.compile.expression.ComplexExpression;
import org.xdb.funsql.compile.expression.EnumExprOperator;
import org.xdb.funsql.compile.expression.EnumExprType;
import org.xdb.funsql.compile.expression.SimpleExpression;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.EnumAggregation;
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
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.compile.tokens.TokenIdentifier;
import org.xdb.funsql.types.EnumSimpleType;
import org.xdb.metadata.EnumPartitionType;
import org.xdb.metadata.PartitionAttribute;
import org.xdb.utils.Identifier;

/**
 * Annotates each operator in a compile plan with partitioning info Avoids
 * re-partitioning when result has only 1 partition!!!
 * 
 * @author cbinnig
 * 
 */
public class CreatePartitionDescVisitor extends AbstractBottomUpTreeVisitor {
	// partitioning description per operator
	private Map<Identifier, Set<PartitionDesc>> op2partDesc = new HashMap<Identifier, Set<PartitionDesc>>();

	// compile plan
	private CompilePlan cPlan;

	// helper
	private int lastInternalAlias = 0;
	private Identifier internalAlias = new Identifier("_ALIAS_PARALLEL");

	// constructor
	public CreatePartitionDescVisitor(CompilePlan cPlan,
			AbstractCompileOperator root) {
		super(root);
		this.cPlan = cPlan;
	}

	// getters and setters
	private Set<PartitionDesc> getPartDescs(Identifier opId) {
		return this.op2partDesc.get(opId);
	}

	private boolean containsPartDescs(Identifier opId) {
		return this.op2partDesc.containsKey(opId);
	}

	// methods
	@Override
	public Error visitGenericAggregation(GenericAggregation ga) {
		Error err = new Error();
		if (this.containsPartDescs(ga.getOperatorId()))
			return err;

		Identifier childId = ga.getChild().getOperatorId();
		Set<PartitionDesc> childPartDescs = this.getPartDescs(childId);
		Collection<AbstractExpression> groupExprs = ga.getGroupExpressions();
		PartitionDesc childPartDesc = childPartDescs.iterator().next();
		int preAggPartCnt = childPartDesc.getPartitionCount();

		boolean doRepartition = !this.isPartDescCompatible(childPartDescs,
				groupExprs) && (preAggPartCnt > 1);

		if (doRepartition) {
			int postAggPartCnt = preAggPartCnt;
			
			// create pre-aggregation operator
			Map<AbstractExpression, AbstractExpression> replaceExpr = new HashMap<AbstractExpression, AbstractExpression>();

			// create partitioning description for both aggregation operators
			EnumPartitionType rePartType = EnumPartitionType
					.getMaterializeType();
			PartitionDesc preAggRePartDesc = new PartitionDesc(rePartType,
					preAggPartCnt);
			PartitionDesc postAggPartDesc = new PartitionDesc(preAggRePartDesc);

			// Create pre-aggregation operator
			GenericAggregation preAgg = this.createPreAggregation(ga,
					replaceExpr);
			for (TokenIdentifier grpAlias : preAgg.getGroupAliases()) {
				preAggRePartDesc
						.addPartAttributes(new TokenAttribute(grpAlias));

				// do not re-partition if group by attribute is non-numeric
				EnumSimpleType groupType = preAgg.getResult().getType(
						preAgg.getAggregationExpressions().size());
				if (!groupType.isNumeric()){
					preAggRePartDesc.setPartCount(1);
					postAggPartCnt = 1;
				}

				break; // only first group by attribute is used for partitioning
			}
			preAgg.getResult().setPartitionDesc(preAggRePartDesc);
			this.storePartDesc(preAgg.getOperatorId(), preAggRePartDesc);
			preAgg.getResult().setPartitionCount(preAggPartCnt);
			
			// create post-aggregation operator
			GenericAggregation postAgg = this.createPostAggregation(ga, preAgg,
					replaceExpr);
			for (TokenIdentifier grpAlias : postAgg.getGroupAliases()) {
				postAggPartDesc.addPartAttributes(new TokenAttribute(grpAlias));
			}
			this.storePartDesc(postAgg.getOperatorId(), preAggRePartDesc);
			postAgg.getResult().setPartitionCount(postAggPartCnt);
		} else {
			//store partition description
			this.storePartDescs(ga.getOperatorId(), childPartDescs);
			
			// set partition count of operator
			ga.getResult().setPartitionCount(preAggPartCnt);
		}
		
		return err;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		Error err = new Error();

		// return if operator has already been visited
		if (this.containsPartDescs(ej.getOperatorId()))
			return err;

		// get input partitioning from left and right child
		Identifier leftId = ej.getLeftChild().getOperatorId();
		Identifier rightId = ej.getRightChild().getOperatorId();
		Set<PartitionDesc> leftPartDescs = this.getPartDescs(leftId);
		Set<PartitionDesc> rightPartDescs = this.getPartDescs(rightId);
		Set<PartitionDesc> joinPartDescs = new HashSet<PartitionDesc>();
		PartitionDesc rightPartDesc = rightPartDescs.iterator().next();
		int rightPartCnt = rightPartDesc.getPartitionCount();
		
		// check if one input must be re-partitioned
		boolean doRepartition = true;
		if ((this.isPartDescCompatible(leftPartDescs,
				ej.getLeftTokenAttribute()) || this.isPartDescCompatible(
				rightPartDescs, ej.getRightTokenAttribute()))
				&& this.isPartDescCompatible(leftPartDescs, rightPartDescs)) {
			doRepartition = false;
		}
		if (rightPartCnt == 1) {
			doRepartition = false;
		}

		// do re-partition if both inputs are not compatible
		if (doRepartition) {
			// re-partition left input
			ResultDesc leftResult = ej.getLeftChild().getResult();
			leftResult.materialize(true);
			leftResult.repartition(true);

			// create partition description for re-partitioning left input
			EnumPartitionType leftRePartType = EnumPartitionType
					.getMaterializeType();
			PartitionDesc leftRePartDesc = new PartitionDesc(leftRePartType,
					rightPartCnt);
			leftRePartDesc.addPartAttributes(ej.getLeftTokenAttribute());
			leftResult.setPartitionDesc(leftRePartDesc);

			// add partition descriptions
			joinPartDescs.add(leftRePartDesc);
			joinPartDescs.addAll(rightPartDescs);
		}
		// don't re-partition if both inputs are compatible
		else {
			joinPartDescs.addAll(leftPartDescs);
			joinPartDescs.addAll(rightPartDescs);
		}

		// add partition specification to operator
		this.storePartDescs(ej.getOperatorId(), joinPartDescs);
		
		// set partition count of operator
		ej.getResult().setPartitionCount(rightPartCnt);
				
		return err;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		Error err = new Error();

		// return if operator has already been visited
		if (this.containsPartDescs(gs.getOperatorId()))
			return err;

		// use partitioning description of input for selection operator
		Set<PartitionDesc> partDescs = this.getPartDescs(gs.getChild()
				.getOperatorId());
		this.storePartDescs(gs.getOperatorId(), partDescs);

		// Set partition count of operator
		int childPartCnt = gs.getChild().getResult().getPartitionCount();
		gs.getResult().setPartitionCount(childPartCnt);

		return err;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		Error err = new Error();

		// return if operator has already been visited
		if (this.containsPartDescs(gp.getOperatorId()))
			return err;

		// use partitioning description of input for selection operator
		Set<PartitionDesc> partDescs = this.getPartDescs(gp.getChild()
				.getOperatorId());
		this.storePartDescs(gp.getOperatorId(), partDescs);

		// set partition count of operator
		int childPartCnt = gp.getChild().getResult().getPartitionCount();
		gp.getResult().setPartitionCount(childPartCnt);

		return err;
	}

	@Override
	public Error visitRename(Rename ro) {
		Error err = new Error();

		// return if operator has already been visited
		if (this.containsPartDescs(ro.getOperatorId()))
			return err;

		// use partitioning description of input for selection operator
		// TODO: Rename attributes according to operator
		Set<PartitionDesc> partDescs = this.getPartDescs(ro.getChild()
				.getOperatorId());
		this.storePartDescs(ro.getOperatorId(), partDescs);

		// set partition count of operator
		int childPartCnt = ro.getChild().getResult().getPartitionCount();
		ro.getResult().setPartitionCount(childPartCnt);

		return err;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		Error err = new Error();

		// return if operator has already been visited
		if (this.containsPartDescs(to.getOperatorId()))
			return err;

		// Create partitioning description from meta data of table
		PartitionDesc partDesc = new PartitionDesc();
		int partCnt = 1;
		if (to.isPartitioned()) {
			partCnt = to.getPartitionCount();
			partDesc.setPartCount(partCnt);
			partDesc.setTableName(to.getTableName());
			partDesc.setPartitionType(to.getPartitionType());
			if (to.getPartitionType().isReference()) {
				partDesc.setRefTableName(to.getRefTableName());
			}

			for (PartitionAttribute partAtt : to.getPartitionAttributes()) {
				String partAttName = ResultDesc.createResultAtt(
						to.getTableName(), partAtt.getName());
				String tableName = to.getOperatorId().toString();
				TokenAttribute tPartAtt = new TokenAttribute(tableName,
						partAttName);
				partDesc.addPartAttributes(tPartAtt);
			}
		}
		this.storePartDesc(to.getOperatorId(), partDesc);

		// set partition count of operator
		to.getResult().setPartitionCount(partCnt);

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
		String[] args = { "SQLCombined operators are currently not supported" };
		Error e = new Error(EnumError.COMPILER_GENERIC, args);
		return e;
	}

	/**
	 * Stores a single partitioning description for operator
	 * 
	 * @param opId
	 * @param partDesc
	 */
	private void storePartDesc(Identifier opId, PartitionDesc partDesc) {
		if (!this.op2partDesc.containsKey(opId)) {
			this.op2partDesc.put(opId, new HashSet<PartitionDesc>());
		}
		PartitionDesc newPartDesc = new PartitionDesc(partDesc);
		newPartDesc.renameTable(opId);
		this.op2partDesc.get(opId).clear();
		this.op2partDesc.get(opId).add(newPartDesc);
	}

	/**
	 * Stores multiple partitioning descriptions for an operator. Removes
	 * partitioning descriptions of type NO_PARTITIONING if set has a size
	 * greater than 1
	 * 
	 * @param opId
	 * @param partDescs
	 */
	private void storePartDescs(Identifier opId, Set<PartitionDesc> partDescs) {
		boolean removeNoPartition = false;
		if (partDescs.size() > 1)
			removeNoPartition = true;

		Set<PartitionDesc> newPartDescs = new HashSet<PartitionDesc>(
				partDescs.size());
		for (PartitionDesc partDesc : partDescs) {
			if (removeNoPartition && !partDesc.isPartitioned())
				continue;

			PartitionDesc newPartDesc = new PartitionDesc(partDesc);
			newPartDesc.renameTable(opId);
			newPartDescs.add(newPartDesc);
		}
		this.op2partDesc.put(opId, newPartDescs);
	}

	/**
	 * Checks if one partitioning description in set is compatible with group-by
	 * expression
	 * 
	 * @param partDescs
	 * @param groupExprs
	 * @return
	 */
	private boolean isPartDescCompatible(Set<PartitionDesc> partDescs,
			Collection<AbstractExpression> groupExprs) {
		// if no grouping is needed
		if (groupExprs.size() == 0)
			return false;

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

	/**
	 * Checks if one partitioning description in set is compatible with given
	 * join attribute
	 * 
	 * @param partDescs
	 * @param joinAtt
	 * @return
	 */
	private boolean isPartDescCompatible(Set<PartitionDesc> partDescs,
			TokenAttribute joinAtt) {
		for (PartitionDesc partDesc : partDescs) {
			if (partDesc.isCompatible(joinAtt))
				return true;
		}

		return false;
	}

	/**
	 * Checks if at least one partitioning description from the left set is
	 * compatible with at least one from the right set
	 * 
	 * @param lPartDescs
	 * @param rPartDescs
	 * @return
	 */
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

	private String generateInternalAlias() {
		return this.internalAlias.clone().append(++this.lastInternalAlias)
				.toString();
	}

	private GenericAggregation createPreAggregation(GenericAggregation agg,
			Map<AbstractExpression, AbstractExpression> replaceExprs) {
		agg.getChild().clearParents();
		GenericAggregation preAgg = new GenericAggregation(agg.getChild());
		TokenIdentifier internalCntAlias = new TokenIdentifier(
				generateInternalAlias());
		AggregationExpression sumExpr = AggregationExpression
				.createSumExpr(internalCntAlias);

		// add all aggregation expressions and replace AVG by SUM and COUNT
		boolean addCount = false;
		for (AbstractExpression expr : agg.getAggregationExpressions()) {
			for (AggregationExpression aggExpr : expr.getAggregations()) {
				boolean foundAvg = false;
				AggregationExpression aggExprClone = new AggregationExpression(
						aggExpr);
				if (aggExprClone.getAggregation().isAvg()) {
					aggExprClone.setAggregation(EnumAggregation.SUM);
					foundAvg = true;
					addCount = true;
				}
				preAgg.addAggregationExpression(aggExprClone);
				TokenIdentifier internalAlias = new TokenIdentifier(
						generateInternalAlias());
				preAgg.addAlias(internalAlias);

				// fill replace expression
				AggregationExpression replaceAggExpr = new AggregationExpression();
				replaceAggExpr.setAggregation(aggExprClone.getAggregation());
				replaceAggExpr.setExpression(new SimpleExpression(
						new TokenAttribute(internalAlias)));

				if (foundAvg) {
					ComplexExpression replaceAvgExpr = new ComplexExpression(
							EnumExprType.MULT_EXPRESSION);
					replaceAvgExpr.setExpr1(replaceAggExpr);
					replaceAvgExpr.addOp(EnumExprOperator.SQL_DIV);
					replaceAvgExpr.addExpr2(sumExpr);
					replaceExprs.put(aggExpr, replaceAvgExpr);
				} else {
					replaceExprs.put(aggExpr, replaceAggExpr);
				}
			}
		}

		// if extra count aggregation is needed
		if (addCount) {
			AggregationExpression cntExpr = AggregationExpression
					.createCountExpr();
			preAgg.addAggregationExpression(cntExpr);
			preAgg.addAlias(internalCntAlias);
		}

		// add group-by expressions
		for (AbstractExpression grpExpr : agg.getGroupExpressions()) {
			preAgg.addGroupExpression(grpExpr);
			TokenIdentifier internalAlias = new TokenIdentifier(
					generateInternalAlias());
			preAgg.addAlias(internalAlias);

			// fill replace expression
			SimpleExpression replaceExpr = new SimpleExpression(
					new TokenAttribute(internalAlias));
			replaceExprs.put(grpExpr, replaceExpr);
		}

		// add operator to plan
		this.cPlan.addOperator(preAgg, false);

		// create result of preAgg
		Map<AbstractToken, EnumSimpleType> types = agg.getChild().getResult()
				.createAttribute2TypeMap();
		CheckOperatorVisitor checkVis = new CheckOperatorVisitor(null, types);
		checkVis.visitGenericAggregation(preAgg);
		CreateResultVisitor resultVis = new CreateResultVisitor(null, types);
		resultVis.visitGenericAggregation(preAgg);
		preAgg.getResult().materialize(true);
		preAgg.getResult().repartition(true);

		return preAgg;
	}

	private GenericAggregation createPostAggregation(GenericAggregation agg,
			GenericAggregation preAgg,
			Map<AbstractExpression, AbstractExpression> replaceExpr) {

		agg.setChild(preAgg);
		preAgg.setParent(agg);
		agg.replaceExpression(replaceExpr);

		return agg;
	}
}
