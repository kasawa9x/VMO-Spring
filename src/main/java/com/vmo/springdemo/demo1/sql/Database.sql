-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: vmo_spring111
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `bill_tbl`
--

DROP TABLE IF EXISTS `bill_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_tbl` (
  `bill_id` int NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `price` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `FKfc68or7t6wcvyhw2kwj6ftkj3` (`user_id`),
  CONSTRAINT `FKfc68or7t6wcvyhw2kwj6ftkj3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_tbl`
--

LOCK TABLES `bill_tbl` WRITE;
/*!40000 ALTER TABLE `bill_tbl` DISABLE KEYS */;
INSERT INTO `bill_tbl` VALUES (1,NULL,NULL,NULL,NULL,NULL,4200,1,'2022-04-26 23:26:19'),(2,NULL,NULL,NULL,NULL,NULL,2700,2,'2022-04-27 09:26:34'),(3,NULL,NULL,NULL,NULL,NULL,2600,3,'2022-04-28 15:14:05');
/*!40000 ALTER TABLE `bill_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billdetail_tbl`
--

DROP TABLE IF EXISTS `billdetail_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billdetail_tbl` (
  `billdetail_id` int NOT NULL AUTO_INCREMENT,
  `price` bigint NOT NULL,
  `quantity` int NOT NULL,
  `bill_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`billdetail_id`),
  KEY `FKjwxmx8x28ot9drfpt6m8namvb` (`bill_id`),
  KEY `FKeyyed9e142cnelku6h6vl1h83` (`product_id`),
  CONSTRAINT `FKeyyed9e142cnelku6h6vl1h83` FOREIGN KEY (`product_id`) REFERENCES `product_tbl` (`product_id`),
  CONSTRAINT `FKjwxmx8x28ot9drfpt6m8namvb` FOREIGN KEY (`bill_id`) REFERENCES `bill_tbl` (`bill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billdetail_tbl`
--

LOCK TABLES `billdetail_tbl` WRITE;
/*!40000 ALTER TABLE `billdetail_tbl` DISABLE KEYS */;
INSERT INTO `billdetail_tbl` VALUES (1,600,2,1,3),(2,1000,3,1,1),(3,1500,1,2,2),(4,600,2,2,3),(5,600,1,3,3),(6,1000,2,3,1);
/*!40000 ALTER TABLE `billdetail_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_tbl`
--

DROP TABLE IF EXISTS `cart_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_tbl` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FKkshfjiisdnhyckqc4h135ujid` (`product_id`),
  KEY `FKoe6r2spo5si8s0j678pf557je` (`user_id`),
  CONSTRAINT `FKkshfjiisdnhyckqc4h135ujid` FOREIGN KEY (`product_id`) REFERENCES `product_tbl` (`product_id`),
  CONSTRAINT `FKoe6r2spo5si8s0j678pf557je` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_tbl`
--

LOCK TABLES `cart_tbl` WRITE;
/*!40000 ALTER TABLE `cart_tbl` DISABLE KEYS */;
INSERT INTO `cart_tbl` VALUES (7,'2022-04-29 09:15:36',1,5,1),(8,'2022-04-29 09:15:51',2,4,1),(9,'2022-04-29 09:17:08',1,3,1),(10,'2022-04-29 09:17:44',1,3,1),(11,'2022-04-29 09:17:59',1,3,2);
/*!40000 ALTER TABLE `cart_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_tbl`
--

DROP TABLE IF EXISTS `category_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_tbl` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_tbl`
--

LOCK TABLES `category_tbl` WRITE;
/*!40000 ALTER TABLE `category_tbl` DISABLE KEYS */;
INSERT INTO `category_tbl` VALUES (1,'SmartPhone'),(2,'Laptop'),(3,'Keyboard');
/*!40000 ALTER TABLE `category_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_tbl`
--

DROP TABLE IF EXISTS `product_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_tbl` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK5xwqb9thqesvk5qhq52ivqe9l` (`category_id`),
  CONSTRAINT `FK5xwqb9thqesvk5qhq52ivqe9l` FOREIGN KEY (`category_id`) REFERENCES `category_tbl` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tbl`
--

LOCK TABLES `product_tbl` WRITE;
/*!40000 ALTER TABLE `product_tbl` DISABLE KEYS */;
INSERT INTO `product_tbl` VALUES (1,'Iphone 12',1000,17,1),(2,'MacBook Pro 13',1500,28,2),(3,'Iphone 11',600,16,1),(4,'Iphone 11 Pro max',700,25,1),(5,'Iphone X',400,25,1),(6,'Iphone 11 Pro',650,25,1);
/*!40000 ALTER TABLE `product_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,1),(3,1),(1,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `coin` double NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,50,'khanh1@gmail.com','$2a$10$3dcGKJ7lkZedJjC.o2ZLGeV9nv3RnI6sOs7nWMn9lY3/Pm7aoV3O6','khanh1'),(2,22,'khanh@gmail.com','$2a$10$OH7G9D1S1RgdERbMYBnZLOQA4sDWBewkawKRyR4uwamXOQWe2JRmC','khanh'),(3,100,'hongsonaaa@gmail.com','$2a$10$aMYZcaNFq35BDna98K.ifOJaA2hamM18jQp4AD.7s5LBIXJKLTy4S','hson');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-29 14:02:22
