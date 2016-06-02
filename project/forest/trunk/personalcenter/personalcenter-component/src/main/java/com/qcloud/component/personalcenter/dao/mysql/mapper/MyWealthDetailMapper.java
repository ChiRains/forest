package com.qcloud.component.personalcenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.personalcenter.model.MyWealthDetail;
import com.qcloud.component.personalcenter.model.query.MyWealthDetailQuery;

public interface MyWealthDetailMapper {

	@Insert("insert into `${table_name}`(`id`,`wealthId`,`userId`,`point`,`remainder`,`time`,`desc`,`type`,`image`,`noteKey`)"
			+ " values(#{id},#{wealthId},#{userId},#{point},#{remainder},#{time},#{desc},#{type},#{image},#{noteKey})")
	public void insert(MyWealthDetail myWealthDetail);

	@Select("select * from `${table_name}` where `id`=#{id}")
	public MyWealthDetail get(Long id);

	@Update("update `${table_name}` set `wealthId`=#{wealthId},`userId`=#{userId},`point`=#{point},`remainder`=#{remainder},`time`=#{time},`desc`=#{desc},`type`=#{type},`image`=#{image},`noteKey`=#{noteKey} where `id`=#{id}")
	public void update(MyWealthDetail myWealthDetail);

	@Delete("delete from `${table_name}` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `${table_name}` limit #{start},#{count}")
	public List<MyWealthDetail> list4page(Map<String,Object> param);

	@Select("select count(*) from `${table_name}`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `${table_name}`")
	public List<MyWealthDetail> listAll();
}