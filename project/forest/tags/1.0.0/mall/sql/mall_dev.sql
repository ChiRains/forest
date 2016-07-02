-- ----------------------------
-- Table structure for `publicservice_message_user_000`
-- ----------------------------
DROP TABLE IF EXISTS `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_000` (
  `id` bigint(11) NOT NULL,
  `typeId` bigint(11) NOT NULL,
  `receiver` bigint(11) DEFAULT NULL COMMENT '消息接收人',
  `title` varchar(200) DEFAULT NULL COMMENT '消息标题',
  `content` text DEFAULT NULL COMMENT '消息内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `state` int(2) DEFAULT NULL COMMENT '是否已读 1未读  2是  3删除',
  `classify` int(2) DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的消息';

-- ----------------------------
-- Records of publicservice_message
-- ----------------------------

DROP TABLE IF EXISTS `publicservice_message_user_001`;
DROP TABLE IF EXISTS `publicservice_message_user_002`;
DROP TABLE IF EXISTS `publicservice_message_user_003`;
DROP TABLE IF EXISTS `publicservice_message_user_004`;
DROP TABLE IF EXISTS `publicservice_message_user_005`;
DROP TABLE IF EXISTS `publicservice_message_user_006`;
DROP TABLE IF EXISTS `publicservice_message_user_007`;
DROP TABLE IF EXISTS `publicservice_message_user_008`;
DROP TABLE IF EXISTS `publicservice_message_user_009`;
DROP TABLE IF EXISTS `publicservice_message_user_010`;
DROP TABLE IF EXISTS `publicservice_message_user_011`;
DROP TABLE IF EXISTS `publicservice_message_user_012`;
DROP TABLE IF EXISTS `publicservice_message_user_013`;
DROP TABLE IF EXISTS `publicservice_message_user_014`;
DROP TABLE IF EXISTS `publicservice_message_user_015`;
DROP TABLE IF EXISTS `publicservice_message_user_016`;
DROP TABLE IF EXISTS `publicservice_message_user_017`;
DROP TABLE IF EXISTS `publicservice_message_user_018`;
DROP TABLE IF EXISTS `publicservice_message_user_019`;
DROP TABLE IF EXISTS `publicservice_message_user_020`;
DROP TABLE IF EXISTS `publicservice_message_user_021`;
DROP TABLE IF EXISTS `publicservice_message_user_022`;
DROP TABLE IF EXISTS `publicservice_message_user_023`;
DROP TABLE IF EXISTS `publicservice_message_user_024`;
DROP TABLE IF EXISTS `publicservice_message_user_025`;
DROP TABLE IF EXISTS `publicservice_message_user_026`;
DROP TABLE IF EXISTS `publicservice_message_user_027`;
DROP TABLE IF EXISTS `publicservice_message_user_028`;
DROP TABLE IF EXISTS `publicservice_message_user_029`;
DROP TABLE IF EXISTS `publicservice_message_user_030`;
DROP TABLE IF EXISTS `publicservice_message_user_031`;
DROP TABLE IF EXISTS `publicservice_message_user_032`;
DROP TABLE IF EXISTS `publicservice_message_user_033`;
DROP TABLE IF EXISTS `publicservice_message_user_034`;
DROP TABLE IF EXISTS `publicservice_message_user_035`;
DROP TABLE IF EXISTS `publicservice_message_user_036`;
DROP TABLE IF EXISTS `publicservice_message_user_037`;
DROP TABLE IF EXISTS `publicservice_message_user_038`;
DROP TABLE IF EXISTS `publicservice_message_user_039`;
DROP TABLE IF EXISTS `publicservice_message_user_040`;
DROP TABLE IF EXISTS `publicservice_message_user_041`;
DROP TABLE IF EXISTS `publicservice_message_user_042`;
DROP TABLE IF EXISTS `publicservice_message_user_043`;
DROP TABLE IF EXISTS `publicservice_message_user_044`;
DROP TABLE IF EXISTS `publicservice_message_user_045`;
DROP TABLE IF EXISTS `publicservice_message_user_046`;
DROP TABLE IF EXISTS `publicservice_message_user_047`;
DROP TABLE IF EXISTS `publicservice_message_user_048`;
DROP TABLE IF EXISTS `publicservice_message_user_049`;
DROP TABLE IF EXISTS `publicservice_message_user_050`;
DROP TABLE IF EXISTS `publicservice_message_user_051`;
DROP TABLE IF EXISTS `publicservice_message_user_052`;
DROP TABLE IF EXISTS `publicservice_message_user_053`;
DROP TABLE IF EXISTS `publicservice_message_user_054`;
DROP TABLE IF EXISTS `publicservice_message_user_055`;
DROP TABLE IF EXISTS `publicservice_message_user_056`;
DROP TABLE IF EXISTS `publicservice_message_user_057`;
DROP TABLE IF EXISTS `publicservice_message_user_058`;
DROP TABLE IF EXISTS `publicservice_message_user_059`;
DROP TABLE IF EXISTS `publicservice_message_user_060`;
DROP TABLE IF EXISTS `publicservice_message_user_061`;
DROP TABLE IF EXISTS `publicservice_message_user_062`;
DROP TABLE IF EXISTS `publicservice_message_user_063`;
DROP TABLE IF EXISTS `publicservice_message_user_064`;
DROP TABLE IF EXISTS `publicservice_message_user_065`;
DROP TABLE IF EXISTS `publicservice_message_user_066`;
DROP TABLE IF EXISTS `publicservice_message_user_067`;
DROP TABLE IF EXISTS `publicservice_message_user_068`;
DROP TABLE IF EXISTS `publicservice_message_user_069`;
DROP TABLE IF EXISTS `publicservice_message_user_070`;
DROP TABLE IF EXISTS `publicservice_message_user_071`;
DROP TABLE IF EXISTS `publicservice_message_user_072`;
DROP TABLE IF EXISTS `publicservice_message_user_073`;
DROP TABLE IF EXISTS `publicservice_message_user_074`;
DROP TABLE IF EXISTS `publicservice_message_user_075`;
DROP TABLE IF EXISTS `publicservice_message_user_076`;
DROP TABLE IF EXISTS `publicservice_message_user_077`;
DROP TABLE IF EXISTS `publicservice_message_user_078`;
DROP TABLE IF EXISTS `publicservice_message_user_079`;
DROP TABLE IF EXISTS `publicservice_message_user_080`;
DROP TABLE IF EXISTS `publicservice_message_user_081`;
DROP TABLE IF EXISTS `publicservice_message_user_082`;
DROP TABLE IF EXISTS `publicservice_message_user_083`;
DROP TABLE IF EXISTS `publicservice_message_user_084`;
DROP TABLE IF EXISTS `publicservice_message_user_085`;
DROP TABLE IF EXISTS `publicservice_message_user_086`;
DROP TABLE IF EXISTS `publicservice_message_user_087`;
DROP TABLE IF EXISTS `publicservice_message_user_088`;
DROP TABLE IF EXISTS `publicservice_message_user_089`;
DROP TABLE IF EXISTS `publicservice_message_user_090`;
DROP TABLE IF EXISTS `publicservice_message_user_091`;
DROP TABLE IF EXISTS `publicservice_message_user_092`;
DROP TABLE IF EXISTS `publicservice_message_user_093`;
DROP TABLE IF EXISTS `publicservice_message_user_094`;
DROP TABLE IF EXISTS `publicservice_message_user_095`;
DROP TABLE IF EXISTS `publicservice_message_user_096`;
DROP TABLE IF EXISTS `publicservice_message_user_097`;
DROP TABLE IF EXISTS `publicservice_message_user_098`;
DROP TABLE IF EXISTS `publicservice_message_user_099`;

CREATE TABLE `publicservice_message_user_001` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_002` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_003` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_004` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_005` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_006` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_007` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_008` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_009` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_010` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_011` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_012` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_013` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_014` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_015` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_016` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_017` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_018` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_019` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_020` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_021` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_022` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_023` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_024` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_025` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_026` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_027` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_028` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_029` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_030` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_031` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_032` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_033` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_034` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_035` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_036` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_037` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_038` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_039` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_040` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_041` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_042` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_043` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_044` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_045` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_046` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_047` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_048` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_049` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_050` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_051` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_052` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_053` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_054` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_055` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_056` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_057` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_058` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_059` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_060` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_061` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_062` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_063` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_064` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_065` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_066` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_067` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_068` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_069` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_070` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_071` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_072` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_073` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_074` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_075` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_076` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_077` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_078` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_079` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_080` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_081` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_082` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_083` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_084` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_085` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_086` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_087` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_088` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_089` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_090` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_091` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_092` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_093` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_094` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_095` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_096` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_097` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_098` LIKE `publicservice_message_user_000`;
CREATE TABLE `publicservice_message_user_099` LIKE `publicservice_message_user_000`;

-- ----------------------------
-- Table structure for `publicservice_message_merchant_000`
-- ----------------------------
DROP TABLE IF EXISTS `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_000` (
  `id` bigint(11) NOT NULL,
  `typeId` bigint(11) NOT NULL,
  `receiver` bigint(11) DEFAULT NULL COMMENT '消息接收人',
  `title` varchar(200) DEFAULT NULL COMMENT '消息标题',
  `content` text DEFAULT NULL COMMENT '消息内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `state` int(2) DEFAULT NULL COMMENT '是否已读 1未读  2是  3删除',
  `classify` int(2) DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的消息';

-- ----------------------------
-- Records of publicservice_message
-- ----------------------------

DROP TABLE IF EXISTS `publicservice_message_merchant_001`;
DROP TABLE IF EXISTS `publicservice_message_merchant_002`;
DROP TABLE IF EXISTS `publicservice_message_merchant_003`;
DROP TABLE IF EXISTS `publicservice_message_merchant_004`;
DROP TABLE IF EXISTS `publicservice_message_merchant_005`;
DROP TABLE IF EXISTS `publicservice_message_merchant_006`;
DROP TABLE IF EXISTS `publicservice_message_merchant_007`;
DROP TABLE IF EXISTS `publicservice_message_merchant_008`;
DROP TABLE IF EXISTS `publicservice_message_merchant_009`;

CREATE TABLE `publicservice_message_merchant_001` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_002` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_003` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_004` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_005` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_006` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_007` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_008` LIKE `publicservice_message_merchant_000`;
CREATE TABLE `publicservice_message_merchant_009` LIKE `publicservice_message_merchant_000`;


-- ----------------------------
-- Table structure for `publicservice_message_store_000`
-- ----------------------------
DROP TABLE IF EXISTS `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_000` (
  `id` bigint(11) NOT NULL,
  `typeId` bigint(11) NOT NULL,
  `receiver` bigint(11) DEFAULT NULL COMMENT '消息接收人',
  `title` varchar(200) DEFAULT NULL COMMENT '消息标题',
  `content` text DEFAULT NULL COMMENT '消息内容',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  `state` int(2) DEFAULT NULL COMMENT '是否已读 1未读  2是  3删除',
  `classify` int(2) DEFAULT NULL COMMENT '消息类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的消息';

-- ----------------------------
-- Records of publicservice_message
-- ----------------------------

DROP TABLE IF EXISTS `publicservice_message_store_001`;
DROP TABLE IF EXISTS `publicservice_message_store_002`;
DROP TABLE IF EXISTS `publicservice_message_store_003`;
DROP TABLE IF EXISTS `publicservice_message_store_004`;
DROP TABLE IF EXISTS `publicservice_message_store_005`;
DROP TABLE IF EXISTS `publicservice_message_store_006`;
DROP TABLE IF EXISTS `publicservice_message_store_007`;
DROP TABLE IF EXISTS `publicservice_message_store_008`;
DROP TABLE IF EXISTS `publicservice_message_store_009`;

CREATE TABLE `publicservice_message_store_001` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_002` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_003` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_004` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_005` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_006` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_007` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_008` LIKE `publicservice_message_store_000`;
CREATE TABLE `publicservice_message_store_009` LIKE `publicservice_message_store_000`;