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
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `attachment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `contact_id` int(10) unsigned NOT NULL,
  `filename` varchar(255) NOT NULL,
  `upload_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`attachment_id`),
  KEY `contact_id_idx` (`contact_id`),
  CONSTRAINT `attachment_contact` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (1,1,'1.txt','2016-09-19 20:25:35','файлик'),(2,1,'01-create-user.sql','2016-09-19 20:21:01','sql');
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
  `gender` enum('М','Ж') NOT NULL DEFAULT 'М',
  `citizenship` varchar(45) DEFAULT NULL,
  `web_site` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `job_current` varchar(45) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `house` varchar(10) DEFAULT NULL,
  `flat` varchar(10) DEFAULT NULL,
  `zip_code` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
INSERT INTO `contacts` VALUES (1,'Арнольд','Шварценеггер','Алоис','1947-07-30','женат','М','США','www.illbeback.com','arni@gmail.com','freelance','arni.jpg','США','Лос-Анджелес','5-е авеню','11','112','321-11-889'),(2,'Иван','Иванов','Иванович','1980-01-01','холост','М','РБ','','ivan@mail.ru','ОАО ',NULL,'РБ','Минск','ул. Жукова','1','31','224098'),(3,'Василиса','Премудрая','Григорьевна','2000-05-15','не замужем','Ж','СССР','www.mult.ru','vas@mail.su','Союзмультфильм',NULL,'РФ','Москва','ул. Ленина','123','334','225089'),(5,'Василий','Пупкин','Васильевич','1853-06-01','холост','М','Австрия','','pupok@mail.ru','Amazon',NULL,'Северная Корея','Пхеньян','ул. Ким Чен Ына','13','21',''),(6,'John','Doe','Smith','1991-05-05','холост','М','США','john.ru','j.doe@gmail.com','Google Inc.',NULL,'','','','','',''),(7,'Александр','Петров','Григорьевич','1951-06-24','холост','М','ОАЭ','emptysite.com','alex.petrov@mail.ru','Facebook',NULL,'','','','','',''),(9,'Андрей','Васильков','Игоревич','2001-08-15','холост','М','РБ',NULL,'andry.vas@gmail.com','ООО \"Евроторг\"',NULL,'РБ','Брест','ул. Московсая','19','317','224001'),(11,'Анна','Васнецова','Вячеславовна','1990-08-13','не замужем','Ж','РБ',NULL,'hanna.vasnetsova@gmail.com','ОАО \"Минскпроект\"',NULL,'РБ','Гродно','ул. Домбровского','15',NULL,'224098'),(14,'Юлия','Прокопец','Вячеславовна','1993-02-25','не замужем','Ж','РБ',NULL,'julia@mail.ru','ОАО \"Минсоблавтотранс\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'Сильвестр','Сталлоне','','1946-07-17','холост','М','США','','','Hollywood','silvi.jpg','США','Лос-Анджелес','ул. Жукова','15','20','1158891');
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
  `country_code` varchar(10) DEFAULT NULL,
  `operator_code` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) NOT NULL,
  `phone_type` enum('мобильный','домашний') NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`phone_id`),
  KEY `contact_phone_id_idx` (`contact_id`),
  CONSTRAINT `phone_contact` FOREIGN KEY (`contact_id`) REFERENCES `contacts` (`contact_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,1,'375','29','888-88-88','мобильный','круглосуточно'),(2,1,'444','33','544-99-00','домашний','Лос-Анджелес');
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

-- Dump completed on 2016-09-20  0:07:44
