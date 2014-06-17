#!/bin/bash

#temp_file="/home/wiadmin/xdb/TEMP_KILL_ALL"
temp_file="/tmp/KILL_ALL_TEMP.txt"
sudo rm -f $temp_file
query="select concat('KILL ',id,';') from information_schema.processlist where user='xroot' and time > 10 into outfile '$temp_file';"
sudo mysql -uxroot -pxroot -e "$query"
sudo mysql -uxroot -pxroot -e "source $temp_file"
