package com.qcloud.component.goods.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.goods.model.Enumeration;
import com.qcloud.component.goods.model.query.EnumerationQuery;

public interface EnumerationMapper {

	@Insert("insert into `goods_enumeration`(`id`,`name`,`value`)"
			+ " values(#{id},#{name},#{value})")
	public void insert(Enumeration enumeration);

	@Select("select * from `goods_enumeration` where `id`=#{id}")
	public Enumeration get(Long id);

	@Update("update `goods_enumeration` set `name`=#{name},`value`=#{value} where `id`=#{id}")
	public void update(Enumeration enumeration);

	@Delete("delete from `goods_enumeration` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `goods_enumeration` limit #{start},#{count}")
	public List<Enumeration> list4page(Map<String,Object> param);

	@Select("select count(*) from `goods_enumeration`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `goods_enumeration`")
	public List<Enumeration> listAll();
}