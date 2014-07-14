package org.xdb.faulttolerance.costmodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xdb.utils.Identifier;

/**
 * 
 * @author Abdallah 
 *
 */
public class CostModelOperator implements Comparable<CostModelOperator> { 
	
	//private int id; 
	private Identifier id;
	private String type;   
	private boolean isMaterilaized;  
	private boolean isForcedMaterlialized;  
	private boolean isMerged;
	private double opRunTimeEstimate; 
	private double opMaterializationTimeEstimate;  
	private int degreeOfPartitioning; 
	private Vector<CostModelOperator> parents = new Vector<CostModelOperator>();  
	private Vector<CostModelOperator> children = new Vector<CostModelOperator>();  
	private Map<Identifier, Double> opToChildrenPaths = new HashMap<Identifier, Double>(); 

	public CostModelOperator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Identifier getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Identifier id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the isMaterilaized
	 */
	public boolean isMaterilaized() {
		return isMaterilaized;
	}

	/**
	 * @param isMaterilaized the isMaterilaized to set
	 */
	public void setMaterilaized(boolean isMaterilaized) {
		this.isMaterilaized = isMaterilaized;
	}


	/**
	 * @return the isForcedMaterlialized
	 */
	public boolean isForcedMaterlialized() {
		return isForcedMaterlialized;
	}


	/**
	 * @param isForcedMaterlialized the isForcedMaterlialized to set
	 */
	public void setForcedMaterlialized(boolean isForcedMaterlialized) {
		this.isForcedMaterlialized = isForcedMaterlialized;
	}


	/**
	 * @return the opRunTimeEstimate
	 */
	public double getOpRunTimeEstimate() {
		return opRunTimeEstimate;
	}

	/**
	 * @param opRunTimeEstimate the opRunTimeEstimate to set
	 */
	public void setOpRunTimeEstimate(double opRunTimeEstimate) {
		this.opRunTimeEstimate = opRunTimeEstimate;
	}

	/**
	 * @return the opMaterializationTimeEstimate
	 */
	public double getOpMaterializationTimeEstimate() {
		return opMaterializationTimeEstimate;
	}

	/**
	 * @param opMaterializationTimeEstimate the opMaterializationTimeEstimate to set
	 */
	public void setOpMaterializationTimeEstimate(
			double opMaterializationTimeEstimate) {
		this.opMaterializationTimeEstimate = opMaterializationTimeEstimate;
	}

	/**
	 * @return the degreeOfPartitioning
	 */
	public int getDegreeOfPartitioning() {
		return degreeOfPartitioning;
	}

	/**
	 * @param degreeOfPartitioning the degreeOfPartitioning to set
	 */
	public void setDegreeOfPartitioning(int degreeOfPartitioning) {
		this.degreeOfPartitioning = degreeOfPartitioning;
	}

	/**
	 * @return the parents
	 */
	public Vector<CostModelOperator> getParents() {
		return parents;
	}

	/**
	 * @param parents the parents to set
	 */
	public void setParents(Vector<CostModelOperator> parents) {
		this.parents = parents;
	}

	/**
	 * @return the children
	 */
	public Vector<CostModelOperator> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Vector<CostModelOperator> children) {
		this.children = children;
	} 
	
	public void removeChild(CostModelOperator childToBeRemoved){
		this.children.remove(childToBeRemoved);
	}
	
	public void addChildren(Vector<CostModelOperator> newChildren){
		this.children.addAll(newChildren);
	} 
	
	public void removeParent(CostModelOperator parentToBeRemoved){
		this.parents.remove(parentToBeRemoved);
	} 
	
	public void addParent(CostModelOperator newParent){
		this.parents.add(newParent);
	}
	
	@Override
	public String toString() {
		StringBuffer value = new StringBuffer();
		value.append("(");
		value.append(this.id);
		value.append(":");
		value.append(this.type);
		value.append(")");
		value.append("\n");
		value.append("Runtime:"); 
		value.append(this.opRunTimeEstimate); 
		value.append("\n");
		value.append("Mat Time:"); 
		value.append(this.opMaterializationTimeEstimate); 
		return value.toString();
	}

	/**
	 * @return the opToChildrenPath
	 */
	public Map<Identifier, Double> getOpToChildrenPath() {
		return opToChildrenPaths;
	}

	/**
	 * @param opToChildrenPath the opToChildrenPath to set
	 */
	public void setOpToChildrenPath(Map<Identifier, Double> opToChildrenPaths) {
		this.opToChildrenPaths = opToChildrenPaths;
	} 
	
	public Double getPathTime(Identifier pathId){
		return this.opToChildrenPaths.get(pathId);
	}

	public void addChild(CostModelOperator child) {
		this.children.add(child);
		
	}

	@Override
	public int compareTo(CostModelOperator op) {
		double totalOpRunTime = this.getOpMaterializationTimeEstimate() + this.getOpRunTimeEstimate(); 
		double totalOpRunTimeCompareTo = op.getOpMaterializationTimeEstimate() + op.getOpRunTimeEstimate();
		if(totalOpRunTime < totalOpRunTimeCompareTo)
		    return 1; 
		else if (totalOpRunTime > totalOpRunTimeCompareTo) 
			return -1; 
		else return 0;
	}

	/**
	 * @return the isMerged
	 */
	public boolean isMerged() {
		return isMerged;
	}

	/**
	 * @param isMerged the isMerged to set
	 */
	public void setMerged(boolean isMerged) {
		this.isMerged = isMerged;
	}

}
