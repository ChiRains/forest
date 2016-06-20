package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.model.key.TypeEnum.BrandType;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.exception.ForestException;
import com.qcloud.project.forest.web.vo.ArticleIndexVO;
import com.qcloud.project.forest.web.vo.ArticleVO;

@Controller
@RequestMapping(value = ForestController.DIR)
public class ForestController {

    public static final String DIR = "/forest";

    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private ParameterClient    parameterClient;

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

    @PiratesApp
    @RequestMapping
    public FrontAjaxView indexsModular() {

        String seckillImage = String.valueOf(parameterClient.get("forest-seckill-ind-image"));
        String couponImage = String.valueOf(parameterClient.get("forest-coupon-ind-image"));
        String summerText = String.valueOf(parameterClient.get("forest-summer-ind-text"));
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("seckillImage", seckillImage);
        view.addObject("couponImage", couponImage);
        view.addObject("summerText", summerText);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView menuAccess() {

        List<String> list = new ArrayList<String>();
        list.add("relax_find");
        list.add("medication_remind");
        list.add("coupon_offers");
        list.add("delivery_search");
        list.add("custom_made");
        list.add("intergral_send");
        list.add("joint_merchant");
        list.add("play_game");
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", list);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView goodHealthy() {

        List<QClassify> classifyList = publicdataClient.listClassifyForTree((long) BrandType.Brand.getKey());
        if (classifyList.size() > 9) {
            classifyList.subList(0, 8);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", classifyList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView latestArticle() {

        List<ArticleIndexVO> voList = new ArrayList<ArticleIndexVO>();
        ArticleIndexVO vo1 = new ArticleIndexVO();
        vo1.setId(00001);
        vo1.setName("最新的一条");
        voList.add(vo1);
        ArticleIndexVO vo2 = new ArticleIndexVO();
        vo2.setId(00002);
        vo2.setName("最新的一条的下一条");
        voList.add(vo2);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView shareSignIn() {

        FrontAjaxView view = new FrontAjaxView();
        view.addObject("title", "标题");
        view.addObject("image", "");
        view.addObject("desc", "分享分享");
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
