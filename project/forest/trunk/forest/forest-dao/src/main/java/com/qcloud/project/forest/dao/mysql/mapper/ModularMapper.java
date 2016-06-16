package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.Modular;
import com.qcloud.project.forest.model.query.ModularQuery;

public interface ModularMapper {

	@Insert("insert into `forest_modular`(`id`,`name`,`code`,`enable`)"
			+ " values(#{id},#{name},#{code},#{enable})")
	public void insert(Modular modular);

	@Select("select * from `forest_modular` where `id`=#{id}")
	public Modular get(Long id);

	@Update("update `forest_modular` set `name`=#{name},`code`=#{code},`enable`=#{enable} where `id`=#{id}")
	public void update(Modular modular);

	@Delete("delete from `forest_modular` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_modular` limit #{start},#{count}")
	public List<Modular> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_modular`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_modular`")
	public List<Modular> listAll();
}