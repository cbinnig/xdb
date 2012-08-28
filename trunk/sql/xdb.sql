CREATE DATABASE IF NOT EXISTS xdb_schema;
CREATE DATABASE IF NOT EXISTS xdb_tmp;

CREATE USER 'xroot'@'localhost' IDENTIFIED BY 'xroot';
GRANT ALL ON *.* TO 'xroot'@'localhost' IDENTIFIED BY 'xroot';
CREATE USER 'xroot'@'%' IDENTIFIED BY 'xroot';
GRANT ALL ON *.* TO 'xroot'@'%' IDENTIFIED BY 'xroot';

-- CREATE USER 'xroot'@'<localip>' IDENTIFIED BY 'xroot';
-- GRANT ALL ON *.* TO 'xroot'@'<localip>' IDENTIFIED BY 'xroot';