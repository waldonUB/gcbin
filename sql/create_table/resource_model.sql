/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-02 20:47:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resource_model
-- ----------------------------
DROP TABLE IF EXISTS `resource_model`;
CREATE TABLE `resource_model` (
  `pk_resource` int(11) NOT NULL AUTO_INCREMENT,
  `funcode` varchar(255) DEFAULT NULL,
  `funname` varchar(255) DEFAULT NULL,
  `restype` varchar(255) DEFAULT NULL,
  `resicon` varchar(255) DEFAULT NULL,
  `pk_parent` int(255) DEFAULT NULL,
  `urls` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `creationtime` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modifiedtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_resource`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
