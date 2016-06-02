package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.OrderDiscount;
import com.qcloud.component.orderform.model.query.OrderDiscountQuery;

public interface OrderDiscountMapper {

	@Insert("insert into `orderform_order_discount`(`id`,`orderId`,`subOrderId`,`orderDate`,`discountId`,`price`,`type`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{orderDate},#{discountId},#{price},#{type})")
	public void insert(OrderDiscount orderDiscount);

	@Select("select * from `orderform_order_discount` where `id`=#{id}")
	public OrderDiscount get(Long id);

	@Update("update `orderform_order_discount` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderDate`=#{orderDate},`discountId`=#{discountId},`price`=#{price},`type`=#{type} where `id`=#{id}")
	public void update(OrderDiscount orderDiscount);

	@Delete("delete from `orderform_order_discount` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_order_discount` limit #{start},#{count}")
	public List<OrderDiscount> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_order_discount`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_order_discount`")
	public List<OrderDiscount> listAll();
}