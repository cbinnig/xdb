package org.xdb.test.doomdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.xdb.Config;
import org.xdb.doomdb.DoomDBClient;
import org.xdb.execute.ComputeNodeDesc;
import org.xdb.utils.Tuple;

/**
 * Introduces node failures using normal distribution based on MTBF per node
 * 
 * @author cbinnig
 *
 */
public class DoomDBFailureSimulator extends Thread {

	// client to kill nodes
	private DoomDBClient dbClient;
	
	//list of compute nodes
	private Vector<ComputeNodeDesc> nodeDescs = new Vector<ComputeNodeDesc>();
	
	// list <nodeIdx, MTBF> for killing
	private Queue<Tuple<Integer, Long>> globalMTBFs = new LinkedList<Tuple<Integer, Long>>(); 
	

	// constructor
	public DoomDBFailureSimulator(DoomDBClient dClient) {
		// client
		this.dbClient = dClient;

		// initialize nodes
		this.nodeDescs = new Vector<ComputeNodeDesc>(this.dbClient.getPlan()
				.getComputeNodes());

		// initialize kill times
		this.initMTBFs();
	}

	// initialize globalMTBFs
	private void initMTBFs() {
		double mean = (double) this.dbClient.getMTBF();
		double stdev = Config.DOOMDB_MTBF_STDEV;
		System.out.println("Init failure simulator with mtbf="+mean+"!");
		
		// generate failure time stamps per node
		Vector<Queue<Long>> timestampsPerNode = new Vector<Queue<Long>>(
				this.nodeDescs.size()); 
	
		// if trace from previous test is activated 
		// then read the timestamps from the the trace file 
		if(Config.TRACE_FAILURE_SIMULATOR) { 
			try {  
				// open the file to read
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader("./stat/mtbf/mtbf_"+mean+"_"+Config.RUN_NUMBER+".trace"));  
				System.out.println("Read MTBF trace from mtbf_"+mean+"_"+Config.RUN_NUMBER+".trace");
				String line = "";    
				while ((line = br.readLine()) != null) {    
					LinkedList<Long> mtbfList = new LinkedList<Long>();
					line = line.substring(0, line.length()-1);
					String[] timeStampsList = line.split(","); 
					for (String timeStamp : timeStampsList) {
						mtbfList.add(Long.parseLong(timeStamp));
					} 
					timestampsPerNode.add(mtbfList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}  
		} 
		for (int i = 0; i < this.nodeDescs.size(); ++i) {
			if(Config.TRACE_FAILURE_SIMULATOR) 
				break;

			LinkedList<Long> mtbfList = new LinkedList<Long>();
			timestampsPerNode.add(mtbfList);
			ExponentialDistribution dist = new ExponentialDistribution(mean, stdev);
			
			for (int j = 0; j < Config.DOOMDB_NUM_FAILUERS; ++j) {
				
				// next MTBF for node i
				long mtbf = (long) dist.sample() * 1000;
				
				// first failure can happen any time
				if(j==0) {
					mtbf = (long)(mtbf * Math.random());   
					System.out.println("First MTBF: "+mtbf);
				}
				
				// calculate time stamp from MTBF
				Long timestamp = mtbf;
				if (j > 0) {
					timestamp += mtbfList.get(j - 1);
				} 
	
				mtbfList.add(timestamp);
			} 
		}

		// merge sort time stamps
		Queue<Tuple<Integer, Long>> globalTimestamps = new LinkedList<Tuple<Integer, Long>>();
		Queue<Tuple<Integer, Long>> tmpGlobalTimestamps = new LinkedList<Tuple<Integer, Long>>();

		for (int i = 0; i < timestampsPerNode.size(); ++i) {
			Queue<Long> localTimestamps = timestampsPerNode.get(i);
			tmpGlobalTimestamps.clear();

			// merge sort
			while (globalTimestamps.size() > 0 && localTimestamps.size() > 0) {
				if (globalTimestamps.peek().getObject2() < localTimestamps.peek()) {
					tmpGlobalTimestamps.add(globalTimestamps.poll());
				} else {
					tmpGlobalTimestamps.add(new Tuple<Integer, Long>(i,
							localTimestamps.poll()));
				}
			}

			// add rest from globalTimestamps
			if (globalTimestamps.size() > 0) {
				tmpGlobalTimestamps.addAll(globalTimestamps);
				globalTimestamps.clear();
			}

			// add rest from tmpGlobalTimestamps
			while (localTimestamps.size() > 0) {
				tmpGlobalTimestamps.add(new Tuple<Integer, Long>(i, localTimestamps
						.poll()));
			}

			globalTimestamps.addAll(tmpGlobalTimestamps);
		}
		
		// transform time stamps to MTBFs
		long lastTimestamp = 0;
		for(Tuple<Integer, Long> timestamp: globalTimestamps){
			long mtbf =  timestamp.getObject2()-lastTimestamp;
			mtbf+=this.dbClient.getMTTR();
			lastTimestamp = timestamp.getObject2();
			timestamp.setObject2(mtbf);
			this.globalMTBFs.add(timestamp);
		}
	}

	@Override
	public void run() {
		System.out.println("Starting failure simulator!");
		// Sleep specified time depends on the runtime of a query.
		try {
			killLoop: for (Tuple<Integer, Long> mtbfTuple : this.globalMTBFs) {

				int failedNodeIndex = mtbfTuple.getObject1();
				ComputeNodeDesc node2Kill = this.nodeDescs.get(failedNodeIndex);
				long mtbf = mtbfTuple.getObject2();
				
				try {
					Thread.sleep(mtbf);
				} catch (InterruptedException e) {
					break killLoop;
				}
				SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
			    Date now = new java.util.Date();
			    String strDate = sdfDate.format(now);
			    
				System.err.println("Kill node " + node2Kill + " [" + strDate + "]");
				
				this.dbClient.killNode(node2Kill);  
				//if the compile mode is coarse grained => kill the remaining nodes
                if(Config.NAIVE_STRATEGY_MODE.equalsIgnoreCase("COARSE")) {
                	for(int i=0; i<this.nodeDescs.size(); i++){
                		if(i == failedNodeIndex) 
                			continue;
                		now = new java.util.Date(); 
                		strDate = sdfDate.format(now); 
                		ComputeNodeDesc newNode2Kill = this.nodeDescs.get(i); 
        				System.err.println("Kill another node " + newNode2Kill + " [" + strDate + "]");
        				this.dbClient.killNode(newNode2Kill); 
        				Thread.sleep(1000);
                	}
                }

				if (this.dbClient.isQueryFinished())
					break killLoop;

			}
		} catch (Exception e) {
			// do nothing
		} finally {
			this.interrupt();
		}
	}
}
