-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library_db
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `Masach` varchar(13) NOT NULL,
  `Tensach` varchar(255) DEFAULT NULL,
  `Tacgia` varchar(255) DEFAULT NULL,
  `Theloai` varchar(255) DEFAULT NULL,
  `Soluong` int DEFAULT NULL,
  PRIMARY KEY (`Masach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('1','sach1','tacgia1','theloai1',39),('11','ten11','tg11','tl11',11),('12','sach12','tg12','tl12',12),('2','sach2','tacgia2','theloai2',18),('3','sach3','tacgia3','theloai1237',1),('4','sach4','tg4','tl4',30),('7','sach7','tacgia7','theloai7',70),('8','sach8','tacgia8','tl8',80);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowings`
--

DROP TABLE IF EXISTS `borrowings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowings` (
  `Mamuon` int NOT NULL AUTO_INCREMENT,
  `Mathanhvien` varchar(255) DEFAULT NULL,
  `Masach` varchar(13) DEFAULT NULL,
  `Ngaymuon` date DEFAULT NULL,
  `Ngaytra` date DEFAULT NULL,
  `DueDate` date DEFAULT NULL,
  PRIMARY KEY (`Mamuon`),
  KEY `member_id` (`Mathanhvien`),
  KEY `isbn` (`Masach`),
  CONSTRAINT `borrowings_ibfk_1` FOREIGN KEY (`Mathanhvien`) REFERENCES `members` (`Mathanhvien`),
  CONSTRAINT `borrowings_ibfk_2` FOREIGN KEY (`Masach`) REFERENCES `books` (`Masach`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowings`
--

LOCK TABLES `borrowings` WRITE;
/*!40000 ALTER TABLE `borrowings` DISABLE KEYS */;
INSERT INTO `borrowings` VALUES (1,'1','1','2024-01-01','2024-02-01','2024-02-02'),(2,'2','2','2024-07-03','2024-07-04','2024-08-03'),(3,'2','2','2024-07-03','2024-07-04','2024-08-03'),(4,'2','2','2024-07-04','2024-07-04','2024-08-03'),(5,'1','2','2024-07-04','2024-07-04','2024-08-03'),(6,'2','7','2024-07-04','2024-07-04','2024-08-04'),(7,'1','1','2024-07-04',NULL,'2024-08-03'),(8,'1','2','2024-07-04',NULL,'2024-08-03'),(9,'2','7','2024-07-04','2024-07-04','2024-08-03'),(10,'2','3','2024-07-04',NULL,'2024-08-03'),(11,'2','3','2024-07-04',NULL,'2024-08-03'),(12,'1','3','2024-07-04','2024-07-04','2024-08-03'),(13,'1','11','2024-07-04','2024-07-04','2024-08-03'),(14,'2','11','2024-07-04','2024-07-04','2024-08-03');
/*!40000 ALTER TABLE `borrowings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `librarians`
--

DROP TABLE IF EXISTS `librarians`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `librarians` (
  `Mathuthu` varchar(255) NOT NULL,
  `Tenthuthu` varchar(255) DEFAULT NULL,
  `Calamviec` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Mathuthu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `librarians`
--

LOCK TABLES `librarians` WRITE;
/*!40000 ALTER TABLE `librarians` DISABLE KEYS */;
INSERT INTO `librarians` VALUES ('1','lam','5');
/*!40000 ALTER TABLE `librarians` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `Mathanhvien` varchar(255) NOT NULL,
  `Tenthanhvien` varchar(255) DEFAULT NULL,
  `Diachi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Mathanhvien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('1','tv1','dc1'),('2','lan','dc2'),('3','thanhvien4','diachi3'),('4','thanhvien3','diachi4');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-04 16:39:41
