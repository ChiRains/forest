package com.qcloud.component.brokerage.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.UserDistributionGrade;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.service.UserDistributionGradeService;
import com.qcloud.component.brokerage.web.form.UserGradeForm;
import com.qcloud.component.brokerage.web.form.UserGradeList;
import com.qcloud.component.brokerage.web.handler.UserDistributionGradeHandler;
import com.qcloud.component.brokerage.model.query.UserDistributionGradeQuery;
import com.qcloud.component.brokerage.web.vo.admin.AdminUserDistributionGradeVO;

@Controller
@RequestMapping(value = "/" + AdminUserDistributionGradeController.DIR)
public class AdminUserDistributionGradeController {

    public static final String           DIR = "admin/userDistributionGrade";

    @Autowired
    private UserDistributionGradeService userDistributionGradeService;

    @Autowired
    private UserDistributionGradeHandler userDistributionGradeHandler;

    @Autowired
    private DistributionGradeService     distributionGradeService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, UserDistributionGradeQuery query) {

        Page<UserDistributionGrade> page = userDistributionGradeService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminUserDistributionGradeVO> list = userDistributionGradeHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-UserDistributionGrade-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(Long gradeId) {

        ModelAndView model = new ModelAndView("/admin/brokerage-UserDistributionGrade-add");
        model.addObject("gradeId", gradeId);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(UserGradeForm form) {

        DistributionGrade distributionGrade = distributionGradeService.get(form.getGradeId());
        List<UserGradeList> list = form.getUserGradeList();
        for (UserGradeList temp : list) {
            UserDistributionGrade userDistributionGrade = userDistributionGradeService.getByUserForAdmin(temp.getUserId());
            if (userDistributionGrade == null) {
                userDistributionGrade = new UserDistributionGrade();
                userDistributionGrade.setGradeId(form.getGradeId());
                userDistributionGrade.setUserId(temp.getUserId());
                userDistributionGrade.setCreateTime(new Date());
                userDistributionGrade.setUpgradeTime(new Date());
                userDistributionGrade.setEffectiveTime(DateUtil.addDate(new Date(), 30 * distributionGrade.getMonthLimit()));
                userDistributionGradeService.add(userDistributionGrade);
            } else {
                DistributionGrade current = distributionGradeService.get(userDistributionGrade.getGradeId());
                if (distributionGrade.getBsid().startsWith(current.getBsid()) && !current.getBsid().equals(distributionGrade.getBsid())) {
                    //
                    userDistributionGrade.setGradeId(form.getGradeId());
                    userDistributionGrade.setUpgradeTime(new Date());
                    userDistributionGrade.setEffectiveTime(DateUtil.addDate(new Date(), 31 * distributionGrade.getMonthLimit()));
                    userDistributionGradeService.update(userDistributionGrade);
                } else {
                    AssertUtil.assertTrue(false, "等级调整只能从低级调整到高级.当前用户:" + temp.getName() + "的会员等级为:" + current.getName());
                }
            }
        }
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?gradeId=" + form.getGradeId());
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        UserDistributionGrade userDistributionGrade = userDistributionGradeService.get(id);
        AdminUserDistributionGradeVO adminUserDistributionGradeVO = userDistributionGradeHandler.toVO4Admin(userDistributionGrade);
        ModelAndView model = new ModelAndView("/admin/brokerage-UserDistributionGrade-edit");
        model.addObject("userDistributionGrade", adminUserDistributionGradeVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(UserDistributionGrade userDistributionGrade, String queryStr) {

        userDistributionGradeService.update(userDistributionGrade);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        userDistributionGradeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
