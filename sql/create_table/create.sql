/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-02 20:45:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sm_user
-- ----------------------------
DROP TABLE IF EXISTS `sm_user`;
CREATE TABLE `sm_user` (
  `cuserid` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `dr` tinyint(4) DEFAULT NULL,
  `user_type` tinyint(4) DEFAULT NULL,
  `is_lock` tinyint(4) DEFAULT NULL,
  `is_online` tinyint(4) DEFAULT NULL,
  `head_img` longtext,
  `last_time` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-02 20:46:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog_praise
-- ----------------------------
DROP TABLE IF EXISTS `blog_praise`;
CREATE TABLE `blog_praise` (
  `pk_praise` int(11) NOT NULL AUTO_INCREMENT,
  `pk_blog` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_praise`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
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
  `last_time` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `ip_area` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cuserid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-19 21:44:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login_info
-- ----------------------------
DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info` (
  `cuserid` varchar(255) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : Stanley
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : db_stanley

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-19 21:10:54
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
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of resource_model
-- ----------------------------
INSERT INTO `resource_model` VALUES ('27', '10', '主页', null, 'fa fa-home', '0', '../user/userindex.html', 'admin', '2018/1/29 上午11:08:24', 'admin', '2018/2/1 下午6:27:39');
INSERT INTO `resource_model` VALUES ('28', '30', '历史', 'fa fa-plane', 'fa fa-map', '0', '../user/loginHistory.html', 'admin', '2018/1/29 上午11:09:29', 'admin', '2018/6/15 下午5:33:05');
INSERT INTO `resource_model` VALUES ('29', '20', '社区', null, 'fa fa-google-wallet', '0', '../user/bloginfo.html', 'admin', '2018/1/29 上午11:10:36', 'admin', '2018/6/15 上午11:38:40');
INSERT INTO `resource_model` VALUES ('30', '40', '教育', null, 'fa fa-steam', '0', 'https://www.baidu.com', 'admin', '2018/1/29 上午11:15:04', 'admin', '2018/1/29 下午6:11:36');
INSERT INTO `resource_model` VALUES ('31', '50', '图库', null, 'fa fa-university', '0', null, 'admin', '2018/1/29 上午11:15:39', 'admin', '2018/1/29 上午11:49:44');
INSERT INTO `resource_model` VALUES ('33', '60', '帮助', null, 'fa fa-hand-paper-o', '0', null, 'admin', '2018/1/29 上午11:59:34', 'admin', '2018/1/29 下午12:01:04');
INSERT INTO `resource_model` VALUES ('35', '6001', '问答', null, 'fa fa-phone', '33', 'https://www.baidu.com', 'admin', '2018/1/29 下午3:12:17', 'admin', '2018/6/11 上午9:30:25');
INSERT INTO `resource_model` VALUES ('36', '6002', '反馈', null, 'fa fa-bug', '33', null, 'admin', '2018/1/29 下午5:47:03', null, null);
INSERT INTO `resource_model` VALUES ('38', '6003', '联系我们', null, 'fa fa-envelope', '33', 'http://www.sina.com.cn/contactus.html', 'admin', '2018/1/29 下午6:00:47', 'admin', '2018/1/30 下午2:36:18');
INSERT INTO `resource_model` VALUES ('39', '5001', '宠物狗', null, 'fa fa-paw', '31', null, 'admin', '2018/1/29 下午6:03:20', null, null);
INSERT INTO `resource_model` VALUES ('40', '4001', '疾病预防', null, 'fa fa-medkit', '30', null, 'admin', '2018/1/29 下午6:05:01', null, null);
INSERT INTO `resource_model` VALUES ('41', '4002', '技能培养', null, 'fa fa-linux', '30', null, 'admin', '2018/1/29 下午6:10:02', null, null);
INSERT INTO `resource_model` VALUES ('53', '5002', '萌猫', null, 'fa fa-optin-monster', '31', null, 'admin', '2018/1/30 上午9:52:33', null, null);
INSERT INTO `resource_model` VALUES ('70', '70', '直播', null, 'fa fa-firefox', '0', null, 'admin', '2018/1/30 下午12:11:12', 'admin', '2018/3/16 下午2:36:46');
INSERT INTO `resource_model` VALUES ('71', '600101', '自助服务', null, 'fa fa-qq', '35', null, 'admin', '2018/1/30 下午12:12:56', null, null);
INSERT INTO `resource_model` VALUES ('72', '600102', '人工客服', null, 'fa fa-slideshare', '35', null, 'admin', '2018/1/30 下午12:13:56', null, null);
INSERT INTO `resource_model` VALUES ('78', '60010101', 'Email', null, 'fa fa-envelope', '71', 'https://www.baidu.com', 'admin', '2018/6/11 上午12:58:11', 'admin', '2018/6/11 上午9:40:26');
INSERT INTO `resource_model` VALUES ('80', '6001010101', '附件Email', null, '', '78', 'https://www.baidu.com', 'admin', '2018/6/11 上午1:48:21', 'admin', '2018/6/11 上午9:44:33');
INSERT INTO `resource_model` VALUES ('81', '60010201', '电脑人', null, 'fa fa-windows', '72', null, 'admin', '2018/6/11 上午2:07:59', 'admin', '2018/6/11 上午9:45:49');
INSERT INTO `resource_model` VALUES ('82', '600201', '举报', null, 'fa fa-gavel', '36', null, 'admin', '2018/6/11 上午2:08:23', 'admin', '2018/6/11 上午9:41:46');
INSERT INTO `resource_model` VALUES ('83', '500101', '神犬电影', null, 'fa fa-video-camera', '39', null, 'admin', '2018/6/11 上午2:32:42', 'admin', '2018/6/19 下午5:32:16');
INSERT INTO `resource_model` VALUES ('84', '60010202', '客服妹子', null, 'fa fa-heartbeat', '72', null, 'admin', '2018/6/11 上午10:18:04', null, null);
