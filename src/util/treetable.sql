/*
 Navicat Premium Data Transfer

 Source Server         : hhqiwei
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 26/08/2019 17:05:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for treetable
-- ----------------------------
DROP TABLE IF EXISTS `treetable`;
CREATE TABLE `treetable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of treetable
-- ----------------------------
INSERT INTO `treetable` VALUES (1, 'Food', 0);
INSERT INTO `treetable` VALUES (2, 'Fruit', 1);
INSERT INTO `treetable` VALUES (3, 'Red', 2);
INSERT INTO `treetable` VALUES (4, 'Cherry', 3);
INSERT INTO `treetable` VALUES (5, 'Yellow', 2);
INSERT INTO `treetable` VALUES (6, 'Banana', 5);
INSERT INTO `treetable` VALUES (7, 'Meat', 1);
INSERT INTO `treetable` VALUES (8, 'Beef', 7);
INSERT INTO `treetable` VALUES (9, 'Pork', 7);

SET FOREIGN_KEY_CHECKS = 1;
