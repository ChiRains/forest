package com.qcloud.component.marketing.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.marketing.service.RegistrationGiftService;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;

@Component
public class RegistrationGiftServiceImpl implements RegistrationGiftService {

    @Autowired(required=false)
    private MarketingClient marketingClient;

    private Long            couponId = 0L;

    private int             number   = 0;

    boolean                 send     = false;

    private Log             logger   = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {
        if(marketingClient == null){
            return ;
        }
        Xml xml = XmlFactory.get("personalcenter-registration-coupon");
        if (xml != null) {
            List<XmlItem> itemList = xml.getItemList();
            for (XmlItem xmlItem : itemList) {
                String idStr = xmlItem.getAttrMap().get("couponId");
                String numberStr = xmlItem.getAttrMap().get("number");
                if (StringUtils.isNotEmpty(idStr)) {
                    couponId = Long.valueOf(idStr);
                }
                if (StringUtils.isNotEmpty(numberStr)) {
                    number = Integer.valueOf(numberStr);
                }
            }
            if (couponId > 0 && number > 0) {
                send = marketingClient.existCoupon(couponId);
            }
        }
    }

    @Override
    public boolean sendGift(Long userId) {

        synchronized (userId) {
            logger.info("注册送优惠劵." + send);
            if (send) {
                for (int index = 0; index < number; index++) {
                    boolean sended = marketingClient.canExtract(userId, couponId);
                    if (sended) {
                        Long myCouponId = marketingClient.extractCoupon(userId, couponId);
                        if (myCouponId > 0) {
                            logger.info("注册送优惠劵成功." + index + " " + userId);
                        } else {
                            logger.info("注册送优惠劵失败." + index + " " + userId);
                            break;
                        }
                    } else {
                        logger.info("注册送优惠劵失败." + index + " " + userId);
                        break;
                    }
                }
            }
            logger.info("注册送优惠劵结束.");
            return true;
        }
    }

    public Long getCouponId() {

        return couponId;
    }
}
