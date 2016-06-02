/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.61
Source Server Version : 50616
Source Host           : 10.10.11.61:3306
Source Database       : personalcenter_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-03-03 15:24:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `personalcenter_grade`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_grade`;
CREATE TABLE `personalcenter_grade` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `desc` varchar(200) DEFAULT NULL COMMENT '等级描述',
  `point` int(11) DEFAULT '0' COMMENT '积分',
  `discount` int(11) DEFAULT '0' COMMENT '折扣',
  `image` varchar(200) DEFAULT NULL COMMENT '图标',
  `integralRatio` int(2) DEFAULT NULL,
  `commissionEnable` tinyint(4) DEFAULT NULL COMMENT '是否分佣',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_grade
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_grade_20160120`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_grade_20160120`;
CREATE TABLE `personalcenter_grade_20160120` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `desc` varchar(200) DEFAULT NULL COMMENT '等级描述',
  `point` int(11) DEFAULT '0' COMMENT '积分',
  `discount` int(11) DEFAULT '0' COMMENT '折扣',
  `image` varchar(200) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_grade_20160120
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_membership_card_warehouse`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_membership_card_warehouse`;
CREATE TABLE `personalcenter_membership_card_warehouse` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `cardNumber` varchar(30) DEFAULT NULL COMMENT '卡号',
  `state` int(2) DEFAULT NULL COMMENT '状态 1初始化 2已使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_membership_card_warehouse
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_my_bank_card`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_my_bank_card`;
CREATE TABLE `personalcenter_my_bank_card` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `cardholder` varchar(255) DEFAULT NULL,
  `bank` varchar(100) DEFAULT NULL COMMENT '银行',
  `card` varchar(100) DEFAULT NULL COMMENT '卡',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_my_bank_card
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_my_commission_withdraw_cash`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_my_commission_withdraw_cash`;
CREATE TABLE `personalcenter_my_commission_withdraw_cash` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `wealthId` bigint(11) DEFAULT NULL COMMENT '财富账号',
  `wealthDetailId` bigint(11) DEFAULT NULL COMMENT '财富明细',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `commissionCash` double(10,2) DEFAULT NULL COMMENT '当次提现值',
  `cardholder` varchar(100) DEFAULT NULL COMMENT '持卡人',
  `bank` varchar(100) DEFAULT NULL COMMENT '银行',
  `card` varchar(100) DEFAULT NULL COMMENT '卡',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发生时间',
  `completeTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '完成时间',
  `state` int(2) DEFAULT NULL COMMENT '类型1. 申请 2.审核  3.成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_my_commission_withdraw_cash
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_my_sign_in_day`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_my_sign_in_day`;
CREATE TABLE `personalcenter_my_sign_in_day` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `year` int(4) DEFAULT NULL COMMENT '年',
  `month` int(4) DEFAULT NULL COMMENT '月',
  `day` int(4) DEFAULT NULL COMMENT '日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_my_sign_in_day
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_my_sign_in_month`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_my_sign_in_month`;
CREATE TABLE `personalcenter_my_sign_in_month` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `year` int(4) DEFAULT NULL COMMENT '年',
  `month` int(4) DEFAULT NULL COMMENT '月',
  `record` varchar(255) DEFAULT NULL COMMENT '记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_my_sign_in_month
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_my_sign_in_statistics`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_my_sign_in_statistics`;
CREATE TABLE `personalcenter_my_sign_in_statistics` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `total` int(4) DEFAULT NULL COMMENT '签到总数',
  `maxSignIn` int(4) DEFAULT NULL COMMENT '最大连签记录',
  `currentSignIn` int(4) DEFAULT NULL COMMENT '当前连签记录',
  `lastSignInDay` timestamp(6) NULL DEFAULT NULL,
  `firstSignInDay` timestamp(6) NULL DEFAULT NULL,
  `firstIntegral` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_my_sign_in_statistics
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_my_wealth`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_my_wealth`;
CREATE TABLE `personalcenter_my_wealth` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `integral` bigint(11) DEFAULT NULL COMMENT '积分',
  `commission` double(10,2) DEFAULT NULL COMMENT '佣金',
  `consumptionCurrency` double(10,2) DEFAULT NULL COMMENT '消费币',
  `integralSummation` bigint(11) DEFAULT NULL COMMENT '积分总计',
  `commissionSummation` double(10,2) DEFAULT NULL COMMENT '佣金总计',
  `consumptionCurrencySummation` double(10,2) DEFAULT NULL COMMENT '消费币总计',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `version` bigint(11) NOT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_my_wealth
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_my_wealth_detail`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_my_wealth_detail`;
CREATE TABLE `personalcenter_my_wealth_detail` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `wealthId` bigint(11) DEFAULT NULL COMMENT '财富账号',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `point` double(10,2) DEFAULT NULL COMMENT '当次财富值',
  `remainder` double(10,2) DEFAULT NULL COMMENT '余额',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发生时间',
  `desc` varchar(500) DEFAULT NULL COMMENT '财富描述',
  `type` int(2) DEFAULT NULL COMMENT '类型1. 积分 2.  佣金  3. 消费币  4.  投资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_my_wealth_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_user`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_user`;
CREATE TABLE `personalcenter_user` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `membershipCard` varchar(100) DEFAULT NULL COMMENT '会员卡',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT 'Email',
  `accountGroup` varchar(100) DEFAULT NULL COMMENT '账号组别',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `headImage` varchar(200) DEFAULT NULL COMMENT '头像',
  `state` int(2) DEFAULT NULL COMMENT '状态',
  `type` int(2) DEFAULT NULL COMMENT '用户类别',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `gradeId` bigint(11) NOT NULL COMMENT '等级',
  `birthYear` int(4) DEFAULT NULL COMMENT '出生年',
  `birthMonth` int(4) DEFAULT NULL COMMENT '出生月',
  `birthDay` int(4) DEFAULT NULL COMMENT '出生日',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `district` varchar(100) DEFAULT NULL COMMENT '区',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_user
-- ----------------------------

-- ----------------------------
-- Table structure for `personalcenter_user_third`
-- ----------------------------
DROP TABLE IF EXISTS `personalcenter_user_third`;
CREATE TABLE `personalcenter_user_third` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户',
  `thirdId` varchar(200) DEFAULT NULL COMMENT '第三方ID',
  `accountType` int(2) DEFAULT NULL COMMENT '类型 1注册用户 2QQ用户 3微信用户 4微博用户',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personalcenter_user_third
-- ----------------------------
