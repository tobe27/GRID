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

 Date: 30/11/2018 18:08:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for credit_report_account
-- ----------------------------
DROP TABLE IF EXISTS `credit_report_account`;
CREATE TABLE `credit_report_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `org_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构名',
  `org_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '机构号',
  `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态 0启用 1冻结 2删除',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `remind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提醒',
  `created_at` bigint(50) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` bigint(50) NULL DEFAULT NULL COMMENT '修改时间',
  `type` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号类型 0 是查询账号 1是审批账号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_back_to_back_pre_credit
-- ----------------------------
DROP TABLE IF EXISTS `customer_back_to_back_pre_credit`;
CREATE TABLE `customer_back_to_back_pre_credit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `customer_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0.00' COMMENT '客户姓名',
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格编号',
  `phone_number` bigint(20) NULL DEFAULT NULL COMMENT '联系方式',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `income` decimal(20, 2) NULL DEFAULT NULL COMMENT '收入',
  `spending` decimal(20, 2) NULL DEFAULT NULL COMMENT '支出',
  `interest_rate` decimal(20, 4) NULL DEFAULT NULL COMMENT '利率',
  `rental` decimal(20, 2) NOT NULL COMMENT '授信总额',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `updated_at` bigint(50) NOT NULL COMMENT '修改时间 ',
  `created_at` bigint(50) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `customer_blacklist`;
CREATE TABLE `customer_blacklist`  (
  `id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `phone_number` bigint(20) NULL DEFAULT NULL COMMENT '联系方式',
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格编号',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '未授信原因',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` bigint(50) NOT NULL COMMENT '创建时间',
  `updated_at` bigint(50) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_business
-- ----------------------------
DROP TABLE IF EXISTS `customer_business`;
CREATE TABLE `customer_business`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deposit_total` decimal(18, 2) NULL DEFAULT NULL,
  `deposit_month` decimal(18, 2) NULL DEFAULT NULL,
  `deposit_season` decimal(18, 2) NULL DEFAULT NULL,
  `deposit_year` decimal(18, 2) NULL DEFAULT NULL,
  `deposit_time_poit` decimal(18, 2) NULL DEFAULT NULL,
  `current_total` decimal(18, 2) NULL DEFAULT NULL,
  `current_month` decimal(18, 2) NULL DEFAULT NULL,
  `current_season` decimal(18, 2) NULL DEFAULT NULL,
  `current_year` decimal(18, 2) NULL DEFAULT NULL,
  `current_time_poit` decimal(18, 2) NULL DEFAULT NULL,
  `fixed_total` decimal(18, 2) NULL DEFAULT NULL,
  `fixed_month` decimal(18, 2) NULL DEFAULT NULL,
  `fixed_season` decimal(18, 2) NULL DEFAULT NULL,
  `fixed_year` decimal(18, 2) NULL DEFAULT NULL,
  `fixed_time_poit` decimal(18, 2) NULL DEFAULT NULL,
  `created_at` bigint(20) NULL DEFAULT NULL,
  `updated_at` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_business_product
-- ----------------------------
DROP TABLE IF EXISTS `customer_business_product`;
CREATE TABLE `customer_business_product`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_code` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `card_number` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rate` decimal(4, 2) NULL DEFAULT NULL,
  `money` decimal(20, 2) NULL DEFAULT NULL,
  `created_at` bigint(20) NULL DEFAULT NULL,
  `updated_at` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_credit_adviser
-- ----------------------------
DROP TABLE IF EXISTS `customer_credit_adviser`;
CREATE TABLE `customer_credit_adviser`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户姓名',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户身份证号',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系地址',
  `income` decimal(10, 2) NULL DEFAULT NULL COMMENT '收入',
  `expenditure` decimal(10, 2) NULL DEFAULT NULL COMMENT '支出',
  `rental` decimal(10, 2) NULL DEFAULT NULL COMMENT '授信额度',
  `grid_code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格编号',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `adviser_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '金融顾问姓名',
  `signing_time` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '签字时间',
  `credted_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0为正常1为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_credit_approval
-- ----------------------------
DROP TABLE IF EXISTS `customer_credit_approval`;
CREATE TABLE `customer_credit_approval`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户身份证号',
  `approval_opinion` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批意见',
  `approval_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批人姓名',
  `approval_account_id` bigint(20) NOT NULL COMMENT '审批人账号id',
  `signatureURL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批人签名图片URL',
  `created_at` bigint(50) NOT NULL COMMENT '创建时间',
  `updated_at` bigint(50) NOT NULL COMMENT '修改时间',
  `approval_result` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '审批结果   0通过  1拒绝',
  `approval_node` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '1.客户经理 2.会计 3.支行长',
  `credit_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '面谈面签记录id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 377 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_credit_detail
-- ----------------------------
DROP TABLE IF EXISTS `customer_credit_detail`;
CREATE TABLE `customer_credit_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `customer_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户姓名',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格编号',
  `begin_at` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授信开始日期',
  `end_at` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授信结束日期',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` bigint(30) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` bigint(30) NULL DEFAULT NULL COMMENT '修改时间',
  `approval_status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT ' 审批状态   0.客户经理待提交 1.客户经理已提交 3.会计同意 4.会计驳回',
  `postil` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批批次',
  `attach_flag` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0.0' COMMENT '附件上传标志  \r\n第一个0代表 合同   0为未上传 1为已上传\r\n第二个0代表 面签表和征信查询授权书  0为未上传  1为已上传',
  `customer_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户编号',
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `spouse_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配偶姓名',
  `spouse_id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配偶身份证号',
  `education_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教育水平',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `member_count` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭人数',
  `company_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位名称',
  `company_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单位地址',
  `native_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '户籍地址',
  `residence_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '常驻地址',
  `jnyd_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '金农易贷户名',
  `jnyd_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '金农易贷账（卡）号',
  `rental` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授信金额',
  `deadline` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授信期限',
  `rate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借款利率类型',
  `year_rate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '年利率',
  `rate_float` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浮动利率',
  `rate_update_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '利率调整日',
  `disbursement` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发放方式',
  `refund_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '还款方式',
  `credit_use_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借款用途',
  `income` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人年收入',
  `family_income` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭年收入',
  `advisers_flag` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否有金融顾问意见',
  `advisers_income` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '收入（金融顾问意见）',
  `advisers_expense` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支出（金融顾问意见）',
  `advisers_rental` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授信额度（金融顾问意见）',
  `advisers_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '金融顾问姓名',
  `advisers_comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注（金融顾问）',
  `true_customer_info` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户资料是否真实',
  `true_customer_talk` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '谈话内容是否真实',
  `staff_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '银行受理人员姓名',
  `staff_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '银行受理人员工号',
  `talk_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '面谈面签日期',
  `grid_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格名称',
  `account_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登记人',
  `account_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登记人账号',
  `org_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登记机构名称',
  `org_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登记机构编号',
  `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '数据状态 0为正常  1为删除 2为临时数据',
  `credit_report` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '征信报告地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 248 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_credit_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_credit_info`;
CREATE TABLE `customer_credit_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `incidence_of_letter` decimal(18, 2) NULL DEFAULT NULL COMMENT '用信发生额',
  `housing_mortgage_debt` decimal(18, 2) NULL DEFAULT NULL COMMENT '房屋按揭（负债）',
  `business_loan_debt` decimal(18, 2) NULL DEFAULT NULL COMMENT '经营贷款（负债）',
  `consumer_loan_debt` decimal(18, 2) NULL DEFAULT NULL COMMENT '消费贷款（负债）',
  `housing_mortgage_remainder` decimal(18, 2) NULL DEFAULT NULL COMMENT '房屋按揭（贷款余额）',
  `business_loan_remainder` decimal(18, 2) NULL DEFAULT NULL COMMENT '经营贷款（贷款余额）',
  `consumer_loans_remainder` decimal(18, 2) NULL DEFAULT NULL COMMENT '消费贷款（贷款余额）',
  `overdue_record` int(11) NULL DEFAULT NULL COMMENT '逾期记录',
  `past_due` decimal(18, 2) NULL DEFAULT NULL COMMENT '逾期金额',
  `basic_identity` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '基本身份（目前记录客户姓名）',
  `address1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址1',
  `address2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址2',
  `address3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址3',
  `mate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配偶',
  `neighborhood_relation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '家庭及邻里关系',
  `have_bad_habits` int(11) NULL DEFAULT NULL COMMENT '有无涉黄、赌、毒等不良嗜好(0无1有)',
  `have_bad_credit` int(11) NULL DEFAULT NULL COMMENT '有无民间债务纠纷或者民间信用不良(0无1有)',
  `is_dishonest` int(11) NULL DEFAULT NULL COMMENT '是否是失信人(0否1是)',
  `create_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_greylist
-- ----------------------------
DROP TABLE IF EXISTS `customer_greylist`;
CREATE TABLE `customer_greylist`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `phone_number` bigint(20) NULL DEFAULT NULL COMMENT '联系方式',
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格编号',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '观察原因',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` bigint(50) NOT NULL COMMENT '创建时间',
  `updated_at` bigint(50) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_image
-- ----------------------------
DROP TABLE IF EXISTS `customer_image`;
CREATE TABLE `customer_image`  (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名\r\n',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格编号',
  `original_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '原文件名',
  `system_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传后名字',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传后的路径',
  `upload_account_id` bigint(25) NULL DEFAULT NULL COMMENT '上传人的ID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '图片类型 1.基本信息（身份证等）2.资产信息 3.经营信息4.贷款流程信息（合同.面签表）5.押品信息 6.其他信息',
  `delete_flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '删除标志  0为未删除，1为删除',
  `delete_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '删除后的路径',
  `delete_time` bigint(25) NULL DEFAULT NULL COMMENT '删除时间',
  `created_at` bigint(25) NOT NULL COMMENT '创建时间',
  `updated_at` bigint(25) NOT NULL COMMENT '修改时间',
  `credit_detail_id` bigint(20) NULL DEFAULT NULL COMMENT '面谈面签记录id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 340121201704195277 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info`  (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `marital_status` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `education_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nation` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `native_place` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `custodian` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `household_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `native_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `residence_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 2,
  `politics_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `living_situation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `physical_condition` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nationality` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `borrower_nature` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `postcode` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_stockholder` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_staff` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_owner` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_buy_house` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cell_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cell_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `credit_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `career` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `work_year` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_farmer` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `investigator1` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `investigator2` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `registrant` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `register_org` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE,
  UNIQUE INDEX `uk_id_number`(`id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1158 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_poor_info
-- ----------------------------
DROP TABLE IF EXISTS `customer_poor_info`;
CREATE TABLE `customer_poor_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `person_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人员编号',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `person_count` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人数',
  `internal_student` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '在校生状况',
  `health_condition` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 健康状况',
  `labor_skills` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '劳动技能',
  `working_conditions` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 务工状况',
  `working_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '务工时间（月',
  `critical_illness_insurance` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '参加大病医疗',
  `overcome_poverty` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '脱贫属性',
  `overcome_poverty_year` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '脱贫年度',
  `attribute` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 贫困户属性',
  `reason` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主要致贫原因',
  `dangerous_building` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 危房户',
  `safety_water` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '饮水安全',
  `dysdipsia` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT ' 饮水困难',
  `income_avg` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人均纯收入 ',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `write_ime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '记录时间',
  `status` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT ' 数据状态 0为正常 1为删除',
  `created_at` bigint(20) NOT NULL COMMENT '创建时间',
  `update_at` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_whitelist
-- ----------------------------
DROP TABLE IF EXISTS `customer_whitelist`;
CREATE TABLE `customer_whitelist`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `phone_number` bigint(20) NULL DEFAULT NULL COMMENT '联系方式',
  `grid_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格编号',
  `comment` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `created_at` bigint(50) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_at` bigint(50) NULL DEFAULT NULL COMMENT '修改时间',
  `adress` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 181 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dishonest_customer_info
-- ----------------------------
DROP TABLE IF EXISTS `dishonest_customer_info`;
CREATE TABLE `dishonest_customer_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `performed_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `card_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `area_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `court_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gist_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `register_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `case_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gist_institution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `duty` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `performance` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `concrete_reason` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `published_at` bigint(20) NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 199 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for family_info
-- ----------------------------
DROP TABLE IF EXISTS `family_info`;
CREATE TABLE `family_info`  (
  `family_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `household_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `population` int(11) NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `postcode` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `local_credit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_harmony` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `social_evaluation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_self_entity` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_owe_tax` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `produce_capacity` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `produce_scene` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `insurance_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`family_id`) USING BTREE,
  UNIQUE INDEX `uk_household_id`(`household_id`) USING BTREE COMMENT '户号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 144 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for family_member
-- ----------------------------
DROP TABLE IF EXISTS `family_member`;
CREATE TABLE `family_member`  (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `relationship` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nation` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_household_head` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 1,
  `customer_id` bigint(20) NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2081 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file_status
-- ----------------------------
DROP TABLE IF EXISTS `file_status`;
CREATE TABLE `file_status`  (
  `serialno` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `taskname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `taskdate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `filename` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `starttime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `endtime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `filenote` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`serialno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for finance_info
-- ----------------------------
DROP TABLE IF EXISTS `finance_info`;
CREATE TABLE `finance_info`  (
  `finance_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `industry` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `career` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `company_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `work_year` int(11) NULL DEFAULT NULL,
  `house_number` int(11) NULL DEFAULT NULL,
  `house_price` double(18, 2) NULL DEFAULT NULL,
  `car_number` int(11) NULL DEFAULT NULL,
  `car_price` double(18, 2) NULL DEFAULT NULL,
  `year_salary` double(18, 2) NULL DEFAULT NULL,
  `shop_number` int(11) NULL DEFAULT NULL,
  `shop_price` double(18, 2) NULL DEFAULT NULL,
  `equipment_number` int(11) NULL DEFAULT NULL,
  `equipment_price` double(18, 2) NULL DEFAULT NULL,
  `year_surplus` double(18, 2) NULL DEFAULT NULL,
  `land_acreage` double(18, 2) NULL DEFAULT NULL,
  `greenhouse_number` int(11) NULL DEFAULT NULL,
  `breed_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `breed_number` int(11) NULL DEFAULT NULL,
  `company_earnings` double(18, 2) NULL DEFAULT NULL,
  `other` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  `business_license` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_business_income` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_farm_income` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`finance_id`) USING BTREE,
  UNIQUE INDEX `uk_id_number`(`id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ftp_file
-- ----------------------------
DROP TABLE IF EXISTS `ftp_file`;
CREATE TABLE `ftp_file`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `filedir` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `flgfiles` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `jarfiles` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `txtfiles` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isinuse` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grid_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `grid_dictionary`;
CREATE TABLE `grid_dictionary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增的序号',
  `type` int(20) NULL DEFAULT NULL COMMENT '所属参数类型',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上级id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典组编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典组名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典组的描述',
  `isenable` int(20) NULL DEFAULT NULL COMMENT '是否启用',
  `dictionary_key` bigint(20) NULL DEFAULT NULL COMMENT '字典项的key',
  `dictionary_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典项的value',
  `creat_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `status` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0为未删除 1为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for grid_map
-- ----------------------------
DROP TABLE IF EXISTS `grid_map`;
CREATE TABLE `grid_map`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `grid_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网格编号',
  `grid_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '坐标类型',
  `grand_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地图一级标记',
  `parent_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地图二级标记',
  `coordinate` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '具体坐标',
  `created_at` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_at` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `delete_flag` int(5) NULL DEFAULT 0 COMMENT '删除标记 0为正常1为删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 93 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for grid_region
-- ----------------------------
DROP TABLE IF EXISTS `grid_region`;
CREATE TABLE `grid_region`  (
  `region_code` bigint(20) NOT NULL AUTO_INCREMENT,
  `region_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pre_region_code` bigint(20) NULL DEFAULT NULL,
  `region_level` int(11) NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`region_code`) USING BTREE,
  UNIQUE INDEX `uk_region_name`(`region_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grid_review
-- ----------------------------
DROP TABLE IF EXISTS `grid_review`;
CREATE TABLE `grid_review`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `grid_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格编号',
  `grid_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格名称',
  `grid_review_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评议小组成员姓名',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `duties` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职务',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `created_at` bigint(20) NOT NULL COMMENT '创建日期',
  `updated_at` bigint(20) NULL DEFAULT NULL COMMENT '修改日期',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '1正式 2备选',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0正常1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 325 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grid_review_grid
-- ----------------------------
DROP TABLE IF EXISTS `grid_review_grid`;
CREATE TABLE `grid_review_grid`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网格号',
  `grid_review_id` bigint(20) NULL DEFAULT NULL COMMENT '评议员编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for grid_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `grid_role_permission`;
CREATE TABLE `grid_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `corp_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '法人编号',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2389 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_role_permission
-- ----------------------------
INSERT INTO `grid_role_permission` VALUES (2006, NULL, 3, 4);
INSERT INTO `grid_role_permission` VALUES (2007, NULL, 3, 139);
INSERT INTO `grid_role_permission` VALUES (2008, NULL, 3, 138);
INSERT INTO `grid_role_permission` VALUES (2009, NULL, 3, 137);
INSERT INTO `grid_role_permission` VALUES (2010, NULL, 3, 136);
INSERT INTO `grid_role_permission` VALUES (2011, NULL, 3, 135);
INSERT INTO `grid_role_permission` VALUES (2012, NULL, 3, 126);
INSERT INTO `grid_role_permission` VALUES (2013, NULL, 3, 122);
INSERT INTO `grid_role_permission` VALUES (2014, NULL, 3, 118);
INSERT INTO `grid_role_permission` VALUES (2015, NULL, 3, 114);
INSERT INTO `grid_role_permission` VALUES (2016, NULL, 3, 110);
INSERT INTO `grid_role_permission` VALUES (2017, NULL, 3, 104);
INSERT INTO `grid_role_permission` VALUES (2018, NULL, 3, 92);
INSERT INTO `grid_role_permission` VALUES (2019, NULL, 3, 88);
INSERT INTO `grid_role_permission` VALUES (2020, NULL, 3, 84);
INSERT INTO `grid_role_permission` VALUES (2021, NULL, 3, 83);
INSERT INTO `grid_role_permission` VALUES (2022, NULL, 3, 82);
INSERT INTO `grid_role_permission` VALUES (2023, NULL, 3, 81);
INSERT INTO `grid_role_permission` VALUES (2024, NULL, 3, 80);
INSERT INTO `grid_role_permission` VALUES (2025, NULL, 3, 79);
INSERT INTO `grid_role_permission` VALUES (2026, NULL, 3, 78);
INSERT INTO `grid_role_permission` VALUES (2027, NULL, 3, 77);
INSERT INTO `grid_role_permission` VALUES (2028, NULL, 3, 76);
INSERT INTO `grid_role_permission` VALUES (2029, NULL, 3, 75);
INSERT INTO `grid_role_permission` VALUES (2030, NULL, 3, 74);
INSERT INTO `grid_role_permission` VALUES (2031, NULL, 3, 73);
INSERT INTO `grid_role_permission` VALUES (2032, NULL, 3, 72);
INSERT INTO `grid_role_permission` VALUES (2033, NULL, 3, 71);
INSERT INTO `grid_role_permission` VALUES (2034, NULL, 3, 70);
INSERT INTO `grid_role_permission` VALUES (2035, NULL, 3, 69);
INSERT INTO `grid_role_permission` VALUES (2036, NULL, 3, 44);
INSERT INTO `grid_role_permission` VALUES (2037, NULL, 3, 36);
INSERT INTO `grid_role_permission` VALUES (2038, NULL, 3, 32);
INSERT INTO `grid_role_permission` VALUES (2039, NULL, 3, 28);
INSERT INTO `grid_role_permission` VALUES (2040, NULL, 3, 24);
INSERT INTO `grid_role_permission` VALUES (2041, NULL, 3, 20);
INSERT INTO `grid_role_permission` VALUES (2042, NULL, 3, 16);
INSERT INTO `grid_role_permission` VALUES (2043, NULL, 3, 12);
INSERT INTO `grid_role_permission` VALUES (2044, NULL, 3, 8);
INSERT INTO `grid_role_permission` VALUES (2204, NULL, 1, 143);
INSERT INTO `grid_role_permission` VALUES (2205, NULL, 1, 4);
INSERT INTO `grid_role_permission` VALUES (2206, NULL, 1, 139);
INSERT INTO `grid_role_permission` VALUES (2207, NULL, 1, 114);
INSERT INTO `grid_role_permission` VALUES (2208, NULL, 1, 110);
INSERT INTO `grid_role_permission` VALUES (2209, NULL, 1, 138);
INSERT INTO `grid_role_permission` VALUES (2210, NULL, 1, 134);
INSERT INTO `grid_role_permission` VALUES (2211, NULL, 1, 130);
INSERT INTO `grid_role_permission` VALUES (2212, NULL, 1, 126);
INSERT INTO `grid_role_permission` VALUES (2213, NULL, 1, 122);
INSERT INTO `grid_role_permission` VALUES (2214, NULL, 1, 118);
INSERT INTO `grid_role_permission` VALUES (2215, NULL, 1, 5);
INSERT INTO `grid_role_permission` VALUES (2216, NULL, 1, 6);
INSERT INTO `grid_role_permission` VALUES (2217, NULL, 1, 7);
INSERT INTO `grid_role_permission` VALUES (2218, NULL, 1, 8);
INSERT INTO `grid_role_permission` VALUES (2219, NULL, 1, 9);
INSERT INTO `grid_role_permission` VALUES (2220, NULL, 1, 10);
INSERT INTO `grid_role_permission` VALUES (2221, NULL, 1, 11);
INSERT INTO `grid_role_permission` VALUES (2222, NULL, 1, 12);
INSERT INTO `grid_role_permission` VALUES (2223, NULL, 1, 13);
INSERT INTO `grid_role_permission` VALUES (2224, NULL, 1, 14);
INSERT INTO `grid_role_permission` VALUES (2225, NULL, 1, 15);
INSERT INTO `grid_role_permission` VALUES (2226, NULL, 1, 16);
INSERT INTO `grid_role_permission` VALUES (2227, NULL, 1, 17);
INSERT INTO `grid_role_permission` VALUES (2228, NULL, 1, 18);
INSERT INTO `grid_role_permission` VALUES (2229, NULL, 1, 19);
INSERT INTO `grid_role_permission` VALUES (2230, NULL, 1, 20);
INSERT INTO `grid_role_permission` VALUES (2231, NULL, 1, 21);
INSERT INTO `grid_role_permission` VALUES (2232, NULL, 1, 22);
INSERT INTO `grid_role_permission` VALUES (2233, NULL, 1, 23);
INSERT INTO `grid_role_permission` VALUES (2234, NULL, 1, 24);
INSERT INTO `grid_role_permission` VALUES (2235, NULL, 1, 25);
INSERT INTO `grid_role_permission` VALUES (2236, NULL, 1, 26);
INSERT INTO `grid_role_permission` VALUES (2237, NULL, 1, 27);
INSERT INTO `grid_role_permission` VALUES (2238, NULL, 1, 28);
INSERT INTO `grid_role_permission` VALUES (2239, NULL, 1, 29);
INSERT INTO `grid_role_permission` VALUES (2240, NULL, 1, 30);
INSERT INTO `grid_role_permission` VALUES (2241, NULL, 1, 31);
INSERT INTO `grid_role_permission` VALUES (2242, NULL, 1, 32);
INSERT INTO `grid_role_permission` VALUES (2243, NULL, 1, 33);
INSERT INTO `grid_role_permission` VALUES (2244, NULL, 1, 34);
INSERT INTO `grid_role_permission` VALUES (2245, NULL, 1, 35);
INSERT INTO `grid_role_permission` VALUES (2246, NULL, 1, 36);
INSERT INTO `grid_role_permission` VALUES (2247, NULL, 1, 40);
INSERT INTO `grid_role_permission` VALUES (2248, NULL, 1, 41);
INSERT INTO `grid_role_permission` VALUES (2249, NULL, 1, 42);
INSERT INTO `grid_role_permission` VALUES (2250, NULL, 1, 43);
INSERT INTO `grid_role_permission` VALUES (2251, NULL, 1, 44);
INSERT INTO `grid_role_permission` VALUES (2252, NULL, 1, 45);
INSERT INTO `grid_role_permission` VALUES (2253, NULL, 1, 46);
INSERT INTO `grid_role_permission` VALUES (2254, NULL, 1, 47);
INSERT INTO `grid_role_permission` VALUES (2255, NULL, 1, 48);
INSERT INTO `grid_role_permission` VALUES (2256, NULL, 1, 49);
INSERT INTO `grid_role_permission` VALUES (2257, NULL, 1, 50);
INSERT INTO `grid_role_permission` VALUES (2258, NULL, 1, 51);
INSERT INTO `grid_role_permission` VALUES (2259, NULL, 1, 52);
INSERT INTO `grid_role_permission` VALUES (2260, NULL, 1, 53);
INSERT INTO `grid_role_permission` VALUES (2261, NULL, 1, 54);
INSERT INTO `grid_role_permission` VALUES (2262, NULL, 1, 55);
INSERT INTO `grid_role_permission` VALUES (2263, NULL, 1, 56);
INSERT INTO `grid_role_permission` VALUES (2264, NULL, 1, 57);
INSERT INTO `grid_role_permission` VALUES (2265, NULL, 1, 58);
INSERT INTO `grid_role_permission` VALUES (2266, NULL, 1, 59);
INSERT INTO `grid_role_permission` VALUES (2267, NULL, 1, 60);
INSERT INTO `grid_role_permission` VALUES (2268, NULL, 1, 61);
INSERT INTO `grid_role_permission` VALUES (2269, NULL, 1, 62);
INSERT INTO `grid_role_permission` VALUES (2270, NULL, 1, 63);
INSERT INTO `grid_role_permission` VALUES (2271, NULL, 1, 64);
INSERT INTO `grid_role_permission` VALUES (2272, NULL, 1, 65);
INSERT INTO `grid_role_permission` VALUES (2273, NULL, 1, 66);
INSERT INTO `grid_role_permission` VALUES (2274, NULL, 1, 67);
INSERT INTO `grid_role_permission` VALUES (2275, NULL, 1, 68);
INSERT INTO `grid_role_permission` VALUES (2276, NULL, 1, 72);
INSERT INTO `grid_role_permission` VALUES (2277, NULL, 1, 76);
INSERT INTO `grid_role_permission` VALUES (2278, NULL, 1, 80);
INSERT INTO `grid_role_permission` VALUES (2279, NULL, 1, 84);
INSERT INTO `grid_role_permission` VALUES (2280, NULL, 1, 88);
INSERT INTO `grid_role_permission` VALUES (2281, NULL, 1, 92);
INSERT INTO `grid_role_permission` VALUES (2282, NULL, 1, 93);
INSERT INTO `grid_role_permission` VALUES (2283, NULL, 1, 94);
INSERT INTO `grid_role_permission` VALUES (2284, NULL, 1, 95);
INSERT INTO `grid_role_permission` VALUES (2285, NULL, 1, 96);
INSERT INTO `grid_role_permission` VALUES (2286, NULL, 1, 97);
INSERT INTO `grid_role_permission` VALUES (2287, NULL, 1, 98);
INSERT INTO `grid_role_permission` VALUES (2288, NULL, 1, 99);
INSERT INTO `grid_role_permission` VALUES (2289, NULL, 1, 100);
INSERT INTO `grid_role_permission` VALUES (2290, NULL, 1, 101);
INSERT INTO `grid_role_permission` VALUES (2291, NULL, 1, 102);
INSERT INTO `grid_role_permission` VALUES (2292, NULL, 1, 103);
INSERT INTO `grid_role_permission` VALUES (2293, NULL, 1, 104);
INSERT INTO `grid_role_permission` VALUES (2294, NULL, 2, 72);
INSERT INTO `grid_role_permission` VALUES (2295, NULL, 2, 4);
INSERT INTO `grid_role_permission` VALUES (2296, NULL, 2, 139);
INSERT INTO `grid_role_permission` VALUES (2297, NULL, 2, 61);
INSERT INTO `grid_role_permission` VALUES (2298, NULL, 2, 110);
INSERT INTO `grid_role_permission` VALUES (2299, NULL, 2, 32);
INSERT INTO `grid_role_permission` VALUES (2300, NULL, 2, 118);
INSERT INTO `grid_role_permission` VALUES (2301, NULL, 2, 134);
INSERT INTO `grid_role_permission` VALUES (2302, NULL, 2, 138);
INSERT INTO `grid_role_permission` VALUES (2303, NULL, 2, 126);
INSERT INTO `grid_role_permission` VALUES (2304, NULL, 2, 96);
INSERT INTO `grid_role_permission` VALUES (2305, NULL, 2, 104);
INSERT INTO `grid_role_permission` VALUES (2306, NULL, 2, 92);
INSERT INTO `grid_role_permission` VALUES (2307, NULL, 2, 88);
INSERT INTO `grid_role_permission` VALUES (2308, NULL, 2, 68);
INSERT INTO `grid_role_permission` VALUES (2309, NULL, 2, 64);
INSERT INTO `grid_role_permission` VALUES (2310, NULL, 2, 60);
INSERT INTO `grid_role_permission` VALUES (2311, NULL, 2, 56);
INSERT INTO `grid_role_permission` VALUES (2312, NULL, 2, 52);
INSERT INTO `grid_role_permission` VALUES (2313, NULL, 2, 48);
INSERT INTO `grid_role_permission` VALUES (2314, NULL, 2, 8);
INSERT INTO `grid_role_permission` VALUES (2315, NULL, 2, 28);
INSERT INTO `grid_role_permission` VALUES (2316, NULL, 7, 72);
INSERT INTO `grid_role_permission` VALUES (2317, NULL, 7, 143);
INSERT INTO `grid_role_permission` VALUES (2318, NULL, 7, 142);
INSERT INTO `grid_role_permission` VALUES (2319, NULL, 7, 141);
INSERT INTO `grid_role_permission` VALUES (2320, NULL, 7, 140);
INSERT INTO `grid_role_permission` VALUES (2321, NULL, 7, 139);
INSERT INTO `grid_role_permission` VALUES (2322, NULL, 7, 39);
INSERT INTO `grid_role_permission` VALUES (2323, NULL, 7, 38);
INSERT INTO `grid_role_permission` VALUES (2324, NULL, 7, 37);
INSERT INTO `grid_role_permission` VALUES (2325, NULL, 7, 40);
INSERT INTO `grid_role_permission` VALUES (2326, NULL, 7, 88);
INSERT INTO `grid_role_permission` VALUES (2327, NULL, 7, 107);
INSERT INTO `grid_role_permission` VALUES (2328, NULL, 7, 108);
INSERT INTO `grid_role_permission` VALUES (2329, NULL, 7, 109);
INSERT INTO `grid_role_permission` VALUES (2330, NULL, 7, 110);
INSERT INTO `grid_role_permission` VALUES (2331, NULL, 7, 111);
INSERT INTO `grid_role_permission` VALUES (2332, NULL, 7, 112);
INSERT INTO `grid_role_permission` VALUES (2333, NULL, 7, 113);
INSERT INTO `grid_role_permission` VALUES (2334, NULL, 7, 114);
INSERT INTO `grid_role_permission` VALUES (2335, NULL, 7, 115);
INSERT INTO `grid_role_permission` VALUES (2336, NULL, 7, 116);
INSERT INTO `grid_role_permission` VALUES (2337, NULL, 7, 117);
INSERT INTO `grid_role_permission` VALUES (2338, NULL, 7, 118);
INSERT INTO `grid_role_permission` VALUES (2339, NULL, 7, 119);
INSERT INTO `grid_role_permission` VALUES (2340, NULL, 7, 120);
INSERT INTO `grid_role_permission` VALUES (2341, NULL, 7, 121);
INSERT INTO `grid_role_permission` VALUES (2342, NULL, 7, 122);
INSERT INTO `grid_role_permission` VALUES (2343, NULL, 7, 123);
INSERT INTO `grid_role_permission` VALUES (2344, NULL, 7, 124);
INSERT INTO `grid_role_permission` VALUES (2345, NULL, 7, 125);
INSERT INTO `grid_role_permission` VALUES (2346, NULL, 7, 126);
INSERT INTO `grid_role_permission` VALUES (2347, NULL, 7, 127);
INSERT INTO `grid_role_permission` VALUES (2348, NULL, 7, 128);
INSERT INTO `grid_role_permission` VALUES (2349, NULL, 7, 129);
INSERT INTO `grid_role_permission` VALUES (2350, NULL, 7, 130);
INSERT INTO `grid_role_permission` VALUES (2351, NULL, 7, 131);
INSERT INTO `grid_role_permission` VALUES (2352, NULL, 7, 132);
INSERT INTO `grid_role_permission` VALUES (2353, NULL, 7, 133);
INSERT INTO `grid_role_permission` VALUES (2354, NULL, 7, 134);
INSERT INTO `grid_role_permission` VALUES (2355, NULL, 7, 92);
INSERT INTO `grid_role_permission` VALUES (2356, NULL, 7, 91);
INSERT INTO `grid_role_permission` VALUES (2357, NULL, 7, 90);
INSERT INTO `grid_role_permission` VALUES (2358, NULL, 7, 89);
INSERT INTO `grid_role_permission` VALUES (2359, NULL, 7, 87);
INSERT INTO `grid_role_permission` VALUES (2360, NULL, 7, 86);
INSERT INTO `grid_role_permission` VALUES (2361, NULL, 7, 85);
INSERT INTO `grid_role_permission` VALUES (2362, NULL, 7, 1);
INSERT INTO `grid_role_permission` VALUES (2363, NULL, 7, 2);
INSERT INTO `grid_role_permission` VALUES (2364, NULL, 7, 3);
INSERT INTO `grid_role_permission` VALUES (2365, NULL, 7, 4);
INSERT INTO `grid_role_permission` VALUES (2366, NULL, 5, 88);
INSERT INTO `grid_role_permission` VALUES (2367, NULL, 5, 92);
INSERT INTO `grid_role_permission` VALUES (2368, NULL, 5, 4);
INSERT INTO `grid_role_permission` VALUES (2369, NULL, 5, 139);
INSERT INTO `grid_role_permission` VALUES (2370, NULL, 5, 138);
INSERT INTO `grid_role_permission` VALUES (2371, NULL, 5, 114);
INSERT INTO `grid_role_permission` VALUES (2372, NULL, 5, 110);
INSERT INTO `grid_role_permission` VALUES (2373, NULL, 5, 84);
INSERT INTO `grid_role_permission` VALUES (2374, NULL, 5, 80);
INSERT INTO `grid_role_permission` VALUES (2375, NULL, 5, 76);
INSERT INTO `grid_role_permission` VALUES (2376, NULL, 5, 72);

-- ----------------------------
-- Table structure for grid_senator
-- ----------------------------
DROP TABLE IF EXISTS `grid_senator`;
CREATE TABLE `grid_senator`  (
  `senator_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `duty` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_formal` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`senator_id`) USING BTREE,
  UNIQUE INDEX `uk_id_number`(`id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grid_senator_relation
-- ----------------------------
DROP TABLE IF EXISTS `grid_senator_relation`;
CREATE TABLE `grid_senator_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senator_id` bigint(20) NOT NULL,
  `grid_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_senator_id`(`senator_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grid_user
-- ----------------------------
DROP TABLE IF EXISTS `grid_user`;
CREATE TABLE `grid_user`  (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `phone_number` bigint(20) NOT NULL,
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qr_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `corp_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `org_code` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 1,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`account_id`) USING BTREE,
  UNIQUE INDEX `uk_account_name`(`account_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_user
-- ----------------------------
INSERT INTO `grid_user` VALUES (1, 'admin', 'a01f3f6ccf0ab748899ebf8174a7a753', '管理员', '342224199910120895', 1, 15555413245, 'yzsbank@sina.com', '', '', '58731948', '', 51314, 1, 1541671035838, 1542595452437);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grid_user_role
-- ----------------------------
INSERT INTO `grid_user_role` VALUES (1, NULL, 1, 7);

-- ----------------------------
-- Table structure for home_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `home_basic_info`;
CREATE TABLE `home_basic_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增序号',
  `family_num` bigint(20) NOT NULL COMMENT '总户数',
  `people_num` bigint(20) NOT NULL COMMENT '总人数',
  `credit_ratio` decimal(20, 2) NULL DEFAULT NULL COMMENT '预授信覆盖率',
  `credit_num` bigint(20) NULL DEFAULT NULL COMMENT '预授信人数',
  `use_ratio` bigint(20) NULL DEFAULT NULL COMMENT '用信率',
  `use_num` bigint(20) NULL DEFAULT NULL COMMENT '用信人数',
  `org_code` bigint(20) NOT NULL COMMENT '机构号',
  `use_promote` decimal(20, 2) NULL DEFAULT NULL COMMENT '用信提升',
  `account_id` bigint(20) NULL DEFAULT NULL COMMENT '客户经理id',
  `craet_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deposit` decimal(20, 2) NULL DEFAULT NULL COMMENT '存款',
  `loan` decimal(20, 2) NULL DEFAULT NULL COMMENT '贷款',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for home_branch_info
-- ----------------------------
DROP TABLE IF EXISTS `home_branch_info`;
CREATE TABLE `home_branch_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增序号',
  `family_num` bigint(20) NOT NULL COMMENT '总户数',
  `people_num` bigint(20) NOT NULL COMMENT '总人数',
  `credit_ratio` decimal(20, 2) NULL DEFAULT NULL COMMENT '预授信覆盖率',
  `credit_num` bigint(20) NULL DEFAULT NULL COMMENT '预授信人数',
  `use_ratio` bigint(20) NULL DEFAULT NULL COMMENT '用信率',
  `use_num` bigint(20) NULL DEFAULT NULL COMMENT '用信人数',
  `org_code` bigint(20) NOT NULL COMMENT '机构号',
  `use_promote` decimal(20, 2) NULL DEFAULT NULL COMMENT '用信提升',
  `craet_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deposit` decimal(20, 2) NULL DEFAULT NULL COMMENT '存款',
  `loan` decimal(20, 2) NULL DEFAULT NULL COMMENT '贷款',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for home_middle_info
-- ----------------------------
DROP TABLE IF EXISTS `home_middle_info`;
CREATE TABLE `home_middle_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增序号',
  `family_num` bigint(20) NOT NULL COMMENT '总户数',
  `people_num` bigint(20) NOT NULL COMMENT '总人数',
  `credit_ratio` decimal(20, 2) NULL DEFAULT NULL COMMENT '预授信覆盖率',
  `credit_num` bigint(20) NULL DEFAULT NULL COMMENT '预授信人数',
  `use_ratio` bigint(20) NULL DEFAULT NULL COMMENT '用信率',
  `use_num` bigint(20) NULL DEFAULT NULL COMMENT '用信人数',
  `org_code` bigint(20) NOT NULL COMMENT '机构号',
  `use_promote` decimal(20, 2) NULL DEFAULT NULL COMMENT '用信提升',
  `craet_at` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `deposit` decimal(20, 2) NULL DEFAULT NULL COMMENT '存款',
  `loan` decimal(20, 2) NULL DEFAULT NULL COMMENT '贷款',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for home_statistics_info
-- ----------------------------
DROP TABLE IF EXISTS `home_statistics_info`;
CREATE TABLE `home_statistics_info`  (
  `org_code` bigint(20) NOT NULL,
  `org_level` int(11) NOT NULL,
  `people_num` bigint(20) NOT NULL DEFAULT 0,
  `amount` decimal(18, 2) NOT NULL DEFAULT 0.00,
  `amount_type` int(11) NOT NULL,
  `time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time_type` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for home_warn_info
-- ----------------------------
DROP TABLE IF EXISTS `home_warn_info`;
CREATE TABLE `home_warn_info`  (
  `org_code` bigint(20) NOT NULL,
  `org_level` int(11) NOT NULL,
  `amount` decimal(18, 2) NOT NULL DEFAULT 0.00,
  `amount_type` int(11) NOT NULL,
  `message` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` bigint(20) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for object_maxsn
-- ----------------------------
DROP TABLE IF EXISTS `object_maxsn`;
CREATE TABLE `object_maxsn`  (
  `tablename` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `columnname` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `maxserialno` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tablename`, `columnname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for platform_info
-- ----------------------------
DROP TABLE IF EXISTS `platform_info`;
CREATE TABLE `platform_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_uuid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `originator_id` bigint(20) NOT NULL,
  `entrance` int(11) NOT NULL,
  `handler_id` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `delete_status` int(11) NOT NULL DEFAULT 1,
  `start_time` bigint(20) NOT NULL,
  `end_time` bigint(20) NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_dict
-- ----------------------------
DROP TABLE IF EXISTS `product_dict`;
CREATE TABLE `product_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT 1,
  `rate` decimal(4, 2) NULL DEFAULT NULL COMMENT '利率（百分之己）',
  `explain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '说明',
  `created_at` bigint(20) NULL DEFAULT NULL,
  `updated_at` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for resident_info
-- ----------------------------
DROP TABLE IF EXISTS `resident_info`;
CREATE TABLE `resident_info`  (
  `resident_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resident_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nation` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthdate` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `household_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `relationship` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `household_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `contact` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_in_list` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  `career` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT 1,
  `grid_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`resident_id`) USING BTREE,
  UNIQUE INDEX `uk_id_number`(`id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1065 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag_customer
-- ----------------------------
DROP TABLE IF EXISTS `tag_customer`;
CREATE TABLE `tag_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) NOT NULL,
  `id_number` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 783 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for task_args
-- ----------------------------
DROP TABLE IF EXISTS `task_args`;
CREATE TABLE `task_args`  (
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isinuse` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `notes` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for task_status
-- ----------------------------
DROP TABLE IF EXISTS `task_status`;
CREATE TABLE `task_status`  (
  `taskname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批处理名称',
  `taskdate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '批处理日期',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '批处理状态 --notstart: 未开始, underway: 执行中,  complete: 完成',
  `tasknote` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `activate` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否激活',
  PRIMARY KEY (`taskname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for unit_status
-- ----------------------------
DROP TABLE IF EXISTS `unit_status`;
CREATE TABLE `unit_status`  (
  `serialno` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '流水号',
  `taskname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务名称',
  `taskdate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '任务日期',
  `unitname` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '节点名称',
  `starttime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '结束时间',
  `unitnote` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '节点功能描述',
  PRIMARY KEY (`serialno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '批处理节点状态表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
