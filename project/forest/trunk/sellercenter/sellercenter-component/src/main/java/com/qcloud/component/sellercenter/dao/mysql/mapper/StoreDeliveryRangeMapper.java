package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.StoreDeliveryRange;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryRangeQuery;

public interface StoreDeliveryRangeMapper {

	@Insert("insert into `sellercenter_store_delivery_range`(`id`,`storeId`,`radius`)"
			+ " values(#{id},#{storeId},#{radius})")
	public void insert(StoreDeliveryRange storeDeliveryRange);

	@Select("select * from `sellercenter_store_delivery_range` where `id`=#{id}")
	public StoreDeliveryRange get(Long id);

	@Update("update `sellercenter_store_delivery_range` set `storeId`=#{storeId},`radius`=#{radius} where `id`=#{id}")
	public void update(StoreDeliveryRange storeDeliveryRange);

	@Delete("delete from `sellercenter_store_delivery_range` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_store_delivery_range` limit #{start},#{count}")
	public List<StoreDeliveryRange> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_store_delivery_range`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_store_delivery_range`")
	public List<StoreDeliveryRange> listAll();
}