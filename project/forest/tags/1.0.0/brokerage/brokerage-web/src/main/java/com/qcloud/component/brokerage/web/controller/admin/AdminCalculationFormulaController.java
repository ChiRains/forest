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
import com.qcloud.component.brokerage.model.CalculationFormula;
import com.qcloud.component.brokerage.service.CalculationFormulaService;
import com.qcloud.component.brokerage.web.handler.CalculationFormulaHandler;
import com.qcloud.component.brokerage.model.query.CalculationFormulaQuery;
import com.qcloud.component.brokerage.web.vo.CalculationFormulaVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminCalculationFormulaVO;
import com.qcloud.component.publicdata.EnableType;

@Controller
@RequestMapping(value = "/" + AdminCalculationFormulaController.DIR)
public class AdminCalculationFormulaController {

    public static final String        DIR = "admin/calculationFormula";

    @Autowired
    private CalculationFormulaService calculationFormulaService;

    @Autowired
    private CalculationFormulaHandler calculationFormulaHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, CalculationFormulaQuery query) {

        Page<CalculationFormula> page = calculationFormulaService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminCalculationFormulaVO> list = calculationFormulaHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-CalculationFormula-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/brokerage-CalculationFormula-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(CalculationFormula calculationFormula) {

        calculationFormula.setState(EnableType.ENABLE.getKey());
        calculationFormulaService.add(calculationFormula);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        CalculationFormula calculationFormula = calculationFormulaService.get(id);
        AssertUtil.assertNotNull(calculationFormula, "佣金公式不存在.");
        AdminCalculationFormulaVO adminCalculationFormulaVO = calculationFormulaHandler.toVO4Admin(calculationFormula);
        ModelAndView model = new ModelAndView("/admin/brokerage-CalculationFormula-edit");
        model.addObject("calculationFormula", adminCalculationFormulaVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(CalculationFormula calculationFormula, String queryStr) {

        AssertUtil.assertNotNull(calculationFormula.getId(), "ID不能为空");
        CalculationFormula old = calculationFormulaService.get(calculationFormula.getId());
        AssertUtil.assertNotNull(old, "佣金公式不存在.");
        calculationFormula.setState(old.getState());
        calculationFormulaService.update(calculationFormula);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        calculationFormulaService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    // 启用
    @RequestMapping
    public AceAjaxView enableFormula(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        CalculationFormula calculationFormula = calculationFormulaService.get(id);
        AssertUtil.assertNotNull(calculationFormula, "分佣公式不存在.");
        calculationFormula.setState(EnableType.ENABLE.getKey());
        calculationFormulaService.update(calculationFormula);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("启用成功.");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    // 停用
    @RequestMapping
    public AceAjaxView disableFormula(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        CalculationFormula calculationFormula = calculationFormulaService.get(id);
        AssertUtil.assertNotNull(calculationFormula, "分佣公式不存在.");
        calculationFormula.setState(EnableType.DISABLE.getKey());
        calculationFormulaService.update(calculationFormula);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("停用成功.");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView listBySelect() {

        List<CalculationFormula> list = calculationFormulaService.list();
        List<CalculationFormulaVO> voList = calculationFormulaHandler.toVOList(list);
        ModelAndView view = new ModelAndView("/admin/brokerage-CalculationFormula-listBySelect");
        view.addObject("list", voList);
        return view;
    }
}
