/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-02 20:46:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info` (
  `pk_blog` int(11) NOT NULL AUTO_INCREMENT,
  `cuserid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `head_img` longtext,
  `last_time` varchar(255) DEFAULT NULL,
  `blog_title` varchar(255) DEFAULT NULL,
  `blog_content` longtext,
  `blog_praise` tinyint(4) DEFAULT NULL,
  `blog_comment` varchar(2550) DEFAULT NULL,
  `blog_image` longtext,
  `blog_classify` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_blog`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
