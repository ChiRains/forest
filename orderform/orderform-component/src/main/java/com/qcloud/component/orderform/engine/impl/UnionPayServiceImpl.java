package com.qcloud.component.orderform.engine.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.orderform.engine.OrderSelecterService;
import com.qcloud.component.orderform.engine.UnionPayService;
import com.qcloud.component.orderform.entity.OrderEntity;
import com.qcloud.component.orderform.exception.OrderformException;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicservice.PayClient;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.union.common.AcpsdkProperties;
import com.union.common.Upacp;
import com.unionpay.acp.sdk.SDKConfig;

@Service
public class UnionPayServiceImpl implements UnionPayService {

    @Autowired
    PayClient                    payClient;

    @Autowired
    private OrderSelecterService orderSelecterService;

    @Autowired
    private ParameterClient      parameterClient;

    private final String         DOMAIN_KEY       = "domain-key";

    private final String         MERID_KEY        = "merId-key";

    private final String         SIGNCERTPWD_KEY  = "signCertPwd-key";

    private final String         SIGNCERT_KEY     = "signcert-key";

    private final String         SIGNCERTPATH_KEY = "signcertPath-key";

    private String getAlipayNotifyUrl() {

        return "http://" + parameterClient.get(DOMAIN_KEY) + "/unionPay/unionPaied.do";
    }

    /**
     * 读取默认的配置,单商户
     */
    private AcpsdkProperties loadProperties() {

        String signCertPath = String.valueOf(parameterClient.get(SIGNCERTPATH_KEY)) + "/" + String.valueOf(parameterClient.get(SIGNCERT_KEY));
        String validateCertDir = String.valueOf(parameterClient.get(SIGNCERTPATH_KEY));
        String encryptCertPath = String.valueOf(parameterClient.get(SIGNCERTPATH_KEY)) + "/encryptpub.cer";
        String signCertPwd = String.valueOf(parameterClient.get(SIGNCERTPWD_KEY));
        AcpsdkProperties acpsdkProperties = new AcpsdkProperties();
        acpsdkProperties.setSignCertType("PKCS12");
        // 商户号
        acpsdkProperties.setMerId(String.valueOf(parameterClient.get(MERID_KEY)));
        // 证书路径
        acpsdkProperties.setSignCertPath(signCertPath);
        // 证书密码
        acpsdkProperties.setSignCertPwd(signCertPwd);
        acpsdkProperties.setValidateCertDir(validateCertDir);
        acpsdkProperties.setEncryptCertPath(encryptCertPath);
        return acpsdkProperties;
    }

    private String createHtml(long userId, String ip, Long orderId, Date orderDate, AcpsdkProperties acpsdkProperties) {

        // 初始化配置
        SDKConfig.getConfig().loadProperties(acpsdkProperties.getProperties());
        /**
         * 组装请求报文
         */
        Map<String, String> data = new HashMap<String, String>();
        OrderEntity orderEntity = orderSelecterService.getOrder(orderId, orderDate);
        AssertUtil.assertNotNull(orderEntity, "订单不存在." + orderId);
        if (orderEntity.getUserId() != userId) {
            throw new OrderformException("该订单不属于你!");
        }
        // -------------------------------------分割线-------------------------------------------
        final String merId = acpsdkProperties.getMerId();
        final String backUrl = getAlipayNotifyUrl();
        final String reqReserved = String.valueOf(orderId) + "_" + DateUtil.date2String(orderDate, "yyyyMMddHHmmss");
        final String frontUrl = "http://" + parameterClient.get(DOMAIN_KEY) + "/unionPay/frontUrl.do";
        // 商户号码，请改成自己的商户号
        data.put("merId", merId);
        // 商户订单号，8-40位数字字母
        data.put("orderId", orderEntity.getOrderNumber());
        // 后台通知地址
        // data.put("backUrl", getAlipayNotifyUrl());
        data.put("backUrl", backUrl);
        // 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
        data.put("reqReserved", reqReserved);
        // 前台通知地址 ，控件接入方式无作用
        data.put("frontUrl", frontUrl);
        // 交易金额，单位分
        data.put("txnAmt", String.valueOf(new Double(orderEntity.getCash() * 100).intValue()));
        // 持卡人发起交易的IP地址，用于防钓鱼
        data.put("customerIp", ip);
        // ---------------------------------------分割线-------------------------------------------
        // 版本号
        data.put("version", "5.0.0");
        // 字符集编码 默认"UTF-8"
        data.put("encoding", "UTF-8");
        // 签名方法 01 RSA
        data.put("signMethod", "01");
        // 交易类型 01-消费
        data.put("txnType", "01");
        // 交易子类型 01:自助消费 02:订购 03:分期付款
        data.put("txnSubType", "01");
        // 业务类型
        data.put("bizType", "000201");
        // 渠道类型，07-PC，08-手机
        data.put("channelType", "08");
        // 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
        data.put("accessType", "0");
        // 订单发送时间，取系统时间
        data.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        // 交易币种
        data.put("currencyCode", "156");
        // 订单描述，可不上送，上送时控件中会显示该信息
        // data.put("orderDesc", "订单描述");
        Map<String, String> submitFromData = Upacp.signData(data);
        // 交易请求url 从配置文件读取
        String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();
        /**
         * 创建表单
         */
        return Upacp.createHtml(requestFrontUrl, submitFromData);
    }

    @Override
    public String requestUnionPayHtml(long userId, String ip, Long orderId, Date orderDate) {

        return createHtml(userId, ip, orderId, orderDate, loadProperties());
    }

    @Override
    public String requestUnionPayHtml(long userId, String ip, Long orderId, Date orderDate, AcpsdkProperties acpsdkProperties) {

        return createHtml(userId, ip, orderId, orderDate, acpsdkProperties);
    }
}
