#!/bin/bash

###########################################
#	Description
# This script sends the $file to all hosts
# specified in allNodes.txt
###########################################

if [ "$#" -ne 3 ]; then
  echo "Usage: $0 KEYPAIR FILE_TO_BE_SENT DESTINATION_FOLDER" >&2
  echo "3 Arguments are needed." >&2
  exit 1
fi

file=$2;			# filename to be sent
to=$3;				# where to be sit in remote machine
keypair_file=$1;

declare -a hosts=( $(cat allNodes.txt) )
echo ${hosts[@]}

for ((i=0; i<${#hosts[@]}; i++)) do
	$(scp -i $keypair_file $file ec2-user@${hosts[$i]}:$to);
	#echo "scp -i $keypair_file $file ec2-user@${hosts[$i]}:$to";
        echo "sent to ${hosts[$i]}";
done
