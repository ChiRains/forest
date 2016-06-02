package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.MySignInMonth;
import com.qcloud.component.personalcenter.model.query.MySignInMonthQuery;
import com.qcloud.component.personalcenter.service.MySignInMonthService;
import com.qcloud.component.personalcenter.web.handler.MySignInMonthHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInMonthVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMySignInMonthController.DIR)
public class AdminMySignInMonthController {

    public static final String   DIR = "admin/mySignInMonth";

    @Autowired
    private MySignInMonthService mySignInMonthService;

    @Autowired
    private MySignInMonthHandler mySignInMonthHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, MySignInMonthQuery query) {

        Page<MySignInMonth> page = mySignInMonthService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMySignInMonthVO> list = mySignInMonthHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MySignInMonth-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-MySignInMonth-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MySignInMonth mySignInMonth) {

        mySignInMonthService.add(mySignInMonth);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MySignInMonth mySignInMonth = mySignInMonthService.get(id);
        AdminMySignInMonthVO adminMySignInMonthVO = mySignInMonthHandler.toVO4Admin(mySignInMonth);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MySignInMonth-edit");
        model.addObject("mySignInMonth", adminMySignInMonthVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MySignInMonth mySignInMonth) {

        mySignInMonthService.update(mySignInMonth);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        mySignInMonthService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
