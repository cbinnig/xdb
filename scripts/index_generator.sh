#!/bin/bash

prefix_db_name="tpch_sf0_5_part64_";
db_cnt=64;
mysql_addr="/usr/local/mysql/bin/mysql";
db_user="xroot"; 
db_pass="xroot";

# Check if the "temp" directory exists
if [ ! -d "temp" ]; then
  mkdir -p temp;
fi

output_file="./temp/index_generator_runnable.sql";

# Remove the old index_generator_runnabel.sql if it exists
$(rm -f $output_file);

for ((i=1; i<=$db_cnt; i++))do
	echo "USE $prefix_db_name$i;" >> $output_file;
	echo "CREATE INDEX l_orderkey_index on lineitem (l_orderkey);" >> $output_file;
	echo "CREATE INDEX l_suppkey_index on lineitem (l_suppkey);" >> $output_file;
	echo "CREATE INDEX o_orderkey_index on orders (o_orderkey);" >> $output_file;
	echo "CREATE INDEX o_orderdate_index on orders (o_orderdate);" >> $output_file;
	echo "CREATE INDEX s_nationkey_index on supplier (s_nationkey);" >> $output_file;
	echo "CREATE INDEX s_suppkey_index on supplier (s_suppkey);" >> $output_file;
	echo "CREATE INDEX c_nationkey_index on customer (c_nationkey);" >> $output_file;
done

echo "$output_file created.";
$($mysql_addr -u$db_user -p$db_pass < $output_file);
echo "indices successully built";
