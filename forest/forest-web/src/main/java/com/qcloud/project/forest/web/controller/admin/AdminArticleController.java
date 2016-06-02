package com.qcloud.project.forest.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import com.qcloud.component.admin.exception.AdminException;
import com.qcloud.component.admin.model.key.TypeEnum.AdminEnableType;
import com.qcloud.component.filesdk.FileSDKClient;
import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.component.publicdata.model.Classify;
import com.qcloud.pirates.data.Page;
import com.qcloud.pirates.mvc.AceAjaxView;
import com.qcloud.pirates.mvc.AcePagingView;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.NumberUtil;
import com.qcloud.pirates.util.RequestUtil;
import com.qcloud.pirates.web.security.annotation.NoReferer;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleHandler;
import com.qcloud.project.forest.model.key.TypeEnum;
import com.qcloud.project.forest.model.query.ArticleQuery;
import com.qcloud.project.forest.web.vo.admin.AdminArticleVO;
		
@Controller
@RequestMapping(value = "/" + AdminArticleController.DIR)
public class AdminArticleController {
	
	public static final String DIR = "admin/article";
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleHandler articleHandler;
    @Autowired
    private FileSDKClient   fileSDKClient;
    @Autowired
    private PublicdataClient   publicdataClient;

	@RequestMapping
	@NoReferer
	public ModelAndView list(Integer pageNum, ArticleQuery query) {
		final int PAGE_SIZE = 10;
		pageNum = RequestUtil.getPageid(pageNum);
		int start = NumberUtil.getPageStart(pageNum, PAGE_SIZE);
		
		Page<Article> page = articleService.page(query, start, PAGE_SIZE);
		List<AdminArticleVO> list = articleHandler.toVOList4Admin(page.getData());
				
		AcePagingView pagingView = new AcePagingView("/admin/forest-Article-list", DIR
				+ "/list", pageNum, PAGE_SIZE, page.getCount());
		pagingView.addObject("result", list);
	  
		return pagingView;
	}
	
	@RequestMapping
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView("/admin/forest-Article-add");
		List<Classify> list = publicdataClient.listClassify((long)TypeEnum.ClassifyType.ARTICLE.getKey());
	        model.addObject("classifyList",list);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView add(Article article) {
		article.setIcon(fileSDKClient.uidToUrl(article.getIcon()));
		articleService.add(article);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("添加成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public ModelAndView toEdit(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		Article article=articleService.get(id);
		AdminArticleVO adminArticleVO=articleHandler.toVO4Admin(article);
		adminArticleVO.setIcon(fileSDKClient.urlToUid(adminArticleVO.getIcon()));
		ModelAndView model = new ModelAndView("/admin/forest-Article-edit");
		List<Classify> list = publicdataClient.listClassify((long)TypeEnum.ClassifyType.ARTICLE.getKey());
        model.addObject("classifyList",list);
		model.addObject("article", adminArticleVO);
		return model;
	}
	
	@RequestMapping
	public AceAjaxView edit(Article article) {
		article.setIcon(fileSDKClient.uidToUrl(article.getIcon()));
		articleService.update(article);		
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("编辑成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}
	
	@RequestMapping
	public AceAjaxView delete(Long id) {
		AssertUtil.assertNotNull(id, "ID不能为空");
		articleService.delete(id);
		AceAjaxView aceAjaxView = new AceAjaxView();
		aceAjaxView.setMessage("删除成功");
		aceAjaxView.setUrl(DIR + "/list");
		return aceAjaxView;
	}	
	
	 @RequestMapping
	    public AceAjaxView enable(Long id, Integer value) {

	        AssertUtil.assertNotNull(id, "ID不能为空");
	        AssertUtil.assertNotNull(value, "value不能为空");
	        Article article = articleService.get(id);
	        AssertUtil.assertNotNull(article, "不存在.");
	        String message = null;
	        if (AdminEnableType.DISABLE.getKey() == value) {
	        	article.setEnable(AdminEnableType.DISABLE.getKey());
	            message = "禁用成功";
	        } else if (AdminEnableType.ENABLE.getKey() == value) {
	        	article.setEnable(AdminEnableType.ENABLE.getKey());
	            message = "启用成功";
	        } else {
	            throw new AdminException("启、禁用状态不正确.");
	        }
	        articleService.update(article);
	        AceAjaxView aceAjaxView = new AceAjaxView();
	        aceAjaxView.setMessage(message);
	        aceAjaxView.setUrl(DIR + "/list");
	        return aceAjaxView;
	    }
	    
	 @RequestMapping
     public ModelAndView classifyList(){
			List<Classify> list = publicdataClient.listClassify((long)TypeEnum.ClassifyType.ARTICLE.getKey());
				AcePagingView pagingView = new AcePagingView("/admin/forest-Article-Classify-list", DIR
						+ "/list", 1, list.size(), list.size());		
				pagingView.addObject("result", list);
				return pagingView;
		 }
	 
	    	@RequestMapping
	    	public ModelAndView toAddClassify() {
	    		ModelAndView model = new ModelAndView("/admin/forest-Article-Classify-add");
	    		return model;
	    	}
	   
		 @RequestMapping
	        public ModelAndView addClassify(Classify classify) {
			    classify.setParentId(-1);
	        	classify.setType(TypeEnum.ClassifyType.ARTICLE.getKey());
	        	publicdataClient.addClassify(classify);
	        	AceAjaxView aceAjaxView = new AceAjaxView();
	    		aceAjaxView.setMessage("添加成功");
	    		aceAjaxView.setUrl(DIR + "/classifyList");
	    		return aceAjaxView;
		 }
		  @RequestMapping
		    public ModelAndView toEditClassify(Long id) {
		        Classify result = publicdataClient.getClassify(id);
		        AssertUtil.assertNotNull(id, "ID不能为空");
		        ModelAndView model = new ModelAndView("/admin/forest-Article-Classify-edit");
		        model.addObject("result", result);
		        return model;
		    }
		  @RequestMapping
		    public AceAjaxView editClassify(Classify classify) {
			  classify.setType(TypeEnum.ClassifyType.ARTICLE.getKey());
			  classify.setParentId(-1);
			  publicdataClient.update(classify);
		        AceAjaxView aceAjaxView = new AceAjaxView();
		        aceAjaxView.setMessage("编辑成功");
		        aceAjaxView.setUrl(DIR + "/classifyList");
		        return aceAjaxView;
		    }
		  @RequestMapping
		    public AceAjaxView deleteClassify(Long id) {
		        AssertUtil.assertNotNull(id, "ID不能为空");
		        publicdataClient.delete(id);
		        AceAjaxView aceAjaxView = new AceAjaxView();
		        aceAjaxView.setMessage("删除成功");
		        aceAjaxView.setUrl(DIR + "/classifyList");
		        return aceAjaxView;
		    }
}
