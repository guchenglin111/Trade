/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : trade

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2016-06-01 22:48:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for circulate_info
-- ----------------------------
DROP TABLE IF EXISTS `circulate_info`;
CREATE TABLE `circulate_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods` int(11) DEFAULT NULL,
  `circulate_status` varchar(50) DEFAULT NULL COMMENT '商品状态',
  `owner` int(11) DEFAULT NULL,
  `lender` int(11) DEFAULT NULL,
  `lend_time` datetime DEFAULT NULL,
  `back_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `circulate_goods_fk` (`goods`),
  KEY `circulate_owner_fk` (`owner`),
  KEY `criculate_lender_fk` (`lender`),
  CONSTRAINT `circulate_goods_fk` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `circulate_owner_fk` FOREIGN KEY (`owner`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `criculate_lender_fk` FOREIGN KEY (`lender`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of circulate_info
-- ----------------------------
INSERT INTO `circulate_info` VALUES ('13', '30', '0', '8', '7', '2016-06-01 22:12:24', null);
INSERT INTO `circulate_info` VALUES ('14', '23', '0', '10', '7', '2016-06-01 22:33:34', null);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collect_time` datetime DEFAULT NULL,
  `goods` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `collect_goods_fk` (`goods`),
  KEY `collect_user_fk` (`user`),
  CONSTRAINT `collect_goods_fk` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collect_user_fk` FOREIGN KEY (`user`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('9', '2016-06-01 22:11:10', '37', '7');
INSERT INTO `collect` VALUES ('10', '2016-06-01 22:33:32', '23', '7');

-- ----------------------------
-- Table structure for focus
-- ----------------------------
DROP TABLE IF EXISTS `focus`;
CREATE TABLE `focus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `this_id` int(11) DEFAULT NULL,
  `friend_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `focus_friendid_fk` (`friend_id`),
  KEY `focus_thisid_fk` (`this_id`),
  CONSTRAINT `focus_thisid_fk` FOREIGN KEY (`this_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE SET NULL,
  CONSTRAINT `focus_friendid_fk` FOREIGN KEY (`friend_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of focus
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `lever` varchar(50) DEFAULT NULL COMMENT '商品级别',
  `type` int(11) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  `region` varchar(100) DEFAULT NULL,
  `original_cost` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `current_price` decimal(10,2) DEFAULT NULL COMMENT '押金价',
  `trade_cost` decimal(10,2) DEFAULT NULL COMMENT '借出价',
  `price_lever` int(11) DEFAULT NULL COMMENT '物品为金钱时的金钱等级',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `out_count` int(50) DEFAULT NULL COMMENT '借出次数',
  `collect_count` int(50) DEFAULT NULL COMMENT '收藏次数',
  `search_count` int(50) DEFAULT NULL COMMENT '搜索次数',
  `detail` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_type_fk` (`type`),
  KEY `goods_owner_fk` (`owner`),
  KEY `goods_region_fk` (`region`),
  CONSTRAINT `goods_owner_fk` FOREIGN KEY (`owner`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goods_type_fk` FOREIGN KEY (`type`) REFERENCES `goods_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('20', 'Google Glass', '九成新', '1', '7', '北京市石景山区苹果园大街海特花园', '8000.00', '4000.00', '100.00', null, '1', '0', '0', '0', 'Google Glass 2.0/智能谷歌眼镜3代 全套原装谷歌眼镜 智能拍照眼镜 页岩灰 二代', 'pic/Gg1.jpeg');
INSERT INTO `goods` VALUES ('21', '联想（ThinkPad）X250', '九成新', '1', '8', '北京市海淀区', '6000.00', '3000.00', '70.00', null, '1', '0', '0', '0', '\n联想（ThinkPad）X250便携轻薄系列12.5英寸笔记本电脑定制商务手提IBM超薄本 黑色铭记 55CDi3-5010U/4G/500G', 'pic/tkp3.jpg');
INSERT INTO `goods` VALUES ('22', '三星 Galaxy S6', '七成新', '1', '9', '北京市朝阳区', '5000.00', '1000.00', '50.00', null, '1', '0', '0', '0', '三星 Galaxy S6 Edge+（G9280）32G版 铂光金 全网通4G手机', 'pic/s63.jpg');
INSERT INTO `goods` VALUES ('23', 'Apple Watch Sport 智能手表', '七成新', '1', '10', '北京市东城区', '2688.00', '500.00', '50.00', null, '1', '1', '1', '0', 'Apple Watch Sport 智能手表(42毫米深空灰色铝金属表壳搭配黑色运动型表带 MJ3T2CH/A）', 'pic/iwh1.jpg');
INSERT INTO `goods` VALUES ('24', '山地自行车', '八成新', '4', '10', '北京市东城区', '500.00', '100.00', '20.00', null, '1', '0', '0', '0', '捷豹 山地自行车21速26寸双碟刹变速山地车 男女学生公路单车减震前叉 21速高碳钢-标-白蓝', 'pic/sdzxc2.jpg');
INSERT INTO `goods` VALUES ('25', '健腹轮', '八成新', '2', '7', '北京市石景山区苹果园大街海特花园', '50.00', '20.00', '5.00', null, '0', '0', '0', '0', ' 迪卡侬 健腹轮 家用锻炼腹肌轮健身器材滚轮俯卧撑轮 DOMYOS QS\n双飞轮设计 超静音顺滑 加厚海绵垫 拆装方便', 'pic/jfl0.jpg');
INSERT INTO `goods` VALUES ('26', '料理机', '八成新', '3', '7', '北京市石景山区', '300.00', '120.00', '10.00', null, '0', '0', '0', '0', 'Joyoung/九阳 JYZ-D57榨汁机 家用电动水果汁机多功能豆浆料理机\n轴心粉碎技术 分离式设计 软硬兼榨', 'pic/llj0.jpg');
INSERT INTO `goods` VALUES ('27', '独轮车', '七成新', '4', '7', '北京市石景山区苹果园大街海特花园', '1500.00', '700.00', '20.00', null, '0', '0', '0', '0', '1500 Ninebot One A1九号单轮平衡车电动独轮车单轮代步车体感车纳恩博\n长续航 迷你款 轻薄型 创意DIY 双电设计', 'pic/dlc2.jpg');
INSERT INTO `goods` VALUES ('28', '按摩椅', '七成新', '5', '7', '北京市石景山区海特花园', '4000.00', '2000.00', '100.00', null, '0', '0', '0', '0', '4000 SminG/尚铭电器尚铭按摩椅家用全身多功能豪华太空舱按摩器SM-700\n全新升级 足底滚轮刮痧 U形头部气囊', 'pic/amy0.jpg');
INSERT INTO `goods` VALUES ('29', '台灯', '九成新', '2', '8', '北京市海淀区', '45.00', '20.00', '3.00', null, '0', '0', '0', '0', '亮朵 LED台灯护眼学习学生书桌卧室床头宿舍节能灯夹式儿童小台灯', 'pic/td2.jpg');
INSERT INTO `goods` VALUES ('30', '榨汁机', '六成新', '3', '8', '北京市海淀区', '168.00', '90.00', '10.00', null, '1', '1', '0', '0', '168  Fronton/弗朗顿 HTZ-1011家用电动迷你榨汁机便携果汁机原汁机\n果汁鲜榨 红点师计 方便携带 超高转速 多功能', 'pic/zzj0.jpg');
INSERT INTO `goods` VALUES ('31', '轮滑鞋', '七成新', '4', '8', '北京市海淀区', '300.00', '150.00', '50.00', null, '1', '0', '0', '0', '贵派仕 溜冰鞋成人成年旱冰鞋滑冰儿童全套装直排轮滑鞋男女可调\n舒适气垫 超厚支架 200kg 承重', 'pic/lhx1.jpg');
INSERT INTO `goods` VALUES ('32', '折叠床', '六成新', '5', '8', '北京市海淀区', '500.00', '200.00', '20.00', null, '1', '0', '0', '0', '亿家达加固折叠床单人午睡床双人床办公室午休床简易儿童床折叠床\n有扶手款送 毛毯 多种尺寸 可供选择 折叠方便 ', 'pic/zdc0.jpg');
INSERT INTO `goods` VALUES ('33', ' 呼啦圈', '七成新', '2', '9', '北京市朝阳区', '25.00', '10.00', '3.00', null, '1', '0', '0', '0', '沃尔克升级可拆卸泡棉呼啦圈成人健身瘦腰海绵泡棉瘦腰身收腹健身\n沃尔克专供 全民健身 瘦身利器 居家瘦身神器', 'pic/hlq.jpg');
INSERT INTO `goods` VALUES ('34', ' 削皮器', '八成新', '3', '9', '北京市朝阳区', '25.00', '10.00', '2.00', null, '1', '0', '0', '0', '合庆手动苹果削皮器削皮刀去皮机配套多功能自动水果分割器水果刀\n削皮分切 一次搞定 八份分切器 质量升级', 'pic/xpq1.jpg');
INSERT INTO `goods` VALUES ('35', '蛇板', '九成新', '4', '9', '北京市朝阳区', '200.00', '100.00', '20.00', null, '1', '0', '0', '0', '鑫奥林改良游龙板二轮活力板儿童两轮闪光轮滑板蛇板高承重\n底板加厚 加强底板 铝合金轮座 高弹闪光轮', 'pic/sb0.jpg');
INSERT INTO `goods` VALUES ('36', '电脑桌 ', '八成新', '5', '9', '北京市朝阳区', '150.00', '80.00', '10.00', null, '1', '0', '0', '0', '电脑桌简约现代台式家用书桌书架组合书柜办公桌简易桌子学习桌\n\n', 'pic/dnz0.jpg');
INSERT INTO `goods` VALUES ('37', ' 跳绳', '七成新', '2', '10', '北京市东城区', '20.00', '10.00', '2.00', null, '1', '0', '1', '0', '奥韵专业跳绳包邮成人健身器材减肥运动体育男女中考跳绳绳子\n健身减肥 专业轴承跳绳 中考跳绳 单摇双摇\n', 'pic/ts2.jpg');
INSERT INTO `goods` VALUES ('38', '刀具组合', '完好无损', '3', '10', '北京市东城区', '400.00', '200.00', '50.00', null, '1', '0', '0', '0', '阳江十八子作厨房套刀刀具 菜刀套装组合七件套 钼钒钢不锈钢\n五铬钼钒钢 锋利更 耐用 铸钢手柄 不断柄', 'pic/djzh0.jpg');
INSERT INTO `goods` VALUES ('39', ' 折叠柜', '六成新', '5', '10', '北京市东城区', '55.00', '20.00', '3.00', null, '1', '0', '0', '0', '麦田衣柜简易组装树脂衣橱折叠组合式收纳柜塑料布艺儿童储物衣柜', 'pic/zdg0.jpg');

-- ----------------------------
-- Table structure for goods_check
-- ----------------------------
DROP TABLE IF EXISTS `goods_check`;
CREATE TABLE `goods_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `result` int(2) DEFAULT NULL COMMENT '审核结果',
  `goods` int(11) DEFAULT NULL COMMENT '被审核商品',
  `time` datetime DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL COMMENT '未通过原因',
  PRIMARY KEY (`id`),
  KEY `check_goods_fk` (`goods`),
  CONSTRAINT `check_goods_fk` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_check
-- ----------------------------
INSERT INTO `goods_check` VALUES ('3', null, '20', null, null);
INSERT INTO `goods_check` VALUES ('4', null, '39', '2016-06-01 22:02:22', null);
INSERT INTO `goods_check` VALUES ('5', null, '38', '2016-06-01 22:02:23', null);
INSERT INTO `goods_check` VALUES ('6', null, '37', '2016-06-01 22:02:25', null);
INSERT INTO `goods_check` VALUES ('7', null, '36', '2016-06-01 22:02:25', null);
INSERT INTO `goods_check` VALUES ('8', null, '35', '2016-06-01 22:02:26', null);
INSERT INTO `goods_check` VALUES ('9', null, '34', '2016-06-01 22:02:29', null);
INSERT INTO `goods_check` VALUES ('10', null, '33', '2016-06-01 22:02:29', null);
INSERT INTO `goods_check` VALUES ('11', null, '32', '2016-06-01 22:02:30', null);
INSERT INTO `goods_check` VALUES ('12', null, '31', '2016-06-01 22:02:31', null);
INSERT INTO `goods_check` VALUES ('13', null, '30', '2016-06-01 22:02:31', null);
INSERT INTO `goods_check` VALUES ('14', null, '24', '2016-06-01 22:02:57', null);
INSERT INTO `goods_check` VALUES ('15', null, '23', '2016-06-01 22:02:58', null);
INSERT INTO `goods_check` VALUES ('16', null, '22', '2016-06-01 22:02:59', null);
INSERT INTO `goods_check` VALUES ('17', null, '21', '2016-06-01 22:02:59', null);

-- ----------------------------
-- Table structure for goods_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `goods_evaluate`;
CREATE TABLE `goods_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` int(11) DEFAULT NULL COMMENT '评价人',
  `goods` int(11) DEFAULT NULL COMMENT '被评价商品',
  `lever` int(11) DEFAULT NULL COMMENT '评价级别',
  `evaluate_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `evaluate_goods_fk` (`goods`),
  KEY `evaluate_user_fk` (`owner`),
  CONSTRAINT `evaluate_goods_fk` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `evaluate_user_fk` FOREIGN KEY (`owner`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_evaluate
-- ----------------------------

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES ('1', '电子设备');
INSERT INTO `goods_type` VALUES ('2', '生活达人');
INSERT INTO `goods_type` VALUES ('3', '吃货合集');
INSERT INTO `goods_type` VALUES ('4', '出行设备');
INSERT INTO `goods_type` VALUES ('5', '家居家纺');
INSERT INTO `goods_type` VALUES ('6', '资金');

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods` int(11) DEFAULT NULL,
  `print` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `good_picture_fk` (`goods`),
  CONSTRAINT `good_picture_fk` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES ('8', '20', 'pic/Gg2.jpg');
INSERT INTO `picture` VALUES ('9', '20', 'pic/Gg3.jpg');
INSERT INTO `picture` VALUES ('10', '20', 'pic/Gg4.jpg');
INSERT INTO `picture` VALUES ('11', '21', 'pic/tkp1.jpg');
INSERT INTO `picture` VALUES ('12', '21', 'pic/tkp2.jpg');
INSERT INTO `picture` VALUES ('13', '22', 'pic/s61.jpg');
INSERT INTO `picture` VALUES ('14', '22', 'pic/s62.jpg');
INSERT INTO `picture` VALUES ('15', '23', 'pic/iwh2.jpg');
INSERT INTO `picture` VALUES ('16', '23', 'pic/iwh3.jpg');
INSERT INTO `picture` VALUES ('17', '24', 'pic/sdzxc3.jpg');
INSERT INTO `picture` VALUES ('18', '24', 'pic/sdzxc1.jpg');
INSERT INTO `picture` VALUES ('19', '25', 'pic/jfl1.jpg');
INSERT INTO `picture` VALUES ('20', '25', 'pic/jfl2.jpg');
INSERT INTO `picture` VALUES ('21', '26', 'pic/llj1.jpg');
INSERT INTO `picture` VALUES ('22', '26', 'pic/llj2.jpg');
INSERT INTO `picture` VALUES ('23', '27', 'pic/dlc0.jpg');
INSERT INTO `picture` VALUES ('24', '27', 'pic/dlc1.jpg');
INSERT INTO `picture` VALUES ('25', '28', 'pic/amy1.jpg');
INSERT INTO `picture` VALUES ('26', '28', 'pic/amy2.jpg');
INSERT INTO `picture` VALUES ('27', '29', 'pic/td0.jpg');
INSERT INTO `picture` VALUES ('28', '29', 'pic/td1.jpg');
INSERT INTO `picture` VALUES ('29', '30', 'pic/zzj1.jpg');
INSERT INTO `picture` VALUES ('30', '30', 'pic/zzj2.jpg');
INSERT INTO `picture` VALUES ('31', '31', 'pic/lhx2.jpg');
INSERT INTO `picture` VALUES ('32', '31', 'pic/lhx3.jpg');
INSERT INTO `picture` VALUES ('33', '32', 'pic/zdc1.jpg');
INSERT INTO `picture` VALUES ('34', '32', 'pic/zdc2.jpg');
INSERT INTO `picture` VALUES ('35', '33', 'pic/hlq0.jpg');
INSERT INTO `picture` VALUES ('36', '33', 'pic/hlq1.jpg');
INSERT INTO `picture` VALUES ('37', '34', 'pic/xpq2.jpg');
INSERT INTO `picture` VALUES ('38', '34', 'pic/xpq0.jpg');
INSERT INTO `picture` VALUES ('39', '36', 'pic/dnz1.jpg');
INSERT INTO `picture` VALUES ('40', '36', 'pic/dnz2.jpg');
INSERT INTO `picture` VALUES ('41', '37', 'pic/ts1.jpg');
INSERT INTO `picture` VALUES ('42', '38', 'pic/djzh1.jpg');
INSERT INTO `picture` VALUES ('43', '38', 'pic/djzh2.jpg');
INSERT INTO `picture` VALUES ('44', '39', 'pic/zdg1.jpg');
INSERT INTO `picture` VALUES ('45', '39', 'pic/zdg2.jpg');

-- ----------------------------
-- Table structure for push_message
-- ----------------------------
DROP TABLE IF EXISTS `push_message`;
CREATE TABLE `push_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `goods` int(11) DEFAULT NULL,
  `push_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `if_read` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `push_message_goods_fk` (`goods`),
  KEY `push_message_receiver_fk` (`receiver`),
  KEY `push_message_type_fk` (`type`),
  KEY `push_mssage_sender_fk` (`sender`),
  CONSTRAINT `push_message_goods_fk` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `push_message_receiver_fk` FOREIGN KEY (`receiver`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `push_message_type_fk` FOREIGN KEY (`type`) REFERENCES `push_message_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `push_mssage_sender_fk` FOREIGN KEY (`sender`) REFERENCES `user_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of push_message
-- ----------------------------
INSERT INTO `push_message` VALUES ('21', '1', '10', null, null, null, '亲爱的 苹果 用户 Google 关注了您', null);

-- ----------------------------
-- Table structure for push_message_type
-- ----------------------------
DROP TABLE IF EXISTS `push_message_type`;
CREATE TABLE `push_message_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of push_message_type
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `portrait` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `asset` decimal(10,0) DEFAULT NULL COMMENT '账户资金',
  `goods_count` int(11) DEFAULT NULL,
  `friend_count` int(11) DEFAULT NULL,
  `fans_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', 'admin', null, null, null, '13820837759', null, 'admin@trade.com', '-590', '0', '0', '0');
INSERT INTO `user_info` VALUES ('7', 'Google', 'google123', null, null, 'pic/google.jpg', '13820837759', null, 'google@gmail.com', '1740', '5', '3', '0');
INSERT INTO `user_info` VALUES ('8', '联想', 'lx1234', null, null, 'pic/lx.jpg', '15095659798', '女', 'lx@gmail.com', '2210', '4', '0', '0');
INSERT INTO `user_info` VALUES ('9', '三星', 'anycall', null, null, null, '13012342345', null, 'anycall@gmail.com', '0', '4', '0', '0');
INSERT INTO `user_info` VALUES ('10', '苹果', 'apple123', null, null, 'pic/apple.jpg', '13767897890', null, 'apple@gmail.com', '4050', '3', '0', '0');
