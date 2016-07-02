package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.CardNumberConfig;
import com.qcloud.component.personalcenter.model.MembershipCardWarehouse;
import com.qcloud.component.personalcenter.model.query.MembershipCardWarehouseQuery;
import com.qcloud.component.personalcenter.service.MembershipCardWarehouseService;
import com.qcloud.component.personalcenter.web.handler.MembershipCardWarehouseHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminMembershipCardWarehouseVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminMembershipCardWarehouseController.DIR)
public class AdminMembershipCardWarehouseController {

    public static final String             DIR = "admin/membershipCardWarehouse";

    @Autowired
    private MembershipCardWarehouseService membershipCardWarehouseService;

    @Autowired
    private MembershipCardWarehouseHandler membershipCardWarehouseHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, MembershipCardWarehouseQuery query) {

        Page<MembershipCardWarehouse> page = membershipCardWarehouseService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminMembershipCardWarehouseVO> list = membershipCardWarehouseHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-MembershipCardWarehouse-list", DIR + "/list", pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toInit() {

        CardNumberConfig config = membershipCardWarehouseService.getConfig();
        ModelAndView model = new ModelAndView("/admin/personalcenter-MembershipCardWarehouse-init");
        model.addObject("start", config.getStart());
        model.addObject("end", config.getEnd());
        return model;
    }

    @RequestMapping
    public AceAjaxView init(String startNumberStr, String endNumberStr) {

        AssertUtil.assertNotNull(startNumberStr, "开始号段不能为空");
        AssertUtil.assertNotNull(endNumberStr, "结束号段不能为空");
        AssertUtil.assertTrue(startNumberStr.length() == endNumberStr.length(), "卡号号段必须长度相等.");
        Long start = Long.valueOf(startNumberStr);
        Long end = Long.valueOf(endNumberStr);
        AssertUtil.assertTrue(end > start, "第一个号码必须小于最后一个号码.");
        membershipCardWarehouseService.init(startNumberStr, endNumberStr);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-MembershipCardWarehouse-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(MembershipCardWarehouse membershipCardWarehouse) {

        membershipCardWarehouseService.add(membershipCardWarehouse);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        MembershipCardWarehouse membershipCardWarehouse = membershipCardWarehouseService.get(id);
        AdminMembershipCardWarehouseVO adminMembershipCardWarehouseVO = membershipCardWarehouseHandler.toVO4Admin(membershipCardWarehouse);
        ModelAndView model = new ModelAndView("/admin/personalcenter-MembershipCardWarehouse-edit");
        model.addObject("membershipCardWarehouse", adminMembershipCardWarehouseVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(MembershipCardWarehouse membershipCardWarehouse) {

        membershipCardWarehouseService.update(membershipCardWarehouse);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        membershipCardWarehouseService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
