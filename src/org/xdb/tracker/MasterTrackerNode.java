package org.xdb.tracker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.error.Error;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.logging.XDBLog;

public class MasterTrackerNode {
	//compute slots
	private Map<String, Integer> computeSlots;
	
	// Helpers
	private Logger logger;
		
		
	public MasterTrackerNode(){
		this.computeSlots = Collections
				.synchronizedMap(new HashMap<String, Integer>()); 
		
		this.logger = XDBLog.getLogger(this.getClass().getName());
	}
	
	public Error registerComputeNode(ComputeNodeDesc desc){
		Error err = new Error();

		logger.log(Level.INFO, "Added compute slots: " + desc);
		this.computeSlots.put(desc.getUrl(), desc.getSlots());
		
		return err;
	}
}
