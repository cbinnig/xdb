/**
 * 
 */
package org.xdb.faulttolerance.costmodel;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.xdb.Config;

/**
 * @author asalama
 *
 */
public class MTBFTraceGen { 
	
	private static final String NEW_LINE = System.getProperty("line.separator");

	/**
	 * 
	 */
	public MTBFTraceGen() {
		// TODO Auto-generated constructor stub
	} 

	// initialize globalMTBFs
	private void initMTBFs() {
        String mtbfsList = Config.MTBFS_LIST; 
		double stdev = Config.DOOMDB_MTBF_STDEV;
        // convert to array list 
        String[] mtbfs = mtbfsList.split(","); 
        List<Double> mtbfsListTest = new ArrayList<Double>(); 
        
        for (String mtbf : mtbfs) {
        	mtbfsListTest.add(Double.parseDouble(mtbf.trim()));
		} 
        for(int i=0; i<mtbfsListTest.size(); i++){
        	double mean = mtbfsListTest.get(i); 
        	for(int j=0; j<Config.NUMBER_OF_RUNS; j++){
        		// generate failure time stamps per node
        		Vector<Queue<Long>> timestampsPerNode = new Vector<Queue<Long>>(Config.NUMBER_OF_NODES); 
        		File file;
        		file = new File("./stat/mtbf/mtbf_"+mean+"_"+(j+1)+".trace");   
        		BufferedOutputStream bos = null;  

        		if(file.exists()) 
        			file.delete();
        		try {
        			file.createNewFile(); 
        			FileOutputStream fos = new FileOutputStream(file, true);
        			bos = new BufferedOutputStream(fos);
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}	 
        		// 
        		for (int k = 0; k < Config.NUMBER_OF_NODES; ++k) {

        			LinkedList<Long> mtbfList = new LinkedList<Long>();
        			timestampsPerNode.add(mtbfList);
        			ExponentialDistribution dist = new ExponentialDistribution(mean);
        			for (int z = 0; z < Config.DOOMDB_NUM_FAILUERS; ++z) {

        				// next MTBF for node i
        				long mtbf = (long) dist.sample() * 1000;

        				// first failure can happen any time
        				if(z==0) {
        					mtbf = (long)(mtbf * Math.random());   
        					System.out.println("First MTBF: "+mtbf);
        				}

        				// calculate time stamp from MTBF
        				Long timestamp = mtbf;
        				if (z > 0) {
        					timestamp += mtbfList.get(z - 1);
        				} 
        				try { 
        					String timeStamp = timestamp+","; 
        					bos.write(timeStamp.getBytes()); 
        				} catch (IOException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        				mtbfList.add(timestamp);
        			} 
        			try { 
        				bos.write(NEW_LINE.getBytes());
        				bos.flush();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		}
   
        	}

        }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MTBFTraceGen mTBFTraceGen = new MTBFTraceGen();
		mTBFTraceGen.initMTBFs();
        
	}

}
