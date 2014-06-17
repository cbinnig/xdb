#!/bin/bash

source_folder="/media/iscsi1/dbgen/tpch_2_17_0/dbgen"

for (( i=2; i<=10; i++ ))
do
	sudo cp ${source_folder}/lineitem_p$((i-1)).dat /media/iscsi${i}/data/tpch_s50
	sudo cp ${source_folder}/orders_p$((i-1)).dat /media/iscsi${i}/data/tpch_s50
	sudo cp ${source_folder}/customer_p$((i-1)).dat /media/iscsi${i}/data/tpch_s50
	sudo cp ${source_folder}/part_p$((i-1)).dat /media/iscsi${i}/data/tpch_s50
	sudo cp ${source_folder}/partsupp_p$((i-1)).dat /media/iscsi${i}/data/tpch_s50
	sudo cp ${source_folder}/supplier_p$((i-1)).dat /media/iscsi${i}/data/tpch_s50
	sudo cp ${source_folder}/nation.dat /media/iscsi${i}/data/tpch_s50
	sudo cp ${source_folder}/region.dat /media/iscsi${i}/data/tpch_s50
	
done
