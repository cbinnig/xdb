#!/bin/bash

machine_id=0;
partitions_cnt_on_each_machine=8;

mysql_addr="mysql";
db_url="localhost";
db_user="root";
db_pass="root";


for ((j=0;j<$partitions_cnt_on_each_machine; j++)) do
	date;
	# Let's calculate the SQLcommand filename to be imported to this machine
	number=$(($machine_id * $partitions_cnt_on_each_machine + $j + 1));
	filename="SQLcommands_$number.sql";
        mysql_command="mysql -u$db_user -p$db_pass < ~/temp/$filename";
        eval $mysql_command;
	echo "Partition $j created on Host $machine_id";
done

