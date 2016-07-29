package com.qcloud.component.marketing.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.marketing.model.FullReduces;
import com.qcloud.component.marketing.model.query.FullReducesQuery;
import com.qcloud.component.marketing.service.FullReducesService;
import com.qcloud.component.marketing.web.handler.FullReducesHandler;
import com.qcloud.component.marketing.web.vo.admin.AdminFullReducesVO;
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

@Controller
@RequestMapping(value = "/" + AdminFullReducesController.DIR)
public class AdminFullReducesController {

    public static final String DIR = "admin/fullReduces";

    @Autowired
    private FullReducesService fullReducesService;

    @Autowired
    private FullReducesHandler fullReducesHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, FullReducesQuery query) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        query.setMerchantId(merchant.getId());
        Page<FullReduces> page = fullReducesService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminFullReducesVO> list = fullReducesHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/marketing-FullReduces-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/marketing-FullReduces-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(HttpServletRequest request, FullReduces fullReduces) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        fullReduces.setMerchantId(merchant.getId());
        fullReduces.setState(1);
        fullReducesService.add(fullReduces);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        FullReduces fullReduces = fullReducesService.get(id);
        AdminFullReducesVO adminFullReducesVO = fullReducesHandler.toVO4Admin(fullReduces);
        ModelAndView model = new ModelAndView("/admin/marketing-FullReduces-edit");
        model.addObject("fullReduces", adminFullReducesVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(HttpServletRequest request, FullReduces fullReduces, String queryStr) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertTrue(fullReduces.getMerchantId() == merchant.getId(), "您没权限进行当前操作");
        fullReducesService.update(fullReduces);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(HttpServletRequest request, Long id) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(id, "ID不能为空");
        FullReduces fullReduces = fullReducesService.get(id);
        AssertUtil.assertTrue(fullReduces.getMerchantId() == merchant.getId(), "您没权限进行当前操作");
        fullReduces.setState(3);
        fullReducesService.update(fullReduces);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView enable(HttpServletRequest request, Long id, int enable) {

        QMerchant merchant = PageParameterUtil.getParameterValues(request, SellercenterClient.MERCHANT_LOGIN_PARAMETER_KEY);
        AssertUtil.assertNotNull(id, "ID不能为空");
        FullReduces fullReduces = fullReducesService.get(id);
        AssertUtil.assertTrue(fullReduces.getMerchantId() == merchant.getId(), "您没权限进行当前操作");
        fullReduces.setState(enable);
        fullReducesService.update(fullReduces);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("操作成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
