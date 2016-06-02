package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.Sexpress;

public interface SexpressMapper {

	@Insert("insert into `sellercenter_sexpress`(`id`,`merchandId`,`name`,`code`,`desc`,`logo`,`sort`,`firstWeight`,`firstPrice`,`continuedWeight`,`continuedPrice`,`enable`,`type`,`fixedPrice`)"
			+ " values(#{id},#{merchandId},#{name},#{code},#{desc},#{logo},#{sort},#{firstWeight},#{firstPrice},#{continuedWeight},#{continuedPrice},#{enable},#{type},#{fixedPrice})")
	public void insert(Sexpress sexpress);

	@Select("select * from `sellercenter_sexpress` where `id`=#{id}")
	public Sexpress get(Long id);

	@Update("update `sellercenter_sexpress` set `merchandId`=#{merchandId},`name`=#{name},`code`=#{code},`desc`=#{desc},`logo`=#{logo},`sort`=#{sort},`firstWeight`=#{firstWeight},`firstPrice`=#{firstPrice},`continuedWeight`=#{continuedWeight},`continuedPrice`=#{continuedPrice},`enable`=#{enable},`type`=#{type}, `fixedPrice`=#{fixedPrice} where `id`=#{id}")
	public void update(Sexpress sexpress);

	@Delete("delete from `sellercenter_sexpress` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_sexpress` limit #{start},#{count}")
	public List<Sexpress> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_sexpress`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_sexpress`")
	public List<Sexpress> listAll();
}