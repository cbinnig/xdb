package org.xdb.tracker.operator;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.xdb.utils.Identifier;

public class TableDesc implements Serializable {

	private static final long serialVersionUID = 8232468468334368891L;

		//logical name of table 
		private String tableName;
		
		//operator which produces table
		private Identifier operatorId;
		
		//table connections 
		private List<URI> uris;
		
		//constructors
		public TableDesc(String tableName, Identifier operatorId) {
			super();
			this.tableName = tableName;
			this.operatorId = operatorId; 
			
		}

		public TableDesc(String tableName, URI uri) {
			super();
			this.tableName = tableName;
			this.uris.add(uri); 
		} 
		
		// Multiple connections support. 
		public TableDesc(String tableName, List<URI> uris) {
			super();
			this.tableName = tableName;
			this.uris = uris;
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
			if(this.uris.size()==0)
				return null;
			
			return uris.get(0);
		}
		
		public  URI getURI(int i) {
			if(!(i<this.uris.size()))
				return null;
			
			return uris.get(i);
		}

		public List<URI> getUris() {
			return uris;
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

		/**
		 * @param uris the uris to set
		 */
		public void setUris(List<URI> uris) {
			this.uris = uris;
		}
}