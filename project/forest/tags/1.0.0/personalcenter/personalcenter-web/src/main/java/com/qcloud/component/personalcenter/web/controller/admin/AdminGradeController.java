package com.qcloud.component.personalcenter.web.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.query.GradeQuery;
import com.qcloud.component.personalcenter.service.GradeService;
import com.qcloud.component.personalcenter.web.handler.GradeHandler;
import com.qcloud.component.personalcenter.web.vo.admin.AdminGradeVO;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;

@Controller
@RequestMapping(value = "/" + AdminGradeController.DIR)
public class AdminGradeController {

    public static final String DIR = "admin/grade";

    @Autowired
    private GradeService       gradeService;

    @Autowired
    private GradeHandler       gradeHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(GradeQuery query) {

        List<Grade> gradeList = gradeService.listAll();
        List<AdminGradeVO> list = gradeHandler.toVOList4Admin(gradeList);
        ModelAndView pagingView = new ModelAndView("/admin/personalcenter-Grade-list");
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd() {

        ModelAndView model = new ModelAndView("/admin/personalcenter-Grade-add");
        return model;
    }

    @RequestMapping
    public AceAjaxView add(Grade grade) {

        gradeService.add(grade);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("添加成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Grade grade = gradeService.get(id);
        AdminGradeVO adminGradeVO = gradeHandler.toVO4Admin(grade);
        ModelAndView model = new ModelAndView("/admin/personalcenter-Grade-edit");
        model.addObject("grade", adminGradeVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Grade grade) {

        gradeService.update(grade);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("编辑成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        gradeService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        aceAjaxView.setUrl(DIR + "/list");
        return aceAjaxView;
    }
}
