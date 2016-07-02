package com.qcloud.component.pay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.qcloud.component.pay.PayObject;
import com.qcloud.component.pay.PaymentOperate;
import com.qcloud.component.pay.service.OperatorService;
import com.qcloud.pirates.core.context.PiratesBeanFactoryAware;
import com.qcloud.pirates.core.env.ProjectInfo;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class OperatorServiceImpl implements OperatorService {

    private Map<String, PaymentOperate> cache = new HashMap<String, PaymentOperate>();

    @Override
    public PayObject op(String module, Long objectId, Date occurTime) {

        PaymentOperate paymentOperate = get(module);
        AssertUtil.assertNotNull(paymentOperate, "支付数据模块尚未配置." + module);
        return paymentOperate.getPayObject(objectId, occurTime);
    }

    @Override
    public boolean notify(String module, Long objectId, Date occurTime) {

        PaymentOperate paymentOperate = get(module);
        AssertUtil.assertNotNull(paymentOperate, "支付数据模块尚未配置." + module);
        return paymentOperate.paid(objectId, occurTime);
    }

    private PaymentOperate get(String module) {

        AssertUtil.assertNotEmpty(module, "支付数据模块不能为空.");
        if (ProjectInfo.isDev()) {
            return parse(module);
        } else {
            PaymentOperate paymentOperate = cache.get(module);
            if (paymentOperate == null) {
                paymentOperate = parse(module);
                cache.put(module, paymentOperate);
            }
            return paymentOperate;
        }
    }

    private PaymentOperate parse(String module) {

        Xml xml = XmlFactory.get("publicservice-pay-operations");
        if (xml != null) {
            List<XmlItem> itemList = xml.getItemList();
            for (XmlItem xmlItem : itemList) {
                String moduleDef = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("module")).trim();
                if (module.equals(moduleDef)) {
                    String operate = StringUtil.nullToEmpty(xmlItem.getAttrMap().get("operate")).trim();
                    Object object = PiratesBeanFactoryAware.getBeanFactory().getBean(operate);
                    if (object instanceof PaymentOperate) {
                        return (PaymentOperate) object;
                    }
                }
            }
        }
        return null;
    }
}
