package org.xdb.metadata;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.funsql.compile.tokens.AbstractToken;
import org.xdb.logging.XDBLog;

public class Catalog {
	private static java.sql.Connection conn = null;

	private static HashMap<Long, Attribute> attributes = new HashMap<Long, Attribute>();
	public static HashMap<String, Attribute> attributesByName = new HashMap<String, Attribute>();
	
	private static HashMap<Long, Connection> connections = new HashMap<Long, Connection>();
	private static HashMap<String, Connection> connectionsByName = new HashMap<String, Connection>();

	private static HashMap<Long, Partition> partitions = new HashMap<Long, Partition>();
	private static HashMap<Long, Partition> partitionsByName = new HashMap<Long, Partition>();
	
	private static HashMap<Long, Long> partitionAttributues = new HashMap<Long, Long>();
	

	private static HashMap<Long, List<TableToConnection>> tableToConnByTableOid = new HashMap<Long, List<TableToConnection>>();
	private static HashMap<Long, List<TableToConnection>> tableToConnByConnOid = new HashMap<Long, List<TableToConnection>>();

	private static HashMap<Long, List<PartitionToConnection>> partitionToConnByPartOid = new HashMap<Long, List<PartitionToConnection>>();
	private static HashMap<Long, List<PartitionToConnection>> partitionToConnByConnOid = new HashMap<Long, List<PartitionToConnection>>();

	
	private static HashMap<Long, Schema> schemas = new HashMap<Long, Schema>();
	private static HashMap<String, Schema> schemasByName = new HashMap<String, Schema>();

	private static HashMap<Long, Table> tables = new HashMap<Long, Table>();
	private static HashMap<String, Table> tablesByName = new HashMap<String, Table>();

	private static HashMap<Long, Function> functions = new HashMap<Long, Function>();
	private static HashMap<String, Function> functionsByName = new HashMap<String, Function>();

	private static Logger log = XDBLog.getLogger(Catalog.class.getName());

	public static synchronized Error delete() {
		try {
			Class.forName(Config.METADATA_DRIVER_CLASS);
			Catalog.conn = DriverManager.getConnection(Config.METADATA_DB_URL,
					Config.METADATA_USER, Config.METADATA_PASSWORD);

			Error lastError = Error.NO_ERROR;

			lastError = Catalog.executeUpdate(TableToConnection.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			lastError = Catalog.executeUpdate(PartitionToConnection.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}
			
			lastError = Catalog.executeUpdate(PartitionAttributes.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			lastError = Catalog.executeUpdate(Attribute.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			lastError = Catalog.executeUpdate(Partition.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			lastError = Catalog.executeUpdate(Table.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			lastError = Catalog.executeUpdate(Schema.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			lastError = Catalog.executeUpdate(Connection.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			lastError = Catalog.executeUpdate(Function.sqlDeleteAll());
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}

			Catalog.conn.close();
			Catalog.unload();

		} catch (Exception e) {
			return createCatalogNotAvailableErr(e);
		}

		return Error.NO_ERROR;
	}

	public static synchronized Error load() {
		Error lastError;

		try {
			Class.forName(Config.METADATA_DRIVER_CLASS);
			Catalog.conn = DriverManager.getConnection(Config.METADATA_DB_URL,
					Config.METADATA_USER, Config.METADATA_PASSWORD);
			Catalog.initConnectionToTable();
			Catalog.initPartitionToConnection();
			Catalog.initPartitions();
			Catalog.initPartitionAttributes();
			Catalog.initConnections();
			Catalog.initSchemas();
			Catalog.initTables();
			Catalog.initAttributes();
			Catalog.initFunctions();

			lastError = Catalog.checkCatalog();
			if (lastError != Error.NO_ERROR) {
				return lastError;
			}
		} catch (Exception e) {
			return createCatalogNotAvailableErr(e);
		}
		return Error.NO_ERROR;
	}

	public static synchronized Error unload() {
		Catalog.attributes.clear();
		Catalog.attributesByName.clear();
		Catalog.connections.clear();
		Catalog.connectionsByName.clear();
		Catalog.schemas.clear();
		Catalog.schemasByName.clear();
		Catalog.tables.clear();
		Catalog.tablesByName.clear();
		Catalog.functions.clear();
		Catalog.functionsByName.clear();
		Catalog.partitions.clear();
		Catalog.partitionsByName.clear();
		Catalog.partitionAttributues.clear();
		Catalog.tableToConnByConnOid.clear();
		Catalog.tableToConnByTableOid.clear();
		Catalog.partitionToConnByConnOid.clear();
		Catalog.partitionToConnByPartOid.clear();

		try {
			Catalog.conn.close();
		} catch (Exception e) {
			return createCatalogNotAvailableErr(e);
		}

		return Error.NO_ERROR;
	}

	public static synchronized Error createCatalogNotAvailableErr(Exception e,
			String detail) {
		String arg0 = e.toString();
		String args[] = { arg0, detail };
		return new Error(EnumError.CATALOG_NOT_AVAILABLE, args);
	}

	public static synchronized Error createCatalogNotAvailableErr(Exception e) {
		String arg0 = e.toString();
		String args[] = { arg0, "undefined reason" };
		return new Error(EnumError.CATALOG_NOT_AVAILABLE, args);
	}

	public static synchronized Error createObjectAlreadyExistsErr(
			AbstractDatabaseObject object) {
		String args[] = { object.getName(), object.getObjectType().toString() };
		Error error = new Error(EnumError.CATALOG_OBJECT_ALREADY_EXISTS, args);
		return error;
	}
	
	public static synchronized Error createObjectAlreadyExistsErr(String name,
			EnumDatabaseObject type) {
		String args[] = { name, type.toString() };
		Error error = new Error(EnumError.CATALOG_OBJECT_ALREADY_EXISTS, args);
		return error;
	}

	public static synchronized Error createObjectNotExistsErr(String name,
			EnumDatabaseObject type) {
		String args[] = { name, type.toString() };
		Error error = new Error(EnumError.CATALOG_OBJECT_NOT_EXISTS, args);
		return error;
	}

	public static synchronized Error createObjectValueTooLongErr(String name,
			EnumDatabaseObject type) {
		String args[] = { name, type.toString() };
		Error error = new Error(EnumError.CATALOG_OBJECT_VALUE_TOO_LONG, args);
		return error;
	}

	public static synchronized Error createTableContainsDupAttErr(
			String tableName, String attName) {
		String[] args = { tableName, attName };
		Error lastError = new Error(EnumError.CATALOG_TABLE_DUP_ATTS, args);
		return lastError;
	}
	
	public static synchronized Error createPartitionContainsDupAttErr(
			String tableName, String attName) {
		String[] args = { tableName, attName };
		Error lastError = new Error(EnumError.CATALOG_PART_DUP_ATTS, args);
		return lastError;
	}
	
	public static synchronized Error createPartitionContainsWrongArgumentAttErr(
			String tableName, String attName) {
		String[] args = { tableName, attName };
		Error lastError = new Error(EnumError.CATALOG_PART_WRONG_ATTS, args);
		return lastError;
	}
	


	private static synchronized Error checkCatalog() {
		if (Catalog.getSchema(Config.COMPILE_DEFAULT_SCHEMA) == null) {
			return Catalog.createObjectNotExistsErr(
					Config.COMPILE_DEFAULT_SCHEMA, EnumDatabaseObject.SCHEMA);
		}
		return Error.NO_ERROR;
	}

	private static synchronized Error executeUpdate(String sql) {
		try {
			log.log(Level.INFO, sql);
			Statement stmt = Catalog.conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			return createCatalogNotAvailableErr(e);
		}
		return Error.NO_ERROR;
	}

	// to deal with partitions
	private static synchronized void initPartitions() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(Partition.sqlSelectMaxOid());
		if (rs.next()) {
			Partition.LAST_OID(rs.getLong(1));
		}

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(Partition.sqlSelectAll());
		while (rs.next()) {
			// add attribute to catalog
			long oid = rs.getLong(1);
			long tableOid = rs.getLong(2);
			String partition_name = rs.getString(3);
			Partition part = new Partition(oid, tableOid, partition_name);
			Catalog.addPartition(part);

		}

	}
	
	private static synchronized void initPartitionAttributes() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(PartitionAttributes.sqlSelectAll());
		while (rs.next()) {
			// add PartitionAttributes to catalog
			long partAttOid = rs.getLong(1);
			long refAttOid = rs.getLong(2);
			Attribute partAttObj = Catalog.attributes.get(partAttOid);
			PartitionAttributes partitionAttributes = new PartitionAttributes(partAttOid, refAttOid);
			Catalog.tables.get(partAttObj.getTableOid()).addPartitionAttribute(partitionAttributes);
		}
	}

	private static synchronized void initAttributes() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = Catalog.conn.createStatement();

		rs = stmt.executeQuery(Attribute.sqlSelectMaxOid());
		if (rs.next()) {
			Attribute.LAST_OID(rs.getLong(1));
		}

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(Attribute.sqlSelectAll());
		while (rs.next()) {
			// add attribute to catalog
			long oid = rs.getLong(1);
			String name = rs.getString(2);
			int type = rs.getInt(3);
			long tableOid = rs.getLong(4);
			Attribute att = new Attribute(oid, name, type, tableOid);
			Catalog.addAttribute(att);

			// add attribute to table
			Catalog.tables.get(tableOid).addAttribute(att);
		}
	}

	private static synchronized void initConnectionToTable() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(TableToConnection.sqlSelectAll());
		while (rs.next()) {
			long t_oid = rs.getLong(1);
			long c_oid = rs.getLong(2);
			TableToConnection tableToConnec = new TableToConnection(t_oid,
					c_oid);
			Catalog.addTableToConnection(tableToConnec);
		}
	}
	

	private static synchronized void initPartitionToConnection() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(PartitionToConnection.sqlSelectAll());
		while (rs.next()) {
			long p_oid = rs.getLong(1);
			long c_oid = rs.getLong(2);
			PartitionToConnection tableToConnec = new PartitionToConnection(p_oid,
					c_oid);
			Catalog.addPartitionToConnection(tableToConnec);
		}
	}

	private static synchronized void initConnections() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = Catalog.conn.createStatement();

		rs = stmt.executeQuery(Connection.sqlSelectMaxOid());
		if (rs.next()) {
			Connection.LAST_OID(rs.getLong(1));
		}

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(Connection.sqlSelectAll());
		while (rs.next()) {
			long oid = rs.getLong(1);
			String name = rs.getString(2);
			String url = rs.getString(3);
			String user = rs.getString(4);
			String passwd = rs.getString(5);
			int store = rs.getInt(6);
			Connection conn = new Connection(oid, name, url, user, passwd,
					store);
			Catalog.addConnection(conn);
		}
	}

	private static synchronized void initSchemas() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = Catalog.conn.createStatement();

		rs = stmt.executeQuery(Schema.sqlSelectMaxOid());
		if (rs.next()) {
			Schema.LAST_OID(rs.getLong(1));
		}

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(Schema.sqlSelectAll());
		while (rs.next()) {
			long oid = rs.getLong(1);
			String name = rs.getString(2);
			Schema schema = new Schema(oid, name);
			Catalog.addSchema(schema);
		}
	}

	private static synchronized void initTables() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = Catalog.conn.createStatement();

		rs = stmt.executeQuery(Table.sqlSelectMaxOid());
		if (rs.next()) {
			Table.LAST_OID(rs.getLong(1));
		}

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(Table.sqlSelectAll());
		while (rs.next()) {
			long oid = rs.getLong(1);
			String name = rs.getString(2);
			long schemaOid = rs.getLong(3);
			long partCnt = rs.getLong(4);
			String partType = rs.getString(5);
			Long refTableOid = rs.getLong(6);
			

			Table table = new Table(oid, name, schemaOid, partCnt, partType, refTableOid);
			Catalog.addTable(table);
		}
	}

	private static synchronized void initFunctions() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		stmt = Catalog.conn.createStatement();

		rs = stmt.executeQuery(Table.sqlSelectMaxOid());
		if (rs.next()) {
			Table.LAST_OID(rs.getLong(1));
		}

		stmt = Catalog.conn.createStatement();
		rs = stmt.executeQuery(Function.sqlSelectAll());
		while (rs.next()) {
			long oid = rs.getLong(1);
			String name = rs.getString(2);
			long schemaOid = rs.getLong(3);
			int language = rs.getInt(4);
			String source = rs.getString(5);

			Function function = new Function(oid, name, schemaOid, language,
					source);
			Catalog.addFunction(function);
		}
	}

	public static synchronized Error createAttribute(Attribute att) {
		if (Catalog.attributes.containsKey(att.getOid())) {
			return createObjectAlreadyExistsErr(att);
		}

		Error lastError = Catalog.executeUpdate(att.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addAttribute(att);
		}

		return lastError;
	}

	public static synchronized Error dropAttribute(Attribute att) {
		Error lastError = Catalog.executeUpdate(att.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removeAttribute(att);
		}
		return lastError;
	}
	
	public static synchronized Error createPartitionAttributes(PartitionAttributes partAtts) {
		if (Catalog.partitionAttributues.containsKey(partAtts.getPart_att_oid())) {
			return createObjectAlreadyExistsErr(partAtts);
		}

		Error lastError = Catalog.executeUpdate(partAtts.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addPartitionAttributes(partAtts);
		}

		return lastError;
	}

	public static synchronized Error dropPartitionAttributes(PartitionAttributes partAtts) {
		Error lastError = Catalog.executeUpdate(partAtts.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removePartitionAttributes(partAtts);
		}
		return lastError;
	}

	public static synchronized Error createPartition(Partition part) {
		if (Catalog.partitions.containsKey(part.getOid())) {
			return createObjectAlreadyExistsErr(part);
		}

		Error lastError = Catalog.executeUpdate(part.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addPartition(part);
		}

		return lastError;
	}

	public static synchronized Error dropPartition(Partition part) {
		//first drop Partition to Connection
		ArrayList<PartitionToConnection> paToCosTmp = (ArrayList<PartitionToConnection>) Catalog.partitionToConnByPartOid
				.get(part.getOid());
		//replicated partition
		Error lastError = new Error();
		if(paToCosTmp != null){
			// copy to avoid errors
			@SuppressWarnings("unchecked")
			ArrayList<PartitionToConnection> paToCos = (ArrayList<PartitionToConnection>) paToCosTmp.clone();
			PartitionToConnection paToCo;
			
			for(int i = 0; i < paToCos.size(); i++){
				paToCo = paToCos.get(i);
				Catalog.dropPartitionToConnection(paToCo);
			}
			
			if (lastError.isError()){
				return lastError;
			}
		}
	
		lastError = Catalog.executeUpdate(part.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removePartition(part);
		}
		return lastError;
	}

	public static synchronized Error createConncection(Connection conn) {
		if (Catalog.connections.containsKey(conn.getOid())) {
			return createObjectAlreadyExistsErr(conn);
		}

		Error lastError = Catalog.executeUpdate(conn.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addConnection(conn);
		}
		return lastError;
	}

	public static synchronized Error dropConnection(Connection conn) {
		Error lastError = Catalog.executeUpdate(conn.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removeConnection(conn);
		}
		return lastError;
	}

	
	public static synchronized Error createPartitionToConnection(
			PartitionToConnection partToConn) {
		if (Catalog.partitionToConnByPartOid
				.containsKey(partToConn.getPartition_oid())
				&& Catalog.tableToConnByConnOid.containsKey(partToConn
						.getConnection_oid())) {
			return createObjectAlreadyExistsErr(partToConn);
		}

		Error lastError = Catalog.executeUpdate(partToConn.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addPartitionToConnection(partToConn);
		}
		return lastError;
	}
	
	public static synchronized Error dropPartitionToConnection(
			PartitionToConnection partToConn) {
		Error lastError = Catalog.executeUpdate(partToConn.sqlDelete());
		if (!lastError.isError() ) {
			Catalog.removePartitionToConnection(partToConn);
		}
		return lastError;
	}
	public static synchronized Error createTableToConnection(
			TableToConnection tableToConn) {
		if (Catalog.tableToConnByTableOid
				.containsKey(tableToConn.getTable_oid())) {
			
			if(Catalog.tableToConnByTableOid.get(tableToConn.getTable_oid()).contains(tableToConn))
				return createObjectAlreadyExistsErr("("+tableToConn.getConnection_oid()+", "+tableToConn.getTable_oid()+")", tableToConn.objectType);
		}

		Error lastError = Catalog.executeUpdate(tableToConn.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addTableToConnection(tableToConn);
		}
		return lastError;
	}

	public static synchronized Error dropTableToConnection(
			TableToConnection conn) {
		Error lastError = Catalog.executeUpdate(conn.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removeTableToConnection(conn);
		}
		return lastError;
	}

	public static synchronized Error createSchema(Schema schema) {
		if (Catalog.schemas.containsKey(schema.getOid())) {
			return createObjectAlreadyExistsErr(schema);
		}

		Error lastError = Catalog.executeUpdate(schema.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addSchema(schema);
		}
		return lastError;
	}

	public static synchronized Error dropSchema(Schema schema) {
		Error lastError = Catalog.executeUpdate(schema.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removeSchema(schema);
		}
		return lastError;
	}

	public static synchronized Error createTable(Table table) {
		if (Catalog.tables.containsKey(table.getOid())) {
			return createObjectAlreadyExistsErr(table);
		}

		Error lastError = Catalog.executeUpdate(table.sqlInsert());
		if (lastError == Error.NO_ERROR) {
			Catalog.addTable(table);
		}
		return lastError;
	}

	public static synchronized Error dropTable(Table table) {

		// drop Table to Connections entry
		Error lastError = Error.NO_ERROR;
		ArrayList<TableToConnection> taToCosTmp = (ArrayList<TableToConnection>) Catalog.tableToConnByTableOid
				.get(table.getOid());
	
		// check if replicated table
		if (taToCosTmp != null) {
			@SuppressWarnings("unchecked")
			ArrayList<TableToConnection> taToCos = (ArrayList<TableToConnection>) taToCosTmp
					.clone();
			TableToConnection tableToConnection;
			for (int i = 0; i < taToCos.size(); i++) {
				tableToConnection = taToCos.get(i);
				lastError = Catalog.dropTableToConnection(tableToConnection);
				if (lastError.isError())
					return lastError;
			}
		}
		
		// drop partitionAttributes
		for (PartitionAttributes partAtts: table.getPartitionAttributes().values()) {
			lastError = Catalog.dropPartitionAttributes(partAtts);
			if (lastError.isError())
				return lastError;

			Catalog.removePartitionAttributes(partAtts);
		}

		// drop attributes
		for (Attribute att : table.getAttributes()) {
			lastError = Catalog.dropAttribute(att);
			if (lastError.isError())
				return lastError;

			Catalog.removeAttribute(att);
		}

		// drop partitions
		for (Partition part : table.getPartitions()) {
			lastError = Catalog.dropPartition(part);
			if (lastError.isError())
				return lastError;
			Catalog.removePartition(part);
		}

		// drop table
		lastError = Catalog.executeUpdate(table.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removeTable(table);
		}
		return lastError;
	}

	public static synchronized Error createFunction(Function function) {
		if (Catalog.functions.containsKey(function.getOid())) {
			return createObjectAlreadyExistsErr(function);
		}

		Error lastError = Catalog.executeUpdate(function.sqlInsert());
		if (!lastError.isError()) {
			Catalog.addFunction(function);
		}
		return lastError;
	}

	public static synchronized Error dropFunction(Function function) {
		Error lastError = Catalog.executeUpdate(function.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removeFunction(function);
		}
		return lastError;
	}

	public static synchronized void addAttribute(Attribute att) {
		Catalog.attributes.put(att.getOid(), att);
		StringBuilder completeName = new StringBuilder();
		completeName.append(tables.get(att.getTableOid()).getName());
		completeName.append(".");
		completeName.append(att.getName());
		Catalog.attributesByName.put(completeName.toString(), att);
	}

	public static synchronized void removeAttribute(Attribute att) {
		Catalog.attributes.remove(att.getOid());
		StringBuilder completeName = new StringBuilder();
		completeName.append(tables.get(att.getTableOid()).getName());
		completeName.append(".");
		completeName.append(att.getName());
		Catalog.attributesByName.remove(completeName.toString());
	}
	
	public static synchronized void addPartitionAttributes(PartitionAttributes partAtts) {
		Catalog.partitionAttributues.put(partAtts.getPart_att_oid(), partAtts.getRef_att_oid());
	}
	
	public static synchronized void removePartitionAttributes(PartitionAttributes partAtts) {
		Catalog.partitions.remove(partAtts.getPart_att_oid());
	}	

	public static synchronized void addPartition(Partition part) {
		Catalog.partitions.put(part.getOid(), part);
	}

	public static synchronized void removePartition(Partition part) {
		Catalog.partitions.remove(part.getOid());
	}
	
	

	public static synchronized void addPartitionToConnection(
			PartitionToConnection paToCo) {
		List<PartitionToConnection> values1 = Catalog.partitionToConnByConnOid
				.get(paToCo.getConnection_oid());
		if (values1 == null) {
			values1 = new ArrayList<PartitionToConnection>();
		}
		values1.add(paToCo);
		Catalog.partitionToConnByConnOid.put(paToCo.getConnection_oid(), values1);

		List<PartitionToConnection> values2 = Catalog.partitionToConnByPartOid
				.get(paToCo.getPartition_oid());
		if (values2 == null) {
			values2 = new ArrayList<PartitionToConnection>();
		}
		values2.add(paToCo);
		Catalog.partitionToConnByPartOid.put(paToCo.getPartition_oid(), values2);
	}

	
	public static synchronized void removePartitionToConnection(
			PartitionToConnection paToCo) {
		List<PartitionToConnection> values1 = Catalog.partitionToConnByConnOid
				.get(paToCo.getConnection_oid());
		values1.remove(paToCo);
		if (values1 != null) {
			if (values1.size() == 0) {
				Catalog.partitionToConnByConnOid.remove(paToCo.getConnection_oid());
			} else {
				Catalog.partitionToConnByConnOid.put(paToCo.getConnection_oid(),
						values1);
			}
		}
		List<PartitionToConnection> values2 = Catalog.partitionToConnByPartOid
				.get(paToCo.getPartition_oid());
		if (values2 != null) {
			values2.remove(paToCo);
			if (values1.size() == 0) {
				Catalog.partitionToConnByPartOid.remove(paToCo.getPartition_oid());
			} else {
				Catalog.partitionToConnByPartOid.put(paToCo.getPartition_oid(),
						values2);
			}
		}

	}

	public static synchronized void addTableToConnection(
			TableToConnection taToCo) {
		List<TableToConnection> values1 = Catalog.tableToConnByConnOid
				.get(taToCo.getConnection_oid());
		if (values1 == null) {
			values1 = new ArrayList<TableToConnection>();
		}
		values1.add(taToCo);
		Catalog.tableToConnByConnOid.put(taToCo.getConnection_oid(), values1);

		List<TableToConnection> values2 = Catalog.tableToConnByTableOid
				.get(taToCo.getTable_oid());
		if (values2 == null) {
			values2 = new ArrayList<TableToConnection>();
		}
		values2.add(taToCo);
		Catalog.tableToConnByTableOid.put(taToCo.getTable_oid(), values2);
	}

	public static synchronized void removeTableToConnection(
			TableToConnection taToCo) {
		List<TableToConnection> values1 = Catalog.tableToConnByConnOid
				.get(taToCo.getConnection_oid());
		values1.remove(taToCo);
		if (values1 != null) {
			if (values1.size() == 0) {
				Catalog.tableToConnByConnOid.remove(taToCo.getConnection_oid());
			} else {
				Catalog.tableToConnByConnOid.put(taToCo.getConnection_oid(),
						values1);
			}
		}
		List<TableToConnection> values2 = Catalog.tableToConnByTableOid
				.get(taToCo.getTable_oid());
		if (values2 != null) {
			values2.remove(taToCo);
			if (values1.size() == 0) {
				Catalog.tableToConnByTableOid.remove(taToCo.getTable_oid());
			} else {
				Catalog.tableToConnByTableOid.put(taToCo.getTable_oid(),
						values2);
			}
		}

	}

	public static synchronized void addConnection(Connection conn) {
		Catalog.connections.put(conn.getOid(), conn);
		Catalog.connectionsByName.put(conn.hashKey(), conn);
	}

	public static synchronized void removeConnection(Connection conn) {
		Catalog.connections.remove(conn.getOid());
		Catalog.connectionsByName.remove(conn.hashKey());
	}

	public static synchronized void addSchema(Schema schema) {
		Catalog.schemas.put(schema.getOid(), schema);
		Catalog.schemasByName.put(schema.hashKey(), schema);
	}

	public static synchronized void removeSchema(Schema schema) {
		Catalog.schemas.remove(schema.getOid());
		Catalog.schemasByName.remove(schema.hashKey());
	}

	public static synchronized void addTable(Table table) {
		Catalog.tables.put(table.getOid(), table);
		Catalog.tablesByName.put(table.hashKey(), table);
	}

	public static synchronized void removeTable(Table table) {
		Catalog.tables.remove(table.getOid());
		Catalog.tablesByName.remove(table.hashKey());
	}

	public static synchronized void addFunction(Function function) {
		Catalog.functions.put(function.getOid(), function);
		Catalog.functionsByName.put(function.hashKey(), function);
	}

	public static synchronized void removeFunction(Function function) {
		Catalog.tables.remove(function.getOid());
		Catalog.tablesByName.remove(function.hashKey());
	}

	public static synchronized Attribute getAttribute(long oid) {
		return Catalog.attributes.get(oid);
	}
	
	public static synchronized Attribute getAttribute(String tableName, String attName){
		StringBuilder completeName = new StringBuilder();
		completeName.append(tableName);
		completeName.append(".");
		completeName.append(attName);
		return Catalog.attributesByName.get(completeName.toString());
		
	}

	public static synchronized Partition getPartition(long oid) {
		return Catalog.partitions.get(oid);
	}

	public static synchronized Connection getConnection(long oid) {
		return Catalog.connections.get(oid);
	}

	public static synchronized Connection getConnection(String key) {
		return Catalog.connectionsByName.get(key);
	}

	public static synchronized Schema getSchema(long oid) {
		return Catalog.schemas.get(oid);
	}

	public static synchronized Schema getSchema(String key) {
		return Catalog.schemasByName.get(key);
	}

	public static synchronized Table getTable(long oid) {
		return Catalog.tables.get(oid);
	}

	public static synchronized Table getTable(String tableName) {
		return Catalog.tablesByName.get(tableName);
	}

	public static synchronized Function getFunction(long oid) {
		return Catalog.functions.get(oid);
	}

	public static synchronized Function getFunction(String key) {
		return Catalog.functionsByName.get(key);
	}

	public synchronized static List<Connection> getConnectionsForTable(String tablename) {
		Table table = Catalog.tablesByName.get(tablename);

		List<TableToConnection> taToCo = tableToConnByTableOid.get(table
				.getOid());

		List<Connection> connections = new ArrayList<Connection>();
		for (TableToConnection ttc : taToCo) {
			connections.add(Catalog.connections.get(ttc.getConnection_oid()));
		}
		return connections;
	}
	
	public synchronized static Collection<Partition> getPartionsForTable(String tableName){
		Table table = Catalog.tablesByName.get(tableName);
		return  table.getPartitions();
	}
	
	/**
	 * This Function need to be splitted into CS, MT, QT but is now implemented here to use the
	 * functionality as soon as possible
	 * @return 
	 */
	public static Error executeInfileStmt(String path, String tblName){
		StringBuffer insertSql = new StringBuffer();
		insertSql.append(AbstractToken.LOAD); 
		insertSql.append(AbstractToken.BLANK); 
		insertSql.append(AbstractToken.DATA);
		insertSql.append(AbstractToken.BLANK); 
		insertSql.append(AbstractToken.INFILE);
		insertSql.append(AbstractToken.BLANK); 

		insertSql.append(AbstractToken.QUOTE);
		insertSql.append(path);
		insertSql.append(AbstractToken.QUOTE);
		insertSql.append(AbstractToken.BLANK);
		
		insertSql.append(AbstractToken.INTO); 
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.TABLE);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(tblName);
		insertSql.append(AbstractToken.BLANK);
		
		insertSql.append(AbstractToken.FIELDS);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.TERMINATED);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.BY);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.QUOTE);
		insertSql.append(AbstractToken.PIPE);
		insertSql.append(AbstractToken.QUOTE);
		insertSql.append(AbstractToken.BLANK);
		
		insertSql.append(AbstractToken.LINES);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.TERMINATED);
		insertSql.append(AbstractToken.BLANK);
		insertSql.append(AbstractToken.BY);
		insertSql.append(AbstractToken.BLANK);

		insertSql.append(AbstractToken.QUOTE);
		insertSql.append(AbstractToken.NEWLINE);
		insertSql.append(AbstractToken.QUOTE);
		insertSql.append(AbstractToken.SEMI);

		String sqlInfile = insertSql.toString();
		System.out.println(sqlInfile);
		
		Error err = executeUpdate(sqlInfile);
		return err;
	}
}