DROP DATABASE IF EXISTS xdb_test;
DROP DATABASE IF EXISTS xdb_schema;
DROP DATABASE IF EXISTS xdb_tmp;
DROP DATABASE IF EXISTS xdb_cluster;

CREATE DATABASE xdb_test;
CREATE DATABASE xdb_schema;
CREATE DATABASE xdb_tmp;
CREATE DATABASE xdb_cluster;

CREATE USER 'xroot'@'localhost' IDENTIFIED BY 'xroot';
GRANT ALL ON *.* TO 'xroot'@'localhost' IDENTIFIED BY 'xroot';
CREATE USER 'xroot'@'%' IDENTIFIED BY 'xroot';
GRANT ALL ON *.* TO 'xroot'@'%' IDENTIFIED BY 'xroot';

-- CREATE USER 'xroot'@'<localip>' IDENTIFIED BY 'xroot';
-- GRANT ALL ON *.* TO 'xroot'@'<localip>' IDENTIFIED BY 'xroot';