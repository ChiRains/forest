package com.qcloud.component.marketing.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.CommoditycenterClient;
import com.qcloud.component.commoditycenter.QMerchandiseItem;
import com.qcloud.component.commoditycenter.QUnifiedMerchandise;
import com.qcloud.component.commoditycenter.model.Merchandise;
import com.qcloud.component.commoditycenter.model.MerchandiseMarketing;
import com.qcloud.component.commoditycenter.model.query.MerchandiseMarketingQuery;
import com.qcloud.component.commoditycenter.service.MerchandiseMarketingService;
import com.qcloud.component.commoditycenter.service.MerchandiseService;
import com.qcloud.component.commoditycenter.web.handler.MerchandiseHandler;
import com.qcloud.component.commoditycenter.web.vo.MerchandiseVO;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.marketing.model.RecentDiscount;
import com.qcloud.component.marketing.model.key.TypeEnum.SenceType;
import com.qcloud.component.marketing.model.query.RecentDiscountQuery;
import com.qcloud.component.marketing.service.RecentDiscountService;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = RecentDiscountController.DIR)
public class RecentDiscountController {

    public static final String               DIR = "/recentDiscount";

    @Autowired
    private RecentDiscountService            recentDiscountService;

    @Autowired
    private MerchandiseMarketingService      merchandiseMarketingService;

    @Autowired
    private CommoditycenterClient            commoditycenterClient;

    @Autowired
    private FileSDKClient                    fileSDKClient;

    @Autowired
    private MerchandiseService               merchandiseService;

    @Autowired
    private MerchandiseHandler               merchandiseHandler;

    @RequestMapping
    @NoReferer
    public FrontAjaxView list(HttpServletRequest request, PPage pPage, RecentDiscountQuery recentQuery) {

        recentQuery.setDate(DateUtil.date2String(new Date()));
        List<RecentDiscount> recentDiscounts = recentDiscountService.list(recentQuery, 0, Integer.MAX_VALUE);
        // 写了一半，先放着
        List<MerchandiseVO> voList = new ArrayList<MerchandiseVO>();
        if (recentDiscounts.size() > 0) {
            RecentDiscount nowDiscount = recentDiscounts.get(0);
            //
            MerchandiseMarketingQuery query = new MerchandiseMarketingQuery();
            query.setActivityId(nowDiscount.getId());
            query.setSence(SenceType.LIMIT.getKey());
            Page<MerchandiseMarketing> merchandiseList = merchandiseMarketingService.page(query, pPage.getPageStart(), pPage.getPageSize());
            for (MerchandiseMarketing temp : merchandiseList.getData()) {
                //
                QUnifiedMerchandise unifiedMerchandise = commoditycenterClient.getUnifiedMerchandise(temp.getUnifiedMerchandiseId());
                QMerchandiseItem qMerchandiseItem = unifiedMerchandise.getList().get(0);
                Merchandise merchandise = merchandiseService.get(qMerchandiseItem.getMerchandiseId());
                MerchandiseVO merchandiseVO = merchandiseHandler.toVO(merchandise);
                merchandiseVO.setPrice(unifiedMerchandise.getPrice());
                merchandiseVO.setDiscount(unifiedMerchandise.getDiscount());
                merchandiseVO.setSalesVolume(qMerchandiseItem.getSalesVolume());
                merchandiseVO.setSalesVolume(merchandiseService.getSalesVolume(merchandise.getId()));
                if (StringUtils.isNotEmpty(merchandise.getImage())) {
                    merchandiseVO.setImage(fileSDKClient.getFileServerUrl() + merchandise.getImage());
                }
                merchandiseVO.setUnifiedMerchandiseId(unifiedMerchandise.getId());
                merchandiseVO.setSpecifications("");
                // 规格
                long sum = qMerchandiseItem.getGoodEvaluation() + qMerchandiseItem.getMiddleEvaluation() + qMerchandiseItem.getLowEvaluation();
                int goodEvaluationRate = 0;
                if (sum != 0) {
                    goodEvaluationRate = new Double(qMerchandiseItem.getGoodEvaluation() * 100 / sum).intValue();
                }
                merchandiseVO.setGoodEvaluationRate(goodEvaluationRate);
                voList.add(merchandiseVO);
            }
        }
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("result", voList);
        view.setMessage("获取近期优惠成功.");
        return view;
    }
}
