package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.ReturnOrderItem;
import com.qcloud.component.orderform.model.query.ReturnOrderItemQuery;

public interface ReturnOrderItemMapper {

	@Insert("insert into `orderform_return_order_item`(`id`,`orderId`,`subOrderId`,`orderItemId`,`state`,`time`,`number`,`sum`,`explain`,`reason`,`returnId`,`returnType`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{orderItemId},#{state},#{time},#{number},#{sum},#{explain},#{reason},#{returnId},#{returnType})")
	public void insert(ReturnOrderItem returnOrderItem);

	@Select("select * from `orderform_return_order_item` where `id`=#{id}")
	public ReturnOrderItem get(Long id);

	@Update("update `orderform_return_order_item` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`state`=#{state},`time`=#{time},`number`=#{number},`sum`=#{sum},`explain`=#{explain},`reason`=#{reason},`returnId`=#{returnId},`returnType`=#{returnType}  where `id`=#{id}")
	public void update(ReturnOrderItem returnOrderItem);

	@Delete("delete from `orderform_return_order_item` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_return_order_item` limit #{start},#{count}")
	public List<ReturnOrderItem> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_return_order_item`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_return_order_item`")
	public List<ReturnOrderItem> listAll();
}