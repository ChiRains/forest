package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.MySignInDay;
import com.qcloud.component.personalcenter.model.query.MySignInDayQuery;
import com.qcloud.component.personalcenter.service.MySignInDayService;
import com.qcloud.component.personalcenter.web.handler.MySignInDayHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMySignInDayVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMySignInDayController.DIR)
public class AdminMySignInDayController {

    public static final String DIR = "admin/mySignInDay";

    @Autowired
    private MySignInDayService mySignInDayService;

    @Autowired
    private MySignInDayHandler mySignInDayHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, MySignInDayQuery query) {

        Page<MySignInDay> page = mySignInDayService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMySignInDayVO> list = mySignInDayHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MySignInDay-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-MySignInDay-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MySignInDay mySignInDay) {

        mySignInDayService.add(mySignInDay);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MySignInDay mySignInDay = mySignInDayService.get(id);
        AdminMySignInDayVO adminMySignInDayVO = mySignInDayHandler.toVO4Admin(mySignInDay);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MySignInDay-edit");
        model.addObject("mySignInDay", adminMySignInDayVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MySignInDay mySignInDay) {

        mySignInDayService.update(mySignInDay);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        mySignInDayService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
