package com.qcloud.component.marketing.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.qcloud.component.marketing.service.CouponMultipleConfigService;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;

@Service
public class CouponMultipleConfigServiceImpl implements CouponMultipleConfigService {

    private static final String MERCHANT_CODE = "marketing-merchant-coupon-multiple-config";

    private static final String MALL_CODE     = "marketing-mall-coupon-multiple-config";

    @Override
    public boolean isMerchantMultiple() {

        boolean isMultiple = false;
        Xml xml = XmlFactory.get(MERCHANT_CODE);
        List<XmlItem> xmlItemList = xml.getItemList();
        for (XmlItem xmlItem : xmlItemList) {
            isMultiple = Boolean.valueOf(xmlItem.getAttrMap().get("config"));
            return isMultiple;
        }
        return isMultiple;
    }

    @Override
    public boolean isMallMultiple() {

        boolean isMultiple = false;
        Xml xml = XmlFactory.get(MALL_CODE);
        List<XmlItem> xmlItemList = xml.getItemList();
        for (XmlItem xmlItem : xmlItemList) {
            isMultiple = Boolean.valueOf(xmlItem.getAttrMap().get("config"));
            return isMultiple;
        }
        return isMultiple;
    }
}
