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
import com.qcloud.component.admin.web.vo.AdminVO;
import com.qcloud.component.brokerage.model.DataPool;
import com.qcloud.component.brokerage.model.DistributionBrokerage;
import com.qcloud.component.brokerage.model.FormulaCalculationResult;
import com.qcloud.component.brokerage.service.DataPoolService;
import com.qcloud.component.brokerage.service.DistributionBrokerageService;
import com.qcloud.component.brokerage.service.DistributionDataPoolTypeService;
import com.qcloud.component.brokerage.service.FormulaCalculationResultService;
import com.qcloud.component.brokerage.web.handler.DataPoolHandler;
import com.qcloud.component.brokerage.web.handler.DistributionBrokerageHandler;
import com.qcloud.component.brokerage.web.handler.FormulaCalculationResultHandler;
import com.qcloud.component.brokerage.model.key.TypeEnum.DistributionBrokerageStateType;
import com.qcloud.component.brokerage.model.query.DistributionBrokerageQuery;
import com.qcloud.component.brokerage.web.vo.admin.AdminDataPoolVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionBrokerageVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminFormulaCalculationResultVO;

@Controller
@RequestMapping(value = "/" + AdminDistributionBrokerageController.DIR)
public class AdminDistributionBrokerageController {

    public static final String              DIR = "admin/distributionBrokerage";

    @Autowired
    private DistributionBrokerageService    distributionBrokerageService;

    @Autowired
    private DistributionBrokerageHandler    distributionBrokerageHandler;

    @Autowired
    private DistributionDataPoolTypeService distributionDataPoolTypeService;

    @Autowired
    private DataPoolService                 dataPoolService;

    @Autowired
    private DataPoolHandler                 poolHandler;

    @Autowired
    private FormulaCalculationResultService formulaCalculationResultService;

    @Autowired
    private FormulaCalculationResultHandler formulaCalculationResultHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, DistributionBrokerageQuery query) {

        if (query.getState() == 0) {
            query.setState(1);
        }
        Page<DistributionBrokerage> page = distributionBrokerageService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminDistributionBrokerageVO> list = distributionBrokerageHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/brokerage-DistributionBrokerage-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("stateType", DistributionBrokerageStateType.values());
        pagingView.addObject("query", query);
        pagingView.addObject("dataPoolType", distributionDataPoolTypeService.listDataPoolType());
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/brokerage-DistributionBrokerage-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(DistributionBrokerage distributionBrokerage) {

        distributionBrokerageService.add(distributionBrokerage);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        DistributionBrokerage distributionBrokerage = distributionBrokerageService.get(id);
        AdminDistributionBrokerageVO adminDistributionBrokerageVO = distributionBrokerageHandler.toVO4Admin(distributionBrokerage);
        ModelAndView model = new ModelAndView("/admin/brokerage-DistributionBrokerage-edit");
        model.addObject("distributionBrokerage", adminDistributionBrokerageVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(DistributionBrokerage distributionBrokerage, String queryStr) {

        distributionBrokerageService.update(distributionBrokerage);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        distributionBrokerageService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView getDetails(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        DistributionBrokerage distributionBrokerage = distributionBrokerageService.get(id);
        AdminDistributionBrokerageVO adminDistributionBrokerageVO = distributionBrokerageHandler.toVO4Admin(distributionBrokerage);
        FormulaCalculationResult formulaCalculationResult = formulaCalculationResultService.get(adminDistributionBrokerageVO.getResultId());
        AssertUtil.assertNotNull(formulaCalculationResult, "计算结果不存在.");
        AdminFormulaCalculationResultVO resultVO = formulaCalculationResultHandler.toVO4Admin(formulaCalculationResult);
        DataPool dataPool = dataPoolService.get(formulaCalculationResult.getDataPoolId());
        AssertUtil.assertNotNull(dataPool, "数据源不存在.");
        AdminDataPoolVO dataPoolVO = poolHandler.toVO4Admin(dataPool);
        ModelAndView model = new ModelAndView("/admin/brokerage-DistributionBrokerage-details");
        model.addObject("distributionBrokerage", adminDistributionBrokerageVO);
        model.addObject("result", resultVO);
        model.addObject("dataPool", dataPoolVO);
        return model;
    }
}
