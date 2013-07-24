/**
 * 
 */
package org.xdb.execute.operators;

/**
 * @author Abdallah
 *
 */
public enum QueryOperatorStatus {
     DEPLOYED, // Operator is deployed without failure (First Deployment) 
     REDEPLOYED, // Operator is re-deployed after failure.
     RUNNING, // Operator is executing 
     FINISHED, // Operator is finished
     ABORTED // Operator is failed. 
    , NEGLECTED // Operator is failed but it does not affect the query execution.
}
