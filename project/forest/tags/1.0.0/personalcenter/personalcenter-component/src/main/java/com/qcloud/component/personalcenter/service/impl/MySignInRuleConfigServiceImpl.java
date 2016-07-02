package com.qcloud.component.personalcenter.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.qcloud.component.personalcenter.model.MySignInRuleConfig;
import com.qcloud.component.personalcenter.model.SignIntegral;
import com.qcloud.component.personalcenter.service.MySignInRuleConfigService;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;

@Service
public class MySignInRuleConfigServiceImpl implements MySignInRuleConfigService {

    @Override
    public MySignInRuleConfig get() {

        Xml xml = XmlFactory.get("personalcenter-sign-in-rule");
        AssertUtil.assertNotNull(xml, "请配置app.xml personalcenter-sign-in-rule");
        List<XmlItem> list = xml.getItemList();
        MySignInRuleConfig ruleConfigSetting = new MySignInRuleConfig();
        ruleConfigSetting.setFirstIntegral(5);
        ruleConfigSetting.setList(new ArrayList<SignIntegral>());
        for (XmlItem xmlItem : list) {
            if ("rule".equals(xmlItem.getAttrMap().get("name"))) {
                ruleConfigSetting.setRule(StringUtil.nullToEmpty(xmlItem.getText()).trim());
            } else if ("firstIntegral".equals(xmlItem.getAttrMap().get("name"))) {
                String firstIntegralStr = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                if (StringUtils.isNotEmpty(firstIntegralStr)) {
                    ruleConfigSetting.setFirstIntegral(Integer.parseInt(firstIntegralStr));
                } else {
                    ruleConfigSetting.setFirstIntegral(5);
                }
            } else if ("signIntegralRule".equals(xmlItem.getAttrMap().get("name"))) {
                String signIntegralRuleStr = StringUtil.nullToEmpty(xmlItem.getText()).trim();
                //
                Xml ruleXml = XmlFactory.get(signIntegralRuleStr);
                if (ruleXml != null) {
                    List<SignIntegral> siList = new ArrayList<SignIntegral>();
                    List<XmlItem> itemList = ruleXml.getItemList();
                    for (XmlItem xi : itemList) {
                        String signStr = StringUtil.nullToEmpty(xi.getAttrMap().get("sign"));
                        String integralStr = StringUtil.nullToEmpty(xi.getAttrMap().get("integral"));
                        SignIntegral signIntegral = new SignIntegral();
                        signIntegral.setSign(Integer.parseInt(signStr));
                        signIntegral.setIntegral(Integer.parseInt(integralStr));
                        siList.add(signIntegral);
                    }
                    Collections.sort(siList, new Comparator<SignIntegral>() {

                        @Override
                        public int compare(SignIntegral o1, SignIntegral o2) {

                            return o1.getSign() - o2.getSign();
                        }
                    });
                    ruleConfigSetting.setList(siList);
                }
            }
        }
        //
        return ruleConfigSetting;
    }

    @Override
    public boolean set(MySignInRuleConfig config) {

        return false;
    }
}
