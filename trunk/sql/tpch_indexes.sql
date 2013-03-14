ALTER TABLE `tpch_s01`.`customer` 
ADD INDEX `c_mktsegment_idx` USING HASH (`C_MKTSEGMENT` ASC) ;

ALTER TABLE `tpch_s01`.`orders` 
ADD INDEX `o_orderdate_idx` USING BTREE (`O_ORDERDATE` ASC) ;

ALTER TABLE `tpch_s01`.`lineitem` 
ADD INDEX `l_shipdate_idx` USING BTREE (`L_SHIPDATE` ASC) ;
