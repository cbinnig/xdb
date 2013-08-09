USE xdb_schema;

-- MySQL dump 10.13  Distrib 5.6.11, for osx10.7 (x86_64)
--
-- Host: localhost    Database: xdb_schema
-- ------------------------------------------------------
-- Server version	5.6.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute` (
  `OID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `TYPE` int(11) NOT NULL,
  `TABLE_OID` bigint(20) NOT NULL,
  PRIMARY KEY (`OID`),
  UNIQUE KEY `ATTRIBUTE_TABLE_OID_NAME_key` (`TABLE_OID`,`NAME`),
  CONSTRAINT `ATTRIBUTE_TABLE_OID_fkey` FOREIGN KEY (`TABLE_OID`) REFERENCES `table` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connection`
--

DROP TABLE IF EXISTS `connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `connection` (
  `OID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `URL` varchar(1023) NOT NULL,
  `USER` varchar(255) NOT NULL,
  `PASSWD` varchar(255) NOT NULL,
  `STORE` int(11) NOT NULL,
  PRIMARY KEY (`OID`),
  UNIQUE KEY `CONNECTION_NAME_key` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connection`
--

LOCK TABLES `connection` WRITE;
/*!40000 ALTER TABLE `connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `function` (
  `OID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `SCHEMA_OID` bigint(20) NOT NULL,
  `LANGUAGE` bigint(20) DEFAULT NULL,
  `SOURCE` text,
  PRIMARY KEY (`OID`),
  KEY `FUNCTION_SCHEMA_OID_fkey` (`SCHEMA_OID`),
  CONSTRAINT `FUNCTION_SCHEMA_OID_fkey` FOREIGN KEY (`SCHEMA_OID`) REFERENCES `schema` (`OID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function`
--

LOCK TABLES `function` WRITE;
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
/*!40000 ALTER TABLE `function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partition`
--

DROP TABLE IF EXISTS `partition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partition` (
  `OID` bigint(20) NOT NULL,
  `TABLE_OID` bigint(20) NOT NULL,
  `PARTITION_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`OID`),
  KEY `PARTITION_TABLE_OID_fkey` (`TABLE_OID`),
  CONSTRAINT `PARTITION_TABLE_OID_fkey` FOREIGN KEY (`TABLE_OID`) REFERENCES `table` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partition`
--

LOCK TABLES `partition` WRITE;
/*!40000 ALTER TABLE `partition` DISABLE KEYS */;
/*!40000 ALTER TABLE `partition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partitionattributes`
--

DROP TABLE IF EXISTS `partitionattributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partitionattributes` (
  `PART_ATT_OID` bigint(20) NOT NULL,
  `REF_ATT_OID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PART_ATT_OID`),
  KEY `PARTATT2_ATT_OID_fkey_idx` (`REF_ATT_OID`),
  CONSTRAINT `PARTATT1_ATT_OID_fkey` FOREIGN KEY (`PART_ATT_OID`) REFERENCES `attribute` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PARTATT2_ATT_OID_fkey` FOREIGN KEY (`REF_ATT_OID`) REFERENCES `attribute` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partitionattributes`
--

LOCK TABLES `partitionattributes` WRITE;
/*!40000 ALTER TABLE `partitionattributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `partitionattributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partitiontoconnection`
--

DROP TABLE IF EXISTS `partitiontoconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partitiontoconnection` (
  `P_OID` bigint(20) NOT NULL,
  `C_OID` bigint(20) NOT NULL,
  PRIMARY KEY (`P_OID`,`C_OID`),
  KEY `PARTITIONTOCONNECTION_CONNECTION_OID_fkey` (`C_OID`),
  CONSTRAINT `PARTITIONTOCONNECTION_CONNECTION_OID_fkey` FOREIGN KEY (`C_OID`) REFERENCES `connection` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `PARTITIONTOCONNECTION_PARTITION_OID_fkey` FOREIGN KEY (`P_OID`) REFERENCES `partition` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partitiontoconnection`
--

LOCK TABLES `partitiontoconnection` WRITE;
/*!40000 ALTER TABLE `partitiontoconnection` DISABLE KEYS */;
/*!40000 ALTER TABLE `partitiontoconnection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schema`
--

DROP TABLE IF EXISTS `schema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schema` (
  `OID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`OID`),
  UNIQUE KEY `SCHEMA_NAME_key` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schema`
--

LOCK TABLES `schema` WRITE;
/*!40000 ALTER TABLE `schema` DISABLE KEYS */;
INSERT INTO `schema` VALUES (1,'PUBLIC');
/*!40000 ALTER TABLE `schema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table`
--

DROP TABLE IF EXISTS `table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table` (
  `OID` bigint(20) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `SCHEMA_OID` bigint(20) NOT NULL,
  `PART_CNT` tinyint(1) NOT NULL,
  `PART_TYPE` varchar(255) DEFAULT NULL,
  `REF_TABLE_OID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`OID`),
  UNIQUE KEY `TABLE_SCHEMA_OID_NAME_key` (`SCHEMA_OID`,`NAME`),
  KEY `TABLE_TABLE_OID_fkey_idx` (`REF_TABLE_OID`),
  CONSTRAINT `TABLE_SCHEMA_OID_fkey` FOREIGN KEY (`SCHEMA_OID`) REFERENCES `schema` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TABLE_TABLE_OID_fkey` FOREIGN KEY (`REF_TABLE_OID`) REFERENCES `table` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table`
--

LOCK TABLES `table` WRITE;
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
/*!40000 ALTER TABLE `table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabletoconnection`
--

DROP TABLE IF EXISTS `tabletoconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabletoconnection` (
  `T_OID` bigint(20) NOT NULL,
  `C_OID` bigint(20) NOT NULL,
  PRIMARY KEY (`T_OID`,`C_OID`),
  KEY `TABLETOCONNECTION_CONNECTION_OID_fkey` (`C_OID`),
  CONSTRAINT `TABLETOCONNECTION_CONNECTION_OID_fkey` FOREIGN KEY (`C_OID`) REFERENCES `connection` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `TABLETOCONNECTION_TABLE_OID_fkey` FOREIGN KEY (`T_OID`) REFERENCES `table` (`OID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabletoconnection`
--

LOCK TABLES `tabletoconnection` WRITE;
/*!40000 ALTER TABLE `tabletoconnection` DISABLE KEYS */;
/*!40000 ALTER TABLE `tabletoconnection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-08-09 10:16:00
