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

 Date: 13/04/2021 10:46:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for questioncomments
-- ----------------------------
DROP TABLE IF EXISTS `questioncomments`;
CREATE TABLE `questioncomments`  (
  `id` int unsigned NOT NULL,
  `questionId` int unsigned NOT NULL,
  `userId` int unsigned NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createAt` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `questionId`(`questionId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  CONSTRAINT `questioncomments_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `questioninfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `questioncomments_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questioncomments
-- ----------------------------
INSERT INTO `questioncomments` VALUES (1, 1, 1, '人民万岁！', NULL, '2021-04-13 10:41:32');
INSERT INTO `questioncomments` VALUES (2, 1, 1, 'ambassador', NULL, '2021-04-13 10:41:32');
INSERT INTO `questioncomments` VALUES (3, 2, 1, '为人民服务！', NULL, '2021-04-13 10:41:32');
INSERT INTO `questioncomments` VALUES (4, 3, 3, '天龙八部', NULL, '2021-04-13 10:41:32');
INSERT INTO `questioncomments` VALUES (5, 3, 1, '剑侠情缘', NULL, '2021-04-13 10:41:32');
INSERT INTO `questioncomments` VALUES (6, 4, 5, '随意流动！！！', NULL, '2021-04-13 10:41:32');

SET FOREIGN_KEY_CHECKS = 1;
