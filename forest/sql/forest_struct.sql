

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `forest_analysisresult`
-- ----------------------------
DROP TABLE IF EXISTS `forest_analysisresult`;
CREATE TABLE `forest_analysisresult` (
  `id` bigint(11) NOT NULL DEFAULT '0' COMMENT 'id',
  `code` varchar(100) DEFAULT NULL COMMENT '唯一编码',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '描述',
  `type` int(10) DEFAULT NULL COMMENT '类型(1.血压分析，2.计算BMI)',
  `previousData` double(10,2) DEFAULT NULL COMMENT '开始数据',
  `afterData` double(10,2) DEFAULT NULL COMMENT '后一个数据',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='血压分析结果，计算BMI分析结果';

-- ----------------------------
-- Records of forest_analysisresult
-- ----------------------------

-- ----------------------------
-- Table structure for `forest_medication`
-- ----------------------------
DROP TABLE IF EXISTS `forest_medication`;
CREATE TABLE `forest_medication` (
  `id` bigint(11) NOT NULL,
  `theme` varchar(50) NOT NULL COMMENT '提醒名称',
  `image` varchar(200) DEFAULT NULL COMMENT '图片',
  `medicine` varchar(250) DEFAULT NULL COMMENT '添加药品',
  `objectName` varchar(50) DEFAULT NULL COMMENT '添加对象',
  `dosage` int(11) DEFAULT NULL COMMENT '每次用量',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位',
  `desc` varchar(200) DEFAULT NULL COMMENT '个人备注',
  `enable` tinyint(4) DEFAULT NULL COMMENT '是否启用',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户id',
  `recordTime` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用药提醒';

-- ----------------------------
-- Records of forest_medication
-- ----------------------------

-- ----------------------------
-- Table structure for `forest_medication_time`
-- ----------------------------
DROP TABLE IF EXISTS `forest_medication_time`;
CREATE TABLE `forest_medication_time` (
  `id` bigint(11) NOT NULL,
  `medicationId` bigint(11) NOT NULL COMMENT '用药id',
  `takeTime` varchar(50) DEFAULT NULL COMMENT '服用时间',
  `enable` tinyint(4) DEFAULT NULL COMMENT '是否启用',
  `userId` bigint(11) DEFAULT NULL COMMENT '用户id',
  `excuteTime` datetime DEFAULT NULL COMMENT '下次执行时间',
  `recordTime` datetime DEFAULT NULL COMMENT '提交时间',
  `endTime` datetime DEFAULT NULL COMMENT '截止时间',
  `periodType` tinyint(4) DEFAULT NULL COMMENT '服药周期(1: 每日  2: 每周  3: 每月 )',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `excuteTime` (`excuteTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用药提醒';

-- ----------------------------
-- Records of forest_medication_time
-- ----------------------------

