package com.qcloud.component.sellercenter.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.sellercenter.model.StoreDeliveryTime;
import com.qcloud.component.sellercenter.model.query.StoreDeliveryTimeQuery;

public interface StoreDeliveryTimeMapper {

	@Insert("insert into `sellercenter_store_delivery_time`(`id`,`storeId`,`pickupStartTime`,`pickupEndTime`,`pickupDesc`,`deliveryStartTime`,`deliveryEndTime`,`deliveryFrequency`,`deliveryDuration`)"
			+ " values(#{id},#{storeId},#{pickupStartTime},#{pickupEndTime},#{pickupDesc},#{deliveryStartTime},#{deliveryEndTime},#{deliveryFrequency},#{deliveryDuration})")
	public void insert(StoreDeliveryTime storeDeliveryTime);

	@Select("select * from `sellercenter_store_delivery_time` where `id`=#{id}")
	public StoreDeliveryTime get(Long id);

	@Update("update `sellercenter_store_delivery_time` set `storeId`=#{storeId},`pickupStartTime`=#{pickupStartTime},`pickupEndTime`=#{pickupEndTime},`pickupDesc`=#{pickupDesc},`deliveryStartTime`=#{deliveryStartTime},`deliveryEndTime`=#{deliveryEndTime},`deliveryFrequency`=#{deliveryFrequency},`deliveryDuration`=#{deliveryDuration} where `id`=#{id}")
	public void update(StoreDeliveryTime storeDeliveryTime);

	@Delete("delete from `sellercenter_store_delivery_time` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `sellercenter_store_delivery_time` limit #{start},#{count}")
	public List<StoreDeliveryTime> list4page(Map<String,Object> param);

	@Select("select count(*) from `sellercenter_store_delivery_time`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `sellercenter_store_delivery_time`")
	public List<StoreDeliveryTime> listAll();
}