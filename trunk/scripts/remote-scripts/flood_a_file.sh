#!/bin/bash

###########################################
#	Description
# This script sends the $file to all hosts
# specified in $hosts
###########################################

file="import_partitions_locally.sh";	# filename to be sent
from="./$file";				# where the file is located on local machine
to="~/temp/";				# where to be sit in remote machine
keypair_file="../../../xdb_keypair.pem";

hosts=("ec2-54-226-111-202.compute-1.amazonaws.com"
	"ec2-67-202-58-39.compute-1.amazonaws.com" 
	"ec2-107-20-117-71.compute-1.amazonaws.com" 
	"ec2-54-227-12-182.compute-1.amazonaws.com"
	"ec2-54-235-35-179.compute-1.amazonaws.com"
	"ec2-184-73-77-3.compute-1.amazonaws.com"
	"ec2-184-73-32-0.compute-1.amazonaws.com"
	"ec2-54-242-34-9.compute-1.amazonaws.com");

for ((i=0; i<${#hosts[@]}; i++)) do
	$(scp -i $keypair_file $from ec2-user@${hosts[$i]}:$to);
done
