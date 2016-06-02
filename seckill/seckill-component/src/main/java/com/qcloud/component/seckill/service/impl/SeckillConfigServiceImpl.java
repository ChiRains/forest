package com.qcloud.component.seckill.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.qcloud.component.seckill.model.SeckillConfig;
import com.qcloud.component.seckill.service.SeckillConfigService;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class SeckillConfigServiceImpl implements SeckillConfigService {

    @Override
    public SeckillConfig get() {

        SeckillConfig seckillConfig = new SeckillConfig();
        seckillConfig.setPayMinutes(20);
        seckillConfig.setScreeningsMinSize(4);
        seckillConfig.setToKillString("即将开抢");
        seckillConfig.setKillingString("抢购进行中");
        seckillConfig.setKilledString("已结束");
        seckillConfig.setKillName("超级秒杀");
        Xml xml = XmlFactory.get("seckill-config-setting");
        AssertUtil.assertNotNull(xml, "请配置app.xml seckill-config-setting");
        for (XmlItem xmlItem : xml.getItemList()) {
            if ("screeningsMinSize".equals(xmlItem.getAttrMap().get("name"))) {
                String screeningsMinSizeStr = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                if (StringUtils.isNotEmpty(screeningsMinSizeStr)) {
                    seckillConfig.setScreeningsMinSize(Integer.parseInt(screeningsMinSizeStr));
                }
            }
            if ("payMinutes".equals(xmlItem.getAttrMap().get("name"))) {
                String payMinutesStr = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                if (StringUtils.isNotEmpty(payMinutesStr)) {
                    seckillConfig.setPayMinutes(Integer.parseInt(payMinutesStr));
                }
            }
            if ("toKillString".equals(xmlItem.getAttrMap().get("name"))) {
                String toKillString = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                seckillConfig.setToKillString(toKillString);
            }
            if ("killingString".equals(xmlItem.getAttrMap().get("name"))) {
                String killingString = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                seckillConfig.setKillingString(killingString);
            }
            if ("killedString".equals(xmlItem.getAttrMap().get("name"))) {
                String killedString = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                seckillConfig.setKilledString(killedString);
            }
            if ("killName".equals(xmlItem.getAttrMap().get("name"))) {
                String killName = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                seckillConfig.setKillName(killName);
            }
        }
        return seckillConfig;
    }
}
