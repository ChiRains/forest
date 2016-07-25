package com.qcloud.project.forest.web.controller.admin;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.PersonalcenterClient;
import com.qcloud.component.personalcenter.QGrade;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.page.PageParameterUtil;
import com.qcloud.pirates.web.page.PiratesParameterKey;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.key.TypeEnum;
import com.qcloud.project.forest.model.query.RangeGradeQuery;
import com.qcloud.project.forest.service.RangeGradeService;
import com.qcloud.project.forest.web.handler.RangeGradeHandler;
import com.qcloud.project.forest.web.vo.admin.AdminRangeGradeVO;

@Controller
@RequestMapping(value = "/" + AdminRangeGradeController.DIR)
public class AdminRangeGradeController {

    public static final String   DIR = "admin/rangeGrade";

    @Autowired
    private RangeGradeService    rangeGradeService;

    @Autowired
    private RangeGradeHandler    rangeGradeHandler;

    @Autowired
    private PublicdataClient     publicdataClient;

    @Autowired
    private PersonalcenterClient personalcenterClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, PPage pPage, RangeGradeQuery query) {

        Page<RangeGrade> page = rangeGradeService.page(query, pPage.getPageStart(), pPage.getPageSize());
        List<AdminRangeGradeVO> list = rangeGradeHandler.toVOList4Admin(page.getData());
        String pageQueryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.PAGE_QUERY_STRING));
        String queryStr = StringUtil.nullToEmpty((String) PageParameterUtil.getParameterValues(request, PiratesParameterKey.QUERY_STRING));
        AcePagingView pagingView = new AcePagingView("/admin/forest-RangeGrade-list", DIR + "/list?" + pageQueryStr, pPage.getPageNum(), pPage.getPageSize(), page.getCount());
        pagingView.addObject("result", list);
        pagingView.addObject("queryStr", URLEncoder.encode(queryStr));
        pagingView.addObject("query", query);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(Long rangeId) {

        List<QGrade> list = personalcenterClient.getGradeList();
        ModelAndView model = new ModelAndView("/admin/forest-RangeGrade-add");
        model.addObject("result", list);
        model.addObject("rangeId", rangeId);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(RangeGrade rangeGrade) {

        rangeGradeService.add(rangeGrade);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list?rangeId=" + rangeGrade.getRangeId());
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        RangeGrade rangeGrade = rangeGradeService.get(id);
        AdminRangeGradeVO adminRangeGradeVO = rangeGradeHandler.toVO4Admin(rangeGrade);
        ModelAndView model = new ModelAndView("/admin/forest-RangeGrade-edit");
        model.addObject("rangeGrade", adminRangeGradeVO);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(RangeGrade rangeGrade, String queryStr) {

        rangeGradeService.update(rangeGrade);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        rangeGradeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView rangeList() {

        List<Classify> list = publicdataClient.listClassify((long) TypeEnum.ClassifyType.IntegralMerchandiseRange.getKey());
        ModelAndView view = new ModelAndView("/admin/forest-RangeGrade-listRange");
        view.addObject("result", list);
        return view;
    }

    @RequestMapping
    public ModelAndView toAddRange() {

        ModelAndView model = new ModelAndView("/admin/forest-RangeGrade-addRange");
        return model;
    }

    @RequestMapping
    public ModelAndView addRange(Classify classify) {

        classify.setParentId(-1);
        classify.setType(TypeEnum.ClassifyType.IntegralMerchandiseRange.getKey());
        publicdataClient.addClassify(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/rangeList");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEditRange(Long id) {

        Classify result = publicdataClient.getClassify(id);
        AssertUtil.assertNotNull(id, "ID不能为空");
        ModelAndView model = new ModelAndView("/admin/forest-RangeGrade-editRange");
        model.addObject("classify", result);
        return model;
    }

    @RequestMapping
    public AceAjaxView editRange(Classify classify) {

        classify.setType(TypeEnum.ClassifyType.IntegralMerchandiseRange.getKey());
        classify.setParentId(-1);
        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/classifyList");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView deleteRange(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        publicdataClient.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/classifyList");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView enableRange(Long id, Integer enable) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Classify classify = publicdataClient.getClassify(id);
        classify.setEnable(enable);
        publicdataClient.update(classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("启用成功");
        aceAjaxView.setUrl(DIR + "/classifyList");
        return aceAjaxView;
    }
}
