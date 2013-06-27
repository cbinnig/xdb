#!/bin/bash

###################################################################
#			Description
# This script builds indexes on every column of Query 5 of TPC-H. 
#
# Note: This script should be called ONLY AFTER index-generator.sh,
# since it uses the latter script's output fies,
# i.e. index_generator_runnable_[#].sql 
###################################################################

mysql_addr="mysql";
db_url="localhost";
db_user="root";
db_pass="root";
file_prefix="index_generator_runnable_";
hosts=("ec2-54-226-111-202.compute-1.amazonaws.com"
	"ec2-67-202-58-39.compute-1.amazonaws.com" 
	"ec2-107-20-117-71.compute-1.amazonaws.com" 
	"ec2-54-227-12-182.compute-1.amazonaws.com"
	"ec2-54-235-35-179.compute-1.amazonaws.com"
	"ec2-184-73-77-3.compute-1.amazonaws.com"
	"ec2-184-73-32-0.compute-1.amazonaws.com"
	"ec2-54-242-34-9.compute-1.amazonaws.com");

for ((i=0; i<${#hosts[@]}; i++)) do
#for ((i=1; i<1; i++)) do
	echo "Creating the indexes on the database on host: ${hosts[$i]}";
	filename="$file_prefix$i.sql";
	$(ssh -t -i ../../../xdb_keypair.pem ec2-user@${hosts[$i]} 'mkdir -p ~/temp');
	$(scp -i ../../../xdb_keypair.pem ../temp/$filename ec2-user@${hosts[$i]}:~/temp/);
	mysql_command="mysql -u$db_user -p$db_pass < ~/temp/$filename";
	ssh_command="ssh -t -i ../../../xdb_keypair.pem ec2-user@${hosts[$i]} '$mysql_command'";
	#eval $ssh_command;
	#echo "$ssh_command";
done;

