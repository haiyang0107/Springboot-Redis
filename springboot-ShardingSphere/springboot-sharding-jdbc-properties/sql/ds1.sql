/*
 Navicat Premium Data Transfer

 Source Server         : localhsot
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : ds1

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 15/07/2020 17:13:10
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
  `is_intercept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
INSERT INTO `message_0` VALUES (7895, '2020-05-09 10:00:00', 3, 6, '测试数据录入', '测试数据', '未读', NULL, '0', '1', '2020-05-09 10:00:00', 1, '2020-05-09 10:00:00', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778408050393354240, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '2', '1', '1', '111', '2020-05-18 11:51:49', NULL, '2020-05-18 11:51:49', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778409004450713600, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '2', '1', '1', '111', '2020-05-18 11:51:53', NULL, '2020-05-18 11:51:53', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778410151014043648, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778420638560817152, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778426149117104128, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778434905318100992, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '1', '1', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778435718308433920, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '1', '2', '1', '111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (778437008732196864, '2020-05-12 14:35:10', 33333, 2, '北投', '站内信', '1', '1', '1', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (780132813335695360, '2020-05-12 14:35:10', 1, 2, '北投qq', '站内信', '1', '1', '1', '北投qq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `message_0` VALUES (780258029621743616, '2020-05-12 14:35:10', 1, 2, '北投qq', '站内信', '1', '1', '1', '北投qq', NULL, NULL, NULL, NULL, '1', '1', '1', '1', '1');

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
INSERT INTO `message_1` VALUES (778807962666799104, '2020-05-12 14:35:10', 33333, 3, '北投', '站内信', '1', '1', '1', '合同', '2020-05-18 14:55:04', 0, '2020-05-18 14:55:04', 0, NULL, NULL, NULL, NULL, NULL);

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
