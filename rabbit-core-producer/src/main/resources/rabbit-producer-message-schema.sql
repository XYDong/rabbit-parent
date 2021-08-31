/*
 Navicat Premium Data Transfer

 Source Server         : TX
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 49.233.171.234:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 30/08/2021 17:09:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for broker_message
-- ----------------------------
DROP TABLE IF EXISTS `broker_message`;
CREATE TABLE `broker_message`  (
  `message_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `try_couunt` int(4) NULL DEFAULT 0,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
  `next_retry` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
