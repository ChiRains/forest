package com.qcloud.component.commoditycenter.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.commoditycenter.model.MonthHotSale;
import com.qcloud.component.commoditycenter.service.MonthHotSaleService;
import com.qcloud.component.commoditycenter.web.handler.MonthHotSaleHandler;
import com.qcloud.component.commoditycenter.web.vo.MonthHotSaleVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;

@Controller
@RequestMapping(value = MonthHotSaleController.DIR)
public class MonthHotSaleController {

    public static final String  DIR = "/monthHotSale";

    @Autowired
    private PublicdataClient    publicdataClient;

    @Autowired
    private MonthHotSaleService monthHotSaleService;

    @Autowired
    private MonthHotSaleHandler monthHotSaleHandler;

    @RequestMapping
    public FrontAjaxView list4Mall(HttpServletRequest request, Long classifyId, Integer number) {

        number = number == null || number <= 0 || number > 100 ? 15 : number;
        AssertUtil.assertNotNull(classifyId, "商城商品分类不能为空.");
        Classify classify = publicdataClient.getTopClassify(classifyId);
        AssertUtil.assertNotNull(classify, "找不到顶级分类." + classifyId);
        List<MonthHotSale> list = monthHotSaleService.listByMallBsid(classify.getBsid(), number);
        List<MonthHotSaleVO> voList = monthHotSaleHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取月销排行榜成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    public FrontAjaxView list4Merchant(HttpServletRequest request, Long classifyId, Integer number) {

        number = number == null || number <= 0 || number > 100 ? 15 : number;
        AssertUtil.assertNotNull(classifyId, "商城商品分类不能为空.");
        Classify classify = publicdataClient.getTopClassify(classifyId);
        AssertUtil.assertNotNull(classify, "找不到顶级分类." + classifyId);
        List<MonthHotSale> list = monthHotSaleService.listByMerchantBsid(classify.getBsid(), number);
        List<MonthHotSaleVO> voList = monthHotSaleHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取月销排行榜成功.");
        view.addObject("list", voList);
        return view;
    }
}
