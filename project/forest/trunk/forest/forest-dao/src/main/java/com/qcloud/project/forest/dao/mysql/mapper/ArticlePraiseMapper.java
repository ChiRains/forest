package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.ArticlePraise;
import com.qcloud.project.forest.model.query.ArticlePraiseQuery;

public interface ArticlePraiseMapper {

	@Insert("insert into `forest_article_praise`(`id`,`articleId`,`userId`,`time`)"
			+ " values(#{id},#{articleId},#{userId},#{time})")
	public void insert(ArticlePraise articlePraise);

	@Select("select * from `forest_article_praise` where `id`=#{id}")
	public ArticlePraise get(Long id);

	@Update("update `forest_article_praise` set `articleId`=#{articleId},`userId`=#{userId},`time`=#{time} where `id`=#{id}")
	public void update(ArticlePraise articlePraise);

	@Delete("delete from `forest_article_praise` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_article_praise` limit #{start},#{count}")
	public List<ArticlePraise> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_article_praise`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_article_praise`")
	public List<ArticlePraise> listAll();
}