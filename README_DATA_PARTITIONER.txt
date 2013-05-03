This README file is to explain the usage of the  data partitioner tool. 
The partitioner partitions CSV separated files describing tables.
The tool supports two partitioning methods: Hash-partition and Reference-partition. 

1. Hashing Partitioning: 
dataPartitioner.sh -f {file name (path)} -m hashing -k 0,1,.. -n {positive number} 

2. Reference Partitioning: 
dataPartitioner.sh -f {file name (path)} -m reference -k 0,1,.. -rf {reference file (only table name)} -rk {reference keys}

For more details about the command options, run: 
dataPartitioner.sh -h 