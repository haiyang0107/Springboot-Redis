/*
 Navicat Premium Data Transfer

 Source Server         : localhsot
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : ds0

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 15/07/2020 17:13:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for message_0
-- ----------------------------
DROP TABLE IF EXISTS `message_0`;
CREATE TABLE `message_0`  (
  `id` bigint(0) NOT NULL,
  `send_time` datetime(0) NOT NULL COMMENT '消息发送时间',
  `addressor_id` bigint(0) NOT NULL COMMENT '发件人id',
  `recipient_id` bigint(0) NOT NULL COMMENT '收件人id',
  `source_system` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统来源',
  `send_way` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息发送方式',
  `message_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息状态',
  `is_intercept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_delete` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否删除',
  `message_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息类别',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) NULL DEFAULT NULL,
  `href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '链接',
  `rec_href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '超链接',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `rec_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收方标题',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_0
-- ----------------------------
INSERT INTO `message_0` VALUES (2482, '2020-05-09 10:00:00', 3, 8, '测试数据录入', '测试数据', '未读', '', '0', '1', '2020-05-09 10:00:00', 1, '2020-05-09 10:00:00', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (17116, '2020-05-09 10:00:00', 3, 8, '测试数据录入', '测试数据', '未读', '', '0', '1', '2020-05-09 10:00:00', 1, '2020-05-09 10:00:00', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778049703274090496, '2020-05-12 14:35:10', 1, 2, '北投qq', '短信', '2', '1', '1', '北投qq', '2020-05-18 14:06:01', NULL, '2020-05-18 14:06:01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778056273005383680, '2020-05-12 14:35:10', 1, 2, '北投', '短信', '2', '1', '1', '北投', '2020-05-18 14:05:58', NULL, '2020-05-18 14:05:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778057314497204224, '2020-05-12 14:35:10', 1, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778057660892188672, '2020-05-12 14:35:10', 11111111, 2, '北投', '短信', '2', '1', '1', '北投', '2020-05-18 11:51:36', NULL, '2020-05-18 11:51:36', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778058798366461952, '2020-05-12 14:35:10', 222222, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778062154124234752, '2020-05-12 14:35:10', 222222, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778062462829203456, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778065370538840064, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778065912652632064, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778066548509118464, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778067585714360320, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778067965982543872, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778068114964221952, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778068328156499968, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778068514547175424, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778068727911419904, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778068995910668288, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778071230312878080, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778071596584669184, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778073651739103232, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778075425908723712, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '北投', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778079586561822720, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778082941573009408, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778083183383023616, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778085851677593600, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778086250149056512, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778088410349834240, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778315568527708160, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778316345207951360, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778319474129375232, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778320164293709824, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778321817466048512, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778324377262362624, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778330007343730688, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778330966643970048, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778331780888399872, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778379497148780544, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778380238663979008, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778380486157275136, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778380933366550528, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778384229103833088, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778384601155375104, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778386290033823744, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778393137809723392, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778396149043171328, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778396855045197824, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778397187636727808, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778397280494424064, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778397428393971712, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778399215851147264, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778400007215648768, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778402396458979328, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778404569053925376, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778404669339734016, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778407625925595136, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778438968373940224, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778439235668545536, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778439394351648768, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778441821310160896, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778443311860944896, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778443819807936512, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778444598929264640, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778445903861125120, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778449516142530560, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778449632324751360, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778449983429939200, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778452213142917120, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778452561614082048, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778674594113392640, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778674978567491584, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778676806017355776, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778677207596797952, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778678148790226944, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778679113375289344, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778681348905766912, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778690648558538752, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778692906331410432, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778696369048129536, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778697617075867648, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778708984440819712, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778709908030754816, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778710147718451200, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778711931987955712, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778714023813189632, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778742202313478144, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778742531234992128, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778742804208685056, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778743187991695360, '2020-05-12 14:35:10', 33333, 2, '北投', '邮件', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778743790742540288, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778744949515489280, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778745644889149440, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778745772488265728, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778746518180990976, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778747420342226944, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778747654338252800, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778747968080580608, '2020-05-12 14:35:10', 33333, 2, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (780132555088203776, '2020-05-12 14:35:10', 1, 2, '北投qq', '短信', '1', '1', '1', '北投qq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for message_1
-- ----------------------------
DROP TABLE IF EXISTS `message_1`;
CREATE TABLE `message_1`  (
  `id` bigint(0) NOT NULL,
  `send_time` datetime(0) NOT NULL COMMENT '消息发送时间',
  `addressor_id` bigint(0) NOT NULL COMMENT '发件人id',
  `recipient_id` bigint(0) NOT NULL COMMENT '收件人id',
  `source_system` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统来源',
  `send_way` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息发送方式',
  `message_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息状态',
  `is_intercept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_delete` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否删除',
  `message_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息类别',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) NULL DEFAULT NULL,
  `href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '链接',
  `rec_href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '超链接',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `rec_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收方标题',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_1
-- ----------------------------
INSERT INTO `message_1` VALUES (778807660890820608, '2020-05-12 14:35:10', 33333, 3, '北投', '短信', '2', '1', '1', '合同', NULL, 0, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (780162106434523136, '2020-05-12 14:35:10', 33333, 3, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (780162948847898624, '2020-05-12 14:35:10', 33333, 3, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (780164079191855104, '2020-05-20 14:35:10', 33333, 3, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (780166051705917440, '2020-05-20 14:35:10', 33333, 3, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (780190625919143936, '2020-05-20 14:35:10', 44444, 3, '北投', '短信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (780194679143534592, '2020-05-20 14:35:10', 555555, 3, '北投', '短信', '1', '1', '1', '合同', '2020-05-19 13:50:03', NULL, '2020-05-19 13:50:03', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (785330559961272320, '2020-05-20 14:35:10', 555555, 3, '北投', '短信', '1', '1', '1', '合同', '2020-06-02 17:58:12', NULL, '2020-06-02 17:58:12', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790691365997514752, '2020-05-20 14:35:10', 555555, 3, '北投', '短信', '1', '1', '1', '合同', '2020-06-17 13:00:08', NULL, '2020-06-17 13:00:08', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790691967968219136, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 13:02:30', NULL, '2020-06-17 13:02:30', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790692497981444096, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 13:04:37', NULL, '2020-06-17 13:04:37', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790693196840570880, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 13:07:23', NULL, '2020-06-17 13:07:23', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790702655763648512, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 13:44:58', NULL, '2020-06-17 13:44:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790704986135728128, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 13:54:14', NULL, '2020-06-17 13:54:14', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790710645682081792, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 14:16:43', NULL, '2020-06-17 14:16:43', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790711069042544640, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 14:18:24', NULL, '2020-06-17 14:18:24', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790715159239725056, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 14:34:40', NULL, '2020-06-17 14:34:40', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790718815259332608, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 14:49:11', NULL, '2020-06-17 14:49:11', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790726155412967424, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 15:18:22', NULL, '2020-06-17 15:18:22', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790726171028361216, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 15:18:25', NULL, '2020-06-17 15:18:25', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790731055685439488, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 15:37:50', NULL, '2020-06-17 15:37:50', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790733205429817344, '2020-05-20 14:35:10', 555555, 3, '北投', '短信', '1', '1', '1', '合同', '2020-06-17 15:46:22', NULL, '2020-06-17 15:46:22', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790733544241500160, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 15:47:43', NULL, '2020-06-17 15:47:43', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790734218463285248, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 15:50:24', NULL, '2020-06-17 15:50:24', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_1` VALUES (790734435396882432, '2020-05-20 14:35:10', 555555, 3, '北投', '邮件', '1', '1', '1', '合同', '2020-06-17 15:51:15', NULL, '2020-06-17 15:51:15', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for message_2
-- ----------------------------
DROP TABLE IF EXISTS `message_2`;
CREATE TABLE `message_2`  (
  `id` bigint(0) NOT NULL,
  `send_time` datetime(0) NOT NULL COMMENT '消息发送时间',
  `addressor_id` bigint(0) NOT NULL COMMENT '发件人id',
  `recipient_id` bigint(0) NOT NULL COMMENT '收件人id',
  `source_system` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统来源',
  `send_way` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息发送方式',
  `message_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息状态',
  `is_intercept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_delete` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否删除',
  `message_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息类别',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) NULL DEFAULT NULL,
  `href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '链接',
  `rec_href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '超链接',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `rec_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收方标题',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message_3
-- ----------------------------
DROP TABLE IF EXISTS `message_3`;
CREATE TABLE `message_3`  (
  `id` bigint(0) NOT NULL,
  `send_time` datetime(0) NOT NULL COMMENT '消息发送时间',
  `addressor_id` bigint(0) NOT NULL COMMENT '发件人id',
  `recipient_id` bigint(0) NOT NULL COMMENT '收件人id',
  `source_system` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统来源',
  `send_way` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息发送方式',
  `message_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息状态',
  `is_intercept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_delete` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否删除',
  `message_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息类别',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_by` bigint(0) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `update_by` bigint(0) NULL DEFAULT NULL,
  `href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '链接',
  `rec_href` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '超链接',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `rec_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收方标题',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
