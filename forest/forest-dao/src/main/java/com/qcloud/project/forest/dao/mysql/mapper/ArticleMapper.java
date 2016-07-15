package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.project.forest.model.Article;

public interface ArticleMapper {

    @Insert("insert into `forest_article`(`id`,`title`,`image`,`brief`,`content`,`date`,`enable`,`isOffshelves`,`classifyId`,`viewCount`,`likeCount`,`evaluationCount`)" + " values(#{id},#{title},#{image},#{brief},#{content},#{date},#{enable},#{isOffshelves},#{classifyId},#{viewCount},#{likeCount},#{evaluationCount})")
    public void insert(Article article);

    @Select("select * from `forest_article` where `id`=#{id}")
    public Article get(Long id);

    @Update("update `forest_article` set `title`=#{title},`image`=#{image},`brief`=#{brief},`content`=#{content},`date`=#{date},`enable`=#{enable},`isOffshelves`=#{isOffshelves},`classifyId`=#{classifyId},`viewCount`=#{viewCount},`likeCount`=#{likeCount},`evaluationCount`=#{evaluationCount} where `id`=#{id}")
    public void update(Article article);

    @Delete("delete from `forest_article` where `id`=#{id}")
    public void delete(Long id);

    @Select("select * from `forest_article` limit #{start},#{count}")
    public List<Article> list4page(Map<String, Object> param);

    @Select("select count(*) from `forest_article`")
    public int count4page(Map<String, Object> param);

    @Select("select * from `forest_article`")
    public List<Article> listAll();
}