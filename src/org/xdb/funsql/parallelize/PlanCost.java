package org.xdb.funsql.parallelize;

public class PlanCost {
	int repartitionOperatorCnt;
	int accumulativeDistanceToRoots;
	
	public PlanCost() {
		this.repartitionOperatorCnt = 0;
		this.accumulativeDistanceToRoots = 0;
	}
	
	public int getAccumulativeDistanceToRoots() {
		return accumulativeDistanceToRoots;
	}
	
	public int getRepartitionOperatorCnt() {
		return repartitionOperatorCnt;
	}
	
	public void setAccumulativeDistanceToRoots(int accumulativeDistanceToRoots) {
		this.accumulativeDistanceToRoots = accumulativeDistanceToRoots;
	}
	
	public void setRepartitionOperatorCnt(int repartitionOperatorCnt) {
		this.repartitionOperatorCnt = repartitionOperatorCnt;
	}
	
	public boolean isCheaperThan(PlanCost planCost){
		if (this.getRepartitionOperatorCnt() < planCost.getRepartitionOperatorCnt())
			return true;
		if (this.getRepartitionOperatorCnt() == planCost.getAccumulativeDistanceToRoots()
				&& this.accumulativeDistanceToRoots < planCost.getAccumulativeDistanceToRoots())
			return true;
		return false;		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RP operator cnt: ");
		sb.append(this.getRepartitionOperatorCnt());
		sb.append('\t');
		sb.append("Accumulative distance to roots: ");
		sb.append(this.getAccumulativeDistanceToRoots());
		return sb.toString();
	}
}
