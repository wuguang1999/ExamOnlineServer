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

 Date: 13/04/2021 14:29:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for questioninfo
-- ----------------------------
DROP TABLE IF EXISTS `questioninfo`;
CREATE TABLE `questioninfo`  (
  `id` int unsigned NOT NULL,
  `subjectId` int unsigned NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` enum('单选题','多选题','判断题','填空题','问答题') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `level` enum('简单','中等','困难') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `correctAnswer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `optionA` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `optionB` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `optionC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `optionD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `optionE` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `isSelect` int unsigned NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `subjectId`(`subjectId`) USING BTREE,
  CONSTRAINT `questioninfo_ibfk_1` FOREIGN KEY (`subjectId`) REFERENCES `subjectinfo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questioninfo
-- ----------------------------
INSERT INTO `questioninfo` VALUES (1, 1, '物理治疗不包含（  ）', '单选题', NULL, '中等', '医学2021-2022春', '提示：C。物理治疗分成运动疗法和物理因子疗法，其中物理因子疗法包括力、电、光、声、水及温度等物理因子。', 'C', '超声波疗法', '运动疗法', '环境改造', '热疗法', '磁疗法', 1);
INSERT INTO `questioninfo` VALUES (2, 1, '进行被动运动时，哪一项动作是不正确的（  ）', '单选题', NULL, '中等', '医学2021-2022春', '提示：D。被动运动顺序应从肢体近端至远端。', 'D', '在进行过程中可对关节稍加牵拉', '在活动最后应对关节稍加挤压', '进行被动运动时可允许有轻微的疼痛', '瘫痪病人被动运动顺序应从肢体远端至近端', '身体不参与活动的部分应给予适当支托', 1);
INSERT INTO `questioninfo` VALUES (3, 1, '物理治疗不包含（  ）', '单选题', NULL, '中等', '医学2021-2022春', '提示：C。物理治疗分成运动疗法和物理因子疗法，其中物理因子疗法包括力、电、光、声、水及温度等物理因子。', 'C', '超声波疗法', '运动疗法', '环境改造', '热疗法', '磁疗法', 1);
INSERT INTO `questioninfo` VALUES (4, 1, '进行被动运动时，哪一项动作是不正确的（  ）', '单选题', NULL, '中等', '医学2021-2022春', '提示：D。被动运动顺序应从肢体近端至远端。', 'D', '在进行过程中可对关节稍加牵拉', '在活动最后应对关节稍加挤压', '进行被动运动时可允许有轻微的疼痛', '瘫痪病人被动运动顺序应从肢体远端至近端', '身体不参与活动的部分应给予适当支托', 1);
INSERT INTO `questioninfo` VALUES (5, 1, '下列不是肌肉牵伸禁忌证的是（    ）', '单选题', NULL, '中等', '医学2021-2022春', '提示：C。肌肉牵伸禁忌证为严重的骨质疏松；骨性限制关节活动；神经损伤或神经吻合术后1个月内；关节活动或肌肉被拉长时疼痛剧烈；挛缩或软组织短缩已经造成关节固定，形成了不可逆性挛缩；新近发生的骨折、肌肉和韧带损伤，组织内有血肿或其他创伤因素存在时；关节内或关节周围组织有炎症、感染、结核或肿瘤，特别是各种炎症急性阶段；当肌麻痹或严重肌无力患者，为了维持关节的稳定性、为了保持一定的肌肉力量而发生代偿性挛缩时，应慎用牵伸治疗。', 'C', '新近发生的骨折、肌肉和韧带损伤', '神经损伤或神经吻合术后1月内', '体育运动后肌肉产生的疼痛', '关节活动或肌肉被拉长时疼痛剧烈', '严重的骨质疏松', 1);
INSERT INTO `questioninfo` VALUES (6, 1, '运动的潜在危险有：（  ）', '多选题', NULL, '简单', '医学2021-2022春', NULL, 'ABD', '运动损伤', '诱发心血管事件', '促进内分泌', '脏器功能过负荷或者衰竭', '导致挫折感', 1);

SET FOREIGN_KEY_CHECKS = 1;
