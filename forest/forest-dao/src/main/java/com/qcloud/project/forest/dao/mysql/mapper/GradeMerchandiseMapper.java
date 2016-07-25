package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.GradeMerchandise;
import com.qcloud.project.forest.model.query.GradeMerchandiseQuery;

public interface GradeMerchandiseMapper {

	@Insert("insert into `forest_grade_merchandise`(`id`,`gradeId`,`unifiedMerchandiseId`)"
			+ " values(#{id},#{gradeId},#{unifiedMerchandiseId})")
	public void insert(GradeMerchandise gradeMerchandise);

	@Select("select * from `forest_grade_merchandise` where `id`=#{id}")
	public GradeMerchandise get(Long id);

	@Update("update `forest_grade_merchandise` set `gradeId`=#{gradeId},`unifiedMerchandiseId`=#{unifiedMerchandiseId} where `id`=#{id}")
	public void update(GradeMerchandise gradeMerchandise);

	@Delete("delete from `forest_grade_merchandise` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_grade_merchandise` limit #{start},#{count}")
	public List<GradeMerchandise> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_grade_merchandise`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_grade_merchandise`")
	public List<GradeMerchandise> listAll();
}