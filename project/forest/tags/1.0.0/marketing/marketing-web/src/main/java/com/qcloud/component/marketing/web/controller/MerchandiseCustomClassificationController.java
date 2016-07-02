package com.qcloud.component.marketing.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.marketing.model.MerchandiseCustomClassification;
import com.qcloud.component.marketing.service.MerchandiseCustomClassificationService;
import com.qcloud.component.marketing.web.handler.MerchandiseCustomClassificationHandler;
import com.qcloud.component.marketing.web.vo.MerchandiseCustomClassificationVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;

@Controller
@RequestMapping(value = MerchandiseCustomClassificationController.DIR)
public class MerchandiseCustomClassificationController {

    public static final String                     DIR = "/merchandiseCustomClassification";

    @Autowired
    private MerchandiseCustomClassificationService merchandiseCustomClassificationService;

    @Autowired
    private MerchandiseCustomClassificationHandler merchandiseCustomClassificationHandler;

    @RequestMapping
    public ModelAndView list(Long merchantId, Long customClassifyId, Integer pageNum, Integer pageSize) {

        // todo
        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        List<MerchandiseCustomClassification> list = merchandiseCustomClassificationService.list(merchantId, customClassifyId, start, PAGE_SIZE);
        List<MerchandiseCustomClassificationVO> voList = merchandiseCustomClassificationHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.addObject("merchandiseList", voList);
        view.setMessage("获取商家推荐商品成功");
        return view;
    }
}
