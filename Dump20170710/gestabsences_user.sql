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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `id_employe` int(11) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_employe_idx` (`id_employe`),
  KEY `fk_users_role_idx` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'damian.humphrey@entreprise.com','BJK36ELZ',1,2),(2,'keely.nichols@entreprise.com','HBU72WZM',2,1),(3,'jacob.stevens@entreprise.com','XNW54VSD',3,1),(4,'mikayla.potts@entreprise.com','URX87MTE',4,1),(5,'chantale.mcintosh@entreprise.com','MXW39NZL',5,1),(6,'virginia.melendez@entreprise.com','UCW58VTY',6,1),(7,'ivan.weaver@entreprise.com','BVF20XZG',7,1),(8,'paloma.velazquez@entreprise.com','FDP58CQL',8,1),(9,'mackensie.fields@entreprise.com','HVZ37UPJ',9,1),(10,'katelyn.flores@entreprise.com','PRB80DLW',10,1),(11,'lee.berry@entreprise.com','BXG70DRV',11,1),(12,'ishmael.walls@entreprise.com','FNQ61YJB',12,1),(13,'irene.blevins@entreprise.com','XMK92BVJ',13,1),(14,'lisandra.walter@entreprise.com','DTF23LVH',14,1),(15,'rae.schwartz@entreprise.com','BQN63EXJ',15,1),(16,'ferdinand.perez@entreprise.com','VEM78HKN',16,1),(17,'jessamine.owens@entreprise.com','ARZ70ESX',17,1),(18,'riley.keith@entreprise.com','DHA39GSV',18,1),(19,'marvin.wooten@entreprise.com','CVF16BQM',19,1),(20,'ocean.glenn@entreprise.com','YFH62CAT',20,2),(21,'anika.wallace@entreprise.com','LWK73GFB',21,2),(22,'hope.ortiz@entreprise.com','UVK96MDX',22,2),(23,'jacob.dickson@entreprise.com','NUP39JHF',23,2),(24,'alyssa.york@entreprise.com','UXC96TNV',24,2),(25,'brooke.leblanc@entreprise.com','DUL50BVZ',25,2),(26,'veronica.aguirre@entreprise.com','ARY13MXE',26,2),(27,'madison.rivas@entreprise.com','VQF94RBA',27,2),(28,'henry.dickerson@entreprise.com','YFX41HTW',28,2),(29,'jean.lefrancois@laposte.net','GJW84PEY',29,2),(30,'hadassah.burch@entreprise.com','GAP36TSE',30,1),(31,'montana.bray@entreprise.com','BGF91RXH',31,1),(32,'julie.frederick@entreprise.com','GUT31DHW',32,1),(33,'sydnee.copeland@entreprise.com','RMG29BWC',33,1),(34,'christian.vang@entreprise.com','SGX54WLV',34,1),(35,'sonia.prince@entreprise.com','VBP57LZD',35,1),(36,'adele.trujillo@entreprise.com','RST49WEH',36,1),(37,'cody.weeks@entreprise.com','WTK61RCQ',37,1),(38,'joan.trujillo@entreprise.com','PVY21SWU',38,1),(39,'regina.beard@entreprise.com','JPN45UGW',39,1),(40,'rashad.estes@entreprise.com','GEN93HRS',40,1),(41,'lacy.hurley@entreprise.com','BYH69JLS',41,1),(42,'sacha.waller@entreprise.com','GWU41FZA',42,1),(43,'kareem.warner@entreprise.com','SHD34YBT',43,1),(44,'brynn.stewart@entreprise.com','BQR74TEY',44,1),(45,'keely.bentley@entreprise.com','BHQ18ELY',45,1),(46,'autumn.frederick@entreprise.com','MZL72ERU',46,1),(47,'howard.norman@entreprise.com','JHT26PEY',47,1),(48,'bryan.winter@entreprise.com','VRB40ULA',48,1),(49,'steven.merrill@entreprise.com','NPW32JQR',49,1),(50,'ulysses.nguyen@entreprise.com','DRY79HSG',50,1),(51,'shoshana.conway@entreprise.com','YWX19CUS',51,1),(52,'kasimir.carver@entreprise.com','YLX14JPA',52,1),(53,'fulton.diaz@entreprise.com','JSC20VFU',53,1),(54,'quyn.haley@entreprise.com','ZUM62CGL',54,1),(55,'meghan.weiss@entreprise.com','QLV46RZA',55,1),(56,'anjolie.williamson@entreprise.com','BPE49THX',56,1),(57,'lars.ayers@entreprise.com','WNF84ZAH',57,1),(58,'skyler.marquez@entreprise.com','FLR29EBW',58,1),(59,'carissa.oneal@entreprise.com','BMC30ZAQ',59,1),(60,'nigel.frost@entreprise.com','TKE62PWZ',60,1),(61,'holmes.rosales@entreprise.com','JQD78VRH',61,1),(62,'ivana.house@entreprise.com','LPB19GWF',62,1),(63,'ira.scott@entreprise.com','WFB43QJY',63,1),(64,'ian.case@entreprise.com','TBJ52YKH',64,1),(65,'peter.robbins@entreprise.com','YSM38BCF',65,1),(66,'harriet.franco@entreprise.com','JUF32MZR',66,1),(67,'sharon.moon@entreprise.com','VMW53ALE',67,1),(68,'virginia.elliott@entreprise.com','EPB98WYJ',68,1),(69,'simone.whitney@entreprise.com','VJL86KDR',69,1),(70,'alexander.aguirre@entreprise.com','DAH53RFL',70,1),(71,'paula.peterson@entreprise.com','LJZ39HGR',71,1),(72,'guinevere.crane@entreprise.com','NYL53ECV',72,1),(73,'boris.clayton@entreprise.com','MLJ95HVS',73,1),(74,'thor.shepard@entreprise.com','WFC41EMV',74,1),(75,'eagan.burt@entreprise.com','RUB25ZEP',75,1),(76,'dillon.conner@entreprise.com','RYN53MVX',76,1),(77,'jaden.luna@entreprise.com','QZD89JKR',77,1),(78,'nathan.mcmillan@entreprise.com','JMT65AQX',78,1),(79,'colleen.beck@entreprise.com','LXN41GTB',79,1),(80,'jeanlefrancois.simplon@gmail.com','ABCD1234',80,2),(81,'serviceRhDirection@gmail.com','IDRH0001',81,3),(82,'serviceRhAdministratif@gmail.com','IDRH0002',82,3),(83,'serviceRhTechnicien@gmail.com','IDRH0003',83,3),(84,'serviceRhProduction1@gmail.com','IDRH0004',84,3),(85,'serviceRhProduction2@gmail.com','IDRH0005',85,3),(86,'serviceRhComminucation@gmail.com','IDRH0006',86,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-10 16:48:42
