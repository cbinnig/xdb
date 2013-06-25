package org.xdb.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;

import org.xdb.Config;

public class XDBExecuteTimeMeasurement {
	private HashMap<String,Long> timeMap;
	private PrintWriter p_writer; 
	private HashMap<String,Long> executionTimes; 
	
	
	private XDBExecuteTimeMeasurement(String filename){
		timeMap = new HashMap<String,Long>(); 
		executionTimes = new HashMap<String,Long>(); 
		try {
			boolean not_existed = false;
			File f = new File ("log/"+filename+".csv");
			
			if (! f.exists()){
				not_existed = true;
			}
			FileWriter fw = new FileWriter(f, true);
			this.p_writer = new PrintWriter (fw);
			if(not_existed) this.printHeader();
			not_existed=false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized XDBExecuteTimeMeasurement getXDBExecuteTimeMeasurement(String filename){
		return new XDBExecuteTimeMeasurement(filename);
	}
	
	private void logTime(String id, long time) {
		p_writer.println(id+";"+time);
		this.flush();
	}
	private void printHeader(){
		p_writer.println("#id;time_in_ms");
		this.flush();
	}
	
	public void start(String id){
		if(Config.LOG_EXECUTION_TIME){
			timeMap.put(id, System.currentTimeMillis()); 
		}
	}
	
	public void stop(String id){
		if(Config.LOG_EXECUTION_TIME){ 
			long dif = System.currentTimeMillis() - timeMap.get(id); 
			this.logTime(id, dif); 
			this.executionTimes.put(id, dif);
		}
	}
	
	private void flush(){
		p_writer.flush();
	}
	
	public void close(){
		p_writer.close();
	}

	/**
	 * @return the executionTime
	 */
	public Long getExecutionTime(String id) {
		return this.executionTimes.get(id);
	}

	
	
}

