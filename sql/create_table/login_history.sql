/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-16 00:46:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login_history
-- ----------------------------
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history` (
  `cuserid` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `dr` tinyint(4) DEFAULT NULL,
  `user_type` tinyint(4) DEFAULT NULL,
  `is_lock` tinyint(4) DEFAULT NULL,
  `is_online` tinyint(4) DEFAULT NULL,
  `head_img` longtext,
  `last_time` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `ip_area` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cuserid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
