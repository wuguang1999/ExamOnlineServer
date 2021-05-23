/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : exam_online

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 22/05/2021 12:13:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int unsigned AUTO_INCREMENT NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `accumulate` int unsigned NOT NULL DEFAULT 0,
  `createAt` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userName`(`userName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, '13033333333', 'Volcano Paul', '654321', 'http://192.168.1.107:8080/userinfo_13033333333_avatar_20212522222515.jpg', 100, '2021-04-22 09:33:55');
INSERT INTO `userinfo` VALUES (2, '13072860573', 'Jena Dunn', '12345', NULL, 99, '2021-04-22 09:33:55');
INSERT INTO `userinfo` VALUES (3, '13985858391', '睡火山', '654321', NULL, 10, '2021-04-22 09:33:56');
INSERT INTO `userinfo` VALUES (4, '测试', '十一月的雨', 'abandon', 'http://192.168.1.107:8080/userinfo_volcano9991_avatar_20212422222434.jpg', 5, now());

SET FOREIGN_KEY_CHECKS = 1;
