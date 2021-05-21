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
    `zan` int unsigned NOT NULL DEFAULT 0,
    `createAt` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0),
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `userId`(`userId`) USING BTREE,
    CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
