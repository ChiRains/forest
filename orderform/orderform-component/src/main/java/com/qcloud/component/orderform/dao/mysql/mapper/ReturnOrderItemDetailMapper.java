package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.ReturnOrderItemDetail;
import com.qcloud.component.orderform.model.query.ReturnOrderItemDetailQuery;

public interface ReturnOrderItemDetailMapper {

	@Insert("insert into `orderform_return_order_item_detail`(`id`,`orderId`,`subOrderId`,`orderItemId`,`orderItemDetailId`,`number`,`returnId`,`returnItemId`,`state`,`returnType`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{orderItemId},#{orderItemDetailId},#{number},#{returnId},#{returnItemId},#{state},#{returnType})")
	public void insert(ReturnOrderItemDetail returnOrderItemDetail);

	@Select("select * from `orderform_return_order_item_detail` where `id`=#{id}")
	public ReturnOrderItemDetail get(Long id);

	@Update("update `orderform_return_order_item_detail` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`orderItemDetailId`=#{orderItemDetailId},`number`=#{number},`returnId`=#{returnId},`returnItemId`=#{returnItemId},`state`=#{state},`returnType`=#{returnType} where `id`=#{id}")
	public void update(ReturnOrderItemDetail returnOrderItemDetail);

	@Delete("delete from `orderform_return_order_item_detail` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_return_order_item_detail` limit #{start},#{count}")
	public List<ReturnOrderItemDetail> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_return_order_item_detail`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_return_order_item_detail`")
	public List<ReturnOrderItemDetail> listAll();
}