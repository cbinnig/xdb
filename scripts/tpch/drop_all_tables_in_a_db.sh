#!/bin/bash
MUSER="xroot"
MPASS="xroot"
MDB=$1
 
# Detect paths
AWK=$(which awk)
GREP=$(which grep)
 
if [ $# -ne 1 ]
then
	echo "Usage: $0 {MySQL-Database-Name}"
	echo "Drops all tables from a MySQL"
	exit 1
fi
 
TABLES=$(mysql -u $MUSER -p$MPASS $MDB -e 'show tables' | awk '{ print $1}' | grep -v '^Tables' )
 
for t in $TABLES
do
	echo "Deleting $t table from $MDB database..."
	mysql -u $MUSER -p$MPASS $MDB -e "drop table $t"
done
