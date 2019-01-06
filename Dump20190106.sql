-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: online_store
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `artikal`
--

DROP TABLE IF EXISTS `artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `artikal` (
  `idartikal` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `cena` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `prodato` int(11) NOT NULL,
  `idkorisnika` int(11) DEFAULT NULL,
  PRIMARY KEY (`idartikal`),
  UNIQUE KEY `idartikal_UNIQUE` (`idartikal`),
  KEY `fk2_idx` (`idkorisnika`),
  CONSTRAINT `fk2` FOREIGN KEY (`idkorisnika`) REFERENCES `korisnik` (`idkorisnik`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikal`
--

LOCK TABLES `artikal` WRITE;
/*!40000 ALTER TABLE `artikal` DISABLE KEYS */;
/*!40000 ALTER TABLE `artikal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `korisnik` (
  `idkorisnik` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(16) NOT NULL,
  `tip` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`idkorisnik`),
  UNIQUE KEY `idkorisnik_UNIQUE` (`idkorisnik`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (1,'admin','admin','admin','admin','admin','odobren');
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porudzbina`
--

DROP TABLE IF EXISTS `porudzbina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `porudzbina` (
  `idporudzbina` int(11) NOT NULL AUTO_INCREMENT,
  `cena` int(11) NOT NULL,
  `idkorisnik` int(11) DEFAULT NULL,
  PRIMARY KEY (`idporudzbina`),
  UNIQUE KEY `idporudzbina_UNIQUE` (`idporudzbina`),
  KEY `fk1_idx` (`idkorisnik`),
  CONSTRAINT `fk1` FOREIGN KEY (`idkorisnik`) REFERENCES `korisnik` (`idkorisnik`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porudzbina`
--

LOCK TABLES `porudzbina` WRITE;
/*!40000 ALTER TABLE `porudzbina` DISABLE KEYS */;
/*!40000 ALTER TABLE `porudzbina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stavka`
--

DROP TABLE IF EXISTS `stavka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stavka` (
  `idstavka` int(11) NOT NULL AUTO_INCREMENT,
  `kolicina` int(11) NOT NULL,
  `idporudzbina` int(11) DEFAULT NULL,
  `idartikal` int(11) DEFAULT NULL,
  PRIMARY KEY (`idstavka`),
  UNIQUE KEY `idstavka_UNIQUE` (`idstavka`),
  KEY `fk3_idx` (`idporudzbina`),
  KEY `fk4_idx` (`idartikal`),
  CONSTRAINT `fk3` FOREIGN KEY (`idporudzbina`) REFERENCES `porudzbina` (`idporudzbina`),
  CONSTRAINT `fk4` FOREIGN KEY (`idartikal`) REFERENCES `artikal` (`idartikal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stavka`
--

LOCK TABLES `stavka` WRITE;
/*!40000 ALTER TABLE `stavka` DISABLE KEYS */;
/*!40000 ALTER TABLE `stavka` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-06 22:37:26
