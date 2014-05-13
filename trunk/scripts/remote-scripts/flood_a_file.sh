#!/bin/bash

###########################################
#	Description
# This script sends the $file to all hosts
# specified in allNodes.txt
###########################################

if [ "$#" -ne 2 ]; then
  echo "Usage: $0 FILE_TO_BE_SENT DESTINATION_FOLDER" >&2
  echo "2 Arguments are needed." >&2
  exit 1
fi

file=$1;			# filename to be sent
to=$2;				# where to be sit in remote machine
keypair_file=$1;

declare -a hosts=( $(cat allNodes.txt) )
echo ${hosts[@]}

for ((i=0; i<${#hosts[@]}; i++)) do
	#$(scp -i $keypair_file $file ec2-user@${hosts[$i]}:$to);
	$(scp $file wiadmin@${hosts[$i]}:$to);
	#echo "scp -i $keypair_file $file ec2-user@${hosts[$i]}:$to";
        echo "sent to ${hosts[$i]}";
done
