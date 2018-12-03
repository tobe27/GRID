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

 Date: 27/11/2018 16:05:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grid_user_role
-- ----------------------------
DROP TABLE IF EXISTS `grid_user_role`;
CREATE TABLE `grid_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `corp_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '法人编号',
  `account_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_user_role
-- ----------------------------
INSERT INTO `grid_user_role` VALUES (1, NULL, 1, 7);


SET FOREIGN_KEY_CHECKS = 1;
