package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.Activity;
import com.qcloud.project.forest.model.query.ActivityQuery;

public interface ActivityMapper {

	@Insert("insert into `forest_activity`(`id`,`name`,`departmentId`,`time`)"
			+ " values(#{id},#{name},#{departmentId},#{time})")
	public void insert(Activity activity);

	@Select("select * from `forest_activity` where `id`=#{id}")
	public Activity get(Long id);

	@Update("update `forest_activity` set `name`=#{name},`departmentId`=#{departmentId},`time`=#{time} where `id`=#{id}")
	public void update(Activity activity);

	@Delete("delete from `forest_activity` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_activity` limit #{start},#{count}")
	public List<Activity> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_activity`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_activity`")
	public List<Activity> listAll();
}