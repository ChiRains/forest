/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.60
Source Server Version : 50616
Source Host           : 10.10.11.60:3306
Source Database       : sellercenter_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-11-20 09:11:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sellercenter_member`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_member`;
CREATE TABLE `sellercenter_member` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `account` varchar(100) DEFAULT NULL COMMENT '账号 唯一 登录使用',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `qq` varchar(50) DEFAULT NULL COMMENT 'qq',
  `headImage` varchar(200) DEFAULT NULL COMMENT '头像',
  `enable` int(2) DEFAULT NULL COMMENT '是否启用,在职',
  `userId` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成员';

-- ----------------------------
-- Records of sellercenter_member
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant`;
CREATE TABLE `sellercenter_merchant` (
  `id` bigint(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编码（商家唯一编码）',
  `userId` bigint(11) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `district` varchar(100) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `longitude` double(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` double(10,6) DEFAULT NULL COMMENT '纬度',
  `image` varchar(255) DEFAULT NULL,
  `logo` varchar(200) DEFAULT NULL COMMENT 'LOGO',
  `classifyId` bigint(11) DEFAULT NULL COMMENT '商家分类',
  `flagship` varchar(200) DEFAULT NULL COMMENT '主打产品',
  `introduction` varchar(200) DEFAULT NULL COMMENT '简介',
  `detailIntroduction` text COMMENT '详细介绍',
  `linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '商家电话',
  `integralModeId` bigint(11) NOT NULL COMMENT '积分模式',
  `commodityAuditing` int(2) DEFAULT NULL COMMENT '上传商品是否需要审核',
  `distribution` int(2) DEFAULT NULL COMMENT '卖出商品是否支持分销',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '登记时间',
  `state` int(2) DEFAULT NULL COMMENT '商家是否禁用(1否，2是)',
  `merchantType` int(3) DEFAULT NULL COMMENT '1普通商家2房产商家',
  `consumptionCurrency` double(10,2) DEFAULT NULL COMMENT '消费币',
  `version` bigint(11) NOT NULL COMMENT '版本',
  `notify` int(2) DEFAULT '0' COMMENT '0不需要短信通知1需要短信通知',
  `receiveMobile` varchar(255) DEFAULT NULL COMMENT '接收短信的手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家';

-- ----------------------------
-- Records of sellercenter_merchant
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_classify`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_classify`;
CREATE TABLE `sellercenter_merchant_classify` (
  `id` bigint(11) NOT NULL,
  `merchantId` bigint(11) NOT NULL COMMENT '商家',
  `classifyId` bigint(11) NOT NULL COMMENT '商品分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家、商品分类关系';

-- ----------------------------
-- Records of sellercenter_merchant_classify
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_consumption_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_consumption_detail`;
CREATE TABLE `sellercenter_merchant_consumption_detail` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '财富账号',
  `point` double(10,2) DEFAULT NULL COMMENT '当次财富值',
  `remainder` double(10,2) DEFAULT NULL COMMENT '余额',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发生时间',
  `desc` varchar(500) DEFAULT NULL COMMENT '财富描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家消费币明细';

-- ----------------------------
-- Records of sellercenter_merchant_consumption_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_evaluation`;
CREATE TABLE `sellercenter_merchant_evaluation` (
  `id` bigint(11) NOT NULL,
  `evaluationId` bigint(11) NOT NULL COMMENT '评价表id',
  `merchantId` bigint(11) NOT NULL COMMENT '商家id',
  `merchandiseId` bigint(11) NOT NULL COMMENT '商品id',
  `evaluationTime` datetime NOT NULL COMMENT '评价时间',
  PRIMARY KEY (`id`),
  KEY `merchantId` (`merchantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家入口表';

-- ----------------------------
-- Records of sellercenter_merchant_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_member`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_member`;
CREATE TABLE `sellercenter_merchant_member` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `memberId` bigint(11) DEFAULT NULL COMMENT 'memberID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT 'merchantID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家成员';

-- ----------------------------
-- Records of sellercenter_merchant_member
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_merchandise_classify`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_merchandise_classify`;
CREATE TABLE `sellercenter_merchant_merchandise_classify` (
  `id` bigint(11) NOT NULL,
  `merchantId` bigint(11) DEFAULT NULL,
  `classifyId` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家——商品分类';

-- ----------------------------
-- Records of sellercenter_merchant_merchandise_classify
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_order_form`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_order_form`;
CREATE TABLE `sellercenter_merchant_order_form` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '卖家',
  `storeId` bigint(11) DEFAULT NULL COMMENT '门店',
  `orderId` bigint(11) DEFAULT NULL COMMENT '总单',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卖家订单';

-- ----------------------------
-- Records of sellercenter_merchant_order_form
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_pay`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_pay`;
CREATE TABLE `sellercenter_merchant_pay` (
  `id` bigint(11) NOT NULL,
  `merchantId` bigint(11) DEFAULT NULL,
  `type` int(2) DEFAULT NULL COMMENT '支付类型',
  `account` varchar(100) DEFAULT NULL COMMENT '账户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家支付信息';

-- ----------------------------
-- Records of sellercenter_merchant_pay
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_service`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_service`;
CREATE TABLE `sellercenter_merchant_service` (
  `id` bigint(11) NOT NULL,
  `merchantId` bigint(11) DEFAULT NULL,
  `linkman` varchar(100) DEFAULT NULL COMMENT '联系人',
  `type` int(2) DEFAULT NULL COMMENT '通讯类型',
  `contactWay` varchar(100) DEFAULT NULL COMMENT '联系信息',
  `desc` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家客服,联系方式';

-- ----------------------------
-- Records of sellercenter_merchant_service
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_merchant_sort`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_merchant_sort`;
CREATE TABLE `sellercenter_merchant_sort` (
  `id` bigint(11) NOT NULL,
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `logo` varchar(100) DEFAULT NULL COMMENT 'logo',
  `sort` bigint(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellercenter_merchant_sort
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_sexpress`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_sexpress`;
CREATE TABLE `sellercenter_sexpress` (
  `id` bigint(11) NOT NULL,
  `merchandId` bigint(11) DEFAULT NULL COMMENT '商家id',
  `name` varchar(255) DEFAULT NULL COMMENT '快递公司名称',
  `code` varchar(255) DEFAULT NULL COMMENT '快递公司编码',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `logo` varchar(255) DEFAULT NULL COMMENT '快递公司图片',
  `sort` bigint(11) DEFAULT NULL COMMENT '排序',
  `firstWeight` double(11,2) DEFAULT NULL COMMENT '首重重量',
  `firstPrice` double(11,2) DEFAULT NULL COMMENT '首重费用',
  `continuedWeight` double(11,2) DEFAULT NULL COMMENT '续重重量',
  `continuedPrice` double(11,2) DEFAULT NULL COMMENT '续重费用',
  `enable` bigint(11) DEFAULT NULL COMMENT '是否启用 0否1是',
  `type` int(11) DEFAULT NULL COMMENT '1包邮2统一邮费3区域收费',
  `fixedPrice` double(11,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellercenter_sexpress
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_sexpress_district`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_sexpress_district`;
CREATE TABLE `sellercenter_sexpress_district` (
  `id` bigint(11) NOT NULL,
  `expressId` bigint(11) DEFAULT NULL COMMENT '快递公司id',
  `firstPrice` double(11,2) DEFAULT NULL COMMENT '首重重量',
  `continuedPrice` double(11,2) DEFAULT NULL COMMENT '续重重量',
  `province` varchar(11) DEFAULT NULL COMMENT '省份',
  `city` varchar(11) DEFAULT NULL COMMENT '城市',
  `district` varchar(11) DEFAULT NULL COMMENT '地区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellercenter_sexpress_district
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_store`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_store`;
CREATE TABLE `sellercenter_store` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `parentId` bigint(11) DEFAULT NULL,
  `bsid` varchar(200) DEFAULT NULL COMMENT '树编码',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `district` varchar(100) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `longitude` double(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` double(10,6) DEFAULT NULL COMMENT '纬度',
  `logo` varchar(200) DEFAULT NULL COMMENT 'LOGO',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(20) DEFAULT NULL COMMENT '商家手机',
  `enable` tinyint(4) DEFAULT NULL COMMENT '是否启用 (0：禁用  1：启用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店';

-- ----------------------------
-- Records of sellercenter_store
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_store_delivery_range`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_store_delivery_range`;
CREATE TABLE `sellercenter_store_delivery_range` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `storeId` bigint(11) DEFAULT NULL,
  `radius` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店配送范围';

-- ----------------------------
-- Records of sellercenter_store_delivery_range
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_store_delivery_time`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_store_delivery_time`;
CREATE TABLE `sellercenter_store_delivery_time` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `storeId` bigint(11) DEFAULT NULL,
  `pickupStartTime` varchar(100) DEFAULT NULL COMMENT '自提开始时间',
  `pickupEndTime` varchar(100) DEFAULT NULL COMMENT '自提结束时间',
  `pickupDesc` varchar(100) DEFAULT NULL COMMENT '自提描述',
  `deliveryStartTime` varchar(100) DEFAULT NULL COMMENT '送货上门开始时间',
  `deliveryEndTime` varchar(100) DEFAULT NULL COMMENT '送货上门结束时间',
  `deliveryFrequency` int(3) DEFAULT NULL COMMENT '送货频率',
  `deliveryDuration` int(3) DEFAULT NULL COMMENT '送货时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店配送时间';

-- ----------------------------
-- Records of sellercenter_store_delivery_time
-- ----------------------------

-- ----------------------------
-- Table structure for `sellercenter_store_member`
-- ----------------------------
DROP TABLE IF EXISTS `sellercenter_store_member`;
CREATE TABLE `sellercenter_store_member` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `memberId` bigint(11) DEFAULT NULL COMMENT 'memberID',
  `merchantId` bigint(11) DEFAULT NULL COMMENT '商家ID',
  `storeId` bigint(11) DEFAULT NULL COMMENT 'storeID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门店成员';

-- ----------------------------
-- Records of sellercenter_store_member
-- ----------------------------
