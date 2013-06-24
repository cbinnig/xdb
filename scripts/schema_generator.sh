#!/bin/bash
db_cnt=64;
mysql_addr="/usr/local/mysql/bin/mysql";
db_url="localhost";
db_user="xroot";
db_pass="xroot";
db_root_user="root";
db_root_pass="";
db_name_prefix="tpch_sf0_5_part64_";
create_databases=true;

output_file="./temp/schema_generator_runnable.sql";

# Check if the "temp" directory exists
if [ ! -d "temp" ]; then
  mkdir -p temp;
fi

# Remove the old schema_generator.sql if it exists
$(rm -f $output_file);

nation="CREATE TABLE nation ( N_NATIONKEY INTEGER NOT NULL, \
N_NAME CHAR(25) NOT NULL, \
N_REGIONKEY INTEGER NOT NULL, \
N_COMMENT VARCHAR(152));";

region="CREATE TABLE region ( R_REGIONKEY INTEGER NOT NULL,\
R_NAME CHAR(25) NOT NULL,\
R_COMMENT VARCHAR(152));";

part="CREATE TABLE part ( P_PARTKEY INTEGER NOT NULL,\
P_NAME VARCHAR(55) NOT NULL,\
P_MFGR CHAR(25) NOT NULL,\
P_BRAND CHAR(10) NOT NULL,\
P_TYPE VARCHAR(25) NOT NULL,\
P_SIZE INTEGER NOT NULL,\
P_CONTAINER CHAR(10) NOT NULL,\
P_RETAILPRICE DECIMAL(15,2) NOT NULL,\
P_COMMENT VARCHAR(23) NOT NULL );";

supplier="CREATE TABLE supplier ( S_SUPPKEY INTEGER NOT NULL,\
S_NAME CHAR(25) NOT NULL,\
S_ADDRESS VARCHAR(40) NOT NULL,\
S_NATIONKEY INTEGER NOT NULL,\
S_PHONE CHAR(15) NOT NULL,\
S_ACCTBAL DECIMAL(15,2) NOT NULL,\
S_COMMENT VARCHAR(101) NOT NULL);";

partsupp="CREATE TABLE partsupp ( PS_PARTKEY INTEGER NOT NULL,\
PS_SUPPKEY INTEGER NOT NULL,\
PS_AVAILQTY INTEGER NOT NULL,\
PS_SUPPLYCOST DECIMAL(15,2) NOT NULL,\
PS_COMMENT VARCHAR(199) NOT NULL );";

customer="CREATE TABLE customer ( C_CUSTKEY INTEGER NOT NULL,\
C_NAME VARCHAR(25) NOT NULL,\
C_ADDRESS VARCHAR(40) NOT NULL,\
C_NATIONKEY INTEGER NOT NULL,\
C_PHONE CHAR(15) NOT NULL,\
C_ACCTBAL DECIMAL(15,2) NOT NULL,\
C_MKTSEGMENT CHAR(10) NOT NULL,\
C_COMMENT VARCHAR(117) NOT NULL);";

orders="CREATE TABLE orders ( O_ORDERKEY INTEGER NOT NULL,\
O_CUSTKEY INTEGER NOT NULL,\
O_ORDERSTATUS CHAR(1) NOT NULL,\
O_TOTALPRICE DECIMAL(15,2) NOT NULL,\
O_ORDERDATE DATE NOT NULL,\
O_ORDERPRIORITY CHAR(15) NOT NULL,\
O_CLERK CHAR(15) NOT NULL,\
O_SHIPPRIORITY INTEGER NOT NULL,\
O_COMMENT VARCHAR(79) NOT NULL);";

lineitem="CREATE TABLE lineitem ( L_ORDERKEY INTEGER NOT NULL,\
L_PARTKEY INTEGER NOT NULL,\
L_SUPPKEY INTEGER NOT NULL,\
L_LINENUMBER INTEGER NOT NULL,\
L_QUANTITY DECIMAL(15,2) NOT NULL,\
L_EXTENDEDPRICE DECIMAL(15,2) NOT NULL,\
L_DISCOUNT DECIMAL(15,2) NOT NULL,\
L_TAX DECIMAL(15,2) NOT NULL,\
L_RETURNFLAG CHAR(1) NOT NULL,\
L_LINESTATUS CHAR(1) NOT NULL,\
L_SHIPDATE DATE NOT NULL,\
L_COMMITDATE DATE NOT NULL,\
L_RECEIPTDATE DATE NOT NULL,\
L_SHIPINSTRUCT CHAR(25) NOT NULL,\
L_SHIPMODE CHAR(10) NOT NULL,\
L_COMMENT VARCHAR(44) NOT NULL);";


# First, lets create the databases;
if $create_databases; then
	for ((i=1; i<=db_cnt; i++))do
		db_name="$db_name_prefix$i";
		
		echo "CREATE DATABASE $db_name;" >> $output_file;
		echo "GRANT ALL PRIVILEGES ON $db_name.* TO $db_user@$db_url IDENTIFIED BY '$db_pass';" >> $output_file;
		echo "USE $db_name;" >> $output_file;
		
		echo $nation >> $output_file;
		echo $region >> $output_file;
		echo $part >> $output_file;
		echo $supplier >> $output_file;
		echo $partsupp >> $output_file;
		echo $customer >> $output_file;
		echo $orders >> $output_file;
		echo $lineitem >> $output_file;
	done
	
	echo "Creating databases ...";	
	$($mysql_addr -u$db_root_user -p$db_root_pass < $output_file);
	echo "Successfully finished. Take care!";	
fi