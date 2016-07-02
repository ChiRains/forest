package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.qcloud.component.my.model.MyCollection;

public interface MyCollectionMapper {

	@Insert("insert into `${table_name}`(`id`,`userId`,`objId`,`classifyId`,`time`,`type`)"
			+ " values(#{id},#{userId},#{objId},#{classifyId},#{time},#{type})")
	public void insert(MyCollection myCollection);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MyCollection get(Long id);

	@Update("update `${table_name}` set `userId`=#{userId},`objId`=#{objId},`classifyId`=#{classifyId},`time`=#{time},`type`=#{type} where `id`=#{id}")
	public void update(MyCollection myCollection);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MyCollection> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MyCollection> listAll();
}