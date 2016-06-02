/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.60
Source Server Version : 50616
Source Host           : 10.10.11.60:3306
Source Database       : marketing_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-10-08 20:12:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `marketing_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon`;
CREATE TABLE `marketing_coupon` (
  `id` bigint(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `interval` int(11) DEFAULT NULL,
  `startDate` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `endDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `validDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '有效时间',
  `totalOfPerson` int(11) DEFAULT NULL COMMENT '总共领取张数/人',
  `priceOfPerson` int(11) DEFAULT NULL COMMENT '总共领取金额数/人',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `enable` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of marketing_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `marketing_coupon_items`
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon_items`;
CREATE TABLE `marketing_coupon_items` (
  `id` bigint(11) NOT NULL,
  `couponID` bigint(11) DEFAULT NULL COMMENT '优惠券ID',
  `name` varchar(255) DEFAULT NULL COMMENT '优惠券名称',
  `price` double(11,0) DEFAULT NULL COMMENT '面额',
  `totalNum` int(11) DEFAULT NULL COMMENT '总数量',
  `sendNum` int(11) DEFAULT NULL COMMENT '发放数量',
  `usedNum` int(11) DEFAULT NULL COMMENT '已使用数量',
  `limitPrice` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of marketing_coupon_items
-- ----------------------------

-- ----------------------------
-- Table structure for `marketing_merchandise_custom_classification`
-- ----------------------------
DROP TABLE IF EXISTS `marketing_merchandise_custom_classification`;
CREATE TABLE `marketing_merchandise_custom_classification` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID,商城为1',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '商品ID',
  `orderNum` int(2) DEFAULT NULL COMMENT '排序',
  `customClassifyId` bigint(11) DEFAULT NULL COMMENT '分类定义',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义分类商品';

-- ----------------------------
-- Records of marketing_merchandise_custom_classification
-- ----------------------------

-- ----------------------------
-- Table structure for `marketing_slide`
-- ----------------------------
DROP TABLE IF EXISTS `marketing_slide`;
CREATE TABLE `marketing_slide` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `clickUrl` varchar(200) DEFAULT NULL COMMENT '点击地址',
  `image` varchar(200) DEFAULT NULL COMMENT '图片',
  `sence` int(2) DEFAULT NULL COMMENT '场景',
  `orderNum` int(2) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='幻灯片';

-- ----------------------------
-- Records of marketing_slide
-- ----------------------------
DROP TABLE IF EXISTS `marketing_recent_discount`;
CREATE TABLE `marketing_recent_discount` (
  `id` bigint(11) NOT NULL,
  `merchantId` bigint(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `startDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `endDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `enable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


