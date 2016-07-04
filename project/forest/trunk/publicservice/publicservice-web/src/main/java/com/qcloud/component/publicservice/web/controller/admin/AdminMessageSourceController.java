package com.qcloud.component.publicservice.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.publicservice.model.MessageSource;
import com.qcloud.component.publicservice.model.query.MessageSourceQuery;
import com.qcloud.component.publicservice.service.MessageSourceService;
import com.qcloud.component.publicservice.web.handler.MessageSourceHandler;
import com.qcloud.component.publicservice.web.vo.admin.AdminMessageSourceVO;
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
@RequestMapping(value = "/" + AdminMessageSourceController.DIR)
public class AdminMessageSourceController {

    public static final String   DIR = "admin/messageSource";

    @Autowired
    private MessageSourceService messageSourceService;

    @Autowired
    private MessageSourceHandler messageSourceHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, MessageSourceQuery query) {

        Page<MessageSource> page = messageSourceService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMessageSourceVO> list = messageSourceHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/publicservice-MessageSource-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/publicservice-MessageSource-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MessageSource messageSource) {

        messageSourceService.add(messageSource);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MessageSource messageSource = messageSourceService.get(id);
        AdminMessageSourceVO adminMessageSourceVO = messageSourceHandler.toVO4Admin(messageSource);
        ModelAndView model = new ModelAndView("/admin/publicservice-MessageSource-edit");
        model.addObject("messageSource", adminMessageSourceVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MessageSource messageSource, String queryStr) {

        messageSourceService.update(messageSource);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        messageSourceService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
