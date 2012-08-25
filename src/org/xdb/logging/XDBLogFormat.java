package org.xdb.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class XDBLogFormat extends Formatter {

	public XDBLogFormat() {
		super();
	}

	public String format(LogRecord record) {

		StringBuffer sb = new StringBuffer();

		Date date = new Date(record.getMillis());
		sb.append("(");
		sb.append(date.toString());
		sb.append(",");

		sb.append(record.getLevel().getName());
		sb.append(")");

		sb.append(" Message: ");
		
		sb.append(this.formatMessage(record));
		sb.append(" ");
		
		sb.append("( Class: ");
		sb.append(record.getSourceClassName());
		sb.append(", Method:");
		
		sb.append(record.getSourceMethodName());
		sb.append(")");
		
		sb.append("\n");

		return sb.toString();
	}
}
