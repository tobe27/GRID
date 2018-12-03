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

 Date: 30/11/2018 17:48:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grid_info
-- ----------------------------
DROP TABLE IF EXISTS `grid_info`;
CREATE TABLE `grid_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `grid_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格编号',
  `grid_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格名称',
  `org_code` bigint(20) NULL DEFAULT NULL COMMENT '机构号',
  `grid_type` int(10) NULL DEFAULT NULL COMMENT '1.村社型、2.社区型、3.专业市场、4.工业园、5.企业、6.事业单位、7.临街商户',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格描述',
  `account_id` bigint(20) NULL DEFAULT NULL COMMENT '网格管理员（客户经理）',
  `assist_manager` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格协管员',
  `supervise_manager` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格监督员',
  `qr_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格二维码',
  `grid_map` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格地图',
  `created_at` bigint(255) NULL DEFAULT NULL COMMENT '创建日期',
  `updated_at` bigint(255) NULL DEFAULT NULL COMMENT '修改日期',
  `delete_flag` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志 0为未删除  1为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_info
-- ----------------------------
INSERT INTO `grid_info` VALUES (1, 'HM20181115', '花木-事业单位', 17005, 6, '花木-事业单位', 2, NULL, NULL, NULL, NULL, 1542251464646, NULL, '0');


SET FOREIGN_KEY_CHECKS = 1;
