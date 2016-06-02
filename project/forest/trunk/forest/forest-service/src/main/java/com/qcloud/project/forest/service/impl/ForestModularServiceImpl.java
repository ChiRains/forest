package com.qcloud.project.forest.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.project.forest.model.ForestModular;
import com.qcloud.project.forest.service.ForestModularService;

@Service
public class ForestModularServiceImpl implements ForestModularService {

    @Autowired
    private ParameterClient parameterClient;

    @Override
    public List<ForestModular> listAppIndexModular() {

        List<ForestModular> modular = getModular("forest-app-index-module");
        return modular;
    }

    private List<ForestModular> getModular(String modularCode) {

        Xml xml = XmlFactory.get(modularCode);
        if (xml == null) {
            return new ArrayList<ForestModular>();
        } else {
            List<ForestModular> forestModulars = new ArrayList<ForestModular>();
            List<XmlItem> itemList = xml.getItemList();
            //
            for (XmlItem xmlItem : itemList) {
                ForestModular modular = new ForestModular();
                String code = xmlItem.getAttrMap().get("code");
                String name = xmlItem.getText();
                modular.setCode(code);
                modular.setName(name);
                modular.setValue(String.valueOf(parameterClient.get(code)));
                modular.setType("1");
                modular.setTips(xmlItem.getAttrMap().get("tips"));
                forestModulars.add(modular);
            }
            return forestModulars;
        }
    }
}
