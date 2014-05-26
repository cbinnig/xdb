#!/bin/bash

xdb=".";
sf=10;
partitions=10;
maxIteration=1;
declare -a queries=(5);

queryIterator=0;

while [[ $queryIterator -lt ${#queries[*]} ]]; do
	query=${queries[$queryIterator]};
	iteration=1;
	schema="TPCH_SF";
	schema+="$sf";
	schema+="_";
	schema+="$partitions";
	schema+="P";
	echo "Schema: $schema";
	echo "Execution Query: $query";
	while [[ $iteration -le $maxIteration ]]; do
	
		echo "***** Iteration: $iteration *****";
		a="java -cp $xdb/bin/:$xdb/lib/mysql-connector-java-5.1.20-bin.jar:$xdb/lib/antlr-3.3-complete.jar:$xdb/lib/oy-lm-1.4.jar:$xdb/lib/commons-math3-3.2.jar:$xdb/lib/xdb.jar org.xdb.test.costmodel.Experiment1 $schema $query";
		eval $a;
		((iteration++));

	done
	((queryIterator++));
done
