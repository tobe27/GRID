/*
 Navicat Premium Data Transfer

 Source Server         : YZS本地
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : 192.168.1.137:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 27/11/2018 16:06:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grid_org
-- ----------------------------
DROP TABLE IF EXISTS `grid_org`;
CREATE TABLE `grid_org`  (
  `org_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_code` bigint(20) NOT NULL,
  `org_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `corp_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pre_org_id` bigint(20) NOT NULL,
  `relation_type` int(11) NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 1,
  `virtual_org_flag` int(11) NOT NULL DEFAULT 0,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `org_level` int(11) NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`org_id`) USING BTREE,
  UNIQUE INDEX `uk_org_code`(`org_code`) USING BTREE,
  UNIQUE INDEX `uk_org_name`(`org_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_org
-- ----------------------------
INSERT INTO `grid_org` VALUES (1, 17005, '上海农村商业银行', '55595455', 0, 1, 1, 0, '上海农村商业银行总行', 1, 1, 1539937070294, 1539937070294);
INSERT INTO `grid_org` VALUES (16, 51314, '上海浦东支行', '3456789', 1, 0, 1, 0, '上海市浦东新区上海支行-test', 1, 1, 1542592309048, 1542615991088);
INSERT INTO `grid_org` VALUES (18, 201811191556, '上海杨浦支行', '', 1, 0, 1, 0, '测试', 1, 1, 1542614238268, 1542684662881);

SET FOREIGN_KEY_CHECKS = 1;
