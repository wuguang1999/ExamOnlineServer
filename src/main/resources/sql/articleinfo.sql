SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `articleinfo`;
CREATE TABLE `articleinfo`  (
    `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `userPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `createAt` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

BEGIN;
INSERT INTO `articleinfo` VALUES (1,'13072860573','Jena Dunn','念奴娇·北戴河','往事越千年，魏武挥鞭，东临碣石有遗篇',null,now());
INSERT INTO `articleinfo` VALUES (2,'13072860573','Jena Dunn','沁园春·长沙','曾记否，到中流击水，浪遏飞舟',null,now());
INSERT INTO `articleinfo` VALUES (3,'13072860573','Jena Dunn','念奴娇·雪','俱往矣，数风流人物，还看今朝。',null,now());
INSERT INTO `articleinfo` VALUES (4,'13072860573','Jena Dunn','忆秦娥·娄山关','雄关漫道真如铁，而今迈步从头越',null,now());
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
