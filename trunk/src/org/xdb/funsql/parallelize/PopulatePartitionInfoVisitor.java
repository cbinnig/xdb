package org.xdb.funsql.parallelize;

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
import org.xdb.funsql.compile.tokens.TokenAttribute;

public class PopulatePartitionInfoVisitor extends AbstractBottomUpTreeVisitor {


	private CompilePlan compilePlan;
	private Parallelizer parallelizer;
	//For counting DataExchange Operators
	private int deOps;
	
	private boolean addVariations = false;
	
	private Error error = new Error();
	public PopulatePartitionInfoVisitor(AbstractCompileOperator root, CompilePlan compilePlan, Parallelizer parallelizer, boolean addVariations) {
		super(root);
		this.compilePlan= compilePlan;
		this.parallelizer = parallelizer;
		this.addVariations = addVariations;
	}
	
	public void reset(AbstractCompileOperator root, CompilePlan compilePlan, Parallelizer parallelizer, boolean addVariations){
		this.compilePlan = compilePlan;
		this.treeRoot = root;
		this.parallelizer = parallelizer;
		this.addVariations = addVariations;
		this.error = new Error();
	}

	@Override
	public Error visitEquiJoin(EquiJoin ej) {
		this.setPartitionInfoFromParent(ej);
		return  error;
	}

	@Override
	public Error visitSQLJoin(SQLJoin ej) {
		return  error;
	}

	@Override
	public Error visitGenericSelection(GenericSelection gs) {
		this.setPartitionInfoFromParent(gs);
		return error;
	}

	@Override
	public Error visitGenericAggregation(GenericAggregation sa) {
		this.setPartitionInfoFromParent(sa);
		return  error;
	}

	@Override
	public Error visitGenericProjection(GenericProjection gp) {
		this.setPartitionInfoFromParent(gp);
		return  error;
	}

	@Override
	public Error visitTableOperator(TableOperator to) {
		//DO nothing, because is leaf
		
		//add Table name to Partition Parameter
		for (TokenAttribute ta : to.getPartitionOutputInfo().getPartitionAttributes()){
			ta.setName(to.getTableName()+"_" +ta.getName());
		}
		return  error;
	}

	@Override
	public Error visitRename(Rename ro) {
		this.setPartitionInfoFromParent(ro);
		return  error;
	}

	@Override
	public Error visitSQLUnary(SQLUnary absOp) {
		return  error;
	}

	@Override
	public Error visitSQLCombined(SQLCombined absOp) {
		return  error;
	}

	@Override
	public Error visitDataExchange(DataExchangeOperator deOp) {
		
		//set inputPartitionin
		
		deOp.setInputPartitioning(deOp.getChild().getPartitionOutputInfo());
		
		PartitionInfo pi = deOp.getPartitionOutputInfo();
		//update Count
		deOps++;
		
		// if the deOp and the child have both Hash as Partition Type, then update Parts
		if(deOp.getChild().getPartitionOutputInfo().getPartitionType().equals((deOp.getPartitionOutputInfo().getPartitionType()))){
			deOp.getPartitionOutputInfo().setParts(deOp.getChild().getPartitionOutputInfo().getParts());
		}
		
		//if Hash Partitioned and Parts == 0 change to No_Partition
		if(pi.getPartitionType().equals(EnumPartitionType.HASH) && pi.getParts() == 0){
			pi = new PartitionInfo(EnumPartitionType.NO_PARTITION);
			deOp.setPartitionOutputInfo(pi);
		}
		
		
		
		if(isRemoveable(deOp.getPartitionOutputInfo(), deOp.getChild().getPartitionOutputInfo())){
			//remove
			deOps--;
			deOp.getChild().setParent(deOp, deOp.getParents().get(0));
			deOp.getParents().get(0).setChild(deOp, deOp.getChild());
			this.compilePlan.removeOperator(deOp.getOperatorId());
		}
		
		return  error;
	}
	
	private void setPartitionInfoFromParent(AbstractCompileOperator parentOp){

		
		//detect join
		if(parentOp.getChildren().size() > 1){
			// join
			if(parentOp.getType().equals(EnumOperator.EQUI_JOIN)){
				//two inputs, both with one attribute
				EquiJoin ej = (EquiJoin) parentOp;
				
				//get childs
				AbstractCompileOperator left = ej.getLeftChild();
			
				AbstractCompileOperator right = ej.getRightChild();
				//update Children parts, to realize parts relationship
				updateChildrenPartitionParts(left, right);
				
				//generate Output Partition Info
				buildJoinOutputPartioning(ej, left, right);
				//type has to be anything, but not not partioned
			} 
		}else {
			if(parentOp.getPartitionOutputInfo() == null){
				parentOp.setPartitionOutputInfo(parentOp.getChildren().get(0).getPartitionOutputInfo());
			}
		}
		
	}

	private void updateChildrenPartitionParts(AbstractCompileOperator left,
			AbstractCompileOperator right) {
		PartitionInfo leftpi = left.getPartitionOutputInfo();
		PartitionInfo rightpi = right.getPartitionOutputInfo();
		if(leftpi.getParts() > 0 && rightpi.getParts() > 0 ){
			if(leftpi.getPartitionType().equals( rightpi.getPartitionType())){
				if(leftpi.getPartitionType().equals(EnumPartitionType.HASH)){
					//both inputs of the Equijoin are Hash partitioned
					// two combinations of parts possible:
					// combination left parts = right parts
					// or right parts = left parts
					// build first combination: 1. copy Plan 2. change parts 3. Add copied plan to set
					CompilePlan copied_plan = this.compilePlan.copy();
					//get integervalue of parts
					int leftparts = leftpi.getParts();
					int rightparts = rightpi.getParts();
					//right = left
					copied_plan.getOperators(left.getOperatorId()).getPartitionOutputInfo().setParts(rightparts);
					addVariationToConsideredCompilePlans(copied_plan);
					//left = right
					right.getPartitionOutputInfo().setParts(leftparts);
					
				}
			}
		}
	}

	private void buildJoinOutputPartioning(EquiJoin ej,
			AbstractCompileOperator left, AbstractCompileOperator right) {
		PartitionInfo pi;
		PartitionInfo piLeft = left.getPartitionOutputInfo();
		PartitionInfo piRight = right.getPartitionOutputInfo();
		if(piLeft.equals(piRight)){
			pi = piRight;
		}else{
			//both not equal
			
			//check type of both
			if(!piRight.getPartitionType().equals(EnumPartitionType.NO_PARTITION)
					&& !piLeft.getPartitionType().equals(EnumPartitionType.NO_PARTITION)){
				//so Hash partioned
				pi = piRight;
				//add left alternative
				CompilePlan newPlan = this.compilePlan.copy();
				newPlan.getOperators(ej.getOperatorId()).setPartitionOutputInfo(piLeft);
				addVariationToConsideredCompilePlans(newPlan);
			}else{
				//only one side is partitioned, or both not
				if(!piRight.getPartitionType().equals(EnumPartitionType.NO_PARTITION)){
					//right is partitioned
					pi = piRight;
				}else if (!piLeft.getPartitionType().equals(EnumPartitionType.NO_PARTITION)){
					//left is partitioned
					pi = piLeft;
				}else {
					//both not, so not relevant which one to choose
					pi = piLeft;
				}
				
			}
			
			//copy alternatives
		}

		ej.setPartitionOutputInfo(pi);
	}
	
	private boolean isRemoveable(PartitionInfo inputOpInfo, PartitionInfo removeOpInfo){	
		
		return (inputOpInfo.equals(removeOpInfo));
	
	}
	
	private void addVariationToConsideredCompilePlans(CompilePlan cp){
		if(this.addVariations){
			this.parallelizer.addNewPossibleCompilePlan(cp);
		}	
	}

	public int getDeOps() {
		return deOps;
	}
}