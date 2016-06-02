
INSERT INTO `parameter_param` VALUES ('1010017000000004', 'merchandise-classify-specifications-number', 'merchandise-classify-specifications-number', '2', '1', '2', '商品分类对应规格属性个数');
INSERT INTO `parameter_param` VALUES ('1010017000000005', 'marketing-slide-sence-type', 'marketing-slide-sence-type', '1', '1', '1:万商创富商城;2:源生联盟商城', '促销广告栏图片类型');
INSERT INTO `parameter_param` VALUES ('1010017000000001', 'personalcenter-account-data-type-key', 'personalcenter-account-data-type-key', '1', '1', 'mobile', '系统用户账号支持1 mobile 2 email');
INSERT INTO `parameter_param` VALUES ('1010017000000002', 'personalcenter-user-default-password', 'personalcenter-user-default-password', '1', '1', '123456', '用户默认密码');
INSERT INTO `parameter_param` VALUES ('1010017000000006', 'personalcenter-user-sms-template', 'personalcenter-user-sms-template', '1', '1', '您好，注册码是{code},有效时间{minute},请xxxx', '用户注册短信验证码');
INSERT INTO `parameter_param` VALUES ('1010017000000007', 'personalcenter-user-grade', 'personalcenter-user-grade', '3', '1', '1010017000000001', '用户注册默认级别');
INSERT INTO `parameter_param` VALUES ('1010017000000003', 'sellercenter-member-default-password', 'sellercenter-member-default-password', '1', '1', '654321', '企业用户默认密码');

INSERT INTO `parameter_param` VALUES ('1010017000000010', 'ums-86-enterpriseNumber', 'ums-86-enterpriseNumber', '1', '1', '223185', '联通一信通企业号');
INSERT INTO `parameter_param` VALUES ('1010017000000011', 'ums-86-adminName', 'ums-86-adminName', '1', '1', 'admin2', '联通一信通账号');
INSERT INTO `parameter_param` VALUES ('1010017000000012', 'ums-86-adminPsw', 'ums-86-adminPsw', '1', '1', 'qcloud#@3399366', '联通一信通密码');

INSERT INTO `parameter_param` VALUES ('1010017000000020', 'weixin-appId', 'weixin-appId', '1', '1', 'wx3fd5da72655f8c3c', '联通一信通企业号');
INSERT INTO `parameter_param` VALUES ('1010017000000021', 'weixin-appSecret', 'weixin-appSecret', '1', '1', '6cd0911b47ba90c116ac5e133537a75d', '联通一信通账号');
INSERT INTO `parameter_param` VALUES ('1010017000000022', 'weixin-pay-mchID', 'weixin-pay-mchID', '1', '1', '1234237002', '联通一信通密码');
INSERT INTO `parameter_param` VALUES ('1010017000000023', 'weixin-pay-certPassword', 'weixin-pay-certPassword', '1', '1', '1234237002', '联通一信通账号');
INSERT INTO `parameter_param` VALUES ('1010017000000024', 'weixin-pay-key', 'weixin-pay-key', '1', '1', '526t2Jx9IVXY2t70JVaG8I87635sso8U', '联通一信通密码');

INSERT INTO `permission_role` VALUES ('1010017000000101', '商家职员', '商家职员', '1010017000000002');
INSERT INTO `permission_role` VALUES ('1010017000000102', '门店职员', '门店职员', '1010017000000002');
INSERT INTO `permission_role` VALUES ('1010017000000103', '商品审核员', '商品审核员', '1010017000000002');

-- 商城菜单分类
INSERT INTO `permission_catalog` VALUES ('1010017000000101', '基础设置', '1010', '1');
INSERT INTO `permission_catalog` VALUES ('1010017000000102', '会员中心', '1020', '1');
INSERT INTO `permission_catalog` VALUES ('1010017000000103', '卖家中心', '1030', '1');
INSERT INTO `permission_catalog` VALUES ('1010017000000104', '商品中心', '1040', '1');
INSERT INTO `permission_catalog` VALUES ('1010017000000105', '订单管理', '1050', '1');
INSERT INTO `permission_catalog` VALUES ('1010017000000106', '评价管理', '1060', '1');
INSERT INTO `permission_catalog` VALUES ('1010017000000107', '分销设置', '1070', '1');
INSERT INTO `permission_catalog` VALUES ('1010017000000108', '营销管理', '1080', '1');

INSERT INTO `permission_menu` VALUES ('1010017000000101', '省', '', '1010', 'admin/province/list', '1010017000000101');
INSERT INTO `permission_menu` VALUES ('1010017000000102', '市', '', '1020', 'admin/city/list', '1010017000000101');
INSERT INTO `permission_menu` VALUES ('1010017000000103', '区', '', '1030', 'admin/district/list', '1010017000000101');
-- INSERT INTO `permission_menu` VALUES ('1010017000000104', '分类管理', '', '1040', 'admin/classify/list', '1010017000000101');
INSERT INTO `permission_menu` VALUES ('1010017000000105', '短信设置', '', '1050', 'admin/umsConfig/getUmsConfig', '1010017000000101');
INSERT INTO `permission_menu` VALUES ('1010017000000106', '微信设置', '', '1060', 'admin/weixinConfig/getWeiXinConfig', '1010017000000101');
INSERT INTO `permission_menu` VALUES ('1010017000000107', '支付设置', '', '1070', 'admin/paySetting/list', '1010017000000101');
INSERT INTO `permission_menu` VALUES ('1010017000000108', '支付时间设置', '', '1080', 'admin/paySetting/settingTime', '1010017000000101');

INSERT INTO `permission_permission` VALUES ('1010017000000101', '省', '1', '1010017000000101');
INSERT INTO `permission_permission` VALUES ('1010017000000102', '市', '1', '1010017000000102');
INSERT INTO `permission_permission` VALUES ('1010017000000103', '区', '1', '1010017000000103');
-- INSERT INTO `permission_permission` VALUES ('1010017000000104', '分类管理', '1', '1010017000000104');
INSERT INTO `permission_permission` VALUES ('1010017000000105', '短信设置', '1', '1010017000000105');
INSERT INTO `permission_permission` VALUES ('1010017000000106', '微信设置', '1', '1010017000000106');
INSERT INTO `permission_permission` VALUES ('1010017000000107', '支付设置', '1', '1010017000000107');
INSERT INTO `permission_permission` VALUES ('1010017000000108', '支付时间设置', '1', '1010017000000108');

INSERT INTO `permission_role_permission` VALUES ('1010017000000101', '1010017000000101', '1010017000000001');
INSERT INTO `permission_role_permission` VALUES ('1010017000000102', '1010017000000102', '1010017000000001');
INSERT INTO `permission_role_permission` VALUES ('1010017000000103', '1010017000000103', '1010017000000001');
-- INSERT INTO `permission_role_permission` VALUES ('1010017000000104', '1010017000000104', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000105', '1010017000000105', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000106', '1010017000000106', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000107', '1010017000000107', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000108', '1010017000000108', '1010017000000002');

INSERT INTO `permission_menu` VALUES ('1010017000000121', '用户等级', '', '1210', 'admin/grade/list', '1010017000000102');
INSERT INTO `permission_menu` VALUES ('1010017000000122', '用户', '', '1220', 'admin/user/list', '1010017000000102');
INSERT INTO `permission_menu` VALUES ('1010017000000123', '用户财务', '', '1230', 'admin/myWealth/list', '1010017000000102');

INSERT INTO `permission_permission` VALUES ('1010017000000121', '用户等级', '1', '1010017000000121');
INSERT INTO `permission_permission` VALUES ('1010017000000122', '用户', '1', '1010017000000122');
INSERT INTO `permission_permission` VALUES ('1010017000000123', '用户财务', '1', '1010017000000123');

INSERT INTO `permission_role_permission` VALUES ('1010017000000151', '1010017000000121', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000152', '1010017000000122', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000153', '1010017000000123', '1010017000000002');

INSERT INTO `permission_menu` VALUES ('1010017000000141', '商家分类', '', '1410', 'admin/classifyMerchant/list', '1010017000000103');
INSERT INTO `permission_menu` VALUES ('1010017000000142', '商家列表', '', '1420', 'admin/merchant/list', '1010017000000103');
INSERT INTO `permission_menu` VALUES ('1010017000000143', '优质商家', '', '1430', 'admin/merchantSort/list', '1010017000000103');
INSERT INTO `permission_menu` VALUES ('1010017000000144', '门店管理', '', '1440', 'admin/store/list', '1010017000000103');
INSERT INTO `permission_menu` VALUES ('1010017000000145', '商家信息维护', '', '1450', 'admin/merchant/toPerfect', '1010017000000103');
-- INSERT INTO `permission_menu` VALUES ('1010017000000146', '区域中心点', '', '1460', 'admin/neighbourhood/list', '1010017000000103');
INSERT INTO `permission_menu` VALUES ('1010017000000147', '距离设置', '', '1470', 'admin/distanceConfig/getDistanceConfig', '1010017000000103');

INSERT INTO `permission_permission` VALUES ('1010017000000141', '商家分类', '1', '1010017000000141');
INSERT INTO `permission_permission` VALUES ('1010017000000142', '商家列表', '1', '1010017000000142');
INSERT INTO `permission_permission` VALUES ('1010017000000143', '优质商家', '1', '1010017000000143');
INSERT INTO `permission_permission` VALUES ('1010017000000144', '门店管理', '1', '1010017000000144');
INSERT INTO `permission_permission` VALUES ('1010017000000145', '商家信息维护', '1', '1010017000000145');
-- INSERT INTO `permission_permission` VALUES ('1010017000000146', '区域中心点', '1', '1010017000000146');
INSERT INTO `permission_permission` VALUES ('1010017000000147', '距离设置', '1', '1010017000000147');

INSERT INTO `permission_role_permission` VALUES ('1010017000000201', '1010017000000141', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000202', '1010017000000142', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000203', '1010017000000143', '1010017000000002');
-- INSERT INTO `permission_role_permission` VALUES ('1010017000000204', '1010017000000146', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000205', '1010017000000147', '1010017000000002');

INSERT INTO `permission_role_permission` VALUES ('1010017000000206', '1010017000000144', '1010017000000101');
INSERT INTO `permission_role_permission` VALUES ('1010017000000207', '1010017000000145', '1010017000000101');

-- INSERT INTO `permission_menu` VALUES ('1010017000000161', '属性管理', '', '1610', 'admin/attributeDefinition/list', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000162', '商城商品分类', '', '1620', 'admin/classifyAttribute/classifyList', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000163', '商家商品分类', '', '1630', 'admin/classifyMerchandise/listForMerchant', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000164', '商品管理', '', '1640', 'admin/merchandise/list', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000165', '商品审核', '', '1650', 'admin/merchandise/list4UnAudit', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000166', '库存管理', '', '1670', 'admin/merchandiseStock/list', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000167', '规格类目', '', '1618', 'admin/classifyAttribute/list', '1010017000000104');
-- INSERT INTO `permission_menu` VALUES ('1010017000000168', '商品审核', '', '1680', 'admin/merchandise/list4UnAudit', '1010017000000104');
-- INSERT INTO `permission_menu` VALUES ('1010017000000169', '规格管理', '', '1615', 'admin/attributeDefinition/specList', '1010017000000104');


-- INSERT INTO `permission_permission` VALUES ('1010017000000161', '属性管理', '1', '1010017000000161');
INSERT INTO `permission_permission` VALUES ('1010017000000162', '商城商品分类', '1', '1010017000000162');
INSERT INTO `permission_permission` VALUES ('1010017000000163', '商家商品分类', '1', '1010017000000163');
INSERT INTO `permission_permission` VALUES ('1010017000000164', '商品管理', '1', '1010017000000164');
INSERT INTO `permission_permission` VALUES ('1010017000000165', '商品审核', '1', '1010017000000165');
INSERT INTO `permission_permission` VALUES ('1010017000000166', '库存管理', '1', '1010017000000166');
INSERT INTO `permission_permission` VALUES ('1010017000000167', '规格类目', '1', '1010017000000167');
-- INSERT INTO `permission_permission` VALUES ('1010017000000168', '商品审核', '1', '1010017000000168');
-- INSERT INTO `permission_permission` VALUES ('1010017000000169', '商品管理', '1', '1010017000000169');


-- INSERT INTO `permission_role_permission` VALUES ('1010017000000251', '1010017000000161', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000252', '1010017000000162', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000253', '1010017000000163', '1010017000000101');
INSERT INTO `permission_role_permission` VALUES ('1010017000000254', '1010017000000164', '1010017000000101');
INSERT INTO `permission_role_permission` VALUES ('1010017000000255', '1010017000000165', '1010017000000103');
INSERT INTO `permission_role_permission` VALUES ('1010017000000256', '1010017000000166', '1010017000000102');
INSERT INTO `permission_role_permission` VALUES ('1010017000000257', '1010017000000167', '1010017000000002');
-- INSERT INTO `permission_role_permission` VALUES ('1010017000000258', '1010017000000168', '1010017000000103');
-- INSERT INTO `permission_role_permission` VALUES ('1010017000000259', '1010017000000169', '1010017000000002');


INSERT INTO `permission_menu` VALUES ('1010017000000181', '订单管理', '', '1810', 'admin/merchantOrderForm/list', '1010017000000105');
INSERT INTO `permission_menu` VALUES ('1010017000000182', '总单管理', '', '1820', 'admin/collectOrder/list', '1010017000000105');
INSERT INTO `permission_menu` VALUES ('1010017000000183', '退货管理', '', '1830', 'admin/returnOrder/list', '1010017000000105');
INSERT INTO `permission_menu` VALUES ('1010017000000184', '换货管理', '', '1840', 'admin/exchangeOrder/list', '1010017000000105');
INSERT INTO `permission_menu` VALUES ('1010017000000185', '订单管理', '', '1815', 'admin/merchantOrderForm/listStore', '1010017000000105');

INSERT INTO `permission_permission` VALUES ('1010017000000181', '订单管理', '1', '1010017000000181');
INSERT INTO `permission_permission` VALUES ('1010017000000182', '总单管理', '1', '1010017000000182');
INSERT INTO `permission_permission` VALUES ('1010017000000183', '退货管理', '1', '1010017000000183');
INSERT INTO `permission_permission` VALUES ('1010017000000184', '换货管理', '1', '1010017000000184');
INSERT INTO `permission_permission` VALUES ('1010017000000185', '换货列表', '1', '1010017000000185');

INSERT INTO `permission_role_permission` VALUES ('1010017000000301', '1010017000000181', '1010017000000101');
INSERT INTO `permission_role_permission` VALUES ('1010017000000302', '1010017000000182', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000303', '1010017000000183', '1010017000000102');
INSERT INTO `permission_role_permission` VALUES ('1010017000000304', '1010017000000184', '1010017000000102');
INSERT INTO `permission_role_permission` VALUES ('1010017000000305', '1010017000000185', '1010017000000102');

INSERT INTO `permission_menu` VALUES ('1010017000000201', '评价管理', '', '2010', 'admin/merchantEvaluation/list', '1010017000000106');

INSERT INTO `permission_permission` VALUES ('1010017000000201', '评价管理', '1', '1010017000000201');

INSERT INTO `permission_role_permission` VALUES ('1010017000000351', '1010017000000201', '1010017000000101');

INSERT INTO `permission_menu` VALUES ('1010017000000221', '分销等级', '', '2210', 'admin/commissionGrade/list', '1010017000000107');
INSERT INTO `permission_menu` VALUES ('1010017000000222', '分销方案', '', '2220', 'admin/commissionScheme/list', '1010017000000107');
INSERT INTO `permission_menu` VALUES ('1010017000000223', '用户分销等级', '', '2230', 'admin/userModeGrade/gradeModeTypeList', '1010017000000107');
-- INSERT INTO `permission_menu` VALUES ('1010017000000224', '商品分销模式', '', '2240', 'admin/modeMerchandise/list', '1010017000000107');
INSERT INTO `permission_menu` VALUES ('1010017000000225', '推荐关系', '', '2250', 'admin/userRelationship/list', '1010017000000107');
INSERT INTO `permission_menu` VALUES ('1010017000000226', '团队组织关系', '', '2260', 'admin/userTeam/list', '1010017000000107');
INSERT INTO `permission_menu` VALUES ('1010017000000227', '佣金审核', '', '2212', 'admin/commissionAccountRecords/list', '1010017000000107');
INSERT INTO `permission_menu` VALUES ('1010017000000228', '模式等级折扣', '', '2215', 'admin/modeGradeDiscount/list', '1010017000000107');

INSERT INTO `permission_permission` VALUES ('1010017000000221', '分销等级', '1', '1010017000000221');
INSERT INTO `permission_permission` VALUES ('1010017000000222', '分销方案', '1', '1010017000000222');
INSERT INTO `permission_permission` VALUES ('1010017000000223', '用户分销等级', '1', '1010017000000223');
-- INSERT INTO `permission_permission` VALUES ('1010017000000224', '商品分销模式', '1', '1010017000000224');
INSERT INTO `permission_permission` VALUES ('1010017000000225', '推荐关系', '1', '1010017000000225');
INSERT INTO `permission_permission` VALUES ('1010017000000226', '团队组织关系', '1', '1010017000000226');
INSERT INTO `permission_permission` VALUES ('1010017000000227', '推荐关系', '1', '1010017000000227');
INSERT INTO `permission_permission` VALUES ('1010017000000228', '团队组织关系', '1', '1010017000000228');

INSERT INTO `permission_role_permission` VALUES ('1010017000000401', '1010017000000221', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000402', '1010017000000222', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000403', '1010017000000223', '1010017000000002');
-- INSERT INTO `permission_role_permission` VALUES ('1010017000000404', '1010017000000224', '1010017000000101');
INSERT INTO `permission_role_permission` VALUES ('1010017000000405', '1010017000000225', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000406', '1010017000000226', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000407', '1010017000000227', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000408', '1010017000000228', '1010017000000002');

INSERT INTO `permission_menu` VALUES ('1010017000000241', '首页轮播图管理', '', '2410', 'admin/slide/list', '1010017000000108');
INSERT INTO `permission_menu` VALUES ('1010017000000242', '优惠券活动', '', '2420', 'admin/coupon/list', '1010017000000108');
INSERT INTO `permission_menu` VALUES ('1010017000000243', '套餐管理', '', '2430', 'admin/combinationMerchandise/list', '1010017000000104');
-- INSERT INTO `permission_menu` VALUES ('1010017000000244', '商城商品归类', '', '2440', 'admin/merchandiseCustomClassification/mallList', '1010017000000104');
-- INSERT INTO `permission_menu` VALUES ('1010017000000245', '商家商品归类', '', '2450', 'admin/merchandiseCustomClassification/list', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000246', '积分兑换商品', '', '2460', 'admin/pointExchange/exchangeList', '1010017000000104');
INSERT INTO `permission_menu` VALUES ('1010017000000247', '消费币兑换商品', '', '2470', 'admin/currencyExchange/exchangeList', '1010017000000104');

INSERT INTO `permission_permission` VALUES ('1010017000000241', '首页轮播图管理', '1', '1010017000000241');
INSERT INTO `permission_permission` VALUES ('1010017000000242', '优惠券活动', '1', '1010017000000242');
INSERT INTO `permission_permission` VALUES ('1010017000000243', '套餐管理', '1', '1010017000000243');
-- INSERT INTO `permission_permission` VALUES ('1010017000000244', '商城商品归类', '1', '1010017000000244');
-- INSERT INTO `permission_permission` VALUES ('1010017000000245', '商家商品归类', '1', '1010017000000245');
INSERT INTO `permission_permission` VALUES ('1010017000000246', '积分兑换商品', '1', '1010017000000246');
INSERT INTO `permission_permission` VALUES ('1010017000000247', '消费币兑换商品', '1', '1010017000000247');

INSERT INTO `permission_role_permission` VALUES ('1010017000000451', '1010017000000241', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000452', '1010017000000242', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000453', '1010017000000243', '1010017000000101');
-- INSERT INTO `permission_role_permission` VALUES ('1010017000000454', '1010017000000244', '1010017000000002');
-- INSERT INTO `permission_role_permission` VALUES ('1010017000000455', '1010017000000245', '1010017000000101');

INSERT INTO `permission_role_permission` VALUES ('1010017000000456', '1010017000000246', '1010017000000002');
INSERT INTO `permission_role_permission` VALUES ('1010017000000457', '1010017000000247', '1010017000000002');

INSERT INTO `publicservice_message_type` VALUES ('1010017000000001', 'user', '用户消息', '100', '12','1:站内信');
INSERT INTO `publicservice_message_type` VALUES ('1010017000000002', 'merchant', '商家消息', '10', '12','1:站内信');