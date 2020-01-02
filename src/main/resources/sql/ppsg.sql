/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : ppsg

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2020-01-02 18:40:49
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
-- Table structure for s_geo_coord
-- ----------------------------
DROP TABLE IF EXISTS `s_geo_coord`;
CREATE TABLE `s_geo_coord` (
  `id` int(11) DEFAULT NULL,
  `province` varchar(384) DEFAULT NULL,
  `city` varchar(384) DEFAULT NULL,
  `latitude` varchar(96) DEFAULT NULL,
  `longitude` varchar(96) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_geo_coord
-- ----------------------------
INSERT INTO `s_geo_coord` VALUES ('1111', '山西', '忻州', '38.8971', '112.4561');
INSERT INTO `s_geo_coord` VALUES ('1112', '山西', '吕梁', '37.7325', '111.3574');
INSERT INTO `s_geo_coord` VALUES ('1113', '山西', '临汾', '36.1615', '111.4783');
INSERT INTO `s_geo_coord` VALUES ('1114', '山西', '晋中', '37.37', '112.7747');
INSERT INTO `s_geo_coord` VALUES ('1115', '山西', '运城', '35.2002', '111.1487');
INSERT INTO `s_geo_coord` VALUES ('1116', '山西', '大同', '39.8035', '113.7854');
INSERT INTO `s_geo_coord` VALUES ('1117', '山西', '长治', '36.4746', '112.8625');
INSERT INTO `s_geo_coord` VALUES ('1118', '山西', '朔州', '39.6991', '113.0713');
INSERT INTO `s_geo_coord` VALUES ('1119', '山西', '晋城', '35.6342', '112.7856');
INSERT INTO `s_geo_coord` VALUES ('1120', '山西', '太原', '37.9413', '112.3352');
INSERT INTO `s_geo_coord` VALUES ('1121', '山西', '阳泉', '38.0951', '113.4778');
INSERT INTO `s_geo_coord` VALUES ('1122', '陕西', '榆林', '38.205', '109.8743');
INSERT INTO `s_geo_coord` VALUES ('1123', '陕西', '延安', '36.4252', '109.1052');
INSERT INTO `s_geo_coord` VALUES ('1124', '陕西', '汉中', '33.0139', '106.886');
INSERT INTO `s_geo_coord` VALUES ('1125', '陕西', '安康', '32.7722', '109.1162');
INSERT INTO `s_geo_coord` VALUES ('1126', '陕西', '商洛', '33.761', '109.8083');
INSERT INTO `s_geo_coord` VALUES ('1127', '陕西', '宝鸡', '34.3433', '107.1826');
INSERT INTO `s_geo_coord` VALUES ('1128', '陕西', '渭南', '35.0299', '109.7864');
INSERT INTO `s_geo_coord` VALUES ('1129', '陕西', '咸阳', '34.8706', '108.4131');
INSERT INTO `s_geo_coord` VALUES ('1130', '陕西', '西安', '34.2004', '109.1162');
INSERT INTO `s_geo_coord` VALUES ('1131', '陕西', '铜川', '35.1947', '109.0393');
INSERT INTO `s_geo_coord` VALUES ('1132', '山东', '烟台', '37.5128', '120.7397');
INSERT INTO `s_geo_coord` VALUES ('1133', '山东', '临沂', '35.2936', '118.3118');
INSERT INTO `s_geo_coord` VALUES ('1134', '山东', '潍坊', '36.524', '119.0918');
INSERT INTO `s_geo_coord` VALUES ('1135', '山东', '青岛', '36.3373', '120.4651');
INSERT INTO `s_geo_coord` VALUES ('1136', '山东', '菏泽', '35.2057', '115.6201');
INSERT INTO `s_geo_coord` VALUES ('1137', '山东', '济宁', '35.3375', '116.8286');
INSERT INTO `s_geo_coord` VALUES ('1138', '山东', '德州', '37.2107', '116.6858');
INSERT INTO `s_geo_coord` VALUES ('1139', '山东', '滨州', '37.4963', '117.8174');
INSERT INTO `s_geo_coord` VALUES ('1140', '山东', '聊城', '36.4032', '115.9167');
INSERT INTO `s_geo_coord` VALUES ('1141', '山东', '东营', '37.5513', '118.7073');
INSERT INTO `s_geo_coord` VALUES ('1142', '山东', '济南', '36.8701', '117.1582');
INSERT INTO `s_geo_coord` VALUES ('1143', '山东', '泰安', '36.0516', '117.0264');
INSERT INTO `s_geo_coord` VALUES ('1144', '山东', '威海', '37.1393', '121.9482');
INSERT INTO `s_geo_coord` VALUES ('1145', '山东', '日照', '35.5023', '119.2786');
INSERT INTO `s_geo_coord` VALUES ('1146', '山东', '淄博', '36.6064', '118.0371');
INSERT INTO `s_geo_coord` VALUES ('1147', '山东', '枣庄', '34.8926', '117.323');
INSERT INTO `s_geo_coord` VALUES ('1148', '山东', '莱芜', '36.2714', '117.6526');
INSERT INTO `s_geo_coord` VALUES ('1149', '青海', '海西蒙古族藏族自治州', '37.1118', '94.9768');
INSERT INTO `s_geo_coord` VALUES ('1150', '青海', '玉树藏族自治州', '33.9368', '93.5925');
INSERT INTO `s_geo_coord` VALUES ('1151', '青海', '果洛藏族自治州', '34.0466', '99.3823');
INSERT INTO `s_geo_coord` VALUES ('1152', '青海', '海南藏族自治州', '35.9418', '100.3711');
INSERT INTO `s_geo_coord` VALUES ('1153', '青海', '海北藏族自治州', '37.9138', '100.3711');
INSERT INTO `s_geo_coord` VALUES ('1154', '青海', '黄南藏族自治州', '35.1178', '101.5686');
INSERT INTO `s_geo_coord` VALUES ('1155', '青海', '海东地区', '36.2988', '102.3706');
INSERT INTO `s_geo_coord` VALUES ('1156', '青海', '西宁', '36.8207', '101.4038');
INSERT INTO `s_geo_coord` VALUES ('1157', '宁夏', '吴忠', '37.3755', '106.853');
INSERT INTO `s_geo_coord` VALUES ('1158', '宁夏', '中卫', '36.9525', '105.4028');
INSERT INTO `s_geo_coord` VALUES ('1159', '宁夏', '固原', '35.9363', '106.1389');
INSERT INTO `s_geo_coord` VALUES ('1160', '宁夏', '银川', '38.1775', '106.3586');
INSERT INTO `s_geo_coord` VALUES ('1161', '宁夏', '石嘴山', '39.0015', '106.4795');
INSERT INTO `s_geo_coord` VALUES ('1162', '内蒙古', '呼伦贝尔', '50.2185', '120.8057');
INSERT INTO `s_geo_coord` VALUES ('1163', '内蒙古', '阿拉善盟', '40.1001', '102.019');
INSERT INTO `s_geo_coord` VALUES ('1164', '内蒙古', '锡林郭勒盟', '44.176', '115.6421');
INSERT INTO `s_geo_coord` VALUES ('1165', '内蒙古', '鄂尔多斯', '39.2487', '108.9734');
INSERT INTO `s_geo_coord` VALUES ('1166', '内蒙古', '赤峰', '43.2642', '118.6743');
INSERT INTO `s_geo_coord` VALUES ('1167', '内蒙古', '巴彦淖尔', '41.3196', '107.5562');
INSERT INTO `s_geo_coord` VALUES ('1168', '内蒙古', '通辽', '43.9673', '121.4758');
INSERT INTO `s_geo_coord` VALUES ('1169', '内蒙古', '乌兰察布', '41.77', '112.5769');
INSERT INTO `s_geo_coord` VALUES ('1170', '内蒙古', '兴安盟', '46.1426', '121.3879');
INSERT INTO `s_geo_coord` VALUES ('1171', '内蒙古', '包头', '41.4899', '110.3467');
INSERT INTO `s_geo_coord` VALUES ('1172', '内蒙古', '呼和浩特', '40.4901', '111.4124');
INSERT INTO `s_geo_coord` VALUES ('1173', '内蒙古', '乌海', '39.4739', '106.886');
INSERT INTO `s_geo_coord` VALUES ('1174', '辽宁', '大连', '39.4409', '122.2229');
INSERT INTO `s_geo_coord` VALUES ('1175', '辽宁', '朝阳', '41.4899', '120.0696');
INSERT INTO `s_geo_coord` VALUES ('1176', '辽宁', '丹东', '40.4242', '124.541');
INSERT INTO `s_geo_coord` VALUES ('1177', '辽宁', '铁岭', '42.7423', '124.2773');
INSERT INTO `s_geo_coord` VALUES ('1178', '辽宁', '沈阳', '42.1216', '123.1238');
INSERT INTO `s_geo_coord` VALUES ('1179', '辽宁', '抚顺', '41.8579', '124.585');
INSERT INTO `s_geo_coord` VALUES ('1180', '辽宁', '葫芦岛', '40.578', '120.1575');
INSERT INTO `s_geo_coord` VALUES ('1181', '辽宁', '阜新', '42.2699', '122.0032');
INSERT INTO `s_geo_coord` VALUES ('1182', '辽宁', '锦州', '41.4294', '121.6626');
INSERT INTO `s_geo_coord` VALUES ('1183', '辽宁', '鞍山', '40.6055', '123.0798');
INSERT INTO `s_geo_coord` VALUES ('1184', '辽宁', '本溪', '41.1987', '124.1455');
INSERT INTO `s_geo_coord` VALUES ('1185', '辽宁', '营口', '40.4297', '122.4316');
INSERT INTO `s_geo_coord` VALUES ('1186', '辽宁', '辽阳', '41.1383', '123.4094');
INSERT INTO `s_geo_coord` VALUES ('1187', '辽宁', '盘锦', '41.0449', '121.9482');
INSERT INTO `s_geo_coord` VALUES ('1188', '江西', '赣州', '25.8124', '115.2795');
INSERT INTO `s_geo_coord` VALUES ('1189', '江西', '吉安', '26.9659', '114.884');
INSERT INTO `s_geo_coord` VALUES ('1190', '江西', '上饶', '28.7292', '117.8613');
INSERT INTO `s_geo_coord` VALUES ('1191', '江西', '九江', '29.3774', '115.4224');
INSERT INTO `s_geo_coord` VALUES ('1192', '江西', '抚州', '27.4933', '116.4441');
INSERT INTO `s_geo_coord` VALUES ('1193', '江西', '宜春', '28.3228', '115.0159');
INSERT INTO `s_geo_coord` VALUES ('1194', '江西', '南昌', '28.6633', '116.0046');
INSERT INTO `s_geo_coord` VALUES ('1195', '江西', '景德镇', '29.3225', '117.334');
INSERT INTO `s_geo_coord` VALUES ('1196', '江西', '萍乡', '27.4823', '113.9282');
INSERT INTO `s_geo_coord` VALUES ('1197', '江西', '鹰潭', '28.2349', '117.0813');
INSERT INTO `s_geo_coord` VALUES ('1198', '江西', '新余', '27.8174', '114.95');
INSERT INTO `s_geo_coord` VALUES ('1199', '江苏', '盐城', '33.5577', '120.2234');
INSERT INTO `s_geo_coord` VALUES ('1200', '江苏', '徐州', '34.3268', '117.5208');
INSERT INTO `s_geo_coord` VALUES ('1201', '江苏', '南通', '32.1625', '121.1023');
INSERT INTO `s_geo_coord` VALUES ('1202', '江苏', '淮安', '33.4039', '118.927');
INSERT INTO `s_geo_coord` VALUES ('1203', '江苏', '苏州', '31.3989', '120.6519');
INSERT INTO `s_geo_coord` VALUES ('1204', '江苏', '宿迁', '33.7775', '118.5535');
INSERT INTO `s_geo_coord` VALUES ('1205', '江苏', '连云港', '34.552', '119.1248');
INSERT INTO `s_geo_coord` VALUES ('1206', '江苏', '扬州', '32.8162', '119.4653');
INSERT INTO `s_geo_coord` VALUES ('1207', '江苏', '南京', '31.9208', '118.8062');
INSERT INTO `s_geo_coord` VALUES ('1208', '江苏', '泰州', '32.5525', '120.0586');
INSERT INTO `s_geo_coord` VALUES ('1209', '江苏', '无锡', '31.5527', '120.3442');
INSERT INTO `s_geo_coord` VALUES ('1210', '江苏', '常州', '31.5582', '119.4543');
INSERT INTO `s_geo_coord` VALUES ('1211', '江苏', '镇江', '31.9702', '119.4763');
INSERT INTO `s_geo_coord` VALUES ('1212', '吉林', '延边朝鲜族自治州', '43.2587', '129.397');
INSERT INTO `s_geo_coord` VALUES ('1213', '吉林', '吉林', '43.6047', '126.8372');
INSERT INTO `s_geo_coord` VALUES ('1214', '吉林', '白城', '45.2637', '123.0029');
INSERT INTO `s_geo_coord` VALUES ('1215', '吉林', '松原', '44.7198', '124.0906');
INSERT INTO `s_geo_coord` VALUES ('1216', '吉林', '长春', '44.2584', '125.8154');
INSERT INTO `s_geo_coord` VALUES ('1217', '吉林', '白山', '42.0941', '127.2217');
INSERT INTO `s_geo_coord` VALUES ('1218', '吉林', '通化', '41.8579', '125.9583');
INSERT INTO `s_geo_coord` VALUES ('1219', '吉林', '四平', '43.4894', '124.541');
INSERT INTO `s_geo_coord` VALUES ('1220', '吉林', '辽源', '42.7643', '125.343');
INSERT INTO `s_geo_coord` VALUES ('1221', '湖南', '怀化', '27.4438', '109.9512');
INSERT INTO `s_geo_coord` VALUES ('1222', '湖南', '永州', '25.752', '111.709');
INSERT INTO `s_geo_coord` VALUES ('1223', '湖南', '邵阳', '26.8121', '110.9619');
INSERT INTO `s_geo_coord` VALUES ('1224', '湖南', '郴州', '25.8673', '113.2361');
INSERT INTO `s_geo_coord` VALUES ('1225', '湖南', '常德', '29.2676', '111.4014');
INSERT INTO `s_geo_coord` VALUES ('1226', '湖南', '湘西土家族苗族自治州', '28.6743', '109.7864');
INSERT INTO `s_geo_coord` VALUES ('1227', '湖南', '衡阳', '26.7902', '112.4121');
INSERT INTO `s_geo_coord` VALUES ('1228', '湖南', '岳阳', '29.1357', '113.2361');
INSERT INTO `s_geo_coord` VALUES ('1229', '湖南', '益阳', '28.3832', '111.731');
INSERT INTO `s_geo_coord` VALUES ('1230', '湖南', '长沙', '28.2568', '113.0823');
INSERT INTO `s_geo_coord` VALUES ('1231', '湖南', '株洲', '27.0319', '113.5327');
INSERT INTO `s_geo_coord` VALUES ('1232', '湖南', '张家界', '29.328', '110.5115');
INSERT INTO `s_geo_coord` VALUES ('1233', '湖南', '娄底', '27.7185', '111.6431');
INSERT INTO `s_geo_coord` VALUES ('1234', '湖南', '湘潭', '27.7075', '112.5439');
INSERT INTO `s_geo_coord` VALUES ('1235', '湖北', '恩施土家族苗族自治州', '30.2563', '109.5007');
INSERT INTO `s_geo_coord` VALUES ('1236', '湖北', '十堰', '32.3877', '110.5115');
INSERT INTO `s_geo_coord` VALUES ('1237', '湖北', '宜昌', '30.7617', '111.1707');
INSERT INTO `s_geo_coord` VALUES ('1238', '湖北', '襄樊', '31.9263', '111.9397');
INSERT INTO `s_geo_coord` VALUES ('1239', '湖北', '黄冈', '30.6628', '115.2686');
INSERT INTO `s_geo_coord` VALUES ('1240', '湖北', '荆州', '30.0092', '113.291');
INSERT INTO `s_geo_coord` VALUES ('1241', '湖北', '荆门', '30.9979', '112.6758');
INSERT INTO `s_geo_coord` VALUES ('1242', '湖北', '咸宁', '29.6631', '114.2578');
INSERT INTO `s_geo_coord` VALUES ('1243', '湖北', '随州', '31.8768', '113.4338');
INSERT INTO `s_geo_coord` VALUES ('1244', '湖北', '孝感', '31.1188', '113.9502');
INSERT INTO `s_geo_coord` VALUES ('1245', '湖北', '武汉', '30.6628', '114.3896');
INSERT INTO `s_geo_coord` VALUES ('1246', '湖北', '黄石', '29.9213', '115.0159');
INSERT INTO `s_geo_coord` VALUES ('1247', '湖北', '神农架林区', '31.5802', '110.4565');
INSERT INTO `s_geo_coord` VALUES ('1248', '湖北', '天门', '30.6409', '113.0273');
INSERT INTO `s_geo_coord` VALUES ('1249', '湖北', '仙桃', '30.3003', '113.3789');
INSERT INTO `s_geo_coord` VALUES ('1250', '湖北', '潜江', '30.3607', '112.7637');
INSERT INTO `s_geo_coord` VALUES ('1251', '湖北', '鄂州', '30.4102', '114.7302');
INSERT INTO `s_geo_coord` VALUES ('1252', '黑龙江', '黑河', '49.2957', '127.1448');
INSERT INTO `s_geo_coord` VALUES ('1253', '黑龙江', '大兴安岭地区', '52.2345', '124.1016');
INSERT INTO `s_geo_coord` VALUES ('1254', '黑龙江', '哈尔滨', '45.368', '127.9688');
INSERT INTO `s_geo_coord` VALUES ('1255', '黑龙江', '齐齐哈尔', '47.5818', '124.541');
INSERT INTO `s_geo_coord` VALUES ('1256', '黑龙江', '牡丹江', '44.7089', '129.7815');
INSERT INTO `s_geo_coord` VALUES ('1257', '黑龙江', '绥化', '46.8018', '126.7163');
INSERT INTO `s_geo_coord` VALUES ('1258', '黑龙江', '伊春', '47.9608', '129.1992');
INSERT INTO `s_geo_coord` VALUES ('1259', '黑龙江', '佳木斯', '47.5763', '133.0005');
INSERT INTO `s_geo_coord` VALUES ('1260', '黑龙江', '鸡西', '45.7361', '132.7917');
INSERT INTO `s_geo_coord` VALUES ('1261', '黑龙江', '双鸭山', '46.7523', '133.5938');
INSERT INTO `s_geo_coord` VALUES ('1262', '黑龙江', '大庆', '46.4282', '124.7717');
INSERT INTO `s_geo_coord` VALUES ('1263', '黑龙江', '鹤岗', '47.7081', '130.4407');
INSERT INTO `s_geo_coord` VALUES ('1264', '黑龙江', '七台河', '45.9558', '131.2756');
INSERT INTO `s_geo_coord` VALUES ('1265', '河南', '南阳', '33.0359', '112.4011');
INSERT INTO `s_geo_coord` VALUES ('1266', '河南', '信阳', '32.0197', '114.8291');
INSERT INTO `s_geo_coord` VALUES ('1267', '河南', '洛阳', '34.3158', '112.0605');
INSERT INTO `s_geo_coord` VALUES ('1268', '河南', '驻马店', '32.9041', '114.1589');
INSERT INTO `s_geo_coord` VALUES ('1269', '河南', '周口', '33.6951', '114.873');
INSERT INTO `s_geo_coord` VALUES ('1270', '河南', '商丘', '34.2828', '115.741');
INSERT INTO `s_geo_coord` VALUES ('1271', '河南', '三门峡', '34.3158', '110.8301');
INSERT INTO `s_geo_coord` VALUES ('1272', '河南', '新乡', '35.3595', '114.2029');
INSERT INTO `s_geo_coord` VALUES ('1273', '河南', '平顶山', '33.739', '112.9724');
INSERT INTO `s_geo_coord` VALUES ('1274', '河南', '郑州', '34.6234', '113.4668');
INSERT INTO `s_geo_coord` VALUES ('1275', '河南', '安阳', '36.0022', '114.5325');
INSERT INTO `s_geo_coord` VALUES ('1276', '河南', '开封', '34.6124', '114.5764');
INSERT INTO `s_geo_coord` VALUES ('1277', '河南', '焦作', '35.1508', '112.8406');
INSERT INTO `s_geo_coord` VALUES ('1278', '河南', '许昌', '34.0466', '113.6975');
INSERT INTO `s_geo_coord` VALUES ('1279', '河南', '濮阳', '35.799', '115.1917');
INSERT INTO `s_geo_coord` VALUES ('1280', '河南', '漯河', '33.6951', '113.8733');
INSERT INTO `s_geo_coord` VALUES ('1281', '河南', '鹤壁', '35.744', '114.3787');
INSERT INTO `s_geo_coord` VALUES ('1282', '河北', '承德', '41.4075', '117.5757');
INSERT INTO `s_geo_coord` VALUES ('1283', '河北', '张家口', '40.8527', '115.1477');
INSERT INTO `s_geo_coord` VALUES ('1284', '河北', '保定', '39.0948', '115.0488');
INSERT INTO `s_geo_coord` VALUES ('1285', '河北', '唐山', '39.6826', '118.4766');
INSERT INTO `s_geo_coord` VALUES ('1286', '河北', '沧州', '38.2104', '116.8286');
INSERT INTO `s_geo_coord` VALUES ('1287', '河北', '石家庄', '38.1006', '114.4995');
INSERT INTO `s_geo_coord` VALUES ('1288', '河北', '邢台', '37.2821', '114.8071');
INSERT INTO `s_geo_coord` VALUES ('1289', '河北', '邯郸', '36.535', '114.4775');
INSERT INTO `s_geo_coord` VALUES ('1290', '河北', '秦皇岛', '40.0232', '119.2126');
INSERT INTO `s_geo_coord` VALUES ('1291', '河北', '衡水', '37.7161', '115.8838');
INSERT INTO `s_geo_coord` VALUES ('1292', '河北', '廊坊', '39.0509', '116.521');
INSERT INTO `s_geo_coord` VALUES ('1293', '海南', '儋州', '19.5653', '109.3291');
INSERT INTO `s_geo_coord` VALUES ('1294', '海南', '文昌', '19.7823', '110.8905');
INSERT INTO `s_geo_coord` VALUES ('1295', '海南', '乐东黎族自治县', '18.6301', '109.0283');
INSERT INTO `s_geo_coord` VALUES ('1296', '海南', '三亚', '18.3698', '109.3716');
INSERT INTO `s_geo_coord` VALUES ('1297', '海南', '琼中黎族苗族自治县', '19.0736', '109.8413');
INSERT INTO `s_geo_coord` VALUES ('1298', '海南', '东方', '19.0414', '108.8498');
INSERT INTO `s_geo_coord` VALUES ('1299', '海南', '海口', '19.8516', '110.3893');
INSERT INTO `s_geo_coord` VALUES ('1300', '海南', '万宁', '18.8388', '110.3137');
INSERT INTO `s_geo_coord` VALUES ('1301', '海南', '澄迈县', '19.7314', '109.9937');
INSERT INTO `s_geo_coord` VALUES ('1302', '海南', '白沙黎族自治县', '19.211', '109.3703');
INSERT INTO `s_geo_coord` VALUES ('1303', '海南', '琼海', '19.224', '110.4208');
INSERT INTO `s_geo_coord` VALUES ('1304', '海南', '昌江黎族自治县', '19.2137', '109.0407');
INSERT INTO `s_geo_coord` VALUES ('1305', '海南', '临高县', '19.8063', '109.6957');
INSERT INTO `s_geo_coord` VALUES ('1306', '海南', '陵水黎族自治县', '18.5415', '109.9924');
INSERT INTO `s_geo_coord` VALUES ('1307', '海南', '屯昌县', '19.362', '110.0377');
INSERT INTO `s_geo_coord` VALUES ('1308', '海南', '定安县', '19.4698', '110.3384');
INSERT INTO `s_geo_coord` VALUES ('1309', '海南', '保亭黎族苗族自治县', '18.6108', '109.6284');
INSERT INTO `s_geo_coord` VALUES ('1310', '海南', '五指山', '18.8299', '109.5282');
INSERT INTO `s_geo_coord` VALUES ('1311', '贵州', '遵义', '28.1744', '106.908');
INSERT INTO `s_geo_coord` VALUES ('1312', '贵州', '黔东南苗族侗族自治州', '26.4166', '108.4241');
INSERT INTO `s_geo_coord` VALUES ('1313', '贵州', '毕节地区', '27.0648', '105.1611');
INSERT INTO `s_geo_coord` VALUES ('1314', '贵州', '黔南布依族苗族自治州', '25.8398', '107.2485');
INSERT INTO `s_geo_coord` VALUES ('1315', '贵州', '铜仁地区', '28.0096', '108.6218');
INSERT INTO `s_geo_coord` VALUES ('1316', '贵州', '黔西南布依族苗族自治州', '25.3949', '105.5347');
INSERT INTO `s_geo_coord` VALUES ('1317', '贵州', '六盘水', '26.0925', '104.7546');
INSERT INTO `s_geo_coord` VALUES ('1318', '贵州', '安顺', '25.9882', '105.9082');
INSERT INTO `s_geo_coord` VALUES ('1319', '贵州', '贵阳', '26.7682', '106.6992');
INSERT INTO `s_geo_coord` VALUES ('1320', '广西', '百色', '23.9227', '106.6003');
INSERT INTO `s_geo_coord` VALUES ('1321', '广西', '河池', '24.5819', '107.8638');
INSERT INTO `s_geo_coord` VALUES ('1322', '广西', '桂林', '25.318', '110.5554');
INSERT INTO `s_geo_coord` VALUES ('1323', '广西', '南宁', '23.1152', '108.479');
INSERT INTO `s_geo_coord` VALUES ('1324', '广西', '柳州', '24.9774', '109.3799');
INSERT INTO `s_geo_coord` VALUES ('1325', '广西', '崇左', '22.4725', '107.3364');
INSERT INTO `s_geo_coord` VALUES ('1326', '广西', '来宾', '23.8403', '109.7095');
INSERT INTO `s_geo_coord` VALUES ('1327', '广西', '玉林', '22.3792', '110.2148');
INSERT INTO `s_geo_coord` VALUES ('1328', '广西', '梧州', '23.5052', '110.9949');
INSERT INTO `s_geo_coord` VALUES ('1329', '广西', '贺州', '24.4006', '111.3135');
INSERT INTO `s_geo_coord` VALUES ('1330', '广西', '钦州', '22.0935', '109.0283');
INSERT INTO `s_geo_coord` VALUES ('1331', '广西', '贵港', '23.3459', '109.9402');
INSERT INTO `s_geo_coord` VALUES ('1332', '广西', '防城港', '21.9287', '108.0505');
INSERT INTO `s_geo_coord` VALUES ('1333', '广西', '北海', '21.6211', '109.314');
INSERT INTO `s_geo_coord` VALUES ('1334', '广东', '清远', '24.3292', '112.9175');
INSERT INTO `s_geo_coord` VALUES ('1335', '广东', '韶关', '24.7028', '113.7964');
INSERT INTO `s_geo_coord` VALUES ('1336', '广东', '湛江', '20.9894', '110.3577');
INSERT INTO `s_geo_coord` VALUES ('1337', '广东', '梅州', '24.1534', '116.1255');
INSERT INTO `s_geo_coord` VALUES ('1338', '广东', '河源', '23.9722', '114.917');
INSERT INTO `s_geo_coord` VALUES ('1339', '广东', '肇庆', '23.5822', '112.1265');
INSERT INTO `s_geo_coord` VALUES ('1340', '广东', '惠州', '23.1647', '114.6204');
INSERT INTO `s_geo_coord` VALUES ('1341', '广东', '茂名', '22.0221', '111.0059');
INSERT INTO `s_geo_coord` VALUES ('1342', '广东', '江门', '22.1484', '112.6318');
INSERT INTO `s_geo_coord` VALUES ('1343', '广东', '阳江', '22.0715', '111.8298');
INSERT INTO `s_geo_coord` VALUES ('1344', '广东', '云浮', '22.8516', '111.7859');
INSERT INTO `s_geo_coord` VALUES ('1345', '广东', '广州', '23.2196', '113.5107');
INSERT INTO `s_geo_coord` VALUES ('1346', '广东', '汕尾', '23.0438', '115.5762');
INSERT INTO `s_geo_coord` VALUES ('1347', '广东', '揭阳', '23.313', '116.1255');
INSERT INTO `s_geo_coord` VALUES ('1348', '广东', '珠海', '22.1155', '113.7305');
INSERT INTO `s_geo_coord` VALUES ('1349', '广东', '佛山', '23.1097', '112.8955');
INSERT INTO `s_geo_coord` VALUES ('1350', '广东', '潮州', '23.8293', '116.7847');
INSERT INTO `s_geo_coord` VALUES ('1351', '广东', '汕头', '23.3405', '117.1692');
INSERT INTO `s_geo_coord` VALUES ('1352', '广东', '深圳', '22.5439', '114.5435');
INSERT INTO `s_geo_coord` VALUES ('1353', '广东', '东莞', '22.901', '113.8953');
INSERT INTO `s_geo_coord` VALUES ('1354', '广东', '中山', '22.478', '113.4229');
INSERT INTO `s_geo_coord` VALUES ('1355', '甘肃', '酒泉', '40.4517', '96.2622');
INSERT INTO `s_geo_coord` VALUES ('1356', '甘肃', '张掖', '38.7433', '99.7998');
INSERT INTO `s_geo_coord` VALUES ('1357', '甘肃', '甘南藏族自治州', '34.6893', '102.9199');
INSERT INTO `s_geo_coord` VALUES ('1358', '甘肃', '武威', '38.1061', '103.0188');
INSERT INTO `s_geo_coord` VALUES ('1359', '甘肃', '陇南', '33.5632', '105.304');
INSERT INTO `s_geo_coord` VALUES ('1360', '甘肃', '庆阳', '36.2', '107.5342');
INSERT INTO `s_geo_coord` VALUES ('1361', '甘肃', '白银', '36.5076', '104.8645');
INSERT INTO `s_geo_coord` VALUES ('1362', '甘肃', '定西', '35.0848', '104.5569');
INSERT INTO `s_geo_coord` VALUES ('1363', '甘肃', '天水', '34.6289', '105.6445');
INSERT INTO `s_geo_coord` VALUES ('1364', '甘肃', '兰州', '36.3043', '103.5901');
INSERT INTO `s_geo_coord` VALUES ('1365', '甘肃', '平凉', '35.321', '107.0728');
INSERT INTO `s_geo_coord` VALUES ('1366', '甘肃', '临夏回族自治州', '35.5737', '103.2715');
INSERT INTO `s_geo_coord` VALUES ('1367', '甘肃', '金昌', '38.5126', '102.074');
INSERT INTO `s_geo_coord` VALUES ('1368', '甘肃', '嘉峪关', '39.8035', '98.1738');
INSERT INTO `s_geo_coord` VALUES ('1369', '福建', '南平', '27.2845', '118.136');
INSERT INTO `s_geo_coord` VALUES ('1370', '福建', '三明', '26.3013', '117.5317');
INSERT INTO `s_geo_coord` VALUES ('1371', '福建', '龙岩', '25.2026', '116.8066');
INSERT INTO `s_geo_coord` VALUES ('1372', '福建', '宁德', '26.9824', '119.6521');
INSERT INTO `s_geo_coord` VALUES ('1373', '福建', '福州', '25.9222', '119.4543');
INSERT INTO `s_geo_coord` VALUES ('1374', '福建', '漳州', '24.3732', '117.5757');
INSERT INTO `s_geo_coord` VALUES ('1375', '福建', '泉州', '25.1147', '118.3228');
INSERT INTO `s_geo_coord` VALUES ('1376', '福建', '莆田', '25.3455', '119.0918');
INSERT INTO `s_geo_coord` VALUES ('1377', '福建', '厦门', '24.6478', '118.1689');
INSERT INTO `s_geo_coord` VALUES ('1378', '重庆', '酉阳土家族苗族自治县', '28.8666', '108.8196');
INSERT INTO `s_geo_coord` VALUES ('1379', '重庆', '奉节县', '30.9265', '109.3909');
INSERT INTO `s_geo_coord` VALUES ('1380', '重庆', '巫溪县', '31.4813', '109.3359');
INSERT INTO `s_geo_coord` VALUES ('1381', '重庆', '开县', '31.2561', '108.4131');
INSERT INTO `s_geo_coord` VALUES ('1382', '重庆', '彭水苗族土家族自治县', '29.3994', '108.2043');
INSERT INTO `s_geo_coord` VALUES ('1383', '重庆', '云阳县', '31.0089', '108.8306');
INSERT INTO `s_geo_coord` VALUES ('1384', '重庆', '万州区', '30.6958', '108.3911');
INSERT INTO `s_geo_coord` VALUES ('1385', '重庆', '城口县', '31.9098', '108.7756');
INSERT INTO `s_geo_coord` VALUES ('1386', '重庆', '江津区', '28.9874', '106.2158');
INSERT INTO `s_geo_coord` VALUES ('1387', '重庆', '石柱土家族自治县', '30.1025', '108.2813');
INSERT INTO `s_geo_coord` VALUES ('1388', '重庆', '巫山县', '31.1188', '109.8853');
INSERT INTO `s_geo_coord` VALUES ('1389', '重庆', '涪陵区', '29.6796', '107.3364');
INSERT INTO `s_geo_coord` VALUES ('1390', '重庆', '丰都县', '29.9048', '107.8418');
INSERT INTO `s_geo_coord` VALUES ('1391', '重庆', '武隆县', '29.35', '107.655');
INSERT INTO `s_geo_coord` VALUES ('1392', '重庆', '南川区', '29.1302', '107.1716');
INSERT INTO `s_geo_coord` VALUES ('1393', '重庆', '秀山土家族苗族自治县', '28.5205', '109.0173');
INSERT INTO `s_geo_coord` VALUES ('1394', '重庆', '黔江区', '29.4708', '108.7207');
INSERT INTO `s_geo_coord` VALUES ('1395', '重庆', '合川区', '30.108', '106.3257');
INSERT INTO `s_geo_coord` VALUES ('1396', '重庆', '綦江县', '28.8171', '106.6553');
INSERT INTO `s_geo_coord` VALUES ('1397', '重庆', '忠县', '30.3223', '107.8967');
INSERT INTO `s_geo_coord` VALUES ('1398', '重庆', '梁平县', '30.6519', '107.7429');
INSERT INTO `s_geo_coord` VALUES ('1399', '重庆', '巴南区', '29.4214', '106.7322');
INSERT INTO `s_geo_coord` VALUES ('1400', '重庆', '潼南县', '30.1135', '105.7764');
INSERT INTO `s_geo_coord` VALUES ('1401', '重庆', '永川区', '29.2566', '105.8643');
INSERT INTO `s_geo_coord` VALUES ('1402', '重庆', '垫江县', '30.2454', '107.4573');
INSERT INTO `s_geo_coord` VALUES ('1403', '重庆', '渝北区', '29.8499', '106.7212');
INSERT INTO `s_geo_coord` VALUES ('1404', '重庆', '长寿区', '29.9762', '107.1606');
INSERT INTO `s_geo_coord` VALUES ('1405', '重庆', '大足县', '29.6136', '105.7544');
INSERT INTO `s_geo_coord` VALUES ('1406', '重庆', '铜梁县', '29.8059', '106.0291');
INSERT INTO `s_geo_coord` VALUES ('1407', '重庆', '荣昌县', '29.4708', '105.5127');
INSERT INTO `s_geo_coord` VALUES ('1408', '重庆', '璧山县', '29.5807', '106.2048');
INSERT INTO `s_geo_coord` VALUES ('1409', '重庆', '北碚区', '29.8883', '106.5674');
INSERT INTO `s_geo_coord` VALUES ('1410', '重庆', '万盛区', '28.9325', '106.908');
INSERT INTO `s_geo_coord` VALUES ('1411', '重庆', '九龙坡区', '29.4049', '106.3586');
INSERT INTO `s_geo_coord` VALUES ('1412', '重庆', '沙坪坝区', '29.6191', '106.3696');
INSERT INTO `s_geo_coord` VALUES ('1413', '重庆', '南岸区', '29.5367', '106.6663');
INSERT INTO `s_geo_coord` VALUES ('1414', '重庆', '江北区', '29.6191', '106.8311');
INSERT INTO `s_geo_coord` VALUES ('1415', '重庆', '大渡口区', '29.4214', '106.4905');
INSERT INTO `s_geo_coord` VALUES ('1416', '重庆', '双桥区', '29.4928', '105.7874');
INSERT INTO `s_geo_coord` VALUES ('1417', '重庆', '渝中区', '29.5477', '106.5344');
INSERT INTO `s_geo_coord` VALUES ('1418', '天津', '蓟县', '40.004', '117.4672');
INSERT INTO `s_geo_coord` VALUES ('1419', '天津', '武清区', '39.4121', '117.0621');
INSERT INTO `s_geo_coord` VALUES ('1420', '天津', '宝坻区', '39.5913', '117.4274');
INSERT INTO `s_geo_coord` VALUES ('1421', '天津', '静海县', '38.8312', '116.9824');
INSERT INTO `s_geo_coord` VALUES ('1422', '天津', '宁河县', '39.3853', '117.6801');
INSERT INTO `s_geo_coord` VALUES ('1423', '天津', '大港区', '38.757', '117.3875');
INSERT INTO `s_geo_coord` VALUES ('1424', '天津', '塘沽区', '38.9987', '117.6801');
INSERT INTO `s_geo_coord` VALUES ('1425', '天津', '西青区', '39.0022', '117.1829');
INSERT INTO `s_geo_coord` VALUES ('1426', '天津', '北辰区', '39.2548', '117.1761');
INSERT INTO `s_geo_coord` VALUES ('1427', '天津', '东丽区', '39.1223', '117.4013');
INSERT INTO `s_geo_coord` VALUES ('1428', '天津', '汉沽区', '39.2191', '117.8888');
INSERT INTO `s_geo_coord` VALUES ('1429', '天津', '津南区', '38.9603', '117.3958');
INSERT INTO `s_geo_coord` VALUES ('1430', '天津', '河西区', '39.0804', '117.2365');
INSERT INTO `s_geo_coord` VALUES ('1431', '天津', '河东区', '39.1209', '117.2571');
INSERT INTO `s_geo_coord` VALUES ('1432', '天津', '南开区', '39.1065', '117.1527');
INSERT INTO `s_geo_coord` VALUES ('1433', '天津', '河北区', '39.1615', '117.2145');
INSERT INTO `s_geo_coord` VALUES ('1434', '天津', '红桥区', '39.1663', '117.1596');
INSERT INTO `s_geo_coord` VALUES ('1435', '天津', '和平区', '39.1189', '117.2008');
INSERT INTO `s_geo_coord` VALUES ('1436', '台湾', '台湾', '23.6082', '121.0295');
INSERT INTO `s_geo_coord` VALUES ('1437', '四川', '甘孜藏族自治州', '31.0803', '99.9207');
INSERT INTO `s_geo_coord` VALUES ('1438', '四川', '阿坝藏族羌族自治州', '32.4536', '102.4805');
INSERT INTO `s_geo_coord` VALUES ('1439', '四川', '凉山彝族自治州', '27.6746', '101.9641');
INSERT INTO `s_geo_coord` VALUES ('1440', '四川', '绵阳', '31.8713', '104.7327');
INSERT INTO `s_geo_coord` VALUES ('1441', '四川', '达州', '31.333', '107.6111');
INSERT INTO `s_geo_coord` VALUES ('1442', '四川', '广元', '32.2284', '105.6885');
INSERT INTO `s_geo_coord` VALUES ('1443', '四川', '雅安', '29.8938', '102.6672');
INSERT INTO `s_geo_coord` VALUES ('1444', '四川', '宜宾', '28.548', '104.6558');
INSERT INTO `s_geo_coord` VALUES ('1445', '四川', '乐山', '29.1742', '103.5791');
INSERT INTO `s_geo_coord` VALUES ('1446', '四川', '南充', '31.1517', '106.2048');
INSERT INTO `s_geo_coord` VALUES ('1447', '四川', '巴中', '31.9977', '107.0618');
INSERT INTO `s_geo_coord` VALUES ('1448', '四川', '泸州', '28.493', '105.4578');
INSERT INTO `s_geo_coord` VALUES ('1449', '四川', '成都', '30.7617', '103.9526');
INSERT INTO `s_geo_coord` VALUES ('1450', '四川', '资阳', '30.1575', '104.9744');
INSERT INTO `s_geo_coord` VALUES ('1451', '四川', '攀枝花', '26.7133', '101.6895');
INSERT INTO `s_geo_coord` VALUES ('1452', '四川', '眉山', '30.0146', '103.8098');
INSERT INTO `s_geo_coord` VALUES ('1453', '四川', '广安', '30.4376', '106.6333');
INSERT INTO `s_geo_coord` VALUES ('1454', '四川', '德阳', '31.1133', '104.48');
INSERT INTO `s_geo_coord` VALUES ('1455', '四川', '内江', '29.6136', '104.8535');
INSERT INTO `s_geo_coord` VALUES ('1456', '四川', '遂宁', '30.6683', '105.5347');
INSERT INTO `s_geo_coord` VALUES ('1457', '四川', '自贡', '29.2786', '104.6667');
INSERT INTO `s_geo_coord` VALUES ('1458', '上海', '崇明县', '31.5383', '121.5637');
INSERT INTO `s_geo_coord` VALUES ('1459', '上海', '南汇区', '30.954', '121.8755');
INSERT INTO `s_geo_coord` VALUES ('1460', '上海', '奉贤区', '30.8475', '121.5747');
INSERT INTO `s_geo_coord` VALUES ('1461', '上海', '浦东新区', '31.2561', '121.6928');
INSERT INTO `s_geo_coord` VALUES ('1462', '上海', '金山区', '30.8112', '121.2657');
INSERT INTO `s_geo_coord` VALUES ('1463', '上海', '青浦区', '31.1909', '121.1751');
INSERT INTO `s_geo_coord` VALUES ('1464', '上海', '松江区', '31.0268', '121.1984');
INSERT INTO `s_geo_coord` VALUES ('1465', '上海', '嘉定区', '31.3625', '121.2437');
INSERT INTO `s_geo_coord` VALUES ('1466', '上海', '宝山区', '31.4051', '121.4346');
INSERT INTO `s_geo_coord` VALUES ('1467', '上海', '闵行区', '31.0838', '121.4992');
INSERT INTO `s_geo_coord` VALUES ('1468', '上海', '杨浦区', '31.2966', '121.528');
INSERT INTO `s_geo_coord` VALUES ('1469', '上海', '普陀区', '31.2602', '121.3879');
INSERT INTO `s_geo_coord` VALUES ('1470', '上海', '徐汇区', '31.1607', '121.4333');
INSERT INTO `s_geo_coord` VALUES ('1471', '上海', '长宁区', '31.2115', '121.3852');
INSERT INTO `s_geo_coord` VALUES ('1472', '上海', '闸北区', '31.2794', '121.4511');
INSERT INTO `s_geo_coord` VALUES ('1473', '上海', '虹口区', '31.2788', '121.4882');
INSERT INTO `s_geo_coord` VALUES ('1474', '上海', '黄浦区', '31.219', '121.4868');
INSERT INTO `s_geo_coord` VALUES ('1475', '上海', '卢湾区', '31.2074', '121.4758');
INSERT INTO `s_geo_coord` VALUES ('1476', '上海', '静安区', '31.2286', '121.4484');
INSERT INTO `s_geo_coord` VALUES ('1477', '西藏', '那曲地区', '33.3215', '88.1982');
INSERT INTO `s_geo_coord` VALUES ('1478', '西藏', '阿里地区', '32.7667', '82.3645');
INSERT INTO `s_geo_coord` VALUES ('1479', '西藏', '日喀则地区', '29.5093', '86.2427');
INSERT INTO `s_geo_coord` VALUES ('1480', '西藏', '林芝地区', '29.1138', '95.4602');
INSERT INTO `s_geo_coord` VALUES ('1481', '西藏', '昌都地区', '30.7068', '97.0203');
INSERT INTO `s_geo_coord` VALUES ('1482', '西藏', '山南地区', '28.3392', '92.2083');
INSERT INTO `s_geo_coord` VALUES ('1483', '西藏', '拉萨', '30.1465', '91.1865');
INSERT INTO `s_geo_coord` VALUES ('1484', '香港', '香港', '22.3057', '114.2784');
INSERT INTO `s_geo_coord` VALUES ('1485', '云南', '普洱', '23.4229', '100.7446');
INSERT INTO `s_geo_coord` VALUES ('1486', '云南', '红河哈尼族彝族自治州', '23.6041', '103.0408');
INSERT INTO `s_geo_coord` VALUES ('1487', '云南', '文山壮族苗族自治州', '23.5712', '104.8865');
INSERT INTO `s_geo_coord` VALUES ('1488', '云南', '曲靖', '25.7025', '103.9417');
INSERT INTO `s_geo_coord` VALUES ('1489', '云南', '楚雄彝族自治州', '25.3619', '101.6016');
INSERT INTO `s_geo_coord` VALUES ('1490', '云南', '大理白族自治州', '25.6805', '99.9536');
INSERT INTO `s_geo_coord` VALUES ('1491', '云南', '临沧', '24.0546', '99.613');
INSERT INTO `s_geo_coord` VALUES ('1492', '云南', '迪庆藏族自治州', '27.9327', '99.4592');
INSERT INTO `s_geo_coord` VALUES ('1493', '云南', '昭通', '27.6031', '104.0955');
INSERT INTO `s_geo_coord` VALUES ('1494', '云南', '昆明', '25.4663', '102.9199');
INSERT INTO `s_geo_coord` VALUES ('1495', '云南', '丽江', '26.955', '100.448');
INSERT INTO `s_geo_coord` VALUES ('1496', '云南', '西双版纳傣族自治州', '21.8628', '100.8984');
INSERT INTO `s_geo_coord` VALUES ('1497', '云南', '保山', '24.9884', '99.0637');
INSERT INTO `s_geo_coord` VALUES ('1498', '云南', '玉溪', '23.8898', '101.9312');
INSERT INTO `s_geo_coord` VALUES ('1499', '云南', '怒江傈僳族自治州', '26.5594', '99.1516');
INSERT INTO `s_geo_coord` VALUES ('1500', '云南', '德宏傣族景颇族自治州', '24.5874', '98.1299');
INSERT INTO `s_geo_coord` VALUES ('1501', '新疆', '巴音郭楞蒙古自治州', '39.6002', '88.1653');
INSERT INTO `s_geo_coord` VALUES ('1502', '新疆', '和田地区', '36.9855', '81.167');
INSERT INTO `s_geo_coord` VALUES ('1503', '新疆', '哈密地区', '42.9236', '93.7793');
INSERT INTO `s_geo_coord` VALUES ('1504', '新疆', '阿克苏地区', '41.0229', '82.9797');
INSERT INTO `s_geo_coord` VALUES ('1505', '新疆', '阿勒泰地区', '47.0929', '88.2971');
INSERT INTO `s_geo_coord` VALUES ('1506', '新疆', '喀什地区', '37.8534', '77.168');
INSERT INTO `s_geo_coord` VALUES ('1507', '新疆', '塔城地区', '45.8514', '86.6272');
INSERT INTO `s_geo_coord` VALUES ('1508', '新疆', '昌吉回族自治州', '44.4507', '89.6814');
INSERT INTO `s_geo_coord` VALUES ('1509', '新疆', '克孜勒苏柯尔克孜自治州', '39.5233', '74.6301');
INSERT INTO `s_geo_coord` VALUES ('1510', '新疆', '吐鲁番地区', '42.4127', '89.6375');
INSERT INTO `s_geo_coord` VALUES ('1511', '新疆', '伊犁哈萨克自治州', '43.5498', '82.5513');
INSERT INTO `s_geo_coord` VALUES ('1512', '新疆', '博尔塔拉蒙古自治州', '44.6979', '81.8481');
INSERT INTO `s_geo_coord` VALUES ('1513', '新疆', '乌鲁木齐', '43.5883', '87.9236');
INSERT INTO `s_geo_coord` VALUES ('1514', '新疆', '克拉玛依', '45.5054', '85.2869');
INSERT INTO `s_geo_coord` VALUES ('1515', '新疆', '阿拉尔', '40.6549', '81.2769');
INSERT INTO `s_geo_coord` VALUES ('1516', '新疆', '图木舒克', '39.8749', '79.1345');
INSERT INTO `s_geo_coord` VALUES ('1517', '新疆', '五家渠', '44.3024', '87.5391');
INSERT INTO `s_geo_coord` VALUES ('1518', '新疆', '石河子', '44.2914', '86.0229');
INSERT INTO `s_geo_coord` VALUES ('1519', '浙江', '丽水', '28.1854', '119.5642');
INSERT INTO `s_geo_coord` VALUES ('1520', '浙江', '杭州', '29.8773', '119.5313');
INSERT INTO `s_geo_coord` VALUES ('1521', '浙江', '温州', '27.8119', '120.498');
INSERT INTO `s_geo_coord` VALUES ('1522', '浙江', '宁波', '29.6466', '121.5967');
INSERT INTO `s_geo_coord` VALUES ('1523', '浙江', '舟山', '30.2234', '122.2559');
INSERT INTO `s_geo_coord` VALUES ('1524', '浙江', '台州', '28.6688', '121.1353');
INSERT INTO `s_geo_coord` VALUES ('1525', '浙江', '金华', '29.1028', '120.0037');
INSERT INTO `s_geo_coord` VALUES ('1526', '浙江', '衢州', '28.8666', '118.6853');
INSERT INTO `s_geo_coord` VALUES ('1527', '浙江', '绍兴', '29.7565', '120.564');
INSERT INTO `s_geo_coord` VALUES ('1528', '浙江', '嘉兴', '30.6354', '120.9155');
INSERT INTO `s_geo_coord` VALUES ('1529', '浙江', '湖州', '30.7782', '119.8608');
INSERT INTO `s_geo_coord` VALUES ('1530', '安徽', '六安', '31.8329', '116.3123');
INSERT INTO `s_geo_coord` VALUES ('1531', '安徽', '安庆', '30.5255', '116.7517');
INSERT INTO `s_geo_coord` VALUES ('1532', '安徽', '滁州', '32.536', '118.1909');
INSERT INTO `s_geo_coord` VALUES ('1533', '安徽', '宣城', '30.6244', '118.8062');
INSERT INTO `s_geo_coord` VALUES ('1534', '安徽', '阜阳', '32.9919', '115.7629');
INSERT INTO `s_geo_coord` VALUES ('1535', '安徽', '宿州', '33.6841', '117.5208');
INSERT INTO `s_geo_coord` VALUES ('1536', '安徽', '黄山', '29.9542', '118.0481');
INSERT INTO `s_geo_coord` VALUES ('1537', '安徽', '巢湖', '31.4978', '117.7734');
INSERT INTO `s_geo_coord` VALUES ('1538', '安徽', '亳州', '33.4698', '116.1914');
INSERT INTO `s_geo_coord` VALUES ('1539', '安徽', '池州', '30.2014', '117.3889');
INSERT INTO `s_geo_coord` VALUES ('1540', '安徽', '合肥', '32.0581', '117.29');
INSERT INTO `s_geo_coord` VALUES ('1541', '安徽', '蚌埠', '33.1073', '117.4109');
INSERT INTO `s_geo_coord` VALUES ('1542', '安徽', '芜湖', '31.0858', '118.3557');
INSERT INTO `s_geo_coord` VALUES ('1543', '安徽', '淮北', '33.6896', '116.6968');
INSERT INTO `s_geo_coord` VALUES ('1544', '安徽', '淮南', '32.7722', '116.7847');
INSERT INTO `s_geo_coord` VALUES ('1545', '安徽', '马鞍山', '31.5363', '118.6304');
INSERT INTO `s_geo_coord` VALUES ('1546', '安徽', '铜陵', '30.9375', '117.9382');
INSERT INTO `s_geo_coord` VALUES ('1547', '澳门', '澳门', '22.1583', '113.5715');
INSERT INTO `s_geo_coord` VALUES ('1548', '北京', '密云县', '40.5121', '117.0923');
INSERT INTO `s_geo_coord` VALUES ('1549', '北京', '怀柔区', '40.6219', '116.6377');
INSERT INTO `s_geo_coord` VALUES ('1550', '北京', '房山区', '39.7163', '115.8453');
INSERT INTO `s_geo_coord` VALUES ('1551', '北京', '延庆县', '40.5286', '116.1543');
INSERT INTO `s_geo_coord` VALUES ('1552', '北京', '门头沟区', '39.9957', '115.8');
INSERT INTO `s_geo_coord` VALUES ('1553', '北京', '昌平区', '40.2134', '116.1777');
INSERT INTO `s_geo_coord` VALUES ('1554', '北京', '大兴区', '39.6352', '116.4716');
INSERT INTO `s_geo_coord` VALUES ('1555', '北京', '顺义区', '40.1619', '116.7242');
INSERT INTO `s_geo_coord` VALUES ('1556', '北京', '平谷区', '40.2052', '117.1706');
INSERT INTO `s_geo_coord` VALUES ('1557', '北京', '通州区', '39.8131', '116.7297');
INSERT INTO `s_geo_coord` VALUES ('1558', '北京', '朝阳区', '39.949', '116.4977');
INSERT INTO `s_geo_coord` VALUES ('1559', '北京', '海淀区', '40.0239', '116.2202');
INSERT INTO `s_geo_coord` VALUES ('1560', '北京', '丰台区', '39.8309', '116.2683');
INSERT INTO `s_geo_coord` VALUES ('1561', '北京', '石景山区', '39.9346', '116.1887');
INSERT INTO `s_geo_coord` VALUES ('1562', '北京', '西城区', '39.9353', '116.3631');
INSERT INTO `s_geo_coord` VALUES ('1563', '北京', '东城区', '39.9367', '116.418');
INSERT INTO `s_geo_coord` VALUES ('1564', '北京', '宣武区', '39.8852', '116.3603');
INSERT INTO `s_geo_coord` VALUES ('1565', '北京', '崇文区', '39.8811', '116.4166');
INSERT INTO `s_geo_coord` VALUES ('1566', '新疆', 'NULL', '41.748', '84.9023');
INSERT INTO `s_geo_coord` VALUES ('1567', '西藏', 'NULL', '31.6846', '88.7695');
INSERT INTO `s_geo_coord` VALUES ('1568', '内蒙古', 'NULL', '44.3408', '117.5977');
INSERT INTO `s_geo_coord` VALUES ('1569', '青海', 'NULL', '35.4199', '96.2402');
INSERT INTO `s_geo_coord` VALUES ('1570', '四川', 'NULL', '30.1904', '102.9199');
INSERT INTO `s_geo_coord` VALUES ('1571', '黑龙江', 'NULL', '48.5156', '128.1445');
INSERT INTO `s_geo_coord` VALUES ('1572', '甘肃', 'NULL', '40.166', '95.7129');
INSERT INTO `s_geo_coord` VALUES ('1573', '云南', 'NULL', '25.1807', '101.8652');
INSERT INTO `s_geo_coord` VALUES ('1574', '广西', 'NULL', '23.6426', '108.2813');
INSERT INTO `s_geo_coord` VALUES ('1575', '湖南', 'NULL', '27.3779', '111.5332');
INSERT INTO `s_geo_coord` VALUES ('1576', '陕西', 'NULL', '35.6396', '109.5996');
INSERT INTO `s_geo_coord` VALUES ('1577', '广东', 'NULL', '22.8076', '113.4668');
INSERT INTO `s_geo_coord` VALUES ('1578', '吉林', 'NULL', '43.5938', '126.4746');
INSERT INTO `s_geo_coord` VALUES ('1579', '河北', 'NULL', '37.9688', '115.4004');
INSERT INTO `s_geo_coord` VALUES ('1580', '湖北', 'NULL', '31.1572', '112.2363');
INSERT INTO `s_geo_coord` VALUES ('1581', '贵州', 'NULL', '26.9385', '106.6113');
INSERT INTO `s_geo_coord` VALUES ('1582', '山东', 'NULL', '36.4307', '118.7402');
INSERT INTO `s_geo_coord` VALUES ('1583', '江西', 'NULL', '27.29', '116.0156');
INSERT INTO `s_geo_coord` VALUES ('1584', '河南', 'NULL', '33.8818', '113.4668');
INSERT INTO `s_geo_coord` VALUES ('1585', '辽宁', 'NULL', '41.0889', '122.3438');
INSERT INTO `s_geo_coord` VALUES ('1586', '山西', 'NULL', '37.6611', '112.4121');
INSERT INTO `s_geo_coord` VALUES ('1587', '安徽', 'NULL', '32.0361', '117.2461');
INSERT INTO `s_geo_coord` VALUES ('1588', '福建', 'NULL', '25.9277', '118.3008');
INSERT INTO `s_geo_coord` VALUES ('1589', '浙江', 'NULL', '29.0918', '120.498');
INSERT INTO `s_geo_coord` VALUES ('1590', '江苏', 'NULL', '32.915', '120.0586');
INSERT INTO `s_geo_coord` VALUES ('1591', '重庆', 'NULL', '30.1904', '107.7539');
INSERT INTO `s_geo_coord` VALUES ('1592', '宁夏', 'NULL', '37.3096', '105.9961');
INSERT INTO `s_geo_coord` VALUES ('1593', '海南', 'NULL', '19.2041', '109.9512');
INSERT INTO `s_geo_coord` VALUES ('1594', '台湾', 'NULL', '23.5986', '121.0254');
INSERT INTO `s_geo_coord` VALUES ('1595', '北京', 'NULL', '40.2539', '116.4551');
INSERT INTO `s_geo_coord` VALUES ('1596', '天津', 'NULL', '39.4189', '117.4219');
INSERT INTO `s_geo_coord` VALUES ('1597', '上海', 'NULL', '31.2891', '121.4648');
INSERT INTO `s_geo_coord` VALUES ('1598', '香港', 'NULL', '22.3242', '114.2578');
INSERT INTO `s_geo_coord` VALUES ('1599', '澳门', 'NULL', '22.1484', '113.5547');

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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', '/ppsg/config/star/list', '', null, '0', null, '0');
INSERT INTO `s_permission` VALUES ('2', '/ppsg/config/country/list', '', null, '0', null, '0');
INSERT INTO `s_permission` VALUES ('3', '/ppsg/config/arms/list', '', null, '0', null, '0');
INSERT INTO `s_permission` VALUES ('4', '/ppsg/config/generalsType/list', '', null, '0', null, '0');

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
