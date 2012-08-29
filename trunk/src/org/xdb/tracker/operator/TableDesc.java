package org.xdb.tracker.operator;

import java.io.Serializable;

import org.xdb.utils.Identifier;

public class TableDesc implements Serializable {

	private static final long serialVersionUID = 8232468468334368891L;

	//operator ID
		private String tableName;
		
		//operator URL
		private Identifier operatorId;
		
		//constructors
		public TableDesc(String tableName, Identifier operatorId) {
			super();
			this.tableName = tableName;
			this.operatorId = operatorId;
		}

		//getter and setters
		public String getTableName() {
			return tableName;
		}

		public Identifier getOperatorID() {
			return operatorId;
		}
		
		//methods
		@Override
		public int hashCode(){
			return this.tableName.hashCode();
		}
		
		@Override
		public String toString(){
			return "("+this.operatorId+","+this.tableName+")";
		}
}
