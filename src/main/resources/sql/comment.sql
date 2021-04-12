SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
    `id` int unsigned  PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `articleId` int unsigned NOT NULL,
    `userId` int unsigned NOT NULL,
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `createAt` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
    FOREIGN KEY (`articleId`) REFERENCES `articleinfo`(`id`),
    FOREIGN KEY (`userId`) REFERENCES `userinfo`(`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

BEGIN;
INSERT INTO `comment` VALUES (0, 1, 1, '人民万岁！', now());
INSERT INTO `comment` VALUES (0, 1, 0, 'ambassador', now());
INSERT INTO `comment` VALUES (0, 2, 1, '为人民服务！', now());
INSERT INTO `comment` VALUES (0, 3, 0, '天龙八部', now());
INSERT INTO `comment` VALUES (0, 3, 1, '剑侠情缘', now());
INSERT INTO `comment` VALUES (0, 4, 0, '随意流动！！！', now());
COMMIT;
SET FOREIGN_KEY_CHECKS = 1;
