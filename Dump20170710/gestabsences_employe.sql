-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestabsences
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `employe`
--

DROP TABLE IF EXISTS `employe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `matricule` varchar(45) DEFAULT NULL,
  `nb_ca` int(11) DEFAULT NULL,
  `nb_rtt` int(11) DEFAULT NULL,
  `nb_rc` int(11) DEFAULT NULL,
  `id_equipe` int(11) DEFAULT NULL,
  `id_service_rh` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_employes_equipe_idx` (`id_equipe`),
  KEY `fk_employes_service_rh_idx` (`id_service_rh`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employe`
--

LOCK TABLES `employe` WRITE;
/*!40000 ALTER TABLE `employe` DISABLE KEYS */;
INSERT INTO `employe` VALUES (1,'HUMPHREY','Damian','TYDM21',25,12,1,1,1),(2,'NICHOLS','Keely','UXCL65',25,10,7,2,1),(3,'STEVENS','Jacob','VXQT96',25,3,2,3,1),(4,'POTTS','Mikayla','VGDU81',25,3,19,4,1),(5,'MCINTOSH','Chantale','DHBR96',25,3,21,5,1),(6,'MELENDEZ','Virginia','RKJQ89',25,15,19,6,1),(7,'WEAVER','Ivan','QYSH74',25,11,18,1,1),(8,'VELAZQUEZ','Paloma','FEGU92',25,7,10,1,1),(9,'FIELDS','MacKensie','FCMA25',25,14,3,2,2),(10,'FLORES','Katelyn','WCAM97',25,7,24,2,2),(11,'BERRY','Lee','WVNK86',25,8,2,2,2),(12,'WALLS','Ishmael','FZRV62',25,7,26,3,6),(13,'BLEVINS','Irene','KJTW95',25,15,18,3,6),(14,'WALTER','Lisandra','YLTV74',25,11,23,4,3),(15,'SCHWARTZ','Rae','DJZQ40',25,5,1,4,3),(16,'PEREZ','Ferdinand','WBKF15',25,15,28,5,4),(17,'OWENS','Jessamine','ZBCK68',25,11,4,5,4),(18,'KEITH','Riley','DJKY58',25,8,21,6,5),(19,'WOOTEN','Marvin','THBK14',25,4,25,6,5),(20,'GLENN','Ocean','UCKP17',25,15,28,7,4),(21,'WALLACE','Anika','SQCF97',25,14,19,8,4),(22,'ORTIZ','Hope','MVJX92',25,14,27,9,4),(23,'DICKSON','Jacob','RMAY17',25,11,15,10,5),(24,'YORK','Alyssa','STNQ38',25,10,2,11,5),(25,'LEBLANC','Brooke','PMTC43',25,8,7,12,5),(26,'AGUIRRE','Veronica','GDWZ47',25,13,2,13,3),(27,'RIVAS','Madison','AUTB50',25,6,12,14,6),(28,'DICKERSON','Henry','LRDB47',25,1,13,15,6),(29,'PETERSEN','Doris','WRJG28',25,9,23,16,2),(30,'BURCH','Hadassah','WMPU23',25,6,1,7,4),(31,'BRAY','Montana','LNMH10',25,14,4,7,4),(32,'FREDERICK','Julie','VQTR86',25,8,22,7,4),(33,'COPELAND','Sydnee','RGMY61',25,12,28,7,4),(34,'VANG','Christian','WBUJ17',25,6,28,7,4),(35,'PRINCE','Sonia','SZKL23',25,4,20,8,4),(36,'TRUJILLO','Adele','TGBQ12',25,6,7,8,4),(37,'WEEKS','Cody','NATL23',25,13,17,8,4),(38,'TRUJILLO','Joan','BYZA32',25,2,24,8,4),(39,'BEARD','Regina','MRDX41',25,8,25,8,4),(40,'ESTES','Rashad','CXGN51',25,6,5,9,4),(41,'HURLEY','Lacy','UZBC87',25,5,14,9,4),(42,'WALLER','Sacha','QEZC98',25,4,20,9,4),(43,'WARNER','Kareem','PRZG98',25,9,18,9,4),(44,'STEWART','Brynn','ESRQ92',25,6,27,9,4),(45,'BENTLEY','Keely','MWLZ28',25,3,13,10,5),(46,'FREDERICK','Autumn','CJLQ98',25,2,7,10,5),(47,'NORMAN','Howard','EWRC35',25,15,5,10,5),(48,'WINTER','Bryan','QSHK36',25,6,1,10,5),(49,'BLEVINS','Irene','RKMT95',25,6,22,10,5),(50,'NGUYEN','Ulysses','DGWR39',25,9,25,11,5),(51,'CONWAY','Shoshana','QHSW59',25,4,5,11,5),(52,'CARVER','Kasimir','DUFZ35',25,11,22,11,5),(53,'DIAZ','Fulton','SRNT46',25,15,27,11,5),(54,'HALEY','Quyn','WEBG41',25,14,24,11,5),(55,'WEISS','Meghan','UCSD36',25,2,25,12,5),(56,'WILLIAMSON','Anjolie','ULJF38',25,2,20,12,5),(57,'AYERS','Lars','FQYS75',25,5,24,12,5),(58,'MARQUEZ','Skyler','QPRM87',25,6,26,12,5),(59,'ONEAL','Carissa','LJWD98',25,8,26,12,5),(60,'FROST','Nigel','RLKA23',25,11,12,13,3),(61,'ROSALES','Holmes','UYQC67',25,10,9,13,3),(62,'HOUSE','Ivana','GPLZ96',25,8,20,13,3),(63,'SCOTT','Ira','NDJY15',25,6,7,13,3),(64,'CASE','Ian','JLPV28',25,3,1,13,3),(65,'ROBBINS','Peter','CJVW19',25,10,22,14,6),(66,'FRANCO','Harriet','YPDC73',25,14,20,14,6),(67,'MOON','Sharon','NBQA74',25,2,1,14,6),(68,'ELLIOTT','Virginia','DWVX45',25,6,21,14,6),(69,'WHITNEY','Simone','GQNP42',25,3,8,14,6),(70,'AGUIRRE','Alexander','HVJL50',25,14,27,15,6),(71,'PETERSON','Paula','KEXY60',25,4,3,15,6),(72,'CRANE','Guinevere','QYFS83',25,12,5,15,6),(73,'CLAYTON','Boris','CRLE93',25,13,6,15,6),(74,'SHEPARD','Thor','YWCG49',25,6,2,15,6),(75,'BURT','Eagan','TQJK34',25,7,9,16,2),(76,'CONNER','Dillon','RVMA23',25,15,15,16,2),(77,'LUNA','Jaden','KZLV14',25,12,20,16,2),(78,'MCMILLAN','Nathan','VBXH94',25,15,9,16,2),(79,'BECK','Colleen','TLJA23',25,5,22,16,2),(80,'LEFRANCOIS','Jean','YLF246',15,1,18,16,2),(81,'Service RH','Direction','RH001',0,0,0,1,1),(82,'Service RH','Administratif','RH002',0,0,0,1,1),(83,'Service RH','Technicien','RH003',0,0,0,1,1),(84,'Service RH','Production1','RH004',0,0,0,1,1),(85,'Service RH','Production2','RH005',0,0,0,1,1),(86,'Service RH','Communication','RH006',0,0,0,1,1);
/*!40000 ALTER TABLE `employe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-10 16:48:41
