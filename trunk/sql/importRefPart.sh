#!/bin/bash
for (( i=0; i<$1; i++ ))
do
	echo "Importing Partition $i"
	database=xdb_tpch_$i

	echo "Dropping DB $database"
	mysql -u root -e "DROP DATABASE IF EXISTS $database;"
	echo "Recreating DB $database"
	mysql -u root -e "CREATE DATABASE $database;"

	echo ".. Nation"
	mysql -u root --database=$database < ./$i/nation.sql
	echo ".. Region"
	mysql -u root --database=$database < ./$i/region.sql
	echo ".. Customer"
	mysql -u root --database=$database < ./$i/customer.sql
	echo ".. Supplier"
	mysql -u root --database=$database < ./$i/supplier.sql
	echo ".. Part"
	mysql -u root --database=$database < ./$i/part.sql
	echo ".. PartSupp"
	mysql -u root --database=$database < ./$i/partsupp.sql
	echo ".. Orders"
	mysql -u root --database=$database < ./$i/orders.sql
	echo ".. Lineitem"
	mysql -u root --database=$database < ./$i/lineitem.sql
done