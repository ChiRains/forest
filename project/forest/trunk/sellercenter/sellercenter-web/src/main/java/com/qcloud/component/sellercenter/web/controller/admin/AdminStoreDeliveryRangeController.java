package com.qcloud.component.sellercenter.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.service.StoreDeliveryRangeService;
import com.qcloud.component.sellercenter.web.handler.StoreDeliveryRangeHandler;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;
import com.qcloud.component.sellercenter.web.vo.admin.AdminStoreDeliveryRangeVO;

@Controller
@RequestMapping(value = "/" + AdminStoreDeliveryRangeController.DIR)
public class AdminStoreDeliveryRangeController {

    public static final String        DIR = "admin/storeDeliveryRange";

    @Autowired
    private StoreDeliveryRangeService storeDeliveryRangeService;

    @Autowired
    private StoreDeliveryRangeHandler storeDeliveryRangeHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, StoreDeliveryRangeQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<StoreDeliveryRange> page = storeDeliveryRangeService.page(query, start, PAGE_SIZE);
        List<AdminStoreDeliveryRangeVO> list = storeDeliveryRangeHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/sellercenter-StoreDeliveryRange-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreDeliveryRange-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(StoreDeliveryRange storeDeliveryRange) {

        storeDeliveryRangeService.add(storeDeliveryRange);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        StoreDeliveryRange storeDeliveryRange = storeDeliveryRangeService.get(id);
        AdminStoreDeliveryRangeVO adminStoreDeliveryRangeVO = storeDeliveryRangeHandler.toVO4Admin(storeDeliveryRange);
        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreDeliveryRange-edit");
        model.addObject("storeDeliveryRange", adminStoreDeliveryRangeVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(StoreDeliveryRange storeDeliveryRange) {

        StoreDeliveryRange s = storeDeliveryRangeService.getByStore(storeDeliveryRange.getStoreId());
        if (s == null) {
            storeDeliveryRangeService.add(storeDeliveryRange);
        } else {
            storeDeliveryRangeService.update(storeDeliveryRange);
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/toEditByStore?storeId=" + storeDeliveryRange.getStoreId());
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        storeDeliveryRangeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditByStore(Long storeId) {

        AssertUtil.assertNotNull(storeId, "storeId不能为空");
        StoreDeliveryRange storeDeliveryRange = storeDeliveryRangeService.getByStore(storeId);
        if (storeDeliveryRange == null) {
            storeDeliveryRange = new StoreDeliveryRange();
            storeDeliveryRange.setId(-1);
            storeDeliveryRange.setStoreId(storeId);
        }
        AdminStoreDeliveryRangeVO adminStoreDeliveryRangeVO = storeDeliveryRangeHandler.toVO4Admin(storeDeliveryRange);
        ModelAndView model = new ModelAndView("/admin/sellercenter-StoreDeliveryRange-edit");
        model.addObject("storeDeliveryRange", adminStoreDeliveryRangeVO);
        return model;
    }
}
