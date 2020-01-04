/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : ppsg

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-01-05 02:09:46
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
-- Table structure for ppsg_discuss_post
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_discuss_post`;
CREATE TABLE `ppsg_discuss_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `images` text,
  `content` text,
  `create_time` datetime DEFAULT NULL COMMENT '发帖时间',
  `last_answer_time` datetime DEFAULT NULL COMMENT '最后一次回复时间',
  `fabulous_count` int(11) DEFAULT NULL COMMENT '点赞次数',
  `answer_count` int(11) DEFAULT NULL COMMENT '回复次数',
  `look_count` int(11) DEFAULT NULL COMMENT '查看次数',
  `status` tinyint(2) DEFAULT '0' COMMENT '0:讨论中 1：已结束',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '0:未删除 1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_discuss_post
-- ----------------------------
INSERT INTO `ppsg_discuss_post` VALUES ('5', '2', '321321', '/static/upload/image/1578158030079.jpg', '213213', '2020-01-04 17:13:53', null, '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for ppsg_discuss_post_reply
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_discuss_post_reply`;
CREATE TABLE `ppsg_discuss_post_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `discuss_id` bigint(20) DEFAULT NULL,
  `content` text COMMENT '答复内容',
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(255) DEFAULT '0' COMMENT '0:未删除 1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_discuss_post_reply
-- ----------------------------

-- ----------------------------
-- Table structure for ppsg_discuss_post_tag
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_discuss_post_tag`;
CREATE TABLE `ppsg_discuss_post_tag` (
  `discuss_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_discuss_post_tag
-- ----------------------------
INSERT INTO `ppsg_discuss_post_tag` VALUES ('5', '2');
INSERT INTO `ppsg_discuss_post_tag` VALUES ('5', '5');

-- ----------------------------
-- Table structure for ppsg_discuss_post_tags
-- ----------------------------
DROP TABLE IF EXISTS `ppsg_discuss_post_tags`;
CREATE TABLE `ppsg_discuss_post_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '标签名',
  `create_time` datetime DEFAULT NULL,
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '0:未删除 1：已删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ppsg_discuss_post_tags
-- ----------------------------
INSERT INTO `ppsg_discuss_post_tags` VALUES ('1', '魏', '2020-01-04 14:11:24', '0');
INSERT INTO `ppsg_discuss_post_tags` VALUES ('2', '蜀', '2020-01-04 14:13:57', '0');
INSERT INTO `ppsg_discuss_post_tags` VALUES ('3', '吴', '2020-01-04 14:17:14', '0');
INSERT INTO `ppsg_discuss_post_tags` VALUES ('4', '群', '2020-01-04 14:17:59', '0');
INSERT INTO `ppsg_discuss_post_tags` VALUES ('5', '集结', '2020-01-04 14:18:57', '0');

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
-- Table structure for s_log
-- ----------------------------
DROP TABLE IF EXISTS `s_log`;
CREATE TABLE `s_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(64) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `time` int(12) DEFAULT NULL COMMENT '耗时',
  `method` varchar(255) DEFAULT NULL,
  `args` varchar(512) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `result` text,
  `type` varchar(32) DEFAULT NULL COMMENT '登录、注册、。。。',
  `mapping` varchar(255) DEFAULT NULL COMMENT '访问路径',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=905 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_log
-- ----------------------------
INSERT INTO `s_log` VALUES ('810', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:28:13', '2020-01-04 17:28:13', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('811', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:28:15', '2020-01-04 17:28:15', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('812', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:28:15', '2020-01-04 17:28:15', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('813', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:28:18', '2020-01-04 17:28:18', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('814', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:28:18', '2020-01-04 17:28:18', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('815', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:30:15', '2020-01-04 17:30:15', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('816', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:30:17', '2020-01-04 17:30:17', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('817', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:30:18', '2020-01-04 17:30:18', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('818', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:30:21', '2020-01-04 17:30:21', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('819', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:30:21', '2020-01-04 17:30:21', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('820', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:32:20', '2020-01-04 17:32:20', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('821', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:32:22', '2020-01-04 17:32:22', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('822', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:32:22', '2020-01-04 17:32:22', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@615d0c7e]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('823', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:32:25', '2020-01-04 17:32:25', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('824', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:32:25', '2020-01-04 17:32:25', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('825', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:33:32', '2020-01-04 17:33:32', '0', 'login', '', null, 'login', 'PAGE', '/login');
INSERT INTO `s_log` VALUES ('826', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:37:34', '2020-01-04 17:37:35', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('827', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:37:37', '2020-01-04 17:37:37', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('828', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:37:52', '2020-01-04 17:37:52', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('829', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:37:52', '2020-01-04 17:37:52', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('830', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:37:52', '2020-01-04 17:37:52', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('831', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:39:00', '2020-01-04 17:39:00', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('832', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:39:02', '2020-01-04 17:39:02', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('833', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:39:02', '2020-01-04 17:39:27', '24', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@8773da3]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('834', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:39:27', '2020-01-04 17:39:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('835', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:39:27', '2020-01-04 17:39:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('836', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:24', '2020-01-04 17:47:24', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('837', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:24', '2020-01-04 17:47:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('838', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:24', '2020-01-04 17:47:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('839', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:24', '2020-01-04 17:47:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('840', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:24', '2020-01-04 17:47:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('841', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:24', '2020-01-04 17:47:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('842', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:34', '2020-01-04 17:47:34', '0', 'login', '', null, 'login', 'PAGE', '/login');
INSERT INTO `s_log` VALUES ('843', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:24', '2020-01-04 17:47:34', '10', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@7d435df3]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('844', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:39', '2020-01-04 17:47:39', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('845', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:41', '2020-01-04 17:47:41', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('846', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:47:41', '2020-01-04 17:47:41', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@5f578866]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('847', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:50:48', '2020-01-04 17:50:48', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('848', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:50:48', '2020-01-04 17:50:48', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@7840cfe2]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('849', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:52:37', '2020-01-04 17:52:37', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('850', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:52:37', '2020-01-04 17:52:37', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@653caba9]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('851', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:52:59', '2020-01-04 17:52:59', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('852', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:52:59', '2020-01-04 17:52:59', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@298db292]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('853', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:54:35', '2020-01-04 17:54:35', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('854', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:54:36', '2020-01-04 17:54:36', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@61872d7f]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('855', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:54:36', '2020-01-04 17:54:36', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('856', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'massDetail', '', null, 'ppsg/strategy/discuss_detail', 'PAGE', '/ppsg/strategy/discuss/detail');
INSERT INTO `s_log` VALUES ('857', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('858', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('859', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('860', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('861', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('862', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('863', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('864', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:19', '2020-01-04 17:55:19', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('865', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:24', '2020-01-04 17:55:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('866', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:24', '2020-01-04 17:55:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('867', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:55:24', '2020-01-04 17:55:24', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('868', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'massDetail', '', null, 'ppsg/strategy/discuss_detail', 'PAGE', '/ppsg/strategy/discuss/detail');
INSERT INTO `s_log` VALUES ('869', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('870', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('871', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('872', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('873', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('874', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('875', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('876', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:27', '2020-01-04 17:56:27', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('877', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:31', '2020-01-04 17:56:31', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('878', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:31', '2020-01-04 17:56:31', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@624e8994]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('879', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:33', '2020-01-04 17:56:33', '0', 'add', '', null, 'ppsg/strategy/discuss_add', 'PAGE', '/ppsg/strategy/discuss/add');
INSERT INTO `s_log` VALUES ('880', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:34', '2020-01-04 17:56:34', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPostTags@4039af69, com.ming.ppsg.entity.DiscussPostTags@663fb541, com.ming.ppsg.entity.DiscussPostTags@40b15b3d, com.ming.ppsg.entity.DiscussPostTags@670aed19, com.ming.ppsg.entity.DiscussPostTags@6ac55476]}', 'SELECT', '/ppsg/strategy/discuss/post/tags/list');
INSERT INTO `s_log` VALUES ('881', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:43', '2020-01-04 17:56:43', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('882', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 17:56:43', '2020-01-04 17:56:43', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@6659c298]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('883', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:04:23', '2020-01-04 18:04:23', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('884', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:04:23', '2020-01-04 18:04:23', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@4ab2a9fe]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('885', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:04:55', '2020-01-04 18:04:55', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('886', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:04:57', '2020-01-04 18:04:57', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('887', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:04:57', '2020-01-04 18:04:57', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@60678225]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('888', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'massDetail', '', null, 'ppsg/strategy/discuss_detail', 'PAGE', '/ppsg/strategy/discuss/detail');
INSERT INTO `s_log` VALUES ('889', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('890', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('891', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('892', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('893', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('894', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('895', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('896', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:02', '2020-01-04 18:05:02', '0', 'error', '', null, '/login', 'PAGE', '/error');
INSERT INTO `s_log` VALUES ('897', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:05', '2020-01-04 18:05:05', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('898', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:05', '2020-01-04 18:05:05', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@4ec87780]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('899', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:16', '2020-01-04 18:05:16', '0', 'index', '', null, 'ppsg/generals/index', 'PAGE', '/ppsg/generals/index');
INSERT INTO `s_log` VALUES ('900', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:30', '2020-01-04 18:05:30', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('901', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:05:30', '2020-01-04 18:05:30', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@276e73cf]}', 'SELECT', '/ppsg/strategy/discuss/post/list');
INSERT INTO `s_log` VALUES ('902', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:07:10', '2020-01-04 18:07:10', '0', 'login', 'user:{username=\"wang\",password=\"123456\",},request:{},response:{},session:{},', null, 'index', 'LOGIN', '/auth/login');
INSERT INTO `s_log` VALUES ('903', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:07:13', '2020-01-04 18:07:13', '0', 'mass', '', null, 'ppsg/strategy/discuss', 'PAGE', '/ppsg/strategy/discuss');
INSERT INTO `s_log` VALUES ('904', '2', 'wang', '0:0:0:0:0:0:0:1', '2020-01-04 18:07:13', '2020-01-04 18:07:13', '0', 'list', '', null, 'ResultMsg{code=1, msg=\'操作成功\', data=[com.ming.ppsg.entity.DiscussPost@2d0e2cce]}', 'SELECT', '/ppsg/strategy/discuss/post/list');

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
) ENGINE=MyISAM AUTO_INCREMENT=20003 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1000', '', '卡册', null, '0', 'zwicon-image-gallery', '0', '0');
INSERT INTO `s_permission` VALUES ('1100', '/ppsg/generals/index', '武将图鉴', null, '1000', null, '0', '0');
INSERT INTO `s_permission` VALUES ('2000', '', '配置', null, '0', 'zwicon-cog', '0', '0');
INSERT INTO `s_permission` VALUES ('2100', '/ppsg/config/generals', '武将配置', null, '2000', null, '0', '0');
INSERT INTO `s_permission` VALUES ('2101', '/ppsg/config/country/list', '武将配置-国家配置', null, '2100', null, '0', '1');
INSERT INTO `s_permission` VALUES ('2102', '/ppsg/config/star/list', '武将配置-星级配置', null, '2100', null, '0', '1');
INSERT INTO `s_permission` VALUES ('2103', '/ppsg/config/arms/list', '武将配置-兵种配置', null, '2100', null, '0', '1');
INSERT INTO `s_permission` VALUES ('2104', '/ppsg/config/generalsType/list', '武将配置-武将类型', null, '2100', null, '0', '1');
INSERT INTO `s_permission` VALUES ('3000', '', '战力', null, '0', 'zwicon-layer-stack', '0', '0');
INSERT INTO `s_permission` VALUES ('3100', '/ppsg/xzl/index', '虚战力查询', null, '3000', null, '0', '0');
INSERT INTO `s_permission` VALUES ('2200', '/ppsg/config/warDevice', '战器配置', null, '2000', null, '0', '0');
INSERT INTO `s_permission` VALUES ('4000', '', '攻略', null, '0', 'zwicon-comment', '0', '0');
INSERT INTO `s_permission` VALUES ('4100', ' /ppsg/strategy/discuss', '讨论区', null, '4000', null, '0', '0');
INSERT INTO `s_permission` VALUES ('3101', '/ppsg/xzl/search', '虚战力查询-查询', null, '3100', null, '0', '1');

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
INSERT INTO `s_role_permission` VALUES ('1', '3101');
INSERT INTO `s_role_permission` VALUES ('2', '3101');
INSERT INTO `s_role_permission` VALUES ('2', '2101');
INSERT INTO `s_role_permission` VALUES ('2', '2102');
INSERT INTO `s_role_permission` VALUES ('2', '2103');
INSERT INTO `s_role_permission` VALUES ('2', '2104');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `head_portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号',
  `skin` varchar(255) DEFAULT NULL COMMENT '自定义背景图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `disabled` tinyint(1) DEFAULT '0' COMMENT '0:启用 1：禁用',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '0:未删除 1：删除',
  PRIMARY KEY (`id`,`username`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'user', 'e10adc3949ba59abbe56e057f20f883e', '/static/formwork/demo/img/profile-pics/5.jpg', '2132@123.cks', null, '/static/formwork/img/bg/1.jpg', '2020-01-01 16:56:08', '0', '0');
INSERT INTO `s_user` VALUES ('2', 'wang', 'e10adc3949ba59abbe56e057f20f883e', '/static/formwork/demo/img/profile-pics/9.jpg', '2132@123.cks', null, '/static/formwork/img/bg/1.jpg', '2020-01-02 16:56:12', '0', '0');
INSERT INTO `s_user` VALUES ('3', 'superadmin', 'e10adc3949ba59abbe56e057f20f883e', '/static/formwork/demo/img/profile-pics/5.jpg', '2132@123.cks', null, '/static/formwork/img/bg/1.jpg', '2020-01-03 16:56:15', '0', '0');
INSERT INTO `s_user` VALUES ('10', 'wang1', 'e10adc3949ba59abbe56e057f20f883e', '/static/formwork/demo/img/profile-pics/5.jpg', '2132@123.cks', null, '/static/formwork/img/bg/1.jpg', '2020-01-04 08:37:16', '0', '0');

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
INSERT INTO `s_user_role` VALUES ('2', '2');
INSERT INTO `s_user_role` VALUES ('3', '2');
INSERT INTO `s_user_role` VALUES ('10', '1');
