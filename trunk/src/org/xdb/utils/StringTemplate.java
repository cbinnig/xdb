package org.xdb.utils;

import java.util.Map;
import java.util.Map.Entry;

/**
 * String template implementation
 * @author cbinnig
 *
 */
public class StringTemplate {
	private static final String START_TAG = "<";
	private static final String END_TAG = ">";
	
	private String template;
	
	//Constructors
	public StringTemplate(String template){
		this.template = template;
	}
	
	/**
	 * Generate instantiated string from template
	 * @param args
	 * @return
	 */
	public String toString(Map<String, String> args){
		String instance = new String(this.template);
		
		for(Entry<String, String> entry: args.entrySet()){
			StringBuffer key = new StringBuffer(START_TAG);
			key.append(entry.getKey());
			key.append(END_TAG);
			
			instance = instance.replaceAll(key.toString(), entry.getValue());
		}
		return instance;
	}
	
	@Override 
	public String toString(){
		return this.template;
	}
}
