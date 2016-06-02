package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.MyCollectionObjStatistics;
import com.qcloud.component.my.model.query.MyCollectionObjStatisticsQuery;

public interface MyCollectionObjStatisticsMapper {

	@Insert("insert into `my_my_collection_obj_statistics`(`id`,`objId`,`type`,`number`)"
			+ " values(#{id},#{objId},#{type},#{number})")
	public void insert(MyCollectionObjStatistics myCollectionObjStatistics);

	@Select("select * from `my_my_collection_obj_statistics` where `id`=#{id}")
	public MyCollectionObjStatistics get(Long id);

	@Update("update `my_my_collection_obj_statistics` set `objId`=#{objId},`type`=#{type},`number`=#{number} where `id`=#{id}")
	public void update(MyCollectionObjStatistics myCollectionObjStatistics);

	@Delete("delete from `my_my_collection_obj_statistics` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_my_collection_obj_statistics` limit #{start},#{count}")
	public List<MyCollectionObjStatistics> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_my_collection_obj_statistics`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_my_collection_obj_statistics`")
	public List<MyCollectionObjStatistics> listAll();
}