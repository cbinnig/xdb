CREATE DATABASE  IF NOT EXISTS stratusdb;
CREATE USER 'stratusroot'@'localhost' IDENTIFIED BY 'stratusroot';
GRANT ALL ON *.* TO 'stratusroot'@'localhost' IDENTIFIED BY 'stratusroot';
CREATE USER 'stratusroot'@'%' IDENTIFIED BY 'stratusroot';
GRANT ALL ON *.* TO 'stratusroot'@'%' IDENTIFIED BY 'stratusroot';
-- CREATE USER 'stratusroot'@'<localip>' IDENTIFIED BY 'stratusroot';
-- GRANT ALL ON *.* TO 'stratusroot'@'<localip>' IDENTIFIED BY 'stratusroot';