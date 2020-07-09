CREATE DATABASE  IF NOT EXISTS `course_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `course_database`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: course_database
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `country` (
  `country_id` bigint(20) NOT NULL,
  `conversion_fees` decimal(19,2) DEFAULT NULL,
  `iso` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,0.00,'IN','India'),(2,100.00,'US','USA'),(3,150.00,'UK','United Kingdom');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `course_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `payment_strategy` enum('FREE','ONETIME','MONTHLY_SUBSCRIPTION') DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Core Java - Basic OOP concepts','FREE','Core Java'),(2,'Spring Boot and JPA','ONETIME','Spring REST API'),(3,'Micro services - Spring Boot','ONETIME','Spring Microservices'),(4,'DevOps - CI/CD, AWS, Jenkins','MONTHLY_SUBSCRIPTION','DevOps'),(5,'Front end development using Angular8',NULL,'Angular8');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `price` (
  `price_id` bigint(20) NOT NULL,
  `base_price` decimal(19,2) DEFAULT NULL,
  `other_charges` decimal(19,2) DEFAULT NULL,
  `taxes` decimal(19,2) DEFAULT NULL,
  `country_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  PRIMARY KEY (`price_id`),
  KEY `FK659paqt4die6q7ovxpig0obes` (`country_id`),
  KEY `FK56am3ub1xoaa2npjr7poj6jag` (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
INSERT INTO `price` VALUES (1,0.00,0.00,0.00,1,1),(2,0.00,0.00,0.00,2,1),(3,0.00,0.00,0.00,3,1),(4,10000.00,500.00,100.00,1,2),(5,20000.00,1000.00,200.00,1,3),(6,2500.00,0.00,25.00,1,4),(7,10000.00,1000.00,100.00,2,2),(8,7000.00,100.00,70.00,1,5);
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-08 17:27:03
