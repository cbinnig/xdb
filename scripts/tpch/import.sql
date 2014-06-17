load data local
INFILE 'region.dat'
INTO TABLE  REGION
FIELDS TERMINATED BY '|';

load data local
INFILE 'nation.dat'
INTO TABLE  NATION
FIELDS TERMINATED BY '|';

load data local
INFILE 'supplier.dat'
INTO TABLE  SUPPLIER
FIELDS TERMINATED BY '|';

load data local
INFILE 'customer.dat'
INTO TABLE  CUSTOMER
FIELDS TERMINATED BY '|';

load data local
INFILE 'part.dat'
INTO TABLE  PART
FIELDS TERMINATED BY '|';

load data local
INFILE 'partsupp.dat'
INTO TABLE  PARTSUPP
FIELDS TERMINATED BY '|';

load data local
INFILE 'orders.dat'
INTO TABLE  ORDERS
FIELDS TERMINATED BY '|';

load data local
INFILE 'lineitem.dat'
INTO TABLE  LINEITEM
FIELDS TERMINATED BY '|';
