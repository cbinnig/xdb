package org.xdb.server;

public class ComputeServerThread extends Thread {
	private boolean running = false;
	private ComputeServer server;
	@Override
	public void run() {
		try {
			this.server = new ComputeServer();
			this.server.execute(this);
		} catch (Exception ex) {
		} finally {
			running = false;
		}
	}

	public void setRunning() {
		this.running=true;
	}
	
	public void setNotRunning() {
		this.running=false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void close(){
		this.server.close();
	}
}