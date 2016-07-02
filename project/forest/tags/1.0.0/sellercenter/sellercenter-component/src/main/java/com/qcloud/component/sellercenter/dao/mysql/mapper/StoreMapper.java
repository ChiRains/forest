package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.Store;
import com.qcloud.component.sellercenter.model.query.StoreQuery;

public interface StoreMapper {

	@Insert("insert into `sellercenter_store`(`id`,`parentId`,`bsid`,`merchantId`,`name`,`province`,`city`,`district`,`address`,`longitude`,`latitude`,`logo`,`phone`,`mobile`,`enable`)"
			+ " values(#{id},#{parentId},#{bsid},#{merchantId},#{name},#{province},#{city},#{district},#{address},#{longitude},#{latitude},#{logo},#{phone},#{mobile},#{enable})")
	public void insert(Store store);

	@Select("select * from `sellercenter_store` where `id`=#{id}")
	public Store get(Long id);

	@Update("update `sellercenter_store` set `parentId`=#{parentId},`bsid`=#{bsid},`merchantId`=#{merchantId},`name`=#{name},`province`=#{province},`city`=#{city},`district`=#{district},`address`=#{address},`longitude`=#{longitude},`latitude`=#{latitude},`logo`=#{logo},`phone`=#{phone},`mobile`=#{mobile},`enable`=#{enable} where `id`=#{id}")
	public void update(Store store);

	@Delete("delete from `sellercenter_store` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_store` limit #{start},#{count}")
	public List<Store> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_store`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_store`")
	public List<Store> listAll();
}