package org.xdb.test.doomdb;

import org.xdb.client.DoomDBClient;
import org.xdb.doomdb.DoomDBPlan;
import org.xdb.doomdb.DoomDBSchema;
import org.xdb.test.DistributedXDBTestCase;
import org.xdb.utils.Identifier;

/**
 * Test case with partitioned TPC-H schema
 * @author cbinnig
 *
 */
public class TestDoomDB extends DistributedXDBTestCase {
	
	public TestDoomDB() {
		super(2);
	}

	private DoomDBClient dClient = new DoomDBClient();

	public void testWith2Levels() throws Exception{
		this.dClient.setSchema(DoomDBSchema.TPCH_2PARTS);
		
		String q  = "Select n_nationkey, " +
					"sum(l_extendedprice * (1-l_discount)) as revenue, " +
					"avg(l_extendedprice * (1-l_discount)) as avgrevenue " +
					"from customer, orders, lineitem, supplier, nation, region " +
					"where c_custkey = o_custkey " +
					"and l_orderkey = o_orderkey " +
					"and l_suppkey = s_suppkey  " +
					"and n_nationkey = s_nationkey " +
					"and r_regionkey = n_regionkey " +
					"and s_nationkey = c_nationkey " +
					"and r_name = 'ASIA' " +
					"and o_orderdate > date '1994-01-01' "+
					"and o_orderdate < date '1995-01-01' "+
					"group by n_nationkey;";
		
		this.dClient.setQuery(q);
		DoomDBPlan dplan = this.dClient.getPlan();
		dplan.tracePlan();
		
		System.out.println("--------------------");
		System.out.println("Query Deployment: ");
		for(Identifier opId: dplan.getNodes()){
			System.out.println("\t"+opId+":"+dplan.getComputeNode(opId));
		}
		System.out.println("--------------------");
		System.out.println("");
		
		System.out.println("--------------------");
		System.out.println("Query Execution: ");
		System.out.print("\tRunning ");
		this.dClient.startQuery();
		while(!this.dClient.isQueryFinished()){
			System.out.print(".");
			Thread.sleep(500);
		}
		System.out.println(" Finished!");
		System.out.println("--------------------");
	}
}
