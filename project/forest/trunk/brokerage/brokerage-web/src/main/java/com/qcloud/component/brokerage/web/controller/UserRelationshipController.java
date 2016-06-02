package com.qcloud.component.brokerage.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.component.brokerage.exception.BrokerageException;
import com.qcloud.component.brokerage.model.UserRelationship;
import com.qcloud.component.brokerage.model.UserShareToken;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.key.TypeEnum.UserAllocationType;
import com.qcloud.component.brokerage.service.UserRelationshipService;
import com.qcloud.component.brokerage.service.UserShareTokenService;
import com.qcloud.component.brokerage.service.UserTeamService;
import com.qcloud.component.brokerage.web.handler.AllocationUserHandler;
import com.qcloud.component.brokerage.web.vo.AllocationUser;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QUser;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.mvc.FrontPagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = UserRelationshipController.DIR)
public class UserRelationshipController {

    //
    public static final String      DIR = "/userRelationship";

    @Autowired
    private UserRelationshipService userRelationshipService;

    @Autowired
    private PersonalcenterClient    personalcenterClient;

    @Autowired
    private AllocationUserHandler   allocationUserHandler;

    @Autowired
    private UserShareTokenService   userShareTokenService;

    @Autowired
    private UserTeamService         userTeamService;

    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView stat4Level(String recommedToken, Integer level) {
    //
    // level = level == null || level <= 0 || level > 3 ? 3 : level;
    // UserShareToken userShareToken = userShareTokenService.getByToken(recommedToken);
    // AssertUtil.assertNotNull(recommedToken, "用户分佣分享token不存在." + recommedToken);
    // List<UserRelationship> list = userRelationshipService.listChildren(userShareToken.getUserId(), null, level);
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("查询我的推荐人成功.");
    // return view;
    // }
    @RequestMapping
    @NoReferer
    public FrontAjaxView list4Level(String recommedToken, Integer level) {

        level = level == null || level <= 0 || level > 3 ? 3 : level;
        UserShareToken userShareToken = userShareTokenService.getByToken(recommedToken);
        AssertUtil.assertNotNull(recommedToken, "用户分佣分享token不存在." + recommedToken);
        List<UserRelationship> list = userRelationshipService.listChildren(userShareToken.getUserId(), null, level);
        List<AllocationUser> voList = allocationUserHandler.toVOList4Relationship(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的推荐人成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView list(String recommedToken) {

        UserShareToken userShareToken = userShareTokenService.getByToken(recommedToken);
        AssertUtil.assertNotNull(recommedToken, "用户分佣分享token不存在." + recommedToken);
        List<UserRelationship> list = userRelationshipService.listChildren(userShareToken.getUserId(), null);
        List<AllocationUser> voList = allocationUserHandler.toVOList4Relationship(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的推荐人成功.");
        view.addObject("list", voList);
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontPagingView listByType(String recommedToken, Integer pageNum, Integer pageSize, Integer type) {

        UserShareToken userShareToken = userShareTokenService.getByToken(recommedToken);
        AssertUtil.assertNotNull(recommedToken, "用户分佣分享token不存在." + recommedToken);
        final int PAGE_SIZE = pageSize == null || pageSize <= 0 ? 10 : pageSize;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<UserRelationship> page = new Page<UserRelationship>();
        switch (type) {
        case 1:
            page = userRelationshipService.pageOneChildren(userShareToken.getUserId(), start, PAGE_SIZE);
            break;
        case 2:
            page = userRelationshipService.pageTwoChildren(userShareToken.getUserId(), start, PAGE_SIZE);
            break;
        case 3:
            page = userRelationshipService.pageThreeChildren(userShareToken.getUserId(), start, PAGE_SIZE);
            break;
        default:
            throw new BrokerageException("类型错误!");
        }
        List<AllocationUser> voList = allocationUserHandler.toVOList4Relationship(page.getData());
        FrontPagingView view = new FrontPagingView(pageNum, PAGE_SIZE, page.getCount());
        view.setMessage("查询我的推荐人成功.");
        view.addObject("list", voList);
        view.addObject("level", type);
        return view;
    }

    @RequestMapping
    @NoReferer
    public FrontAjaxView listByCount(String recommedToken) {

        UserShareToken userShareToken = userShareTokenService.getByToken(recommedToken);
        AssertUtil.assertNotNull(recommedToken, "用户分佣分享token不存在." + recommedToken);
        int countOne = userRelationshipService.getCountByOneChildren(userShareToken.getUserId());
        int countTwo = userRelationshipService.getCountByTwoChildren(userShareToken.getUserId());
        int countThree = userRelationshipService.getCountByThreeChildren(userShareToken.getUserId());
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的推荐人成功.");
        view.addObject("countOne", countOne);
        view.addObject("countTwo", countTwo);
        view.addObject("countThree", countThree);
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView listNotAllocation(String recommedToken) {

        UserShareToken userShareToken = userShareTokenService.getByToken(recommedToken);
        AssertUtil.assertNotNull(recommedToken, "用户分佣分享token不存在." + recommedToken);
        List<UserRelationship> list = userRelationshipService.listChildren(userShareToken.getUserId(), UserAllocationType.NOT_ALLOCATION);
        List<AllocationUser> voList = allocationUserHandler.toVOList4Relationship(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的推荐人成功.");
        view.addObject("list", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView buildByToken(HttpServletRequest request, String recommedToken) {

        AssertUtil.assertNotEmpty(recommedToken, "推荐人token不能为空.");
        UserShareToken userShareToken = userShareTokenService.getByToken(recommedToken);
        AssertUtil.assertNotNull(userShareToken, "推荐人token不存在." + recommedToken);
        QUser rq = personalcenterClient.getUser(userShareToken.getUserId());
        return build(request, rq.getMobile());
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView build(HttpServletRequest request, String recommedMobile) {

        QUser rq = personalcenterClient.getUser(recommedMobile);
        AssertUtil.assertNotNull(rq, "用户信息不存在" + recommedMobile);
        QUser uq = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        // 两行代码,把和分佣有关的数据先初始化好
        userShareTokenService.getByUser(rq.getId());
        userShareTokenService.getByUser(uq.getId());
        // 两行代码,把和分佣有关的数据先初始化好
        if (rq.getId() == uq.getId()) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("不允许自己推荐自己.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        UserRelationship userRelationship = userRelationshipService.getByUserId(uq.getId());
        if (userRelationship != null) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("推荐关系已经建立过.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        } else {
            int count = userRelationshipService.countChildren(uq.getId(), null);
            if (count == 0) {
                UserRelationship ur = new UserRelationship();
                ur.setUserId(uq.getId());
                ur.setRecommedId(rq.getId());
                ur.setAllocation(UserAllocationType.NOT_ALLOCATION.getKey());
                ur.setTime(new Date());
                userRelationshipService.add(ur);
                FrontAjaxView view = new FrontAjaxView();
                view.setMessage("建立推荐关系成功");
                view.addObject("result", Boolean.TRUE.toString());
                return view;
            } else {
                FrontAjaxView view = new FrontAjaxView();
                view.setMessage("当前用户已经推荐了其他人,不允许作为被推荐人注册.");
                view.addObject("result", Boolean.FALSE.toString());
                return view;
            }
        }
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView stat(HttpServletRequest request, Integer number) {

        number = number == null || number <= 0 || number > 10 ? 4 : number;
        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        List<UserRelationship> list = userRelationshipService.listChildren(user.getId(), null);
        List<UserRelationship> notAllocationList = userRelationshipService.listChildren(user.getId(), UserAllocationType.NOT_ALLOCATION);
        List<UserTeam> teamList = userTeamService.listChildren(user.getId());
        List<QUser> userList = new ArrayList<QUser>();
        Set<Long> idSet = new HashSet<Long>();
        for (UserRelationship userRelationship : list) {
            if (idSet.add(userRelationship.getUserId())) {
                userList.add(personalcenterClient.getUser(userRelationship.getUserId()));
            }
        }
        for (UserTeam userTeam : teamList) {
            if (idSet.add(userTeam.getUserId())) {
                userList.add(personalcenterClient.getUser(userTeam.getUserId()));
            }
        }
        Collections.sort(userList, new Comparator<QUser>() {

            @Override
            public int compare(QUser u1, QUser u2) {

                return u1.getEntryDate().after(u2.getEntryDate()) ? 1 : -1;
            }
        });
        //
        if (userList.size() > number) {
            userList = userList.subList(0, number);
        }
        List<AllocationUser> voList = new ArrayList<AllocationUser>();
        for (QUser u : userList) {
            AllocationUser au = new AllocationUser();
            au.setName(u.getName());
            au.setEntryDateStr(DateUtil.date2String(u.getEntryDate()));
            au.setSexStr(SexType.getSexType(u.getSex()).getValue());
            voList.add(au);
        }
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("获取我的团队基本信息成功.");
        view.addObject("totalNumber", idSet.size());
        view.addObject("teamNumber", teamList.size());
        view.addObject("recommendNumber", list.size());
        view.addObject("notAllocationNumber", notAllocationList.size());
        view.addObject("newMemberList", voList);
        return view;
    }

    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView getRecommedUserMobile(HttpServletRequest request) {

        QUser user = PageParameterUtil.getParameterValues(request, PersonalcenterClient.USER_LOGIN_PARAMETER_KEY);
        UserRelationship userRelationship = userRelationshipService.getByUserId(user.getId());
        FrontAjaxView view = new FrontAjaxView();
        if (userRelationship == null) {
            view.addObject("mobile", "");
        } else {
            QUser recommed = personalcenterClient.getUser(userRelationship.getRecommedId());
            view.addObject("mobile", recommed.getMobile());
        }
        return view;
    }
}
