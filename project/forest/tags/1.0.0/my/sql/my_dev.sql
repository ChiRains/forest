/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.61
Source Server Version : 50616
Source Host           : 10.10.11.61:3306
Source Database       : my_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-03-03 15:22:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `my_consignee`
-- ----------------------------
DROP TABLE IF EXISTS `my_consignee`;
CREATE TABLE `my_consignee` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `district` varchar(100) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `mobile` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `zipCode` varchar(100) DEFAULT NULL COMMENT '邮编',
  `acquiesce` int(2) DEFAULT NULL COMMENT '默认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_consignee
-- ----------------------------

-- ----------------------------
-- Table structure for `my_delivery_mode`
-- ----------------------------
DROP TABLE IF EXISTS `my_delivery_mode`;
CREATE TABLE `my_delivery_mode` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `type` int(2) DEFAULT NULL COMMENT '类型 1物流 2门店自提 3送货上门',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  `storeId` bigint(11) DEFAULT NULL COMMENT '门店',
  `time` varchar(100) DEFAULT NULL COMMENT '时间 门店自提时间或送货上门时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_delivery_mode
-- ----------------------------

-- ----------------------------
-- Table structure for `my_invoice_mode`
-- ----------------------------
DROP TABLE IF EXISTS `my_invoice_mode`;
CREATE TABLE `my_invoice_mode` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `mode` int(2) DEFAULT NULL COMMENT '模式 1开发票 1不开发票',
  `type` int(2) DEFAULT NULL COMMENT '类型 1普通 1增值税',
  `head` varchar(100) DEFAULT NULL COMMENT '抬头',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_invoice_mode
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_after_sale`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_after_sale`;
CREATE TABLE `my_my_after_sale` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) NOT NULL COMMENT '用户id',
  `type` int(2) NOT NULL COMMENT '类型',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '申请时间',
  `lastUpdateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  `afterSaleId` bigint(11) DEFAULT NULL COMMENT '售后单号',
  `view` int(2) NOT NULL COMMENT '状态(1:显示 2:不显示)',
  `orderId` bigint(11) DEFAULT NULL,
  `subOrderId` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_after_sale
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_collection`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_collection`;
CREATE TABLE `my_my_collection` (
  `id` bigint(11) NOT NULL,
  `userId` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `objId` bigint(11) DEFAULT NULL COMMENT '对象ID',
  `classifyId` bigint(11) DEFAULT NULL COMMENT '对象分类ID',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
  `type` int(2) DEFAULT NULL COMMENT '对象类别1 商品 2商家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_collection
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_collection_obj_statistics`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_collection_obj_statistics`;
CREATE TABLE `my_my_collection_obj_statistics` (
  `id` bigint(11) NOT NULL,
  `objId` bigint(11) DEFAULT NULL COMMENT '对象ID',
  `type` int(2) DEFAULT NULL COMMENT '对象类别1 商品 2商家',
  `number` int(3) DEFAULT NULL COMMENT '收藏数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_collection_obj_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_collection_statistics`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_collection_statistics`;
CREATE TABLE `my_my_collection_statistics` (
  `id` bigint(11) NOT NULL,
  `userId` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `classifyId` bigint(11) DEFAULT NULL COMMENT '对象分类ID',
  `type` int(2) DEFAULT NULL COMMENT '对象类别1 商品 2商家',
  `number` int(3) DEFAULT NULL COMMENT '收藏数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_collection_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_coupon`;
CREATE TABLE `my_my_coupon` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `couponId` bigint(11) DEFAULT NULL COMMENT '优惠劵活动',
  `couponItemId` bigint(11) DEFAULT NULL COMMENT '优惠劵项',
  `extractDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '领取时间',
  `validDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '有效时间',
  `name` varchar(255) DEFAULT NULL COMMENT '优惠券名称',
  `price` double(11,0) DEFAULT NULL COMMENT '面额',
  `limitPrice` double(11,2) DEFAULT NULL,
  `state` int(2) DEFAULT NULL COMMENT '状态 1未使用 2已使用',
  `orderId` bigint(11) DEFAULT NULL COMMENT '使用订单',
  `orderDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '下订单时间',
  `merchantId` bigint(11) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_evaluation`;
CREATE TABLE `my_my_evaluation` (
  `id` bigint(11) NOT NULL,
  `evaluationId` bigint(11) NOT NULL COMMENT '评价表id',
  `merchandiseId` bigint(11) NOT NULL COMMENT '商品id',
  `userId` bigint(11) NOT NULL COMMENT '评价人',
  `orderItemDetailId` bigint(11) NOT NULL COMMENT '订单明细id',
  `orderTime` datetime NOT NULL COMMENT '订单时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`) USING BTREE,
  KEY `orderItemDetailId` (`orderItemDetailId`) USING BTREE,
  KEY `orderTime` (`orderTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_order_form`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_order_form`;
CREATE TABLE `my_my_order_form` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) NOT NULL COMMENT '用户',
  `orderId` bigint(11) NOT NULL COMMENT '总单',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `state` int(2) DEFAULT NULL COMMENT '状态,待付款,已付款,待发货,发货中,已签收',
  `subOrderId` bigint(11) DEFAULT NULL,
  `lastUpdateTime` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_order_form
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_shopping_cart`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_shopping_cart`;
CREATE TABLE `my_my_shopping_cart` (
  `id` bigint(11) NOT NULL,
  `userId` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `merchantClassifyId` bigint(11) DEFAULT NULL COMMENT '商家分类ID',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '统一商品ID',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏时间',
  `number` int(2) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for `my_my_to_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `my_my_to_evaluation`;
CREATE TABLE `my_my_to_evaluation` (
  `id` bigint(11) NOT NULL,
  `userId` bigint(11) NOT NULL COMMENT '购买用户',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '统一商品ID',
  `merchandiseId` bigint(11) NOT NULL COMMENT '商品id',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `image` varchar(200) DEFAULT NULL COMMENT '缩略图',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣价,成交价',
  `merchantId` bigint(11) NOT NULL COMMENT '商家ID',
  `orderId` bigint(11) DEFAULT NULL COMMENT '订单ID',
  `subOrderId` bigint(11) DEFAULT NULL COMMENT '订单ID',
  `orderItemId` bigint(11) DEFAULT NULL COMMENT '订单ID',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单时间',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单号',
  `signDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '签收时间',
  `orderItemDetailId` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_my_to_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for `my_search_history`
-- ----------------------------
DROP TABLE IF EXISTS `my_search_history`;
CREATE TABLE `my_search_history` (
  `id` bigint(11) NOT NULL DEFAULT '0',
  `keywords` varchar(255) DEFAULT NULL,
  `time` timestamp(6) NULL DEFAULT NULL,
  `userId` bigint(11) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of my_search_history
-- ----------------------------
