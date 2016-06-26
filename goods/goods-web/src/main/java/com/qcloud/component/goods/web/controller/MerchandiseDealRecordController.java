package com.qcloud.component.goods.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.goods.model.Merchandise;
import com.qcloud.component.goods.model.MerchandiseDealRecord;
import com.qcloud.component.goods.service.MerchandiseDealRecordService;
import com.qcloud.component.goods.service.MerchandiseService;
import com.qcloud.component.goods.web.handler.MerchandiseDealRecordHandler;
import com.qcloud.component.goods.web.vo.MerchandiseDealRecordVO;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;

@Controller
@RequestMapping(value = MerchandiseDealRecordController.DIR)
public class MerchandiseDealRecordController {

    public static final String           DIR = "/merchandiseDealRecord";

    @Autowired
    private MerchandiseDealRecordService merchandiseDealRecordService;

    @Autowired
    private MerchandiseDealRecordHandler merchandiseDealRecordHandler;

    @Autowired
    private MerchandiseService           merchandiseService;

    @RequestMapping
    public FrontAjaxView list(Long merchandiseId, Integer pageNum, Integer pageSize) {

        AssertUtil.assertNotNull(merchandiseId, "商品档案id不能为空.");
        Merchandise merchandise = merchandiseService.get(merchandiseId);
        AssertUtil.assertNotNull(merchandise, "商品档案不存在." + merchandiseId);
        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        List<MerchandiseDealRecord> list = merchandiseDealRecordService.listByMerchandise(merchandiseId, start, PAGE_SIZE);
        List<MerchandiseDealRecordVO> voList = merchandiseDealRecordHandler.toVOList(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询商品成交记录成功.");
        view.addObject("list", voList);
        return view;
    }
}
