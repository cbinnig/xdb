#!/bin/bash
if [ "$#" -ne 2 ]; then
  echo "Usage: $0 DATABASE_NAME PARTITION_NUMBER" >&2
  exit 1
fi

db=$1;
mysql -uxroot -pxroot -e "RENAME TABLE customer TO customer_p$2" $db;
mysql -uxroot -pxroot -e "RENAME TABLE lineitem TO lineitem_p$2" $db;
mysql -uxroot -pxroot -e "RENAME TABLE orders TO orders_p$2" $db;
mysql -uxroot -pxroot -e "RENAME TABLE part TO part_p$2" $db;
mysql -uxroot -pxroot -e "RENAME TABLE partsupp TO partsupp_p$2" $db;
mysql -uxroot -pxroot -e "RENAME TABLE supplier TO supplier_p$2" $db;
