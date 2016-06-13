package com.qcloud.project.forest.web.controller;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.exception.ForestException;

@Controller
@RequestMapping(value = ForestController.DIR)
public class ForestController {

    public static final String DIR = "/forest";

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private FileSDKClient      fileSDKClient;

    /**
     * 商家id
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getMerchant() {

        Long merchantId = getMerchantClassify();
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("merchant", merchantId);
        return view;
    }

    /**
     * 商品分类
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView classifyList() {

        List<QClassify> classifyList = publicdataClient.listClassifyForTree(getMerchantClassify());
        for (QClassify qClassify : classifyList) {
            fillFileServerUrlBeforeImage(qClassify);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("list", classifyList);
        return view;
    }

    long getMerchantClassify() {

        Xml xml = XmlFactory.get("forest-merchant");
        AssertUtil.assertNotNull(xml, "大参林商家未配置.forest-merchant");
        List<XmlItem> itemList = xml.getItemList();
        for (XmlItem xmlItem : itemList) {
            if (xmlItem.getAttrMap().get("merchant") != null) {
                return Long.valueOf(String.valueOf(xmlItem.getAttrMap().get("merchant")));
            }
        }
        throw new ForestException("大参林商家未配置.forest-merchant");
    }

    private void fillFileServerUrlBeforeImage(QClassify qClassify) {

        if (StringUtils.isNotEmpty(qClassify.getImage())) {
            qClassify.setImage(fileSDKClient.getFileServerUrl() + qClassify.getImage());
        } else {
            qClassify.setImage("");
        }
        List<QClassify> children = qClassify.getChildrenList();
        for (QClassify c : children) {
            fillFileServerUrlBeforeImage(c);
        }
    }
}
