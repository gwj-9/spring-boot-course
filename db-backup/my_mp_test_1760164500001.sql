-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: my_mp_test
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：1-启用，0-禁用',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除：1-已删除，0-未删除',
  `version` int DEFAULT '0' COMMENT '版本号（乐观锁）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_username` (`username`),
  UNIQUE KEY `uk_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户账户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,'aaron_zhang','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','张亚伦','aaron.zhang@example.com','13811223344','https://avatar.example.com/aaron.jpg',1,0,0,'2025-09-18 08:15:30','2025-09-18 08:15:30'),(2,'cindy_li','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','李欣迪','cindy.li@example.com','13955667788','https://avatar.example.com/cindy.jpg',1,0,1,'2025-09-18 08:42:15','2025-09-18 09:30:22'),(3,'david_wang','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','王大卫','david.wang@example.com','13799887766',NULL,1,0,0,'2025-09-18 09:05:40','2025-09-18 09:05:40'),(4,'emily_chen','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','陈艾米','emily.chen@example.com','13644556677','https://avatar.example.com/emily.jpg',0,0,0,'2025-09-18 09:33:25','2025-09-18 09:33:25'),(5,'felix_zhao','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','赵菲利','felix.zhao@example.com','13588776655','https://avatar.example.com/felix.jpg',1,0,2,'2025-09-18 10:12:10','2025-09-18 11:45:30'),(6,'grace_sun','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','孙格蕾丝','grace.sun@example.com','13433221100',NULL,1,0,0,'2025-09-18 10:45:50','2025-09-18 10:45:50'),(7,'henry_zhou','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','周亨利','henry.zhou@example.com','13377665544','https://avatar.example.com/henry.jpg',1,0,0,'2025-09-18 11:08:35','2025-09-18 11:08:35'),(8,'iris_wu','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','吴艾瑞斯','iris.wu@example.com','13222334455','https://avatar.example.com/iris.jpg',0,0,1,'2025-09-18 11:30:45','2025-09-18 13:20:15'),(9,'jack_yang','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','杨杰克','jack.yang@example.com','13166554433',NULL,1,0,0,'2025-09-18 13:45:20','2025-09-18 13:45:20'),(10,'kelly_huang','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','黄凯莉','kelly.huang@example.com','13011223344','https://avatar.example.com/kelly.jpg',1,0,3,'2025-09-18 14:10:10','2025-09-18 15:30:45'),(11,'leo_zhu','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','朱利奥','leo.zhu@example.com','18955667788','https://avatar.example.com/leo.jpg',1,0,0,'2025-09-18 14:35:55','2025-09-18 14:35:55'),(12,'mona_gao','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','高莫娜','mona.gao@example.com','18899887766','https://avatar.example.com/mona.jpg',1,0,1,'2025-09-18 15:02:30','2025-09-18 15:50:30'),(13,'nathan_song','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','宋内森','nathan.song@example.com','18744556677',NULL,1,0,0,'2025-09-18 15:28:15','2025-09-18 15:28:15'),(14,'olivia_hu','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','胡奥利维亚','olivia.hu@example.com','18688776655','https://avatar.example.com/olivia.jpg',1,0,0,'2025-09-18 15:55:40','2025-09-18 15:55:40'),(15,'paul_chen','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','陈保罗','paul.chen@example.com','18533221100','https://avatar.example.com/paul.jpg',0,0,2,'2025-09-18 16:12:25','2025-09-18 16:40:20'),(16,'queenie_lin','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','林奎妮','queenie.lin@example.com','18477665544',NULL,1,0,0,'2025-09-18 16:30:10','2025-09-18 16:30:10'),(17,'ryan_mao','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','毛瑞安','ryan.mao@example.com','18322334455','https://avatar.example.com/ryan.jpg',1,0,0,'2025-09-18 16:55:35','2025-09-18 16:55:35'),(18,'susan_deng','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','邓苏珊','susan.deng@example.com','18266554433','https://avatar.example.com/susan.jpg',1,0,1,'2025-09-18 17:10:50','2025-09-18 17:30:15'),(19,'tony_liu','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','刘托尼','tony.liu@example.com','18111223344',NULL,1,0,0,'2025-09-18 17:35:25','2025-09-18 17:35:25'),(20,'vicky_zhang','$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi','张维姬','vicky.zhang@example.com','18055667788','https://avatar.example.com/vicky.jpg',1,1,0,'2025-09-18 17:50:40','2025-09-18 17:50:40');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-11 14:35:00
