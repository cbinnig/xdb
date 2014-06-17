#!/bin/bash
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 DATABASE_NAME PARTITION_NUMBER" >&2
  exit 1
fi

db=$1;
mysql -uxroot -pxroot -e "RENAME TABLE customer_p$2 to customer" $db;
mysql -uxroot -pxroot -e "RENAME TABLE lineitem_p$2 to lineitem" $db;
mysql -uxroot -pxroot -e "RENAME TABLE orders_p$2 to orders" $db;
mysql -uxroot -pxroot -e "RENAME TABLE part_p$2 to part" $db;
mysql -uxroot -pxroot -e "RENAME TABLE partsupp_p$2 to partsupp" $db;
mysql -uxroot -pxroot -e "RENAME TABLE supplier_p$2 to supplier" $db;
