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

 Date: 27/11/2018 16:05:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grid_role
-- ----------------------------
DROP TABLE IF EXISTS `grid_role`;
CREATE TABLE `grid_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID，自增主键',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_scope` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色应用范围',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `created_at` bigint(20) NOT NULL COMMENT '创建日期',
  `updated_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_role
-- ----------------------------
INSERT INTO `grid_role` VALUES (1, '客户经理', 'managerA', '', 1539937070294, 1542088767988);
INSERT INTO `grid_role` VALUES (2, '会计', 'accountant', '', 1539937120510, 1542111257117);
INSERT INTO `grid_role` VALUES (3, '支行长', 'subpresident', '', 1539937211922, 1541986383774);
INSERT INTO `grid_role` VALUES (5, '总行领导', 'president', '', 1539937346889, 1541986365729);
INSERT INTO `grid_role` VALUES (7, '系统管理员', 'super', '', 1539937404255, 1542111275203);

SET FOREIGN_KEY_CHECKS = 1;
