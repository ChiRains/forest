package com.qcloud.project.forest.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.goods.model.key.TypeEnum.BrandType;
import com.qcloud.component.marketing.model.Slide;
import com.qcloud.component.marketing.service.SlideService;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.pirates.core.xml.Xml;
import com.qcloud.pirates.core.xml.XmlFactory;
import com.qcloud.pirates.core.xml.XmlItem;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.project.forest.exception.ForestException;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.model.query.ArticleQuery;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleHandler;
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

    @Autowired
    private SlideService       slideService;

    @Autowired
    private ArticleService     articleService;

    @Autowired
    private ArticleHandler     articleHandler;

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

    /**
     * 首页图片+文字描述
     * @return
     */
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

    /**
     * 首页模块入口
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView menuAccess() {

        List<String> list = new ArrayList<String>();
        list.add("relax_find");// 轻松找药
        list.add("medication_remind");// 用药提醒
        list.add("coupon_offers");// 促销优惠
        list.add("delivery_search");// 物流查询
        list.add("custom_made");// 私人定制
        list.add("intergral_send");// 积分多多
        list.add("joint_merchant");// 合作商家
        list.add("play_game");// 互动游戏
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", list);
        return view;
    }

    /**
     * 健康精选
     * 
     * @return
     */
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

    /**
     * 首页资讯轮播
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView latestArticle() {

        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setEnable(1);
        Page<Article> page = articleService.page(articleQuery, 0, 5);
        List<ArticleVO> voList = articleHandler.toVOList(page.getData());
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", voList);
        return view;
    }

    /**
     * 签到分享
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView shareSignIn() {

        FrontAjaxView view = new FrontAjaxView();
        view.addObject("title", "标题");
        view.addObject("image", "");
        view.addObject("desc", "分享分享");
        return view;
    }

    /**
     * 启动页
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView startPage() {

        List<Slide> list = slideService.listBySence(2);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("exist", list.size() > 0);
        if (CollectionUtils.isNotEmpty(list)) {
            view.addObject("slide", list.get(0));
        }
        if (CollectionUtils.isNotEmpty(list)) {
            view.addObject("slide", "");
        }
        return view;
    }

    private Long getMerchantClassify() {

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
