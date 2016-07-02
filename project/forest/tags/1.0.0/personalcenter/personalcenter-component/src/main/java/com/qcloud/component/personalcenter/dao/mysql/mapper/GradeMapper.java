package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.Grade;
import com.qcloud.component.personalcenter.model.query.GradeQuery;

public interface GradeMapper {

	@Insert("insert into `personalcenter_grade`(`id`,`name`,`desc`,`point`,`discount`,`image`)"
			+ " values(#{id},#{name},#{desc},#{point},#{discount},#{image})")
	public void insert(Grade grade);

	@Select("select * from `personalcenter_grade` where `id`=#{id}")
	public Grade get(Long id);

	@Update("update `personalcenter_grade` set `name`=#{name},`desc`=#{desc},`point`=#{point},`discount`=#{discount},`image`=#{image} where `id`=#{id}")
	public void update(Grade grade);

	@Delete("delete from `personalcenter_grade` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `personalcenter_grade` limit #{start},#{count}")
	public List<Grade> list4page(Map<String,Object> param);

	@Select("select count(*) from `personalcenter_grade`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `personalcenter_grade`")
	public List<Grade> listAll();
}