package com.qcloud.component.commoditycenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.commoditycenter.model.Enumeration;
import com.qcloud.component.commoditycenter.model.query.EnumerationQuery;

public interface EnumerationMapper {

	@Insert("insert into `commoditycenter_enumeration`(`id`,`name`,`value`)"
			+ " values(#{id},#{name},#{value})")
	public void insert(Enumeration enumeration);

	@Select("select * from `commoditycenter_enumeration` where `id`=#{id}")
	public Enumeration get(Long id);

	@Update("update `commoditycenter_enumeration` set `name`=#{name},`value`=#{value} where `id`=#{id}")
	public void update(Enumeration enumeration);

	@Delete("delete from `commoditycenter_enumeration` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `commoditycenter_enumeration` limit #{start},#{count}")
	public List<Enumeration> list4page(Map<String,Object> param);

	@Select("select count(*) from `commoditycenter_enumeration`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `commoditycenter_enumeration`")
	public List<Enumeration> listAll();
}