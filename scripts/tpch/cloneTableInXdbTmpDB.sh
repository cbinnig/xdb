#!/bin/bash
user="xroot"
pass="xroot"
db="xdb_tmp"

if [ $# -ne 1 ]
then
        echo "Usage: $0 {MySQL-Table-Name}"
        echo "By clones the table to a new table, finds read/write time"
        exit 1
fi

table=$1

echo "Cloning the table's schema" 
mysql -u $user -p$pass $db -e "CREATE TABLE temp_$table SELECT * FROM $table LIMIT 0"

echo "Flushing the cache/buffer"
mysql -u $user -p$pass $db -e "RESET QUERY CACHE" 
mysql -u $user -p$pass $db -e "FLUSH TABLES"

echo "Copying data"
query="INSERT INTO temp_$table SELECT * FROM $table"
time=$(TIMEFORMAT=%R; (time mysql -u $user -p$pass $db -e "$query" 2>&1)2>&1 > /dev/null)
echo -e "******************"
echo "Time spent: $time"
echo -e "******************"

mysql -u $user -p$pass $db -e "SELECT count(*) FROM $table" 


echo "Deleting the footprints (and the duplicated table) "
mysql -u $user -p$pass $db -e "DROP TABLE temp_$table" 
