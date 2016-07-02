package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.DeliveryMode;
import com.qcloud.component.my.model.query.DeliveryModeQuery;

public interface DeliveryModeMapper {

	@Insert("insert into `my_delivery_mode`(`id`,`userId`,`type`,`desc`,`storeId`,`time`)"
			+ " values(#{id},#{userId},#{type},#{desc},#{storeId},#{time})")
	public void insert(DeliveryMode deliveryMode);

	@Select("select * from `my_delivery_mode` where `id`=#{id}")
	public DeliveryMode get(Long id);

	@Update("update `my_delivery_mode` set `userId`=#{userId},`type`=#{type},`desc`=#{desc},`storeId`=#{storeId},`time`=#{time} where `id`=#{id}")
	public void update(DeliveryMode deliveryMode);

	@Delete("delete from `my_delivery_mode` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_delivery_mode` limit #{start},#{count}")
	public List<DeliveryMode> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_delivery_mode`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_delivery_mode`")
	public List<DeliveryMode> listAll();
}