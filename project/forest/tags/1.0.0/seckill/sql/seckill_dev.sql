/*
Navicat MySQL Data Transfer

Source Server         : 10.10.11.60
Source Server Version : 50616
Source Host           : 10.10.11.60:3306
Source Database       : seckill_dev

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2016-01-12 22:43:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `seckill_merchandise_seckill`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_merchandise_seckill`;
CREATE TABLE `seckill_merchandise_seckill` (
  `id` bigint(11) NOT NULL,
  `screeningsId` bigint(11) DEFAULT NULL COMMENT '场次',
  `maketingId` bigint(11) DEFAULT NULL,
  `discountPrice` double(10,2) DEFAULT NULL COMMENT '折扣价',
  `mallClassifyId` bigint(11) DEFAULT NULL COMMENT '商城分类ID',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seckill_merchandise_seckill
-- ----------------------------

-- ----------------------------
-- Table structure for `seckill_screenings`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_screenings`;
CREATE TABLE `seckill_screenings` (
  `id` bigint(11) NOT NULL,
  `beginTime` timestamp(6) NULL DEFAULT NULL,
  `endTime` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='场次';

-- ----------------------------
-- Records of seckill_screenings
-- ----------------------------

-- ----------------------------
-- Table structure for `seckill_screenings_slide`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_screenings_slide`;
CREATE TABLE `seckill_screenings_slide` (
  `id` bigint(11) NOT NULL COMMENT 'ID',
  `screeningsId` bigint(11) DEFAULT NULL COMMENT '场次',
  `clickUrl` varchar(200) DEFAULT NULL COMMENT '点击地址',
  `image` varchar(200) DEFAULT NULL COMMENT '图片',
  `orderNum` int(2) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='幻灯片';

-- ----------------------------
-- Records of seckill_screenings_slide
-- ----------------------------
