package com.qcloud.component.personalcenter.web.controller.admin;

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
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.service.MyWealthDetailService;
import com.qcloud.component.personalcenter.web.handler.MyWealthDetailHandler;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyWealthDetailVO;

@Controller
@RequestMapping(value = "/" + AdminMyWealthDetailController.DIR)
public class AdminMyWealthDetailController {

    public static final String    DIR = "admin/myWealthDetail";

    @Autowired
    private MyWealthDetailService myWealthDetailService;

    @Autowired
    private MyWealthDetailHandler myWealthDetailHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, MyWealthDetailQuery query) {

        Page<MyWealthDetail> page = myWealthDetailService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMyWealthDetailVO> list = myWealthDetailHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyWealthDetail-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-MyWealthDetail-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MyWealthDetail myWealthDetail) {

        myWealthDetailService.add(myWealthDetail);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MyWealthDetail myWealthDetail = myWealthDetailService.get(id);
        AdminMyWealthDetailVO adminMyWealthDetailVO = myWealthDetailHandler.toVO4Admin(myWealthDetail);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MyWealthDetail-edit");
        model.addObject("myWealthDetail", adminMyWealthDetailVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MyWealthDetail myWealthDetail) {

        myWealthDetailService.update(myWealthDetail);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        myWealthDetailService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView getWealthDetails(Long wealthId, Long userId, int type, int pageNum) {

        AssertUtil.assertNotNull(wealthId, "ID不能为空");
        AssertUtil.assertNotNull(userId, "ID不能为空");
        AssertUtil.assertNotNull(type, "type不能为空");
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<MyWealthDetail> myWealthDetail = myWealthDetailService.getWealthDetails(wealthId, userId, type, start, PAGE_SIZE);
        List<AdminMyWealthDetailVO> voList = myWealthDetailHandler.toVOList4Admin(myWealthDetail.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyWealthDetail-list", DIR + "/getWealthDetails?wealthId=" + wealthId + "&userId=" + userId + "&type=" + type, pageNum, PAGE_SIZE, myWealthDetail.getCount());
        pagingView.addObject("result", voList);
        return pagingView;
    }
}
