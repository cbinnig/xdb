package org.xdb.funsql.compile.operator;

import java.util.Collection;
import java.util.HashMap;

import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.TokenAttribute;
import org.xdb.funsql.parallelize.PartitionInfo;
import org.xdb.utils.Identifier;

import com.oy.shared.lm.graph.Graph;
import com.oy.shared.lm.graph.GraphNode;

/**
 * Logical operator, which is used to indicate the need for repartitioning in
 * the current data flow
 * 
 * @author Alexander C. Mueller
 * 
 */
public class DataExchangeOperator extends AbstractUnaryOperator {

	private static final long serialVersionUID = 6320968568439747158L;
	
	private PartitionInfo inputPartitioning;
	
	/**
	 * Constructor used when exactly one partion column can be used
	 * @param child
	 * @param result
	 * @param outputPartioning
	 */
	public DataExchangeOperator(AbstractCompileOperator child, ResultDesc result,
			PartitionInfo outputPartioning) {
		super(child);
		this.partitionOutputInfo = outputPartioning;
		this.results.add(result);
		this.type = EnumOperator.DATA_EXCHANGE;

	}
	/**
	 * Copy Constructor
	 * @param toCopy Element to copy
	 */
	public DataExchangeOperator(DataExchangeOperator toCopy){
		super(toCopy);
		if(toCopy.inputPartitioning != null){
			this.inputPartitioning = new PartitionInfo(toCopy.inputPartitioning);
		}
	
	}
	
	
	/**
	 * Constructor used when exactly one partion column can be used
	 * @param child
	 * @param result
	 * @param outputPartioning
	 */
	public DataExchangeOperator(AbstractCompileOperator child, ResultDesc result) {
		super(child);
		this.results.add(result);
		this.type = EnumOperator.DATA_EXCHANGE;

	}
	

	// getters + setters

	@Override
	public String toSqlString() {
		// no need for this because only logical operator
		return null;
	}

	@Override
	public void renameAttributes(String oldChildId, String newChildId) {
		// TODO check if needed

	}

	@Override
	public boolean isLeaf() {
		// can never be a leaf node
		return false;
	}
	
	@Override
	public Error traceOperator(Graph g, HashMap<Identifier, GraphNode> nodes){
		Error err = super.traceOperator(g, nodes);
		
		GraphNode node = nodes.get(this.operatorId);
		if(this.getPartitionOutputInfo() != null){
			node.getInfo().setFooter("Output partitioning: " + this.partitionOutputInfo.toString());

		}
		
		if(this.getInputPartitioning() != null){
			node.getInfo().setFooter(node.getInfo() + " \n Output partitioning: " + this.getInputPartitioning().toString());

		}
		
		return err;
	}

	@Override
	public void renameForPushDown(Collection<TokenAttribute> selAtts) {
		// TODO Auto-generated method stub
		
	}


	public PartitionInfo getInputPartitioning() {
		return inputPartitioning;
	}


	public void setInputPartitioning(PartitionInfo inputPartitioning) {
		this.inputPartitioning = inputPartitioning;
	}	
}