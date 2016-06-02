/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.60
Source Server Version : 50616
Source Host           : 10.10.11.60:3306
Source Database       : orderform_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2015-10-21 13:54:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `orderform_collect_order`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_collect_order`;
CREATE TABLE `orderform_collect_order` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单号',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `lastUpdateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  `sum` double(10,2) DEFAULT NULL COMMENT '订单金额',
  `cash` double(10,2) DEFAULT NULL COMMENT '现金',
  `integral` int(2) DEFAULT NULL COMMENT '积分',
  `consumption` double(10,2) DEFAULT NULL COMMENT '消费币',
  `myCouponId` bigint(11) DEFAULT NULL COMMENT '优惠劵',
  `coupon` double(10,2) DEFAULT NULL COMMENT '优惠面额',
  `consignee` varchar(100) DEFAULT NULL COMMENT '收货人',
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `mobile` varchar(20) DEFAULT NULL COMMENT '收货电话',
  `email` varchar(100) DEFAULT NULL COMMENT '收货人邮箱',
  `pickupAddressStr` varchar(100) DEFAULT NULL COMMENT '自提地址',
  `deliveryTimeStr` varchar(100) DEFAULT NULL COMMENT '送货时间说明',
  `deliveryMode` int(2) DEFAULT NULL COMMENT '送货方式',
  `paymentMode` int(2) DEFAULT NULL COMMENT '现金支付方式 101支付宝 102微信支付 103银联 104货到付款 105钱包',
  `state` int(2) DEFAULT NULL COMMENT '状态,待付款,已付款,待发货,发货中,已签收',
  `explain` varchar(200) DEFAULT NULL COMMENT '订单说明',
  `needInvoice` int(2) DEFAULT NULL COMMENT '是否开发票',
  `invoiceType` int(2) DEFAULT NULL COMMENT '发票类型',
  `invoiceHead` varchar(200) DEFAULT NULL COMMENT '发票抬头',
  `invoiceContent` varchar(200) DEFAULT NULL COMMENT '发票内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='总单';

-- ----------------------------
-- Records of orderform_collect_order
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_exchange_order`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_exchange_order`;
CREATE TABLE `orderform_exchange_order` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单号',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `merchantId` bigint(11) NOT NULL COMMENT '商家ID',
  `storeId` bigint(11) NOT NULL COMMENT '门店ID',
  `state` int(2) NOT NULL COMMENT '状态(1:已申请 2:通过 3:未通过 )',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '申请换货时间',
  `userId` bigint(11) NOT NULL COMMENT '用户id',
  `explain` varchar(200) DEFAULT NULL COMMENT '说明',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `exchangeNumber` varchar(50) DEFAULT NULL COMMENT '换货单号',
  `userLogisticsCompany` varchar(200) DEFAULT NULL COMMENT '物流公司',
  `userLogisticsNumber` varchar(50) DEFAULT NULL COMMENT '物流查询号',
  `merchantLogisticsCompany` varchar(200) DEFAULT NULL COMMENT '物流公司',
  `merchantLogisticsNumber` varchar(50) DEFAULT NULL COMMENT '物流查询号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='换货统计单';

-- ----------------------------
-- Records of orderform_exchange_order
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_exchange_order_item_detail`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_exchange_order_item_detail`;
CREATE TABLE `orderform_exchange_order_item_detail` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `orderItemId` bigint(11) NOT NULL COMMENT '订单项ID',
  `orderItemDetailId` bigint(11) NOT NULL COMMENT '订单项明细ID',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '申请换货时间',
  `state` int(2) DEFAULT NULL COMMENT '状态(1:已申请 2:通过 3:未通过 )',
  `number` int(2) DEFAULT NULL COMMENT '数量',
  `explain` varchar(200) DEFAULT NULL COMMENT '说明',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `exchangeId` bigint(11) NOT NULL COMMENT '换货总计单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='换货单';

-- ----------------------------
-- Records of orderform_exchange_order_item_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_order_item`;
CREATE TABLE `orderform_order_item` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `merchantId` bigint(11) NOT NULL COMMENT '商家ID',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '统一商品ID',
  `sence` int(2) DEFAULT NULL COMMENT '场景',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `image` varchar(200) DEFAULT NULL COMMENT '缩略图',
  `purchase` double(10,2) DEFAULT NULL COMMENT '进货价',
  `discount` double(10,2) DEFAULT NULL COMMENT '折扣价,成交价',
  `price` double(10,2) DEFAULT NULL COMMENT '原价',
  `number` int(2) DEFAULT NULL COMMENT '数量',
  `sum` double(10,2) DEFAULT NULL COMMENT '小计金额',
  `state` int(2) DEFAULT NULL COMMENT '状态,待付款,已付款,待发货,已发货,已签收',
  `snapshot` varchar(200) DEFAULT NULL COMMENT '商品快照',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情';

-- ----------------------------
-- Records of orderform_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_order_item_detail`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_order_item_detail`;
CREATE TABLE `orderform_order_item_detail` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `orderItemId` bigint(11) NOT NULL COMMENT '订单项ID',
  `merchantId` bigint(11) NOT NULL COMMENT '商家ID',
  `unifiedMerchandiseId` bigint(11) NOT NULL COMMENT '统一商品ID',
  `merchandiseItemId` bigint(11) NOT NULL COMMENT '单一商品ID',
  `number` int(2) DEFAULT NULL COMMENT '数量',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `state` int(2) DEFAULT NULL COMMENT '状态,待付款,已付款,待发货,已发货,已签收',
  `logisticsCompany` varchar(200) DEFAULT NULL COMMENT '物流公司',
  `logisticsNumber` varchar(50) DEFAULT NULL COMMENT '物流查询号',
  `specifications` varchar(200) DEFAULT NULL COMMENT '规格说明',
  `image` varchar(200) DEFAULT NULL COMMENT '缩略图',
  `code` varchar(200) DEFAULT NULL COMMENT '商品编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情';

-- ----------------------------
-- Records of orderform_order_item_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_pay_record`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_pay_record`;
CREATE TABLE `orderform_pay_record` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单号',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `tradeId` varchar(200) DEFAULT NULL COMMENT '交易ID',
  `tradeTyped` int(2) DEFAULT NULL COMMENT '交易类别 101支付宝 102微信支付 103银联 104货到付款 105钱包 106消费币 107积分',
  `sum` varchar(200) DEFAULT NULL COMMENT '交易ID',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态跳转时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单时间';

-- ----------------------------
-- Records of orderform_pay_record
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_record_state_time`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_record_state_time`;
CREATE TABLE `orderform_record_state_time` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `dataId` bigint(11) NOT NULL COMMENT '总单ID',
  `dataIdType` int(2) DEFAULT NULL COMMENT 'dataId类型,1总单 2子单 3订单项 4订单明细 5退货单 6退货明细 7换货单 8换货明细',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '状态跳转时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单时间';

-- ----------------------------
-- Records of orderform_record_state_time
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_refund_order`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_refund_order`;
CREATE TABLE `orderform_refund_order` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单号',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `merchantId` bigint(11) NOT NULL COMMENT '商家ID',
  `storeId` bigint(11) NOT NULL COMMENT '门店ID',
  `sum` double(10,2) NOT NULL COMMENT '退款金额',
  `state` int(2) NOT NULL COMMENT '状态(1:已申请 2:通过 3:未通过 )',
  `userId` bigint(11) NOT NULL COMMENT '用户id',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `refundNumber` varchar(50) DEFAULT NULL COMMENT '退款单号',
  `explain` varchar(200) DEFAULT NULL COMMENT '说明',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款统计单';

-- ----------------------------
-- Records of orderform_refund_order
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_refund_order_item_detail`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_refund_order_item_detail`;
CREATE TABLE `orderform_refund_order_item_detail` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `orderItemId` bigint(11) NOT NULL COMMENT '订单项ID',
  `orderItemDetailId` bigint(11) NOT NULL COMMENT '订单项明细ID',
  `state` int(2) DEFAULT NULL COMMENT '状态(1:已申请 2:通过 3:未通过 )',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '申请退货时间',
  `number` int(2) DEFAULT NULL COMMENT '数量',
  `sum` double(10,2) DEFAULT NULL COMMENT '退款金额',
  `explain` varchar(200) DEFAULT NULL COMMENT '说明',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `refundId` bigint(11) NOT NULL COMMENT '退款总计单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款单';

-- ----------------------------
-- Records of orderform_refund_order_item_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_return_order`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_return_order`;
CREATE TABLE `orderform_return_order` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单号',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `merchantId` bigint(11) NOT NULL COMMENT '商家ID',
  `storeId` bigint(11) NOT NULL COMMENT '门店ID',
  `sum` double(10,2) NOT NULL COMMENT '退款金额',
  `state` int(2) NOT NULL COMMENT '状态(1:已申请 2:通过 3:未通过 )',
  `userId` bigint(11) NOT NULL COMMENT '用户id',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `returnNumber` varchar(50) DEFAULT NULL COMMENT '退货单号',
  `explain` varchar(200) DEFAULT NULL COMMENT '说明',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `logisticsCompany` varchar(200) DEFAULT NULL COMMENT '物流公司',
  `logisticsNumber` varchar(50) DEFAULT NULL COMMENT '物流查询号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退货统计单';

-- ----------------------------
-- Records of orderform_return_order
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_return_order_item_detail`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_return_order_item_detail`;
CREATE TABLE `orderform_return_order_item_detail` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `subOrderId` bigint(11) NOT NULL COMMENT '子单ID',
  `orderItemId` bigint(11) NOT NULL COMMENT '订单项ID',
  `orderItemDetailId` bigint(11) NOT NULL COMMENT '订单项明细ID',
  `state` int(2) DEFAULT NULL COMMENT '状态(1:已申请 2:通过 3:未通过 )',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '申请退货时间',
  `number` int(2) DEFAULT NULL COMMENT '数量',
  `sum` double(10,2) DEFAULT NULL COMMENT '退款金额',
  `explain` varchar(200) DEFAULT NULL COMMENT '说明',
  `reason` varchar(200) DEFAULT NULL COMMENT '原因',
  `returnId` bigint(11) NOT NULL COMMENT '退货总计单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退货单';

-- ----------------------------
-- Records of orderform_return_order_item_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `orderform_sub_order`
-- ----------------------------
DROP TABLE IF EXISTS `orderform_sub_order`;
CREATE TABLE `orderform_sub_order` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `orderId` bigint(11) NOT NULL COMMENT '总单ID',
  `merchantId` bigint(11) NOT NULL COMMENT '商家ID',
  `storeId` bigint(11) NOT NULL COMMENT '门店ID',
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '子单号',
  `sum` double(10,2) DEFAULT NULL COMMENT '订单金额',
  `state` int(2) DEFAULT NULL COMMENT '状态,待付款,已付款,待发货,已发货,已签收',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='子单';

-- ----------------------------
-- Records of orderform_sub_order
-- ----------------------------
