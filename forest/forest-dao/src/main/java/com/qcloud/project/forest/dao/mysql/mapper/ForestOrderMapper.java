package com.qcloud.project.forest.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.project.forest.model.ForestOrder;
import com.qcloud.project.forest.model.query.ForestOrderQuery;

public interface ForestOrderMapper {

	@Insert("insert into `forest_forest_order`(`id`,`orderId`,`orderDate`,`orderNumber`,`merchantId`,`storeId`,`userId`,`giftCouponId`,`state`,`deliveryDate`,`deliveryMode`)"
			+ " values(#{id},#{orderId},#{orderDate},#{orderNumber},#{merchantId},#{storeId},#{userId},#{giftCouponId},#{state},#{deliveryDate},#{deliveryMode})")
	public void insert(ForestOrder forestOrder);

	@Select("select * from `forest_forest_order` where `id`=#{id}")
	public ForestOrder get(Long id);

	@Update("update `forest_forest_order` set `orderId`=#{orderId},`orderDate`=#{orderDate},`orderNumber`=#{orderNumber},`merchantId`=#{merchantId},`storeId`=#{storeId},`userId`=#{userId},`giftCouponId`=#{giftCouponId},`state`=#{state},`deliveryDate`=#{deliveryDate},`deliveryMode`=#{deliveryMode} where `id`=#{id}")
	public void update(ForestOrder forestOrder);

	@Delete("delete from `forest_forest_order` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `forest_forest_order` limit #{start},#{count}")
	public List<ForestOrder> list4page(Map<String,Object> param);

	@Select("select count(*) from `forest_forest_order`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `forest_forest_order`")
	public List<ForestOrder> listAll();
}