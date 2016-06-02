package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.service.AnalysisresultService;
import com.qcloud.project.forest.web.handler.AnalysisresultHandler;
import com.qcloud.project.forest.model.key.TypeEnum.AnalysisresultType;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;
import com.qcloud.project.forest.web.vo.admin.AdminAnalysisresultVO;

@Controller
@RequestMapping(value = "/" + AdminAnalysisresultController.DIR)
public class AdminAnalysisresultController {

    public static final String    DIR = "admin/analysisresult";

    @Autowired
    private AnalysisresultService analysisresultService;

    @Autowired
    private AnalysisresultHandler analysisresultHandler;

    @RequestMapping
    @NoReferer
    public ModelAndView list(Integer pageNum, AnalysisresultQuery query) {// BIM计算列表

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        query.setType(AnalysisresultType.BIM.getKey());
        Page<Analysisresult> page = analysisresultService.page(query, start, PAGE_SIZE);
        List<AdminAnalysisresultVO> list = analysisresultHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/forest-Analysisresult-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    @NoReferer
    public ModelAndView listPB(Integer pageNum, AnalysisresultQuery query) {// 血压分析列表

        final int PAGE_SIZE = 10;
        pageNum = RequestUtil.getPageid(pageNum);
        int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
        query.setType(AnalysisresultType.BLOOB.getKey());
        Page<Analysisresult> page = analysisresultService.page(query, start, PAGE_SIZE);
        List<AdminAnalysisresultVO> list = analysisresultHandler.toVOList4Admin(page.getData());
        AcePagingView pagingView = new AcePagingView("/admin/forest-Analysisresult-BloodPressure-list", DIR + "/list", pageNum, PAGE_SIZE, page.getCount());
        pagingView.addObject("result", list);
        return pagingView;
    }

    @RequestMapping
    public ModelAndView toAdd(int type) {

        ModelAndView model = new ModelAndView("/admin/forest-Analysisresult-add");
        model.addObject("type", type);
        return model;
    }

    @RequestMapping
    public AceAjaxView add(Analysisresult analysisresult) {

         String message = null;
        if (analysisresult.getAfterData() < analysisresult.getPreviousData()) {
            analysisresultService.add(analysisresult);
            message ="添加成功";
        }else message ="开始范围不能大于结束范围";
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage(message);
        if (analysisresult.getType() == AnalysisresultType.BIM.getKey()) {
            aceAjaxView.setUrl(DIR + "/list");
        }else if (analysisresult.getType() == AnalysisresultType.BLOOB.getKey()){
            aceAjaxView.setUrl(DIR + "/listPB");
        }
        return aceAjaxView;
    }

    @RequestMapping
    public ModelAndView toEdit(Long id) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        Analysisresult analysisresult = analysisresultService.get(id);
        AdminAnalysisresultVO adminAnalysisresultVO = analysisresultHandler.toVO4Admin(analysisresult);
        ModelAndView model = new ModelAndView("/admin/forest-Analysisresult-edit");
        model.addObject("analysisresult", adminAnalysisresultVO);
        return model;
    }

    @RequestMapping
    public AceAjaxView edit(Analysisresult analysisresult) {
        
          String message = null;
         if(analysisresult.getAfterData()<analysisresult.getPreviousData()){
               analysisresultService.update(analysisresult);
               message ="编辑成功";
         }else message ="开始范围不能大于等于结束范围";
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage(message);
        if (analysisresult.getType() == AnalysisresultType.BIM.getKey()) {
            aceAjaxView.setUrl(DIR + "/list");
        } else if (analysisresult.getType() == AnalysisresultType.BLOOB.getKey()) {
            aceAjaxView.setUrl(DIR + "/listPB");
        }
        return aceAjaxView;
    }

    @RequestMapping
    public AceAjaxView delete(Long id, int type) {

        AssertUtil.assertNotNull(id, "ID不能为空");
        analysisresultService.delete(id);
        AceAjaxView aceAjaxView = new AceAjaxView();
        aceAjaxView.setMessage("删除成功");
        if (type == AnalysisresultType.BIM.getKey()) {
            aceAjaxView.setUrl(DIR + "/list");
        } else if (type == AnalysisresultType.BLOOB.getKey()) {
            aceAjaxView.setUrl(DIR + "/listPB");
        }
        return aceAjaxView;
    }
}
