# Introduction #
The TPCH partitioning scheme used for SIGMOD demo and fault-tolerance paper (10 partitions).


# Initial Setup #
Make sure the extension of table names is .dat (and not .tbl)


# Partitioning commands #
./dataPartitioner.sh -c 31 -f lineitem.dat -fp -k 0 -m hashing -n 10 -t int

./dataPartitioner.sh -c 31 -f orders.dat -fp -k 0 -m reference -n 10 -rf lineitem -rk 0 -t int

./dataPartitioner.sh -c 31 -f customer.dat -fp -k 0 -m reference -n 10 -rf orders -rk 1 -t int

./dataPartitioner.sh -c 31 -f supplier.dat -fp -k 0 -m reference -n 10 -rf lineitem -rk 2 -t int

./dataPartitioner.sh -c 31 -f partsupp.dat -fp -k 0,1 -m reference -n 10 -rf lineitem -rk 1,2 -t int

./dataPartitioner.sh -c 31 -f part.dat -fp -k 0 -m reference -n 10 -rf lineitem -rk 1 -t int