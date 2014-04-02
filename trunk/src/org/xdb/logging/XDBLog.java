package org.xdb.logging;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.xdb.Config;

/**
 * Simple file logging
 * 
 * @author cbinnig
 * 
 */
public class XDBLog extends Logger{
	
	protected XDBLog(String name) {
		super(name, null);
	}

	@Override
	public void log(Level level, String msg) {
		if (Config.LOGGING_ENABLED)
			super.log(level, msg);
		else
			System.err.println(level + ">" + msg);
	}

	public static Logger getLogger(EnumXDBComponents comp) {
		return getLogger(comp.toString());
	}
	
	public static Logger getLogger(String name) {
		LogManager manager = LogManager.getLogManager();
		Logger logger = manager.getLogger(name);
		if (logger == null) {
			logger = new XDBLog(name);
			manager.addLogger(logger);
			
			try {
				logger.setUseParentHandlers(false);
				FileHandler handler = new FileHandler(Config.LOG_FILE+name, true);
				handler.setFormatter(new XDBLogFormat());
				logger.addHandler(handler);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.setLevel(Config.LOG_LEVEL);
		return logger;
		
	}
}