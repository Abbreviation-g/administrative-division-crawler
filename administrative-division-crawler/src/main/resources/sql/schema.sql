/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.16-log : Database - province_city_county
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `province_city_county`;

/*Table structure for table `t_county` */

CREATE TABLE IF NOT EXISTS `t_county` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(5) NOT NULL COMMENT '所属地级行政区id',
  `county_name` varchar(12) NOT NULL COMMENT '县级行政区名',
  `division_code` varchar(6) NOT NULL COMMENT '行政区号',
  `phone_code` varchar(4) DEFAULT NULL COMMENT '电话区号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2156 DEFAULT CHARSET=utf8;

/*Table structure for table `t_prefecture` */

CREATE TABLE IF NOT EXISTS `t_prefecture` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `pid` int(3) NOT NULL COMMENT '所属省级行政区id',
  `prefecture_name` varchar(15) NOT NULL COMMENT '地级行政区名',
  `division_code` varchar(6) NOT NULL COMMENT '行政区号',
  `phone_code` varchar(4) DEFAULT NULL COMMENT '电话区号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=utf8;

/*Table structure for table `t_province` */

CREATE TABLE IF NOT EXISTS `t_province` (
  `id` int(3) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province_name` varchar(8) NOT NULL COMMENT '全名',
  `division_code` varchar(6) NOT NULL COMMENT '行政区号',
  `abbreviation` varchar(1) NOT NULL COMMENT '简称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `province_name` (`province_name`),
  UNIQUE KEY `division_code` (`division_code`),
  UNIQUE KEY `abbreviation` (`abbreviation`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
