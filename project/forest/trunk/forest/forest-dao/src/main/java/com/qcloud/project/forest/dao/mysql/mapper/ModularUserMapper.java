package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.ModularUser;
import com.qcloud.project.forest.model.query.ModularUserQuery;

public interface ModularUserMapper {

	@Insert("insert into `forest_modular_user`(`id`,`modularId`,`userId`)"
			+ " values(#{id},#{modularId},#{userId})")
	public void insert(ModularUser modularUser);

	@Select("select * from `forest_modular_user` where `id`=#{id}")
	public ModularUser get(Long id);

	@Update("update `forest_modular_user` set `modularId`=#{modularId},`userId`=#{userId} where `id`=#{id}")
	public void update(ModularUser modularUser);

	@Delete("delete from `forest_modular_user` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_modular_user` limit #{start},#{count}")
	public List<ModularUser> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_modular_user`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_modular_user`")
	public List<ModularUser> listAll();
}