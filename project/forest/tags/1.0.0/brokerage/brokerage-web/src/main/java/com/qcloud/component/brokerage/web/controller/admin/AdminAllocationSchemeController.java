package com.qcloud.component.brokerage.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.component.brokerage.model.AllocationScheme;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.service.AllocationSchemeService;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.web.handler.AllocationSchemeHandler;
import com.qcloud.component.brokerage.model.query.AllocationSchemeQuery;
import com.qcloud.component.brokerage.web.vo.admin.AdminAllocationSchemeVO;

@Controller
@RequestMapping(value = "/" + AdminAllocationSchemeController.DIR)
public class AdminAllocationSchemeController {

    public static final String       DIR = "admin/allocationScheme";

    @Autowired
    private AllocationSchemeService  allocationSchemeService;

    @Autowired
    private AllocationSchemeHandler  allocationSchemeHandler;

    @Autowired
    private DistributionGradeService distributionGradeService;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, AllocationSchemeQuery query) {

        Page<AllocationScheme> page = allocationSchemeService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminAllocationSchemeVO> list = allocationSchemeHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-AllocationScheme-list", DIR + "/list" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(Long formulaId) {

        List<DistributionGrade> gradeList = distributionGradeService.listAll();
        ModelAndView model = new ModelAndView("/admin/brokerage-AllocationScheme-add");
        model.addObject("formulaId", formulaId);
        model.addObject("gradeList", gradeList);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(AllocationScheme allocationScheme) {

        AssertUtil.greatZero(allocationScheme.getAllocationGradeId(), "请选择等级类别");
        allocationSchemeService.add(allocationScheme);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?formulaId="+allocationScheme.getFormulaId());
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, Long formulaId, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        AllocationScheme allocationScheme = allocationSchemeService.get(id);
        AdminAllocationSchemeVO adminAllocationSchemeVO = allocationSchemeHandler.toVO4Admin(allocationScheme);
        List<DistributionGrade> gradeList = distributionGradeService.listAll();
        ModelAndView model = new ModelAndView("/admin/brokerage-AllocationScheme-edit");
        model.addObject("allocationScheme", adminAllocationSchemeVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        model.addObject("formulaId", formulaId);
        model.addObject("gradeList", gradeList);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(AllocationScheme allocationScheme, String queryStr) {

        AssertUtil.greatZero(allocationScheme.getAllocationGradeId(), "请选择等级类别");
        allocationSchemeService.update(allocationScheme);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        allocationSchemeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
