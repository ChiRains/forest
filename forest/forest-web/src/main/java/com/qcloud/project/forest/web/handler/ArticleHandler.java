package com.qcloud.project.forest.web.handler;

import java.util.List;

import com.qcloud.project.forest.model.Article;
import com.qcloud.project.forest.web.vo.ArticleVO;
import com.qcloud.project.forest.web.vo.admin.AdminArticleVO;

public interface ArticleHandler {

	List<ArticleVO> toVOList(List<Article> list);

	ArticleVO toVO(Article article);

	List<AdminArticleVO> toVOList4Admin(List<Article> list);

	AdminArticleVO toVO4Admin(Article article);
}
