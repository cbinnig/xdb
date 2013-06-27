#!/bin/bash

###################################################################
#			Description
# This script dispatches the SQLcommands files to corresponding nodes.
# This script DOES NOT import the dbgen data to remote databases, 
# because that would be very slow. But now, it only sends the files 
# to each node, and the node is responsible to import its own data.
#
# Note: This script should be called ONLY AFTER dbgen-to-mysql-importer.sh,
# and after that, each node should run "import-partitions-locally.sh"
# to actually import the data
###################################################################

mysql_addr="mysql";
db_url="localhost";
db_user="root";
db_pass="root";
partitions_cnt_on_each_machine=8;
file="schema_generator_runnable.sql";

hosts=("ec2-54-226-111-202.compute-1.amazonaws.com"
	"ec2-67-202-58-39.compute-1.amazonaws.com" 
	"ec2-107-20-117-71.compute-1.amazonaws.com" 
	"ec2-54-227-12-182.compute-1.amazonaws.com"
	"ec2-54-235-35-179.compute-1.amazonaws.com"
	"ec2-184-73-77-3.compute-1.amazonaws.com"
	"ec2-184-73-32-0.compute-1.amazonaws.com"
	"ec2-54-242-34-9.compute-1.amazonaws.com");

# We want to apply SQLcommand_1 to SQLcomands_8 to the first machine, SQLcommands_9 to SQLcommands_16 to the second machine, and so one.
for ((i=0; i<${#hosts[@]}; i++)) do
	echo "Importing tables to the database on host: ${hosts[$i]}";
	for ((j=0;j<$partitions_cnt_on_each_machine; j++)) do
		date;
		# Let's calculate the SQLcommand filename to be imported to this machine
		number=$(($i * $partitions_cnt_on_each_machine + $j + 1));
		filename="SQLcommands_$number.sql";
		$(ssh -t -i ../../../xdb_keypair.pem ec2-user@${hosts[$i]} 'mkdir -p ~/temp');
	        $(scp -i ../../../xdb_keypair.pem ../temp/$filename ec2-user@${hosts[$i]}:~/temp/);
        
       		mysql_command="mysql -u$db_user -p$db_pass < ~/temp/$filename";
        	ssh_command="ssh -t -i ../../../xdb_keypair.pem ec2-user@${hosts[$i]} '$mysql_command'";
        	#eval $ssh_command;
		echo "Partition $j created on Host $i";
	done
done;
