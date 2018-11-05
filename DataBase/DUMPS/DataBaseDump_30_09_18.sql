-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: m153
-- ------------------------------------------------------
-- Server version	8.0.12

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
-- Table structure for table `tbl_address`
--

DROP TABLE IF EXISTS `tbl_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(45) NOT NULL,
  `tbl_city_fk` int(11) NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_id_UNIQUE` (`address_id`),
  KEY `fk_tbl_address_tbl_city1` (`tbl_city_fk`),
  CONSTRAINT `fk_tbl_address_tbl_city1` FOREIGN KEY (`tbl_city_fk`) REFERENCES `tbl_city` (`city_plz_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_address`
--

LOCK TABLES `tbl_address` WRITE;
/*!40000 ALTER TABLE `tbl_address` DISABLE KEYS */;
INSERT INTO `tbl_address` VALUES (1,'Dapibus Street',8006),(2,'1583 Enim Rd.',8014),(3,'5122 Tempor St.',8007),(4,'At Road',8015),(5,'Metus. Rd.',8006),(6,'Interdum Rd.',8006),(7,'Lobortis Rd.',8014),(8,'Velit Av.',8004),(9,'Ligula. Road',8018),(10,'Sit Avenue',8012),(11,'Ac Av.',8002),(12,'Amet, Rd.',8004),(13,'Tincidunt Road',8007),(14,'Risus. Av.',8013),(15,'Ante Street',8014),(16,'Curabitur Av.',8002),(17,'Sodales Rd.',8012),(18,'Neque. Ave',8017),(19,'Risus. St.',8011),(20,'Egestas. St.',8014),(21,'Magna. St.',8017),(22,'Integer Road',8018),(23,'Et Avenue',8009),(24,'Ligula. St.',8016),(25,'Sollicitudin Avenue',8005),(26,'Metus. Av.',8000),(27,'Nunc Rd.',8004),(28,'Inceptos Ave',8002),(29,'Ligula. St.',8015),(30,'Nunc Avenue',8015),(31,'Dolor Rd.',8013),(32,'Curabitur St.',8014),(33,'Massa. Rd.',8014),(34,'Laoreet Ave',8002),(35,'Scelerisque Street',8010),(36,'Nullam Av.',8009),(37,'Aenean Street',8019),(38,'Felis Road',8012),(39,'Urna. Road',8010),(40,'Tellus St.',8003),(41,'Sem. Road',8017),(42,'Risus. St.',8001),(43,'Elit, St.',8015),(44,'Euismod Rd.',8010),(45,'Accumsan Av.',8010),(46,'Non St.',8006),(47,'Libero Rd.',8005),(48,'Integer Avenue',8013),(49,'Hymenaeos. Av.',8011),(50,'Vitae, Road',8002),(51,'Et, Av.',8007),(52,'Tempus St.',8015),(53,'Orci Rd.',8014),(54,'Donec Ave',8013),(55,'Malesuada Rd.',8015),(56,'Id St.',8006),(57,'Augue, Road',8007),(58,'Placerat, St.',8016),(59,'Proin St.',8003),(60,'Ipsum Road',8004),(100,'King Street',9000);
/*!40000 ALTER TABLE `tbl_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_author`
--

DROP TABLE IF EXISTS `tbl_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_author` (
  `author_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_author`
--

LOCK TABLES `tbl_author` WRITE;
/*!40000 ALTER TABLE `tbl_author` DISABLE KEYS */;
INSERT INTO `tbl_author` VALUES (1,'Guinevere','Durham'),(2,'Destiny','Ortiz'),(3,'Urielle','Avery'),(4,'Velma','Stokes'),(5,'Austin','Finley'),(6,'Paloma','Cunningham'),(7,'Kimberly','William'),(8,'Marny','Kelly'),(9,'Nolan','Chaney'),(10,'Galvin','Hayes'),(11,'Donovan','Mccarty'),(12,'Priscilla','Joyce'),(13,'Bernard','Santos'),(14,'Halee','Meyer'),(15,'Eden','Oconnor'),(16,'Ocean','Lara'),(17,'Erich','Charles'),(18,'Dillon','Irwin'),(19,'Evan','Allen'),(20,'Tiger','Landry');
/*!40000 ALTER TABLE `tbl_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_book`
--

DROP TABLE IF EXISTS `tbl_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` mediumtext,
  `releaseDate` date NOT NULL,
  `price_day` varchar(20) NOT NULL,
  `price_replace` varchar(20) NOT NULL,
  `rating` int(11) DEFAULT NULL,
  `tbl_publisher_fk` int(11) NOT NULL,
  `tbl_author_fk` int(10) unsigned NOT NULL,
  `tbl_language_fk` int(11) NOT NULL,
  `tbl_category_fk` int(11) NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `idtbl_book_UNIQUE` (`book_id`),
  KEY `fk_tbl_book_tbl_publisher1` (`tbl_publisher_fk`),
  KEY `fk_tbl_book_tbl_author1` (`tbl_author_fk`),
  KEY `fk_tbl_book_tbl_language1` (`tbl_language_fk`),
  KEY `fk_tbl_book_tbl_category1` (`tbl_category_fk`),
  CONSTRAINT `fk_tbl_book_tbl_author1` FOREIGN KEY (`tbl_author_fk`) REFERENCES `tbl_author` (`author_id`),
  CONSTRAINT `fk_tbl_book_tbl_category1` FOREIGN KEY (`tbl_category_fk`) REFERENCES `tbl_category` (`category_id`),
  CONSTRAINT `fk_tbl_book_tbl_language1` FOREIGN KEY (`tbl_language_fk`) REFERENCES `tbl_language` (`language_id`),
  CONSTRAINT `fk_tbl_book_tbl_publisher1` FOREIGN KEY (`tbl_publisher_fk`) REFERENCES `tbl_publisher` (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_book`
--

LOCK TABLES `tbl_book` WRITE;
/*!40000 ALTER TABLE `tbl_book` DISABLE KEYS */;
INSERT INTO `tbl_book` VALUES (1,'BOOK Cruz Underwood',NULL,'2013-11-21','10.22 CHF','103.33 CHF',7,9,13,1,4),(2,'BOOK Simone Reeves',NULL,'2010-01-18','10.26 CHF','85.89 CHF',1,8,16,5,9),(3,'BOOK Hamilton Stokes',NULL,'2005-02-23','24.43 CHF','70.15 CHF',8,5,7,4,4),(4,'BOOK Selma Mccarty',NULL,'2009-07-11','2.32 CHF','132.48 CHF',7,3,3,1,9),(5,'BOOK Cameron Salinas',NULL,'2003-04-13','2.60 CHF','83.45 CHF',10,7,14,1,1),(6,'BOOK Dale Prince',NULL,'2008-07-17','4.25 CHF','128.02 CHF',5,7,2,2,9),(7,'BOOK Ariana Reynolds',NULL,'2014-05-17','17.90 CHF','32.95 CHF',9,9,13,3,7),(8,'BOOK Bo David',NULL,'2017-01-16','12.35 CHF','222.38 CHF',2,1,1,3,5),(9,'BOOK Austin Sherman',NULL,'2005-09-20','13.59 CHF','52.28 CHF',9,3,17,3,7),(10,'BOOK Jesse Ayala',NULL,'2005-11-01','26.99 CHF','168.44 CHF',7,7,9,1,3),(11,'BOOK Hanae Wong',NULL,'2011-03-07','5.61 CHF','37.63 CHF',2,3,1,1,8),(12,'BOOK Quin Chandler',NULL,'2018-12-06','3.16 CHF','179.57 CHF',1,7,13,1,10),(13,'BOOK Macaulay Mack',NULL,'2017-09-23','23.50 CHF','241.30 CHF',10,7,13,4,8),(14,'BOOK Cody Keith',NULL,'2010-05-23','14.15 CHF','213.47 CHF',1,8,9,1,10),(15,'BOOK Scarlet Griffith',NULL,'2010-06-21','15.56 CHF','211.01 CHF',4,5,14,1,3),(16,'BOOK Noah Bridges',NULL,'2012-02-03','6.14 CHF','125.92 CHF',9,1,20,1,5),(17,'BOOK Nissim Macias',NULL,'2013-08-25','29.27 CHF','145.56 CHF',3,1,15,3,6),(18,'BOOK Sandra Huffman',NULL,'2002-04-03','27.20 CHF','81.82 CHF',3,7,10,5,9),(19,'BOOK Zelenia Bean',NULL,'2018-11-30','16.11 CHF','210.36 CHF',6,9,5,4,9),(20,'BOOK Hayley Mckee',NULL,'2001-04-21','21.05 CHF','35.55 CHF',7,6,17,3,4),(21,'BOOK Conan Campos',NULL,'2006-11-05','29.35 CHF','155.93 CHF',7,5,19,1,9),(22,'BOOK Jena Bryant',NULL,'2015-12-03','4.53 CHF','44.53 CHF',8,3,20,5,6),(23,'BOOK Peter Santiago',NULL,'2012-10-17','4.48 CHF','144.82 CHF',10,1,4,5,4),(24,'BOOK Kelsey Jackson',NULL,'2014-03-07','6.31 CHF','219.03 CHF',3,6,1,5,10),(25,'BOOK Logan Blevins',NULL,'2012-09-05','12.87 CHF','62.81 CHF',10,9,17,5,3),(26,'BOOK Stewart Mclaughlin',NULL,'2009-09-08','22.41 CHF','223.62 CHF',9,9,11,2,1),(27,'BOOK Zeph Griffith',NULL,'2011-07-08','14.29 CHF','174.81 CHF',4,2,17,3,6),(28,'BOOK Elliott Salas',NULL,'2004-05-06','26.78 CHF','76.79 CHF',3,9,14,1,8),(29,'BOOK Patrick Gardner',NULL,'2013-10-12','15.59 CHF','84.40 CHF',7,6,3,5,2),(30,'BOOK Remedios Sexton',NULL,'2018-12-01','3.00 CHF','52.25 CHF',3,2,17,5,2),(31,'BOOK Dieter Cain',NULL,'2012-06-28','7.43 CHF','173.82 CHF',9,8,13,4,2),(32,'BOOK Jeanette Duke',NULL,'2008-04-04','9.62 CHF','34.99 CHF',4,4,11,1,9),(33,'BOOK Savannah Giles',NULL,'2008-09-25','21.29 CHF','228.17 CHF',3,6,6,5,7),(34,'BOOK Erich Spence',NULL,'2014-12-24','24.90 CHF','120.06 CHF',1,2,18,1,9),(35,'BOOK Aurora Mann',NULL,'2013-04-14','19.25 CHF','161.94 CHF',1,1,10,1,3),(36,'BOOK Stuart Frazier',NULL,'2013-11-28','10.49 CHF','164.10 CHF',2,1,5,3,6),(37,'BOOK Chastity James',NULL,'2017-04-18','3.51 CHF','240.00 CHF',2,4,11,3,8),(38,'BOOK Lilah Nichols',NULL,'2016-08-14','28.51 CHF','185.28 CHF',1,10,19,5,9),(39,'BOOK Jason Rowe',NULL,'2005-02-10','15.28 CHF','86.50 CHF',3,1,12,1,2),(40,'BOOK Clarke Shaffer',NULL,'2009-06-19','10.26 CHF','92.45 CHF',2,6,3,2,10),(41,'BOOK Gemma Michael',NULL,'2001-05-24','15.33 CHF','47.16 CHF',8,10,2,2,1),(42,'BOOK Myra Russo',NULL,'2010-11-06','19.79 CHF','40.87 CHF',8,7,12,3,2),(43,'BOOK Keely Morrison',NULL,'2001-10-05','16.77 CHF','59.55 CHF',7,4,14,4,3),(44,'BOOK Timon Bates',NULL,'2009-10-13','25.15 CHF','241.99 CHF',3,2,16,5,6),(45,'BOOK Dale Stewart',NULL,'2008-05-29','27.27 CHF','227.51 CHF',3,6,1,2,10),(46,'BOOK Desiree Hardy',NULL,'2008-04-29','13.99 CHF','174.33 CHF',10,5,16,4,1),(47,'BOOK Chastity Page',NULL,'2015-05-29','18.85 CHF','97.47 CHF',6,2,1,1,5),(48,'BOOK Isabelle Curtis',NULL,'2002-10-26','25.15 CHF','220.70 CHF',8,2,6,5,10),(49,'BOOK Zenaida Harrell',NULL,'2008-07-21','28.88 CHF','44.23 CHF',8,7,5,2,2),(50,'BOOK Cecilia Spence',NULL,'2010-05-06','23.96 CHF','173.99 CHF',7,4,12,3,2),(51,'BOOK Noel Pacheco',NULL,'2019-03-15','5.11 CHF','125.20 CHF',1,8,15,4,6),(52,'BOOK Amy Matthews',NULL,'2010-06-19','23.49 CHF','154.80 CHF',8,7,17,4,9),(53,'BOOK Amir Burke',NULL,'2001-03-24','11.48 CHF','74.15 CHF',5,6,17,4,10),(54,'BOOK Erich Dennis',NULL,'2010-01-13','29.26 CHF','106.26 CHF',3,2,7,3,3),(55,'BOOK Helen Horne',NULL,'2010-03-30','28.05 CHF','171.25 CHF',5,8,2,4,5),(56,'BOOK Stephen Randall',NULL,'2017-02-26','5.14 CHF','138.67 CHF',2,4,2,5,8),(57,'BOOK Prescott Benson',NULL,'2013-04-24','27.74 CHF','197.34 CHF',7,10,11,1,7),(58,'BOOK Tatum Hardy',NULL,'2017-07-01','2.49 CHF','43.74 CHF',8,3,3,5,6),(59,'BOOK Regan Ortiz',NULL,'2019-05-25','1.31 CHF','245.53 CHF',2,4,10,2,1),(60,'BOOK Nigel Stafford',NULL,'2016-11-21','1.78 CHF','85.37 CHF',9,2,14,3,1),(61,'BOOK Justin Henderson',NULL,'2019-03-30','10.07 CHF','159.14 CHF',4,7,14,1,10),(62,'BOOK Stacy Neal',NULL,'2007-11-27','28.91 CHF','100.77 CHF',5,6,6,2,5),(63,'BOOK Merrill Jacobs',NULL,'2009-08-22','12.61 CHF','124.64 CHF',6,7,15,5,7),(64,'BOOK Halla Russo',NULL,'2015-02-21','20.93 CHF','116.66 CHF',6,2,15,1,9),(65,'BOOK Candace Duffy',NULL,'2010-07-24','15.24 CHF','163.98 CHF',3,9,10,4,3),(66,'BOOK Jana Forbes',NULL,'2017-06-23','14.18 CHF','78.95 CHF',2,8,12,1,3),(67,'BOOK Gil Chaney',NULL,'2005-03-14','17.31 CHF','191.03 CHF',9,10,19,4,3),(68,'BOOK Ann Wright',NULL,'2018-10-01','17.91 CHF','140.07 CHF',6,1,6,2,5),(69,'BOOK Leroy Griffin',NULL,'2004-02-29','18.26 CHF','233.12 CHF',3,9,16,1,5),(70,'BOOK Abra Whitfield',NULL,'2019-07-19','24.34 CHF','190.39 CHF',4,10,4,2,7),(71,'BOOK Camilla Franks',NULL,'2001-07-10','21.01 CHF','146.17 CHF',4,3,12,5,8),(72,'BOOK Kaitlin Benson',NULL,'2016-12-29','3.24 CHF','71.95 CHF',4,4,10,4,9),(73,'BOOK Aurora Carroll',NULL,'2001-10-03','28.09 CHF','144.26 CHF',4,6,7,3,3),(74,'BOOK Sacha Velazquez',NULL,'2001-10-24','28.12 CHF','244.09 CHF',9,1,12,4,5),(75,'BOOK Cullen Evans',NULL,'2002-01-31','5.70 CHF','221.00 CHF',3,7,5,5,8),(76,'BOOK Myra Gregory',NULL,'2012-11-23','18.88 CHF','47.43 CHF',4,3,20,4,2),(77,'BOOK Kiayada Franks',NULL,'2002-10-21','5.98 CHF','209.32 CHF',5,4,1,3,4),(78,'BOOK Caesar Gonzalez',NULL,'2007-11-21','22.00 CHF','133.82 CHF',8,3,7,1,3),(79,'BOOK Hu Dixon',NULL,'2015-05-07','16.42 CHF','170.39 CHF',9,3,8,5,5),(80,'BOOK Devin Baird',NULL,'2016-06-10','13.47 CHF','176.20 CHF',1,4,16,1,10),(81,'BOOK Xander Maxwell',NULL,'2012-01-04','8.84 CHF','50.28 CHF',10,5,10,2,10),(82,'BOOK Tyrone Farley',NULL,'2013-09-27','1.15 CHF','211.04 CHF',5,6,12,2,1),(83,'BOOK Cruz Mueller',NULL,'2006-11-30','27.17 CHF','65.93 CHF',3,10,10,4,5),(84,'BOOK Lila Oconnor',NULL,'2012-10-20','22.04 CHF','117.71 CHF',6,8,15,5,4),(85,'BOOK Craig Giles',NULL,'2017-04-25','3.21 CHF','201.16 CHF',10,10,6,5,4),(86,'BOOK Amir Cummings',NULL,'2011-10-05','19.78 CHF','185.83 CHF',4,8,4,4,3),(87,'BOOK Latifah Hughes',NULL,'2005-06-01','21.98 CHF','94.44 CHF',2,8,19,2,6),(88,'BOOK Heidi Mercado',NULL,'2014-10-30','14.05 CHF','179.43 CHF',7,5,6,2,9),(89,'BOOK Winter Pacheco',NULL,'2013-06-18','10.09 CHF','163.33 CHF',1,6,17,3,7),(90,'BOOK Marny Dickson',NULL,'2006-11-18','14.59 CHF','73.30 CHF',10,5,12,2,6),(91,'BOOK Tana Howell',NULL,'2011-10-12','4.76 CHF','163.18 CHF',4,4,18,3,8),(92,'BOOK Pamela Crawford',NULL,'2017-01-21','25.87 CHF','100.60 CHF',1,10,14,1,9),(93,'BOOK Dante Stafford',NULL,'2005-11-08','24.28 CHF','145.98 CHF',1,5,8,2,6),(94,'BOOK Ina Melendez',NULL,'2013-11-04','22.86 CHF','158.92 CHF',10,3,4,2,10),(95,'BOOK Candice Murray',NULL,'2013-06-01','4.92 CHF','164.88 CHF',3,3,2,4,5),(96,'BOOK Sean Melendez',NULL,'2013-12-01','21.77 CHF','218.86 CHF',7,3,1,4,4),(97,'BOOK Denise Mercer',NULL,'2007-12-03','26.81 CHF','119.20 CHF',9,5,16,5,5),(98,'BOOK Herrod May',NULL,'2014-09-12','13.18 CHF','95.43 CHF',7,10,3,5,6),(99,'BOOK Brady King',NULL,'2012-02-25','3.69 CHF','167.07 CHF',4,7,5,4,6),(100,'BOOK Violet Mullins',NULL,'2010-08-10','16.69 CHF','231.46 CHF',9,7,20,2,9),(101,'BOOK Salvador Taylor',NULL,'2002-05-06','6.32 CHF','64.75 CHF',10,2,10,3,4),(102,'BOOK Quin Terrell',NULL,'2017-01-21','15.29 CHF','183.51 CHF',6,2,2,5,1),(103,'BOOK Suki Warner',NULL,'2007-05-22','28.06 CHF','191.22 CHF',7,4,3,1,3),(104,'BOOK Brent Simpson',NULL,'2002-09-16','23.85 CHF','48.16 CHF',8,8,11,4,2),(105,'BOOK Shoshana Cook',NULL,'2003-05-01','26.01 CHF','191.36 CHF',3,10,8,3,10),(106,'BOOK Taylor Valenzuela',NULL,'2007-12-11','19.55 CHF','160.10 CHF',10,2,16,4,9),(107,'BOOK Christen Boyd',NULL,'2006-03-01','2.64 CHF','47.69 CHF',3,3,19,2,7),(108,'BOOK Madonna Stanton',NULL,'2001-10-30','3.36 CHF','114.60 CHF',10,7,1,3,1),(109,'BOOK Nell Barker',NULL,'2015-09-21','2.74 CHF','37.46 CHF',5,8,18,4,3),(110,'BOOK Rudyard Rowe',NULL,'2007-01-28','2.88 CHF','82.66 CHF',5,2,17,1,5),(111,'BOOK Elliott Melendez',NULL,'2005-06-14','15.86 CHF','114.15 CHF',5,3,9,5,3),(112,'BOOK Bert Dale',NULL,'2019-05-23','4.76 CHF','232.93 CHF',5,3,20,3,8),(113,'BOOK Mercedes Mcdaniel',NULL,'2009-01-15','19.06 CHF','104.16 CHF',10,2,9,2,6),(114,'BOOK Sarah Stevens',NULL,'2008-06-06','29.42 CHF','147.35 CHF',4,1,1,4,4),(115,'BOOK Hasad Mccarty',NULL,'2001-11-04','11.40 CHF','233.41 CHF',3,4,12,1,5),(116,'BOOK Sarah Morales',NULL,'2007-08-25','10.65 CHF','41.21 CHF',5,10,12,4,3),(117,'BOOK Barclay Fernandez',NULL,'2007-06-06','22.49 CHF','234.02 CHF',2,9,14,4,5),(118,'BOOK Dakota Spears',NULL,'2008-08-25','1.36 CHF','34.53 CHF',4,1,9,5,8),(119,'BOOK Sean Moreno',NULL,'2005-11-17','5.12 CHF','144.83 CHF',6,7,1,1,2),(120,'BOOK Kuame Francis',NULL,'2004-10-16','3.47 CHF','114.04 CHF',3,1,19,1,9),(121,'BOOK Violet Livingston',NULL,'2018-10-09','15.89 CHF','77.02 CHF',7,9,17,3,9),(122,'BOOK Suki Walter',NULL,'2006-03-21','6.68 CHF','49.91 CHF',3,9,1,3,5),(123,'BOOK Dustin Wiggins',NULL,'2003-06-13','17.14 CHF','225.93 CHF',9,2,12,4,1),(124,'BOOK Ebony Cain',NULL,'2002-11-05','19.75 CHF','99.77 CHF',5,9,9,1,8),(125,'BOOK Myles Mcguire',NULL,'2001-02-06','11.78 CHF','232.33 CHF',10,4,10,2,3),(126,'BOOK Lillian Phillips',NULL,'2014-10-06','10.13 CHF','73.18 CHF',2,4,7,5,9),(127,'BOOK Sybil Roy',NULL,'2007-06-23','25.19 CHF','237.75 CHF',4,6,2,1,8),(128,'BOOK Noble Hoffman',NULL,'2014-11-21','23.77 CHF','146.38 CHF',10,6,3,1,7),(129,'BOOK Kuame Lancaster',NULL,'2017-08-13','14.47 CHF','114.98 CHF',5,8,8,2,1),(130,'BOOK Jael Vasquez',NULL,'2005-11-28','13.86 CHF','54.96 CHF',5,9,19,5,7),(131,'BOOK Cora Burns',NULL,'2019-01-19','16.50 CHF','187.80 CHF',4,10,12,3,5),(132,'BOOK Kibo Livingston',NULL,'2017-12-01','12.61 CHF','239.71 CHF',1,2,17,3,3),(133,'BOOK Travis Kirkland',NULL,'2010-10-30','9.78 CHF','93.59 CHF',5,8,4,4,4),(134,'BOOK Marsden Kerr',NULL,'2011-03-12','5.84 CHF','166.72 CHF',5,2,4,3,7),(135,'BOOK Joshua Casey',NULL,'2014-10-28','27.97 CHF','183.93 CHF',8,3,5,2,6),(136,'BOOK Karleigh Jennings',NULL,'2009-09-22','11.79 CHF','52.80 CHF',10,3,17,4,1),(137,'BOOK Shay Hansen',NULL,'2009-07-31','14.78 CHF','60.45 CHF',2,10,11,2,8),(138,'BOOK Kermit Powers',NULL,'2016-02-13','17.04 CHF','116.82 CHF',5,5,5,2,10),(139,'BOOK Brock Hayden',NULL,'2007-04-05','17.41 CHF','139.64 CHF',9,7,20,4,10),(140,'BOOK Joy Malone',NULL,'2010-05-30','19.11 CHF','215.20 CHF',1,2,14,2,9),(141,'BOOK Jermaine Weiss',NULL,'2008-11-16','10.69 CHF','163.87 CHF',2,6,14,5,10),(142,'BOOK Roanna Hogan',NULL,'2014-11-05','13.77 CHF','104.29 CHF',6,5,10,4,4),(143,'BOOK Daryl Roberson',NULL,'2017-12-23','3.14 CHF','225.23 CHF',4,10,3,4,7),(144,'BOOK Garth Carey',NULL,'2012-09-30','25.12 CHF','127.72 CHF',1,6,15,1,10),(145,'BOOK Morgan Rivera',NULL,'2007-06-15','18.53 CHF','57.66 CHF',6,1,1,1,8),(146,'BOOK Jasper Goodman',NULL,'2017-11-09','3.54 CHF','162.09 CHF',4,9,18,4,4),(147,'BOOK Alexa Castro',NULL,'2019-06-11','9.26 CHF','218.67 CHF',2,7,2,1,9),(148,'BOOK Chaney Knowles',NULL,'2015-09-23','26.67 CHF','62.31 CHF',8,4,12,2,1),(149,'BOOK Sara Hopkins',NULL,'2015-09-07','4.28 CHF','180.52 CHF',7,1,20,1,9),(150,'BOOK Amos Britt',NULL,'2015-01-19','21.78 CHF','204.33 CHF',7,2,15,2,8),(151,'BOOK Abdul Harris',NULL,'2016-10-22','29.86 CHF','79.88 CHF',2,2,2,3,5),(152,'BOOK Barbara Dejesus',NULL,'2018-12-08','25.57 CHF','161.76 CHF',6,7,3,5,9),(153,'BOOK Sopoline Perry',NULL,'2019-04-20','11.75 CHF','143.42 CHF',1,3,15,3,1),(154,'BOOK Griffith Chavez',NULL,'2008-05-26','10.08 CHF','181.66 CHF',9,10,14,3,8),(155,'BOOK Henry Rutledge',NULL,'2014-12-05','28.92 CHF','214.51 CHF',2,10,16,3,8),(156,'BOOK Roary Winters',NULL,'2002-05-16','13.17 CHF','152.82 CHF',8,3,4,1,2),(157,'BOOK Nash Camacho',NULL,'2003-09-24','16.63 CHF','47.42 CHF',10,2,5,3,1),(158,'BOOK Macey Riggs',NULL,'2015-09-21','13.67 CHF','85.30 CHF',3,1,1,1,1),(159,'BOOK Reece Weeks',NULL,'2016-08-19','10.91 CHF','153.65 CHF',6,4,9,1,6),(160,'BOOK Alvin Morin',NULL,'2017-01-31','26.31 CHF','120.96 CHF',4,7,12,4,4),(161,'BOOK Yvette Chavez',NULL,'2018-11-19','19.88 CHF','87.23 CHF',2,8,17,5,10),(162,'BOOK Heidi Hayden',NULL,'2004-09-21','7.82 CHF','164.73 CHF',4,3,17,5,9),(163,'BOOK Josiah Cantrell',NULL,'2003-05-23','16.75 CHF','101.41 CHF',9,6,9,2,5),(164,'BOOK Anika Rogers',NULL,'2004-02-10','10.28 CHF','107.62 CHF',4,10,7,5,7),(165,'BOOK Whoopi Christensen',NULL,'2004-08-05','20.15 CHF','72.22 CHF',3,10,9,2,6),(166,'BOOK Alvin Christian',NULL,'2017-03-28','10.23 CHF','102.03 CHF',3,6,17,1,2),(167,'BOOK Knox Thomas',NULL,'2019-03-04','24.42 CHF','223.41 CHF',6,8,16,1,7),(168,'BOOK Aquila Gonzales',NULL,'2005-02-22','27.10 CHF','37.10 CHF',7,6,2,4,1),(169,'BOOK Taylor Robertson',NULL,'2016-11-10','15.34 CHF','96.78 CHF',8,10,11,5,4),(170,'BOOK Aline Greene',NULL,'2019-01-23','13.38 CHF','228.36 CHF',3,10,6,2,9),(171,'BOOK Haley Cote',NULL,'2015-02-17','9.00 CHF','64.14 CHF',2,3,20,4,5),(172,'BOOK Victor Dudley',NULL,'2003-05-18','25.89 CHF','226.82 CHF',6,4,16,2,6),(173,'BOOK Jerome Vasquez',NULL,'2007-12-17','4.92 CHF','87.41 CHF',4,6,4,5,2),(174,'BOOK Katelyn Yates',NULL,'2003-01-21','21.38 CHF','215.46 CHF',4,8,17,2,4),(175,'BOOK Bevis Dickson',NULL,'2006-11-18','2.52 CHF','119.84 CHF',3,4,8,5,3),(176,'BOOK Deirdre Swanson',NULL,'2007-03-23','24.19 CHF','166.16 CHF',10,9,13,5,6),(177,'BOOK Daquan Hutchinson',NULL,'2003-02-21','15.29 CHF','81.35 CHF',8,3,3,4,2),(178,'BOOK Lucian Cleveland',NULL,'2010-12-15','14.11 CHF','202.43 CHF',2,6,13,4,1),(179,'BOOK Jelani Moran',NULL,'2003-09-17','24.21 CHF','47.22 CHF',5,10,1,5,3),(180,'BOOK Jack Randall',NULL,'2004-10-11','15.77 CHF','114.14 CHF',5,3,10,3,7),(181,'BOOK Bethany Guy',NULL,'2006-07-07','16.20 CHF','171.06 CHF',10,2,19,1,3),(182,'BOOK Gisela Duffy',NULL,'2004-12-23','26.46 CHF','77.56 CHF',2,3,20,1,4),(183,'BOOK Lydia Oliver',NULL,'2014-01-12','17.25 CHF','62.12 CHF',8,6,14,2,5),(184,'BOOK Dominic Dyer',NULL,'2016-11-26','24.66 CHF','233.22 CHF',7,4,9,5,4),(185,'BOOK Kyla Boyer',NULL,'2016-02-26','15.21 CHF','171.26 CHF',10,7,4,4,6),(186,'BOOK Dennis Rivas',NULL,'2015-12-04','17.02 CHF','142.44 CHF',6,8,16,3,2),(187,'BOOK Stephen Abbott',NULL,'2004-09-08','14.97 CHF','70.22 CHF',5,3,14,3,7),(188,'BOOK Lisandra Bonner',NULL,'2004-06-20','25.45 CHF','207.56 CHF',10,6,5,4,6),(189,'BOOK Iona Cunningham',NULL,'2001-06-23','5.12 CHF','102.84 CHF',4,2,3,1,9),(190,'BOOK Ivy Horton',NULL,'2008-02-26','17.06 CHF','33.31 CHF',8,5,10,2,9),(191,'BOOK Maggie Hyde',NULL,'2011-07-18','15.43 CHF','39.07 CHF',10,9,15,1,5),(192,'BOOK Britanney Pierce',NULL,'2004-03-12','24.45 CHF','86.18 CHF',10,10,11,3,8),(193,'BOOK Chantale Wynn',NULL,'2010-08-13','2.26 CHF','157.45 CHF',6,4,4,1,3),(194,'BOOK Malcolm Lopez',NULL,'2015-07-21','18.58 CHF','172.41 CHF',3,6,4,3,5),(195,'BOOK Chancellor Phillips',NULL,'2008-06-08','13.74 CHF','94.81 CHF',6,4,2,4,4),(196,'BOOK Dorian Velez',NULL,'2008-11-13','29.89 CHF','247.66 CHF',2,10,10,1,2),(197,'BOOK Oleg Foreman',NULL,'2018-06-07','2.69 CHF','62.63 CHF',5,1,8,3,1),(198,'BOOK Lenore Meyer',NULL,'2011-07-18','3.45 CHF','108.12 CHF',9,8,13,1,10),(199,'BOOK Quon Dickerson',NULL,'2014-12-17','1.69 CHF','131.16 CHF',8,1,16,4,7),(200,'BOOK Ebony Hernandez',NULL,'2011-09-04','8.27 CHF','168.24 CHF',10,9,19,2,10);
/*!40000 ALTER TABLE `tbl_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_category`
--

DROP TABLE IF EXISTS `tbl_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `cat` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_id_UNIQUE` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_category`
--

LOCK TABLES `tbl_category` WRITE;
/*!40000 ALTER TABLE `tbl_category` DISABLE KEYS */;
INSERT INTO `tbl_category` VALUES (1,'Sience fiction'),(2,'Satire'),(3,'Drama'),(4,'Action'),(5,'Adventure'),(6,'Romance'),(7,'Mystery'),(8,'Horror'),(9,'Self Help'),(10,'UNDEFINED?');
/*!40000 ALTER TABLE `tbl_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_city`
--

DROP TABLE IF EXISTS `tbl_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_city` (
  `city_plz_id` int(11) NOT NULL,
  `cityName` varchar(45) NOT NULL,
  `tbl_country_fk` int(11) NOT NULL,
  PRIMARY KEY (`city_plz_id`),
  UNIQUE KEY `city_plz_id_UNIQUE` (`city_plz_id`),
  KEY `fk_tbl_city_tbl_country1` (`tbl_country_fk`),
  CONSTRAINT `fk_tbl_city_tbl_country1` FOREIGN KEY (`tbl_country_fk`) REFERENCES `tbl_country` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_city`
--

LOCK TABLES `tbl_city` WRITE;
/*!40000 ALTER TABLE `tbl_city` DISABLE KEYS */;
INSERT INTO `tbl_city` VALUES (8000,'Great Falls',2),(8001,'Valladolid',3),(8002,'Lang',1),(8003,'Deschambault',2),(8004,'Algeciras',2),(8005,'Erlangen',1),(8006,'Södertälje',3),(8007,'Offenbach am Main',1),(8008,'York',2),(8009,'Freux',2),(8010,'Gandhidham',2),(8011,'Leffinge',2),(8012,'Nodebais',1),(8013,'Perchtoldsdorf',2),(8014,'Tumbler Ridge',1),(8015,'Zele',2),(8016,'Tauranga',1),(8017,'Sierra Gorda',3),(8018,'Langford',1),(8019,'Belsele',3),(9000,'Zurich',3);
/*!40000 ALTER TABLE `tbl_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_country`
--

DROP TABLE IF EXISTS `tbl_country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `countryName` varchar(45) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_id_UNIQUE` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_country`
--

LOCK TABLES `tbl_country` WRITE;
/*!40000 ALTER TABLE `tbl_country` DISABLE KEYS */;
INSERT INTO `tbl_country` VALUES (1,'New Zealand'),(2,'Korea, North'),(3,'Switzerland');
/*!40000 ALTER TABLE `tbl_country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_customer`
--

DROP TABLE IF EXISTS `tbl_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_customer` (
  `tbl_person_fk` int(11) NOT NULL,
  `since` date NOT NULL,
  PRIMARY KEY (`tbl_person_fk`),
  UNIQUE KEY `tbl_person_fk_UNIQUE` (`tbl_person_fk`),
  CONSTRAINT `fk_customer_tbl_person1` FOREIGN KEY (`tbl_person_fk`) REFERENCES `tbl_person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_customer`
--

LOCK TABLES `tbl_customer` WRITE;
/*!40000 ALTER TABLE `tbl_customer` DISABLE KEYS */;
INSERT INTO `tbl_customer` VALUES (1,'2013-11-21'),(2,'2010-08-17'),(3,'2006-10-20'),(4,'2014-06-09'),(5,'2017-11-03'),(6,'2016-06-25'),(7,'2003-03-19'),(8,'2017-01-29'),(9,'2017-04-15'),(10,'2004-09-11'),(11,'2006-05-27'),(12,'2012-08-25'),(13,'2010-11-18'),(14,'2016-10-10'),(15,'2001-10-23'),(16,'2007-03-17'),(17,'2018-08-10'),(18,'2018-07-20'),(19,'2015-08-15'),(20,'2019-07-19'),(21,'2016-08-04'),(22,'2012-12-24'),(23,'2010-07-31'),(24,'2017-07-06'),(25,'2019-04-01'),(26,'2010-08-14'),(27,'2014-02-25'),(28,'2001-04-01'),(29,'2018-05-19'),(30,'2006-11-28'),(31,'2006-03-21'),(32,'2010-07-16'),(33,'2010-06-03'),(34,'2004-03-20'),(35,'2018-02-18'),(36,'2002-07-18'),(37,'2007-12-31'),(38,'2010-08-27'),(39,'2003-01-23'),(40,'2001-07-06'),(41,'2007-09-05'),(42,'2011-04-23'),(43,'2017-01-13'),(44,'2012-09-07'),(45,'2002-02-14'),(46,'2014-01-21'),(47,'2001-06-20'),(48,'2003-02-24'),(49,'2002-04-21'),(50,'2013-03-06'),(51,'2006-02-03'),(52,'2019-07-01'),(53,'2010-04-22'),(54,'2006-07-08'),(55,'2019-06-26'),(56,'2009-11-25'),(57,'2014-05-04'),(58,'2004-01-03'),(59,'2009-12-18'),(60,'2002-07-20'),(61,'2009-02-17'),(62,'2013-08-27'),(63,'2001-03-14'),(64,'2011-08-10'),(65,'2019-02-04'),(66,'2016-02-04'),(67,'2018-09-21'),(68,'2003-11-19'),(69,'2004-08-27'),(70,'2014-10-31'),(71,'2001-02-19'),(72,'2004-01-23'),(73,'2001-06-04'),(74,'2017-05-12'),(75,'2007-09-09'),(76,'2007-06-12'),(77,'2004-02-27'),(78,'2001-11-24'),(79,'2006-11-24'),(80,'2017-08-27');
/*!40000 ALTER TABLE `tbl_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_inventory`
--

DROP TABLE IF EXISTS `tbl_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_inventory` (
  `inventory_id` int(11) NOT NULL AUTO_INCREMENT,
  `tbl_store_fk` int(11) NOT NULL,
  `tbl_book_fk` int(11) NOT NULL,
  PRIMARY KEY (`inventory_id`),
  UNIQUE KEY `idtbl_inventory_UNIQUE` (`inventory_id`),
  KEY `fk_tbl_inventory_tbl_store1` (`tbl_store_fk`),
  KEY `fk_tbl_inventory_tbl_book1` (`tbl_book_fk`),
  CONSTRAINT `fk_tbl_inventory_tbl_book1` FOREIGN KEY (`tbl_book_fk`) REFERENCES `tbl_book` (`book_id`),
  CONSTRAINT `fk_tbl_inventory_tbl_store1` FOREIGN KEY (`tbl_store_fk`) REFERENCES `tbl_store` (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=400 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_inventory`
--

LOCK TABLES `tbl_inventory` WRITE;
/*!40000 ALTER TABLE `tbl_inventory` DISABLE KEYS */;
INSERT INTO `tbl_inventory` VALUES (1,2,37),(2,3,32),(3,1,45),(4,2,193),(5,1,182),(6,2,63),(7,1,24),(8,2,153),(9,1,48),(10,3,159),(11,1,190),(12,1,117),(13,1,57),(14,1,142),(15,1,32),(16,3,192),(17,2,35),(18,1,96),(19,3,82),(20,3,132),(21,3,200),(22,2,94),(23,3,98),(24,1,164),(25,3,59),(26,1,81),(27,3,21),(28,1,171),(29,2,166),(30,2,186),(31,1,49),(32,1,84),(33,1,24),(34,2,82),(35,2,56),(36,1,192),(37,2,63),(38,1,157),(39,1,113),(40,2,169),(41,3,63),(42,1,62),(43,2,174),(44,3,132),(45,3,51),(46,3,146),(47,1,150),(48,3,167),(49,2,88),(50,3,149),(51,1,113),(52,3,48),(53,3,108),(54,1,116),(55,1,49),(56,1,120),(57,3,132),(58,3,36),(59,3,149),(60,2,74),(61,1,94),(62,2,79),(63,2,27),(64,3,86),(65,2,12),(66,1,130),(67,1,134),(68,2,81),(69,2,183),(70,2,138),(71,2,133),(72,3,78),(73,3,110),(74,3,160),(75,2,5),(76,1,103),(77,2,32),(78,1,147),(79,2,132),(80,1,27),(81,2,83),(82,2,197),(83,2,144),(84,3,29),(85,3,149),(86,3,105),(87,2,172),(88,3,91),(89,2,168),(90,1,200),(91,1,153),(92,2,130),(93,1,189),(94,1,181),(95,1,51),(96,1,143),(97,3,8),(98,1,95),(99,2,174),(100,1,161),(101,3,176),(102,2,167),(103,3,197),(104,1,23),(105,3,57),(106,2,20),(107,2,173),(108,2,181),(109,1,157),(110,3,184),(111,1,94),(112,3,165),(113,1,80),(114,3,45),(115,1,24),(116,3,146),(117,1,65),(118,2,155),(119,2,48),(120,3,68),(121,2,92),(122,3,41),(123,1,85),(124,2,77),(125,3,116),(126,1,153),(127,1,13),(128,2,164),(129,3,129),(130,2,17),(131,2,59),(132,3,70),(133,1,44),(134,3,188),(135,1,57),(136,3,43),(137,2,67),(138,2,105),(139,3,35),(140,3,43),(141,1,149),(142,3,162),(143,1,35),(144,1,196),(145,1,2),(146,1,128),(147,2,158),(148,2,118),(149,1,93),(150,2,166),(151,2,90),(152,2,95),(153,3,196),(154,2,50),(155,3,191),(156,1,70),(157,1,92),(158,1,98),(159,1,12),(160,3,6),(161,2,177),(162,1,36),(163,3,32),(164,1,188),(165,1,193),(166,2,28),(167,1,186),(168,1,27),(169,3,31),(170,3,144),(171,3,179),(172,1,193),(173,3,98),(174,1,78),(175,3,93),(176,1,166),(177,3,56),(178,3,46),(179,2,194),(180,2,89),(181,1,132),(182,1,131),(183,1,170),(184,3,3),(185,3,126),(186,2,44),(187,3,90),(188,3,43),(189,1,177),(190,1,84),(191,2,32),(192,1,16),(193,2,193),(194,2,99),(195,2,142),(196,2,90),(197,1,167),(198,3,2),(199,1,160),(200,1,200),(201,1,94),(202,2,116),(203,3,86),(204,1,37),(205,3,99),(206,3,6),(207,1,76),(208,1,49),(209,2,75),(210,1,23),(211,1,150),(212,3,82),(213,2,102),(214,1,47),(215,3,189),(216,3,104),(217,1,41),(218,1,161),(219,2,187),(220,2,61),(221,1,129),(222,2,3),(223,1,73),(224,1,188),(225,2,21),(226,3,120),(227,1,177),(228,2,47),(229,1,39),(230,1,21),(231,2,114),(232,3,112),(233,2,26),(234,3,56),(235,1,130),(236,1,99),(237,2,93),(238,3,128),(239,1,168),(240,3,39),(241,2,66),(242,3,4),(243,3,196),(244,2,48),(245,2,107),(246,2,182),(247,3,8),(248,1,109),(249,2,115),(250,2,22),(251,3,165),(252,1,16),(253,1,34),(254,1,145),(255,2,51),(256,3,180),(257,2,43),(258,3,180),(259,3,6),(260,1,130),(261,1,158),(262,1,181),(263,2,5),(264,2,128),(265,3,106),(266,2,89),(267,1,85),(268,2,140),(269,1,35),(270,3,191),(271,2,74),(272,2,129),(273,2,102),(274,1,181),(275,3,68),(276,1,52),(277,2,75),(278,3,31),(279,2,64),(280,3,153),(281,1,147),(282,3,195),(283,2,192),(284,1,58),(285,1,62),(286,1,13),(287,2,75),(288,3,29),(289,1,91),(290,3,129),(291,3,14),(292,1,188),(293,1,145),(294,1,171),(295,3,58),(296,3,82),(297,3,138),(298,2,125),(299,1,39),(300,2,75),(301,1,22),(302,3,79),(303,3,63),(304,1,140),(305,2,50),(306,1,171),(307,2,164),(308,1,198),(309,1,129),(310,3,86),(311,1,187),(312,2,11),(313,3,68),(314,1,184),(315,2,17),(316,3,190),(317,1,128),(318,3,125),(319,3,82),(320,2,167),(321,1,40),(322,3,133),(323,2,125),(324,3,134),(325,2,144),(326,3,157),(327,1,39),(328,1,190),(329,1,200),(330,2,27),(331,3,20),(332,3,109),(333,2,117),(334,2,72),(335,2,104),(336,2,111),(337,2,1),(338,3,23),(339,1,99),(340,3,113),(341,2,49),(342,1,73),(343,2,47),(344,2,145),(345,1,24),(346,2,7),(347,2,124),(348,1,110),(349,3,174),(350,1,172),(351,1,87),(352,2,110),(353,2,38),(354,3,55),(355,2,179),(356,2,176),(357,2,126),(358,2,80),(359,2,65),(360,1,61),(361,2,53),(362,2,152),(363,3,144),(364,2,5),(365,2,186),(366,3,181),(367,1,48),(368,2,187),(369,2,115),(370,3,120),(371,2,41),(372,2,162),(373,1,180),(374,1,24),(375,3,133),(376,1,197),(377,1,141),(378,2,156),(379,2,161),(380,3,11),(381,2,150),(382,2,82),(383,2,170),(384,1,176),(385,1,64),(386,3,90),(387,1,113),(388,2,163),(389,3,38),(390,3,134),(391,1,124),(392,1,45),(393,3,109),(394,3,127),(395,3,115),(396,1,10),(397,2,129),(398,3,98),(399,1,112);
/*!40000 ALTER TABLE `tbl_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_language`
--

DROP TABLE IF EXISTS `tbl_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_language` (
  `language_id` int(11) NOT NULL AUTO_INCREMENT,
  `lang` varchar(45) NOT NULL,
  PRIMARY KEY (`language_id`),
  UNIQUE KEY `language_id_UNIQUE` (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_language`
--

LOCK TABLES `tbl_language` WRITE;
/*!40000 ALTER TABLE `tbl_language` DISABLE KEYS */;
INSERT INTO `tbl_language` VALUES (1,'German'),(2,'English'),(3,'Italian'),(4,'French'),(5,'Greek');
/*!40000 ALTER TABLE `tbl_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_listofrentalbooks`
--

DROP TABLE IF EXISTS `tbl_listofrentalbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_listofrentalbooks` (
  `tbl_inventory_fk` int(11) NOT NULL,
  `tbl_rental_fk` int(11) NOT NULL,
  PRIMARY KEY (`tbl_inventory_fk`,`tbl_rental_fk`),
  KEY `fk_tbl_inventory_has_tbl_rental_tbl_rental1` (`tbl_rental_fk`),
  CONSTRAINT `fk_tbl_inventory_has_tbl_rental_tbl_inventory1` FOREIGN KEY (`tbl_inventory_fk`) REFERENCES `tbl_inventory` (`inventory_id`),
  CONSTRAINT `fk_tbl_inventory_has_tbl_rental_tbl_rental1` FOREIGN KEY (`tbl_rental_fk`) REFERENCES `tbl_rental` (`rental_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_listofrentalbooks`
--

LOCK TABLES `tbl_listofrentalbooks` WRITE;
/*!40000 ALTER TABLE `tbl_listofrentalbooks` DISABLE KEYS */;
INSERT INTO `tbl_listofrentalbooks` VALUES (256,1),(271,1),(399,1),(102,2),(110,2),(271,2),(48,3),(144,3),(219,3),(355,3),(383,3),(23,4),(84,4),(126,4),(40,5),(235,5),(152,6),(304,6),(43,7),(162,7),(25,9),(82,9),(178,9),(327,9),(38,10),(160,10),(100,11),(227,11),(9,12),(98,12),(272,12),(273,12),(55,13),(214,13),(70,14),(295,14),(15,15),(144,15),(171,15),(187,15),(38,16),(177,16),(198,17),(307,17),(43,19),(56,19),(385,19),(178,20),(244,20),(64,21),(94,21),(283,21),(244,22),(278,22),(317,22),(358,22),(219,23),(89,24),(173,24),(230,24),(255,24),(375,24),(131,25),(145,25),(217,25),(264,25),(280,25),(347,25),(21,26),(399,26),(28,27),(71,27),(335,27),(301,28),(82,30),(249,30),(325,31),(111,32),(221,32),(395,32),(8,33),(68,34),(96,34),(394,34),(80,35),(179,35),(375,35),(391,35),(107,37),(130,37),(27,38),(313,38),(115,39),(201,39),(290,39),(386,39),(205,40),(285,40),(106,41),(188,41),(50,42),(65,42),(325,42),(18,43),(115,45),(125,45),(126,45),(154,45),(276,45),(38,46),(149,46),(186,46),(215,47),(266,47),(397,47),(60,48),(259,48),(268,51),(334,51),(196,52),(321,52),(57,53),(200,53),(303,53),(60,54),(150,54),(330,54),(343,54),(11,55),(92,55),(262,55),(158,56),(156,57),(179,57),(259,57),(328,57),(313,58),(91,59),(141,59),(392,59),(5,60),(37,60),(129,60),(213,60),(291,60),(69,62),(76,62),(353,63),(2,64),(171,64),(202,64),(304,64),(15,65),(66,65),(125,65),(138,65),(350,66),(198,67),(312,67),(115,68),(253,69),(361,69),(42,70),(72,70),(149,70),(317,70),(329,70),(11,71),(149,71),(299,71),(283,72),(48,74),(206,74),(210,74),(19,75),(186,75),(343,75),(350,75),(23,76),(43,76),(176,76),(223,76),(136,77),(246,77),(318,77),(65,79),(75,79),(115,79),(168,80),(202,80),(257,80),(261,80),(356,80),(361,80),(107,81),(125,81),(136,81),(208,82),(377,82),(28,83),(100,83),(184,83),(300,83),(6,84),(46,84),(186,84),(261,84),(63,85),(138,85),(249,85),(368,85),(316,86),(334,86),(381,86),(15,87),(22,87),(155,87),(385,87),(81,89),(163,89),(268,89),(270,89),(302,89),(356,89),(20,90),(143,90),(169,90),(264,90),(60,91),(289,91),(76,92),(268,92),(301,92),(194,93),(237,93),(294,93),(11,94),(58,94),(277,94),(315,94),(168,95),(204,95),(21,96),(229,96),(246,97),(139,98),(166,98),(371,98),(164,99),(179,99),(280,99),(46,100),(198,100),(249,100),(359,100),(138,101),(179,101),(317,101),(134,103),(182,103),(356,103),(55,105),(90,105),(247,105),(306,105),(354,105),(132,106),(136,107),(142,107),(185,107),(195,107),(120,109),(137,109),(153,109),(53,110),(100,110),(331,110),(365,110),(187,112),(271,113),(361,113),(32,114),(92,114),(10,115),(100,115),(40,116),(76,116),(40,117),(354,117),(19,118),(171,118),(351,119),(356,119),(204,120),(247,120),(150,121),(228,122),(12,124),(169,124),(333,124),(12,125),(29,125),(159,125),(227,125),(312,125),(11,126),(63,126),(128,126),(146,126),(290,127),(314,128),(330,128),(345,128),(345,129),(152,130),(161,131),(272,131),(398,131),(131,132),(189,132),(308,132),(338,132),(86,133),(116,133),(204,133),(231,133),(255,133),(351,133),(382,133),(39,134),(110,134),(234,134),(110,135),(203,135),(314,135),(397,135),(35,136),(379,136),(28,137),(235,139),(301,139),(340,139),(359,139),(368,139),(88,141),(116,141),(224,141),(248,141),(329,141),(57,142),(157,142),(75,143),(269,143),(380,143),(276,144),(286,144),(40,145),(41,145),(47,145),(63,145),(325,145),(390,145),(140,146),(255,146),(7,147),(53,147),(249,147),(298,147),(227,148),(326,148),(76,149),(127,149),(150,149),(288,149),(4,150),(145,150),(186,150),(206,152),(306,152),(384,152),(385,152),(67,154),(72,155),(92,155),(243,156),(36,157),(271,157),(84,158),(14,159),(52,159),(347,159),(82,160),(251,160),(29,161),(42,161),(244,161),(20,162),(193,162),(67,163),(236,163),(276,163),(15,164),(294,164),(22,166),(377,166),(25,167),(261,167),(72,168),(122,168),(343,168),(344,169),(100,170),(282,170),(290,170),(325,170),(362,170),(121,171),(127,171),(311,171),(354,171),(128,172),(164,172),(286,172),(297,172),(1,173),(215,173),(245,173),(263,173),(323,173),(48,174),(385,174),(37,175),(48,175),(287,175),(308,175),(67,176),(113,176),(209,176),(245,176),(24,177),(134,177),(36,178),(136,178),(171,178),(280,178),(48,180),(176,180),(286,180),(23,181),(253,181),(312,182),(397,182),(47,183),(187,183),(317,183),(73,184),(159,184),(244,184),(271,184),(339,184),(197,185),(245,185),(319,185),(339,185),(274,187),(290,187),(379,187),(210,188),(237,188),(329,188),(206,189),(389,189),(277,190),(331,190),(380,190),(90,191),(147,191),(371,191),(127,192),(165,192),(363,193),(95,194),(327,194),(149,195),(56,196),(103,196),(148,196),(235,196),(333,196),(80,197),(252,197),(277,197),(309,197),(331,197),(355,197),(391,197),(179,198),(240,198),(321,199),(315,200);
/*!40000 ALTER TABLE `tbl_listofrentalbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_person`
--

DROP TABLE IF EXISTS `tbl_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `tel` varchar(20) NOT NULL,
  `tbl_address_fk` int(11) NOT NULL,
  `str_nmbr` int(11) NOT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE KEY `person_id_UNIQUE` (`person_id`),
  KEY `fk_tbl_person_tbl_address1` (`tbl_address_fk`),
  CONSTRAINT `fk_tbl_person_tbl_address1` FOREIGN KEY (`tbl_address_fk`) REFERENCES `tbl_address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_person`
--

LOCK TABLES `tbl_person` WRITE;
/*!40000 ALTER TABLE `tbl_person` DISABLE KEYS */;
INSERT INTO `tbl_person` VALUES (1,'Quin','Benton','primis@eu.edu','+41-62-796-37-96',7,118),(2,'Aspen','Mueller','sem@sapien.com','+41-92-434-74-76',57,89),(3,'Gray','Ortiz','libero.Donec@venenatis.com','+41-23-764-18-00',17,134),(4,'Celeste','Gamble','Vivamus.molestie.dapibus@Duiscursus.net','+41-85-297-31-26',26,193),(5,'Laurel','Flores','ante.ipsum.primis@quistristique.ca','+41-72-139-26-34',59,168),(6,'MacKenzie','Patton','Aenean.eget.magna@malesuadavel.co.uk','+41-37-875-34-35',38,144),(7,'Nigel','Higgins','risus@velitQuisque.ca','+41-87-757-99-03',12,43),(8,'Rajah','Boyer','et@dictumProin.edu','+41-12-305-39-24',54,126),(9,'Brent','Whitfield','ut@loremvitaeodio.com','+41-16-920-46-33',7,123),(10,'Haley','King','arcu.vel.quam@cubiliaCurae.org','+41-84-953-32-02',44,52),(11,'Stacey','Pruitt','sapien.cursus.in@etmagnisdis.co.uk','+41-21-765-33-56',54,197),(12,'Quon','Kennedy','libero.est.congue@Namac.co.uk','+41-48-888-62-18',35,167),(13,'Lavinia','Camacho','vitae.sodales.at@augueac.com','+41-28-490-39-85',34,51),(14,'Hollee','Jensen','Integer.vulputate.risus@Donecnibh.net','+41-24-886-99-58',42,152),(15,'Valentine','Hurley','interdum.enim@cursus.net','+41-38-279-71-62',35,34),(16,'Craig','Brown','velit.Aliquam@massaQuisqueporttitor.edu','+41-48-753-01-62',4,97),(17,'Benedict','Lowe','Aliquam.fringilla.cursus@aliquetmetusurna.com','+41-12-212-73-11',5,209),(18,'Ferdinand','Guthrie','Lorem@Maurisquisturpis.ca','+41-13-839-96-66',37,156),(19,'April','Vaughan','nisi.magna@egestasSedpharetra.ca','+41-89-682-81-07',37,94),(20,'Zane','Adams','Proin.mi.Aliquam@Ut.net','+41-45-674-37-69',55,95),(21,'Angela','Sullivan','mauris.id.sapien@euelit.edu','+41-41-888-77-55',44,160),(22,'Linus','Vance','Sed@nascetur.net','+41-59-674-61-83',12,168),(23,'Evan','Blackburn','Sed.neque@Sed.edu','+41-89-268-42-48',58,115),(24,'Lesley','Spence','id@Sed.co.uk','+41-89-200-45-62',16,133),(25,'Benjamin','Collins','In@velitSedmalesuada.edu','+41-39-681-27-16',18,242),(26,'Kimberley','Reed','orci@eget.edu','+41-87-341-67-52',43,38),(27,'Cruz','Garza','eget@aauctornon.ca','+41-14-399-30-40',3,8),(28,'Robert','Kidd','sodales@nec.org','+41-23-607-67-02',57,38),(29,'Cameran','Holloway','quam.quis@arcu.com','+41-76-984-88-43',47,170),(30,'Fallon','Boyd','pede@auctorvitaealiquet.net','+41-59-168-39-13',51,159),(31,'Peter','Booth','mi.tempor@adipiscingenim.ca','+41-14-278-07-28',15,97),(32,'Duncan','Riddle','placerat.Cras@luctusut.ca','+41-91-289-81-23',9,146),(33,'Hamish','Ayers','ante.Maecenas.mi@arcuNunc.net','+41-67-549-16-27',60,108),(34,'Rama','Harvey','aliquet.sem@Donec.edu','+41-91-490-79-30',17,230),(35,'Yolanda','Battle','dolor.egestas@nonquamPellentesque.com','+41-99-782-71-12',48,58),(36,'Ivor','Kirby','nisl@augueut.org','+41-94-386-28-49',29,99),(37,'Wing','Castillo','eu.tellus.Phasellus@velit.ca','+41-40-492-59-39',28,209),(38,'Frances','Mcclain','id.enim.Curabitur@Aliquamgravida.org','+41-94-919-38-92',40,176),(39,'Desirae','Gibbs','condimentum.eget@euultrices.com','+41-53-292-35-47',2,61),(40,'Steven','Short','a.enim@fringilla.co.uk','+41-57-282-30-52',51,102),(41,'Abigail','Brock','In.tincidunt@dolor.co.uk','+41-44-917-45-44',44,167),(42,'Dora','Christensen','et.arcu.imperdiet@Vivamussitamet.org','+41-63-237-80-11',6,82),(43,'Basil','Tanner','eu@urna.edu','+41-83-348-46-76',54,3),(44,'Barrett','Cochran','ornare.elit.elit@gravidamaurisut.org','+41-86-203-71-13',48,113),(45,'Alice','Garrett','erat@risus.ca','+41-97-936-78-13',5,37),(46,'Hedy','Tran','dui.nec@scelerisque.edu','+41-73-852-82-61',45,232),(47,'Sebastian','Mcgowan','tristique.neque@hendreritconsectetuer.co.uk','+41-39-966-15-98',5,186),(48,'Belle','Warner','metus@lectusNullamsuscipit.co.uk','+41-36-327-30-17',43,47),(49,'Ria','Mcdaniel','et.lacinia.vitae@euaccumsansed.co.uk','+41-71-372-64-59',57,154),(50,'Cullen','Morton','Sed.eu@Proin.org','+41-30-635-93-83',47,175),(51,'Wendy','Green','sociis.natoque.penatibus@necurna.net','+41-18-342-74-39',30,68),(52,'Idola','Mcintosh','adipiscing.elit@utcursus.com','+41-44-900-15-21',9,241),(53,'Abraham','Rasmussen','laoreet@Praesent.co.uk','+41-10-233-73-05',19,110),(54,'Cameran','Flynn','dui@tinciduntpedeac.com','+41-80-735-25-22',14,13),(55,'Elijah','Paul','mauris.aliquam@tristique.com','+41-83-356-21-58',14,72),(56,'Dominic','Mcclure','vitae.diam.Proin@pellentesqueSed.co.uk','+41-77-601-06-05',5,70),(57,'Hakeem','Knapp','Cum.sociis@neque.co.uk','+41-57-604-83-95',34,144),(58,'Evangeline','Sweeney','Pellentesque.tincidunt.tempus@egetnisi.edu','+41-57-255-33-52',58,158),(59,'Anne','Ashley','Nulla.eu.neque@variuset.ca','+41-82-835-07-54',49,146),(60,'Deacon','Avery','nonummy@risusQuisque.co.uk','+41-68-417-88-13',39,29),(61,'Martina','Bonner','in@incursus.co.uk','+41-60-493-36-67',24,17),(62,'Sean','Soto','vulputate.ullamcorper.magna@pedeNunc.co.uk','+41-89-315-54-98',60,169),(63,'Isaac','Battle','dictum.Phasellus.in@odiosemper.com','+41-98-273-72-61',21,217),(64,'Kirestin','Dotson','consequat@necmaurisblandit.net','+41-73-471-04-74',16,189),(65,'Brady','Roberts','a.facilisis.non@consequat.net','+41-84-378-59-24',13,246),(66,'Martina','Valentine','augue@Aliquamfringillacursus.com','+41-87-441-96-90',42,147),(67,'Gary','Robbins','eget.dictum.placerat@tristiquesenectuset.edu','+41-67-876-91-05',53,246),(68,'Kadeem','Massey','dictum@enimSednulla.net','+41-64-231-43-91',5,148),(69,'Indira','Burch','egestas.blandit@liberoMorbiaccumsan.net','+41-21-206-11-15',49,240),(70,'Patience','Tran','purus@semelitpharetra.org','+41-29-547-62-06',50,230),(71,'Ryan','Stanley','blandit@Nulla.ca','+41-81-325-75-04',36,164),(72,'William','Wilder','magna.Duis.dignissim@Maurisblandit.edu','+41-58-598-14-93',33,250),(73,'Eric','Sanders','neque.In@Namconsequatdolor.edu','+41-14-317-52-37',48,40),(74,'Candice','Jensen','enim.non.nisi@orci.net','+41-47-135-05-62',47,111),(75,'Basil','Allen','et.eros@nisidictumaugue.ca','+41-63-751-75-53',12,175),(76,'Jenette','Hampton','pharetra.ut.pharetra@ac.ca','+41-40-603-95-77',20,196),(77,'Karly','Houston','Nullam@etcommodo.co.uk','+41-58-710-70-99',59,102),(78,'Keiko','Dunn','eu@natoquepenatibus.org','+41-26-670-90-02',39,109),(79,'Selma','Lowe','consequat.enim@tacitisociosquad.co.uk','+41-41-957-97-23',10,193),(80,'Noelani','Mccray','rhoncus.Proin.nisl@ipsum.ca','+41-73-750-21-17',34,242),(81,'Audra','Mathews','a.sollicitudin@Nunc.org','+41-18-956-22-73',12,124),(82,'Samuel','Mcdonald','In.at.pede@interdum.net','+41-48-597-16-07',59,30),(83,'Jarrod','Rodriquez','eget@Quisque.org','+41-91-698-28-22',21,52),(84,'Warren','Porter','sit.amet@in.edu','+41-63-344-06-98',39,63),(85,'Quinn','Spence','semper@elitpede.co.uk','+41-88-810-22-89',4,37),(86,'Dahlia','Lamb','pede@interdum.net','+41-31-148-39-86',44,10),(87,'Amal','Mcintosh','eget@nequesed.co.uk','+41-93-171-06-77',52,244),(88,'Nelle','Knapp','quis.diam.Pellentesque@Phasellusornare.co.uk','+41-87-585-88-91',18,236),(89,'Nelle','House','dui.nec@sempertellus.co.uk','+41-93-319-23-43',46,190),(90,'Nathan','Vargas','ultricies@diamnuncullamcorper.ca','+41-29-352-91-55',29,118),(91,'Belle','Hill','sagittis.augue@natoquepenatibuset.ca','+41-40-232-76-32',33,247),(92,'Herrod','Pickett','consequat.enim@risus.org','+41-19-379-69-06',58,24),(93,'Elijah','Griffith','elit.pharetra@nibhDonecest.com','+41-89-787-34-22',50,57),(94,'Marny','Henderson','ac@luctuslobortisClass.edu','+41-68-702-46-75',3,107),(95,'Debra','Nash','ante@neceleifendnon.org','+41-67-740-75-97',17,244),(96,'Samson','Strong','cursus.Integer.mollis@semut.com','+41-33-880-25-45',23,154),(97,'Kirsten','Vang','bibendum@ridiculusmusProin.com','+41-64-725-97-37',32,111),(98,'Carlos','Stone','velit.Aliquam@ullamcorper.edu','+41-43-830-57-03',19,49),(99,'Rahim','Vaughn','natoque@odiosempercursus.net','+41-47-774-84-19',59,49),(100,'Jolie','Raymond','tempor.augue@Duisgravida.com','+41-71-936-47-33',34,130),(200,'Chris','OConnor','christopher.oconnor@edu.tbz.ch','+41-62-796-37-96',100,118),(201,'Umut','Savas','umut.savas@edu.tbz.ch','+41-62-796-37-96',100,119);
/*!40000 ALTER TABLE `tbl_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_publisher`
--

DROP TABLE IF EXISTS `tbl_publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_publisher` (
  `publisher_id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(45) NOT NULL,
  PRIMARY KEY (`publisher_id`),
  UNIQUE KEY `publisher_id_UNIQUE` (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_publisher`
--

LOCK TABLES `tbl_publisher` WRITE;
/*!40000 ALTER TABLE `tbl_publisher` DISABLE KEYS */;
INSERT INTO `tbl_publisher` VALUES (1,'Lacinia At Incorporated'),(2,'Id Associates'),(3,'Lectus Rutrum Inc.'),(4,'Ipsum Dolor Consulting'),(5,'Non Inc.'),(6,'Accumsan Inc.'),(7,'Lobortis Class Aptent PC'),(8,'Mauris Industries'),(9,'Ut Molestie In LLP'),(10,'Consequat Institute');
/*!40000 ALTER TABLE `tbl_publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rental`
--

DROP TABLE IF EXISTS `tbl_rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_rental` (
  `rental_id` int(11) NOT NULL AUTO_INCREMENT,
  `startRental` date NOT NULL,
  `endRental` date DEFAULT NULL,
  `expires` date NOT NULL,
  `payed` tinyint(4) NOT NULL DEFAULT '0',
  `tbl_staff_fk` int(11) NOT NULL,
  `tbl_customer_fk` int(11) NOT NULL,
  PRIMARY KEY (`rental_id`),
  UNIQUE KEY `idtbl_rental_UNIQUE` (`rental_id`),
  KEY `fk_tbl_rental_tbl_staff1` (`tbl_staff_fk`),
  KEY `fk_tbl_rental_tbl_customer1` (`tbl_customer_fk`),
  CONSTRAINT `fk_tbl_rental_tbl_customer1` FOREIGN KEY (`tbl_customer_fk`) REFERENCES `tbl_customer` (`tbl_person_fk`),
  CONSTRAINT `fk_tbl_rental_tbl_staff1` FOREIGN KEY (`tbl_staff_fk`) REFERENCES `tbl_staff` (`tbl_person_fk`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rental`
--

LOCK TABLES `tbl_rental` WRITE;
/*!40000 ALTER TABLE `tbl_rental` DISABLE KEYS */;
INSERT INTO `tbl_rental` VALUES (1,'2008-05-23','2018-07-28','2019-01-04',1,89,56),(2,'2002-02-28','2012-09-15','2019-02-01',1,82,31),(3,'2001-02-01','2014-09-02','2019-02-17',1,62,54),(4,'2003-01-30','2010-10-17','2019-01-15',1,89,50),(5,'2005-11-19','2011-04-21','2019-01-03',1,86,6),(6,'2009-11-09','2018-02-18','2019-02-24',1,75,16),(7,'2001-06-19','2013-05-15','2019-02-27',1,74,10),(8,'2007-11-30','2018-07-14','2019-02-19',1,88,30),(9,'2006-12-22','2013-08-18','2019-02-01',1,84,35),(10,'2006-11-27','2018-12-06','2019-01-16',1,85,8),(11,'2004-07-26','2011-09-07','2019-01-29',1,88,56),(12,'2009-03-16','2015-10-15','2019-01-28',1,60,30),(13,'2002-11-30','2016-02-02','2019-02-11',1,60,23),(14,'2003-06-23','2015-07-10','2019-02-13',1,85,4),(15,'2003-11-12','2014-07-03','2019-01-20',1,65,27),(16,'2009-09-09','2014-01-13','2019-01-10',1,65,11),(17,'2004-06-18','2013-01-15','2019-01-10',1,74,5),(18,'2005-07-11','2011-04-11','2019-02-19',1,66,20),(19,'2003-02-26','2016-10-11','2019-01-30',1,85,70),(20,'2004-01-07','2010-02-07','2019-01-25',1,88,42),(21,'2008-10-18','2017-10-21','2019-02-16',1,83,15),(22,'2007-07-24','2013-08-28','2019-02-18',1,66,61),(23,'2002-08-08','2012-01-18','2019-01-30',1,68,30),(24,'2009-10-18','2016-02-24','2019-01-23',1,65,33),(25,'2005-01-10','2016-05-29','2019-01-19',1,87,77),(26,'2002-02-12','2016-01-25','2019-01-21',1,70,34),(27,'2003-09-01','2014-09-22','2019-01-05',1,63,53),(28,'2009-09-06','2011-03-01','2019-01-24',1,84,68),(29,'2004-05-13','2017-07-07','2019-03-01',1,88,23),(30,'2006-08-28','2013-06-19','2019-02-06',1,87,80),(31,'2004-09-08','2017-05-04','2019-01-21',1,68,53),(32,'2009-12-08','2010-04-09','2019-02-19',1,75,7),(33,'2001-08-07','2014-07-24','2019-01-19',1,62,19),(34,'2009-08-15','2017-08-13','2019-01-06',1,61,80),(35,'2005-02-10','2010-10-18','2019-01-06',1,69,67),(36,'2002-03-20','2015-07-01','2019-02-22',1,66,23),(37,'2001-08-28','2013-09-01','2019-02-04',1,63,78),(38,'2001-01-22','2016-02-10','2019-01-16',1,87,30),(39,'2008-11-10','2014-04-04','2019-02-26',1,66,44),(40,'2005-04-26','2013-06-23','2019-02-02',1,88,69),(41,'2006-07-16','2014-01-24','2019-02-16',1,80,25),(42,'2001-01-08','2017-01-07','2019-01-08',1,61,72),(43,'2004-04-25','2016-10-02','2019-02-19',1,70,66),(44,'2007-09-19','2010-02-24','2019-02-24',1,75,34),(45,'2004-06-14','2015-06-14','2019-03-01',1,65,15),(46,'2001-10-18','2013-10-17','2019-01-16',1,74,27),(47,'2006-09-03','2013-06-23','2019-01-24',1,75,65),(48,'2003-02-16','2018-03-15','2019-01-30',1,63,32),(49,'2002-09-24','2013-02-13','2019-02-08',1,76,18),(50,'2004-11-22','2014-11-26','2019-02-03',1,76,26),(51,'2007-03-05','2015-08-14','2019-02-25',1,76,7),(52,'2005-09-18','2016-01-31','2019-02-03',1,81,71),(53,'2007-12-25','2012-12-24','2019-02-23',1,70,62),(54,'2005-04-27','2017-11-25','2019-01-02',1,62,29),(55,'2002-09-14','2011-04-24','2019-02-14',1,65,74),(56,'2005-02-26','2018-07-02','2019-02-11',1,71,77),(57,'2009-04-29','2013-01-07','2019-02-09',1,78,50),(58,'2001-05-15','2014-04-03','2019-02-10',1,60,38),(59,'2009-10-21','2015-10-23','2019-02-14',1,88,59),(60,'2007-09-01','2015-08-24','2019-02-27',1,77,70),(61,'2003-12-06','2017-07-10','2019-02-16',1,67,38),(62,'2003-11-19','2014-01-15','2019-02-20',1,73,45),(63,'2002-08-21','2018-02-11','2019-02-27',1,75,8),(64,'2003-02-28','2014-02-05','2019-02-06',1,68,2),(65,'2005-09-05','2016-05-25','2019-02-23',1,81,47),(66,'2005-11-10','2013-01-21','2019-01-24',1,89,63),(67,'2006-06-16','2013-05-28','2019-02-19',1,82,63),(68,'2003-05-14','2016-10-22','2019-02-14',1,73,14),(69,'2003-09-05','2010-06-05','2019-01-02',1,77,58),(70,'2006-01-06','2017-01-21','2019-01-20',1,61,49),(71,'2001-09-27','2017-11-08','2019-03-01',1,73,34),(72,'2002-11-30','2012-04-03','2019-02-14',1,68,67),(73,'2002-03-24','2017-01-28','2019-01-31',1,63,44),(74,'2004-11-27','2012-08-09','2019-01-29',1,86,70),(75,'2003-10-29','2017-11-29','2019-02-03',1,68,69),(76,'2006-04-10','2014-09-13','2019-01-20',1,71,16),(77,'2006-02-26','2010-04-15','2019-01-30',1,88,39),(78,'2006-07-09','2010-09-24','2019-01-24',1,82,13),(79,'2006-07-04','2011-10-24','2019-01-04',1,70,41),(80,'2003-07-06','2010-07-28','2019-01-29',1,62,78),(81,'2007-02-03','2013-08-07','2019-01-05',1,62,57),(82,'2008-10-17','2012-08-19','2019-02-20',1,65,8),(83,'2002-03-04','2011-07-25','2019-02-09',1,76,69),(84,'2003-08-01','2015-03-20','2019-02-14',1,79,76),(85,'2007-01-09','2012-05-17','2019-01-21',1,60,49),(86,'2004-07-16','2017-05-28','2019-02-11',1,64,76),(87,'2008-06-22','2017-05-20','2019-02-11',1,87,52),(88,'2004-12-27','2012-04-22','2019-01-14',1,76,65),(89,'2008-10-14','2013-01-14','2019-02-11',1,67,37),(90,'2003-10-09','2010-01-06','2019-02-01',1,80,42),(91,'2005-09-14','2010-02-15','2019-02-26',1,75,80),(92,'2005-01-12','2015-11-15','2019-01-17',1,87,19),(93,'2002-04-16','2017-08-18','2019-02-06',1,64,32),(94,'2008-03-17','2016-08-19','2019-02-12',1,89,53),(95,'2001-08-13','2014-06-28','2019-01-20',1,62,55),(96,'2002-12-30','2016-07-23','2019-01-24',1,84,50),(97,'2003-07-08','2018-06-23','2019-01-18',1,69,54),(98,'2007-07-23','2017-11-06','2019-01-29',1,61,3),(99,'2002-06-24','2016-04-27','2019-01-18',1,66,30),(100,'2004-04-03','2010-10-24','2019-01-18',1,86,33),(101,'2006-09-15','2017-09-11','2019-02-02',1,60,44),(102,'2001-07-08','2014-10-03','2019-02-28',1,85,30),(103,'2001-08-18','2016-12-13','2019-01-10',1,89,77),(104,'2005-01-07','2011-08-09','2019-01-12',1,88,37),(105,'2002-12-28','2016-06-14','2019-02-03',1,67,2),(106,'2006-07-11','2014-08-05','2019-01-21',1,87,16),(107,'2008-08-13','2018-10-02','2019-02-08',1,64,57),(108,'2005-10-06','2015-10-26','2019-02-23',1,89,16),(109,'2003-12-26','2017-07-04','2019-01-22',1,70,3),(110,'2004-11-08','2010-06-13','2019-01-22',1,81,5),(111,'2002-10-16','2017-09-15','2019-02-01',1,67,23),(112,'2003-02-21','2011-09-19','2019-01-26',1,60,17),(113,'2007-11-27','2016-03-16','2019-02-12',1,73,65),(114,'2003-01-10','2014-03-06','2019-02-01',1,69,18),(115,'2008-11-05','2015-01-13','2019-01-26',1,81,13),(116,'2009-12-25','2010-07-08','2019-01-12',1,71,59),(117,'2009-02-17','2015-03-17','2019-02-06',1,62,5),(118,'2006-03-23','2016-05-28','2019-01-14',1,68,74),(119,'2008-01-19','2017-11-02','2019-03-01',1,69,36),(120,'2004-07-24','2011-11-07','2019-02-19',1,77,29),(121,'2007-08-15','2014-01-30','2019-02-13',1,62,73),(122,'2003-07-14','2015-12-08','2019-02-03',1,86,7),(123,'2001-03-29','2018-02-27','2019-02-27',1,73,14),(124,'2009-05-10','2017-02-17','2019-02-01',1,75,59),(125,'2007-06-30','2016-12-13','2019-01-19',1,60,61),(126,'2007-12-01','2012-06-29','2019-02-14',1,84,31),(127,'2009-07-12','2015-09-02','2019-02-28',1,84,9),(128,'2005-03-09','2010-05-01','2019-01-09',1,60,20),(129,'2005-03-29','2014-06-15','2019-01-25',1,60,22),(130,'2008-02-14','2010-07-15','2019-02-17',1,64,79),(131,'2002-02-15','2016-05-28','2019-02-17',1,62,18),(132,'2006-06-28','2018-11-26','2019-01-24',1,79,21),(133,'2006-01-03','2016-11-19','2019-02-27',1,75,63),(134,'2005-03-17','2016-09-29','2019-01-08',1,88,13),(135,'2004-03-08','2012-05-10','2019-02-14',1,82,56),(136,'2002-10-09','2017-03-02','2019-02-20',1,85,16),(137,'2007-10-07','2015-03-08','2019-02-14',1,77,7),(138,'2008-04-21','2012-12-20','2019-01-18',1,76,6),(139,'2004-10-03','2011-11-24','2019-02-04',1,61,45),(140,'2004-01-11','2016-04-18','2019-02-20',1,73,28),(141,'2007-02-03','2015-11-05','2019-01-02',1,67,34),(142,'2001-12-29','2015-10-23','2019-02-02',1,70,30),(143,'2002-02-02','2014-10-26','2019-02-07',1,64,23),(144,'2001-11-23','2018-06-07','2019-01-09',1,86,69),(145,'2009-07-18','2015-06-19','2019-02-07',1,89,58),(146,'2008-04-05','2017-02-16','2019-02-04',1,69,35),(147,'2002-07-03','2015-05-26','2019-01-30',1,62,54),(148,'2001-09-09','2016-04-17','2019-02-08',1,64,58),(149,'2005-02-23','2018-05-15','2019-01-02',1,66,50),(150,'2008-09-07','2018-12-21','2019-02-01',1,69,4),(151,'2004-07-04','2017-05-23','2019-01-09',1,85,78),(152,'2001-02-19','2018-04-06','2019-02-12',1,73,48),(153,'2008-11-23','2016-03-23','2019-01-16',1,63,40),(154,'2006-04-03','2013-12-20','2019-02-17',1,85,69),(155,'2009-02-16','2011-02-24','2019-01-07',1,78,42),(156,'2005-07-22','2012-10-21','2019-02-25',1,74,1),(157,'2001-04-10','2018-01-01','2019-01-15',1,84,39),(158,'2005-06-16','2012-06-04','2019-01-29',1,67,8),(159,'2001-05-21','2014-04-04','2019-02-20',1,62,13),(160,'2002-03-05','2015-09-09','2019-01-19',1,61,77),(161,'2008-12-27','2018-06-23','2019-01-18',1,78,74),(162,'2006-01-27','2017-09-15','2019-02-10',1,70,69),(163,'2004-11-17','2013-03-04','2019-01-23',1,61,64),(164,'2008-04-08','2018-05-23','2019-02-02',1,61,12),(165,'2008-02-14','2010-06-04','2019-02-02',1,62,68),(166,'2003-06-26','2014-11-15','2019-02-21',1,79,32),(167,'2004-11-22','2017-02-20','2019-02-13',1,88,1),(168,'2006-03-06','2011-01-10','2019-01-15',1,77,70),(169,'2008-10-18','2010-11-28','2019-01-30',1,64,38),(170,'2002-06-04','2016-10-15','2019-02-28',1,75,5),(171,'2009-06-14','2016-07-26','2019-01-23',1,66,78),(172,'2002-01-19','2011-09-09','2019-02-13',1,82,48),(173,'2007-09-29','2012-11-26','2019-01-06',1,81,64),(174,'2001-12-31','2012-01-17','2019-02-15',1,73,4),(175,'2008-06-29','2015-10-06','2019-01-07',1,76,54),(176,'2002-08-18','2014-07-30','2019-02-11',1,60,74),(177,'2001-08-22','2017-11-14','2019-01-24',1,64,28),(178,'2006-06-11','2016-04-05','2019-01-29',1,69,18),(179,'2004-06-30','2016-07-16','2019-01-08',1,77,32),(180,'2004-04-27','2017-05-26','2019-01-18',1,75,30),(181,'2005-04-25','2012-02-09','2019-01-09',1,89,11),(182,'2002-10-26','2012-11-13','2019-01-16',1,85,76),(183,'2003-09-05','2014-07-30','2019-01-03',1,86,54),(184,'2005-02-04','2012-05-15','2019-01-02',1,67,77),(185,'2001-06-28','2018-10-18','2019-02-10',1,73,34),(186,'2009-05-10','2014-12-06','2019-01-22',1,64,31),(187,'2003-11-02','2016-03-13','2019-01-13',1,63,34),(188,'2002-11-27','2011-11-01','2019-01-03',1,76,75),(189,'2004-07-19','2015-12-07','2019-02-11',1,72,32),(190,'2001-03-27','2016-04-24','2019-01-25',1,60,75),(191,'2006-02-20','2013-04-06','2019-02-08',1,61,65),(192,'2002-09-30','2017-07-15','2019-02-26',1,84,3),(193,'2008-04-30','2017-07-16','2019-01-07',1,73,76),(194,'2009-09-03','2018-07-23','2019-01-29',1,71,13),(195,'2005-01-09','2015-01-19','2019-01-21',1,69,36),(196,'2007-11-09','2016-04-08','2019-02-14',1,72,16),(197,'2002-01-31','2016-07-26','2019-02-21',1,61,47),(198,'2002-10-16','2014-04-21','2019-01-23',1,62,34),(199,'2006-09-26','2015-04-26','2019-02-08',1,73,36),(200,'2002-12-04','2017-05-30','2019-01-18',1,66,64);
/*!40000 ALTER TABLE `tbl_rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_staff`
--

DROP TABLE IF EXISTS `tbl_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_staff` (
  `tbl_person_fk` int(11) NOT NULL,
  `picture` blob,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `wage` decimal(10,2) NOT NULL,
  `since` date NOT NULL,
  `tbl_store_fk` int(11) NOT NULL,
  PRIMARY KEY (`tbl_person_fk`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_staff_store1` (`tbl_store_fk`),
  CONSTRAINT `fk_staff_store1` FOREIGN KEY (`tbl_store_fk`) REFERENCES `tbl_store` (`store_id`),
  CONSTRAINT `fk_staff_tbl_person1` FOREIGN KEY (`tbl_person_fk`) REFERENCES `tbl_person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_staff`
--

LOCK TABLES `tbl_staff` WRITE;
/*!40000 ALTER TABLE `tbl_staff` DISABLE KEYS */;
INSERT INTO `tbl_staff` VALUES (60,NULL,NULL,'2813',6513.26,'2014-08-09',2),(61,NULL,NULL,'8957',7981.33,'2015-01-05',1),(62,NULL,NULL,'9048',3241.11,'2002-11-03',2),(63,NULL,NULL,'2217',9104.36,'2009-05-16',3),(64,NULL,NULL,'4748',8836.16,'2003-06-26',3),(65,NULL,NULL,'3438',8219.49,'2019-08-13',3),(66,NULL,NULL,'8872',7097.39,'2010-04-02',3),(67,NULL,NULL,'5775',4378.94,'2017-02-13',1),(68,NULL,NULL,'3183',8526.34,'2010-12-10',1),(69,NULL,NULL,'7900',3643.72,'2011-02-12',3),(70,NULL,NULL,'2621',3704.78,'2010-10-28',2),(71,NULL,NULL,'6128',6061.27,'2009-05-04',1),(72,NULL,NULL,'9411',5205.95,'2012-08-29',3),(73,NULL,NULL,'4106',8875.47,'2017-11-07',3),(74,NULL,NULL,'5922',6027.04,'2005-08-31',3),(75,NULL,NULL,'1186',5225.03,'2001-07-22',1),(76,NULL,NULL,'9729',7292.29,'2017-06-05',1),(77,NULL,NULL,'8773',3282.05,'2011-09-04',1),(78,NULL,NULL,'5907',6559.54,'2004-09-08',3),(79,NULL,NULL,'6698',5914.42,'2002-11-01',2),(80,NULL,NULL,'7924',4916.56,'2015-04-20',1),(81,NULL,NULL,'6912',5535.31,'2003-09-17',1),(82,NULL,NULL,'6014',3279.10,'2006-03-06',3),(83,NULL,NULL,'2254',9568.55,'2012-02-03',1),(84,NULL,NULL,'4086',3239.26,'2006-10-10',2),(85,NULL,NULL,'3801',9568.58,'2007-08-25',2),(86,NULL,NULL,'7677',9533.67,'2009-11-01',1),(87,NULL,NULL,'8383',5160.83,'2005-05-17',1),(88,NULL,NULL,'4359',5993.34,'2014-01-31',2),(89,NULL,NULL,'1356',4884.39,'2007-04-01',3),(200,NULL,'chris','1234',16513.26,'2014-08-09',1),(201,NULL,'umut','1234',17981.33,'2015-01-05',1);
/*!40000 ALTER TABLE `tbl_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_store`
--

DROP TABLE IF EXISTS `tbl_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `tbl_address_fk` int(11) NOT NULL,
  `str_nmbr` int(11) NOT NULL,
  PRIMARY KEY (`store_id`),
  UNIQUE KEY `idstore_UNIQUE` (`store_id`),
  KEY `fk_store_tbl_address1` (`tbl_address_fk`),
  CONSTRAINT `fk_store_tbl_address1` FOREIGN KEY (`tbl_address_fk`) REFERENCES `tbl_address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_store`
--

LOCK TABLES `tbl_store` WRITE;
/*!40000 ALTER TABLE `tbl_store` DISABLE KEYS */;
INSERT INTO `tbl_store` VALUES (1,15,1),(2,52,133),(3,32,2);
/*!40000 ALTER TABLE `tbl_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `test` (
  `testID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`testID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'test');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'm153'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-30 13:07:47
