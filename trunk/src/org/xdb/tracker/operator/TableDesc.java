package org.xdb.tracker.operator;

import java.io.Serializable;
import java.net.URI;

import org.xdb.utils.Identifier;

public class TableDesc implements Serializable {

	private static final long serialVersionUID = 8232468468334368891L;

		//logical name of table 
		private String tableName;
		
		//operator which produces table
		private Identifier operatorId;
		
		//table connection
		private URI uri;
		
		//constructors
		public TableDesc(String tableName, Identifier operatorId) {
			super();
			this.tableName = tableName;
			this.operatorId = operatorId;
		}

		public TableDesc(String tableName, URI uri) {
			super();
			this.tableName = tableName;
			this.uri = uri;
		}

		//getter and setters
		public boolean isTemp(){
			return this.operatorId != null;
		}
		
		public String getTableName() {
			return tableName;
		}

		public Identifier getOperatorID() {
			return operatorId;
		}

		public URI getURI() {
			return uri;
		}

		//methods
		@Override
		public int hashCode(){
			return this.tableName.hashCode();
		}
		
		@Override
		public String toString(){
			return this.tableName;
		}
}