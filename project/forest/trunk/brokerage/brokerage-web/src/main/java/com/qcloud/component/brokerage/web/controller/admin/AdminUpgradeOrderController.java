package com.qcloud.component.brokerage.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.brokerage.model.UpgradeOrder;
import com.qcloud.component.brokerage.service.UpgradeOrderService;
import com.qcloud.component.brokerage.web.handler.UpgradeOrderHandler;
import com.qcloud.component.brokerage.model.query.UpgradeOrderQuery;
import com.qcloud.component.brokerage.web.vo.admin.AdminUpgradeOrderVO;

@Controller
@RequestMapping(value = "/" + AdminUpgradeOrderController.DIR)
public class AdminUpgradeOrderController {

    public static final String  DIR = "admin/upgradeOrder";

    @Autowired
    private UpgradeOrderService upgradeOrderService;

    @Autowired
    private UpgradeOrderHandler upgradeOrderHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, UpgradeOrderQuery query) {

        Page<UpgradeOrder> page = upgradeOrderService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminUpgradeOrderVO> list = upgradeOrderHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-UpgradeOrder-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }
}
