package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.RangeGrade;
import com.qcloud.project.forest.model.query.RangeGradeQuery;

public interface RangeGradeMapper {

	@Insert("insert into `forest_range_grade`(`id`,`rangeId`,`gradeId`)"
			+ " values(#{id},#{rangeId},#{gradeId})")
	public void insert(RangeGrade rangeGrade);

	@Select("select * from `forest_range_grade` where `id`=#{id}")
	public RangeGrade get(Long id);

	@Update("update `forest_range_grade` set `rangeId`=#{rangeId},`gradeId`=#{gradeId} where `id`=#{id}")
	public void update(RangeGrade rangeGrade);

	@Delete("delete from `forest_range_grade` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_range_grade` limit #{start},#{count}")
	public List<RangeGrade> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_range_grade`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_range_grade`")
	public List<RangeGrade> listAll();
}