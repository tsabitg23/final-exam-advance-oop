-- MariaDB dump 10.19  Distrib 10.4.19-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: auto_film_shop
-- ------------------------------------------------------
-- Server version	10.4.19-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auto_film_items`
--

DROP TABLE IF EXISTS `auto_film_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auto_film_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auto_film_id` int(11) NOT NULL,
  `type` varchar(100) NOT NULL,
  `price` int(14) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `auto_film_items_auto_film_id_fk` (`auto_film_id`),
  CONSTRAINT `auto_film_items_auto_film_id_fk` FOREIGN KEY (`auto_film_id`) REFERENCES `auto_films` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto_film_items`
--

LOCK TABLES `auto_film_items` WRITE;
/*!40000 ALTER TABLE `auto_film_items` DISABLE KEYS */;
INSERT INTO `auto_film_items` (`id`, `auto_film_id`, `type`, `price`, `created_at`, `updated_at`, `deleted_at`) VALUES (1,1,'full',200,'2022-02-16 01:30:15','2022-02-16 01:30:15',NULL),(2,1,'front_back_only',100,'2022-02-16 01:30:15','2022-02-16 01:30:15',NULL),(3,2,'full',150,'2022-02-16 01:30:15','2022-02-16 01:30:15',NULL),(4,2,'front_back_only',90,'2022-02-16 01:30:15','2022-02-16 01:30:15',NULL),(5,3,'full',100,'2022-02-16 01:30:15','2022-02-16 01:30:15',NULL),(6,3,'front_back_only',50,'2022-02-16 01:30:15','2022-02-16 01:30:15',NULL);
/*!40000 ALTER TABLE `auto_film_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auto_films`
--

DROP TABLE IF EXISTS `auto_films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auto_films` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto_films`
--

LOCK TABLES `auto_films` WRITE;
/*!40000 ALTER TABLE `auto_films` DISABLE KEYS */;
INSERT INTO `auto_films` (`id`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES (1,'V-Kool','2022-02-16 01:28:22','2022-02-16 01:28:22',NULL),(2,'Lumar','2022-02-16 01:28:36','2022-02-16 01:28:36',NULL),(3,'MMM','2022-02-16 01:28:36','2022-02-16 01:28:36',NULL);
/*!40000 ALTER TABLE `auto_films` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_car_id` int(11) NOT NULL,
  `booking_code` varchar(20) NOT NULL,
  `total` int(14) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_customer_cars_id_fk` (`customer_car_id`),
  CONSTRAINT `booking_customer_cars_id_fk` FOREIGN KEY (`customer_car_id`) REFERENCES `customer_cars` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_detail`
--

DROP TABLE IF EXISTS `booking_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_detail_booking_id_fk` (`booking_id`),
  KEY `booking_detail_auto_film_items_id_fk` (`item_id`),
  CONSTRAINT `booking_detail_auto_film_items_id_fk` FOREIGN KEY (`item_id`) REFERENCES `auto_film_items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `booking_detail_booking_id_fk` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_detail`
--

LOCK TABLES `booking_detail` WRITE;
/*!40000 ALTER TABLE `booking_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_types`
--

DROP TABLE IF EXISTS `car_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `installation_price` int(14) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_types`
--

LOCK TABLES `car_types` WRITE;
/*!40000 ALTER TABLE `car_types` DISABLE KEYS */;
INSERT INTO `car_types` (`id`, `name`, `installation_price`, `created_at`, `updated_at`, `deleted_at`) VALUES (1,'APV',10,'2022-02-16 01:30:50','2022-02-16 01:30:50',NULL),(2,'SUV',10,'2022-02-16 01:30:50','2022-02-16 01:30:50',NULL),(3,'MPV',10,'2022-02-16 01:30:50','2022-02-16 01:30:50',NULL),(4,'Sedan',30,'2022-02-16 01:30:50','2022-02-16 01:30:50',NULL),(5,'Coupe',30,'2022-02-16 01:30:50','2022-02-16 01:30:50',NULL);
/*!40000 ALTER TABLE `car_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_cars`
--

DROP TABLE IF EXISTS `customer_cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `car_type_id` int(11) NOT NULL,
  `plate_number` varchar(10) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_cars_car_types_id_fk` (`car_type_id`),
  KEY `customer_cars_customers_id_fk` (`customer_id`),
  CONSTRAINT `customer_cars_car_types_id_fk` FOREIGN KEY (`car_type_id`) REFERENCES `car_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `customer_cars_customers_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_cars`
--

LOCK TABLES `customer_cars` WRITE;
/*!40000 ALTER TABLE `customer_cars` DISABLE KEYS */;
INSERT INTO `customer_cars` (`id`, `customer_id`, `car_type_id`, `plate_number`, `created_at`, `updated_at`, `deleted_at`) VALUES (1,1,1,'B 1234 JA','2022-02-16 01:31:32','2022-02-16 01:31:32',NULL);
/*!40000 ALTER TABLE `customer_cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_at` datetime NOT NULL DEFAULT current_timestamp(),
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`id`, `name`, `phone_number`, `created_at`, `updated_at`, `deleted_at`) VALUES (1,'james','08912738213','2022-02-16 01:31:18','2022-02-16 01:31:18',NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-16  3:49:47
