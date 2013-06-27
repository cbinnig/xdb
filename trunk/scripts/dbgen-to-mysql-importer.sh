#!/bin/bash

#if [ -z "$1" ]
#  then
#    echo "No argument supplied"
#fi

is_partitioned=true; 
part_cnt=64;	# if no partitioning, must be set to 1
tables_dest="/media/ephemeral0/original-data/";	# IMPORTANT: with the final slash
mysql_addr="mysql";	
db_name_prefix="tpch_sf64_part64_";
db_user="root";
db_pass="root";
declare -a table_names=('customer' 'supplier' 'orders' 'lineitem' 'partsupp' 'part' 'nation' 'region');
declare -a does_table_exist=(true true true true false false true true);

# Check if the "temp" directory exists
if [ ! -d "temp" ]; then
  mkdir -p temp;
fi

# Remove the old command_file.sql if it exists
$(rm -f temp/SQLcommands_*)

if $is_partitioned; then
	# We will deal with tables nation and region later on.
	for ((i=0; i<6; i++)); do 
		if ! ${does_table_exist[$i]}; then
			continue;
		fi
		# Now we go through the partitions of the table and create the import sql command for it 
		for ((p=0; p<$part_cnt; p++))do
			input_file="$tables_dest${table_names[$i]}";
			rest="_p$p.tbl";
			partition_dest="$input_file$rest";
			db_name_suffix=`expr $p + 1`;
			db_name="$db_name_prefix$db_name_suffix";
			table_complete_name="$db_name.${table_names[$i]}";
			
			output_file="temp/SQLcommands_$db_name_suffix.sql";
			echo "LOAD DATA INFILE \"$partition_dest\" INTO TABLE $table_complete_name FIELDS TERMINATED BY '|' ;" >> $output_file;			
		done
	done
else 
	# Tables are not partitioned.
	
	# We will deal with tables nation and region later on.
	for ((i=0; i<6; i++)); do 
		if ! ${does_table_exist[$i]}; then
			continue;
		fi
		input_file="$tables_dest${table_names[$i]}.tbl";
		db_name_suffix="1";
		db_name="$db_name_prefix$db_name_suffix";
		table_complete_name="$db_name.${table_names[$i]}";
		
		output_file="temp/SQLcommands_$db_name_suffix.sql";
		echo "LOAD DATA INFILE \"$input_file\" INTO TABLE $table_complete_name FIELDS TERMINATED BY '|' ;" >> $output_file;			
	done
fi


# Now, lets add the two static-sized tables: region and nation
input_file_nation="$tables_dest${table_names[6]}.tbl";
input_file_region="$tables_dest${table_names[7]}.tbl";

for ((p=0; p<$part_cnt; p++))do
	db_name_suffix=`expr $p + 1`;
	db_name="$db_name_prefix$db_name_suffix";
	table_nat_complete_name="$db_name.${table_names[6]}";
	table_reg_complete_name="$db_name.${table_names[7]}";
	
	output_file="temp/SQLcommands_$db_name_suffix.sql";	
	echo "LOAD DATA INFILE \"$input_file_nation\" INTO TABLE $table_nat_complete_name FIELDS TERMINATED BY '|' ;" >> $output_file;			
	echo "LOAD DATA INFILE \"$input_file_region\" INTO TABLE $table_reg_complete_name FIELDS TERMINATED BY '|' ;" >> $output_file;			
done

echo "The SQL files are now created. Now, I will run them, you go get a coffee ...";
for ((p=0; p<$part_cnt; p++))do
	db_name_suffix=`expr $p + 1`;
	db_name="$db_name_prefix$db_name_suffix";
	SQL_file="temp/SQLcommands_$db_name_suffix.sql";
	echo "($mysql_addr -u$db_user -p$db_pass -D $db_name < $SQL_file);";
	
	echo "Database $db_name is successfully loaded.";
done
echo "Importing data is successfully finished. Take care!";
