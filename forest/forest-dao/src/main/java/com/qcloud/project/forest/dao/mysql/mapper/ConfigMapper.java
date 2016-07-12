package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.Config;
import com.qcloud.project.forest.model.query.ConfigQuery;

public interface ConfigMapper {

	@Insert("insert into `forest_config`(`id`,`code`,`config`,`remark`,`type`)"
			+ " values(#{id},#{code},#{config},#{remark},#{type})")
	public void insert(Config config);

	@Select("select * from `forest_config` where `id`=#{id}")
	public Config get(Long id);

	@Update("update `forest_config` set `code`=#{code},`config`=#{config},`remark`=#{remark},`type`=#{type} where `id`=#{id}")
	public void update(Config config);

	@Delete("delete from `forest_config` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_config` limit #{start},#{count}")
	public List<Config> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_config`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_config`")
	public List<Config> listAll();
}