#!/bin/bash

###################################################################
#			Description
# This script creates the TPC-H database schema on remote machines
# specified in $host variable.
#
# Note: This script should be run ONLY AFTER schema-generator.sh,
# since it uses the latter script's output fies,
# i.e. schema_generator_runnable_[#].sql 
###################################################################

mysql_addr="mysql";		# MySQL address
db_url="localhost";		
db_root_user="root";
db_root_pass="root";
file_prefix="schema_generator_runnable_";	# The prefix
hosts=("ec2-54-226-111-202.compute-1.amazonaws.com"
	"ec2-67-202-58-39.compute-1.amazonaws.com" 
	"ec2-107-20-117-71.compute-1.amazonaws.com" 
	"ec2-54-227-12-182.compute-1.amazonaws.com"
	"ec2-54-235-35-179.compute-1.amazonaws.com"
	"ec2-184-73-77-3.compute-1.amazonaws.com"
	"ec2-184-73-32-0.compute-1.amazonaws.com"
	"ec2-54-242-34-9.compute-1.amazonaws.com");


for ((i=0; i<${#hosts[@]}; i++)) do
	echo "Creating the database on host: ${hosts[$i]}";
	filename="$file_prefix$i.sql";
	$(ssh -t -i ../../../xdb_keypair.pem ec2-user@${hosts[$i]} 'mkdir -p ~/temp');
	$(scp -i ../../../xdb_keypair.pem ../temp/$filename ec2-user@${hosts[$i]}:~/temp/);
	mysql_command="$mysql_addr -u$db_root_user -p$db_root_pass < ~/temp/$filename";
	ssh_command="ssh -t -i ../../../xdb_keypair.pem ec2-user@${hosts[$i]} '$mysql_command'";
	eval $ssh_command;
done;
