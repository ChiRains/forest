/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.60
Source Server Version : 50616
Source Host           : 10.10.11.60:3306
Source Database       : warehouse_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-09-28 09:29:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `warehouse_merchandise_stock`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_merchandise_stock`;
CREATE TABLE `warehouse_merchandise_stock` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `storeId` bigint(11) DEFAULT NULL COMMENT '门店ID',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '统一商品ID',
  `merchandiseName` varchar(100) DEFAULT NULL COMMENT '名称',
  `purchase` double(10,2) DEFAULT NULL COMMENT '进货价',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣价',
  `price` double(10,2) DEFAULT NULL COMMENT '原价',
  `stock` int(6) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存';

-- ----------------------------
-- Records of warehouse_merchandise_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `warehouse_merchandise_stock_state`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_merchandise_stock_state`;
CREATE TABLE `warehouse_merchandise_stock_state` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `stockStateId` bigint(11) NOT NULL COMMENT '商品库存出入货id',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '唯一商品id',
  `number` int(11) NOT NULL COMMENT '数量',
  `state` tinyint(4) NOT NULL COMMENT '状态: 1-申请 2-确定 3-签收',
  `applyTime` datetime DEFAULT NULL COMMENT '申请时间',
  `confirmTime` datetime DEFAULT NULL COMMENT '确认时间',
  `signTime` datetime DEFAULT NULL COMMENT '签收时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出入货单商品关联';

-- ----------------------------
-- Records of warehouse_merchandise_stock_state
-- ----------------------------

-- ----------------------------
-- Table structure for `warehouse_stock_state`
-- ----------------------------
DROP TABLE IF EXISTS `warehouse_stock_state`;
CREATE TABLE `warehouse_stock_state` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `formStoreId` bigint(11) NOT NULL COMMENT '出货门店id',
  `toStoreId` bigint(11) NOT NULL COMMENT '要货门店id',
  `state` tinyint(4) NOT NULL COMMENT '状态: 1-申请 2-确认 3-签收',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存出入货状态';

-- ----------------------------
-- Records of warehouse_stock_state
-- ----------------------------
