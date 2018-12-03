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

 Date: 27/11/2018 16:05:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grid_permission
-- ----------------------------
DROP TABLE IF EXISTS `grid_permission`;
CREATE TABLE `grid_permission`  (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `sys_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '系统代码',
  `permission_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `permission_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限类型',
  `permission_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `created_at` bigint(20) NULL DEFAULT NULL COMMENT '创建日期',
  `updated_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 146 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_permission
-- ----------------------------
INSERT INTO `grid_permission` VALUES (1, NULL, '后台添加', NULL, 'POST:/super', 1, NULL);
INSERT INTO `grid_permission` VALUES (2, NULL, '后台删除', NULL, 'DELETE:/super', 2, NULL);
INSERT INTO `grid_permission` VALUES (3, NULL, '后台修改', NULL, 'PUT:/super', 3, NULL);
INSERT INTO `grid_permission` VALUES (4, '23', '后台查看', '3', 'GET:/super', 4, 1540260652226);
INSERT INTO `grid_permission` VALUES (5, NULL, '添加客户信息', NULL, 'POST:/customer/info', 5, NULL);
INSERT INTO `grid_permission` VALUES (6, NULL, '删除客户信息', NULL, 'DELETE:/customer/info', 6, NULL);
INSERT INTO `grid_permission` VALUES (7, NULL, '修改客户信息', NULL, 'PUT:/customer/info', 7, NULL);
INSERT INTO `grid_permission` VALUES (8, '3', '查看客户信息', '2', 'GET:/customer/info', 8, 1540260704135);
INSERT INTO `grid_permission` VALUES (9, NULL, '添加家庭信息', NULL, 'POST:/customer/family', 9, NULL);
INSERT INTO `grid_permission` VALUES (10, NULL, '删除家庭信息', NULL, 'DELETE:/customer/family', 10, NULL);
INSERT INTO `grid_permission` VALUES (11, NULL, '修改家庭信息', NULL, 'PUT:/customer/family', 11, NULL);
INSERT INTO `grid_permission` VALUES (12, NULL, '查看家庭信息', NULL, 'GET:/customer/family', 12, NULL);
INSERT INTO `grid_permission` VALUES (13, NULL, '添加财务信息', NULL, 'POST:/customer/finance', 13, NULL);
INSERT INTO `grid_permission` VALUES (14, NULL, '删除财务信息', NULL, 'DELETE:/customer/finance', 14, NULL);
INSERT INTO `grid_permission` VALUES (15, NULL, '修改财务信息', NULL, 'PUT:/customer/finance', 15, NULL);
INSERT INTO `grid_permission` VALUES (16, NULL, '查看财务信息', NULL, 'GET:/customer/finance', 16, NULL);
INSERT INTO `grid_permission` VALUES (17, NULL, '添加业务信息', NULL, 'POST:/customer/product', 17, NULL);
INSERT INTO `grid_permission` VALUES (18, NULL, '删除业务信息', NULL, 'DELETE:/customer/product', 18, NULL);
INSERT INTO `grid_permission` VALUES (19, NULL, '修改业务信息', NULL, 'PUT:/customer/product', 19, NULL);
INSERT INTO `grid_permission` VALUES (20, NULL, '查看业务信息', NULL, 'GET:/customer/product', 20, NULL);
INSERT INTO `grid_permission` VALUES (21, NULL, '添加信誉信息', NULL, 'POST:/customer/credit', 21, NULL);
INSERT INTO `grid_permission` VALUES (22, NULL, '删除信誉信息', NULL, 'DELETE:/customer/credit', 22, NULL);
INSERT INTO `grid_permission` VALUES (23, NULL, '修改信誉信息', NULL, 'PUT:/customer/credit', 23, NULL);
INSERT INTO `grid_permission` VALUES (24, NULL, '查看信誉信息', NULL, 'GET:/customer/credit', 24, NULL);
INSERT INTO `grid_permission` VALUES (25, NULL, '添加客户标签', NULL, 'POST:/customer/tag', 25, NULL);
INSERT INTO `grid_permission` VALUES (26, NULL, '删除客户标签', NULL, 'DELETE:/customer/tag', 26, NULL);
INSERT INTO `grid_permission` VALUES (27, NULL, '修改客户标签', NULL, 'PUT:/customer/tag', 27, NULL);
INSERT INTO `grid_permission` VALUES (28, NULL, '查看客户标签', NULL, 'GET:/customer/tag', 28, NULL);
INSERT INTO `grid_permission` VALUES (29, NULL, '添加影像资料', NULL, 'POST:/customer/image', 29, NULL);
INSERT INTO `grid_permission` VALUES (30, NULL, '删除影像资料', NULL, 'DELETE:/customer/image', 30, NULL);
INSERT INTO `grid_permission` VALUES (31, NULL, '修改影像资料', NULL, 'PUT:/customer/image', 31, NULL);
INSERT INTO `grid_permission` VALUES (32, NULL, '查看影像资料', NULL, 'GET:/customer/image', 32, NULL);
INSERT INTO `grid_permission` VALUES (33, NULL, '添加失信人', NULL, 'POST:/customer/dishonest', 33, NULL);
INSERT INTO `grid_permission` VALUES (34, NULL, '删除失信人', NULL, 'DELETE:/customer/dishonest', 34, NULL);
INSERT INTO `grid_permission` VALUES (35, NULL, '修改失信人', NULL, 'PUT:/customer/dishonest', 35, NULL);
INSERT INTO `grid_permission` VALUES (36, NULL, '查看失信人', NULL, 'GET:/customer/dishonest', 36, NULL);
INSERT INTO `grid_permission` VALUES (37, NULL, '添加权限', NULL, 'POST:/super/permission', 37, NULL);
INSERT INTO `grid_permission` VALUES (38, NULL, '删除权限', NULL, 'DELETE:/super/permission', 38, NULL);
INSERT INTO `grid_permission` VALUES (39, NULL, '修改权限', NULL, 'PUT:/super/permission', 39, NULL);
INSERT INTO `grid_permission` VALUES (40, NULL, '查看权限', NULL, 'GET:/super/permission', 40, NULL);
INSERT INTO `grid_permission` VALUES (41, NULL, '添加户籍模块', NULL, 'POST:/customer/resident', 41, NULL);
INSERT INTO `grid_permission` VALUES (42, NULL, '删除户籍模块', NULL, 'DELETE:/customer/resident', 42, NULL);
INSERT INTO `grid_permission` VALUES (43, NULL, '修改户籍模块', NULL, 'PUT:/customer/resident', 43, NULL);
INSERT INTO `grid_permission` VALUES (44, NULL, '查看户籍模块', NULL, 'GET:/customer/resident', 44, NULL);
INSERT INTO `grid_permission` VALUES (45, NULL, '添加白名单', NULL, 'POST:/customer/whitelist', 45, NULL);
INSERT INTO `grid_permission` VALUES (46, NULL, '删除白名单', NULL, 'DELETE:/customer/whitelist', 46, NULL);
INSERT INTO `grid_permission` VALUES (47, NULL, '修改白名单', NULL, 'PUT:/customer/whitelist', 47, NULL);
INSERT INTO `grid_permission` VALUES (48, NULL, '查看白名单', NULL, 'GET:/customer/whitelist', 48, NULL);
INSERT INTO `grid_permission` VALUES (49, NULL, '添加灰名单', NULL, 'POST:/customer/greylist', 49, NULL);
INSERT INTO `grid_permission` VALUES (50, NULL, '删除灰名单', NULL, 'DELETE:/customer/greylist', 50, NULL);
INSERT INTO `grid_permission` VALUES (51, NULL, '修改灰名单', NULL, 'PUT:/customer/greylist', 51, NULL);
INSERT INTO `grid_permission` VALUES (52, NULL, '查看灰名单', NULL, 'GET:/customer/greylist', 52, NULL);
INSERT INTO `grid_permission` VALUES (53, NULL, '添加黑名单', NULL, 'POST:/customer/blacklist', 53, NULL);
INSERT INTO `grid_permission` VALUES (54, NULL, '删除黑名单', NULL, 'DELETE:/customer/blacklist', 54, NULL);
INSERT INTO `grid_permission` VALUES (55, NULL, '修改黑名单', NULL, 'PUT:/customer/blacklist', 55, NULL);
INSERT INTO `grid_permission` VALUES (56, NULL, '查看黑名单', NULL, 'GET:/customer/blacklist', 56, NULL);
INSERT INTO `grid_permission` VALUES (57, NULL, '添加授信明细', NULL, 'POST:/customer/creditdetail', 57, NULL);
INSERT INTO `grid_permission` VALUES (58, NULL, '删除授信明细', NULL, 'DELETE:/customer/creditdetail', 58, NULL);
INSERT INTO `grid_permission` VALUES (59, NULL, '修改授信明细', NULL, 'PUT:/customer/creditdetail', 59, NULL);
INSERT INTO `grid_permission` VALUES (60, NULL, '查看授信明细', NULL, 'GET:/customer/creditdetail', 60, NULL);
INSERT INTO `grid_permission` VALUES (61, NULL, '添加审批记录', NULL, 'POST:/customer/creditapproval', 61, NULL);
INSERT INTO `grid_permission` VALUES (62, NULL, '删除审批记录', NULL, 'DELETE:/customer/creditapproval', 62, NULL);
INSERT INTO `grid_permission` VALUES (63, NULL, '修改审批记录', NULL, 'PUT:/customer/creditapproval', 63, NULL);
INSERT INTO `grid_permission` VALUES (64, NULL, '查看审批记录', NULL, 'GET:/customer/creditapproval', 64, NULL);
INSERT INTO `grid_permission` VALUES (65, NULL, '添加顾问意见', NULL, 'POST:/customer/creditadviser', 65, NULL);
INSERT INTO `grid_permission` VALUES (66, NULL, '删除顾问意见', NULL, 'DELETE:/customer/creditadviser', 66, NULL);
INSERT INTO `grid_permission` VALUES (67, NULL, '修改顾问意见', NULL, 'PUT:/customer/creditadviser', 67, NULL);
INSERT INTO `grid_permission` VALUES (68, NULL, '查看顾问意见', NULL, 'GET:/customer/creditadviser', 68, NULL);
INSERT INTO `grid_permission` VALUES (69, NULL, '添加首页', NULL, 'POST:/home', 69, NULL);
INSERT INTO `grid_permission` VALUES (70, NULL, '删除首页', NULL, 'DELETE:/home', 70, NULL);
INSERT INTO `grid_permission` VALUES (71, NULL, '修改首页', NULL, 'PUT:/home', 71, NULL);
INSERT INTO `grid_permission` VALUES (72, NULL, '查看首页', NULL, 'GET:/home', 72, NULL);
INSERT INTO `grid_permission` VALUES (73, NULL, '添加统计信息', NULL, 'POST:/home/statistics', 73, NULL);
INSERT INTO `grid_permission` VALUES (74, NULL, '删除统计信息', NULL, 'DELETE:/home/statistics', 74, NULL);
INSERT INTO `grid_permission` VALUES (75, NULL, '修改统计信息', NULL, 'PUT:/home/statistics', 75, NULL);
INSERT INTO `grid_permission` VALUES (76, NULL, '查看统计信息', NULL, 'GET:/home/statistics', 76, NULL);
INSERT INTO `grid_permission` VALUES (77, NULL, '添加异动信息', NULL, 'POST:/home/warn', 77, NULL);
INSERT INTO `grid_permission` VALUES (78, NULL, '删除异动信息', NULL, 'DELETE:/home/warn', 78, NULL);
INSERT INTO `grid_permission` VALUES (79, NULL, '修改异动信息', NULL, 'PUT:/home/warn', 79, NULL);
INSERT INTO `grid_permission` VALUES (80, NULL, '查看异动信息', NULL, 'GET:/home/warn', 80, NULL);
INSERT INTO `grid_permission` VALUES (81, NULL, '添加首页基本信息', NULL, 'POST:/home/basicinfo', 81, NULL);
INSERT INTO `grid_permission` VALUES (82, NULL, '删除首页基本信息', NULL, 'DELETE:/home/basicinfo', 82, NULL);
INSERT INTO `grid_permission` VALUES (83, '1', '修改首页基本信息', '1', 'PUT:/home/basicinfo', 83, 1542174937184);
INSERT INTO `grid_permission` VALUES (84, '1', '查看首页基本信息', '1', 'GET:/home/basicinfo', 84, 1542174928790);
INSERT INTO `grid_permission` VALUES (85, NULL, '添加网格信息', NULL, 'POST:/gridinfo', 85, NULL);
INSERT INTO `grid_permission` VALUES (86, NULL, '删除网格信息', NULL, 'DELETE:/gridinfo', 86, NULL);
INSERT INTO `grid_permission` VALUES (87, NULL, '修改网格信息', NULL, 'PUT:/gridinfo', 87, NULL);
INSERT INTO `grid_permission` VALUES (88, NULL, '查看网格信息', NULL, 'GET:/gridinfo', 88, NULL);
INSERT INTO `grid_permission` VALUES (89, NULL, '添加网格坐标', NULL, 'POST:/gridmap', 89, NULL);
INSERT INTO `grid_permission` VALUES (90, NULL, '删除网格坐标', NULL, 'DELETE:/gridmap', 90, NULL);
INSERT INTO `grid_permission` VALUES (91, NULL, '修改网格坐标', NULL, 'PUT:/gridmap', 91, NULL);
INSERT INTO `grid_permission` VALUES (92, NULL, '查看网格坐标', NULL, 'GET:/gridmap', 92, NULL);
INSERT INTO `grid_permission` VALUES (93, NULL, '添加文件', NULL, 'POST:/file', 93, NULL);
INSERT INTO `grid_permission` VALUES (94, NULL, '删除文件', NULL, 'DELETE:/file', 94, NULL);
INSERT INTO `grid_permission` VALUES (95, NULL, '修改文件', NULL, 'PUT:/file', 95, NULL);
INSERT INTO `grid_permission` VALUES (96, NULL, '查看文件', NULL, 'GET:/file', 96, NULL);
INSERT INTO `grid_permission` VALUES (97, NULL, '添加OCR', NULL, 'POST:/customer/ocr', 97, NULL);
INSERT INTO `grid_permission` VALUES (98, NULL, '删除OCR', NULL, 'DELETE:/customer/ocr', 98, NULL);
INSERT INTO `grid_permission` VALUES (99, NULL, '修改OCR', NULL, 'PUT:/customer/ocr', 99, NULL);
INSERT INTO `grid_permission` VALUES (100, NULL, '查看OCR', NULL, 'GET:/customer/ocr', 100, NULL);
INSERT INTO `grid_permission` VALUES (101, NULL, '添加客户', NULL, 'POST:/customer', 101, NULL);
INSERT INTO `grid_permission` VALUES (102, NULL, '删除客户', NULL, 'DELETE:/customer', 102, NULL);
INSERT INTO `grid_permission` VALUES (103, NULL, '修改客户', NULL, 'PUT:/customer', 103, NULL);
INSERT INTO `grid_permission` VALUES (104, NULL, '查看客户', NULL, 'GET:/customer', 104, NULL);
INSERT INTO `grid_permission` VALUES (107, NULL, '添加用户', NULL, 'POST:/super/account', 105, NULL);
INSERT INTO `grid_permission` VALUES (108, NULL, '删除用户', NULL, 'DELETE:/super/account', 106, NULL);
INSERT INTO `grid_permission` VALUES (109, NULL, '修改用户', NULL, 'PUT:/super/account', 107, NULL);
INSERT INTO `grid_permission` VALUES (110, NULL, '查看用户', NULL, 'GET:/super/account', 108, NULL);
INSERT INTO `grid_permission` VALUES (111, NULL, '添加角色', NULL, 'POST:/super/role', 109, NULL);
INSERT INTO `grid_permission` VALUES (112, NULL, '删除角色', NULL, 'DELETE:/super/role', 110, NULL);
INSERT INTO `grid_permission` VALUES (113, NULL, '修改角色', NULL, 'PUT:/super/role', 111, NULL);
INSERT INTO `grid_permission` VALUES (114, NULL, '查看角色', NULL, 'GET:/super/role', 112, NULL);
INSERT INTO `grid_permission` VALUES (115, NULL, '添加标签', NULL, 'POST:/super/tag', 113, NULL);
INSERT INTO `grid_permission` VALUES (116, NULL, '删除标签', NULL, 'DELETE:/super/tag', 114, NULL);
INSERT INTO `grid_permission` VALUES (117, NULL, '修改标签', NULL, 'PUT:/super/tag', 115, NULL);
INSERT INTO `grid_permission` VALUES (118, NULL, '查看标签', NULL, 'GET:/super/tag', 116, NULL);
INSERT INTO `grid_permission` VALUES (119, NULL, '添加区域', NULL, 'POST:/super/region', 117, NULL);
INSERT INTO `grid_permission` VALUES (120, NULL, '删除区域', NULL, 'DELETE:/super/region', 118, NULL);
INSERT INTO `grid_permission` VALUES (121, NULL, '修改区域', NULL, 'PUT:/super/region', 119, NULL);
INSERT INTO `grid_permission` VALUES (122, NULL, '查看区域', NULL, 'GET:/super/region', 120, NULL);
INSERT INTO `grid_permission` VALUES (123, NULL, '添加机构', NULL, 'POST:/super/org', 121, NULL);
INSERT INTO `grid_permission` VALUES (124, NULL, '删除机构', NULL, 'DELETE:/super/org', 122, NULL);
INSERT INTO `grid_permission` VALUES (125, NULL, '修改机构', NULL, 'PUT:/super/org', 123, NULL);
INSERT INTO `grid_permission` VALUES (126, NULL, '查看机构', NULL, 'GET:/super/org', 124, NULL);
INSERT INTO `grid_permission` VALUES (127, NULL, '添加字典', NULL, 'POST:/super/dictionary', 125, NULL);
INSERT INTO `grid_permission` VALUES (128, NULL, '删除字典', NULL, 'DELETE:/super/dictionary', 126, NULL);
INSERT INTO `grid_permission` VALUES (129, NULL, '修改字典', NULL, 'PUT:/super/dictionary', 127, NULL);
INSERT INTO `grid_permission` VALUES (130, NULL, '查看字典', NULL, 'GET:/super/dictionary', 128, NULL);
INSERT INTO `grid_permission` VALUES (131, NULL, '添加产品', NULL, 'POST:/super/product', 129, NULL);
INSERT INTO `grid_permission` VALUES (132, NULL, '删除产品', NULL, 'DELETE:/super/product', 130, NULL);
INSERT INTO `grid_permission` VALUES (133, NULL, '修改产品', NULL, 'PUT:/super/product', 131, NULL);
INSERT INTO `grid_permission` VALUES (134, NULL, '查看产品', NULL, 'GET:/super/product', 132, NULL);
INSERT INTO `grid_permission` VALUES (135, NULL, '添加任务', NULL, 'POST:/user/task', 133, NULL);
INSERT INTO `grid_permission` VALUES (136, NULL, '删除任务', NULL, 'DELETE:/user/task', 134, NULL);
INSERT INTO `grid_permission` VALUES (137, NULL, '修改任务', NULL, 'PUT:/user/task', 135, NULL);
INSERT INTO `grid_permission` VALUES (138, NULL, '查看任务', NULL, 'GET:/user/task', 136, NULL);
INSERT INTO `grid_permission` VALUES (139, '1', '查看工作平台', '1', 'GET:/platform/info', 1541748787660, 1541748787660);
INSERT INTO `grid_permission` VALUES (140, '1', '新建征信报告账号', '1', 'POST:/grid', 1542088560859, 1542088560859);
INSERT INTO `grid_permission` VALUES (141, '1', '删除征信报告账号', '1', 'DELETE:/grid', 1542088658466, 1542088658466);
INSERT INTO `grid_permission` VALUES (142, '1', '编辑征信报告账号', '1', 'PUT:/grid', 1542088689459, 1542088689459);
INSERT INTO `grid_permission` VALUES (143, '1', '查看征信报告账号', '1', 'GET:/grid', 1542088729315, 1542088729315);
INSERT INTO `grid_permission` VALUES (144, '1', '查看首页支行排名', '1', 'GET:/home/branchsort', 1542175365853, 1542175365853);
INSERT INTO `grid_permission` VALUES (145, '1', '查看首页客户经理排名', '1', 'GET:/home/accountsort', 1542175407550, 1542175407550);

SET FOREIGN_KEY_CHECKS = 1;
