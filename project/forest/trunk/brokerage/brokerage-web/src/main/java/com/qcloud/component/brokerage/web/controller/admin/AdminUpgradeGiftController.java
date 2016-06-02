package com.qcloud.component.brokerage.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.brokerage.model.UpgradeGift;
import com.qcloud.component.brokerage.model.query.UpgradeGiftQuery;
import com.qcloud.component.brokerage.service.UpgradeGiftService;
import com.qcloud.component.brokerage.web.handler.UpgradeGiftHandler;
import com.qcloud.component.brokerage.web.vo.admin.AdminUpgradeGiftVO;
import com.qcloud.component.marketing.MarketingClient;
import com.qcloud.component.marketing.QCoupon;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminUpgradeGiftController.DIR)
public class AdminUpgradeGiftController {

    public static final String DIR = "admin/upgradeGift";

    @Autowired
    private UpgradeGiftService upgradeGiftService;

    @Autowired
    private UpgradeGiftHandler upgradeGiftHandler;

    @Autowired
    private MarketingClient    marketingClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, UpgradeGiftQuery query) {

        Page<UpgradeGift> page = upgradeGiftService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminUpgradeGiftVO> list = upgradeGiftHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-UpgradeGift-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(Long gradeId) {

        ModelAndView model = new ModelAndView("/admin/brokerage-UpgradeGift-add");
        List<QCoupon> list = marketingClient.listByPlatform();
        model.addObject("list", list);
        model.addObject("gradeId", gradeId);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(UpgradeGift upgradeGift) {

        upgradeGiftService.add(upgradeGift);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?gradeId=" + upgradeGift.getGradeId());
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        UpgradeGift upgradeGift = upgradeGiftService.get(id);
        AdminUpgradeGiftVO adminUpgradeGiftVO = upgradeGiftHandler.toVO4Admin(upgradeGift);
        ModelAndView model = new ModelAndView("/admin/brokerage-UpgradeGift-edit");
        model.addObject("upgradeGift", adminUpgradeGiftVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(UpgradeGift upgradeGift, String queryStr) {

        upgradeGiftService.update(upgradeGift);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        upgradeGiftService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
