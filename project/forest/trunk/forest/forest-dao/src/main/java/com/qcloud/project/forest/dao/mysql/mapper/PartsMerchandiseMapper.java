package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.PartsMerchandise;
import com.qcloud.project.forest.model.query.PartsMerchandiseQuery;

public interface PartsMerchandiseMapper {

	@Insert("insert into `forest_parts_merchandise`(`id`,`merchandiseId`,`classifyId`)"
			+ " values(#{id},#{merchandiseId},#{classifyId})")
	public void insert(PartsMerchandise partsMerchandise);

	@Select("select * from `forest_parts_merchandise` where `id`=#{id}")
	public PartsMerchandise get(Long id);

	@Update("update `forest_parts_merchandise` set `merchandiseId`=#{merchandiseId},`classifyId`=#{classifyId} where `id`=#{id}")
	public void update(PartsMerchandise partsMerchandise);

	@Delete("delete from `forest_parts_merchandise` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_parts_merchandise` limit #{start},#{count}")
	public List<PartsMerchandise> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_parts_merchandise`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_parts_merchandise`")
	public List<PartsMerchandise> listAll();
}