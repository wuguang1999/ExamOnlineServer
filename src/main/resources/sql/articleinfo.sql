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

 Date: 16/04/2021 13:23:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for articleinfo
-- ----------------------------
DROP TABLE IF EXISTS `articleinfo`;
CREATE TABLE `articleinfo`  (
  `id` int unsigned AUTO_INCREMENT NOT NULL,
  `userId` int unsigned NOT NULL,
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `field` enum('面试求职','生活日常','学习心得') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` mediumblob DEFAULT NULL,
  `commentNums` int unsigned NOT NULL DEFAULT 0,
  `zanNums` int unsigned NOT NULL DEFAULT 0,
  `createAt` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  CONSTRAINT `articleinfo_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `articleinfo_ibfk_2` FOREIGN KEY (`userName`) REFERENCES `userinfo` (`userName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of articleinfo
-- ----------------------------
INSERT INTO `articleinfo` VALUES (1, 2, 'Jena Dunn', '念奴娇·北戴河', '往事越千年，魏武挥鞭，东临碣石有遗篇', '生活日常', NULL, 0, 0, current_timestamp);
INSERT INTO `articleinfo` VALUES (2, 2, 'Jena Dunn', '沁园春·长沙', '曾记否，到中流击水，浪遏飞舟', '生活日常', NULL, 0, 0, '2021-04-16 13:22:46');
INSERT INTO `articleinfo` VALUES (3, 2, 'Jena Dunn', '念奴娇·雪', '俱往矣，数风流人物，还看今朝。', '生活日常', NULL, 0, 0, '2021-04-16 13:22:46');
INSERT INTO `articleinfo` VALUES (4, 4, '十一月的雨', '忆秦娥·娄山关', '雄关漫道真如铁，而今迈步从头越', '生活日常', NULL, 0, 0, '2021-04-16 13:22:46');
SET FOREIGN_KEY_CHECKS = 1;
