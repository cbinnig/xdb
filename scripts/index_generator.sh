#!/bin/bash

prefix_db_name="tpch_sf64_part64_";
db_cnt=64;
machine_cnt=8;
mysql_addr="mysql";
db_user="root"; 
db_pass="root";

# Check if the "temp" directory exists
if [ ! -d "temp" ]; then
  mkdir -p temp;
fi

output_file_prefix="./temp/index_generator_runnable_";

# Remove the old index_generator_runnabel.sql if it exists
$(rm -f $output_file_prefix*);

for ((i=0; i<$db_cnt; i++))do
	filenumber=$(($i / $machine_cnt));
	output_file="$output_file_prefix$filenumber.sql";
	db_number=$(($i + 1));
	echo "USE $prefix_db_name$db_number;" >> $output_file;
	echo "CREATE INDEX l_orderkey_index on lineitem (l_orderkey);" >> $output_file;
	echo "CREATE INDEX l_suppkey_index on lineitem (l_suppkey);" >> $output_file;
	echo "CREATE INDEX o_orderkey_index on orders (o_orderkey);" >> $output_file;
	echo "CREATE INDEX o_orderdate_index on orders (o_orderdate);" >> $output_file;
	echo "CREATE INDEX s_nationkey_index on supplier (s_nationkey);" >> $output_file;
	echo "CREATE INDEX s_suppkey_index on supplier (s_suppkey);" >> $output_file;
	echo "CREATE INDEX c_nationkey_index on customer (c_nationkey);" >> $output_file;
done

echo "output_files are created.";
#$($mysql_addr -u$db_user -p$db_pass < $output_file);
echo "indices successully built";
