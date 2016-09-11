-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: dmitry_kach_db
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `house` varchar(10) DEFAULT NULL,
  `zip_code` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'США','Лос-Анджелес','5-е авеню','11','321-11-889'),(2,'РБ','Минск','ул. Притыцкого','1','111-002'),(3,'РФ','Москва','пр. Ленина','32','222'),(4,'ОАЭ','Дубаи','ул. Ленина','15','332-05-545');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `attachment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contact_id` int(10) unsigned NOT NULL,
  `filename` varchar(255) NOT NULL,
  `upload_date` datetime DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`attachment_id`),
  KEY `contact_id_idx` (`contact_id`),
  CONSTRAINT `attachment_contact` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `contact_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `status` enum('холост','женат','замужем','не замужем') DEFAULT NULL,
  `gender` enum('М','Ж') NOT NULL,
  `citizenship` varchar(45) DEFAULT NULL,
  `web_site` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `job_current` varchar(45) DEFAULT NULL,
  `address_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `contacts_address_idx` (`address_id`),
  CONSTRAINT `contacts_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (1,'Арнольд','Шварценеггер','Алоис','1947-07-30','женат','М','США','www.illbeback.com','arni@gmail.com','freelance',1),(2,'Иван','Иванов','Васильевич','1980-01-01','холост','М','РБ',NULL,'ivan@mail.ru','ООО \"Рога и копыта\"',2),(3,'Василиса','Премудрая',NULL,'2000-05-15','не замужем','Ж','СССР',NULL,'vas@mail.su','Союзмультфильм',NULL),(4,'Александр','Васечкин','Олегович','1988-11-11','женат','М','РФ','www.сайт.рф',NULL,'ЖКХ - 13',3),(5,'Василий','Пупкин','Васильевич','1853-06-01','холост','М','Австрия',NULL,NULL,'',NULL),(6,'John','Doe','Smith','1991-05-05','холост','М','США','john.ru','j.doe@gmail.com','Google Inc.',NULL),(7,'Александр','Петров','Григорьевич','1951-06-24','холост','М','ОАЭ','emptysite.com','alex.petrov@mail.ru','Facebook',4),(8,'Ирина','Сидорова','Александровна','1980-12-22','замужем','Ж','РБ',NULL,'ira.sid@mail.ru','ИП Сидорова И.А.',NULL),(9,'Барак','Обама',NULL,'2001-08-15','холост','М','РБ',NULL,NULL,'ООО \"Евроторг\"',NULL),(10,'Дарья','Василева','Ивановна','1985-07-12','замужем','Ж','США',NULL,'daska@mail.ru','безработная',NULL);
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `phone_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contact_id` int(10) unsigned NOT NULL,
  `county_code` varchar(45) DEFAULT NULL,
  `operator_code` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) NOT NULL,
  `phone_type` enum('мобильный','домашний') NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phone_id`),
  KEY `contact_phone_id_idx` (`contact_id`),
  CONSTRAINT `phone_contact` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-11 22:14:35
