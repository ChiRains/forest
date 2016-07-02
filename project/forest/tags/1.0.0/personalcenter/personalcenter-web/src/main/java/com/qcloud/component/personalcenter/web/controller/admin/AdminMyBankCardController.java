package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.MyBankCard;
import com.qcloud.component.personalcenter.model.query.MyBankCardQuery;
import com.qcloud.component.personalcenter.service.MyBankCardService;
import com.qcloud.component.personalcenter.web.handler.MyBankCardHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMyBankCardVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMyBankCardController.DIR)
public class AdminMyBankCardController {

    public static final String DIR = "admin/myBankCard";

    @Autowired
    private MyBankCardService  myBankCardService;

    @Autowired
    private MyBankCardHandler  myBankCardHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, MyBankCardQuery query) {

        Page<MyBankCard> page = myBankCardService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMyBankCardVO> list = myBankCardHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MyBankCard-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-MyBankCard-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MyBankCard myBankCard) {

        myBankCardService.add(myBankCard);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MyBankCard myBankCard = myBankCardService.get(id);
        AdminMyBankCardVO adminMyBankCardVO = myBankCardHandler.toVO4Admin(myBankCard);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MyBankCard-edit");
        model.addObject("myBankCard", adminMyBankCardVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MyBankCard myBankCard) {

        myBankCardService.update(myBankCard);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        myBankCardService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
