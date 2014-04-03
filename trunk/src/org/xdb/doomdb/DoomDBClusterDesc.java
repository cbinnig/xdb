package org.xdb.doomdb;

import java.io.Serializable;

import org.xdb.Config;

public class DoomDBClusterDesc implements Serializable{

	private static final long serialVersionUID = 7457437477634969527L;

	private int numberOfNodes = 1;
	private int startPort = Config.COMPUTE_PORT;
	
	public DoomDBClusterDesc(int numberOfNodes){
		this.numberOfNodes = numberOfNodes;
	}
	
	public int getNumberOfNodes(){
		return this.numberOfNodes;
	}
	
	public int getStartPort(){
		return this.startPort;
	}
	
	public int getSize(){
		return this.numberOfNodes;
	}
}
