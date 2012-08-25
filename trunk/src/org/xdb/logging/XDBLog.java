package org.xdb.logging;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.xdb.Config;

/**
 * Simple file logging
 * @author cbinnig
 *
 */
public class XDBLog {
	private static Logger logger = Logger.getLogger("XDB");

	static {
		logger.setLevel(Config.LOG_LEVEL);
		try {
			logger.setUseParentHandlers(false);
			FileHandler handler = new FileHandler(Config.LOG_FILE, true);
			handler.setFormatter(new XDBLogFormat());
			logger.addHandler(handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public static synchronized Logger getLogger(String sub) {
		Logger subLogger = logger.getLogger(sub);
		subLogger.setParent(logger);
		subLogger.setUseParentHandlers(true);
		return subLogger;
	}
}
