package com.qcloud.component.orderform.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.orderform.model.RefundOrderItem;
import com.qcloud.component.orderform.model.query.RefundOrderItemQuery;

public interface RefundOrderItemMapper {

	@Insert("insert into `orderform_refund_order_item`(`id`,`orderId`,`subOrderId`,`orderItemId`,`state`,`time`,`number`,`sum`,`explain`,`reason`,`refundId`)"
			+ " values(#{id},#{orderId},#{subOrderId},#{orderItemId},#{state},#{time},#{number},#{sum},#{explain},#{reason},#{refundId})")
	public void insert(RefundOrderItem refundOrderItem);

	@Select("select * from `orderform_refund_order_item` where `id`=#{id}")
	public RefundOrderItem get(Long id);

	@Update("update `orderform_refund_order_item` set `orderId`=#{orderId},`subOrderId`=#{subOrderId},`orderItemId`=#{orderItemId},`state`=#{state},`time`=#{time},`number`=#{number},`sum`=#{sum},`explain`=#{explain},`reason`=#{reason},`refundId`=#{refundId} where `id`=#{id}")
	public void update(RefundOrderItem refundOrderItem);

	@Delete("delete from `orderform_refund_order_item` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `orderform_refund_order_item` limit #{start},#{count}")
	public List<RefundOrderItem> list4page(Map<String,Object> param);

	@Select("select count(*) from `orderform_refund_order_item`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `orderform_refund_order_item`")
	public List<RefundOrderItem> listAll();
}