package org.xdb.execute.operators.udf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.xdb.error.Error;
import org.xdb.execute.operators.AbstractExecuteOperator;
import org.xdb.utils.Identifier;

/**
 * UDF implementation of Levenshtein (ExecOp)
 * @author cbinnig
 *
 */
public class LevenshteinExecOp extends AbstractExecuteOperator {

	private static final long serialVersionUID = -7108326963144707383L;
	private String sqlSelectIn;
	private String outTableName;

	// constructors
	public LevenshteinExecOp(Identifier execOpId) {
		super(execOpId);
	}

	// getters and setters
	public void setSqlSelectIn(String sqlSelectIn) {
		this.sqlSelectIn = sqlSelectIn;
	}

	public void setOutTableName(String outTableName) {
		this.outTableName = outTableName;
	}

	// methods
	@Override
	protected Error openOperator() {
		return err;
	}

	@Override
	protected Error executeOperator() {
		// call getLevenshteinDistance(...) for all result rows
		try {
			Statement stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery(this.sqlSelectIn);

			while (rs.next()) {
				String p1_key = rs.getString(1);
				String p2_key = rs.getString(2);
				String p1_type = rs.getString(3);
				String p2_type = rs.getString(4);
				String frequency = rs.getString(5);

				double lsd = levenshteinDistance(p1_type, p2_type);
				if (lsd >= 0.3) {
					Statement updateStmt = this.conn.createStatement();
					String sqlInsert = "INSERT INTO " + this.outTableName
							+ " VALUES ( " + p1_key + ", " + p2_key + ", '"
							+ p1_type + "', '" + p2_type + "', " + frequency + ")";
					updateStmt.executeUpdate(sqlInsert);
				}
			}
		} catch (SQLException e) {
			err = createMySQLError(e);
		}

		return err;
	}

	@Override
	protected Error closeOperator() {
		return err;
	}

	/**
	 * Calculate Levenshtein distance
	 * @param string1
	 * @param string2
	 * @return
	 */
	public static double levenshteinDistance(String string1, String string2) {
		final int length1 = string1.length();
		final int length2 = string2.length();

		int[][] distance = new int[length1 + 1][length2 + 1];

		for (int i = 0; i <= length1; i++) {
			distance[i][0] = i;
		}
		for (int j = 0; j <= length2; j++) {
			distance[0][j] = j;
		}

		for (int i = 1; i <= length1; i++) {
			for (int j = 1; j <= length2; j++) {
				final char char1 = string1.charAt(i - 1);
				final char char2 = string2.charAt(j - 1);
				final int offset = (char1 == char2) ? 0 : 1;
				distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1,
						distance[i][j - 1] + 1), distance[i - 1][j - 1]
						+ offset);
			}
		}
		return 1-((double)distance[length1][length2]) / Math.max(length1, length2);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(super.toString());
		
		return builder.toString();
	}
}