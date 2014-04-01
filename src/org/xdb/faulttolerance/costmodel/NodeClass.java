package org.xdb.faulttolerance.costmodel;

/**
 * @author Abdallah
 *
 */
public enum NodeClass {
	A (4, 500000), // |node| = 4, MTBF = 1, 
	B (4, 500000); // |node| = 4, MTBF = 2  
	
	private int meanTimeBetweenFailure; 
	private int numberOfNodesPerClass; 
	
	NodeClass(int numberOfNodesPerClass, int meanTimeBetweenFailure){
		//this.setMeanTimeBetweenFailure(meanTimeBetweenFailure); 
		this.setNumberOfNodesPerClass(numberOfNodesPerClass);
	}

	/**
	 * @return the meanTimeBetweenFailure
	 */
	public int getMeanTimeBetweenFailure1() {
		return meanTimeBetweenFailure;
	}

	/**
	 * @param meanTimeBetweenFailure the meanTimeBetweenFailure to set
	 */
	public void setMeanTimeBetweenFailure1(int meanTimeBetweenFailure) {
		this.meanTimeBetweenFailure = meanTimeBetweenFailure;
	}

	/**
	 * @return the numberOfNodesPerClass
	 */
	public int getNumberOfNodesPerClass1() {
		return numberOfNodesPerClass;
	}

	/**
	 * @param numberOfNodesPerClass the numberOfNodesPerClass to set
	 */
	public void setNumberOfNodesPerClass(int numberOfNodesPerClass) {
		this.numberOfNodesPerClass = numberOfNodesPerClass;
	}
}


