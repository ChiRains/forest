package com.qcloud.project.forest.web.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleHandler;

@Controller
@RequestMapping(value = AppArticleController.DIR)
public class AppArticleController {

    public static final String DIR = "/app/article";

    @Autowired
    private ArticleService     articleService;

    @Autowired
    private ArticleHandler     articleHandler;
    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView list(Long classifyId){
    // AssertUtil.assertNotNull(classifyId, "ID不能为空");
    // Page<Article> page = articleService.page(classifyId);
    // List<ArticleVO> list = articleHandler.toVOList(page.getData());
    // FrontAjaxView view = new FrontAjaxView();
    // view.addObject("result", list);
    // return view;
    //
    // }
    // @RequestMapping
    // @NoReferer
    // public FrontAjaxView get(Long id){
    // AssertUtil.assertNotNull(id, "ID不能为空");
    // Article article = articleService.get(id);
    // ArticleVO result = articleHandler.toVO(article);
    // FrontAjaxView view = new FrontAjaxView();
    // view.addObject("result", result);
    // return view;
    // }
}
