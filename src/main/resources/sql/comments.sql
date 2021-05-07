SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
    `id` int unsigned AUTO_INCREMENT NOT NULL,
    `targetId` int unsigned NOT NULL,
    `userId` int unsigned NOT NULL,
    `type` enum('试题','文章') NOT NULL,
    `description` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `img` mediumblob DEFAULT NULL,
    `zan` int unsigned DEFAULT NULL,
    `createAt` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `userId`(`userId`) USING BTREE,
    CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `comments` VALUES (0, 1, 1,'文章', '人民万岁！', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 1, 7,'文章', 'ambassador', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 2, 4,'文章', '为人民服务！', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 3, 3,'文章', '天龙八部', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 3, 2,'文章', '剑侠情缘', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 4, 5,'文章', '随意流动！！！', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 1, 6,'试题', '人民万岁！', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 2, 5,'试题', 'ambassador', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 3, 4,'试题', '为人民服务！', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 4, 3,'试题', '天龙八部', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 5, 2,'试题', '剑侠情缘', NULL, NULL, '2021-04-13 10:41:32');
INSERT INTO `comments` VALUES (0, 6, 1,'试题', '随意流动！！！', NULL, NULL, '2021-04-13 10:41:32');



SET FOREIGN_KEY_CHECKS = 1;
