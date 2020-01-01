/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : ppsg

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-01-01 23:23:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ppsg_config_arms
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_config_arms`;
CREATE TABLE `ppsg_config_arms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arms_name` varchar(32) DEFAULT NULL,
  `force_rate` double(5,2) DEFAULT NULL COMMENT '武力科技加成',
  `intellect_rate` double(5,2) DEFAULT NULL COMMENT '智力科技加成',
  `troops_rate` double(5,2) DEFAULT NULL COMMENT '兵力科技加成',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_config_arms
-- ----------------------------
INSERT INTO `ppsg_config_arms` VALUES ('1', '枪兵', '0.60', '0.40', '0.80');
INSERT INTO `ppsg_config_arms` VALUES ('2', '弓兵', '0.60', '0.80', '0.40');
INSERT INTO `ppsg_config_arms` VALUES ('3', '骑兵', '0.80', '0.40', '0.60');

-- ----------------------------
-- Table structure for ppsg_config_country
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_config_country`;
CREATE TABLE `ppsg_config_country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(32) DEFAULT NULL COMMENT '国家',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_config_country
-- ----------------------------
INSERT INTO `ppsg_config_country` VALUES ('1', '魏');
INSERT INTO `ppsg_config_country` VALUES ('2', '蜀');
INSERT INTO `ppsg_config_country` VALUES ('3', '吴');
INSERT INTO `ppsg_config_country` VALUES ('4', '群');

-- ----------------------------
-- Table structure for ppsg_config_generals_type
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_config_generals_type`;
CREATE TABLE `ppsg_config_generals_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `generals_type_name` varchar(32) DEFAULT NULL,
  `force_growth` int(11) DEFAULT NULL COMMENT '武力成长',
  `intellect_growth` int(11) DEFAULT NULL COMMENT '智力成长',
  `troops_growth` int(11) DEFAULT NULL COMMENT '兵力成长',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_config_generals_type
-- ----------------------------
INSERT INTO `ppsg_config_generals_type` VALUES ('1', '勇将型', '3', '1', '2');
INSERT INTO `ppsg_config_generals_type` VALUES ('2', '将军型', '2', '1', '3');
INSERT INTO `ppsg_config_generals_type` VALUES ('3', '智将型', '2', '2', '2');
INSERT INTO `ppsg_config_generals_type` VALUES ('4', '策士型', '1', '3', '2');
INSERT INTO `ppsg_config_generals_type` VALUES ('5', '强袭勇将型', '4', '2', '3');
INSERT INTO `ppsg_config_generals_type` VALUES ('6', '统帅将军型', '3', '2', '4');
INSERT INTO `ppsg_config_generals_type` VALUES ('7', '鬼才智将型', '3', '3', '3');
INSERT INTO `ppsg_config_generals_type` VALUES ('8', '天命策士型', '2', '4', '3');

-- ----------------------------
-- Table structure for ppsg_config_star
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_config_star`;
CREATE TABLE `ppsg_config_star` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `star_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_config_star
-- ----------------------------
INSERT INTO `ppsg_config_star` VALUES ('1', '白');
INSERT INTO `ppsg_config_star` VALUES ('2', '黑');
INSERT INTO `ppsg_config_star` VALUES ('3', '银');
INSERT INTO `ppsg_config_star` VALUES ('4', '金');
INSERT INTO `ppsg_config_star` VALUES ('5', '钻');

-- ----------------------------
-- Table structure for ppsg_config_wardevice
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_config_wardevice`;
CREATE TABLE `ppsg_config_wardevice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '0:普通战器 1：特殊战器',
  `level` tinyint(1) DEFAULT NULL COMMENT '品质：白 黑 银 金 钻',
  `force_val` int(11) DEFAULT NULL,
  `intellect_val` int(11) DEFAULT NULL,
  `troops_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_config_wardevice
-- ----------------------------
INSERT INTO `ppsg_config_wardevice` VALUES ('1', '刀', '0', null, '133', '106', '160');
INSERT INTO `ppsg_config_wardevice` VALUES ('2', '剑', '0', null, '133', '133', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('3', '枪', '0', null, '160', '106', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('4', '弓', '0', null, '133', '133', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('5', '扇', '0', null, '133', '160', '106');
INSERT INTO `ppsg_config_wardevice` VALUES ('6', '特殊刀', '1', null, '106', '80', '132');
INSERT INTO `ppsg_config_wardevice` VALUES ('7', '特殊剑', '1', null, '133', '106', '160');
INSERT INTO `ppsg_config_wardevice` VALUES ('8', '特殊枪', '1', null, '133', '133', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('9', '特殊弓', '1', null, '160', '106', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('10', '特殊扇', '1', null, '133', '133', '133');

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pid` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', '/index2', 'index', null, '0');
INSERT INTO `s_permission` VALUES ('2', '/search', 'search', null, '0');
INSERT INTO `s_permission` VALUES ('3', '/loginVerify', 'loginVerify', null, '0');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'USER');
INSERT INTO `s_role` VALUES ('2', 'ADMIN');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `role_id` bigint(11) NOT NULL,
  `permission_id` bigint(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('1', '1');
INSERT INTO `s_role_permission` VALUES ('2', '1');
INSERT INTO `s_role_permission` VALUES ('2', '2');
INSERT INTO `s_role_permission` VALUES ('2', '3');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'user', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `s_user` VALUES ('2', 'wang', 'e10adc3949ba59abbe56e057f20f883e');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1');
INSERT INTO `s_user_role` VALUES ('2', '1');
INSERT INTO `s_user_role` VALUES ('2', '2');
