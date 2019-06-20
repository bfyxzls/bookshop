/*
 Navicat Premium Data Transfer

 Source Server         : local3306
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : bookshop

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 04/04/2019 17:44:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `is_new` int(1) NOT NULL DEFAULT '0',
  `is_recommend` int(1) NOT NULL DEFAULT '0',
  `category_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sale_price` decimal(10,2) DEFAULT NULL,
  `floor_price` decimal(10,2) DEFAULT NULL,
  `discount` int(8) DEFAULT NULL,
  `category_name` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `img_url` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `level` int(8) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `order_time` timestamp NOT NULL,
  `order_status` int(8) NOT NULL DEFAULT 0,
  `total_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `order_info_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `username` varchar(255) NOT NULL,
  `realname` varchar(255)  DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `age` int(8) DEFAULT '0',
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_shipper
-- ----------------------------
DROP TABLE IF EXISTS `user_shipper`;
CREATE TABLE `user_shipper` (
  `id` bigint(20) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `user_id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `video_lesson`;
CREATE TABLE `video_lesson` (
  `id` bigint(20) NOT NULL,
  `created_on` timestamp NOT NULL,
  `updated_on` timestamp NOT NULL,
  `is_delete` int(1) NOT NULL DEFAULT '0',
  `file_path` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS = 1;

