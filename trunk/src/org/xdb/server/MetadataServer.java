package org.xdb.server;

import org.xdb.error.Error;
import org.xdb.metadata.Catalog;

/**
 * 
 * @author cbinnig
 *
 * TODO:
 * - AbstractStatement: compile - need to check for max. length of values
 */
public class MetadataServer {

	public static Error start() {
		Error lastError;
	
		//init catalog from database
		lastError = Catalog.load();
		
		return lastError;
	}
	
	public static synchronized Error delete() {
		return Catalog.delete();
	}

}
