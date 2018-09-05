/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-02 20:46:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `pk_comment` int(11) NOT NULL AUTO_INCREMENT,
  `pk_blog` int(11) DEFAULT NULL,
  `cm_content` varchar(2550) DEFAULT NULL,
  `cm_praise` varchar(2550) DEFAULT NULL,
  `cm_time` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `head_img` longtext,
  PRIMARY KEY (`pk_comment`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
