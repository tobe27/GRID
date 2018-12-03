/*
 Navicat Premium Data Transfer

 Source Server         : YZS阿里云
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 101.132.152.195:3306
 Source Schema         : grid

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 30/11/2018 17:50:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tag_info
-- ----------------------------
DROP TABLE IF EXISTS `tag_info`;
CREATE TABLE `tag_info`  (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(11) NOT NULL,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `uk_tag_name`(`tag_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag_info
-- ----------------------------
INSERT INTO `tag_info` VALUES (1, '黑名单', '黑名单', 1, '黑白灰名单', 1542007783375, 1542360626147);
INSERT INTO `tag_info` VALUES (2, '授信客户', '授信客户', 1, '黑白灰名单', 1542007783377, 1542360636865);
INSERT INTO `tag_info` VALUES (3, '灰名单', '灰名单', 1, '黑白灰名单', 1542007783373, 1542360643331);
INSERT INTO `tag_info` VALUES (4, '贫困户', '贫困户', 2, '贫困户', 1542007783376, 1542360666324);
INSERT INTO `tag_info` VALUES (5, '白名单', '白名单', 1, '白名单', 1542007783374, 1542771974171);

SET FOREIGN_KEY_CHECKS = 1;
