package org.xdb.funsql.compile;

import org.xdb.client.statement.ClientStmt;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.QueryStats;
import org.xdb.error.Error;
import org.xdb.funsql.statement.AbstractServerStmt;
import org.xdb.utils.Tuple;

/**
 * 
 * @author cbinnig
 *
 */
public class CompileServerNode {

	/**
	 * 
	 * @param clientStmt
	 * @return
	 */
	public Error compileAndExecuteStmt(final ClientStmt clientStmt) {
		Error err = new Error();
		final FunSQLCompiler compiler = new FunSQLCompiler();
		final AbstractServerStmt serverStmt = compiler.compile(clientStmt
				.getStmt());
		err = compiler.getLastError();
		if (err.isError()) {
			return err;
		}

		err = serverStmt.execute();

		return err;
	}

	/**
	 * 
	 * @param clientStmt
	 * @param queryStats
	 * @return
	 */
	public Tuple<Error, DoomDBPlan> doomDBCompileStmt(
			final ClientStmt clientStmt, final QueryStats queryStats) {
		Error err = new Error();
		final FunSQLCompiler compiler = new FunSQLCompiler(queryStats);
		final AbstractServerStmt serverStmt = compiler.compile(clientStmt
				.getStmt());
		err = compiler.getLastError();
		if (err.isError()) {
			return new Tuple<Error, DoomDBPlan>(err, new DoomDBPlan());
		}

		Tuple<Error, DoomDBPlan> result = serverStmt.generateDoomDBQPlan();
		err = result.getObject1();
		if (err.isError()) {
			return new Tuple<Error, DoomDBPlan>(err, new DoomDBPlan());
		}

		DoomDBPlan dplan = result.getObject2();

		return new Tuple<Error, DoomDBPlan>(err, dplan);
	}
}
