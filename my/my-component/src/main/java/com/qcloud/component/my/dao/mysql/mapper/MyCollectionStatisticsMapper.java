package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.MyCollectionStatistics;
import com.qcloud.component.my.model.query.MyCollectionStatisticsQuery;

public interface MyCollectionStatisticsMapper {

	@Insert("insert into `my_my_collection_statistics`(`id`,`userId`,`classifyId`,`type`,`number`)"
			+ " values(#{id},#{userId},#{classifyId},#{type},#{number})")
	public void insert(MyCollectionStatistics myCollectionStatistics);

	@Select("select * from `my_my_collection_statistics` where `id`=#{id}")
	public MyCollectionStatistics get(Long id);

	@Update("update `my_my_collection_statistics` set `userId`=#{userId},`classifyId`=#{classifyId},`type`=#{type},`number`=#{number} where `id`=#{id}")
	public void update(MyCollectionStatistics myCollectionStatistics);

	@Delete("delete from `my_my_collection_statistics` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_my_collection_statistics` limit #{start},#{count}")
	public List<MyCollectionStatistics> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_my_collection_statistics`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_my_collection_statistics`")
	public List<MyCollectionStatistics> listAll();
}