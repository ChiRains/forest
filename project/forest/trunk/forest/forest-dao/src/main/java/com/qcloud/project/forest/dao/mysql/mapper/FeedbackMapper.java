package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.Feedback;
import com.qcloud.project.forest.model.query.FeedbackQuery;

public interface FeedbackMapper {

	@Insert("insert into `forest_feedback`(`id`,`userId`,`content`,`classify`,`type`,`state`,`date`)"
			+ " values(#{id},#{userId},#{content},#{classify},#{type},#{state},#{date})")
	public void insert(Feedback feedback);

	@Select("select * from `forest_feedback` where `id`=#{id}")
	public Feedback get(Long id);

	@Update("update `forest_feedback` set `userId`=#{userId},`content`=#{content},`classify`=#{classify},`type`=#{type},`state`=#{state},`date`=#{date} where `id`=#{id}")
	public void update(Feedback feedback);

	@Delete("delete from `forest_feedback` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_feedback` limit #{start},#{count}")
	public List<Feedback> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_feedback`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_feedback`")
	public List<Feedback> listAll();
}