package com.qcloud.project.forest.web.controller;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.filesdk.FileSDKClient;
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
@RequestMapping(value = BrandSalesController.DIR)
public class BrandSalesController {

    public static final String        DIR = "/brandSales";

    @Autowired
    private PublicdataClient          publicdataClient;

    @Autowired
    private UnifiedMerchandiseService unifiedMerchandiseService;

    @Autowired
    private PromotionalOffersHandler  promotionalOffersHandler;

    @Autowired
    private FileSDKClient             fileSDKClient;

    @PiratesApp
    @RequestMapping
    public FrontAjaxView brandOnSaleBrands() {

        List<Classify> classifies = publicdataClient.listClassify((long) ClassifyType.BRANDONSALEBRAND.getKey());
        Iterator<Classify> iter = classifies.iterator();
        while (iter.hasNext()) {
            Classify s = iter.next();
            s.setImage(fileSDKClient.getFileServerUrl() + s.getImage());
            if (s.getEnable() != 1) {
                iter.remove();
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("list", classifies);
        return view;
    }

    @PiratesApp
    @RequestMapping
    public FrontAjaxView brandOnSaleBrandsForBigPic() {

        List<Classify> classifies = publicdataClient.listClassify((long) ClassifyType.BRANDONSALEBRANDFORBIGPIC.getKey());
        if (classifies.size() >= 2) {
            classifies.subList(0, 2);
        }
        Iterator<Classify> iter = classifies.iterator();
        while (iter.hasNext()) {
            Classify s = iter.next();
            s.setImage(fileSDKClient.getFileServerUrl() + s.getImage());
            if (s.getEnable() != 1) {
                iter.remove();
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("list", classifies);
        return view;
    }

    /**
     * 展示品牌特卖下面的类别
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontAjaxView getBrandSalesClassify() {

        List<Classify> classifies = publicdataClient.listClassify((long) ClassifyType.BRANDONSALECLASSIFY.getKey());
        Iterator<Classify> iter = classifies.iterator();
        while (iter.hasNext()) {
            Classify s = iter.next();
            s.setImage(fileSDKClient.getFileServerUrl() + s.getImage());
            if (s.getEnable() != 1) {
                iter.remove();
            }
        }
        FrontAjaxView frontAjaxView = new FrontAjaxView();
        frontAjaxView.addObject("result", classifies);
        return frontAjaxView;
    }

    /**
     * 展示品牌特卖的商品
     * @param pPage
     * @param classifyId
     * @return
     */
    @PiratesApp
    @RequestMapping
    public FrontPagingView getBrandSaleslist(PPage pPage, Long classifyId) {

        UnifiedMerchandiseQuery unifiedMerchandiseQuery = new UnifiedMerchandiseQuery();
        unifiedMerchandiseQuery.setActivityId(classifyId);
        unifiedMerchandiseQuery.setQueryItemType(QueryItemType.S);
        Page<UnifiedMerchandise> page = unifiedMerchandiseService.page(unifiedMerchandiseQuery, pPage.getPageStart(), pPage.getPageSize());
        List<PromotionalOffersVO> list = promotionalOffersHandler.toVOList(page.getData());
        FrontPagingView frontPagingView = new FrontPagingView(pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        frontPagingView.setList(list);
        return frontPagingView;
    }
}
