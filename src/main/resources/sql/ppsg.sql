/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : ppsg

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-01-02 21:47:42
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
INSERT INTO `ppsg_config_wardevice` VALUES ('1', '刀', '0', '4', '133', '106', '160');
INSERT INTO `ppsg_config_wardevice` VALUES ('2', '剑', '0', '4', '133', '133', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('3', '枪', '0', '4', '160', '106', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('4', '弓', '0', '4', '133', '133', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('5', '扇', '0', '4', '133', '160', '106');
INSERT INTO `ppsg_config_wardevice` VALUES ('6', '特殊刀', '1', '4', '106', '80', '132');
INSERT INTO `ppsg_config_wardevice` VALUES ('7', '特殊剑', '1', '4', '133', '106', '160');
INSERT INTO `ppsg_config_wardevice` VALUES ('8', '特殊枪', '1', '4', '133', '133', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('9', '特殊弓', '1', '4', '160', '106', '133');
INSERT INTO `ppsg_config_wardevice` VALUES ('10', '特殊扇', '1', '4', '133', '133', '133');

-- ----------------------------
-- Table structure for ppsg_config_wardevice_quenching
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_config_wardevice_quenching`;
CREATE TABLE `ppsg_config_wardevice_quenching` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_config_wardevice_quenching
-- ----------------------------

-- ----------------------------
-- Table structure for ppsg_config_wardevice_strengthen
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_config_wardevice_strengthen`;
CREATE TABLE `ppsg_config_wardevice_strengthen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lv` int(10) DEFAULT NULL COMMENT '强化等级',
  `device_id` bigint(20) DEFAULT NULL COMMENT 'ConfigWarDevice表ID',
  `card_soul` int(11) DEFAULT NULL COMMENT '卡魂数量',
  `bloodstone` int(11) DEFAULT NULL COMMENT '血玉数量',
  `success_rate` double(5,2) DEFAULT NULL COMMENT '成功率',
  `safe` tinyint(1) DEFAULT NULL COMMENT '是否可以安全强化',
  `bottom_ticket` int(11) DEFAULT NULL COMMENT '保底券数量',
  `force_val` int(11) DEFAULT NULL,
  `intellect_val` int(11) DEFAULT NULL,
  `troops_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_config_wardevice_strengthen
-- ----------------------------

-- ----------------------------
-- Table structure for ppsg_geo_coord
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_geo_coord`;
CREATE TABLE `ppsg_geo_coord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(384) DEFAULT NULL,
  `city` varchar(384) DEFAULT NULL,
  `latitude` varchar(96) DEFAULT NULL,
  `longitude` varchar(96) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_geo_coord
-- ----------------------------
INSERT INTO `ppsg_geo_coord` VALUES ('1', '魏', '晋阳', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('2', '魏', '上党', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('3', '魏', '邺城', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('4', '魏', '平原', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('5', '魏', '安平', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('6', '魏', '渤海', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('7', '魏', '北海', '21.6211', '109.314');
INSERT INTO `ppsg_geo_coord` VALUES ('8', '魏', '琅琊', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('9', '魏', '彭城', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('10', '魏', '陈郡', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('11', '魏', '许昌', '34.0466', '113.6975');
INSERT INTO `ppsg_geo_coord` VALUES ('12', '魏', '祭坛北', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('13', '蜀', '梓潼', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('14', '蜀', '汉中', '33.0139', '106.886');
INSERT INTO `ppsg_geo_coord` VALUES ('15', '蜀', '成都', '30.7617', '103.9526');
INSERT INTO `ppsg_geo_coord` VALUES ('16', '蜀', '建宁', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('17', '蜀', '巴郡', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('18', '蜀', '江阳', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('19', '蜀', '永安', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('20', '蜀', '涪陵', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('21', '蜀', '武陵', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('22', '蜀', '长沙', '28.2568', '113.0823');
INSERT INTO `ppsg_geo_coord` VALUES ('23', '蜀', '江陵', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('24', '蜀', '祭坛南', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('25', '群', '武威', '38.1061', '103.0188');
INSERT INTO `ppsg_geo_coord` VALUES ('26', '群', '朔方', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('27', '群', '安定', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('28', '群', '金城', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('29', '群', '长安', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('30', '群', '武都', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('31', '群', '洛阳', '34.3158', '112.0605');
INSERT INTO `ppsg_geo_coord` VALUES ('32', '群', '西河', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('33', '群', '上庸', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('34', '群', '新野', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('35', '群', '襄阳', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('36', '群', '祭坛西', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('37', '吴', '会稽', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('38', '吴', '建安', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('39', '吴', '建业', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('40', '吴', '东海', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('41', '吴', '庐江', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('42', '吴', '寿春', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('43', '吴', '柴桑', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('44', '吴', '鄱阳', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('45', '吴', '江夏', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('46', '吴', '庐陵', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('47', '吴', '汝南', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('48', '吴', '祭坛东', null, null);
INSERT INTO `ppsg_geo_coord` VALUES ('49', '', '南阳', '33.0359', '112.4011');

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` varchar(64) DEFAULT NULL,
  `pid` bigint(11) NOT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `is_open` tinyint(1) DEFAULT '0' COMMENT '1:打开 0：关闭',
  `type` tinyint(1) DEFAULT NULL COMMENT '0:菜单 1：功能',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', '', '卡册', null, '0', 'zwicon-cog', '0', '0');
INSERT INTO `s_permission` VALUES ('2', '/ppsg/generals/index', '武将图鉴', null, '1', null, '0', '0');
INSERT INTO `s_permission` VALUES ('3', '', '配置', null, '0', 'zwicon-cog', '0', '0');
INSERT INTO `s_permission` VALUES ('4', '/ppsg/config/generals', '武将配置', null, '3', null, '0', '0');
INSERT INTO `s_permission` VALUES ('5', '/ppsg/config/country/list', '武将配置-国家配置', null, '4', null, '0', '1');
INSERT INTO `s_permission` VALUES ('6', '/ppsg/config/star/list', '武将配置-星级配置', null, '4', null, '0', '1');
INSERT INTO `s_permission` VALUES ('7', '/ppsg/config/arms/list', '武将配置-兵种配置', null, '4', null, '0', '1');
INSERT INTO `s_permission` VALUES ('8', '/ppsg/config/generalsType/list', '武将配置-武将类型', null, '4', null, '0', '1');
INSERT INTO `s_permission` VALUES ('9', '', '战力', null, '0', 'zwicon-cog', '0', '0');
INSERT INTO `s_permission` VALUES ('10', '/ppsg/xzl/index', '虚战力查询', null, '9', null, '0', '0');
INSERT INTO `s_permission` VALUES ('49', '/ppsg/config/warDevice', '战器配置', null, '3', null, '0', '0');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'USER_SELECT');
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
INSERT INTO `s_role_permission` VALUES ('1', '2');
INSERT INTO `s_role_permission` VALUES ('1', '3');
INSERT INTO `s_role_permission` VALUES ('1', '4');
INSERT INTO `s_role_permission` VALUES ('2', '1');
INSERT INTO `s_role_permission` VALUES ('2', '2');
INSERT INTO `s_role_permission` VALUES ('2', '3');
INSERT INTO `s_role_permission` VALUES ('2', '4');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'user', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `s_user` VALUES ('2', 'wang', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `s_user` VALUES ('3', 'superadmin', 'e10adc3949ba59abbe56e057f20f883e');

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
INSERT INTO `s_user_role` VALUES ('3', '2');
