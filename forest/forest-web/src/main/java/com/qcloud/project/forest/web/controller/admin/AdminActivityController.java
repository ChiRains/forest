package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.sellercenter.QMerchant;
import com.qcloud.component.sellercenter.SellercenterClient;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;
import com.qcloud.project.forest.service.ActivityService;
import com.qcloud.project.forest.web.handler.ActivityHandler;
import com.qcloud.project.forest.web.vo.admin.AdminActivityVO;

@Controller
@RequestMapping(value = "/" + AdminActivityController.DIR)
public class AdminActivityController {

    public static final String DIR = "admin/activity";

    @Autowired
    private ActivityService    activityService;

    @Autowired
    private ActivityHandler    activityHandler;

    @Autowired
    private SellercenterClient sellercenterClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, ActivityQuery query) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, sellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setDepartmentId(merchant.getId());
        Page<Activity> page = activityService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminActivityVO> list = activityHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-Activity-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/forest-Activity-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(Activity activity, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, sellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        activity.setDepartmentId(merchant.getId());
        activity.setTime(new Date());
        activityService.add(activity);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, sellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(id, "ID不能为空");
        Activity activity = activityService.get(id);
        AssertUtil.assertTrue(activity.getDepartmentId() == merchant.getId(), "您没有权限修改当前信息");
        AdminActivityVO adminActivityVO = activityHandler.toVO4Admin(activity);
        ModelAndView model = new ModelAndView("/admin/forest-Activity-edit");
        model.addObject("activity", adminActivityVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Activity activity, String queryStr, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, sellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Activity activity1 = activityService.get(activity.getId());
        AssertUtil.assertTrue(activity1.getDepartmentId() == merchant.getId(), "您没有权限修改当前信息");
        activityService.update(activity);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id, HttpServletRequest request) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, sellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        Activity activity1 = activityService.get(id);
        AssertUtil.assertTrue(activity1.getDepartmentId() == merchant.getId(), "您没有权限删除当前信息");
        AssertUtil.assertNotNull(id, "ID不能为空");
        activityService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
