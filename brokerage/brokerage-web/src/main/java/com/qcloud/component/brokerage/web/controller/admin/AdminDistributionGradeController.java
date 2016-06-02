package com.qcloud.component.brokerage.web.controller.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.brokerage.model.DistributionGrade;
import com.qcloud.component.brokerage.model.key.TypeEnum;
import com.qcloud.component.brokerage.model.query.DistributionGradeQuery;
import com.qcloud.component.brokerage.service.DistributionGradeService;
import com.qcloud.component.brokerage.web.handler.DistributionGradeHandler;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionGradeTreeVO;
import com.qcloud.component.brokerage.web.vo.admin.AdminDistributionGradeVO;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.QClassify;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.component.publicdata.service.ClassifyService;
import com.qcloud.component.publicdata.web.handler.ClassifyHandler;
import com.qcloud.component.publicdata.web.vo.admin.AdminClassifyVO;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.StringUtil;
import com.qcloud.pirates.web.page.PPage;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminDistributionGradeController.DIR)
public class AdminDistributionGradeController {

    public static final String       DIR = "admin/distributionGrade";

    @Autowired
    private DistributionGradeService distributionGradeService;

    @Autowired
    private DistributionGradeHandler distributionGradeHandler;

    @Autowired
    private ClassifyHandler          classifyHandler;

    @Autowired
    private PublicdataClient         publicdataClient;

    @RequestMapping
    @NoReferer
    public ModelAndView list(HttpServletRequest request, Integer pageNum, PPage pPage, DistributionGradeQuery query) {

        List<AdminDistributionGradeTreeVO> treeList = distributionGradeHandler.toTree();
        ModelAndView model = new ModelAndView("/admin/brokerage-DistributionGrade-list");
        model.addObject("result", treeList);
        model.addObject("classType", TypeEnum.Distribution_Grade_Type);
        return model;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/brokerage-DistributionGrade-add");
        List<Classify> list = publicdataClient.listClassify((long) TypeEnum.Distribution_Grade_Type);
        List<AdminClassifyVO> voList = classifyHandler.toVOList4Admin(list, null, (long) TypeEnum.Distribution_Grade_Type);
        model.addObject("classifyList", voList);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(DistributionGrade distributionGrade, Long parentId) {

        AssertUtil.greatZero((long) distributionGrade.getCash(), "金额必须为正整数.");
        Classify classify = new Classify();
        classify.setName(distributionGrade.getName());
        classify.setParentId(parentId);
        classify.setType(TypeEnum.Distribution_Grade_Type);
        distributionGradeService.add(distributionGrade, classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id, String queryStr) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        DistributionGrade distributionGrade = distributionGradeService.get(id);
        AdminDistributionGradeVO adminDistributionGradeVO = distributionGradeHandler.toVO4Admin(distributionGrade);
        Classify classify = publicdataClient.getClassify(distributionGrade.getClassifyId());
        ModelAndView model = new ModelAndView("/admin/brokerage-DistributionGrade-edit");
        model.addObject("distributionGrade", adminDistributionGradeVO);
        //
        List<Classify> list = publicdataClient.listClassify((long) TypeEnum.Distribution_Grade_Type);
        List<AdminClassifyVO> voList = classifyHandler.toVOList4Admin(list, null, (long) TypeEnum.Distribution_Grade_Type);
        model.addObject("classifyList", voList);
        //
        model.addObject("classify", classify);
        model.addObject("queryStr", StringUtil.nullToEmpty(queryStr));
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(DistributionGrade temp, String queryStr, Long parentId) {

        DistributionGrade distributionGrade = distributionGradeService.get(temp.getId());
        distributionGrade.setName(temp.getName());
        distributionGrade.setCash(temp.getCash());
        distributionGrade.setDesc(temp.getDesc());
        distributionGrade.setMonthLimit(temp.getMonthLimit());
        distributionGrade.setUserResource(temp.getUserResource());
        //
        Classify classify = publicdataClient.getClassify(distributionGrade.getClassifyId());
        classify.setName(temp.getName());
        //
        distributionGradeService.update(distributionGrade, classify);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list?" + StringUtil.nullToEmpty(queryStr));
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        distributionGradeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView gradeList() {

        List<DistributionGrade> gradeList = distributionGradeService.listAll();
        AceAjaxView view = new AceAjaxView();
        view.addObject("gradeList", gradeList);
        return view;
    }

    @RequestMapping
    public AceAjaxView editFormula(Boolean isSelect, Long formulaId) {

        List<Long> list = distributionGradeService.gradeFormulaList();
        String formulaList = "";
        if (isSelect) {
            //
            if (!list.contains(formulaId)) {
                list.add(formulaId);
            }
        } else {
            list.remove(formulaId);
        }
        for (Long id : list) {
            formulaList = formulaList + id + ",";
        }
        distributionGradeService.editGradeFormula(formulaList);
        AceAjaxView view = new AceAjaxView();
        view.setMessage("编辑成功");
        return view;
    }
}
