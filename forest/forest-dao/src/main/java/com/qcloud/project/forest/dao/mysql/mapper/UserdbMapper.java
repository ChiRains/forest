package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.Userdb;
import com.qcloud.project.forest.model.query.UserdbQuery;

public interface UserdbMapper {

	@Insert("insert into `forest_userdb`(`id`,`name`,`nickname`,`password`)"
			+ " values(#{id},#{name},#{nickname},#{password})")
	public void insert(Userdb userdb);

	@Select("select * from `forest_userdb` where `id`=#{id}")
	public Userdb get(Long id);

	@Update("update `forest_userdb` set `name`=#{name},`nickname`=#{nickname},`password`=#{password} where `id`=#{id}")
	public void update(Userdb userdb);

	@Delete("delete from `forest_userdb` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_userdb` limit #{start},#{count}")
	public List<Userdb> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_userdb`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_userdb`")
	public List<Userdb> listAll();
}