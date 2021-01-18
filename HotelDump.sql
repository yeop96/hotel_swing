-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: javadb
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `member` (
  `id` varchar(30) NOT NULL,
  `pw` varchar(30) NOT NULL,
  `pwcheck` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `birth` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `address1` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('root','0000','0000','루트','970110','root@naver.com','01012341234','서울시 광진구 화양동 1234-123'),('user1','0000','0000','루트','970110','r','01012341234','서울특별시 광진구 화양동'),('user2','0000','0000','루트','970110','r','01012341234','서울특별시 광진구 화양동'),('user3','0000','0000','루트','970110','r','01012341234','서울특별시 광진구 화양동'),('user4','0000','0000','루트','970110','r','01012341234','서울특별시 광진구 화양동');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservation` (
  `f_id` varchar(30) DEFAULT NULL,
  `startday` datetime NOT NULL,
  `endday` datetime DEFAULT NULL,
  `roomnum` varchar(30) NOT NULL,
  `adult` varchar(30) NOT NULL,
  `child` varchar(30) NOT NULL,
  `comeway` int(11) NOT NULL,
  `pay` int(11) NOT NULL,
  `salepay` int(11) NOT NULL,
  `totalpay` int(11) NOT NULL,
  `payway` varchar(30) NOT NULL,
  KEY `f_id` (`f_id`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`f_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES ('root','2021-01-07 00:00:00','2021-01-10 00:00:00','2호실','2','0',0,40000,0,40000,'세종페이'),('root','2021-02-07 00:00:00','2021-02-10 00:00:00','3호실','2','0',1,50000,0,50000,'간편 계좌 이체'),('user1','2021-01-07 00:00:00','2021-01-10 00:00:00','7호실','2','0',0,70000,0,70000,'토스'),('user1','2021-02-05 00:00:00','2021-02-06 00:00:00','2호실','2','0',0,40000,0,40000,'현금결제'),('user2','2021-01-12 00:00:00','2021-01-14 00:00:00','2호실','2','0',0,40000,0,40000,'현금결제'),('root','2021-03-02 00:00:00','2021-03-05 00:00:00','2호실','2','0',0,40000,0,40000,'간편 계좌 이체');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-19  1:10:51
