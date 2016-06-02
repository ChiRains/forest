package com.qcloud.component.brokerage.web.controller.admin;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.query.UserRelationshipQuery;
import com.qcloud.component.brokerage.service.UserRelationshipService;
import com.qcloud.component.brokerage.web.handler.UserRelationshipHandler;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserRelationshipVO;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminUserRelationshipController.DIR)
public class AdminUserRelationshipController {

    public static final String      DIR = "admin/userRelationship";

    @Autowired
    private UserRelationshipService userRelationshipService;

    @Autowired
    private UserRelationshipHandler userRelationshipHandler;

    @Autowired
    private PersonalcenterClient    personalcenterClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, UserRelationshipQuery query) {

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        if (!StringUtils.isEmpty(query.getAccount())) {
            QUser user = personalcenterClient.getUser(query.getAccount());
            query.setRecommedId(user.getId());
        }
        Page<UserRelationship> page = userRelationshipService.pageRecommed(query, start, PAGE_SIZE);
        List<AdminUserRelationshipVO> list = userRelationshipHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-UserRelationship-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView listDetail(Long recommedId) {

        AssertUtil.assertNotNull(recommedId, "推荐人标识不能为空.");
        List<UserRelationship> list = userRelationshipService.listChildren(recommedId, null, 3);
        List<AdminUserRelationshipVO> voList = userRelationshipHandler.toVOList4Recommed(list);
        ModelAndView model = new ModelAndView("/admin/brokerage-UserRelationship-listDetail");
        model.addObject("result", voList);
        return model;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/brokerage-UserRelationship-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(UserRelationship userRelationship) {

        userRelationshipService.add(userRelationship);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        UserRelationship userRelationship = userRelationshipService.get(id);
        AdminUserRelationshipVO adminUserRelationshipVO = userRelationshipHandler.toVO4Admin(userRelationship);
        ModelAndView model = new ModelAndView("/admin/brokerage-UserRelationship-edit");
        model.addObject("userRelationship", adminUserRelationshipVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(UserRelationship userRelationship) {

        userRelationshipService.update(userRelationship);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        userRelationshipService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
