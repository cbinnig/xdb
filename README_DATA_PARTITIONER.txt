This readme file is for running the data files partitioner tool. The partitioner
tool has two options: Hashing partition and Reference Partition. 

1- Hashing Partitioning. 
type the following command 

./partitionerData.sh -f {file name (path)} -m hashing -k 0,1,.. -n {positive number} 

2- Reference Partitioning 
type the following command 

./partitionerData.sh -f {file name (path)} -m reference -k 0,1,.. -rf {reference file (only table name)} -rk {reference keys}

For more details about the command options; 

type ./partitionerData.sh -h 