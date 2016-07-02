

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `forest_analysisresult`
-- ----------------------------
DROP TABLE IF EXISTS `forest_analysisresult`;
CREATE TABLE `forest_analysisresult` (
  `id` bigint(11) NOT NULL DEFAULT '0' COMMENT 'id',
  `code` varchar(100) DEFAULT NULL COMMENT 'Ψһ����',
  `name` varchar(100) DEFAULT NULL COMMENT '����',
  `description` text COMMENT '����',
  `type` int(10) DEFAULT NULL COMMENT '����(1.Ѫѹ������2.����BMI)',
  `previousData` double(10,2) DEFAULT NULL COMMENT '��ʼ����',
  `afterData` double(10,2) DEFAULT NULL COMMENT '��һ������',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ѫѹ�������������BMI�������';

-- ----------------------------
-- Records of forest_analysisresult
-- ----------------------------

-- ----------------------------
-- Table structure for `forest_medication`
-- ----------------------------
DROP TABLE IF EXISTS `forest_medication`;
CREATE TABLE `forest_medication` (
  `id` bigint(11) NOT NULL,
  `theme` varchar(50) NOT NULL COMMENT '��������',
  `image` varchar(200) DEFAULT NULL COMMENT 'ͼƬ',
  `medicine` varchar(250) DEFAULT NULL COMMENT '���ҩƷ',
  `objectName` varchar(50) DEFAULT NULL COMMENT '��Ӷ���',
  `dosage` int(11) DEFAULT NULL COMMENT 'ÿ������',
  `unit` varchar(50) DEFAULT NULL COMMENT '��λ',
  `desc` varchar(200) DEFAULT NULL COMMENT '���˱�ע',
  `enable` tinyint(4) DEFAULT NULL COMMENT '�Ƿ�����',
  `userId` bigint(11) DEFAULT NULL COMMENT '�û�id',
  `recordTime` datetime DEFAULT NULL COMMENT '�ύʱ��',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҩ����';

-- ----------------------------
-- Records of forest_medication
-- ----------------------------

-- ----------------------------
-- Table structure for `forest_medication_time`
-- ----------------------------
DROP TABLE IF EXISTS `forest_medication_time`;
CREATE TABLE `forest_medication_time` (
  `id` bigint(11) NOT NULL,
  `medicationId` bigint(11) NOT NULL COMMENT '��ҩid',
  `takeTime` varchar(50) DEFAULT NULL COMMENT '����ʱ��',
  `enable` tinyint(4) DEFAULT NULL COMMENT '�Ƿ�����',
  `userId` bigint(11) DEFAULT NULL COMMENT '�û�id',
  `excuteTime` datetime DEFAULT NULL COMMENT '�´�ִ��ʱ��',
  `recordTime` datetime DEFAULT NULL COMMENT '�ύʱ��',
  `endTime` datetime DEFAULT NULL COMMENT '��ֹʱ��',
  `periodType` tinyint(4) DEFAULT NULL COMMENT '��ҩ����(1: ÿ��  2: ÿ��  3: ÿ�� )',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `excuteTime` (`excuteTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ҩ����';

-- ----------------------------
-- Records of forest_medication_time
-- ----------------------------

