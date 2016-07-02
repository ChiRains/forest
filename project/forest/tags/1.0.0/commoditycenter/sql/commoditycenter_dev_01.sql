/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.60
Source Server Version : 50616
Source Host           : 10.10.11.60:3306
Source Database       : commoditycenter_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-11-20 09:09:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `commoditycenter_attribute_definition`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_attribute_definition`;
CREATE TABLE `commoditycenter_attribute_definition` (
  `id` bigint(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码',
  `remark` varchar(200) DEFAULT NULL COMMENT '说明',
  `enumeration` varchar(100) DEFAULT NULL COMMENT '枚举定义名称',
  `value` text COMMENT '值',
  `type` varchar(100) DEFAULT NULL COMMENT '类别 1属性2规格',
  `valueType` varchar(100) DEFAULT NULL COMMENT '值类型：1输入框2下拉框3单选框4多选框5富文本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性定义';

-- ----------------------------
-- Records of commoditycenter_attribute_definition
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_classify_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_classify_attribute`;
CREATE TABLE `commoditycenter_classify_attribute` (
  `id` bigint(11) NOT NULL,
  `classifyId` bigint(11) DEFAULT NULL COMMENT '分类定义',
  `attributeId` bigint(11) DEFAULT NULL COMMENT '属性定义',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类属性';

-- ----------------------------
-- Records of commoditycenter_classify_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_classify_specifications`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_classify_specifications`;
CREATE TABLE `commoditycenter_classify_specifications` (
  `id` bigint(11) NOT NULL,
  `classifyId` bigint(11) DEFAULT NULL COMMENT '分类定义',
  `attributeId` bigint(11) DEFAULT NULL COMMENT '属性定义',
  `uploadImage` int(2) DEFAULT NULL COMMENT '是否上传图片',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类规格';

-- ----------------------------
-- Records of commoditycenter_classify_specifications
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_combination_merchandise`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_combination_merchandise`;
CREATE TABLE `commoditycenter_combination_merchandise` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT 'ID',
  `purchase` double(10,2) DEFAULT NULL COMMENT '进货价',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣价',
  `price` double(10,2) DEFAULT NULL COMMENT '原价',
  `stock` int(6) DEFAULT NULL COMMENT '库存',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组合商品';

-- ----------------------------
-- Records of commoditycenter_combination_merchandise
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_combination_merchandise_item`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_combination_merchandise_item`;
CREATE TABLE `commoditycenter_combination_merchandise_item` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `combinationMerchandiseId` bigint(11) DEFAULT NULL COMMENT '组合商品ID',
  `merchandiseItemId` bigint(11) DEFAULT NULL COMMENT '单一商品ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `num` int(11) DEFAULT '0' COMMENT '数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组合商品条目';

-- ----------------------------
-- Records of commoditycenter_combination_merchandise_item
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_enumeration`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_enumeration`;
CREATE TABLE `commoditycenter_enumeration` (
  `id` bigint(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` text COMMENT '值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格枚举';

-- ----------------------------
-- Records of commoditycenter_enumeration
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise`;
CREATE TABLE `commoditycenter_merchandise` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchantClassifyId` bigint(11) DEFAULT NULL COMMENT '商家分类ID',
  `mallClassifyId` bigint(11) DEFAULT NULL COMMENT '商城分类ID',
  `specClassifyId` bigint(11) DEFAULT NULL COMMENT '规格分类ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `sysCode` varchar(100) DEFAULT NULL COMMENT '系统编号',
  `code` varchar(100) DEFAULT NULL COMMENT '商品编号',
  `image` varchar(200) DEFAULT NULL COMMENT '图片,缩略图',
  `keywords` varchar(200) DEFAULT NULL COMMENT '关键字',
  `weight` int(2) DEFAULT NULL COMMENT '重量',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `unit` varchar(100) DEFAULT NULL COMMENT '单位',
  `details` text COMMENT '详情',
  `desc` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of commoditycenter_merchandise
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_attribute`;
CREATE TABLE `commoditycenter_merchandise_attribute` (
  `id` bigint(11) NOT NULL,
  `attributeId` bigint(11) NOT NULL,
  `merchandiseId` bigint(11) NOT NULL,
  `value` varchar(4000) DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性';

-- ----------------------------
-- Records of commoditycenter_merchandise_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_deal_record`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_deal_record`;
CREATE TABLE `commoditycenter_merchandise_deal_record` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchandiseId` bigint(11) DEFAULT NULL COMMENT '商品档案ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '买家ID',
  `number` int(2) DEFAULT NULL COMMENT '数量',
  `specifications` varchar(200) DEFAULT NULL COMMENT '规格说明',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `orderItemId` bigint(11) NOT NULL COMMENT '订单项ID',
  `orderItemDetailId` bigint(11) NOT NULL COMMENT '订单项ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品成交记录';

-- ----------------------------
-- Records of commoditycenter_merchandise_deal_record
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_evaluation`;
CREATE TABLE `commoditycenter_merchandise_evaluation` (
  `id` bigint(11) NOT NULL,
  `merchandiseId` bigint(11) NOT NULL COMMENT '商品id',
  `content` varchar(255) DEFAULT NULL COMMENT '评价内容',
  `star` tinyint(4) NOT NULL COMMENT '星级(1-5星，星值=(1-5)*10)',
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '评价时间',
  `status` tinyint(4) NOT NULL COMMENT '审核状态(0:未处理,1:未通过,2:已通过)',
  `specifications` varchar(100) DEFAULT NULL COMMENT '规格',
  `userId` bigint(11) NOT NULL COMMENT '评价人',
  PRIMARY KEY (`id`),
  KEY `merchandiseId` (`merchandiseId`),
  KEY `star` (`star`),
  KEY `time` (`time`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价表';

-- ----------------------------
-- Records of commoditycenter_merchandise_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_image`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_image`;
CREATE TABLE `commoditycenter_merchandise_image` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchandiseId` bigint(11) DEFAULT NULL COMMENT '商品ID',
  `attributeId` bigint(11) NOT NULL,
  `value` varchar(200) DEFAULT NULL COMMENT '值',
  `image` varchar(200) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片';

-- ----------------------------
-- Records of commoditycenter_merchandise_image
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_item`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_item`;
CREATE TABLE `commoditycenter_merchandise_item` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT 'ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `merchandiseId` bigint(11) DEFAULT NULL COMMENT '商品ID',
  `mallClassifyId` bigint(11) DEFAULT NULL COMMENT '商城分类ID',
  `merchantClassifyId` bigint(11) DEFAULT NULL COMMENT '商品分类ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `keywords` varchar(200) DEFAULT NULL COMMENT '关键字',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `purchase` double(10,2) DEFAULT NULL COMMENT '进货价',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣价',
  `price` double(10,2) DEFAULT NULL COMMENT '原价',
  `stock` int(6) DEFAULT NULL COMMENT '库存',
  `merchandiseSpecificationsId` bigint(11) DEFAULT NULL COMMENT '商品规格',
  `salesVolume` bigint(11) NOT NULL COMMENT '销量',
  `virtualSalesVolume` bigint(11) NOT NULL COMMENT '虚销量',
  `clickRate` bigint(11) NOT NULL COMMENT '点击数量',
  `lowEvaluation` bigint(11) NOT NULL COMMENT '差评数量',
  `middleEvaluation` bigint(11) NOT NULL COMMENT '中评数量',
  `goodEvaluation` bigint(11) NOT NULL COMMENT '好评数量',
  `recordTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '录入时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单一商品';

-- ----------------------------
-- Records of commoditycenter_merchandise_item
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_marketing`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_marketing`;
CREATE TABLE `commoditycenter_merchandise_marketing` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchandiseItemId` bigint(11) DEFAULT NULL COMMENT '单一商品ID',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '统一商品ID',
  `purchase` double(10,2) DEFAULT NULL COMMENT '进货价',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣价',
  `price` double(10,2) DEFAULT NULL COMMENT '原价',
  `stock` int(6) DEFAULT NULL COMMENT '库存',
  `sence` int(2) DEFAULT NULL COMMENT '场景',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `order` int(2) DEFAULT NULL COMMENT '排序',
  `currencyType` int(2) DEFAULT NULL COMMENT '1人民币2积分3消费币',
  `activityId` bigint(11) DEFAULT NULL COMMENT '活动id',
  `enable` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `sysCode` varchar(255) DEFAULT NULL COMMENT '系统编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of commoditycenter_merchandise_marketing
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_specifications`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_specifications`;
CREATE TABLE `commoditycenter_merchandise_specifications` (
  `id` bigint(11) NOT NULL,
  `merchandiseId` bigint(11) NOT NULL,
  `attributeId0` bigint(11) NOT NULL,
  `value0` varchar(200) DEFAULT NULL COMMENT '值',
  `attributeId1` bigint(11) NOT NULL,
  `value1` varchar(200) DEFAULT NULL COMMENT '值',
  `attributeId2` bigint(11) NOT NULL,
  `value2` varchar(200) DEFAULT NULL COMMENT '值',
  `state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格';

-- ----------------------------
-- Records of commoditycenter_merchandise_specifications
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_merchandise_specifications_relation`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_merchandise_specifications_relation`;
CREATE TABLE `commoditycenter_merchandise_specifications_relation` (
  `id` bigint(11) NOT NULL,
  `merchandiseId` bigint(11) NOT NULL,
  `attributeId` bigint(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `oldAlias` varchar(255) DEFAULT NULL,
  `isCheck` int(11) DEFAULT NULL COMMENT '0否1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格';

-- ----------------------------
-- Records of commoditycenter_merchandise_specifications_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `commoditycenter_unified_merchandise`
-- ----------------------------
DROP TABLE IF EXISTS `commoditycenter_unified_merchandise`;
CREATE TABLE `commoditycenter_unified_merchandise` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `type` int(2) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品的统称.虚拟的商品,可以是单一商品,也可以是商品的组合等';

-- ----------------------------
-- Records of commoditycenter_unified_merchandise
-- ----------------------------
