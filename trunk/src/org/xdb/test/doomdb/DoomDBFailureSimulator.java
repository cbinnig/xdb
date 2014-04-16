package org.xdb.test.doomdb;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import org.apache.commons.math3.distribution.NormalDistribution;
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

		// generate failure time stamps per node
		Vector<Queue<Long>> timestampsPerNode = new Vector<Queue<Long>>(
				this.nodeDescs.size());
		for (int i = 0; i < this.nodeDescs.size(); ++i) {
			LinkedList<Long> mtbfList = new LinkedList<Long>();
			timestampsPerNode.add(mtbfList);
			NormalDistribution dist = new NormalDistribution(mean, stdev);

			for (int j = 0; j < Config.DOOMDB_NUM_FAILUERS; ++j) {
				
				// next MTBF for node i
				long mtbf = (long) dist.sample() * 1000;
				
				// first failure can happen any time
				if(j==0)
					mtbf = (long)(mtbf * Math.random());
				
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
			mtbf+=Config.DOOMDB_MTTR;
			lastTimestamp = timestamp.getObject2();
			timestamp.setObject2(mtbf);
			this.globalMTBFs.add(timestamp);
		}
	}

	@Override
	public void run() {

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

				System.err.println("Kill node " + node2Kill );
				
				this.dbClient.killNode(node2Kill);

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
