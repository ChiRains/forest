package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.parameter.ParameterClient;
import com.qcloud.component.personalcenter.exception.PersonalCenterException;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.User;
import com.qcloud.component.personalcenter.model.key.TypeEnum.AccountType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.EnableType;
import com.qcloud.component.personalcenter.model.key.TypeEnum.UserStateType;
import com.qcloud.component.personalcenter.model.query.UserQuery;
import com.qcloud.component.personalcenter.service.GradeService;
import com.qcloud.component.personalcenter.service.UserService;
import com.qcloud.component.personalcenter.web.handler.GradeHandler;
import com.qcloud.component.personalcenter.web.handler.UserHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminGradeVO;
import com.qcloud.component.personalcenter.web.vo.admin.AdminUserVO;
import com.qcloud.component.publicdata.KeyValueVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.SexType;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminUserController.DIR)
public class AdminUserController {

    public static final String  DIR               = "admin/user";

    @Autowired
    private UserService         userService;

    @Autowired
    private UserHandler         userHandler;

    @Autowired
    private GradeService        gradeService;

    @Autowired
    private GradeHandler        gradeHandler;

    Log                         logger            = LogFactory.getLog(getClass());

    @Autowired
    private PublicdataClient    publicdataClient;

    private static final String USER_PASSWORD_KEY = "personalcenter-user-default-password";

    @Autowired
    private ParameterClient     parameterClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(PPage pPage, UserQuery query) {

        Page<User> page = userService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminUserVO> list = userHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-User-list", DIR + "/list?name=" + StringUtil.nullToEmpty(query.getName()), pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-User-add");
        List<Grade> gradeList = gradeService.listAll();
        model.addObject("gradeList", gradeList);
        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), -1, "selected");
        model.addObject("sexTypeList", sexTypeList);
        String fileSize = publicdataClient.getImageInformationByCode("yonghutouxiang");
        model.addObject("fileSize", fileSize);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(User user) {

        user.setType(AccountType.REGISTER.getKey());
        user.setState(UserStateType.ACTIVATE.getKey());
        userService.add(user, null);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        List<Grade> gradeList = gradeService.listAll();
        User user = userService.get(id);
        List<AdminGradeVO> gradeVOList = gradeHandler.toVOList4Admin(gradeList, user.getGradeId());
        AdminUserVO adminUserVO = userHandler.toVO4Admin(user);
        ModelAndView model = new ModelAndView("/admin/personalcenter-User-edit");
        String fileSize = publicdataClient.getImageInformationByCode("yonghutouxiang");
        model.addObject("fileSize", fileSize);
        model.addObject("user", adminUserVO);
        model.addObject("gradeList", gradeVOList);
        List<KeyValueVO> sexTypeList = publicdataClient.exchageObj(SexType.values(), user.getSex(), "selected");
        model.addObject("sexTypeList", sexTypeList);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(User user) {

        userService.update(user);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        userService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView enable(Long id, Integer value) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        AssertUtil.assertNotNull(value, "value不能为空");
        User user = userService.get(id);
        AssertUtil.assertNotNull(user, "管理员不存在.");
        logger.info("enable " + id + " " + value);
        String message = null;
        if (EnableType.DISABLE.getKey() == value) {
            user.setState(UserStateType.FORBIDDEN.getKey());
            message = "禁用成功";
        } else if (EnableType.ENABLE.getKey() == value) {
            user.setState(UserStateType.ACTIVATE.getKey());
            message = "启用成功";
        } else {
            throw new PersonalCenterException("启、禁用状态不正确.");
        }
        // 不然密码被改
        userService.update(user);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage(message);
        aceAjaxView.setUrl("admin/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView listBySelect(PPage pPage, UserQuery query) {

        Page<User> page = userService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminUserVO> list = userHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/personalcenter-User-list4Select", DIR + "/listBySelect.do?name=" + StringUtil.nullToEmpty(query.getName()), pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public AceAjaxView activate(Long id) {

        User user = userService.get(id);
        AssertUtil.assertTrue(user.getState() == UserStateType.NEW.getKey(), "用户原状态不正确.");
        user.setState(UserStateType.ACTIVATE.getKey());
        userService.update(user);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("激活会员成功.");
        return view;
    }

    @RequestMapping
    public AceAjaxView resetPwd(Long id) {

        userService.resetPwd(id);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("重置密码成功.");
        return view;
    }
}
