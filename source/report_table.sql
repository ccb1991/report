/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.93.1_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 192.168.93.1:3306
 Source Schema         : report

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 31/01/2023 22:44:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer_question_relation
-- ----------------------------
DROP TABLE IF EXISTS `answer_question_relation`;
CREATE TABLE `answer_question_relation`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `question_id` int(0) NULL DEFAULT NULL,
  `option` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL,
  `standard_answer` tinyint(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `creator` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `editor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for domain_position
-- ----------------------------
DROP TABLE IF EXISTS `domain_position`;
CREATE TABLE `domain_position`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `domain_id` int(0) NULL DEFAULT NULL COMMENT '领域id',
  `position_type` int(0) NULL DEFAULT NULL COMMENT '0、表示总分，1、表示得分',
  `col` int(0) NULL DEFAULT NULL COMMENT '列',
  `row` int(0) NULL DEFAULT NULL COMMENT '行',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for exam_record
-- ----------------------------
DROP TABLE IF EXISTS `exam_record`;
CREATE TABLE `exam_record`  (
  `id` int(0) NOT NULL,
  `child_id` int(0) NULL DEFAULT NULL,
  `communication` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for item_position
-- ----------------------------
DROP TABLE IF EXISTS `item_position`;
CREATE TABLE `item_position`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `item_id` int(0) NULL DEFAULT NULL COMMENT '大项',
  `position_type` int(0) NULL DEFAULT NULL COMMENT '0、表示总分，1、表示得分',
  `col` int(0) NULL DEFAULT NULL COMMENT '列',
  `row` int(0) NULL DEFAULT NULL COMMENT '行',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 311 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `domain` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `item` int(0) NULL DEFAULT NULL,
  `subitem` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `describe` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `creator` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `editor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_domain
-- ----------------------------
DROP TABLE IF EXISTS `question_domain`;
CREATE TABLE `question_domain`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `domain` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `domain_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `domain_sheet` int(0) NULL DEFAULT NULL,
  `col` int(0) NULL DEFAULT NULL COMMENT '列',
  `row` int(0) NULL DEFAULT NULL COMMENT '行',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `editor` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_item
-- ----------------------------
DROP TABLE IF EXISTS `question_item`;
CREATE TABLE `question_item`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `item` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `item_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `domain_id` int(0) NULL DEFAULT NULL,
  `row` int(0) NULL DEFAULT NULL COMMENT '行',
  `col` int(0) NULL DEFAULT NULL COMMENT '列',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `creator` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `editor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for report_sheet
-- ----------------------------
DROP TABLE IF EXISTS `report_sheet`;
CREATE TABLE `report_sheet`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `sheet_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `root_domain_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
