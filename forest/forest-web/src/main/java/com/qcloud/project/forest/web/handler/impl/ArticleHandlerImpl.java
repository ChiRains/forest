package com.qcloud.project.forest.web.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qcloud.component.publicdata.PublicdataClient;
import com.qcloud.pirates.core.json.Json;
import com.qcloud.pirates.util.AssertUtil;
import com.qcloud.pirates.util.DateUtil;
import com.qcloud.project.forest.service.ArticleEvaluationService;
import com.qcloud.project.forest.service.ArticleService;
import com.qcloud.project.forest.web.handler.ArticleHandler;
import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.web.vo.ArticleVO;
import com.qcloud.project.forest.web.vo.admin.AdminArticleVO;

@Component
public class ArticleHandlerImpl implements ArticleHandler {
	@Autowired
	private ArticleService articleService;
	
    @Autowired
    private PublicdataClient   publicdataClient;

    @Autowired
    private ArticleEvaluationService articleEvaluationService;
    
	@Override
	public List<ArticleVO> toVOList(List<Article> list){
		List<ArticleVO> voList = new ArrayList<ArticleVO>();
		for (Article article : list) {
			
			ArticleVO vo = new ArticleVO();
			vo.setBrief(article.getBrief());
			vo.setClassifyId(article.getClassifyId());
			vo.setDate(DateUtil.date2String(article.getDate()));
			vo.setDetail(article.getDetail());
			vo.setEnable(article.getEnable());
			vo.setId(article.getId());
			vo.setKeywords(article.getKeywords());
			vo.setLabel(article.getLabel());
			vo.setLikeCount(article.getLikeCount());
			vo.setName(article.getName());
			vo.setSort(article.getSort());
			vo.setViewCount(article.getViewCount());
			AssertUtil.assertNotNull(vo.getId(),"资讯信息id不能为空");
			vo.setComment(articleEvaluationService.getCommentCount(vo.getId()));
			
			String[] icon = article.getIcon().split(",");
			List<String> sList = new ArrayList<String>();
			for(String i : icon){
				sList.add(i);
			}
			if(sList!=null)  vo.setIconList(sList);
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public ArticleVO toVO(Article article){
		
		ArticleVO vo = new ArticleVO();
		vo.setBrief(article.getBrief());
		vo.setClassifyId(article.getClassifyId());
		vo.setDate(DateUtil.date2String(article.getDate()));
		vo.setDetail(article.getDetail());
		vo.setEnable(article.getEnable());
		vo.setId(article.getId());
		vo.setKeywords(article.getKeywords());
		vo.setLabel(article.getLabel());
		vo.setLikeCount(article.getLikeCount());
		vo.setName(article.getName());
		vo.setSort(article.getSort());
		vo.setViewCount(article.getViewCount());
       Long	nextId = articleService.getBySort(article.getClassifyId(),article.getSort(),1);//后一条id  1
       if(nextId!=null)
 		vo.setNextId(nextId);
      Long pId = articleService.getBySort(article.getClassifyId(),article.getSort(),2);//前一条id  2
      if(pId!=null)
 	  vo.setPreviousId(pId);
		return vo;
		
	}

	@Override
	public List<AdminArticleVO> toVOList4Admin(List<Article> list){
		List<AdminArticleVO> voList = new ArrayList<AdminArticleVO>();
		for (Article adminArticle : list) {
			 publicdataClient.getClassify(adminArticle.getClassifyId());
			AdminArticleVO vo = toVO4Admin(adminArticle);
			AssertUtil.assertNotNull(vo.getClassifyId(),"分类ID不能为空");
			vo.setClassifyName(publicdataClient.getClassify(vo.getClassifyId()).getName());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	public AdminArticleVO toVO4Admin(Article article){
		String json = Json.toJson(article);
		return Json.toObject(json, AdminArticleVO.class, true);
	}
}
