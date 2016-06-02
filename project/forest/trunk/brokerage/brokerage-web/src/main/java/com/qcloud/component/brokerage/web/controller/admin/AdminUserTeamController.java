package com.qcloud.component.brokerage.web.controller.admin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.brokerage.model.UserTeam;
import com.qcloud.component.brokerage.model.query.UserTeamQuery;
import com.qcloud.component.brokerage.service.UserTeamService;
import com.qcloud.component.brokerage.web.handler.UserTeamHandler;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserTeamVO;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
@Controller
@RequestMapping(value = "/" + AdminUserTeamController.DIR)
public class AdminUserTeamController {
    public static final String DIR = "admin/userTeam";
    @Autowired
    private UserTeamService    userTeamService;
    @Autowired
    private UserTeamHandler    userTeamHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, UserTeamQuery query) {
        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        Page<UserTeam> page = userTeamService.pageLeader(query, start, PAGE_SIZE);
        List<AdminUserTeamVO> list = userTeamHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-UserTeam-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView listDetail(Long leader) {
        AssertUtil.assertNotNull(leader, "推荐人标识不能为空.");
        List<UserTeam> list = userTeamService.listChildren(leader);
        List<AdminUserTeamVO> voList = userTeamHandler.toVOList4Admin(list);
        ModelAndView model = new ModelAndView("/admin/brokerage-UserTeam-listDetail");
        model.addObject("result", voList);
        return model;
    }

    @RequestMapping
    public ModelAndView toAdd() {
        ModelAndView model = new ModelAndView("/admin/brokerage-UserTeam-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(UserTeam userTeam) {
        userTeamService.add(userTeam);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        UserTeam userTeam = userTeamService.get(id);
        AdminUserTeamVO adminUserTeamVO = userTeamHandler.toVO4Admin(userTeam);
        ModelAndView model = new ModelAndView("/admin/brokerage-UserTeam-edit");
        model.addObject("userTeam", adminUserTeamVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(UserTeam userTeam) {
        userTeamService.update(userTeam);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {
        AssertUtil.assertNotNull(id, "ID不能为空");
        userTeamService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
