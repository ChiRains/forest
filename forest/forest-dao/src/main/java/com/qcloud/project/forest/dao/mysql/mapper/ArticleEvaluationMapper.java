package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.ArticleEvaluation;
import com.qcloud.project.forest.model.query.ArticleEvaluationQuery;

public interface ArticleEvaluationMapper {

	@Insert("insert into `forest_article_evaluation`(`id`,`articleId`,`content`,`state`,`userId`,`time`)"
			+ " values(#{id},#{articleId},#{content},#{state},#{userId},#{time})")
	public void insert(ArticleEvaluation articleEvaluation);

	@Select("select * from `forest_article_evaluation` where `id`=#{id}")
	public ArticleEvaluation get(Long id);

	@Update("update `forest_article_evaluation` set `articleId`=#{articleId},`content`=#{content},`state`=#{state},`userId`=#{userId},`time`=#{time} where `id`=#{id}")
	public void update(ArticleEvaluation articleEvaluation);

	@Delete("delete from `forest_article_evaluation` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_article_evaluation` limit #{start},#{count}")
	public List<ArticleEvaluation> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_article_evaluation`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_article_evaluation`")
	public List<ArticleEvaluation> listAll();
}