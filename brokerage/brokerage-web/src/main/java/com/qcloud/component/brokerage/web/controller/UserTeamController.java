package com.qcloud.component.brokerage.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.qcloud.pirates.mvc.FrontAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.mvc.annotation.PiratesApp;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = UserTeamController.DIR)
public class UserTeamController {

    public static final String      DIR = "/userTeam";

    @Autowired
    private UserTeamService         userTeamService;

    @Autowired
    private UserShareTokenService   userShareTokenService;

    @Autowired
    private PersonalcenterClient    personalcenterClient;

    @Autowired
    private UserRelationshipService userRelationshipService;

    @Autowired
    private AllocationUserHandler   allocationUserHandler;
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView list(String leaderToken) {

        UserShareToken leaderShareToken = userShareTokenService.getByToken(leaderToken);
        AssertUtil.assertNotNull(leaderShareToken, "用户分佣分享token不存在." + leaderToken);
        List<UserTeam> list = userTeamService.listChildren(leaderShareToken.getUserId());
        List<AllocationUser> voList = allocationUserHandler.toVOList4Team(list);
        FrontAjaxView view = new FrontAjaxView();
        view.setMessage("查询我的部门成功.");
        view.addObject("list", voList);
        return view;
    }

    // recommedToken 推荐人,只有推荐人可以设置
    // leaderToken 组织上级
    // userToken 加入组织成员
    @PiratesApp
    @RequestMapping
    @NoReferer
    public FrontAjaxView build(String recommedToken, String leaderToken, String userToken) {

        UserShareToken rt = userShareTokenService.getByToken(recommedToken);
        AssertUtil.assertNotNull(rt, "推荐人信息不存在" + recommedToken);
        QUser rq = personalcenterClient.getUser(rt.getUserId());
        AssertUtil.assertNotNull(rq, "推荐人信息不存在" + rt.getUserId());
        UserShareToken lt = userShareTokenService.getByToken(leaderToken);
        AssertUtil.assertNotNull(lt, "上级信息不存在" + leaderToken);
        QUser lq = personalcenterClient.getUser(lt.getUserId());
        AssertUtil.assertNotNull(lq, "上级信息不存在" + lt.getUserId());
        UserShareToken ut = userShareTokenService.getByToken(userToken);
        AssertUtil.assertNotNull(ut, "用户信息不存在" + userToken);
        QUser uq = personalcenterClient.getUser(ut.getUserId());
        AssertUtil.assertNotNull(uq, "用户信息不存在" + ut.getUserId());
        if (recommedToken.equals(userToken)) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("不允许自己设置上级.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        if (leaderToken.equals(userToken)) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("不允许自己作为自己的上级.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        int teamUserNum = userTeamService.countChildren(lq.getId());
        if (teamUserNum >= 5) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("组织已经满员.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        UserRelationship userRelationship = userRelationshipService.getByUserId(uq.getId());
        AssertUtil.assertNotNull(userRelationship, "组织成员尚未建立推荐关系." + uq.getName());
        if (userRelationship.getRecommedId() != rq.getId()) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("组织成员所属只能由推荐人设定.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        boolean isLeader = userTeamService.isInLeader(lq.getId(), rq.getId());
        if (!isLeader) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("指定上级不是推荐人本人或其下级.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        }
        UserTeam userTeam = userTeamService.getByUserId(uq.getId());
        if (userTeam != null) {
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("用户组织关系已经建立过.");
            view.addObject("result", Boolean.FALSE.toString());
            return view;
        } else {
            userTeam = new UserTeam();
            userTeam.setLeader(lt.getUserId());
            userTeam.setUserId(ut.getUserId());
            userTeamService.add(userTeam);
            userRelationship.setAllocation(UserAllocationType.ALLOCATION.getKey());
            userRelationshipService.update(userRelationship);
            FrontAjaxView view = new FrontAjaxView();
            view.setMessage("设置组织成功.");
            view.addObject("result", Boolean.TRUE.toString());
            return view;
        }
    }
}
