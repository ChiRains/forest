package com.qcloud.project.forest.web.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = AppArticleEvaluationController.DIR)
public class AppArticleEvaluationController {

    public static final String DIR = "/app/articleEvaluation";
    //
    // @Autowired
    // private ArticleEvaluationService articleEvaluationService;
    // @Autowired
    // private ArticleEvaluationHandler articleEvaluationHandler;
    //
    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView list(ArticleEvaluationQuery query){
    // AssertUtil.assertNotNull(query.getArticleId(), "资讯ID不能为空");
    // Page<ArticleEvaluation> page = articleEvaluationService.page(query,0 , Integer.MAX_VALUE);
    // List<ArticleEvaluationVO> list = articleEvaluationHandler.toVOList(page.getData());
    // FrontAjaxView view = new FrontAjaxView();
    // view.addObject("result", list);
    // return view;
    // }
    // @RequestMapping
    //
    // public FrontAjaxView add(ArticleEvaluation articleEvaluation){
    // articleEvaluation.setState(0);//默认状态为未处理
    // articleEvaluationService.add(articleEvaluation);
    // FrontAjaxView view = new FrontAjaxView();
    // view.setMessage("添加成功");
    // view.addObject("id",articleEvaluation.getId());
    // return view;
    //
    // }
    //
}
