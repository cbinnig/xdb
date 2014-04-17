package org.xdb.server;

public class ServerThread extends Thread {
	private boolean running = false;
	private AbstractServer server;
	
	protected ServerThread(AbstractServer server){
		this.server = server;
	}
	
	@Override
	public void run() {
		this.server.executeServer();
	}

	//getters and setters
	public void setRunning() {
		this.running=true;
	}
	
	public void setNotRunning() {
		this.running=false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Close server socket
	 */
	public void closeSocket(){
		this.server.closeSocket();
	}
}