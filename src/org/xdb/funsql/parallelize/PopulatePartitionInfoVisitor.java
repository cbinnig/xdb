package org.xdb.funsql.parallelize;

import java.util.ArrayList;
import java.util.List;

import org.xdb.error.Error;
import org.xdb.funsql.compile.CompilePlan;
import org.xdb.funsql.compile.analyze.operator.AbstractBottomUpTreeVisitor;
import org.xdb.funsql.compile.operator.AbstractCompileOperator;
import org.xdb.funsql.compile.operator.DataExchangeOperator;
import org.xdb.funsql.compile.operator.EnumOperator;
import org.xdb.funsql.compile.operator.EquiJoin;
import org.xdb.funsql.compile.operator.GenericAggregation;
import org.xdb.funsql.compile.operator.GenericProjection;
import org.xdb.funsql.compile.operator.GenericSelection;
import org.xdb.funsql.compile.operator.Rename;
import org.xdb.funsql.compile.operator.SQLCombined;
import org.xdb.funsql.compile.operator.SQLJoin;
import org.xdb.funsql.compile.operator.SQLUnary;
import org.xdb.funsql.compile.operator.TableOperator;
import org.xdb.metadata.EnumPartitionType;

/**
 * This visitor populates the partition info bottom up through the plan. A
 * specific behavior is that at a specific place in the plan more than one
 * possible variation is possible the second is added to the set of considered
 * Compile plans in the parallelizer. This special case is apply while dealing
 * with joins and to decide to use which side of the join parameter.
 * 
 * @author A.C.Mueller
 * 
 */
public class PopulatePartitionInfoVisitor extends AbstractBottomUpTreeVisitor {

	private CompilePlan compilePlan;
	private Parallelizer parallelizer;
	// For counting DataExchange Operators

	private boolean addVariations = false;

	private Error error = new Error();

	private ArrayList<String> consideredVariations;

	public PopulatePartitionInfoVisitor(AbstractCompileOperator root,
			CompilePlan compilePlan, Parallelizer parallelizer,
			boolean addVariations, ArrayList<String> consideredVariations) {
		super(root);
		this.compilePlan = compilePlan;
		this.parallelizer = parallelizer;
		this.addVariations = addVariations;
		this.consideredVariations = consideredVariations;

	}

	public void reset(AbstractCompileOperator root, CompilePlan compilePlan,
			Parallelizer parallelizer, boolean addVariations, ArrayList<String> consideredVariations) {
		this.compilePlan = compilePlan;
		this.treeRoot = root;
		this.parallelizer = parallelizer;
		this.addVariations = addVariations;
		this.error = new Error();
		this.consideredVariations = consideredVariations;
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		this.setPartitionInfoFromChildren(ej);
		return error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		return error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		this.setPartitionInfoFromChildren(gs);
		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		this.setPartitionInfoFromChildren(sa);
		return error;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		this.setPartitionInfoFromChildren(gp);
		return error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		// DO nothing, because is leaf
		return error;
	}

	@Override
	public Error visitRename(Rename ro) {
		this.setPartitionInfoFromChildren(ro);
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
		// Update Dataexchange only if not already set (So Input + Output
		// Partitioning is set)

		if (deOp.getOutputPartitionInfo() != null
				&& deOp.getInputPartitioning() != null)
			return new Error();
		// set inputPartitioning
		deOp.setInputPartitioning(deOp.getChild().getOutputPartitionInfo());

		// PartitionInfo pi = deOp.getOutputPartitionInfo();
		// if the deOp and the child have both Hash as Partition Type, then
		// update Parts
		// TODO: Must have a second thought on this part. Seems strange (Comment by Erfan)
		if (deOp.getChild().getOutputPartitionInfo().getPartitionType()
				.equals((deOp.getOutputPartitionInfo().getPartitionType()))) {
			deOp.getOutputPartitionInfo().setParts(
					deOp.getChild().getOutputPartitionInfo().getParts());
		}

		// if Hash Partitioned and Parts == 0 change to No_Partition
		// TODO: I commented out the code below, however, must put another thought into it.
		/*
		if (pi.getPartitionType().equals(EnumPartitionType.HASH) && pi.getParts() == 0) {
			pi = new PartitionInfo(EnumPartitionType.NO_PARTITION);
			deOp.setOutputPartitionInfo(pi);
		}
		 */

		return error;
	}

	/**
	 * Author: EZamanain
	 * @param parentOp
	 */
	private void setPartitionInfoFromChildren(AbstractCompileOperator operation) {
		// detect join
		if (operation.getChildren().size() > 1) {
			// join
			if (operation.getType().equals(EnumOperator.EQUI_JOIN)) {

				// two inputs, both with one attribute
				EquiJoin ej = (EquiJoin) operation;

				// get children
				AbstractCompileOperator left = ej.getLeftChild();
				AbstractCompileOperator right = ej.getRightChild();

				// update Children parts, to realize parts relationship
				updateChildrenPartitionParts(left, right);

				// left.setPartitionOutputInfo(partitionOutputInfo)
				// generate Output Partition Info
				buildJoinOutputPartioning(ej, left, right);
				// type has to be anything, but not not partioned
			}
		} else {
			if (operation.getOutputPartitionInfo() == null) {
				operation.setOutputPartitionInfo(operation.getChildren().get(0)
						.getOutputPartitionInfo());
			}
		}

	}

	/*
	Alex's Code: 
	private void setPartitionInfoFromParent(AbstractCompileOperator parentOp) {

		// detect join
		if (parentOp.getChildren().size() > 1) {
			// join
			if (parentOp.getType().equals(EnumOperator.EQUI_JOIN)) {
				// two inputs, both with one attribute
				EquiJoin ej = (EquiJoin) parentOp;

				// get childs
				AbstractCompileOperator left = ej.getLeftChild();

				AbstractCompileOperator right = ej.getRightChild();
				// update Children parts, to realize parts relationship
				updateChildrenPartitionParts(left, right);

				// left.setPartitionOutputInfo(partitionOutputInfo)
				// generate Output Partition Info
				buildJoinOutputPartioning(ej, left, right);
				// type has to be anything, but not not partioned
			}
		} else {
			if (parentOp.getOutputPartitionInfo() == null) {
				parentOp.setOutputPartitionInfo(parentOp.getChildren().get(0)
						.getOutputPartitionInfo());
			}
		}

	}
	 */
	private void updateChildrenPartitionParts(AbstractCompileOperator left,
			AbstractCompileOperator right) {
		PartitionInfo leftpi = left.getOutputPartitionInfo();
		PartitionInfo rightpi = right.getOutputPartitionInfo();
		if (leftpi.getParts() > 0 && rightpi.getParts() > 0) {
			if (leftpi.getPartitionType().equals(rightpi.getPartitionType())) {
				// get integervalue of parts

				int rightparts = rightpi.getParts();

				// TODO: The current code always prefers the left leg child and overwrite its info on the right.
				right.getOutputPartitionInfo().setParts(rightparts);
			}

		}
	}

	private void buildJoinOutputPartioning(EquiJoin ej,
			AbstractCompileOperator left, AbstractCompileOperator right) {
		PartitionInfo pi = null;
		PartitionInfo piLeft = left.getOutputPartitionInfo();
		PartitionInfo piRight = right.getOutputPartitionInfo();

		//partition is defined by a triple (TYPE, Attribute, parts)

		//if both have the same type
		//left leading:
		if (piLeft.equals(piRight)) {
			pi = piRight;
		} else {
			// both not equal
			// check type of both
			if (!piRight.getPartitionType().equals(EnumPartitionType.NO_PARTITION)
					&& !piLeft.getPartitionType().equals(EnumPartitionType.NO_PARTITION)) {

				if(piRight.getPartitionType().isCompatibleWith(piLeft.getPartitionType())){
					// TODO: and what if their types are not the same?
					pi = new PartitionInfo(piLeft);
					pi.addPartitionAttributeSet(piRight.getPartitionAttributeSet());

					//right leading (copy)
					CompilePlan newPlan = new CompilePlan(this.compilePlan);
					// Consider new Combination
					buildRightJoinAlternative(ej, piLeft, piRight, newPlan);


					//left leading 
					piRight.setParts(piLeft.getParts());
					String leftstring = ej.getLeftTokenAttribute().getName().getName()+piLeft.getParts()+ ej.getRightTokenAttribute().getName().getName()+piLeft.getParts();

					this.consideredVariations.add(leftstring);			
				}			

			} else {
				// only one side is partitioned, or both not
				if (!piRight.getPartitionType().equals(
						EnumPartitionType.NO_PARTITION)) {
					// right is partitioned
					pi = new PartitionInfo(piRight);
					PartitionAttributeSet pas = new PartitionAttributeSet();
					pas.addAttribute(ej.getLeftTokenAttribute());
					List<PartitionAttributeSet> wrapper = new ArrayList<PartitionAttributeSet>();
					wrapper.add(pas);
					pi.addPartitionAttributeSet(wrapper);
				} else if (!piLeft.getPartitionType().equals(
						EnumPartitionType.NO_PARTITION)) {
					// left is partitioned
					pi = new PartitionInfo(piLeft);
					PartitionAttributeSet pas = new PartitionAttributeSet();
					pas.addAttribute(ej.getRightTokenAttribute());
					List<PartitionAttributeSet> wrapper = new ArrayList<PartitionAttributeSet>();
					wrapper.add(pas);
					pi.addPartitionAttributeSet(wrapper);
				} else {
					// both not, so not relevant which one to choose
					pi = piLeft;
				}

			}
			// copy alternatives
		}

		ej.setOutputPartitionInfo(pi);
	}

	/**
	 * Add the right Join alternative to the plan
	 * 
	 * @param ej
	 * @param piLeft
	 * @param piRight
	 * @param newPlan
	 */
	private void buildRightJoinAlternative(EquiJoin ej, PartitionInfo piLeft,
			PartitionInfo piRight, CompilePlan newPlan) {


		String rightstring = ej.getLeftTokenAttribute().getName().getName()
				+ piRight.getParts()
				+ ej.getRightTokenAttribute().getName().getName()
				+ piRight.getParts();

		if(this.consideredVariations.contains(rightstring)) return;

		this.consideredVariations.add(rightstring);

		// TODO: The right child part always overwrites the left child. Must be fixed.
		EquiJoin newEJ = (EquiJoin) newPlan.getOldOptoNewOpMap().get(ej);
		newEJ.getLeftChild().getOutputPartitionInfo().setParts(piRight.getParts());;
		newEJ.setOutputPartitionInfo(new PartitionInfo(piRight));
		//newEJ.getOutputPartitionInfo().addPartitionAttributes(piLeft.getPartitionAttributes());

		AbstractCompileOperator newRoot = newPlan.getOldOptoNewOpMap().get(
				this.treeRoot);
		int deleteindex = newPlan.getCopyVistorRoots().indexOf(
				newRoot.getOperatorId());

		newPlan.getCopyVistorRoots().remove(deleteindex);
		addVariationToConsideredCompilePlans(newPlan);
	}

	private void addVariationToConsideredCompilePlans(CompilePlan cp) {
		if (this.addVariations) {
			this.parallelizer.addNewPossibleCompilePlan(cp);
		}
	}

	public ArrayList<String> getConsideredVariations() {
		return consideredVariations;
	}

	public void setConsideredVariations(ArrayList<String> consideredVariations) {
		this.consideredVariations = consideredVariations;
	}
}