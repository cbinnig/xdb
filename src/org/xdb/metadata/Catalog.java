package org.xdb.metadata;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xdb.Config;
import org.xdb.error.EnumError;
import org.xdb.error.Error;
import org.xdb.logging.XDBLog;

public class Catalog {
	private static java.sql.Connection conn = null;

	private static HashMap<Long, Attribute> attributes = new HashMap<Long, Attribute>();

	private static HashMap<Long, Connection> connections = new HashMap<Long, Connection>();
	private static HashMap<String, Connection> connectionsByName = new HashMap<String, Connection>();

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
			Catalog.conn = DriverManager.getConnection(
					Config.METADATA_DB_URL, Config.METADATA_USER,
					Config.METADATA_PASSWORD);

			Error lastError = Error.NO_ERROR;

			lastError = Catalog.executeUpdate(Attribute.sqlDeleteAll());
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
		} catch (Exception e) {
			return createCatalogNotAvailableErr(e);
		}

		return Error.NO_ERROR;
	}

	public static synchronized Error load() {
		Error lastError;

		try {
			Class.forName(Config.METADATA_DRIVER_CLASS);
			Catalog.conn = DriverManager.getConnection(
					Config.METADATA_DB_URL, Config.METADATA_USER,
					Config.METADATA_PASSWORD);

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
		Catalog.connections.clear();
		Catalog.connectionsByName.clear();
		Catalog.schemas.clear();
		Catalog.schemasByName.clear();
		Catalog.tables.clear();
		Catalog.tablesByName.clear();
		Catalog.functions.clear();
		Catalog.functionsByName.clear();

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
		Error lastError = new Error(
				EnumError.CREATE_TABLE_DUPLICATE_ATTRIBUTES, args);
		return lastError;
	}

	private static synchronized Error checkCatalog() {
		if (Catalog.getSchema(Config.COMPILE_DEFAULT_SCHEMA) == null) {
			return Catalog.createObjectNotExistsErr(Config.COMPILE_DEFAULT_SCHEMA,
					EnumDatabaseObject.SCHEMA);
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
			String sourceName = rs.getString(3);
			String sourceSchema = rs.getString(4);
			long schemaOid = rs.getLong(5);
			long connectionOid = rs.getLong(6);

			Table table = new Table(oid, name, sourceName, sourceSchema,
					schemaOid, connectionOid);
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
			
			Function function = new Function(oid, name, schemaOid, language, source);
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

	public static synchronized Error dropConncection(Connection conn) {
		Error lastError = Catalog.executeUpdate(conn.sqlDelete());
		if (lastError == Error.NO_ERROR) {
			Catalog.removeConnection(conn);
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
		// drop attributes
		Error lastError = Error.NO_ERROR;
		for (Attribute att : table.getAttributes()) {
			lastError = Catalog.dropAttribute(att);
			if (lastError != Error.NO_ERROR)
				return lastError;

			Catalog.removeAttribute(att);
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
		if (lastError == Error.NO_ERROR) {
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
	}

	public static synchronized void removeAttribute(Attribute att) {
		Catalog.attributes.remove(att.getOid());
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

	public static synchronized Table getTable(String key) {
		return Catalog.tablesByName.get(key);
	}
	
	public static synchronized Function getFunction(long oid) {
		return Catalog.functions.get(oid);
	}

	public static synchronized Function getFunction(String key) {
		return Catalog.functionsByName.get(key);
	}
}