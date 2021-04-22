

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `hotkey`;
CREATE TABLE `hotkey`  (
     `id` int unsigned AUTO_INCREMENT NOT NULL,
     `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
     `order` int unsigned NOT NULL,
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `hotkey` VALUES (0, '物理治疗', 1);
INSERT INTO `hotkey` VALUES (0, 'flutter', 2);
INSERT INTO `hotkey` VALUES (0, '肌肉拉伤', 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
