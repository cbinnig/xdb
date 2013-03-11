package org.xdb.tools.refpart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xdb.tools.refpart.partitioners.CustomerFilePartitioner;
import org.xdb.tools.refpart.partitioners.LineItemFilePartitioner;
import org.xdb.tools.refpart.partitioners.NationFilePartitioner;
import org.xdb.tools.refpart.partitioners.OrderFilePartitioner;
import org.xdb.tools.refpart.partitioners.PartFilePartitioner;
import org.xdb.tools.refpart.partitioners.PartSuppFilePartitioner;
import org.xdb.tools.refpart.partitioners.RegionFilePartitioner;
import org.xdb.tools.refpart.partitioners.SupplierFilePartitioner;

/**
 * This tool partitions a set of TPC-H TBL input files into a number of
 * partitioned output files that can be imported into a MySQL database. The data
 * will be referenced partitioned across multiple tables based on the TPC-H data
 * model.
 * 
 * Use the /sql/importRefPart.sh <numberOfPartitions> script in the root dir of
 * the created files to import the created SQL files into separate databases on
 * the same host (xdb_tpch_0, xdb_tpch_1, ... xdb_tpch_n)
 * 
 * **** IMPORTANT ***** Please provide the partition count, input, output and
 * temp directory as start up parameters Example: ReferencePartitioner 10
 * /my/input/dir /my/output/dir /my/temp/dir **** IMPORTANT *****
 * 
 * @author sven
 * 
 */
public class ReferencePartitioner {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length < 4) {
			System.out.println("Please provide the partition count, input, output and temp directory as parameters!");
			System.out.println("Example: ReferencePartitioner 10 /my/input/dir /my/output/dir /my/temp/dir");
			System.exit(0);
		}
		ReferencePartitioner rp = new ReferencePartitioner(Integer.valueOf(args[0]).intValue(), args[1], args[2],
				args[3]);
		rp.execute();
	}
	private String inputDir;
	private String outputDir;
	private int partitionCount;
	private List<AbstractFilePartitioner> partitionerList = new ArrayList<AbstractFilePartitioner>();

	private String tempDir;

	public ReferencePartitioner(int partitionCount, String inputDir, String outputDir, String tempDir) {
		super();
		this.partitionCount = partitionCount;
		this.inputDir = inputDir;
		this.outputDir = outputDir;
		this.tempDir = tempDir;
	}

	public void execute() throws IOException {
		partitionerList.add(new CustomerFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "customer"));
		partitionerList.add(new OrderFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "orders"));
		partitionerList.add(new LineItemFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "lineitem"));
		partitionerList.add(new PartFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "part"));
		partitionerList.add(new SupplierFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "supplier"));
		partitionerList.add(new PartSuppFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "partsupp"));
		partitionerList.add(new RegionFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "region"));
		partitionerList.add(new NationFilePartitioner(partitionCount, inputDir, outputDir, tempDir, "nation"));

		for (AbstractFilePartitioner partitioner : partitionerList) {
			partitioner.execute();
		}
	}

}
