package com.qcloud.project.forest.web.controller;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.model.UnifiedMerchandise;
import com.qcloud.component.goods.model.key.TypeEnum.QueryItemType;
import com.qcloud.component.goods.model.query.UnifiedMerchandiseQuery;
import com.qcloud.component.goods.service.UnifiedMerchandiseService;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.project.forest.model.key.TypeEnum.ClassifyType;
import com.qcloud.project.forest.web.handler.PromotionalOffersHandler;
import com.qcloud.project.forest.web.vo.PromotionalOffersVO;

@Controller
@RequestMapping(value = PromotionalOffersController.DIR)
public class PromotionalOffersController {

    public static final String        DIR = "/promotionalOffers";

    @Autowired
    private PublicdataClient          publicdataClient;

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private PromotionalOffersHandler  promotionalOffersHandler;

    /**
     * 获取促销优惠类别
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getPromotionalOffersClassify() {

        List<Classify> classifies = publicdataClient.listClassify((long) ClassifyType.PROMOTIONALOFFERS.getKey());
        Iterator<Classify> iter = classifies.iterator();
        while (iter.hasNext()) {
            Classify s = iter.next();
            if (s.getEnable() != 1) {
                iter.remove();
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", classifies);
        return frontAjaxView;
    }

    /**
     * 获取促销优惠商品
     * @param pPage
     * @param classifyId
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontPagingView getPromotionalOfferslist(PPage pPage, Long classifyId) {

        Classify classify = publicdataClient.getClassify(classifyId);
        UnifiedMerchandiseQuery unifiedMerchandiseQuery = new UnifiedMerchandiseQuery();
        unifiedMerchandiseQuery.setActivityId(classifyId);
        unifiedMerchandiseQuery.setQueryItemType(QueryItemType.S);
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(unifiedMerchandiseQuery, pPage.getPageStart(), pPage.getPageSize());
        List<PromotionalOffersVO> list = promotionalOffersHandler.toVOList(page.getData());
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        frontPagingView.setList(list);
        frontPagingView.addObject("image", classify.getImage());
        return frontPagingView;
    }
}
