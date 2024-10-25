-- MySQL dump 10.13  Distrib 5.7.44, for Linux (x86_64)
--
-- Host: localhost    Database: cl26103485
-- ------------------------------------------------------
-- Server version	5.7.44

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
-- Current Database: `cl26103485`
--

/*!40000 DROP DATABASE IF EXISTS `cl26103485`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `cl26103485` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `cl26103485`;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `value` longtext COMMENT '值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='轮播图';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'2024-10-24 08:43:26','swiper1','file/swiperPicture1.jpg'),(2,'2024-10-24 08:43:26','swiper2','file/swiperPicture2.jpg'),(3,'2024-10-24 08:43:26','swiper3','file/swiperPicture3.jpg');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) DEFAULT NULL COMMENT '帖子标题',
  `content` longtext NOT NULL COMMENT '帖子内容',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `avatarurl` longtext COMMENT '头像',
  `isdone` varchar(200) DEFAULT NULL COMMENT '状态',
  `is_top` int(11) DEFAULT '0' COMMENT '是否置顶',
  `top_time` datetime DEFAULT NULL COMMENT '置顶时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='论坛交流';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` VALUES (1,'2024-10-24 08:43:25','帖子标题1','帖子内容1',0,'用户名1','file/forumAvatarurl1.jpg,file/forumAvatarurl2.jpg,file/forumAvatarurl3.jpg','开放',1,'2024-10-24 16:43:25',1),(2,'2024-10-24 08:43:25','帖子标题2','帖子内容2',0,'用户名2','file/forumAvatarurl2.jpg,file/forumAvatarurl3.jpg,file/forumAvatarurl4.jpg','开放',0,'2024-10-24 16:57:54',2),(3,'2024-10-24 08:43:25','帖子标题3','帖子内容3',0,'用户名3','file/forumAvatarurl3.jpg,file/forumAvatarurl4.jpg,file/forumAvatarurl5.jpg','开放',0,'2024-10-24 16:57:54',3),(4,'2024-10-24 08:43:25','帖子标题4','帖子内容4',0,'用户名4','file/forumAvatarurl4.jpg,file/forumAvatarurl5.jpg,file/forumAvatarurl6.jpg','开放',0,'2024-10-24 16:57:54',4),(5,'2024-10-24 08:43:25','帖子标题5','帖子内容5',0,'用户名5','file/forumAvatarurl5.jpg,file/forumAvatarurl6.jpg,file/forumAvatarurl7.jpg','开放',0,'2024-10-24 16:57:54',5),(6,'2024-10-24 08:43:25','帖子标题6','帖子内容6',0,'用户名6','file/forumAvatarurl6.jpg,file/forumAvatarurl7.jpg,file/forumAvatarurl8.jpg','开放',0,'2024-10-24 16:57:54',6),(7,'2024-10-24 08:43:25','帖子标题7','帖子内容7',0,'用户名7','file/forumAvatarurl7.jpg,file/forumAvatarurl8.jpg,file/forumAvatarurl9.jpg','开放',0,'2024-10-24 16:57:54',7),(8,'2024-10-24 08:43:25','帖子标题8','帖子内容8',0,'用户名8','file/forumAvatarurl8.jpg,file/forumAvatarurl9.jpg,file/forumAvatarurl10.jpg','开放',0,'2024-10-24 16:57:54',8),(9,'2024-10-24 08:43:25','帖子标题9','帖子内容9',0,'用户名9','file/forumAvatarurl9.jpg,file/forumAvatarurl10.jpg,file/forumAvatarurl11.jpg','开放',0,'2024-10-24 16:57:54',9),(10,'2024-10-24 08:43:25','帖子标题10','帖子内容10',0,'用户名10','file/forumAvatarurl10.jpg,file/forumAvatarurl11.jpg,file/forumAvatarurl12.jpg','开放',1,'2024-10-24 16:57:55',10);
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiaoshi`
--

DROP TABLE IF EXISTS `jiaoshi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiaoshi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoshigonghao` varchar(200) NOT NULL COMMENT '教师工号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `jiaoshixingming` varchar(200) NOT NULL COMMENT '教师姓名',
  `touxiang` longtext COMMENT '头像',
  `lianxidianhua` varchar(200) DEFAULT NULL COMMENT '联系电话',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `zhuanye` varchar(200) DEFAULT NULL COMMENT '专业',
  `sfsh` varchar(200) DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COMMENT '回复内容',
  `suoshoukemu` varchar(200) DEFAULT NULL COMMENT '所授科目',
  `keyueshijian` varchar(200) DEFAULT NULL COMMENT '可约时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jiaoshigonghao` (`jiaoshigonghao`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='教师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiaoshi`
--

LOCK TABLES `jiaoshi` WRITE;
/*!40000 ALTER TABLE `jiaoshi` DISABLE KEYS */;
INSERT INTO `jiaoshi` VALUES (31,'2024-10-24 08:43:25','教师工号1','123456','教师姓名1','file/jiaoshiTouxiang1.jpg','19819881111','男','专业1','是','','所授科目1','可约时间1'),(32,'2024-10-24 08:43:25','教师工号2','123456','教师姓名2','file/jiaoshiTouxiang2.jpg','19819881112','男','专业2','是','','所授科目2','可约时间2'),(33,'2024-10-24 08:43:25','教师工号3','123456','教师姓名3','file/jiaoshiTouxiang3.jpg','19819881113','男','专业3','是','','所授科目3','可约时间3'),(34,'2024-10-24 08:43:25','教师工号4','123456','教师姓名4','file/jiaoshiTouxiang4.jpg','19819881114','男','专业4','是','','所授科目4','可约时间4'),(35,'2024-10-24 08:43:25','教师工号5','123456','教师姓名5','file/jiaoshiTouxiang5.jpg','19819881115','男','专业5','是','','所授科目5','可约时间5'),(36,'2024-10-24 08:43:25','教师工号6','123456','教师姓名6','file/jiaoshiTouxiang6.jpg','19819881116','男','专业6','是','','所授科目6','可约时间6'),(37,'2024-10-24 08:43:25','教师工号7','123456','教师姓名7','file/jiaoshiTouxiang7.jpg','19819881117','男','专业7','是','','所授科目7','可约时间7'),(38,'2024-10-24 08:43:25','教师工号8','123456','教师姓名8','file/jiaoshiTouxiang8.jpg','19819881118','男','专业8','是','','所授科目8','可约时间8'),(39,'2024-10-24 08:43:25','教师工号9','123456','教师姓名9','file/jiaoshiTouxiang9.jpg','19819881119','男','专业9','是','','所授科目9','可约时间9'),(40,'2024-10-24 08:43:25','教师工号10','123456','教师姓名10','file/jiaoshiTouxiang10.jpg','198198811110','男','专业10','是','','所授科目10','可约时间10');
/*!40000 ALTER TABLE `jiaoshi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiaoshikongbaiyemian`
--

DROP TABLE IF EXISTS `jiaoshikongbaiyemian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiaoshikongbaiyemian` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `neirong` longtext COMMENT '内容',
  `jiaoshigonghao` varchar(200) DEFAULT NULL COMMENT '教师工号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='教师空白页面';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiaoshikongbaiyemian`
--

LOCK TABLES `jiaoshikongbaiyemian` WRITE;
/*!40000 ALTER TABLE `jiaoshikongbaiyemian` DISABLE KEYS */;
INSERT INTO `jiaoshikongbaiyemian` VALUES (1,'2024-10-24 08:43:26','内容1','教师工号1'),(2,'2024-10-24 08:43:26','内容2','教师工号2'),(3,'2024-10-24 08:43:26','内容3','教师工号3'),(4,'2024-10-24 08:43:26','内容4','教师工号4'),(5,'2024-10-24 08:43:26','内容5','教师工号5'),(6,'2024-10-24 08:43:26','内容6','教师工号6'),(7,'2024-10-24 08:43:26','内容7','教师工号7'),(8,'2024-10-24 08:43:26','内容8','教师工号8'),(9,'2024-10-24 08:43:26','内容9','教师工号9'),(10,'2024-10-24 08:43:26','内容10','教师工号10');
/*!40000 ALTER TABLE `jiaoshikongbaiyemian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiaoxueziyuan`
--

DROP TABLE IF EXISTS `jiaoxueziyuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiaoxueziyuan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ziyuanmingcheng` varchar(200) NOT NULL COMMENT '资源名称',
  `fengmian` longtext COMMENT '封面',
  `ziyuanleixing` varchar(200) NOT NULL COMMENT '资源类型',
  `ziyuanfujian` longtext COMMENT '资源附件',
  `ziyuanjianshu` longtext COMMENT '资源简述',
  `ziyuanxiangqing` longtext COMMENT '资源详情',
  `jiaoshigonghao` varchar(200) DEFAULT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) DEFAULT NULL COMMENT '教师姓名',
  `storeup_number` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='教学资源';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiaoxueziyuan`
--

LOCK TABLES `jiaoxueziyuan` WRITE;
/*!40000 ALTER TABLE `jiaoxueziyuan` DISABLE KEYS */;
INSERT INTO `jiaoxueziyuan` VALUES (1,'2024-10-24 08:43:25','资源名称1','file/jiaoxueziyuanFengmian1.jpg,file/jiaoxueziyuanFengmian2.jpg,file/jiaoxueziyuanFengmian3.jpg','资源类型1','','资源简述1','资源详情1','教师工号1','教师姓名1',1),(2,'2024-10-24 08:43:25','资源名称2','file/jiaoxueziyuanFengmian2.jpg,file/jiaoxueziyuanFengmian3.jpg,file/jiaoxueziyuanFengmian4.jpg','资源类型2','','资源简述2','资源详情2','教师工号2','教师姓名2',2),(3,'2024-10-24 08:43:25','资源名称3','file/jiaoxueziyuanFengmian3.jpg,file/jiaoxueziyuanFengmian4.jpg,file/jiaoxueziyuanFengmian5.jpg','资源类型3','','资源简述3','资源详情3','教师工号3','教师姓名3',3),(4,'2024-10-24 08:43:25','资源名称4','file/jiaoxueziyuanFengmian4.jpg,file/jiaoxueziyuanFengmian5.jpg,file/jiaoxueziyuanFengmian6.jpg','资源类型4','','资源简述4','资源详情4','教师工号4','教师姓名4',4),(5,'2024-10-24 08:43:25','资源名称5','file/jiaoxueziyuanFengmian5.jpg,file/jiaoxueziyuanFengmian6.jpg,file/jiaoxueziyuanFengmian7.jpg','资源类型5','','资源简述5','资源详情5','教师工号5','教师姓名5',5),(6,'2024-10-24 08:43:25','资源名称6','file/jiaoxueziyuanFengmian6.jpg,file/jiaoxueziyuanFengmian7.jpg,file/jiaoxueziyuanFengmian8.jpg','资源类型6','','资源简述6','资源详情6','教师工号6','教师姓名6',6),(7,'2024-10-24 08:43:25','资源名称7','file/jiaoxueziyuanFengmian7.jpg,file/jiaoxueziyuanFengmian8.jpg,file/jiaoxueziyuanFengmian9.jpg','资源类型7','','资源简述7','资源详情7','教师工号7','教师姓名7',7),(8,'2024-10-24 08:43:25','资源名称8','file/jiaoxueziyuanFengmian8.jpg,file/jiaoxueziyuanFengmian9.jpg,file/jiaoxueziyuanFengmian10.jpg','资源类型8','','资源简述8','资源详情8','教师工号8','教师姓名8',8),(9,'2024-10-24 08:43:25','资源名称9','file/jiaoxueziyuanFengmian9.jpg,file/jiaoxueziyuanFengmian10.jpg,file/jiaoxueziyuanFengmian11.jpg','资源类型9','','资源简述9','资源详情9','教师工号9','教师姓名9',9),(10,'2024-10-24 08:43:25','资源名称10','file/jiaoxueziyuanFengmian10.jpg,file/jiaoxueziyuanFengmian11.jpg,file/jiaoxueziyuanFengmian12.jpg','资源类型10','','资源简述10','资源详情10','教师工号10','教师姓名10',10);
/*!40000 ALTER TABLE `jiaoxueziyuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiazhang`
--

DROP TABLE IF EXISTS `jiazhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiazhang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiazhangzhanghao` varchar(200) NOT NULL COMMENT '家长账号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `jiazhangxingming` varchar(200) NOT NULL COMMENT '家长姓名',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `shoujihaoma` varchar(200) DEFAULT NULL COMMENT '手机号码',
  `xuehao` varchar(200) DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jiazhangzhanghao` (`jiazhangzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8 COMMENT='家长';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiazhang`
--

LOCK TABLES `jiazhang` WRITE;
/*!40000 ALTER TABLE `jiazhang` DISABLE KEYS */;
INSERT INTO `jiazhang` VALUES (151,'2024-10-24 08:43:25','家长账号1','123456','家长姓名1','男','19819881111','学号1'),(152,'2024-10-24 08:43:25','家长账号2','123456','家长姓名2','男','19819881112','学号2'),(153,'2024-10-24 08:43:25','家长账号3','123456','家长姓名3','男','19819881113','学号3'),(154,'2024-10-24 08:43:25','家长账号4','123456','家长姓名4','男','19819881114','学号4'),(155,'2024-10-24 08:43:25','家长账号5','123456','家长姓名5','男','19819881115','学号5'),(156,'2024-10-24 08:43:25','家长账号6','123456','家长姓名6','男','19819881116','学号6'),(157,'2024-10-24 08:43:25','家长账号7','123456','家长姓名7','男','19819881117','学号7'),(158,'2024-10-24 08:43:25','家长账号8','123456','家长姓名8','男','19819881118','学号8'),(159,'2024-10-24 08:43:25','家长账号9','123456','家长姓名9','男','19819881119','学号9'),(160,'2024-10-24 08:43:25','家长账号10','123456','家长姓名10','男','198198811110','学号10');
/*!40000 ALTER TABLE `jiazhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiazhangkongbaiyemian`
--

DROP TABLE IF EXISTS `jiazhangkongbaiyemian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiazhangkongbaiyemian` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `neirong` longtext COMMENT '内容',
  `jiazhangzhanghao` varchar(200) DEFAULT NULL COMMENT '家长账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='家长空白页面';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiazhangkongbaiyemian`
--

LOCK TABLES `jiazhangkongbaiyemian` WRITE;
/*!40000 ALTER TABLE `jiazhangkongbaiyemian` DISABLE KEYS */;
INSERT INTO `jiazhangkongbaiyemian` VALUES (1,'2024-10-24 08:43:26','内容1','家长账号1'),(2,'2024-10-24 08:43:26','内容2','家长账号2'),(3,'2024-10-24 08:43:26','内容3','家长账号3'),(4,'2024-10-24 08:43:26','内容4','家长账号4'),(5,'2024-10-24 08:43:26','内容5','家长账号5'),(6,'2024-10-24 08:43:26','内容6','家长账号6'),(7,'2024-10-24 08:43:26','内容7','家长账号7'),(8,'2024-10-24 08:43:26','内容8','家长账号8'),(9,'2024-10-24 08:43:26','内容9','家长账号9'),(10,'2024-10-24 08:43:26','内容10','家长账号10');
/*!40000 ALTER TABLE `jiazhangkongbaiyemian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiazhangyuyuejiaoshi`
--

DROP TABLE IF EXISTS `jiazhangyuyuejiaoshi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiazhangyuyuejiaoshi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoshigonghao` varchar(200) DEFAULT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) DEFAULT NULL COMMENT '教师姓名',
  `jiazhangzhanghao` varchar(200) DEFAULT NULL COMMENT '家长账号',
  `jiazhangxingming` varchar(200) DEFAULT NULL COMMENT '家长姓名',
  `xuehao` varchar(200) DEFAULT NULL COMMENT '学号',
  `yuyueshijian` datetime DEFAULT NULL COMMENT '预约时间',
  `shhf` longtext COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='家长预约教师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiazhangyuyuejiaoshi`
--

LOCK TABLES `jiazhangyuyuejiaoshi` WRITE;
/*!40000 ALTER TABLE `jiazhangyuyuejiaoshi` DISABLE KEYS */;
INSERT INTO `jiazhangyuyuejiaoshi` VALUES (1,'2024-10-24 08:43:25','教师工号1','教师姓名1','家长账号1','家长姓名1','学号1','2024-10-24 16:43:25',''),(2,'2024-10-24 08:43:25','教师工号2','教师姓名2','家长账号2','家长姓名2','学号2','2024-10-24 16:43:25',''),(3,'2024-10-24 08:43:25','教师工号3','教师姓名3','家长账号3','家长姓名3','学号3','2024-10-24 16:43:25',''),(4,'2024-10-24 08:43:25','教师工号4','教师姓名4','家长账号4','家长姓名4','学号4','2024-10-24 16:43:25',''),(5,'2024-10-24 08:43:25','教师工号5','教师姓名5','家长账号5','家长姓名5','学号5','2024-10-24 16:43:25',''),(6,'2024-10-24 08:43:25','教师工号6','教师姓名6','家长账号6','家长姓名6','学号6','2024-10-24 16:43:25',''),(7,'2024-10-24 08:43:25','教师工号7','教师姓名7','家长账号7','家长姓名7','学号7','2024-10-24 16:43:25',''),(8,'2024-10-24 08:43:25','教师工号8','教师姓名8','家长账号8','家长姓名8','学号8','2024-10-24 16:43:25',''),(9,'2024-10-24 08:43:25','教师工号9','教师姓名9','家长账号9','家长姓名9','学号9','2024-10-24 16:43:25',''),(10,'2024-10-24 08:43:25','教师工号10','教师姓名10','家长账号10','家长姓名10','学号10','2024-10-24 16:43:25','');
/*!40000 ALTER TABLE `jiazhangyuyuejiaoshi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menujson` longtext NOT NULL COMMENT '菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'2024-10-24 08:43:26','[{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-addressbook\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"config\",\"menu\":\"轮播图\",\"menuJump\":\"列表\",\"tableName\":\"config\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"users\",\"menu\":\"管理员\",\"menuJump\":\"列表\",\"tableName\":\"users\"}],\"fontClass\":\"icon-common12\",\"menu\":\"管理员管理\",\"unicode\":\"&#xedf4;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\",\"家长预约\",\"学生预约\"],\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\"],\"classname\":\"jiaoshi\",\"menu\":\"教师\",\"menuJump\":\"列表\",\"tableName\":\"jiaoshi\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"学生性别统计\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-paint\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"学生性别统计\",\"首页总数\",\"首页统计\"],\"classname\":\"xuesheng\",\"menu\":\"学生\",\"menuJump\":\"列表\",\"tableName\":\"xuesheng\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiazhang\",\"menu\":\"家长\",\"menuJump\":\"列表\",\"tableName\":\"jiazhang\"}],\"fontClass\":\"icon-user6\",\"menu\":\"用户管理\",\"unicode\":\"&#xef9c;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"提交\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"zuoyexinxi\",\"menu\":\"作业信息\",\"menuJump\":\"列表\",\"tableName\":\"zuoyexinxi\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"批改\"],\"appFrontIcon\":\"cuIcon-circle\",\"buttons\":[\"查看\",\"删除\",\"修改\"],\"classname\":\"tijiaozuoye\",\"menu\":\"提交作业\",\"menuJump\":\"列表\",\"tableName\":\"tijiaozuoye\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"查看\",\"删除\",\"修改\"],\"classname\":\"zuoyepigai\",\"menu\":\"作业批改\",\"menuJump\":\"列表\",\"tableName\":\"zuoyepigai\"}],\"fontClass\":\"icon-common20\",\"menu\":\"作业信息管理\",\"unicode\":\"&#xee02;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"回复\"],\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"查看\",\"删除\",\"回复\",\"修改\"],\"classname\":\"messages\",\"menu\":\"留言板\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"fontClass\":\"icon-common49\",\"menu\":\"留言板管理\",\"unicode\":\"&#xef79;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"查看评论\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"查看评论\"],\"classname\":\"forum\",\"menu\":\"论坛交流\",\"tableName\":\"forum\"}],\"fontClass\":\"icon-common21\",\"menu\":\"论坛管理\",\"unicode\":\"&#xee03;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-cardboard\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"news\",\"menu\":\"公告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"fontClass\":\"icon-common23\",\"menu\":\"公告信息管理\",\"unicode\":\"&#xee05;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiaoshikongbaiyemian\",\"menu\":\"教师空白页面\",\"menuJump\":\"列表\",\"tableName\":\"jiaoshikongbaiyemian\"}],\"fontClass\":\"icon-common34\",\"menu\":\"教师空白页面管理\",\"unicode\":\"&#xee85;\"},{\"child\":[{\"allButtons\":[\"菜单管理\"],\"appFrontIcon\":\"cuIcon-album\",\"buttons\":[\"菜单管理\"],\"classname\":\"menu\",\"menu\":\"菜单\",\"menuJump\":\"列表\",\"tableName\":\"menu\"}],\"fontClass\":\"icon-common39\",\"menu\":\"菜单管理\",\"unicode\":\"&#xeeba;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-news\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiazhangkongbaiyemian\",\"menu\":\"家长空白页面\",\"menuJump\":\"列表\",\"tableName\":\"jiazhangkongbaiyemian\"}],\"fontClass\":\"icon-common8\",\"menu\":\"家长空白页面管理\",\"unicode\":\"&#xedb6;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"xueshengkongbaiyemian\",\"menu\":\"学生空白页面\",\"menuJump\":\"列表\",\"tableName\":\"xueshengkongbaiyemian\"}],\"fontClass\":\"icon-common4\",\"menu\":\"空白页面1管理\",\"unicode\":\"&#xedab;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\"],\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"查看\",\"删除\"],\"classname\":\"jiazhangyuyuejiaoshi\",\"menu\":\"家长预约教师\",\"menuJump\":\"列表\",\"tableName\":\"jiazhangyuyuejiaoshi\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\"],\"appFrontIcon\":\"cuIcon-form\",\"buttons\":[\"查看\",\"删除\"],\"classname\":\"xueshengyuyuejiaoshi\",\"menu\":\"学生预约教师\",\"menuJump\":\"列表\",\"tableName\":\"xueshengyuyuejiaoshi\"}],\"fontClass\":\"icon-common5\",\"menu\":\"预约信息管理\",\"unicode\":\"&#xedae;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"资源类型\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"查看\",\"修改\",\"删除\",\"资源类型\",\"首页总数\",\"首页统计\"],\"classname\":\"jiaoxueziyuan\",\"menu\":\"教学资源\",\"menuJump\":\"列表\",\"tableName\":\"jiaoxueziyuan\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-album\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"ziyuanleixing\",\"menu\":\"资源类型\",\"menuJump\":\"列表\",\"tableName\":\"ziyuanleixing\"}],\"fontClass\":\"icon-common11\",\"menu\":\"教学资源管理\",\"unicode\":\"&#xeded;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"提交\"],\"classname\":\"zuoyexinxi\",\"menu\":\"作业信息\",\"menuJump\":\"列表\",\"tableName\":\"zuoyexinxi\"}],\"menu\":\"作业信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"公告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"新闻资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"forum\",\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"menuJump\":\"列表\",\"tableName\":\"forum\",\"unicode\":\"&#xedab;\"}],\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"unicode\":\"&#xedab;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"messages\",\"menu\":\"留言板\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"menu\":\"留言板管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"查看\"],\"classname\":\"jiaoxueziyuan\",\"menu\":\"教学资源\",\"menuJump\":\"列表\",\"tableName\":\"jiaoxueziyuan\"}],\"menu\":\"教学资源管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-full\",\"buttons\":[\"查看\",\"家长预约\",\"学生预约\"],\"classname\":\"jiaoshi\",\"menu\":\"教师\",\"menuJump\":\"列表\",\"tableName\":\"jiaoshi\"}],\"menu\":\"教师管理\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"管理员\",\"tableName\":\"users\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"提交\"],\"appFrontIcon\":\"cuIcon-vip\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"zuoyexinxi\",\"menu\":\"作业信息\",\"menuJump\":\"列表\",\"tableName\":\"zuoyexinxi\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"批改\"],\"appFrontIcon\":\"cuIcon-circle\",\"buttons\":[\"查看\",\"删除\",\"批改\"],\"classname\":\"tijiaozuoye\",\"menu\":\"提交作业\",\"menuJump\":\"列表\",\"tableName\":\"tijiaozuoye\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"查看\",\"删除\"],\"classname\":\"zuoyepigai\",\"menu\":\"作业批改\",\"menuJump\":\"列表\",\"tableName\":\"zuoyepigai\"}],\"fontClass\":\"icon-common20\",\"menu\":\"作业信息管理\",\"unicode\":\"&#xee02;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiaoshikongbaiyemian\",\"menu\":\"教师空白页面\",\"menuJump\":\"列表\",\"tableName\":\"jiaoshikongbaiyemian\"}],\"fontClass\":\"icon-common34\",\"menu\":\"教师空白页面管理\",\"unicode\":\"&#xee85;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\"],\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"查看\",\"删除\",\"审核\"],\"classname\":\"jiazhangyuyuejiaoshi\",\"menu\":\"家长预约教师\",\"menuJump\":\"列表\",\"tableName\":\"jiazhangyuyuejiaoshi\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\"],\"appFrontIcon\":\"cuIcon-form\",\"buttons\":[\"删除\",\"查看\",\"审核\"],\"classname\":\"xueshengyuyuejiaoshi\",\"menu\":\"学生预约教师\",\"menuJump\":\"列表\",\"tableName\":\"xueshengyuyuejiaoshi\"}],\"fontClass\":\"icon-common5\",\"menu\":\"预约信息管理\",\"unicode\":\"&#xedae;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"资源类型\",\"首页总数\",\"首页统计\"],\"appFrontIcon\":\"cuIcon-attentionfavor\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"首页统计\",\"资源类型\",\"首页总数\"],\"classname\":\"jiaoxueziyuan\",\"menu\":\"教学资源\",\"menuJump\":\"列表\",\"tableName\":\"jiaoxueziyuan\"}],\"fontClass\":\"icon-common11\",\"menu\":\"教学资源管理\",\"unicode\":\"&#xeded;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"提交\"],\"classname\":\"zuoyexinxi\",\"menu\":\"作业信息\",\"menuJump\":\"列表\",\"tableName\":\"zuoyexinxi\"}],\"menu\":\"作业信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"公告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"新闻资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"forum\",\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"menuJump\":\"列表\",\"tableName\":\"forum\",\"unicode\":\"&#xedab;\"}],\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"unicode\":\"&#xedab;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"messages\",\"menu\":\"留言板\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"menu\":\"留言板管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"查看\"],\"classname\":\"jiaoxueziyuan\",\"menu\":\"教学资源\",\"menuJump\":\"列表\",\"tableName\":\"jiaoxueziyuan\"}],\"menu\":\"教学资源管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-full\",\"buttons\":[\"查看\",\"家长预约\",\"学生预约\"],\"classname\":\"jiaoshi\",\"menu\":\"教师\",\"menuJump\":\"列表\",\"tableName\":\"jiaoshi\"}],\"menu\":\"教师管理\"}],\"hasBackLogin\":\"是\",\"hasBackRegister\":\"是\",\"hasFrontLogin\":\"否\",\"hasFrontRegister\":\"否\",\"roleName\":\"教师\",\"tableName\":\"jiaoshi\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"批改\"],\"appFrontIcon\":\"cuIcon-circle\",\"buttons\":[\"查看\",\"删除\",\"修改\"],\"classname\":\"tijiaozuoye\",\"menu\":\"提交作业\",\"menuJump\":\"列表\",\"tableName\":\"tijiaozuoye\"},{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"删除\",\"查看\"],\"classname\":\"zuoyepigai\",\"menu\":\"作业批改\",\"menuJump\":\"列表\",\"tableName\":\"zuoyepigai\"}],\"fontClass\":\"icon-common20\",\"menu\":\"作业信息管理\",\"unicode\":\"&#xee02;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"修改\",\"删除\"],\"classname\":\"forum\",\"menu\":\"我的发布\",\"menuJump\":\"14\",\"tableName\":\"forum\"}],\"fontClass\":\"icon-common21\",\"menu\":\"论坛管理\",\"unicode\":\"&#xee03;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"xueshengkongbaiyemian\",\"menu\":\"学生空白页面\",\"menuJump\":\"列表\",\"tableName\":\"xueshengkongbaiyemian\"}],\"fontClass\":\"icon-common4\",\"menu\":\"空白页面1管理\",\"unicode\":\"&#xedab;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"查看\"],\"classname\":\"storeup\",\"menu\":\"我的收藏\",\"menuJump\":\"1\",\"tableName\":\"storeup\"}],\"fontClass\":\"icon-common44\",\"menu\":\"我的收藏管理\",\"unicode\":\"&#xef28;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\"],\"appFrontIcon\":\"cuIcon-form\",\"buttons\":[\"查看\",\"删除\"],\"classname\":\"xueshengyuyuejiaoshi\",\"menu\":\"学生预约教师\",\"menuJump\":\"列表\",\"tableName\":\"xueshengyuyuejiaoshi\"}],\"fontClass\":\"icon-common5\",\"menu\":\"预约信息管理\",\"unicode\":\"&#xedae;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"提交\"],\"classname\":\"zuoyexinxi\",\"menu\":\"作业信息\",\"menuJump\":\"列表\",\"tableName\":\"zuoyexinxi\"}],\"menu\":\"作业信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"公告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"新闻资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"forum\",\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"menuJump\":\"列表\",\"tableName\":\"forum\",\"unicode\":\"&#xedab;\"}],\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"unicode\":\"&#xedab;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"messages\",\"menu\":\"留言板\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"menu\":\"留言板管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"查看\"],\"classname\":\"jiaoxueziyuan\",\"menu\":\"教学资源\",\"menuJump\":\"列表\",\"tableName\":\"jiaoxueziyuan\"}],\"menu\":\"教学资源管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-full\",\"buttons\":[\"查看\",\"家长预约\",\"学生预约\"],\"classname\":\"jiaoshi\",\"menu\":\"教师\",\"menuJump\":\"列表\",\"tableName\":\"jiaoshi\"}],\"menu\":\"教师管理\"}],\"hasBackLogin\":\"否\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"roleName\":\"学生\",\"tableName\":\"xuesheng\"},{\"backMenu\":[{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"查看\",\"删除\"],\"classname\":\"forum\",\"menu\":\"我的发布\",\"menuJump\":\"14\",\"tableName\":\"forum\"}],\"fontClass\":\"icon-common21\",\"menu\":\"论坛管理\",\"unicode\":\"&#xee03;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"查看\"],\"classname\":\"storeup\",\"menu\":\"我的收藏\",\"menuJump\":\"1\",\"tableName\":\"storeup\"}],\"fontClass\":\"icon-common44\",\"menu\":\"我的收藏管理\",\"unicode\":\"&#xef28;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"appFrontIcon\":\"cuIcon-news\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"jiazhangkongbaiyemian\",\"menu\":\"家长空白页面\",\"menuJump\":\"列表\",\"tableName\":\"jiazhangkongbaiyemian\"}],\"fontClass\":\"icon-common8\",\"menu\":\"家长空白页面管理\",\"unicode\":\"&#xedb6;\"},{\"child\":[{\"allButtons\":[\"新增\",\"查看\",\"修改\",\"删除\",\"审核\"],\"appFrontIcon\":\"cuIcon-taxi\",\"buttons\":[\"查看\",\"删除\"],\"classname\":\"jiazhangyuyuejiaoshi\",\"menu\":\"家长预约教师\",\"menuJump\":\"列表\",\"tableName\":\"jiazhangyuyuejiaoshi\"}],\"fontClass\":\"icon-common5\",\"menu\":\"预约信息管理\",\"unicode\":\"&#xedae;\"}],\"frontMenu\":[{\"child\":[{\"appFrontIcon\":\"cuIcon-copy\",\"buttons\":[\"查看\",\"提交\"],\"classname\":\"zuoyexinxi\",\"menu\":\"作业信息\",\"menuJump\":\"列表\",\"tableName\":\"zuoyexinxi\"}],\"menu\":\"作业信息管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-skin\",\"buttons\":[\"查看\"],\"classname\":\"news\",\"menu\":\"公告信息\",\"menuJump\":\"列表\",\"tableName\":\"news\"}],\"menu\":\"新闻资讯管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-phone\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"forum\",\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"menuJump\":\"列表\",\"tableName\":\"forum\",\"unicode\":\"&#xedab;\"}],\"fontClass\":\"icon-common4\",\"menu\":\"论坛交流\",\"unicode\":\"&#xedab;\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-similar\",\"buttons\":[\"新增\",\"查看\",\"修改\",\"删除\"],\"classname\":\"messages\",\"menu\":\"留言板\",\"menuJump\":\"列表\",\"tableName\":\"messages\"}],\"menu\":\"留言板管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-shop\",\"buttons\":[\"查看\"],\"classname\":\"jiaoxueziyuan\",\"menu\":\"教学资源\",\"menuJump\":\"列表\",\"tableName\":\"jiaoxueziyuan\"}],\"menu\":\"教学资源管理\"},{\"child\":[{\"appFrontIcon\":\"cuIcon-full\",\"buttons\":[\"查看\",\"家长预约\",\"学生预约\"],\"classname\":\"jiaoshi\",\"menu\":\"教师\",\"menuJump\":\"列表\",\"tableName\":\"jiaoshi\"}],\"menu\":\"教师管理\"}],\"hasBackLogin\":\"否\",\"hasBackRegister\":\"否\",\"hasFrontLogin\":\"是\",\"hasFrontRegister\":\"是\",\"roleName\":\"家长\",\"tableName\":\"jiazhang\"}]');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '留言人id',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `avatarurl` longtext COMMENT '头像',
  `content` longtext NOT NULL COMMENT '留言内容',
  `cpicture` longtext COMMENT '留言图片',
  `reply` longtext COMMENT '回复内容',
  `rpicture` longtext COMMENT '回复图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='留言板';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'2024-10-24 08:43:25',1,'用户名1','file/messagesAvatarurl1.jpg','留言内容1','file/messagesCpicture1.jpg','回复内容1','file/messagesRpicture1.jpg'),(2,'2024-10-24 08:43:25',2,'用户名2','file/messagesAvatarurl2.jpg','留言内容2','file/messagesCpicture2.jpg','回复内容2','file/messagesRpicture2.jpg'),(3,'2024-10-24 08:43:25',3,'用户名3','file/messagesAvatarurl3.jpg','留言内容3','file/messagesCpicture3.jpg','回复内容3','file/messagesRpicture3.jpg'),(4,'2024-10-24 08:43:25',4,'用户名4','file/messagesAvatarurl4.jpg','留言内容4','file/messagesCpicture4.jpg','回复内容4','file/messagesRpicture4.jpg'),(5,'2024-10-24 08:43:25',5,'用户名5','file/messagesAvatarurl5.jpg','留言内容5','file/messagesCpicture5.jpg','回复内容5','file/messagesRpicture5.jpg'),(6,'2024-10-24 08:43:25',6,'用户名6','file/messagesAvatarurl6.jpg','留言内容6','file/messagesCpicture6.jpg','回复内容6','file/messagesRpicture6.jpg'),(7,'2024-10-24 08:43:25',7,'用户名7','file/messagesAvatarurl7.jpg','留言内容7','file/messagesCpicture7.jpg','回复内容7','file/messagesRpicture7.jpg'),(8,'2024-10-24 08:43:25',8,'用户名8','file/messagesAvatarurl8.jpg','留言内容8','file/messagesCpicture8.jpg','回复内容8','file/messagesRpicture8.jpg'),(9,'2024-10-24 08:43:25',9,'用户名9','file/messagesAvatarurl9.jpg','留言内容9','file/messagesCpicture9.jpg','回复内容9','file/messagesRpicture9.jpg'),(10,'2024-10-24 08:43:25',10,'用户名10','file/messagesAvatarurl10.jpg','留言内容10','file/messagesCpicture10.jpg','回复内容10','file/messagesRpicture10.jpg');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `introduction` longtext COMMENT '简介',
  `picture` longtext NOT NULL COMMENT '图片',
  `content` longtext NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='公告信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2024-10-24 08:43:25','浪漫手机--周杰伦','《浪漫手机》是周杰伦演唱的一首歌曲，由周杰伦作曲，方文山填词，洪敬尧编曲，收录在周杰伦2005年11月1日发行的专辑《11月的萧邦》中。\n带着喝下午茶的轻松心情，微笑浏览手机里的浪漫，在虚拟土壤慢慢用文字灌溉情感，给你的真心只有杰伦限定。 整张专辑终于开始有了暖意 为接下来更阴冷的《逆鳞》作一个过渡 无论有多少人鄙视这首大众情歌 都不能影响它成为另外一些人的心中最爱。这是一首抒情R&B 前奏轻松 肆意又慵懒 一路有言笑 沿途阳光灿烂 清纯的厉害 讲的是一种爱的羞怯。这种歌曲历来受广大歌迷喜欢 就像《星晴》《简单爱》《园游会》浪漫的爱情是这种歌曲永恒的主题旋律。','file/newsPicture1.jpg','轻轻放  我就是卸不下对你的喜欢  原来爱会慢慢增加重量  想关上这城市所有的灯光  黑暗中专心闻你的发香  这夜晚 让暗恋很有画面感  回想\n与你约会过的地方都舍不得删 在脑海里储存欣赏  你微笑浏览手机里的浪漫\n原来真心送出爱是这么简单 温习荧幕上  你可爱的模样  关于缘分的解释 我又多传了一行  你微笑浏览手机里的浪漫  原来爱情可以来得这么突然\n短信的桥梁 将暧昧期拉长 我们的感情蔓延滋长用文字培养 在虚拟土壤\n电视墙 吵杂的情歌还在拼命播放 我安静在闹区等来电铃响\n有一些话打好了却不敢传  怕收到信息的你在为难 街道上 人潮衬托我的孤单\n想象  谁幸运的陪在你身旁  却误会一场  你也在等你手机响  你微笑浏览 手机里的浪漫  原来真心送出爱是这么简单  温习荧幕上 你可爱的模样  关于缘份的解释我又多传了一行  你微笑浏览 手机里的浪漫  原来爱情可以来的这么突然  短信的桥梁 将暧昧期拉长  我们的感情蔓延滋长用文字培养 在虚拟土壤\n歌曲鉴赏-可以带着喝下午茶轻松心情来聆听的甜蜜小品，也是内地电信业者的广告歌曲。《爱在暧昧不明时最美丽…》，方文山将男女之间暧昧的浪漫情愫，转换成温暖的文字情感，藉由浏览手机里的浪漫简讯，让人重新感受暗恋时那悸动的心跳，也让女孩们听了会会心一笑'),(2,'2024-10-24 08:43:25','听爸爸的话-周杰伦','《听爸爸的话》是周杰伦演唱的一首歌曲，由周杰伦作词、作曲，黄雨勋编曲，收录于周杰伦2014年12月26日发行的专辑《哎呦，不错哦》中。\n周杰伦再度令人惊艳之作，全新创作专辑包含独特的“周氏幽默”，是呼应自己的歌，男生小时候要听妈妈的话，女生长大了要「听爸爸的话」，在谈恋爱时，明明是两个人对于爱情的不对等而埋怨，女生有时候却搬出爸爸来当分手的藉口，可是男生却也以孝顺为理由，听妈妈的话，不能轻言放弃，誓言追回女生！','file/newsPicture2.jpg','你听爸爸的话  说我不是个好东西  送给你的花  不配放在你家花瓶里\n他要我越走越远越好  不要在一起那最好  但我听妈妈的话  要我不能轻易放弃  还喜欢的话让我再追回你求求你  难道你要我不孝顺吗\n这是借口你应该懂吧  你说 谁说 我说 我不爱了  胡说 瞎说 我习惯了\n那烟 和酒 也都戒了  证明有多在乎你了  光上 一句 我想你懂  我真的不太会说话  你看你 笑了吧  你说我 算了吧  一个人 的爱情 很简单  两个人的相爱  为什么反而变得很难  我受够我的爱你比较多  也受够你的爱沉默很久\n承诺太多 多少会错  你宁愿难过也不要有结果  我受够我的爱你比较多\n倒不如回到从前的自由  说好放手 谁先开口  没有对错  反正花开了终究会落\n你说 谁说 我说 我不爱了  胡说 瞎说 我习惯了  那烟 和酒 也都戒了\n证明有多在乎你了  一个人 的爱情 很简单  两个人的相爱 为什么反而变得很难  你听爸爸的话  说我不是个好东西  送给你的花  不配放在你家花瓶里\n他要我越走越远越好  不要在一起那最好  但我听妈妈的话  要我不能轻易放弃  还喜欢的话让我再追回你求求你  难道你要我不孝顺吗 这是借口你应该懂吧  我受够我的爱你比较多  也受够你的爱沉默很久  承诺太多 多少会错\n你宁愿难过也不要有结果  我受够我的爱你比较多  倒不如回到从前的自由\n说好放手 谁先开口  没有对错  反正花开了终究会落  难道你要我不孝顺吗\n这是借口你应该懂吧\n歌曲鉴赏-男生小时候要听妈妈的话，女生长大了要听爸爸的话，谈恋爱时女生常搬出爸爸来当分手的借口，可是男生却也以孝顺为理由，听妈妈的话！不能轻言放弃！周杰伦用男生的观点来写这首情歌，很厉害的是他还故意设计了两个副歌！同样的和弦却有不同的旋律，是一次很特别的歌曲编排！MV里周杰伦帅气带领摇滚BAND入镜，在布置成满天星斗的场景里对嘴，为这首抒情摇滚的情歌增添浪漫气息'),(3,'2024-10-24 08:43:25','卓玛-乌兰托娅','歌手介绍：来自草原的天籁之音美丽的呼伦贝尔大草原，辽阔的疆域、矫健的马匹、肥大的羊群、清澈的泉水、朴实的牧民，还有丰富多彩的民族歌谣。\n乌兰托娅，正是从这个绿茫茫的草原欢歌而来，带着天籁般的声音，伴着草原的香气。','file/newsPicture3.jpg','草原的风 草原的雨  草原的羊群  草原的花 草原的水 草原的姑娘\n啊…卓玛 啊…卓玛  草原上的姑娘卓玛拉  你有一个花的名字\n美丽姑娘卓玛拉  你有一个花的笑容  美丽姑娘卓玛拉\n你像一只自由的小鸟  歌唱在那草原上  你像春天飞舞的彩蝶\n闪烁在那花丛中  啊…卓玛 草原上的格桑花  你把歌声献给雪山\n养育你的雪山  你把美丽献给草原  养育你的草原 啊…卓玛 啊…卓玛\n草原上的姑娘卓玛拉  你有一个花的名字 美丽姑娘卓玛拉\n你有一个花的笑容  美丽姑娘卓玛拉  你像一杯甘甜的美酒\n醉了太阳 醉了月亮  你像一支悠扬的牧歌  美了雪山 美了草原\n啊…卓玛 草原上的格桑花  你把歌声献给雪山  养育你的雪山\n你把美丽献给草原  养育你的草原  你把歌声献给雪山  养育你的雪山\n你把美丽献给草原  养育你的草原  啊…卓玛 啊…卓玛\n草原上的姑娘卓玛拉  啊…卓玛  草原上的姑娘卓玛拉\n她一直在用心情去歌唱，用其纯绿色的天籁之音去感化疲惫。\n早在06年5月，乌兰托娅就推出专辑《阿尔斯楞的眼睛》，专辑中，乌兰托娅所展现的特有的纯天然的草原之声，所演绎出来的草原人独有的汩汩而流的情感，更是打动了当时许多向往草原的人们，也感动了许多热爱草原歌谣的人。\n从此，乌兰托娅就被冠以了“草原天籁之音”的美誉。'),(4,'2024-10-24 08:43:25','离别开出花-柯柯柯啊','词Lyricist：李浩瑞 曲Composer：李浩瑞  吉他/贝斯Guitar&Bass：李国宏  混音棚：匠心音乐  OP/SP：好乐无荒  制作人Producer：廖凌鹏\n人声编辑Vocal Editing：任天宇  混音师Mixing Engineer：任天宇','file/newsPicture4.jpg','坐上那朵离家的云霞  飘去无人知晓的天涯  背着妈妈说的那句话\n孩子人生其实不复杂  喔眼泪轻轻地擦  别管那多嘴乌鸦\n咽下那些风沙  你才能慢慢长大  要错过几个她\n用你最好的年华  这是青春的代价  当离别开出花\n伸出新长的枝桠  像冬去春又来  等待心雪融化 你每次离开家\n带着远方的牵挂  那城市的繁华  盖住了月牙  当离别开出花\n它生长在悬崖  在最高的山顶  才听得见回答  没什么好害怕\n孩子放心去飞吧  在你的身后  有个等你的家  坐上那朵离家的云霞\n飘去无人知晓的天涯  背着妈妈说的那句话  孩子人生其实不复杂\n喔眼泪轻轻地擦  别忘那童年梦话  散在远方的花  也随风慢慢长大\n要错过几个她  用你最真的年华  这是青春的回答 当离别开出花\n伸出新长的枝桠  像冬去春又来  等待心雪融化  你每次离开家\n带着远方的牵挂  那城市的繁华  盖住了月牙  当离别开出花\n它生长在悬崖  在最高的山顶  才听得见回答  没什么好害怕\n孩子放心去飞吧  在你的身后  有个等你的家  当离别开出花\n它生长在悬崖  在最高的山顶  才听得见回答  没什么好害怕\n孩子放心去飞吧  在你的身后  有个等你的家\n《离别开出花》之所以爆火，主要是因为其富有哲理的歌词和旋律成功地传递了关于离别、成长和梦想追求的思考和领悟，加上精心的推广和宣传策略。\n这首歌曲由‌李浩瑞作词作曲，由就是‌南方凯演唱，发行于2023年7月10日，并在2024年5月因‌杨坤和‌吉克隽逸在‌浙江卫视的倾情演绎而彻底出圈。其歌词深刻描绘了青春的启程、家庭的嘱托、青春的代价以及离别与重逢的主题，传递了积极向上的生活态度和对梦想的追求。'),(5,'2024-10-24 08:43:25','在加纳共和国离婚-菲道尔、Dior大颖','演唱：菲道尔、Dior大颖  词：菲道尔  曲：菲道尔\n制作人：林言奕 Andy Lin @上奕音乐  编曲：林言奕 Andy Lin\n钢琴：林言奕 Andy Lin、姚逸轩 Captain Yao  配唱制作人：林言奕 Andy Lin  协助配唱：姚逸轩 Captain Yao  Vocal Editing：林言奕 Andy Lin','file/newsPicture5.jpg','你还爱我吗你还爱我吗  你懂我会不争气想回到你身旁 没想到只能走到这\n看你湿了眼我到底该说什么  总以为还有一辈子的时间 慢慢跟你说再见\n你不用对我说什么  我哭不是因为我觉得舍不得 只是觉得努力了那么久\n最后却还是败给不适合  也许这一切都是最好的安排 但我无法看着你离开\n你还爱我吗  你还爱我吗  我没办法看着你  双眼说那句话 \n你懂我的吧你懂我的啊  你懂我会不争气想回到你身旁 你不用对我说什么\n我哭就是因为我觉得舍不得  我们一起努力了那么久 最后却还是败给不适合  也许这一切都是最好的安排  但我无法看着你离开 你还爱我吗你还爱我吗\n我没办法看着你双眼说那句话  你懂我的吧你懂我的啊 你懂我会不争气想回到你身旁  你还爱我吗(我还爱你啊)  你还爱我吗(我还爱你啊)\n我没办法看着你双眼说那句话  (原谅我没办法看着你双眼说那句话)\n你懂我的吧 你懂我的啊  你懂我会不争气想回到你身旁\n原来一辈子 它真的很遥远  抵达一辈子的时候 你不在身边\n‌《在加纳共和国离婚》这首歌曲表达了对爱情变调的讽刺和对婚姻走到尽头的反思，同时也传达了一种复杂的情感，即即使名为“离婚”，这最后一次的“婚礼”也属于他们共同的记忆。‌\n这首歌的歌词和旋律唱出了许多段爱情的心酸与无奈，反映了现实与理想爱情碰撞的结局。它描述了情侣在分开前的最后时刻，彼此对过去共同走过的路的回忆，以及对即将到来的分别的深深眷恋和不舍。这种歌曲不仅是对爱情变调的讽刺，也是对婚姻走到尽头的反思。它传达了一种复杂的情感：即使名为“离婚”，这最后一次的“婚礼”也属于他们共同的记忆。'),(6,'2024-10-24 08:43:25','异客-杨坤','曲Composer：刘昊霖/拜德盖Viigho  词Lyricist：赵大白/拜德盖Viigho/刘昊霖  制作人Producer：刘昊霖  编曲Arranger：张宗炜/谭侃侃\n吉他Guitar：谭侃侃  班卓琴Banjo：赛夫  口琴Harmonica：晓月老板\n人声编辑Vocal Editing：刘昊霖/谭笑聪  和声Backing Vocal：杨坤\n和声编写Backing Vocal Design：杨坤 混音师Mixing Engineer：袁中仁','file/newsPicture6.jpg','那远山呼唤我 曾千百次路过  半山腰摘几朵 便飘向歌颂者\n那份简单 离开后 就再也没见过 单程票的火车 一路上唱着\n他们说  月亮照着 背包上的归途 照亮回家的路\n你想要的 从来不是漂泊 远乡的异客 用你久违的 乡音形容 这一路的苦衷\n城市霓虹 怎懂你的颠簸 脚步有多重  那远山目送我 曾千百次梦过\n那年少的萤火 是否还在等着  那个车站 离别后 就一直梦见的 \n单程票的奔波 一路上唱着  他们说 月亮照着 背包上的归途 \n照亮回家的路  我懂你是不得不去漂泊 远乡的异客 用你久违的 \n乡音形容 这一路的苦衷  城市霓虹 不懂你的颠簸 脚步有多重\n月亮照着 你脚下的泥土 丈量回家的路 生活总是这样缝缝补补 \n却从未停驻  笑容里的皱纹在告诉我 走了太远太久 回家的路 \n终于不再颠簸 脚步不再重 再见 故乡的云 从不曾散去 \n天涯的游子游走了多少光阴 归来吧 月亮哟 听你 离开数年 \n最熟悉的乡音 再哼着老去的歌 伴着回家的路漫漫流淌的河也会向东而扑\n归来吧 漂泊的青春 别让目光躲在岁月里等\n《异客》这首歌曲，犹如一封寄往远方的信，承载着无数异乡人的心灵独白。它由赵大白、拜德盖Viigho、刘昊霖联袂创作，刘昊霖、拜德盖Viigho共同谱曲，杨坤以其独特的嗓音深情演绎，于2024年1月6日正式发行。\n歌曲开篇，“那远山呼唤我，曾千百次路过”，如同一幅淡墨山水画，勾勒出游子心中那份对故土的深深眷恋。半山腰采撷的野花，飘向远方的歌颂者，象征着游子离乡背井时，那份纯真与梦想的寄托。然而，这份“简单”却在离开后，变得遥不可及。单程票的火车，载着游子在城市的霓虹中颠簸，追寻着那份属于自己的归宿。'),(7,'2024-10-24 08:43:25','若月亮没来-王宇宙Leto，乔浚丞','《若月亮没来(若是月亮还没来)》是王宇宙Leto，乔浚丞演唱的歌曲，于2024年1月22日上线发行。','file/newsPicture7.jpg','风吹过山 船靠了岸  风光呀 一点点看  我走向北 你去往南\n故事呀 一篇篇翻  好烦 又加班到很晚  你搭上空荡的 地铁已是末班\n好烦 很爱却要分开  恋爱谈不明白  好烦 接近理想好难 却又还很不甘\n如何拥抱平淡  如果 最难得到圆满  不如选择坦然 若是月亮还没来\n路灯也可照窗台  照着白色的山茶花微微开  若是晨风还没来\n晚风也可吹入怀  吹着那一地树影温柔摇摆  你总以为你不够好\n不够苗条和美貌  可是完美谁能达到  做你自己就很好\n烦恼烦恼拥有太少  没有房车没有钞票  可爱你的人永远会 \n把你当做心尖珍宝  我也懂大多数的时候  你只想逃离这世界\n我也懂太多的情绪在  一个人失眠的深夜 你可以是悲伤或者埋怨\n但请不要放弃明天  这一直灰暗的世界  我想看见你的笑脸\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n若是月亮还没来  路灯也可照窗台  照着白色的山茶花微微开\n若是晨风还没来  晚风也可吹入怀  吹着那一地树影温柔摇摆\n李哲今年1月份创作了《若月亮没来》这首歌。随着歌曲的热度不断上升，约他写歌的人越来越多，他的工作变得更加繁忙。\n近年来，李哲凭借对音乐的热爱和不懈的努力，创作了几百首音乐作品，其中不乏广为人知的佳作，例如王栎鑫的《一个人》、何赛飞与叶炫清在央视演唱的《木兰诗》等歌曲，都是由李哲作曲。\n如今，李哲的音乐才华和努力得到了业界的广泛认可，他的音乐作品多次登上各大音乐平台的热歌榜，成为备受瞩目的新生代音乐人。“现在，走在街头、坐在车里，经常会听到自己的作品。看到自己的音乐作品受到如此广泛的喜爱和认可，我感到非常有成就感。”李哲笑着说，他会努力创作更多优秀音乐作品，为家乡争光。'),(8,'2024-10-24 08:43:25','感官先生-刘凤瑶','《感官先生》是由刘凤瑶作词、作曲，刘凤瑶演唱的歌曲，发行于2019年7月5日。 [1]收录于同名专辑《感官先生》中。','file/newsPicture8.jpg','那就忘了吧忘了吧  忘就忘了吧忘了吧  肉麻容易尴尬\n那就放下吧放下吧  放不放得下放得下  继续走马观花\n总有上不上下不下  气氛真不真假不假  图个附庸风雅\n那就再见吧再见吧  再也不见吧不见啦  承诺人间蒸发\n安慰都是废话  算了吧  雨再大也不过打湿头发  无碍无伤大雅\n心好大  没想法  没牵挂  如果天都黑了 夜都深了  心的门没锁\n每盏灯火 都有睡不着的  故事可以说  反正 一个人能凑合\n两个人也不嫌多  Wu I dont know  怎么天都黑了\n睡不着的 人呐没梦做  爱得越多 越没把握  越是有话不直说\n像我这种性格 干下两口  管他够不够  Wu I dont know\n总有上不上下不下  气氛真不真假不假  图个附庸风雅\n那就再见吧再见吧  再也不见吧不见啦  承诺人间蒸发\n安慰都是废话  算了吧  雨再大也不过打湿头发\n无碍无伤大雅  心好大  没想法  没牵挂 如果天都黑了 夜都深了\n心的门没锁  每盏灯火 都有睡不着的  故事可以说 反正 一个人能凑合\n两个人也不嫌多  Wu I dont know 怎么天都黑了\n睡不着的 人呐没梦做 爱得越多 越没把握  上过当的骗子说\n像我这种性格 干下两口  管他够不够  Wu I dont know\nI dont know  I dont know  Wu I dont know\n杨坤与杨宗纬携手演唱的《感官先生》不仅是一次音乐的盛宴，更是一次心灵的触动。他们独特的嗓音交织在一起，仿佛诉说着每个人内心深处的故事。《感官先生》这首歌，犹如一面镜子，映照出我们生活中的种种情感。它表达的是随遇而安的人生态度，泰然处之的处世之道。在人生的旅途中，我们总会遇到各种各样的问题和挑战，但正如歌词所言，“心好大，没想法，没牵挂”，有时候，我们不如放下心中的包袱，跟着感觉随性而为。'),(9,'2024-10-24 08:43:25','字字句句-卢卢快闭嘴','《字字句句》是卢卢快闭嘴演唱的歌曲，发行于2022年12月29日。收录于同名专辑《字字句句》中。','file/newsPicture9.jpg','他关于你 绝口不提  他是否短暂爱过你  来去自如 也分不清他什么目的\n若即若离 是空欢喜  听他和谁逢场作戏  可是你的委屈却无从说起\n他字字未提喜欢你  你句句都是我愿意 他一句寂寞时候的回应\n你却激动不已 他次次回避着话题 你傻傻热情的贴近 穿过多少城市为了他\n只因为他淋湿了自己 他关于你 绝口不提  他是否短暂爱过你\n来去自如 也分不清他什么目的 若即若离 是空欢喜 听他和谁逢场作戏\n可是你的委屈却无从说起 他字字未提喜欢你 你句句都是我愿意\n他一句寂寞时候的回应  你却激动不已  他次次回避着话题\n你傻傻热情的贴近  穿过多少城市为了他  只因为他淋湿了自己\n他字字未提喜欢你  你句句都是我愿意 他一句寂寞时候的回应\n你却激动不已 他次次回避着话题 你傻傻热情的贴近 穿过多少城市为了他\n只因为他淋湿了自己 你说你爱他 和他没关系\n《字字句句》是一首充满热情和情感的歌曲，每一个音符都充满了张碧晨对音乐的热爱和执着。歌曲的旋律充满了生命的活力，仿佛在寂静的夜晚，突然刮起的一阵微风，牵动着我们的心弦，让我们无法抗拒。《字字句句》的歌词，同样充满了热情与情感。每一个字、每一个句都像是从心底涌出的呼喊，描述着一份深深的情感。在歌词的字里行间，我们仿佛看到了一个个燃烧的灵魂，他们用热情的旋律，将生命中的喜怒哀乐一一诉说。'),(10,'2024-10-24 08:43:25','像风一样-薛之谦','《像风一样》是薛之谦作词、作曲并演唱的一首歌，收录于2017年11月28日发行的专辑《渡 The Crossing》中。 [1]电影《不能流泪的悲伤》插曲《像风一样》中，薛之谦用深情的嗓音演唱出爱情中的勇敢与力量。','file/newsPicture10.jpg','我等的模样好不具象  用皮肤感受你的流向  你竟然能做到带走阳光  我一味的跟随过了量  像风一样  你靠近云都下降 你卷起千层海浪  我躲也不躲往里闯  你不就像风一样  侵略时沙沙作响 再宣布恢复晴朗  就好像我们两个没爱过一样  曲折的夕阳负责格挡 让委屈的感官无法释放  最近我的伤口没生长  因为我躲在没风的地方  像风一样 你靠近云都下降 你卷起千层海浪我躲也不躲往里闯  你不就像风一样  侵略时沙沙作响  再宣布恢复晴朗 就好像我们两个没爱过一样  你像风一样  触摸时温柔流淌  席卷我所有抵抗不急着要我投降  你不就像风一样 掠夺时沙沙作响  可惜我自投罗网  你也就没什么可骄傲的地方  和风一样  你离开不声不响  我喜欢这种收场  看上去谁也不曾亏欠过对方\n‌《‌像风一样》这首歌曲表达了复杂而深刻的情感，包括温柔甜蜜、伤感迷茫、自由无拘束以及对爱情的独特理解。‌ ‌薛之谦通过这首歌曲，将爱情比作风，描绘了爱情的矛盾和变化，同时也反映了人们在追求爱情过程中所经历的挫折和成长。‌\n‌歌词和旋律的具体情感‌：\n‌歌词‌：薛之谦亲笔填写的歌词美丽而富有诗意，通过丰富的意象和生动的比喻，描绘了一段纯净无暇的爱情故事。歌词中的“像风一样”既是一种形象的描绘，也是一种心灵的追求，表现出爱情的自由与无拘束。');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) DEFAULT NULL COMMENT 'refid',
  `tablename` varchar(200) DEFAULT NULL COMMENT '表名',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `picture` longtext NOT NULL COMMENT '图片',
  `type` varchar(200) DEFAULT '1' COMMENT '类型(1:收藏,21:赞,22:踩,31:竞拍参与,41:关注)',
  `inteltype` varchar(200) DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tijiaozuoye`
--

DROP TABLE IF EXISTS `tijiaozuoye`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tijiaozuoye` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zuoyemingcheng` varchar(200) NOT NULL COMMENT '作业名称',
  `jiaoshigonghao` varchar(200) DEFAULT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) DEFAULT NULL COMMENT '教师姓名',
  `zuoyedaan` longtext COMMENT '作业答案',
  `xuehao` varchar(200) DEFAULT NULL COMMENT '学号',
  `xueshengxingming` varchar(200) DEFAULT NULL COMMENT '学生姓名',
  `tijiaoshijian` datetime DEFAULT NULL COMMENT '提交时间',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='提交作业';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tijiaozuoye`
--

LOCK TABLES `tijiaozuoye` WRITE;
/*!40000 ALTER TABLE `tijiaozuoye` DISABLE KEYS */;
INSERT INTO `tijiaozuoye` VALUES (1,'2024-10-24 08:43:25','作业名称1','教师工号1','教师姓名1','','学号1','学生姓名1','2024-10-24 16:43:25',1,1),(2,'2024-10-24 08:43:25','作业名称2','教师工号2','教师姓名2','','学号2','学生姓名2','2024-10-24 16:43:25',2,2),(3,'2024-10-24 08:43:25','作业名称3','教师工号3','教师姓名3','','学号3','学生姓名3','2024-10-24 16:43:25',3,3),(4,'2024-10-24 08:43:25','作业名称4','教师工号4','教师姓名4','','学号4','学生姓名4','2024-10-24 16:43:25',4,4),(5,'2024-10-24 08:43:25','作业名称5','教师工号5','教师姓名5','','学号5','学生姓名5','2024-10-24 16:43:25',5,5),(6,'2024-10-24 08:43:25','作业名称6','教师工号6','教师姓名6','','学号6','学生姓名6','2024-10-24 16:43:25',6,6),(7,'2024-10-24 08:43:25','作业名称7','教师工号7','教师姓名7','','学号7','学生姓名7','2024-10-24 16:43:25',7,7),(8,'2024-10-24 08:43:25','作业名称8','教师工号8','教师姓名8','','学号8','学生姓名8','2024-10-24 16:43:25',8,8),(9,'2024-10-24 08:43:25','作业名称9','教师工号9','教师姓名9','','学号9','学生姓名9','2024-10-24 16:43:25',9,9),(10,'2024-10-24 08:43:25','作业名称10','教师工号10','教师姓名10','','学号10','学生姓名10','2024-10-24 16:43:25',10,10);
/*!40000 ALTER TABLE `tijiaozuoye` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,61,'学号1','xuesheng','学生','3rzyfypu3v4l5capeq52du9c8i22hwvd','2024-10-24 08:47:21','2024-10-24 09:57:01'),(2,1,'admin','users','管理员','8jwlpz1qn1mqrs6dx386f2db8v448rmy','2024-10-24 08:48:42','2024-10-24 09:57:41');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `role` varchar(200) DEFAULT NULL COMMENT '角色',
  `touxiang` longtext COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2024-10-24 08:43:26','admin','admin','管理员',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xuesheng`
--

DROP TABLE IF EXISTS `xuesheng`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xuesheng` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xuehao` varchar(200) NOT NULL COMMENT '学号',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `xueshengxingming` varchar(200) NOT NULL COMMENT '学生姓名',
  `touxiang` longtext COMMENT '头像',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `shoujihaoma` varchar(200) DEFAULT NULL COMMENT '手机号码',
  `jiazhangzhanghao` varchar(200) DEFAULT NULL COMMENT '家长账号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xuehao` (`xuehao`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='学生';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xuesheng`
--

LOCK TABLES `xuesheng` WRITE;
/*!40000 ALTER TABLE `xuesheng` DISABLE KEYS */;
INSERT INTO `xuesheng` VALUES (61,'2024-10-24 08:43:25','学号1','123456','学生姓名1','file/xueshengTouxiang1.jpg','男','19819881111','家长账号1'),(62,'2024-10-24 08:43:25','学号2','123456','学生姓名2','file/xueshengTouxiang2.jpg','男','19819881112','家长账号2'),(63,'2024-10-24 08:43:25','学号3','123456','学生姓名3','file/xueshengTouxiang3.jpg','男','19819881113','家长账号3'),(64,'2024-10-24 08:43:25','学号4','123456','学生姓名4','file/xueshengTouxiang4.jpg','男','19819881114','家长账号4'),(65,'2024-10-24 08:43:25','学号5','123456','学生姓名5','file/xueshengTouxiang5.jpg','男','19819881115','家长账号5'),(66,'2024-10-24 08:43:25','学号6','123456','学生姓名6','file/xueshengTouxiang6.jpg','男','19819881116','家长账号6'),(67,'2024-10-24 08:43:25','学号7','123456','学生姓名7','file/xueshengTouxiang7.jpg','男','19819881117','家长账号7'),(68,'2024-10-24 08:43:25','学号8','123456','学生姓名8','file/xueshengTouxiang8.jpg','男','19819881118','家长账号8'),(69,'2024-10-24 08:43:25','学号9','123456','学生姓名9','file/xueshengTouxiang9.jpg','男','19819881119','家长账号9'),(70,'2024-10-24 08:43:25','学号10','123456','学生姓名10','file/xueshengTouxiang10.jpg','男','198198811110','家长账号10');
/*!40000 ALTER TABLE `xuesheng` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xueshengkongbaiyemian`
--

DROP TABLE IF EXISTS `xueshengkongbaiyemian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xueshengkongbaiyemian` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `neirong` longtext COMMENT '内容',
  `xuehao` varchar(200) DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='学生空白页面';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xueshengkongbaiyemian`
--

LOCK TABLES `xueshengkongbaiyemian` WRITE;
/*!40000 ALTER TABLE `xueshengkongbaiyemian` DISABLE KEYS */;
INSERT INTO `xueshengkongbaiyemian` VALUES (1,'2024-10-24 08:43:26','内容1','学号1'),(2,'2024-10-24 08:43:26','内容2','学号2'),(3,'2024-10-24 08:43:26','内容3','学号3'),(4,'2024-10-24 08:43:26','内容4','学号4'),(5,'2024-10-24 08:43:26','内容5','学号5'),(6,'2024-10-24 08:43:26','内容6','学号6'),(7,'2024-10-24 08:43:26','内容7','学号7'),(8,'2024-10-24 08:43:26','内容8','学号8'),(9,'2024-10-24 08:43:26','内容9','学号9'),(10,'2024-10-24 08:43:26','内容10','学号10');
/*!40000 ALTER TABLE `xueshengkongbaiyemian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xueshengyuyuejiaoshi`
--

DROP TABLE IF EXISTS `xueshengyuyuejiaoshi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xueshengyuyuejiaoshi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoshigonghao` varchar(200) DEFAULT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) DEFAULT NULL COMMENT '教师姓名',
  `keyueshijian` varchar(200) DEFAULT NULL COMMENT '可约时间',
  `xuehao` varchar(200) DEFAULT NULL COMMENT '学号',
  `xueshengxingming` varchar(200) DEFAULT NULL COMMENT '学生姓名',
  `jiazhangzhanghao` varchar(200) DEFAULT NULL COMMENT '家长账号',
  `yuyueshijian` datetime DEFAULT NULL COMMENT '预约时间',
  `shhf` longtext COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='学生预约教师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xueshengyuyuejiaoshi`
--

LOCK TABLES `xueshengyuyuejiaoshi` WRITE;
/*!40000 ALTER TABLE `xueshengyuyuejiaoshi` DISABLE KEYS */;
INSERT INTO `xueshengyuyuejiaoshi` VALUES (1,'2024-10-24 08:43:25','教师工号1','教师姓名1','可约时间1','学号1','学生姓名1','家长账号1','2024-10-24 16:43:25',''),(2,'2024-10-24 08:43:25','教师工号2','教师姓名2','可约时间2','学号2','学生姓名2','家长账号2','2024-10-24 16:43:25',''),(3,'2024-10-24 08:43:25','教师工号3','教师姓名3','可约时间3','学号3','学生姓名3','家长账号3','2024-10-24 16:43:25',''),(4,'2024-10-24 08:43:25','教师工号4','教师姓名4','可约时间4','学号4','学生姓名4','家长账号4','2024-10-24 16:43:25',''),(5,'2024-10-24 08:43:25','教师工号5','教师姓名5','可约时间5','学号5','学生姓名5','家长账号5','2024-10-24 16:43:25',''),(6,'2024-10-24 08:43:25','教师工号6','教师姓名6','可约时间6','学号6','学生姓名6','家长账号6','2024-10-24 16:43:25',''),(7,'2024-10-24 08:43:25','教师工号7','教师姓名7','可约时间7','学号7','学生姓名7','家长账号7','2024-10-24 16:43:25',''),(8,'2024-10-24 08:43:25','教师工号8','教师姓名8','可约时间8','学号8','学生姓名8','家长账号8','2024-10-24 16:43:25',''),(9,'2024-10-24 08:43:25','教师工号9','教师姓名9','可约时间9','学号9','学生姓名9','家长账号9','2024-10-24 16:43:25',''),(10,'2024-10-24 08:43:25','教师工号10','教师姓名10','可约时间10','学号10','学生姓名10','家长账号10','2024-10-24 16:43:25','');
/*!40000 ALTER TABLE `xueshengyuyuejiaoshi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ziyuanleixing`
--

DROP TABLE IF EXISTS `ziyuanleixing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ziyuanleixing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ziyuanleixing` varchar(200) NOT NULL COMMENT '资源类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ziyuanleixing` (`ziyuanleixing`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='资源类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ziyuanleixing`
--

LOCK TABLES `ziyuanleixing` WRITE;
/*!40000 ALTER TABLE `ziyuanleixing` DISABLE KEYS */;
INSERT INTO `ziyuanleixing` VALUES (1,'2024-10-24 08:43:25','资源类型1'),(2,'2024-10-24 08:43:25','资源类型2'),(3,'2024-10-24 08:43:25','资源类型3'),(4,'2024-10-24 08:43:25','资源类型4'),(5,'2024-10-24 08:43:25','资源类型5'),(6,'2024-10-24 08:43:25','资源类型6'),(7,'2024-10-24 08:43:25','资源类型7'),(8,'2024-10-24 08:43:25','资源类型8'),(9,'2024-10-24 08:43:25','资源类型9'),(10,'2024-10-24 08:43:25','资源类型10');
/*!40000 ALTER TABLE `ziyuanleixing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zuoyepigai`
--

DROP TABLE IF EXISTS `zuoyepigai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zuoyepigai` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zuoyemingcheng` varchar(200) DEFAULT NULL COMMENT '作业名称',
  `jiaoshigonghao` varchar(200) DEFAULT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) DEFAULT NULL COMMENT '教师姓名',
  `xuehao` varchar(200) DEFAULT NULL COMMENT '学号',
  `xueshengxingming` varchar(200) DEFAULT NULL COMMENT '学生姓名',
  `pigaineirong` longtext COMMENT '批改内容',
  `pingfen` int(11) DEFAULT NULL COMMENT '评分',
  `pigaishijian` datetime DEFAULT NULL COMMENT '批改时间',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='作业批改';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zuoyepigai`
--

LOCK TABLES `zuoyepigai` WRITE;
/*!40000 ALTER TABLE `zuoyepigai` DISABLE KEYS */;
INSERT INTO `zuoyepigai` VALUES (1,'2024-10-24 08:43:25','作业名称1','教师工号1','教师姓名1','学号1','学生姓名1','批改内容1',1,'2024-10-24 16:43:25',1,1),(2,'2024-10-24 08:43:25','作业名称2','教师工号2','教师姓名2','学号2','学生姓名2','批改内容2',2,'2024-10-24 16:43:25',2,2),(3,'2024-10-24 08:43:25','作业名称3','教师工号3','教师姓名3','学号3','学生姓名3','批改内容3',3,'2024-10-24 16:43:25',3,3),(4,'2024-10-24 08:43:25','作业名称4','教师工号4','教师姓名4','学号4','学生姓名4','批改内容4',4,'2024-10-24 16:43:25',4,4),(5,'2024-10-24 08:43:25','作业名称5','教师工号5','教师姓名5','学号5','学生姓名5','批改内容5',5,'2024-10-24 16:43:25',5,5),(6,'2024-10-24 08:43:25','作业名称6','教师工号6','教师姓名6','学号6','学生姓名6','批改内容6',6,'2024-10-24 16:43:25',6,6),(7,'2024-10-24 08:43:25','作业名称7','教师工号7','教师姓名7','学号7','学生姓名7','批改内容7',7,'2024-10-24 16:43:25',7,7),(8,'2024-10-24 08:43:25','作业名称8','教师工号8','教师姓名8','学号8','学生姓名8','批改内容8',8,'2024-10-24 16:43:25',8,8),(9,'2024-10-24 08:43:25','作业名称9','教师工号9','教师姓名9','学号9','学生姓名9','批改内容9',9,'2024-10-24 16:43:25',9,9),(10,'2024-10-24 08:43:25','作业名称10','教师工号10','教师姓名10','学号10','学生姓名10','批改内容10',10,'2024-10-24 16:43:25',10,10);
/*!40000 ALTER TABLE `zuoyepigai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zuoyexinxi`
--

DROP TABLE IF EXISTS `zuoyexinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zuoyexinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zuoyemingcheng` varchar(200) NOT NULL COMMENT '作业名称',
  `fengmian` longtext COMMENT '封面',
  `zuoyeneirong` longtext COMMENT '作业内容',
  `fabushijian` datetime DEFAULT NULL COMMENT '发布时间',
  `jiaoshigonghao` varchar(200) DEFAULT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) DEFAULT NULL COMMENT '教师姓名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `reversetime` datetime DEFAULT NULL COMMENT '最近点击时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='作业信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zuoyexinxi`
--

LOCK TABLES `zuoyexinxi` WRITE;
/*!40000 ALTER TABLE `zuoyexinxi` DISABLE KEYS */;
INSERT INTO `zuoyexinxi` VALUES (1,'2024-10-24 08:43:25','作业名称1','file/zuoyexinxiFengmian1.jpg,file/zuoyexinxiFengmian2.jpg,file/zuoyexinxiFengmian3.jpg','','2024-10-24 16:43:25','教师工号1','教师姓名1',1,'2024-10-25 00:00:00'),(2,'2024-10-24 08:43:25','作业名称2','file/zuoyexinxiFengmian2.jpg,file/zuoyexinxiFengmian3.jpg,file/zuoyexinxiFengmian4.jpg','','2024-10-24 16:43:25','教师工号2','教师姓名2',2,'2024-10-25 00:00:00'),(3,'2024-10-24 08:43:25','作业名称3','file/zuoyexinxiFengmian3.jpg,file/zuoyexinxiFengmian4.jpg,file/zuoyexinxiFengmian5.jpg','','2024-10-24 16:43:25','教师工号3','教师姓名3',3,'2024-10-25 00:00:00'),(4,'2024-10-24 08:43:25','作业名称4','file/zuoyexinxiFengmian4.jpg,file/zuoyexinxiFengmian5.jpg,file/zuoyexinxiFengmian6.jpg','','2024-10-24 16:43:25','教师工号4','教师姓名4',4,'2024-10-25 00:00:00'),(5,'2024-10-24 08:43:25','作业名称5','file/zuoyexinxiFengmian5.jpg,file/zuoyexinxiFengmian6.jpg,file/zuoyexinxiFengmian7.jpg','','2024-10-24 16:43:25','教师工号5','教师姓名5',5,'2024-10-25 00:00:00'),(6,'2024-10-24 08:43:25','作业名称6','file/zuoyexinxiFengmian6.jpg,file/zuoyexinxiFengmian7.jpg,file/zuoyexinxiFengmian8.jpg','','2024-10-24 16:43:25','教师工号6','教师姓名6',6,'2024-10-25 00:00:00'),(7,'2024-10-24 08:43:25','作业名称7','file/zuoyexinxiFengmian7.jpg,file/zuoyexinxiFengmian8.jpg,file/zuoyexinxiFengmian9.jpg','','2024-10-24 16:43:25','教师工号7','教师姓名7',7,'2024-10-25 00:00:00'),(8,'2024-10-24 08:43:25','作业名称8','file/zuoyexinxiFengmian8.jpg,file/zuoyexinxiFengmian9.jpg,file/zuoyexinxiFengmian10.jpg','','2024-10-24 16:43:25','教师工号8','教师姓名8',8,'2024-10-25 00:00:00'),(9,'2024-10-24 08:43:25','作业名称9','file/zuoyexinxiFengmian9.jpg,file/zuoyexinxiFengmian10.jpg,file/zuoyexinxiFengmian11.jpg','','2024-10-24 16:43:25','教师工号9','教师姓名9',9,'2024-10-25 00:00:00'),(10,'2024-10-24 08:43:25','作业名称10','file/zuoyexinxiFengmian10.jpg,file/zuoyexinxiFengmian11.jpg,file/zuoyexinxiFengmian12.jpg','','2024-10-24 16:43:25','教师工号10','教师姓名10',10,'2024-10-25 00:00:00');
/*!40000 ALTER TABLE `zuoyexinxi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-25 20:55:16
