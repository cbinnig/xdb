package org.xdb.tracker.operator.udf;

import java.util.Map;

import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.execute.operators.OperatorDesc;
import org.xdb.execute.operators.udf.LevenshteinExecOp;
import org.xdb.tracker.operator.AbstractTrackerOperator;
import org.xdb.utils.Identifier;

/**
 * UDF implementation of Levenshtein (TrackerOp)
 * @author cbinnig
 *
 */
public class LevenshteinTrackerOp extends AbstractTrackerOperator {

	private static final long serialVersionUID = -822553010345167290L;

	private String inTableName;
	private String outTableName;

	public LevenshteinTrackerOp(String inTableName, String outTableName) {
		this.inTableName = inTableName;
		this.outTableName = outTableName;
	}

	@Override
	public AbstractExecuteOperator genDeployOperator(OperatorDesc operDesc,
			Map<Identifier, OperatorDesc> currentDeployment) {
		// create a UDF operator
		Identifier execOpId = operDesc.getOperatorID();

		LevenshteinExecOp execOp = new LevenshteinExecOp(execOpId);

		// generate DDLs for input and output tables and return deployment table
		// names
		Map<String, String> args = this.genInputAndOutput(execOp, operDesc,
				currentDeployment);
		
		execOp.setSqlSelectIn(args.get(inTableName));
		execOp.setOutTableName(args.get(outTableName));
		
		return execOp;
	}

}
