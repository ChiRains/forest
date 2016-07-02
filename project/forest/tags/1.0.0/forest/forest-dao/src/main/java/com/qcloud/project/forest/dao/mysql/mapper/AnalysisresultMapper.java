package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.Analysisresult;
import com.qcloud.project.forest.model.query.AnalysisresultQuery;

public interface AnalysisresultMapper {

	@Insert("insert into `forest_analysisresult`(`id`,`name`,`description`,`type`,`previousData`,`afterData`)"
			+ " values(#{id},#{name},#{description},#{type},#{previousData},#{afterData})")
	public void insert(Analysisresult analysisresult);

	@Select("select * from `forest_analysisresult` where `id`=#{id}")
	public Analysisresult get(Long id);

	@Update("update `forest_analysisresult` set `name`=#{name},`description`=#{description},`type`=#{type},`previousData`=#{previousData},`afterData`=#{afterData} where `id`=#{id}")
	public void update(Analysisresult analysisresult);

	@Delete("delete from `forest_analysisresult` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_analysisresult` limit #{start},#{count}")
	public List<Analysisresult> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_analysisresult`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_analysisresult`")
	public List<Analysisresult> listAll();
}